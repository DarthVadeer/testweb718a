
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- Bootstrap -->
<link href="../../css/bootstrap_3.0.3/css/bootstrap.min1.css" rel="stylesheet">
<link rel="stylesheet"
	href="../../css/bootstrap_3.0.3/css/font-awesome.min1.css">

<script src="css/bootstrap_3.0.3/js/jquery-1.8.2.min.js"></script>
<script src="css/bootstrap_3.0.3/js/bootstrap.min1.js"></script>
<base href="<%=basePath%>">

<title>Road page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="Road">

<script src="Resources/lib/jquery-1.11.1.min.js"></script>





<style>
#Road{

width:70%;
float:left;
}
#AddRoad{
 width:30%;
 
	
float:right;
}
</style>


<script>

function toAddRoad() {
	
		$("#Road").fadeOut();
		$("#AddRoad").fadeIn();
		$("#RoadPointList").empty();
$("#RoadPoint").val("");
		$("#RoadName").val(null);
		$("#p1").text("增加道路");
		$("#sub").text("添加");
		$("#sub").attr("onclick", "addRoadSubmit()");
		map.on('click', addFirstPoint);
		if(newPoly!=null)
		newPoly.setMap(null);
		
	};
var newmarker, polyline,polylineEditor,newPoly,oldPoly;

function loadLocation(){
$("#RoadPosition").val(polyline.getPath( ));
};

function AddPointToRoadOn(){

for(var i = 0;i < markers.length; i++) {
addMarkerOn(i);
}
};


$(document).ready(function(){
	$("#AddRoad").hide();
});


function addMarkerOn(i){


       markers[i].on('click', function() {
        
       addPointToRoad(markersid[ searchPointbyMarker(markers[i])]);
       
     markers[i].setIcon(null);
   
    });

 
};


function addPointToRoad(id){

var str = $("#RoadPoint").val();
var roadListSen =""; 
if(str!=""&&str!=null&&str!="null")
str+=",";
else
str="";
if(!(str.indexOf(id) >= 0)){
str+=id;
roadListSen+="<tr  id= \"pointOnRoad"+searchPointbyId(id)+"\"><td onclick=\"showPoint("+searchPointbyId(id)+")\">"+findPointNameById(id)+"</td><td onclick=\"removeFromRoad("+searchPointbyId(id)+")\">删除</td></tr>";

}
$("#RoadPoint").val(str);
$("#RoadPointList").append(roadListSen);
};

	function showPoint(i){
			map.setZoomAndCenter(12, markers[i].getPosition( ));
			markers[i].setIcon("add.png");
			setTimeout("resetMarker("+i+")",1000); 
			
			};
				function  resetMarker(i){
			markers[i].setIcon(null);
			};

function findPointNameById(id){

return markers[searchPointbyId(id)].getTitle();
};

function removeFromRoad(index){

$("#pointOnRoad"+index).remove();
markers[index].setIcon("add.png");
var str = $("#RoadPoint").val();

var strs = new Array();

strs = str.split(",");

str="";

for (i=0;i<strs.length ;i++ ) {
if(markersid[index]!=strs[i])
str+=strs[i]+",";
}

str=str.substring(0,str.length-1);
$("#RoadPoint").val(str);

};


function searchPointbyId(id){

for(var i = 0;i < markersid.length; i++) {

if(id==markersid[i]){

return i;}
}
};

function searchPointbyMarker(marker){
for(var i = 0;i < markers.length; i++) {
if(marker==markers[i]){
return i;}
}
};

function addMarker(jd,wd,ti,id){

	 newmarker = new AMap.Marker({
				position: [jd,wd],
				icon:"add.png",
				title:ti,
				map: map
			});
			markers.push(newmarker);
			
			markersid.push(id);
			
			};
			
function addFirstPoint(e){
			 newmarker = new AMap.Marker({
				position: e.lnglat,
				icon:null,
				map: map
			});
			map.off('click', addFirstPoint);
			map.on('click', addSecondPoint);
			$("#RoadCPoint").val(e.lnglat);
			};
			
function addSecondPoint(e){
			var lineArr = [
			newmarker.getPosition(),
			e.lnglat
			];
			
		polyline = new AMap.Polyline({
        path: lineArr,          //设置线覆盖物路径
        strokeColor: "#3366FF", //线颜色
        strokeOpacity: 1,       //线透明度
        strokeWeight: 5,        //线宽
        strokeStyle: "solid",   //线样式
        strokeDasharray: [10, 5] //补充线样式
    });
     polyline.setMap(map);

			newmarker.setMap(null);
			
			    map.plugin(["AMap.PolyEditor"],function(){
			    if(polylineEditor!=null)
			    polylineEditor.close();
			    polylineEditor = null;
        polylineEditor = new AMap.PolyEditor(map,polyline); 
        polylineEditor.open(); 
        $("#RoadPosition").val(polyline.getPath( ));
        polyline.on('change',loadLocation);
    });  
			map.off('click', addSecondPoint);
			};
			
function removeEditor(){
			polylineEditor.close();
			polyline.setMap(null);
			map.on('click', addFirstPoint);
			var str = $("#RoadPoint").val();
			$("#RoadPoint").val("");
			$("#RoadPosition").val("")
			reomvePointByIds(str);
			$("#RoadCPoint").val();
			};
			
function reomvePointByIds(str){
			
			var strs= new Array(); //定义一数组 
strs=str.split(","); //字符分割 
for (i=0;i<strs.length ;i++ ) 

markers[searchPointbyId(strs[i])].setIcon("add.png");

			};
			
function addPointByIds(str){

			if(str!=null&&str!="null"){
			
			strs=str.split(","); //字符分割 

for (i=0;i<strs.length ;i++ ) {
addPointToRoad(strs[i]);
var index = searchPointbyId(strs[i]);

if(index>=0){
markers[index].setIcon(null);

}
}
}

};
			
	function addRoadSubmit(){
			$.ajax({
			url : "RoadAddAction",
			type : "get",
			
			data:encodeURI($('#RoadInfo').serialize()),
			success : function(data) {
			    
			 	if(data==1){
				alert("添加成功！");
				window.location.reload();}
				else
				alert("添加失败！");
				
                 
	
	
		}
     
		});
			
			};
			
function deleteRoadsubmit(id){
				$.ajax({
			url : "RoadDeleteAction",
			type : "get",
			
			data:{
			"RoadId":id
			},
			success : function(data) {
			    
			 	if(data==1){
				alert("修改成功！");
				window.location.reload();}
				else
				alert("修改失败！");
			}
			});
			
			};
			
			
function modifyRoadSubmit(){
				
			$.ajax({
			url : "RoadUpdateAction",
			type : "get",
			
			data:encodeURI($('#RoadInfo').serialize()),
			success : function(data) {
			    
			 	if(data==1){
				alert("修改成功！");
				window.location.reload();}
				else
				alert("修改失败！");
				
		}
     
		});
			
			};



	
	function toRoad() {
		$("#Road").fadeIn();
		$("#AddRoad").fadeOut();
		map.off('click', addFirstPoint);
	}
	;
	function setInitInfo(id,name){
			$("#Road").fadeOut();
		$("#AddRoad").fadeIn();
		$("#RoadName").val(name);
		$("#RoadId").val(id);
		$("#RoadPointList").empty();
		
		$("#p1").text("修改道路");
		$("#sub").text("修改");
		$("#sub").attr("onclick", "modifyRoadSubmit()");
		return 1;
	}
	
	
	function toModifyRoad(id,name,points,paths) {
	alert(id);
	alert(name);
	alert(points);
	alert(paths);
if(setInitInfo(id,name)==1)
		if(points!=null&points!="null")
		addPointByIds(points);
		var lineArr = new Array();
var strs= new Array(); //定义一数组 
strs=paths.split(","); //字符分割 


for (i=0;i<strs.length ;i++ ) 
{ 

var pot = new AMap.LngLat(parseFloat(strs[i++]),parseFloat(strs[i]));
lineArr.push(pot);
} 
	if(polyline!=null)
		polyline.setMap(null);
		polyline = new AMap.Polyline({
        path: lineArr,          //设置线覆盖物路径
        strokeColor: "#3366FF", //线颜色
        strokeOpacity: 1,       //线透明度
        strokeWeight: 5,        //线宽
        strokeStyle: "solid",   //线样式
        strokeDasharray: [10, 5] //补充线样式
    });
    if(newPoly!=null)
    newPoly.setMap(null);
     polyline.setMap(map);
		
		
		   map.plugin(["AMap.PolyEditor"],function(){
			    if(polylineEditor!=null)
			    polylineEditor.close();
			    polylineEditor = null;
        polylineEditor = new AMap.PolyEditor(map,polyline); 
        polylineEditor.open(); 
        $("#RoadPosition").val(polyline.getPath( ));
        polyline.on('change',loadLocation);
    });  
	};
	
function resetMarkers(){
for(var i= 0 ;i<markers.length;i++)
markers[i].setIcon("add.png");
}


function showRoad(paths,points){
resetMarkers();
var lineArr = new Array();
var strs= new Array(); //定义一数组 
strs=paths.split(","); //字符分割 

addPointByIds(points);


for (i=0;i<strs.length ;i++ ) 
{ 

var pot = new AMap.LngLat(parseFloat(strs[i++]),parseFloat(strs[i]));
lineArr.push(pot);
} 
map.setZoomAndCenter(12, lineArr[0]);
	if(newPoly!=null)
		newPoly.setMap(null);
		newPoly = new AMap.Polyline({
        path: lineArr,          //设置线覆盖物路径
        strokeColor: "#3366FF", //线颜色
        strokeOpacity: 1,       //线透明度
        strokeWeight: 5,        //线宽
        strokeStyle: "solid",   //线样式
        strokeDasharray: [10, 5] //补充线样式
    });
   
     newPoly.setMap(map);

};
	

	
	

</script>



</head>

<body>

	
	<br>

	<div id="container" style="width:100%; height:50%"></div>


	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.3&key=07016be98c6769fa4acd03e91dab6589"></script>

	<script type="text/javascript">
		var map = new AMap.Map('container', {
			resizeEnable : true,
			zoom : 10,
			center : [ 116.369962, 39.872351 ]
		});
		var pointIds = new Array();
		     var markers =  new Array();
        var markersid = new Array();
        var pointIdsOnRoad = new Array();
		
	</script>

<%  List<Point> pointList =(List<Point>)request.getAttribute("pointlist");  
for (int i = 0; i <pointList.size();i++) {%> 
	
		<script type="text/javascript">
		addMarker('<%=pointList.get(i).getLongitude()%>','<%=pointList.get(i).getLatitude()%>','<%=pointList.get(i).getPointname()%>','<%=pointList.get(i).getPointid()%>');
		</script>
		<%} %>

	<h3>道路管理</h3>
	<br>
	<br>


	<div id="Road">



		<form action="RoadQueryAction" method="post">

			<div class=" ">
				<div class="">
					<input name="condition" type="text" class="form-control" style="" id="Road"
						placeholder="道路名称">
					<button type="submit" class="btn btn-default" id="Serch">查询</button>
					
				</div>
			</div>



		</form>

		<a onclick="toAddRoad()">添加</a>

		<table border="1" cellspacing="0"class="table table-bordered">
			<tr>
				<th colspan='5'><label class="" id="RoadList">&nbsp;&nbsp;道路信息</label></th>
			</tr>

			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>

				<td><label class="">道路名称</label></td>
			
				<td width="300"><label class="">所属点位</label></td>
				<td><label class="" >修改信息</label></td>
			</tr>
<%  List<Road> roadList =(List<Road>)request.getAttribute("list");  
for (int i = 0; i <roadList.size();i++) {%> 
<script type="text/javascript">
		
		</script>
			<tr >


				<td></td>
				<td onclick="showRoad('<%=roadList.get(i).getRoadMPoint()%>','<%=roadList.get(i).getRoadPoint()%>')"><%=roadList.get(i).getRoadName()%></td>

			
				<td><%=roadList.get(i).getRoadpointname()%></td>

				<td>
				

					<button class="btn btn-warning" onclick="toModifyRoad('<%=roadList.get(i).getRoadID()%>','<%=roadList.get(i).getRoadName()%>','<%=roadList.get(i).getRoadPoint()%>','<%=roadList.get(i).getRoadMPoint()%>')">&nbsp;&nbsp;修改&nbsp;&nbsp;</button>
					<button class="btn btn-danger" onclick = "deleteRoadsubmit('<%=roadList.get(i).getRoadID()%>')">&nbsp;&nbsp;删除&nbsp;&nbsp;</button>


				</td>

			</tr>
			<%} %>

		</table>


	</div>



	<div id="AddRoad"  style="display:none;">
		<p id="pl">添加道路</p>
		<br> <br>

		<form id="RoadInfo" action="">

			<div style="display:none;">

				<input type=hidden name="RoadId" type="text" class="form-control"
					id="RoadId">
			</div>

			<div class=" " style="display:none;" >
				<div class=" ">
					<label for="RoadPosition" class=" label label-info">位置(点)&nbsp;：</label> <input
						name="RoadPosition" class="form-control" id="RoadPosition"
						value="">
				</div>
			</div>

			<br>



			<div class=" ">
				<div class=" ">
					<label for="RoadName" class=" label label-info">道路名称：</label> <input
						name="RoadName" type="text" class="form-control" id="RoadName"
						value="">
				</div>
			</div>

			<br>


			<div class=" " style="display:none;">
				<div class=" ">
					<label for="RoadCPoint" class=" label label-info">中心点&nbsp;&nbsp;&nbsp;：</label> <input
						name="RoadCPoint" class="form-control" id="RoadCPoint"
						value="">
				</div>
			</div>
			<br>

			<div class=" " style="display:none;">
				<div class=" ">
					<label for="RoadPoint" class=" label label-info">所属点位：</label> <input
						name="RoadPoint" class="form-control" id="RoadPoint"
						>
				</div>
			</div>
			<br>
			<div>
			<table id="RoadPointList">
			<tr>
			<td>点位</td><td>操作</td>
			</tr>
			</table>
			</div>


			<div class="">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


				<button class="btn btn-warning" onclick="toRoad()">&nbsp;取消&nbsp;</button>
				&nbsp;&nbsp; <a class="btn btn-success" onclick="AddPointToRoadOn()">&nbsp;加点&nbsp;</a>
				&nbsp;&nbsp;
				<a class="btn btn-info" onclick="removeEditor()">&nbsp;重置&nbsp;</a>
				&nbsp;&nbsp;
				<button id="sub" type="button" class="btn btn-danger">&nbsp;添加&nbsp;</button>
			</div>
		</form>
	</div>



</body>

</html>
