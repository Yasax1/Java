我觉得可以先看一下这个文章

http://m0d9.me/2021/05/08/Xstream%E5%8F%8D%E5%BA%8F%E5%88%97%E5%8C%96%E8%AF%A6%E8%A7%A3%EF%BC%88%E4%B8%80%EF%BC%89/

https://blog.0kami.cn/2021/03/14/java-how-to-find-gadget-chains/

XStream系列

XStream反序列化和fastjson的反序列化不同,fastjson会进行动态的调用getter,setter方法。但XStream是通过一个反射来进行赋值的。

并不具备一个动态调用。但是在java中有些特殊的类。在

HashMap、PriorityQueue等对象（key不可重复等特性）会自动去调用hashCode、equal、compareTo等这种函数。





