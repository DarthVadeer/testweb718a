<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="shortcut icon" href="img/title.ico">
<script type="text/javascript"> 
if (top.location !== self.location) {
    top.location=self.location;
}
</script>
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="../../css/bootstrap_3.0.3/css/bootstrap.css">
<link rel="stylesheet" href="../../css/main_css/supersized.css">
<link rel="stylesheet" type="text/css" href="../../css/main_css/main.css">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<title>用户登录</title>

<script type="text/javascript">
	var canRegister = true;
	
	<%int i = 0;
			int o = 0;
			String str = "";%>
		
	function Check() {
	
	str = document.getElementById("userName").value;
	o = str.length;

		if (document.getElementById("userName").value == "" || document.getElementById("password").value == "") {
			alert("用户名或者密码不能为空！");
			return false;
		} else {
		}
		
		if((str.indexOf(".")>-1) || (str.indexOf("+")>-1)||(str.indexOf("-")>-1)||(str.indexOf("*")>-1)||(str.indexOf("/")>-1)
		||(str.indexOf("?")>-1)||(str.indexOf(">")>-1)||(str.indexOf("<")>-1)||(str.indexOf(",")>-1)||(str.indexOf(";")>-1)||(str.indexOf("'")>-1)
		||(str.indexOf("[")>-1)||(str.indexOf("]")>-1)||(str.indexOf("%")>-1)||(str.indexOf("^")>-1)||(str.indexOf("&")>-1)||(str.indexOf("@")>-1)||(str.indexOf("#")>-1)
		||(str.indexOf("$")>-1)||(str.indexOf("~")>-1)||(str.indexOf("`")>-1)||(str.indexOf("|")>-1)||(str.indexOf("{")>-1)||(str.indexOf("}")>-1)||(str.indexOf(":")>-1)
		||(str.indexOf("(")>-1)||(str.indexOf(")")>-1)  )
		{
		alert("非法字符");
		return false;
		}else{
			
			if( o >12){
			alert("12字符以内！");
			return false;
			}
		}
		
			
			
		var numstr = /^[A-Za-z0-9]+$/;
		if(!numstr.test(document.getElementById("userName").value)){
			
			
			
			
			
			
			
			alert("不合规范");
			return false;
			}else{
			if(!numstr.test(document.getElementById("password").value)){
			
			alert("不合规范");
			return false;
			}else{
		
			return true;
			}
			}
			
			
	//CheckChinese()
	var re = /^[\u4e00-\u9fa5]{0,}$/;
	var str = "理科计划";

	alert(document.getElementById("00").value);
	
	if(document.getElementById("00").value.length >str.length){
	
	alert("最多四个字");
	return false;
	}
	if(!re.test(document.getElementById("00").value)){
	alert("输入中文");
	return false;
	}

	//CheckNumber
	var num = /^[0-9]*$/;
	var NUM = /^[１-９]*$/;
	var DByte = /[^\x00-\xff]/;

	if(!DByte.test(document.getElementById("userNum").value)){
	
	if(!NUM.test(document.getElementById("userNum").value)){

	alert("输入int类型");
	return false;
	}
	}else{
	if(!num.test(document.getElementById("userNum").value)){

	alert("输入int类型");
	return false;
	}
	alert("ＯＫ");
	return true;
	}
	}
	
	



	function GetNewUsernameFocus() {
		document.getElementById("nameValid").innerHTML = "";
		document.getElementById("NewUsername").value = "";
	}

	function GetUsernameFocus() {
		document.getElementById("usernameValid").innerHTML = "";
	}

	function GetPasswordFocus() {
		document.getElementById("passwordValid").innerHTML = "";
	}

	function resetValue() {
		document.getElementById("userName").value = "";
		document.getElementById("password").value = "";
	}
</script>



</head>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="css/bootstrap_3.0.3/js/jquery-2.0.2.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="css/bootstrap_3.0.3/js/bootstrap.js"></script>
	<script src="css/main_js/supersized.3.2.7.min.js"></script>
	<script src="css/main_js/supersized-init.js"></script>


<body>
	<div class="container">
		<h1>PVCS</h1>
		
		<form class="form-horizontal" role="form" action="LoginAction" method="post"
			onsubmit="return Check()">

			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-8 col-sm-offset-4 col-sm-4">
					<input name="userName" onfocus="GetUsernameFocus()" type="text"
						class="form-control form-control-white"
						style="background:rgba(100,100,100,.15);border:1px solid #ffffff"
						id="userName" placeholder="用户名:">
				</div>
				<label id="usernameValid" style="color:red;"></label>
			</div>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-8 col-sm-offset-4 col-sm-4">
					<input name="password" onblur="RequestForLogin()"
						onfocus="GetPasswordFocus()" type="password"
						class="form-control form-control-white"
						style="background:rgba(100,100,100,.15);border:1px solid #ffffff"
						id="password" placeholder="密   码:">
				</div>
				<label id="passwordValid" style="color:red;"></label>
			</div>
			<%
				Integer result = (Integer) request.getAttribute("result");
				if (result != null) {
					out.println("<div class=\"form-group \">");
					out.println("<div class=\"col-xs-offset-2 col-xs-8 col-sm-offset-4 col-sm-4\">");
					out.println(
							"<div class=\"alert alert-warning alert-dismissable\" style=\"background-color:rgba(255,255,255,0.1); border:0px\">");
					out.println(
							"<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>");
					if (result == StatusCode.ERROR_NOUSERNAME) {
						out.println("<strong>错误：&nbsp;&nbsp;</strong>用户名不存在");
					} else if (result == StatusCode.ERROR_WRONGPASSWORD) {
						out.println("<strong>错误：&nbsp;&nbsp;</strong>密码错误");
					} else if (result == StatusCode.ERROR_NORIGHT) {
						out.println("<strong>提示：&nbsp;&nbsp;</strong>你还没有权限，等管理员分配权限");
					} else if (result == StatusCode.PROMPT_LOGIN_OUT) {
						out.println("<strong>提示：&nbsp;&nbsp;</strong>注销成功!");
					} else if (result == StatusCode.ERROR_LOGIN_OUT) {
						out.println("<strong>警告：&nbsp;&nbsp;</strong>连接超时，请重新登录");
					} else if (result == StatusCode.PROMPT_REGISTER_SUCCESS) {
						out.println("<strong>提示：&nbsp;&nbsp;</strong>添加成功，等待管理员分配权限");
					} else if (result == StatusCode.ERROR_LOGIN_OUT) {
						out.println("<strong>错误：&nbsp;&nbsp;</strong>添加失败!");
					}
					//out.println(result);
					out.println("</div></div></div>");
				}
			%>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-8 col-sm-offset-4 col-sm-4"
					align="center">
					<button type="submit" class="btn btn-info btn-block" id="登录">登录</button>
					<button onclick="resetValue()" class="btn btn-info btn-block"
						data-toggle="modal" data-target="#myModal">重置</button>

				</div>
			</div>
		</form>
	
<jsp:include page="Footer.jsp"></jsp:include>
	</div>


	
</body>
</html>