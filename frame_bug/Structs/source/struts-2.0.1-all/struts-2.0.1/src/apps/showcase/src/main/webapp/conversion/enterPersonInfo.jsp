<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Showcase - Conversion - Populate Object into Struts' action List</title>
</head>
<body>

<p/>
An example populating a list of object (Person.java) into Struts' action (PersonAction.java)

<p/>

See the jsp code <s:url id="url" action="showPersonJspCode" namespace="/conversion" /><s:a href="%{#url}">here.</s:a><br/>
See the code for PersonAction.java <s:url id="url" action="showPersonActionJavaCode" namespace="/conversion" /><s:a href="%{#url}">here.</s:a><br/>
See the code for Person.java <s:url id="url" action="showPersonJavaCode" namespace="/conversion" /><s:a href="%{#url}">here.</s:a><br/>

<p/>

<s:actionerror />
<s:fielderror />

<s:form action="submitPersonInfo" namespace="/conversion" method="post">
    <%--
        The following is done Dynamically
    --%>
    <s:iterator value="new int[3]" status="stat">
        <s:textfield    label="%{'Person '+#stat.index+' Name'}" 
                        name="%{'persons['+#stat.index+'].name'}" />
        <s:textfield    label="%{'Person '+#stat.index+' Age'}" 
                        name="%{'persons['+#stat.index+'].age'}" />
    </s:iterator>
    
    
    
    <%--
    The following is done statically:-
    --%>
    <%-- 
    <s:textfield    label="Person 1 Name" 
                    name="persons[0].name" />
    <s:textfield    label="Person 1 Age"
                    name="persons[0].age" />
    <s:textfield    label="Person 2 Name" 
                    name="persons[1].name" />
    <s:textfield    label="Person 2 Age"
                    name="persons[1].age" />
    <s:textfield    label="Person 3 Name" 
                    name="persons[2].name" />
    <s:textfield    label="Person 3 Age"
                    name="persons[2].age" />
    --%>                
                    
    <s:submit />
</s:form>

</body>
</html>