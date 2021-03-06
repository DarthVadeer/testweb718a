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
    <script src="../../css/bootstrap_3.0.3/js/jquery-1.8.2.min.js"></script>
    <link rel="stylesheet" href="../../Resources/css/bootstrap_3.0.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../Resources/css/styles2.css">
    <script src="../../Resources/js/jquery.min.js"></script>
    <script src="../../Resources/css/bootstrap_3.0.3/js/bootstrap.min.js"></script>
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
    </style>
    
    <script>
    
    function submitAddEquipment(){

   $("#submitButton").attr({"disabled":"disabled"});
   
      $.ajax({
			url : "EquipAddAction",//==========================
			type : "get",
			 dataType: "json",
			data:
{
"equipName":$("#equipName").val(),
"equipPoint":$("#equipPoint").val(),
"equipState":$("#equipState").val()

},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			 if(data!=2){
			 alert("修改成功");
			  window.location.reload();
          }
          else{
          alert("修改失败");
          $("#submitButton").removeAttr("disabled");
          }
          }
		});
    
    }
      
  $(document).ready(function(){
   $("#equipDetail").fadeOut();
  
        $('#keywords').bind('keypress',function(event){  
  
            if(event.keyCode == "13")      
  
            {  
  
               searchPoint();
  
            }  
  
        });
        });
    
    
    function searchPoint(){
    var condition = $("#keywords").val();
    
     $.ajax({
			url : "PointInfoListAction",//==========================
			type : "get",
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
			for(var i =0;i<data.length;i++)
			{
			addMarker(data[i].longitude,data[i].latitude,data[i].pointname,data[i].pointid);
			}
			addEventToMarkers();
    }
    });
    }
    
    
    
    
    function addEventToMarkers(){
	for(var i = 0;i < markers.length; i++) {
	
	addEventToMarker(i);

}
};
function addEventToMarker(i){
 markers[i].on('click', function() {
        
       changeEquipPointOn(i);
});
};


function record(i){
return i;}

function changeEquipPointOn(i){

if(newmarker!=null)
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
				map: map
			});
			markers.push(newmarker);
			markersid.push(id);
			return 1;
			};
    
    
    
    function searchEquip(){
    $.ajax({
			url : "EquipQueryAction",//==========================
			type : "get",
			 dataType: "json",
			data:
{
"conditionRoad":$("#conditionRoad").val(),
"conditionPoint":$("#conditionPoint").val(),
"conditionName":$("#conditionName").val(),
"conditionState":$("#conditionState").val()

},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			 
			try {data = JSON.parse(data);}
			catch(e)
			{
			}
			//清除原本信息
			var equipList = "";
			equipments.splice(0,equipments.length);
			
			for(var i =0;i<data.length;i++)
			{
			equipments.push(data[i]);
			equipList+="<tr id=\""+data[i].epuipId+"\">";
			
			equipList+="<td>"+data[i].equipName+"</td>";
			equipList+="<td>"+showState(data[i].equipState)+"</td>";
			equipList+="<td>"+data[i].equipBPoint+"</td>";
			equipList+="<td><a onclick=\"+toModifyEquip("+i+")\">修改</a></td>";
			equipList+="</tr>";
			}
			
			$("#List").empty();
			$("#List").append(equipList);
          }
		});
    };
    
  function showState(state){
  switch(state){
  case 0:return "正常";break;
    case 1:return "异常";break;
      case 2:return "废弃";break;
        default:return "未知";break;
  }
  }
    
    function searchPointbyId(id){

for(var i = 0;i < markersid.length; i++) {

if(id==markersid[i]){

return i;}
}
};
    
    function toAddEquip(){
      $("#equipDetail").fadeIn();
     $("#equipList").fadeOut();
     $("#equipId").val("");
     $("#equipName").val("");
      $("#equipState").val("");
       $("#equipPoint").val("");   
       $("#submitButton").attr("onclick","submitAddEquipment()");  
    };
    
    function toModifyEquip(i){
    setInitInfo(i);
    $("#equipDetail").fadeIn();
     $("#equipList").fadeOut();
     $("#submitButton").attr("onclick","submitModify()");  
    };
    
    function submitModify(){
      $.ajax({
			url : "EquipUpdateAction",//==========================
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
			 if(data!=2){
			 alert("修改成功");
			 $("#"+$("#equipId").val()).find("td").eq(0).val($("#equipName").val());
			 $("#"+$("#equipId").val()).find("td").eq(1).val($("#equipState").text());
			  $("#"+$("#equipId").val()).find("td").eq(2).val($("#equipPointName").val());
			  var index = searchPointbyId($("#equipPoint").val());
			  equipments[index].equipName=$("#equipName").val();
			  equipments[index].equipBPoint=$("#equipPoint").val();
			  equipments[index].equipState=$("#equipState").val();
			  
          }
          else{
          alert("修改失败");
          }
          }
		});
    
    }
    
    function setInitInfo(i){
   
    var tempEquipId = equipments[i].equipID;
        $.ajax({
			url : "PointInfoByIdAction",
			type : "get",
			 dataType: "json",
			data:
{
"PointId":equipments[i].equipBPoint

},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			try {data = JSON.parse(data);}
			catch(e){}
			for(var i =0;i<data.length;i++){
			if(addMarker(data[i].longitude,data[i].latitude,data[i].pointname,data[i].pointid)==1){
newmarker.setIcon(null);
map.setZoomAndCenter(12, newmarker.getPosition());
}
          }}
		});
    
    
    $("#equipId").val(equipments[i].equipID);
     $("#equipName").val(equipments[i].equipName);
      $("#equipState").val(equipments[i].equipState);
       $("#equipPoint").val(equipments[i].equipBPoint);   
       //$("#equipPointName").val(equipments[i].equipPointName);
    };
    
    function resetInfo(){
    map.remove(markers);
			 markers.splice(0,markers.length);
			 markersid.splice(0,markersid.length);
       $("#equipId").val("");
     $("#equipName").val("");
      $("#equipState").val("");
       $("#equipPoint").val("");   
    };
    
    function cancelModify(){
    $("#equipDetail").fadeOut();
     $("#equipList").fadeIn();
     resetInfo();
    };
    
    </script>
    
</head>

<div id="container" style="width:100%; height:50%"></div>
   <input type="text" id="keywords" value="" />


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
        var equipments = new Array();
		
	</script>

<body>

<div id = "equipList">
    <div class="well"><span>卡口设备管理</span>
        <div class="pos"><a onclick="toAddEquip()"><i class="glyphicon glyphicon-plus"></i><span >添加</span></a></div>
    </div>
    <div class="textPostion">
        <label for="conditionRoad" class="lable label-danger ">道路：</label>
        <input type="text"  id = "conditionRoad"value="">&nbsp;
        <label for="conditionPoint" class="lable label-danger ">点位：</label>
            <input type="text"  id = "conditionPoint"value="">&nbsp;
            <label for="conditionName" class="lable label-danger ">卡口：</label>
                <input type="text"  id = "conditionName"value="">&nbsp;
                <label for="conditionState" class="lable label-danger ">状态：</label>
                <select
					name="conditionState" class="form-control" id="conditionState">
					<option></option>
					<option value="0">正常</option>
					<option value="1">异常</option>
					<option value="2">废弃</option>
					</select>
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
			<button id = "cancelButton" type="button" onclick="cancelModify()" class="btn btn-warning">&nbsp;取消&nbsp;</button>
			&nbsp;&nbsp;
			<button id = "submitButton" type="button" class="btn btn-info">&nbsp;修改&nbsp;</button>
		</div>

	</form>
	</div>
    
    
    
</body>

</html>