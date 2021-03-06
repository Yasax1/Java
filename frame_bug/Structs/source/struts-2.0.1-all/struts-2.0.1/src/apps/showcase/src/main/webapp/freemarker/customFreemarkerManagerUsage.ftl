
<html>
<head>
	<title>Showcase - Freemarker - CustomFreemarkerManager Usage</title>
</head>
<body>
	<h1>Custom Freemarker Manager Usage</h1>
	
	<p/>
	This page shows a simple example of using a custom freemarker manager.
	The custom freemarker manager put into freemarker model an util classed 
	under the name 'customFreemarkerManagerUtil'. so one could use
	<p/>
	<ul>
		<li>$ { customFreemarkerManagerUtil.getTodayDate() } - to get today's date</li>
		<li>$ { customFreemarkerManagerUtil.todayDate } - to get today's date</li>
		<li>$ { customFreemarkerManagerUtil.getTimeNow() } - to get the time now</li>
		<li>$ { customFreemarkerManagerUtil.timeNow } - to get the time now</li>
	</ul>
	 
	 Today's Date = ${customFreemarkerManagerUtil.todayDate}<br/>
	 Time now = ${customFreemarkerManagerUtil.getTimeNow()}<br/>

</body>
</html>

