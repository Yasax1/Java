<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Ajax examples - tabbled panel</title>

    <jsp:include page="/ajax/commonInclude.jsp"/>
    <link rel="stylesheet" type="text/css" href="<s:url value="/struts/tabs.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/struts/niftycorners/niftyCorners.css"/>">
    <link rel="stylesheet" type="text/css" href="<s:url value="/struts/niftycorners/niftyPrint.css"/>" media="print">
    <script type="text/javascript" src="<s:url value="/struts/niftycorners/nifty.js"/>"></script>
    <script type="text/javascript">
        dojo.event.connect(window, "onload", function() {
            if (!NiftyCheck())
                return;
            Rounded("li.tab_selected", "top", "white", "transparent", "border #ffffffS");
            Rounded("li.tab_unselected", "top", "white", "transparent", "border #ffffffS");
            //                Rounded("div#tab_header_main li","top","white","transparent","border #ffffffS");
            // "white" needs to be replaced with the background color
        });
    </script>
</head>

<body>

    <table cellpadding="0" cellspacing="10" border="0" width="600">
        <tr>
            <td align="top">
                <s:tabbedPanel id="test" >
                    <s:panel id="one" tabName="one">
                        This is the first pane<br/>
                        <s:form>
                            <s:textfield name="tt" label="Test Text"/>  <br/>
                            <s:textfield name="tt2" label="Test Text2"/>
                        </s:form>
                    </s:panel>
                    <s:panel id="two" tabName="two">
                        This is the second panel
                    </s:panel>
                    <s:panel id="three" tabName="three">
                        This is the three
                    </s:panel>
                </s:tabbedPanel>
            </td>
            <td align="top">
                <s:tabbedPanel id="test2" >
                    <s:panel id="left" tabName="left">
                        This is the left pane<br/>
                        <s:form>
                            <s:textfield name="tt" label="Test Text"/>  <br/>
                            <s:textfield name="tt2" label="Test Text2"/>
                        </s:form>
                    </s:panel>
                    <s:panel remote="true" href="/AjaxTest.action" id="ryh1" theme="ajax"
                                    tabName="remote one"></s:panel>
                    <s:panel id="middle" tabName="middle">
                        middle tab<br/>
                        <s:form>
                            <s:textfield name="tt" label="Test Text44"/>  <br/>
                            <s:textfield name="tt2" label="Test Text442"/>
                        </s:form>
                    </s:panel>
                    <s:panel remote="true" href="/AjaxTest.action" id="ryh21" theme="ajax" tabName="remote right"/>
                </s:tabbedPanel>
            </td>
        </tr>
        <tr>
            <td align="top">
                <s:tabbedPanel id="testremote">
                    <s:panel remote="true" href="/AjaxTest.action" id="r1" theme="ajax" tabName="remote one">
                        <s:action name="AjaxTest" executeResult="true" />
                    </s:panel>
                    <s:panel remote="true" href="/AjaxTest.action" id="r2" theme="ajax" tabName="remote two"></s:panel>
                    <s:panel remote="true" href="/AjaxTest.action" id="r3" theme="ajax" tabName="remote three"></s:panel>
                </s:tabbedPanel>
            </td>
            <td align="top">
                <s:tabbedPanel id="test3" >
                    <s:panel id="left1" tabName="out one">
                        Outer one<br/>
                        <s:tabbedPanel id="test11">
                            <s:panel id="i11" tabName="inner 1 one">Inner 1</s:panel>
                            <s:panel id="112" tabName="inner 1 two">Inner 2</s:panel>
                            <s:panel id="i13" tabName="inner 1 three">Inner 3</s:panel>
                        </s:tabbedPanel>
                    </s:panel>
                    <s:panel id="middle1" tabName="out two">
                        Outer two<br/>
                        <s:tabbedPanel id="test12" >
                            <s:panel id="i21" tabName="inner 2 one">Inner 21</s:panel>
                            <s:panel id="122" tabName="inner 2 two">Inner 22</s:panel>
                            <s:panel id="i23" tabName="inner 2 three">Inner 23</s:panel>
                        </s:tabbedPanel>
                    </s:panel>
                    <s:panel id="right1" tabName="out three">
                        Outer three<br/>
                        <s:tabbedPanel id="test13">
                            <s:panel id="i31" tabName="inner 3 one">Inner 31</s:panel>
                            <s:panel id="132" tabName="inner 3 two">Inner 32</s:panel>
                            <s:panel id="i33" tabName="inner 3 three">Inner 33</s:panel>
                        </s:tabbedPanel>
                    </s:panel>
                </s:tabbedPanel>
            </td>
        </tr>
    </table>

<s:include value="../footer.jsp"/>


</body>
</html>
