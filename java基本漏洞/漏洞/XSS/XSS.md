反射性xss:

```java
public void Message(HttpServletRequest req, HttpServletResponse resp) {
        // TODO Auto-generated method stub
        String message = req.getParameter("msg");

        try {
            resp.getWriter().print(message);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
```

修复方案:

1.设置全局过滤器

```xml
<filter>  
        <filter-name>XssSafe</filter-name>  
        <filter-class>XssFilter</filter-class>  
    </filter>  
    <filter-mapping>  
        <filter-name>XssSafe</filter-name>  
        <url-pattern>/*</url-pattern>  
    </filter-mapping>

```

> 我们的配置是`/*`而不是`/`，`< url-pattern>/</url-pattern>` 会匹配到`/login`这样的路径型url，不会匹配到模式为`*.jsp`这样的后缀型url，而`< url-pattern>/*</url-pattern>`会匹配所有url

2.使用工具库

比如谷歌的一个防止XSS攻击库https://code.google.com/archive/p/xssprotect/

3.commons.lang包

在这个包中有个StringUtils 类，该类主要提供对字符串的操作，对null是安全的，主要提供了字符串查找、替换、分割、去空白、去掉非法字符等等操作。存在三个函数可以供我们过滤使用。

- StringEscapeUtils.escapeHtml(string)
  使用HTML实体，转义字符串中的字符。

```
"bread" & "butter"
```

```
&quot;bread&quot; &amp; &quot;butter&quot;
```

- StringEscapeUtils.escapeJavaScript(string)

使用JavaScript字符串规则转义字符串中的字符。

```
input string: He didn't say, "Stop!"
```

```
output string: He didn\'t say, \"Stop!\"
```

## 过滤绕过

alert,onerror

```
<object data="data:text/html;base64,PHNjcmlwdD5hbGVydCgiSGVsbG8iKTs8L3NjcmlwdD4=">
<svg/onLoad=confirm(1)>
<img src="x" ONERROR=confirm(0)>
```

