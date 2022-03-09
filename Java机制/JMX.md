jmx简介和使用(归档里面还有更多)https://honeypps.com/java/jmx-quick-start-1-standard-mbean/

漏洞介绍https://www.anquanke.com/post/id/194126

简单来说,就是我们将设置好的mlet文件还有恶意的PayloadMbean设置在网站上可供访问,然后服务端如果使用了远程Mbean执行了getMBeansFromURL函数,传入的参数为网站的mlet文件的url,则可以加载payloadMbean然后执行恶心代码

还没有自己操作过,初步理解是这样,具体遇到了再说