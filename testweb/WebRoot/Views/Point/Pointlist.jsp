<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Point"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>

<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../css/bootstrap_3.0.3/css/bootstrap.min1.css" rel="stylesheet">
<link rel="stylesheet"
	href="../../css/bootstrap_3.0.3/css/font-awesome.min1.css">

<script src="css/bootstrap_3.0.3/js/jquery-1.8.2.min.js"></script>
<script src="css/bootstrap_3.0.3/js/bootstrap.min1.js"></script>
<script src="Resources/lib/jquery-1.11.1.min.js"></script>
<title>My page</title>
<script type="text/javascript">
$(document).ready(function(){
searchPointbyName();
});


function searchPointbyName(){

    var condition = $("#condition").val();
   
     $.ajax({
			url : "PointInfoListAction",//==========================
			type : "post",
			 dataType: "json",
			data:
{
"condition":condition
},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			 map.remove(markers);
			 markers.splice(0,markers.length);
			 markersid.splice(0,markersid.length);
			try {data = JSON.parse(data);}
			catch(e){}
			 map.remove(markers);
			 markers.splice(0,markers.length);
			 markersid.splice(0,markersid.length);
			var pointInfoList = "";
			for(var i =0;i<data.length;i++)
			{
			addMarker(data[i].longitude,data[i].latitude,data[i].pointname,data[i].pointid);
			pointInfoList+="<tr onclick =\"printem("+i+")\">";
			pointInfoList+="<td>"+data[i].pointname+"</td>";
			pointInfoList+="<td>"+data[i].longitude+","+data[i].latitude+"</td>";
			pointInfoList+="<td>"+data[i].equipment+"</td>";
			pointInfoList+="<td><a onclick=\"toModify("+i+")\">修改</a>/<a onclick=\"pointDelete("+i+")\">删除</a></td>";
		pointInfoList+="</tr>";
			}
			
			$("#PointTable").empty();
			$("#PointTable").append(pointInfoList);
	
    }
    });
    };
	function resetValue() {
		
	};
	var newmarker,oldmarker;
	function addMarker(jd,wd,ti,id){
	  var  newmarker = new AMap.Marker({
				position: [jd,wd],
				icon:"add.png",
				title:ti,
				map: map
			});
			markers.push(newmarker);
			markersid.push(id);
			
			};
	function printem(i){
			map.setZoomAndCenter(12, markers[i].getPosition( ));
			markers[i].setIcon(null);
			setTimeout("resetMarker("+i+")",1000); 
			
			};
				function  resetMarker(i){
			markers[i].setIcon("add.png");
			};
			
		var getLocation = function (e) {
                if(newmarker!=null){
                 oldmarker = newmarker;
                 oldmarker.setMap(null);}
                 newmarker = new AMap.Marker({
				position: e.lnglat,
				icon:"add.png",
				map: map
			});
			 document.getElementById("PointLongitude").value = e.lnglat.getLng();
			  document.getElementById("PointLatitude").value = e.lnglat.getLat();	
    };

function pointDelete(i){
   $.ajax({
url:"PointDeleteAction",
type: "get",
data:
{
"PointId":markersid[i]
},
success : function(data) {
alert("删除点位成功！");
window.location.reload();
}
});
};
function submitModify(){
$("#sub").attr("disabled", true); 
	$.ajax({
			url : "PointEditPageAction",
			type : "get",
			
			data:encodeURI($('#pointInfo').serialize()),
			success : function(data) {
			    
			 	if(data!=2){
				alert("修改成功！");
				window.location.reload();}
				else
				alert("修改失败！");
				$("#sub").removeAttr("disabled"); 
				
		}
     
		});
}


function submitAdd(){
$("#sub").attr("disabled", true); 
	$.ajax({
			url : "PointAddAction",
			type : "get",
			
			data:encodeURI($('#pointInfo').serialize()),
			success : function(data) {
			    
			 	if(data!=2){
				alert("添加成功！");
				window.location.reload();}
				else
				alert("添加失败！");
				$("#sub").removeAttr("disabled"); 
				
		}
     
		});
}

function toAddPoint(){
$("#PointList").fadeOut();
$("#addPoint").fadeIn();
$("#PointLongitude").val(null);
$("#PointLatitude").val(null);
$("#PointName").val(null);
$("#p1").text("增加点位");
$("#sub").text("添加");
$("#sub").attr("onclick","submitAdd()");
map.on('click',getLocation);
};
function toPointList(){
$("#PointList").fadeIn();
$("#addPoint").fadeOut();
map.off('click',getLocation);
};
function toModify(i){
$("#PointList").fadeOut();
$("#addPoint").fadeIn();
$("#PointLongitude").val(markers[i].getPosition().getLng());
$("#PointLatitude").val(markers[i].getPosition().getLat());
$("#PointName").val(markers[i].getTitle());
$("#PointId").val(markersid[i]);
$("#p1").text("修改点位");
$("#sub").text("修改");
$("#sub").attr("onclick","submitModify()");
newmarker = markers[i];
map.on('click',getLocation);
}

 


	
</script>
</head>






<body>


  <div id="container"  style="width:100%; height:50%">  </div>
	

	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&amp;key=07016be98c6769fa4acd03e91dab6589"></script>
		
	<script type="text/javascript">
        var map = new AMap.Map('container',{
            resizeEnable: true,
            zoom: 10,
            center: [116.369962, 39.872351]
        });
        var markers =  new Array();
        var markersid = new Array();
        
    </script>


<div id = "PointList">
	<h3><small>点位查询</small></h3>
	<br>

	<form action="PointInfoListAction" method="post">



		<div class=" ">
			<div class="">
				<input name="condition" type="text" class="form-control" style="" id="点位"
					placeholder="">
				<button type="submit" class="btn btn-default" id="查询">查询</button>

				
			</div>
		</div>

	</form>

 <a onclick="toAddPoint()" ><i class="icon-edit"></i> 添加</a>	

	<table border="1"  width=100% height = 50% class="table table-bordered">
<thead>
		<tr>
			<th colspan='7'><label class="label label-danger" id="点位信息">&nbsp;&nbsp;点位信息列表</label></th>
		</tr>

		<tr>
			<td><label class="label label-default">点位名称</label></td>
			<td><label class="label label-default" >点位经纬度</label></td>
			<td><label class="label label-default" >设备数量</label></td>
			<td><label class="label label-default" >操作</label></td>
		</tr>
		</thead>
<tbody  id = "PointTable" ></tbody>

	</table>
	</div>
	<div id= "addPoint" >
	<p id = "p1">添加点位</p>
	<br>

	<br>
	<br>
	<form id=pointInfo >
	<input type  = hidden
					name="PointId" type="text" class="form-control" id="PointId">
		<div class=" ">
			<div class=" ">
				<label for="NewIdName" class="lable label-danger ">点位名称：</label> <input
					name="PointName" type="text" class="form-control" id="PointName"
					>
			</div>
		</div>

		<br>
		<div class=" ">
			<div class=" ">
				<label for="PointLongitude" class="label label-warning ">点位经度：</label> <input 
					name="PointLongitude" class="form-control" id="PointLongitude" onfocus=this.blur()
					>
			</div>
		</div>
		<br>

		<div class=" ">
			<div class=" ">
				<label for="PointLatitude" class="label label-default ">点位纬度：</label> <input 
					name="PointLatitude" class="form-control" id="PointLatitude" onfocus=this.blur()
					>
			</div>
		</div>
		<br>
		<div class="">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
			&nbsp;&nbsp;
			 <a onclick="toPointList()" ><i class="icon-edit"></i> 取消</a>	
			&nbsp;&nbsp;
			<button id = "sub" type="submit" class="btn btn-info">&nbsp;添加&nbsp;</button>
		</div>

	</form>
			</div>
				<script type="text/javascript">
				$("#addPoint").hide();
				</script>

</body>
</html>