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

<script src="../../css/bootstrap_3.0.3/js/jquery-1.8.2.min.js"></script>
<script src="../../css/bootstrap_3.0.3/js/bootstrap.min1.js"></script>
<script src="。。/../Resources/lib/jquery-1.11.1.min.js"></script>
<title>My page</title>
<script type="text/javascript">
	function resetValue() {
		if (condition == "点位ID") {

			condition = "点位名称";
		} else {
			condition = "点位ID";
		}
	}
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
window.location.href = "PointInfoListAction";
}
});
};

function toAddPoint(){
$("#PointList").fadeOut();
$("#addPoint").fadeIn();
$("#PointLongitude").val(null);
$("#PointLatitude").val(null);
$("#PointName").val(null);
$("#p1").text("增加点位");
$("#sub").text("添加");
$("#pointInfo").attr("action","PointAddAction");
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
$("#pointInfo").attr("action","PointEditPageAction");
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

				<button onclick="resetValue()" class="btn btn-default"></button>
			</div>
		</div>

	</form>

 <a onclick="toAddPoint()" ><i class="icon-edit"></i> add</a>	

	<table id = "PointTable" border="1">

		<tr>
			<th colspan='7'><label class="label label-danger" id="点位信息">&nbsp;&nbsp;点位信息列表</label></th>
		</tr>

		<tr>
			<td><label class="label label-default">点位名称</label></td>
			<td><label class="label label-default" >点位经纬度</label></td>
			<td><label class="label label-default" >设备数量</label></td>
			<td><label class="label label-default" >操作</label></td>
		</tr>
		
<%  List<Point> pointList =(List<Point>)request.getAttribute("pointlist");  
for (int i = 0;i <pointList.size();i++) {%> 
		<tr onclick = "printem(<%= i%>)">
		<script type="text/javascript">
		addMarker('<%=pointList.get(i).getLongitude()%>','<%=pointList.get(i).getLatitude()%>','<%=pointList.get(i).getPointname()%>','<%=pointList.get(i).getPointid()%>');
		</script>
			<td><%=pointList.get(i).getPointname()%></td>
			<td><%=pointList.get(i).getLongitude()%>,<%=pointList.get(i).getLatitude()%></td>
			<td><%=pointList.get(i).getEquipment()%></td>
			<td><a onclick="toModify(<%=i%>)"><i class="icon-edit"></i> 修改</a>/<a onclick="pointDelete(<%=i%>)"><i class="icon-edit"></i> 删除</a></td>
		
		</tr>
		
<%} %>

	</table>
	</div>
	<div id= "addPoint" >
	<p id = "p1">添加点位</p>
	<br>

	<br>
	<br>
	<form id=pointInfo action="PointAddAction">
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
					name="PointLongitude" class="form-control" id="PointLongitude"
					>
			</div>
		</div>
		<br>

		<div class=" ">
			<div class=" ">
				<label for="PointLatitude" class="label label-default ">点位纬度：</label> <input
					name="PointLatitude" class="form-control" id="PointLatitude"
					>
			</div>
		</div>
		<br>
		<div class="">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button onclick="toPointList()" class="btn btn-warning">&nbsp;取消&nbsp;</button>
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