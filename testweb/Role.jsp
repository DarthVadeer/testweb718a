<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@page import="model.Point"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Role.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="Resources/lib/jquery-1.6.min.js"></script>
</head>


<br>

<%

String addORmodify = "";

 %>


<script type="text/javascript">

var roles = new Array();


$(document).ready(function(){
	$.ajax({
			url : "RoleQueryAllAction",
			type : "get",
			 dataType: "json",
			data:
{
},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			 
			try {data = JSON.parse(data);}
			catch(e)
			{
			}
			
			var depOptions = "";
			
			for(var i =0;i<data.length;i++)
			{
			roles.push(data[i]);
			depOptions+="<tr>";
			depOptions+="<td></td>";
			depOptions+="<td>"+data[i].roleid+"</td>";
			depOptions+="<td>"+data[i].rolename+"</td>";
			depOptions+="<td>"+data[i].rolemenuids+"</td>";
			depOptions+="<td>"+data[i].rolerenarks+"</td>";
			depOptions+="<td><a onclick=\"+toModifyRole("+i+")\">修改</a><a onclick=\"+DeleteRole("+i+")\">删除</a></td>";
			depOptions+="</tr>";
			}
			
			$("#roleTable tr:not(:first)").html("");
			
			$("#roleTable").append(depOptions);
          }
		});

});

function modifyRole(){
	var Rolemenuids = "",Rolemenunames="";
		var check = document.getElementsByName("CheckValues");
		for (var i = 0; i < check.length; i++) {
				if (check[i].checked == true) {
					Rolemenuids += check[i].value + ",";
					//alert(check[i].value);
					switch (parseInt(check[i].value)) {
					case 001:
						//ThroughCar.attr("checked", "checked");
						Rolemenunames = "ThroughCar" + ",";
						break;
					case 002:
						Rolemenunames += "PathReplay" + ",";
						break;
					case 003:
						Rolemenunames += "CardManage" + ",";
						break;
					case 11:
						Rolemenunames += "MonitorApply" + ",";
						break;
					case 12:
						Rolemenunames += "MonitorExamine" + ",";
						break;
					case 13:
						Rolemenunames += "MonitorManage" + ",";
						break;
					case 14:
						Rolemenunames += "AlarmProcess" + ",";
						break;
					case 15:
						Rolemenunames += "MonitorRevoke" + ",";
						break;
					case 16:
						Rolemenunames += "IntegratQuery" + ",";
						break;
					case 17:
						Rolemenunames += "AlarmInquiry" + ",";
						break;
					case 21:
						Rolemenunames += "Road" + ",";
						break;
					case 22:
						Rolemenunames += "POINT" + ",";
						break;
					case 31:
						Rolemenunames += "DEPARTMENT" + ",";
						break;
					case 32:
						Rolemenunames += "ROLE" + ",";
						break;
					case 33:
						Rolemenunames += "PERSONNEL";
						break;
					default:
						break;

					}
				}

				}
		Rolemenuids=Rolemenuids.substring(0,Rolemenuids.length-1);
	$.ajax({
			url : "RoleUpdateAction",
			type : "get",
			data:{
			"RoleId":$("#RoleId").val(),
				"RoleName":$("#RoleName").val(),
			"Rolemenunames":Rolemenunames,
			"Rolemenuids":Rolemenuids,
			"RoleDesc":$("#RoleDesc").val()
			
			},
			

			success : function(data) {

			if(data==1){
				alert("修改成功！");
				window.location.href="RoleListAction";
			}
				else
				alert("修改失败！");	
}
});
};


	function roleAdd() {
	
	$("#CheckValues").attr("checked", false);
	$("#PathReplay").attr("checked", false);
	$("#CardManage").attr("checked", false);
	$("#MonitorApply").attr("checked", false);
	$("#MonitorExamine").attr("checked", false);
	$("#MonitorManage").attr("checked", false);
	$("#AlarmProcess").attr("checked", false);
	$("#MonitorRevoke").attr("checked", false);
	$("#IntegratQuery").attr("checked", false);
	$("#AlarmInquiry").attr("checked", false);
	$("#Road").attr("checked", false);
	$("#POINT").attr("checked", false);
	$("#DEPARTMENT").attr("checked", false);
	$("#ROLE").attr("checked", false);
	$("#PERSONNEL").attr("checked", false);
	
	
	
	var Rolemenuids = "",Rolemenunames="";
		var check = document.getElementsByName("CheckValues");
		for (var i = 0; i < check.length; i++) {
			if (check[i].checked == true) {
				Rolemenuids+=check[i].value+",";

				switch (parseInt(check[i].value)) {
				case 001:
					//ThroughCar.attr("checked", "checked");
					Rolemenunames = "ThroughCar" + ",";
					break;
				case 002:
					Rolemenunames += "PathReplay" + ",";
					break;
				case 003:
					Rolemenunames += "CardManage" + ",";
					break;
				case 11:
					Rolemenunames += "MonitorApply" + ",";
					break;
				case 12:
					Rolemenunames += "MonitorExamine" + ",";
					break;
				case 13:
					Rolemenunames += "MonitorManage" + ",";
					break;
				case 14:
					Rolemenunames += "AlarmProcess" + ",";
					break;
				case 15:
					Rolemenunames += "MonitorRevoke" + ",";
					break;
				case 16:
					Rolemenunames += "IntegratQuery" + ",";
					break;
				case 17:
					Rolemenunames += "AlarmInquiry" + ",";
					break;
				case 21:
					Rolemenunames += "Road" + ",";
					break;
				case 22:
					Rolemenunames += "POINT" + ",";
					break;
				case 31:
					Rolemenunames += "DEPARTMENT" + ",";
					break;
				case 32:
					Rolemenunames += "ROLE" + ",";
					break;
				case 33:
					Rolemenunames += "PERSONNEL";
					break;
				default:
					break;
				}
			}
		}
		Rolemenuids=Rolemenuids.substring(0,Rolemenuids.length-1);
			
		$.ajax({
			url : "RoleAddAction",
			type : "get",
			data:{
			"RoleName":$("#RoleName").val(),
			"Rolemenunames":Rolemenunames,
			"Rolemenuids":Rolemenuids,
			"RoleDesc":$("#RoleDesc").val()
			},

			success : function(data) {
			
				if(data==1)
				alert("添加成功！");
				else
				alert("添加失败！");
				window.location.href="RoleListAction";
}
});
		
	}
	;





	function DeleteRole(i) {
		alert("删除角色...");
		$.ajax({
			url : "RoleDeleteAction",
			type : "get",
			data : {
				"Roleid" : roles[i].roleid
			},

			success : function(data) {
				if (data == 1)
					alert("添加成功！");
				else
					alert("添加失败！");
				window.location.href = "RoleListAction";
			}
		});
	}
	;




	function toAddRole(){
$("#p1").text("增加角色");
$("#sub").text("添加");

$("#sub").attr("onclick","roleAdd()");
};





function toModifyRole(i){


$("#CheckValues").attr("checked", false);
$("#PathReplay").attr("checked", false);
$("#CardManage").attr("checked", false);
$("#MonitorApply").attr("checked", false);
$("#MonitorExamine").attr("checked", false);
$("#MonitorManage").attr("checked", false);
$("#AlarmProcess").attr("checked", false);
$("#MonitorRevoke").attr("checked", false);
$("#IntegratQuery").attr("checked", false);
$("#AlarmInquiry").attr("checked", false);
$("#Road").attr("checked", false);
$("#POINT").attr("checked", false);
$("#DEPARTMENT").attr("checked", false);
$("#ROLE").attr("checked", false);
$("#PERSONNEL").attr("checked", false);


//$("#AddRole").fadeIn();
$("#RoleId").val(roles[i].roleid);
$("#RoleName").val(roles[i].rolename);

var arr = roles[i].rolemenuids.split(",");

var ThroughCar = document.getElementById('ThroughCar');

$("#sub").text("oiu");
for (var j = 0; j < arr.length; j++) {
	
switch(parseInt(arr[j])){

case 001:
//ThroughCar.attr("checked", "checked");
$("#ThroughCar").attr("checked", "checked");
break;

case 002:
$("#PathReplay").attr("checked", "checked");

break;

case 003:
$("#CardManage").attr("checked", "checked");

break;


case 11:
$("#MonitorApply").attr("checked", "checked");

break;

case 12:
$("#MonitorExamine").attr("checked", "checked");

break;

case 13:
$("#MonitorManage").attr("checked", "checked");

break;

case 14:
$("#AlarmProcess").attr("checked", "checked");

break;

case 15:
$("#MonitorRevoke").attr("checked", "checked");

break;

case 16:
$("#IntegratQuery").attr("checked", "checked");

break;

case 17:
$("#AlarmInquiry").attr("checked", "checked");

break;

case 20:
//ThroughCar.attr("checked", "checked");

break;
case 21:
$("#Road").attr("checked", "checked");

break;

case 22:
$("#POINT").attr("checked", "checked");

break;
case 30:
//ThroughCar.attr("checked", "checked");

break;
case 31:
$("#DEPARTMENT").attr("checked", "checked");

break;

case 32:
$("#ROLE").attr("checked", "checked");

break;

case 33:
$("#PERSONNEL").attr("checked", "checked");

break;

default:

break;



}

}
$("#RoleDesc").val(roles[i].rolerenarks);
$("#p1").text("修改角色");
$("#sub").text("ddoii");
$("#sub").attr("onclick","modifyRole();");
}

	
</script>


<body>
	<br>

	<h3>角色管理</h3>
	<br>
	<br>

	<div id="AddRole" style="float:right">

	<p id = "p1">添加角色</p>

		<form class="vote" action="" method="post">
			<div>
				<table frame="box" width="480px" height="580px">
					<tr>
						<td align=center style="  display:none;"><br> <label
							for="RoleId" class=" ">角色ID：</label> <input name="RoleId"
							type="text" class="form-control" id="RoleId" value=""></td>
					</tr>
					
					<tr>
						<td align=center><br> <label for="RoleName" class=" ">角色名称：</label>
							<input name="RoleName" type="text" class="form-control"
							id="RoleName" value=""></td>
					</tr>

					<tr>


						<td style="height: 20px;">
						<a> 过车信息查询: </a><input type="checkbox" name="CheckValues" value="001" id = "ThroughCar" ></input>
						<a>车辆轨迹回放: </a><input type="checkbox" name="CheckValues" value="002" id = "PathReplay"></input>
						<a> 卡口设备管理: </a><input type="checkbox" name="CheckValues" value="003" id = "CardManage"></input>
						<a> 布控申请: </a><input type="checkbox" name="CheckValues" value="011" id = "MonitorApply"></input>
					    <a> 布控审核: </a><input type="checkbox" name="CheckValues" value="012" id = "MonitorExamine"></input>
						<a> 布控管理: </a><input type="checkbox" name="CheckValues" value="013" id = "MonitorManage"></input>
						<a>报警处理: </a><input type="checkbox" name="CheckValues" value="014" id = "AlarmProcess"></input>
						<a> 布控撤销: </a><input type="checkbox" name="CheckValues" value="015" id = "MonitorRevoke"></input> 
						<a> 综合查询: </a><input type="checkbox"name="CheckValues" value="016" id = "IntegratQuery"></input> 
						<a> 报警查询: </a><input type="checkbox" name="CheckValues" value="017" id = "AlarmInquiry"></input> 
						<a>道路管理: </a><input type="checkbox" name="CheckValues" value="021" id = "Road"></input>
						<a> 点位信息: </a><input type="checkbox" name="CheckValues" value="022" id = "POINT"></input>
						<a> 部门管理: </a><input type="checkbox" name="CheckValues" value="031" id = "DEPARTMENT"></input>
						<a> 角色管理: </a><input type="checkbox" name="CheckValues" value="032" id = "ROLE"></input> 
						<a> 人员管理: </a><input type="checkbox" name="CheckValues" value="033" id = "PERSONNEL"></input>
							<!-- <a> 个人信息: </a><input type = "checkbox"  name ="PersonalInfo"></input> -->
						</td>
					</tr>

					<tr>
						<td align=left>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class=" ">
								角色描述：</label>
					</tr>

					<tr>
						<td align=center height="98"><textarea rows="5" cols="27"
								id="RoleDesc"></textarea></td>
					</tr>

					<tr>
						<td align=center>
							<button onclick="toAddRole()">&nbsp;返回&nbsp;</button>
							<Button id = "sub" type="button" class="" onclick= "roleAdd()">&nbsp;提交&nbsp;</Button>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>

	
	<div id="RoleList"
		style="overflow-y:scroll; width:1000px; height:300px;">


		<table border="1" cellspacing="0" id="roleTable">

			<tr>
				<td width="10%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td width="10%"><label class="" id="RoleId">角色ID</label></td>
				<td width="10%"><label class="" id="RoleName">角色名</label></td>
				<td width="10%"><label class="" id="PersonnelRole">角色权限</label></td>
				<td width="30%"><label class="" id="PersonnelDep">角色描述</label></td>
			
				<td align=center><label class="" id="">操作</label></td>
				</tr>
		<tr>
		<td></td>
		<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><a>查看</a>/<a href="#"><i class="icon-edit"></i> 删除</a></td>
		
	
		</tr>
		</table>


	</div>


	<script type="text/javascript">
		//$("#AddRole").fadeOut();
	</script>


</body>
</html>
