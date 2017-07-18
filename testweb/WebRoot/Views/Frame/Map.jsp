<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Map.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   
   
    <div id="container"  style="width:100%; height:100%">  </div>
	

	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.3&key=07016be98c6769fa4acd03e91dab6589"></script>
		
	<script type="text/javascript">
        var map = new AMap.Map('container',{
            resizeEnable: true,
            zoom: 10,
            center: [121.480983, 37.0958]
        });
    </script>
    
    
    <form>
    
    
    
    
    
    
    
    </form>
    
    
    
    
    
    
    
    
    
    
    
    
    
  </body>
</html>
