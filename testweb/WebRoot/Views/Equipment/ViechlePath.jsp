<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ViechlePath.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="Resources/lib/jquery-1.6.min.js"></script>
	   <style>
    #keywords {
        position: absolute;
        right: 20px;
        top: 7px;
        line-height: 200%;
        padding: 0 5px;
        font-size: 13;
        border-radius: 3px;
        border: 1px solid #ccc;
        width: 150px;
    }
        #startTime {
        position: absolute;
        right: 20px;
        top: 67px;
        line-height: 200%;
        padding: 0 5px;
        font-size: 13;
        border-radius: 3px;
        border: 1px solid #ccc;
        width: 150px;
    }
        #endTime {
        position: absolute;
        right: 20px;
        top: 127px;
        line-height: 200%;
        padding: 0 5px;
        font-size: 13;
        border-radius: 3px;
        border: 1px solid #ccc;
        width: 150px;
    }
    </style>
    	<script type="text/javascript">

    	
    	
  $(document).ready(function(){

        $('#keywords').bind('keypress',function(event){  
  
            if(event.keyCode == "13")      
  
            {  
  
               searchPath();
  
            }  
  
        });
        });
        
        
        function searchPath(){
        var condition = $("#keywords").val();
      
         $.ajax({
			url : "CarOrbitAction",//==========================
			type : "post",
			 dataType: "json",
			data:
{
"condition":condition,
"startTime":$("#startTime").val(),
"endTime":$("#endTime").val()

},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			map.clearMap();
			try {data = JSON.parse(data);}
			catch(e){}
			
			if(data.length>0){
			var ViePath = new Array();
			for(var i =0;i<data.length;i++)
			{
			
			var locInfo=new AMap.LngLat(parseFloat(data[i].longitude),parseFloat(data[i].latitude));
			if(i==0){
			if(carMarker!=null)
			carMarker.setMap(null);
			carMarker =new  AMap.Marker({
        map: map,
        position: locInfo,
        icon: "http://webapi.amap.com/images/car.png",
        offset: new AMap.Pixel(-26, -13),
        autoRotation: true
    });
}//end if
newMarker = new  AMap.Marker({
        map: map,
        position: locInfo,
        icon: null,
        title:data[i].date,
     
    });


			ViePath.push(locInfo);
			}//end for
			
var polyline = new AMap.Polyline({
        map: map,
        path: ViePath,
        strokeColor: "#00A",  //线颜色
        // strokeOpacity: 1,     //线透明度
        strokeWeight: 3,      //线宽
        // strokeStyle: "solid"  //线样式
    });
    map.setZoomAndCenter(12,carMarker.getPosition( ));
     carMarker.moveAlong(ViePath, 2000);
             }
    }
    });
 }
        </script>
  </head>
  
  <body>

 <div id="container"  style="width:100%; height:100%">  </div>
	   <input type="text" id="keywords" value="" />

    <input type="date" id="startTime">
    <input type="date" id="endTime">
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&amp;key=07016be98c6769fa4acd03e91dab6589"></script>
		<script src="//webapi.amap.com/ui/1.0/main.js"></script>
	<script type="text/javascript">
        var map = new AMap.Map('container',{
            resizeEnable: true,
            zoom: 12,
            center: [116.369962, 39.872351]
        });
        var carMarker,newMarker;
        var markers =  new Array();
        var markersid = new Array();



  
             
          

         
        
    </script>
  </body>
</html>
