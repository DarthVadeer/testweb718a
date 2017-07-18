<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  <html><frameset cols="20%,*">
<frame>这里是菜单页面</frame>
<frame>这里是点菜单后显示对应的内容页面</frame>-->
<html>
<head>
<title>Bootstrap菜单</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<!-- Bootstrap -->
<link href="../../Resources/css/bootstrap_3.0.3/css/bootstrap.min1.css" rel="stylesheet">
<link rel="stylesheet"
	href="../../Resources/css/bootstrap_3.0.3/css/font-awesome.min1.css">

<script src="../../Resources/css/bootstrap_3.0.3/js/jquery-1.8.2.min.js"></script>
<script src="../../Resources/css/bootstrap_3.0.3/js/bootstrap.min1.js"></script>

<style type="text/css">
body {
	color: #000;
	font-size: 12px;
	font-family: "Helvetica Neue", Helvetica, STheiti, 微软雅黑, 宋体, Arial,
		Tahoma, sans-serif, serif;
}

table {
	width: 20%;
	height: 100%;
}

/*左侧菜单*/
.sidebar-menu {
	border-right: 1px solid #c4c8cb;
}
/*一级菜单*/
.menu-first {
	height: 45px;
	line-height: 45px;
	background-color: #e9e9e9;
	border-top: 1px solid #efefef;
	border-bottom: 1px solid #e1e1e1;
	padding: 0;
	font-size: 18px;
	font-weight: normal;
	text-align: left;
}
/*一级菜单鼠标划过状态*/
.menu-first:hover {
	text-decoration: none;
	background-color: #d6d4d5;
	border-top: 1px solid #b7b7b7;
	border-bottom: 1px solid #acacac;
}
/*二级菜单*/
.menu-second li a {
	background-color: #f6f6f6;
	height: 31px;
	line-height: 31px;
	border-top: 1px solid #efefef;
	border-bottom: 1px solid #efefef;
	font-size: 14px;
	text-align: center;
}
/*二级菜单鼠标划过样式*/
.menu-second li a:hover {
	text-decoration: none;
	background-color: #66c3ec;
	border-top: 1px solid #83ceed;
	border-bottom: 1px solid #83ceed;
	border-right: 3px solid #f8881c;
	border-left: 3px solid #66c3ec;
}
/*二级菜单选中状态*/
.menu-second-selected {
	background-color: #66c3ec;
	height: 31px;
	line-height: 31px;
	border-top: 1px solid #83ceed;
	border-bottom: 1px solid #83ceed;
	border-right: 3px solid #f8881c;
	border-left: 3px solid #66c3ec;
	text-align: center;
}
/*覆盖bootstrap的样式*/
.nav-list, .nav-list li a {
	padding: 0px;
	margin: 0px;
}
</style>


<%
	String 卡口系统 = (String) session.getAttribute("000");
	String 过车信息管理 = (String) session.getAttribute("001");
	//String 车辆轨迹回放 = (String) session.getAttribute("002");
	String 车辆轨迹回放 = "1";
	String 卡口设备管理 = (String) session.getAttribute("003");

	String 黑名单布控 = (String) session.getAttribute("010");
	String 布控申请 = (String) session.getAttribute("011");
	String 布控审核 = (String) session.getAttribute("012");
	String 布控管理 = (String) session.getAttribute("013");
	//String 报警处理 = (String) session.getAttribute("014");
	String 报警处理 = "1";
	String 布控撤销 = (String) session.getAttribute("015");
	String 综合查询 = (String) session.getAttribute("016");
	String 报警查询 = (String) session.getAttribute("017");

	String 资源管理 = (String) session.getAttribute("020");
	//String 道路代码 = (String) session.getAttribute("021");
	String 道路代码  = "1";
	String 点位信息 = (String) session.getAttribute("022");

	String 系统管理 = (String) session.getAttribute("030");
	String 部门管理 = (String) session.getAttribute("031");
	String 角色管理 = (String) session.getAttribute("032");
	//String 人员管理 = (String) session.getAttribute("033");
	String 人员管理  = "1";
	String 个人信息 = (String) session.getAttribute("034");
%>




</head>
<!-- target="main"  -->
<body>
	<div id="container1" style="width:100%; height:100%">
		<div class="sidebar-menu">

			<%
				if (过车信息管理 == "1" || 车辆轨迹回放 == "1" || 卡口设备管理 == "1") {
			%>

			<a href="#Equipment" class="nav-header menu-first collapsed"
				data-toggle="collapse"><i class="icon-list icon-large"></i> 卡口系统</a>

			<ul id="Equipment" class="nav nav-list collapse menu-second">

				<%
					if (过车信息管理 == "1") {
				%>

				<li><a href="CarInfoListAction" target="map"><i class="icon-list"></i> 过车信息查询</a></li>
				<%
					}
				%>
				<%
					if (车辆轨迹回放 == "1") {
				%>
				<li><a href="CarPathViewAction" target="map"><i class="icon-edit"></i> 车辆轨迹回放</a></li>

				<%
					}
				%>
				<%
					if (卡口设备管理 == "1") {
				%>
				<li><a href="EquipListAction" target="map"><i class="icon-list"></i> 卡口设备管理</a></li>

				<%
					}
				%>


			</ul>
		</div>
		<%
			}
		%>



		<%
			if (布控申请 == "1" || 布控审核 == "1" || 布控管理 == "1" || 报警处理 == "1" || 布控撤销 == "1" || 综合查询 == "1" || 报警查询 == "1") {
		%>

		<a href="#Blacklist" class="nav-header menu-first collapsed"
			data-toggle="collapse"><i class="icon-user-md icon-large"></i>
			黑名单布控</a>
		<ul id="Blacklist" class="nav nav-list collapse menu-second">
			<%
				if (布控申请 == "1") {
			%>
			<li><a href="#"><i class="icon-edit"></i> 布控申请</a></li>
			<%
				}
			%>
			<%
				if (布控审核== "1") {
			%>
			<li><a href="#"><i class="icon-list"></i> 布控审核</a></li>
			<%
				}
			%>
			<%
				if (布控管理 == "1") {
			%>
			<li><a href="#"><i class="icon-edit"></i> 布控管理</a></li>
			<%
				}
			%>
			<%
				if (报警处理 == "1") {
			%>
			<li><a href="#"><i class="icon-edit"></i> 报警处理</a></li>
			<%
				}
			%>
			<%
				if ( 布控撤销 == "1") {
			%>
			<li><a href="#"><i class="icon-trash"></i> 布控撤销</a></li>
			<%
				}
			%>
			<%
				if (综合查询 == "1") {
			%>
			<li><a href="BlackAllQueryAction" target="map"><i class="icon-list"></i> 综合查询</a></li>
			<%
				}
			%>
			<%
				if (报警查询 == "1") {
			%>
			<li><a href="AlarmAllQueryAction" target = "map"><i class="icon-list"></i> 报警查询</a></li>
			<%
				}
			%>
		</ul>

		<%
			}
		%>

		<%
				if (过车信息管理 == "1" || 车辆轨迹回放 == "1" || 卡口设备管理 == "1") {
			%>
		<a href="#Resources" class="nav-header menu-first collapsed"
			data-toggle="collapse"><i class="icon-edit icon-large"></i> 资源管理</a>
		<ul id="Resources" class="nav nav-list collapse menu-second">

			<%
				if (道路代码 == "1") {
			%>
			<li><a href="RoadQueryAction" target="map"><i class="icon-edit"></i> 道路代码</a></li>
			<%
			}
		%>
			<%
				if (点位信息 == "1") {
			%>
			<li><a href="PointListAction" target="map"><i class="icon-edit"></i> 点位信息</a></li>
			<%
			}
		%>
		</ul>
		<%
			}
		%>


		<%
				if (部门管理 == "1" || 角色管理 == "1" || 人员管理 == "1"|| 个人信息 == "1") {
			%>
		<a href="#System" class="nav-header menu-first collapsed"
			data-toggle="collapse"><i class="icon-book icon-large"></i> 系统管理</a>
		<ul id="System" class="nav nav-list collapse menu-second">

			<%
				if (部门管理 == "1" ) {
			%>
			<li><a href="DepartQueryAction" target="map"><i class="icon-list-alt"></i> 部门管理</a></li>
			<%
			}
		%>
			<%
				if (角色管理 == "1" ) {
			%>
			<li><a href="RoleListAction" target="map"><i class="icon-user"></i> 角色管理</a></li>
			<%
			}
		%>
			<%
				if (人员管理== "1" ) {
			%>
			<li><a href="DepartMenuAction"   target="map"><i class="icon-edit"></i> 人员管理</a></li>
			<%
			}
		%>
			<%
				if (个人信息== "1") {
			%>
			<li><a href="PeopleInfoAction"  target="map"><i class="icon-user"></i> 个人信息</a></li>
			<%
			}
		%>
		</ul>
		<%
			}
		%>

	</div>




</body>
</html>
