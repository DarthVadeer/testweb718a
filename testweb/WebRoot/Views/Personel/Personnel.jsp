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
<base href="<%=basePath%>">

<title>My JSP 'Personnel.jsp' starting page</title>
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
<script type="text/javascript" >
var peoples = new Array();
function getDep(){

var depId='000',depName='000';
	for(var i=0 ;i<5;i++){
	
	if($("#select"+i).find("option:selected").val()!=null&&$("#select"+i).find("option:selected").val()!=""){
			depId = $("#select"+i).find("option:selected").val();
			depName = $("#select"+i).find("option:selected").html();
			}
			}
			var dep = new Array();
			dep.push(depId);
			dep.push(depName);
			return dep;
}

$(document).ready(function(){
	$.ajax({
			url : "DepartSubMenuAction",
			type : "get",
			 dataType: "json",
			data:
{
"DepId":theDepId
},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			
			try {data = JSON.parse(data);}
			catch(e)
			{
			
			}
			
			var depOptions = "<option value=\"\"></option>";
			for(var i =0;i<data.length;i++)
			{
			depOptions+="<option value=\""+data[i].departID+"\">"+data[i].departName+"</option>";
			
			}
			
			$("#select"+theDepLevel).find("option").remove();
			
			$("#select"+theDepLevel).append(depOptions);
                 
                 $("#selectIn"+theDepLevel).find("option").remove();
			
			$("#selectIn"+theDepLevel).append(depOptions);
	
			}
		});

});

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
			
			var depOptions = "<option></option>";
			for(var i =0;i<data.length;i++)
			{
			depOptions+="<option value=\""+data[i].roleid+"\">"+data[i].rolename+"</option>";
			
		
			}
			
			
			
			$("#roleId").append(depOptions);
			$("#PersonnelRole").append(depOptions);
           
             
	
			}
		});
});

$(document).ready(function(){
  $("#selectIn0, #selectIn1,#selectIn2,#selectIn3  ").change(function(){
  var ParentId,ParentIdName;
  
    ParentId = $(this).find("option:selected").val();  
     ParentIdName = $(this).find("option:selected").text();  
    
    	$("#PersonnelDepId").val(ParentId);
		$("#PersonnelDep").val(ParentIdName);
    
  });
  });

$(document).ready(function(){
  $("#select0, #select1, #select2  ,#selectIn0, #selectIn1,#selectIn2 ").change(function(){
  
  var ParentId;
  
    ParentId = $(this).find("option:selected").val();  
    var thestr = $(this).attr("id");
    str = thestr.substring(thestr.length-1,thestr.length);
    str = parseInt(str)+1;
    prevStr = thestr.substring(0,thestr.length-1);
  	$.ajax({
			url : "DepartSubMenuAction",
			type : "get",
			 dataType: "json",
			data:
{
"DepId":ParentId
},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			
			try {data = JSON.parse(data);}
			catch(e)
			{
			
			}
		
			var depOptions = "<option value=\"\"></option>";
			for(var i =0;i<data.length;i++)
			{
			depOptions+="<option value=\""+data[i].departID+"\">"+data[i].departName+"</option>";
			
			}
			var index = str;
			for(str;str<5;str++){
			$("#"+prevStr+str).find("option").remove();

			}
			
			$("#"+prevStr+index).append(depOptions);
                 
	
			}
		});
  
 }); 
});
</script>
</head>




<br>

<script>


function deleteFromPersonnel(id){
alert("删除人");
   $.ajax({
url:"PeopleDeleteAction",
data:{
"PeopleId":id,
},
type: "get",
success : function(data) {
if(data==1){
alert("删除人员成功！");
$("#"+id).remove();
}
else
alert("删除人员不成功！");
}
});
};



	function toAddPersonnel() {
		

		$("#AddPersonnel").fadeIn();
		var dep = new Array();
		dep = getDep();
		$("#PersonnelId").val("");
					$("#PersonnelName").val("");
					$("#PersonnelPassword").val("");
					$("#PersonnelDepId").val("");
					$("#PersonnelPhone").val("");
					$("#PersonnelRole").val("");
		$("#PersonnelDepId").val(dep[0]);
		$("#PersonnelDep").val(dep[1]);
		$("#p1").text("添加人员");
		$("#sub").text("添加");
		

	}
	;

	function toHideAdd() {
		$("#AddPersonnel").hide();
	};
	
function ModifyPersonnel(){
	$.ajax({
			url : "PeopleUpdateAction",
			type : "get",
			datatype:"json",
			data:encodeURI($('#newPeople').serialize()),
			success : function(data) {
			
			 if(data==1){
			 alert("修改成功！");
				updateModify();
toHideAdd();
}
else
alert("修改失败！");
	
	
		}
     
		});
	
	};
	
	
	function updateModify(){
	
	
	
		var listSen="";
			listSen+="<td></td><td>"+$("#PersonnelId").val()
			+"</td><td>"+	$("#PersonnelName").val()
			+"</td><td>"+$("#PersonnelRole").val()
			+"</td><td>"+$("#PersonnelDep").val()
			+"</td><td>"+$("#PersonnelPhone").val()
			+"</td><td><a onclick=\"toModifyPersonnel("+
			$("#PersonnelId").val()+")\">修改</a>"
			+"<a onclick=\"deleteFromPersonnel("+$("#PersonnelId").val()+")\">删除</a></td>";
		$("#"+$("#PersonnelId").val()).empty();
	$("#"+$("#PersonnelId").val()).append(listSen);
	
	};

	function toModifyPersonnel(i) {
	
		$.ajax({
			url : "PeopleQueryAction",
			type : "get",
			datatype:"json",
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			data:{
			"poepleName":"",
			"poepleId":peoples[i].peopid,
			"roleId":"",
			"poepleDep":peoples[i].peoSubdetach
			},
			success : function(data) {
			
			    
				try {data = JSON.parse(data);}
			catch(e)
			{
			}
			
		
			
			
			for(var i =0;i<data.length;i++){
		
				$("#PersonnelId").val(data[i].peopid);
					$("#PersonnelName").val(data[i].name);
				
					$("#PersonnelPassword").val(data[i].password);
					$("#PersonnelDepId").val(data[i].peoSubdetach);
					$("#PersonnelPhone").val(data[i].phone);
					$("#PersonnelRole").val(data[i].role);
				
					
			}
	
		}
     
		});
	
		$("#AddPersonnel").fadeIn();

		$("#p1").text("修改人员");
		$("#sub").text("修改");
		$("#sub").attr("onclick", "ModifyPersonnel()");

	}
	
	function submitAdd(){
	
	$.ajax({
			url : "PeopleAddAction",
			type : "get",
			datatype:"json",
			data:encodeURI($('#newPeople').serialize()),
			success : function(data) {
			
			    if(data!=2){
			    alert("添加成功！");
				try {data = JSON.parse(data);}
			catch(e)
			{
			
			}
			var listSen="";
				for(var i =0;i<data.length;i++){
			var tempid = data[i].peopid;
			listSen+="<tr id=\""+data[i].peopid+"\"><td></td><td>"+data[i].peopid
			+"</td><td>"+data[i].name
			+"</td><td>"+data[i].role
			+"</td><td>"+data[i].peoSubdetach
			+"</td><td>"+data[i].phone
			+"</td><td><a onclick=\"toModifyPersonnel("+
			tempid+")\">修改</a>"
			+"<a onclick=\"deleteFromPersonnel("+tempid+")\">删除</a></td></tr>";
			}
	$("#peopleList").append(listSen);
			

toHideAdd();
}
else
alert("添加失败！");
	
	
		}
     
		});
	};
	
	function findDep(){
	var i = 0;
	var dep = '000';
	for(i=0;i<4;i++)
	{
	if($("#select"+i).find("option:selected").val()!=null&&$("#select"+i).find("option:selected").val()!=""){
	dep = $("#select"+i).find("option:selected").val();
	}
	
	}
	return dep;
	};
	
	function searchPeople(){
	$.ajax({
			url : "PeopleQueryAction",
			type : "get",
			datatype:"json",
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			data:{
			"poepleName":$("#conditionName").val(),
			"poepleId":$("#conditionId").val(),
			"roleId":$("#roleId").find("option:selected").val(),
			"poepleDep":findDep(),
			},
			success : function(data) {
			    
				try {data = JSON.parse(data);}
			catch(e)
			{
			}
			
			$("#peopleList  tr:not(:first)").remove();
			var listSen="";
			peoples.splice(0,peoples.length)
			for(var i =0;i<data.length;i++){
			peoples.push( data[i]);
			var tempid = data[i].peopid;
			listSen+="<tr id=\""+data[i].peopid+"\"><td></td><td>"+data[i].peopid
			+"</td><td>"+data[i].name
			+"</td><td>"+data[i].role
			+"</td><td>"+data[i].peoSubdetach
			+"</td><td>"+data[i].phone
			+"</td><td><a onclick=\"toModifyPersonnel("+
			i+")\">修改</a>"
			+"<a onclick=\"deleteFromPersonnel("+i+")\">删除</a></td></tr>";
			}
	$("#peopleList").append(listSen);
	
		}
     
		});
	
	}
	
</script>


<body>
<%List<DepartForMenu> Deplist =(List<DepartForMenu>)request.getAttribute("Deplist");  %>
<script>
					var  theDepId,theDepLevel;
					theDepLevel ='<%=Deplist.size()%>';
					theDepId = '<%=Deplist.get(Deplist.size()-1).getDepartID()%>';
	</script>
	<br>

	<h3>人员管理</h3>
	<br>
	<br>



	<div id="AddPersonnel" style="float:right; display:none">
	
		<p id="p1">添加人员</p>
		
		<form id = "newPeople"class="vote" action="" method="post">


			<div>


				<table frame="box" width="480px" height="580px">

					<tr>
						<td align=center ><br> <label
							for="PersonnelId" class=" ">人员ID：</label> <input
							name="PersonnelId" type="text" class="form-control"
							id="PersonnelId" value=""></td>

					</tr>


					<tr>
						<td align=center><br> <label for="PersonnelName"
							class=" ">人员名称：</label> <input name="PersonnelName" type="text"
							class="form-control" id="PersonnelName" value=""></td>

					</tr>
					
							<tr>
						<td align=center><label for="PersonnelPassword" class=" ">密码：</label>
							<input name="PersonnelPassword" class="form-control"
							id="PersonnelPassword" value=""></td>
					</tr>

					<tr>
						<td align=center><label for="PersonnelRole" class=" ">角色类别：</label>
							<select name="PersonnelRole" class="form-control"
							id="PersonnelRole" ></select></td>
					</tr>

					<tr style="display:none;">
						<td align=center><label for="PersonnelDep" class=" ">所属部门：</label>
							<input name="PersonnelDepId" class="form-control" id="PersonnelDepId"
							value=""></td>
							
					</tr>
					<tr>
					<td>
					 <select class="depMenu" name="selectIn0" id = "selectIn0">
						 <% if(Deplist.size()>0){%>
						 <option value="<%=Deplist.get(0).getDepartID()%>"  onfocus=this.blur() selected="selected"><%=Deplist.get(0).getDepartName()%></option>
						 <%} %>
					</select><label>总队</label> 
					</td>
					</tr>
					<tr>
					<td>
						 <select name="selectIn1" id = "selectIn1">
					 <% if(Deplist.size()>1){%>
						 <option value="<%=Deplist.get(1).getDepartID()%>" onfocus=this.blur() selected="selected"><%=Deplist.get(1).getDepartName()%></option>
						 <%} %>
					</select><label>支队</label> 
					</td>
					</tr>
					<tr>
					<td>
					<select name="selectIn2" id = "selectIn2">
						<% if(Deplist.size()>2){%>
						 <option value="<%=Deplist.get(2).getDepartID()%>" onfocus=this.blur() selected="selected"><%=Deplist.get(2).getDepartName()%></option>
						 <%} %>
					</select> <label>大队</label> 
					</td>
					</tr>
					<tr>
					<td>
					<select name="selectIn3"  id = "selectIn3">
						<% if(Deplist.size()>3){%>
						 <option value="<%=Deplist.get(3).getDepartID()%>" onfocus=this.blur() selected="selected"><%=Deplist.get(3).getDepartName()%></option>
						 <%} %>
					</select> <label>中队</label>
					</td>
					</tr>

						<tr>
						<td align=center><label for="PersonnelDep" class=" ">所属部门：</label>
							<input name="PersonnelDep" class="form-control" id="PersonnelDep"
							value="" onfocus=this.blur()></td>
					</tr>

					<tr>
						<td align=center><label for="PersonnelPhone" class=" ">电话：</label>
							<input name="PersonnelPhone" class="form-control"
							id="PersonnelPhone" value=""></td>

					</tr>

					<tr>
						<td align=center><button type="button" onclick="toHideAdd()" class="">&nbsp;返回&nbsp;</button> <button id= "sub" type="button" onclick="submitAdd()" class="">&nbsp;修改&nbsp;</button></td>

					</tr>
				</table>
			</div>
		</form>
	</div>





	<%
		int number = 0;
	%>



	<div id="Personnel"
		style="overflow-y:scroll; width:1000px; height:300px;">

		<form action="  " method="post">

			<div class=" ">
				<div class="">
					<input name="conditionId" type="text" class="" style=""
						id="conditionId" placeholder="人员编号"> <input
						name="conditionName" type="text" class="" style="" id="conditionName"
						placeholder="人员姓名">
						 <select class="depMenu" name="roleId" id = "roleId">
						 </select>
						 <select class="depMenu" name="select0" id = "select0">
						 <% if(Deplist.size()>0){%>
						 <option value="<%=Deplist.get(0).getDepartID()%>"  disabled="disabled"  selected="selected"><%=Deplist.get(0).getDepartName()%></option>
						 <%} %>
					</select><label>总队</label> 
						 <select name="select1" id = "select1">
					 <% if(Deplist.size()>1){%>
						 <option value="<%=Deplist.get(1).getDepartID()%>" disabled="disabled"  selected="selected"><%=Deplist.get(1).getDepartName()%></option>
						 <%} %>
					</select><label>支队</label> <select name="select2" id = "select2">
						<% if(Deplist.size()>2){%>
						 <option value="<%=Deplist.get(2).getDepartID()%>" disabled="disabled"  selected="selected"><%=Deplist.get(2).getDepartName()%></option>
						 <%} %>
					</select> <label>大队</label> <select name="select3"  id = "select3">
						<% if(Deplist.size()>3){%>
						 <option value="<%=Deplist.get(3).getDepartID()%>" disabled="disabled"  selected="selected"><%=Deplist.get(3).getDepartName()%></option>
						 <%} %>
					</select> <label>中队</label>
					









					<button type="button" class="" id="Search" onclick="searchPeople()">&nbsp;&nbsp;查询</button>
					&nbsp;<a onclick="toAddPersonnel()">添加</a>
				</div>
			</div>

		</form>



		<table id = "peopleList" border="1" cellspacing="0">
	
			<tr>
				<td width="10%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td width="10%"><label class="" >ID</label></td>
				<td width="10%"><label class="" >姓名</label></td>
				<td width="10%"><label class="" >角色类别</label></td>
				<td width="30%"><label class="">所属支队</label></td>
				
				
				
				<td width="10%"><label class="" >联系电话</label></td>
				<td align=center><label class="" >操作</label></td>
			</tr>

			

				


	

		</table>


	</div>






</body>
</html>
