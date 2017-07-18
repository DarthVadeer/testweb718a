<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Frame.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/main_css/frame11.css">
	

  </head>
  
 
	<!-- Frame structure:upper, middle (left, right), under-->
	<frameset rows="100,*,70"  frameborder="no" border="0">
		<frame src="Views/Frame/Header.jsp"  scrolling="no"/>
		<frameset cols="15%,*">
			<frame src="Views/Frame/Left.jsp"  scrolling="auto"/>
			
			<frame src="Views/Frame/Map.jsp"  name="map"  scrolling="auto"/> 
		</frameset >
		
		<frame src="Views/Frame/Footer1.jsp" scrolling="no"/>
	</frameset>
	<!-- Set prompt message for browser that does not support Frame structure-->
	<noframes>
		<body>
			Your browser does not support frame structure!
		</body>
	</noframes>
</html>
