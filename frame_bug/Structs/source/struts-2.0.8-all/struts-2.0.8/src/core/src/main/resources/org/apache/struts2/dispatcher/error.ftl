<html>
<head>
    <title>Struts Problem Report</title>
    <style>
    	pre {
	    	margin: 0;
	        padding: 0;
	    }    
    </style>
</head>
<body>
    <h2>Struts Problem Report</h2>
    <p>
    Struts has detected an unhandled exception:
    </p>

<#assign msgs = [] />
<#list chain as ex>
    <#if ex.message?exists>
        <#assign msgs = [ex.message] + msgs/>
    </#if>    
</#list>
<#assign rootex = exception/>
<#list chain as ex>
    <#if (ex.location?exists && (ex.location != unknown))>
        <#assign rootloc = ex.location/>
        <#assign rootex = ex/>
    <#else>
            <#assign tmploc = locator.getLocation(ex) />
            <#if (tmploc != unknown)>
            <#assign rootloc = tmploc/>
                <#assign rootex = ex/>
            </#if>  
    </#if>    
</#list>

<div id="exception-info">
<table>
    <tr>
        <td><strong>Messages</strong>:</td>
        <td>
            <#if (msgs?size > 1)>
            <ol>
                <#list msgs as msg>
                    <#if (msg?is_method)>
                        <li>${msg[0]}</li>
                    <#else>
                        <li>${msg}</li>
                    </#if>
                </#list>
            </ol>
            <#elseif (msgs?size == 1)>
                <#if (msgs[0]?is_method)>
                    <li>${msgs[0][0]}</li>
                <#else>
                    <li>${msgs[0]}</li>
                </#if>
            </#if>
        </td>
    </tr>
    <#if rootloc?exists>
    <tr>
        <td><strong>File</strong>:</td>
        <td>${rootloc.URI}</td>
    </tr>
    <tr>
        <td><strong>Line number</strong>:</td>
        <td>${rootloc.lineNumber}</td>
    </tr>
    <#if (rootloc.columnNumber >= 0)>
    <tr>
        <td><strong>Column number</strong>:</td>
        <td>${rootloc.columnNumber}</td>
    </tr>
    </#if>
    </#if>
    
</table>
</div>

<#if rootloc?exists>
    <#assign snippet = rootloc.getSnippet(2) />
    <#if (snippet?size > 0)>
        <div id="snippet">
        <hr />
            
            <#list snippet as line>
                <#if (line_index == 2)>
                	<#if (rootloc.columnNumber >= 3)>
                        <pre style="background:yellow">${(line[0..(rootloc.columnNumber-3)]?html)}<span style="background:red">${(line[(rootloc.columnNumber-2)]?html)}</span><#if ((rootloc.columnNumber)<line.length())>${(line[(rootloc.columnNumber-1)..]?html)}</#if></pre>
                    <#else>
                       	<pre style="background:yellow">${line?html}</pre>
                    </#if>    
                <#else>
                    <pre>${line?html}</pre>
                </#if>    
            </#list>
        </div>
    </#if>    
</#if>

<div id="stacktraces">
<hr />
<h3>Stacktraces</h3>
<#list chain as ex>
<div class="stacktrace" style="padding-left: ${ex_index * 2}em">
    <strong>${ex}</strong>
    <div>
    <pre>
    <#list ex.stackTrace as frame>
    ${frame}
    </#list>
    </pre>
    </div>
</div>
</#list>
</div>

<div class="footer">
<hr />
<p>
You are seeing this page because development mode is enabled.  Development mode, or devMode, enables extra
debugging behaviors and reports to assist developers.  To disable this mode, set:
<pre>
  struts.devMode=false
</pre>
in your <code>WEB-INF/classes/struts.properties</code> file.
</p>
</div>
</body>
</html>
