sql学习:https://xz.aliyun.com/t/6872

预编译:

https://www.cnpanda.net/sec/589.html

```java
String sql = "select * from t_student where name = ? and content = ?"
try {
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1,name);
    ps.setString(2,content);
    ps.executeUpdate(sql_update);
}catch(Exception e){
    e.printStackTrace();
}
```

当出现order by的时候,是无法通过预编译处理的,此时需要手动过滤,不然任会触发漏洞

```java
  String sql = "select * from userinfo where id = ？"+"order by ''" + age + "' asc'" ;
```



## 框架

MyBatis

https://www.anquanke.com/post/id/190170#h2-4

