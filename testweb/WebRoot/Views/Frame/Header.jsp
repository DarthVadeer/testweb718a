<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PVCS - Head</title>
<link href="../../Resources/css/main_css/frame11.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="header" style="background-image: url(../../Resources/img/header2.jpg);">
		<div class="toplinks">
			<%
					String userName = (String) session.getAttribute("userName");
				%>
			<span>Hello:&nbsp;<%=userName%> &nbsp;&nbsp; Welcome to PVCS [<a
				href="LogoutAction" >Logout</a>]
			</span>
			<!--Logout     target="_top" -->
		</div>
		<!--  <h1>
				<img src="img/progress.gif" alt="PVCS" />
			</h1>
			-->
	</div>
</body>
</html>