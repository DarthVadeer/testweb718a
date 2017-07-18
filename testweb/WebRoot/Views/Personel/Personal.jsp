<%@page import="model.People"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="model.People" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../../Resources/css/bootstrap_3.0.3/css/bootstrap.min.css">
    <link rel="stylesheet"
	href="../../Resources/css/style/common.css">
    <link rel="stylesheet" href="../../Resources/css/common.css">
    <script src="Resources/js/jquery.min.js"></script>
    <script src="Resources/css/bootstrap_3.0.3/js/bootstrap.min.js"></script>
<base href="<%=basePath%>">

<title>My JSP 'Personal.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="Resources/lib/jquery-1.6.min.js"></script>
<%People people = new People();
      people = (People)request.getAttribute("people");  
  %>
<script type="text/javascript">


function submitInfo(){

	$.ajax({
			url : "PeopleUpdateAction",
			type : "get",
			data:encodeURI($('#peopleInfo').serialize()),

			success : function(data) {
				if(data==1)
				alert("修改成功！");
				else
				alert("修改失败！");
}


});
};


function modifyCancel(){
$("#PersonName").val(<%=people.getName()%>);
$("#PersonnelPhone").val(<%=people.getPhone()%>);


}



</script>

</head>

<body class="row tableHeader" >
	
	<div id="Personl" >
    <div class="well" style="background-color:#333">
		<h3>个人信息</h3>
        </div>

		<form id = "peopleInfo" action="" method="post">

			<div style="float:center;" >


				<table frame="box" width="480px" height="580px" class="check-div">

					<tr>
						<td align=center style="display:none;"><br> <label
							for="PersonId" class=" ">人员ID：</label> <input name="PersonnelId"  onfocus=this.blur() 
							type="text" class="form-control" id="PersonnelId"  value = "<%=people.getPeopid()%>"></td>

					</tr>


					<tr>
						<td align=center style="display:none;"><label
							for="PersonnelPassword" class=" ">密码：</label> <input
							name="PersonnelPassword" class="form-control" id="PersonnelPassword"
							 value = "<%=people.getPassword()%>"></td>
					</tr>



					<tr>
						<td align=center><label for="PersonnelName" >姓名：</label>
							<input name="PersonnelName" class="form-control" id="PersonnelName"
							  value = "<%=people.getName()%>"></td>
					</tr>

					<tr>
						<td align=center><br> <label for="PersonnelDepId" class=" ">部门名称：</label>
							<input name="PersonnelDepId" type="text" class="form-control"
							id="PersonnelDepId"    onfocus=this.blur()  value = "<%=people.getPeoSubdetachName()%>"></td>

					</tr>



					<tr>
						<td align=center><label for="PersonnelPhone" class=" ">电话：</label>
							<input name="PersonnelPhone" class="form-control" id="PersonnelPhone"
							  value = "<%=people.getPhone()%>"></td>

					</tr>
					
								<tr>
						<td align=center><label for="PersonnelRole" class=" ">角色：</label>
							<input name="PersonnelRole" class="form-control" id="PersonnelRole"  onfocus=this.blur() 
							  value = "<%=people.getRoleName()%>"></td>

					</tr>


					<tr>
						<td align=left>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class=" ">
								个人简介：</label>
					</tr>
					
					

					<tr>
						<td align=center height="98" ><textarea rows="5" cols="27" class="form-control"
								name = “PersonDesc” id="PersonDesc"  ></textarea></td>

					</tr>


					<tr>

						<td align=center>
							<button type="button" onclick="modifyCancel()" class="btn btn-success" >&nbsp;取消&nbsp;</button>
							<button type="button" onclick="submitInfo()" class="btn btn-danger">&nbsp;修改&nbsp;</button>
						</td>

					</tr>
					</table>

				<br>


			</div>
		</form>
	</div>

</body>
</html>
