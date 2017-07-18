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
$("#PersonName").val();

}


</script>

</head>

<body>
	<br>
	<div id="Personl">
		<h3>个人信息</h3>

		<form id = "peopleInfo" action="" method="post">

			<div style="float:center">


				<table frame="box" width="480px" height="580px">

					<tr>
						<td align=center style="display:none;"><br> <label
							for="PersonId" class=" ">人员ID：</label> <input name="PersonId"
							type="text" class="form-control" id="PersonId"  value = "<%=people.getPeopid()%>"></td>

					</tr>


					<tr>
						<td align=center style="display:none;"><label
							for="PersonPassword" class=" ">密码：</label> <input
							name="PersonPassword" class="form-control" id="PersonPassword"
							 value = "<%=people.getPassword()%>"></td>
					</tr>



					<tr>
						<td align=center><label for="PersonName" class=" ">姓名：</label>
							<input name="PersonName" class="form-control" id="PersonName"
							  value = "<%=people.getName()%>"></td>
					</tr>

					<tr>
						<td align=center><br> <label for="DepName" class=" ">部门名称：</label>
							<input name="DepName" type="text" class="form-control"
							id="DepName"   value = "<%=people.getPeoSubdetach()%>"></td>

					</tr>



					<tr>
						<td align=center><label for="PersonPhone" class=" ">电话：</label>
							<input name="PersonPhone" class="form-control" id="PersonPhone"
							  value = "'<%=people.getPhone()%>'"></td>

					</tr>
					
								<tr>
						<td align=center><label for="PersonRole" class=" ">角色：</label>
							<input name="PersonRole" class="form-control" id="PersonRole"
							  value = "<%=people.getRole()%>"></td>

					</tr>


					<tr>
						<td align=left>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class=" ">
								个人简介：</label>
					</tr>
					
					

					<tr>
						<td align=center height="98"><textarea rows="5" cols="27"
								name = “PersonDesc” id="PersonDesc"  ></textarea></td>

					</tr>


					<tr>

						<td align=center>
							<button type="button" onclick="modifyCancel()" class="" >&nbsp;取消&nbsp;</button>
							<button type="button" onclick="submitInfo()" class="">&nbsp;修改&nbsp;</button>
						</td>

					</tr>

				</table>

			</div>
		</form>
	</div>

</body>
</html>
