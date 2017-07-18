<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DevicesManage</title>
    <link rel="stylesheet" href="../../Resources/css/bootstrap_3.0.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../Resources/css/styles2.css">
    <script src="../../Resources/js/jquery.min.js"></script>
    <script src="../../Resources/css/bootstrap_3.0.3/js/bootstrap.min.js"></script>
    <script>
    $(document).ready(function(){
	for(var i = 0;i < markers.length; i++) {
markers[i].on('click', function() {
   changeEquipPointOn(i);
    });
}
});

function changeEquipPointOn(i){
newmarker.setIcon("add.png");
newmarker= markers[i];
$("#equipPointName"). val(newmarker.getTitle());
$("#equipPoint"). val(markersid[i]);
newmarker.setIcon(null);
};


    
    
    function addMarker(jd,wd,ti,id){

	 newmarker = new AMap.Marker({
				position: [jd,wd],
				icon:"add.png",
				title:ti,
				map: null
			});
			markers.push(newmarker);
			
			markersid.push(id);
			
			};
    
    
    
    function searchEquip(){
    $.ajax({
			url : "EquipemntQuerryAction",//==========================
			type : "get",
			 dataType: "json",
			data:
{
"conditionRoad":$("#conditionRoad").val(),
"conditionPoint":$("#conditionPoint").val(),
"conditionName":$("#conditionName").val()

},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			 
			try {data = JSON.parse(data);}
			catch(e)
			{
			}
			//清除原本信息
			var equipList = "";
			epuipments.splice(0,epuipments.length);
			
			for(var i =0;i<data.length;i++)
			{
			epuipments.push(data[i]);
			equipList+="<tr>";
			
			equipList+="<td>"+data[i].epuipName+"</td>";
			equipList+="<td>"+data[i].epuip+"</td>";
			equipList+="<td><a onclick=\"+toModifyEqui("+i+")\">修改</a><a onclick=\"+DeleteEqui("+i+")\">删除</a></td>";
			equipList+="</tr>";
			}
			
			$("#List").empty();
			$("#List").append(equipList);
          }
		});
    };
    
    function searchPointbyId(id){

for(var i = 0;i < markersid.length; i++) {

if(id==markersid[i]){

return i;}
}
};
    
    
    function toModifyEquip(i){
    setInitInfo(i);
    $("#equipDetail").fadeIn();
     $("#equipList").fadeOut();
     $("#submitButton").attr("onclick()","submitModify()");
    
    };
    
    function submitModify(){
      $.ajax({
			url : "EquipemntAction",//==========================
			type : "get",
			 dataType: "json",
			data:
{
"equipId":$("#equipId").val(),
"equipName":$("#equipName").val(),
"equipPoint":$("#equipPoint").val(),
"equipState":$("#equipState").val()

},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			 
			try {data = JSON.parse(data);}
			catch(e)
			{
			}
			//清除原本信息
			var equipList = "";
			epuipments.splice(0,epuipments.length);
			
			for(var i =0;i<data.length;i++)
			{
			epuipments.push(data[i]);
			equipList+="<tr>";
			
			equipList+="<td>"+data[i].epuipName+"</td>";
			equipList+="<td>"+data[i].epuip+"</td>";
			equipList+="<td><a onclick=\"+toModifyEqui("+i+")\">修改</a><a onclick=\"+DeleteEqui("+i+")\">删除</a></td>";
			equipList+="</tr>";
			}
			
			$("#List").empty();
			$("#List").append(equipList);
          }
		});
    
    }
    
    function setInitInfo(i){
    var tempEquipId = equipments[i].equipID;
    newmarker = markers[searchPointbyId(tempEquipId)];
    newmarker.setIcon(null);
    map.setZoomAndCenter(12, newmarker.getPosition());
    $("#equipId").val(equipments[i].equipID);
     $("#equipName").val(equipments[i].equipName);
      $("#EquipState").val(equipments[i].equipState);
       $("#EquipBPoint").val(equipments[i].equipBPoint);   
    };
    
    
    </script>
    
</head>

<div id="container" style="width:100%; height:50%"></div>


	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.3&key=07016be98c6769fa4acd03e91dab6589"></script>

	<script type="text/javascript">
		var map = new AMap.Map('container', {
			resizeEnable : true,
			zoom : 10,
			center : [ 116.369962, 39.872351 ]
		});
		var newmarker;
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
  <script>
  
  var equipments = new Array();
  </script>
<body>

<div id = "equipList">
    <div class="well"><span>卡口设备管理</span>
        <div class="pos"><a href="#"><i class="glyphicon glyphicon-plus"></i><span >添加</span></a></div>
    </div>
    <div class="textPostion">
        <input type="text"  id = "conditionRoad"value="">&nbsp;
            <input type="text"  id = "conditionPoint"value="">&nbsp;
                <input type="text"  id = "conditionName"value="">&nbsp;
        <button class="btn btn-default" type="button" onclick="searchEquip()">查询</button>
    </div>
    <div>
        <div class="container">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>卡口编号</th>
                            <th>工作状态</th>
                            <th>定点位置</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody id="List">
                       
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>  
    
    
  <div id="equipDetail">
    <form id=quipInfo >
	<input type  = hidden
					name="equipId" type="text" class="form-control" id="equipId">
		<div class=" ">
			<div class=" ">
				<label for="equipName" class="lable label-danger ">卡口名称：</label> <input
					name="equipName" type="text" class="form-control" id="equipName"
					>
			</div>
		</div>

		<br>
		<div class=" ">
			<div class=" ">
				<label for="equipPoint" class="label label-warning ">所在点位：</label> <input
					name="equipPoint" class="form-control" id="equipPoint"
					>
			</div>
		</div>
		<br>
				<div class=" ">
			<div class=" ">
				<label for="equipPointName" class="label label-warning ">所在点位：</label> <input
					name="equipPointName" class="form-control" id="equipPointName"
					>
			</div>
		</div>
		<br>

		<div class=" ">
			<div class=" ">
				<label for="equipState" class="label label-default ">工作状态：</label> <select
					name="equipState" class="form-control" id="equipState">
					<option value="1">正常</option>
					<option value="2">异常</option>
					<option value="3">废弃</option>
					</select>
			</div>
		</div>
		<br>
		<div class="">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id = "cancelButton"onclick="cancelModify()" class="btn btn-warning">&nbsp;取消&nbsp;</button>
			&nbsp;&nbsp;
			<button id = "submitButton" type="button" class="btn btn-info">&nbsp;修改&nbsp;</button>
		</div>

	</form>
	</div>
    
    
    
</body>

</html>