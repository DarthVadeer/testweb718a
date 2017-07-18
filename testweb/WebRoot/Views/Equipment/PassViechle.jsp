<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>过车信息查询</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cookie">
    <link rel="stylesheet" href="assets/css/styles1.css">
    <link rel="stylesheet" href="assets/css/Hero-Technology.css">
    <link rel="stylesheet" href="assets/css/Mockup-iPhone-6.css">
    <link rel="stylesheet" href="assets/css/Pretty-Footer.css">
    
    <script src="../../css/bootstrap_3.0.3/js/jquery-1.8.2.min.js"></script>
<script src="../../css/bootstrap_3.0.3/js/bootstrap.min1.js"></script>
<script src="Resources/lib/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
var infoList = new Array();

function infoQuery(){
 $.ajax({
			url : "CarQueryAction",
			type : "post",
			 dataType: "json",
			data:
{
"carID":$("#carID").val(),
"carType":$("#carType").val(),
"startTime":$("#startTime").val(),
"endTime":$("#endTime").val()
},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			try {data = JSON.parse(data);}
			catch(e){}
			
			 infoList.splice(0,infoList.length);
			var pointInfoList = "";
			for(var i =0;i<data.length;i++)
			{
			infoList.push(data[i]);
			pointInfoList+="<tr>";
			pointInfoList+="<td>"+data[i].pastCarNum+"</td>";
			pointInfoList+="<td>"+data[i].pastLicType+"</td>";
			pointInfoList+="<td>"+data[i].pastDcoDate+"</td>";
			pointInfoList+="<td>"+data[i].pastDevCode+"</td>";
			pointInfoList+="<td>"+data[i].pastLaneNum+"</td>";
			pointInfoList+="<td>"+data[i].pastVehSpeed+"</td>";
			pointInfoList+="<td>"+"<img src\""+data[i].pastPicPath+"\"></td>";
			pointInfoList+="<td>"+data[i].pastDesc+"</td>";
			pointInfoList+="<td><a onclick=\"showPosition("+i+")\">详情</a></td>";
		pointInfoList+="</tr>";
			}
			
			$("#pastViechleList").empty();
			$("#pastViechleList").append(pointInfoList);
	
    }
    });
};


function showPosition(i){

 $.ajax({
			url : "CarInfoAction",//==========================
			type : "get",
			 dataType: "text",
			data:
{
"equipId":infoList[i].pastDevCode
},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			var datas=data.split(',');
			if(newmarker!=null)newmarker.setMap(null);
			newmarker = new AMap.Marker({
				position: [datas[0],datas[1]],
				icon:null,
				title:infoList[i].pastCarNum,
				map: map
			});
			map.setZoomAndCenter(12,newmarker.getPosition( ));
          }
		});


};

	var newmarker,oldmarker;
	function addMarker(jd,wd,ti){
	newmarker = new AMap.Marker({
				position: [jd,wd],
				icon:"add.png",
				title:ti,
				map: map
			});

			
			};


</script>

</head>

<body>

<div id="container"  style="width:100%; height:30%">  </div>
	

	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&amp;key=07016be98c6769fa4acd03e91dab6589"></script>
		
	<script type="text/javascript">
        var map = new AMap.Map('container',{
            resizeEnable: true,
            zoom: 12,
            center: [116.369962, 39.872351]
        });
        var markers =  new Array();
        var markersid = new Array();
        
    </script>
    <div>
    <div class="well"><span>过车信息查询</span></div><div class="henglan"><span >点位：</span>
    <input class="input-sm" type="text" value="点位名称" id="pointName">

 
    
  <span ">时间：</span><span>起始时间</span>
    <input type="date" id="startTime"><span >结束时间</span>
    <input type="date" id="endTime">
    <button class="btn btn-default" type="button" id="carQueyButton" onclick="infoQuery()">查询</button></div>
    </div>
    <br><br>
    <div class="container">
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>车牌号</th>
                        <th>号牌类型</th>
                        <th>采集时间</th>
                        <th>卡口编号</th>
                        <th>车道编号</th>
                        <th>车辆速度</th>
                        <th>证据图片</th>
                        <th>行驶方向</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id ="pastViechleList">
                   
                </tbody>
            </table>
        </div>
        <nav>
            <ul class="pagination">
                <li><a aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                <li><a>1</a></li>
                <li><a>2</a></li>
                <li><a>3</a></li>
                <li><a>4</a></li>
                <li><a>5</a></li>
                <li><a aria-label="Next"><span aria-hidden="true">»</span></a></li>
            </ul>
        </nav>
    </div>
  <div role="dialog" tabindex="-1" class="modal fade">
        <div class="modal-dialog" id="myModal" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">车辆详细信息</h4></div>
                <div class="modal-body">
                    <p>1111.</p>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" data-dismiss="modal">Close</button>
                    
                </div>
            </div>
        </div>
    </div> 
 <script src="assets/js/jquery.min.js"></script>
 <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>