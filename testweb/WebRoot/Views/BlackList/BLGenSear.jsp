<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'BLGenSea.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
<link rel="stylesheet" type="text/css" href="styles.css">-->

<script type="text/javascript" src="Resources/lib/jquery-1.6.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : "BlackAllQuery",
			type : "get",
			dataType : "json",
			data : {},
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data) {

				try {
					data = JSON.parse(data);
				} catch (e) {
				}

				var depOptions = "";
				alert(data.length);
				for (var i = 0; i < data.length; i++) {
					depOptions += "<tr>";
					depOptions += "<td></td>";
					depOptions += "<td>" + data[i].BlackId + "</td>";
					depOptions += "<td>" + data[i].BlackFlowId + "</td>";
					depOptions += "<td>" + data[i].Blackcarnumber + "</td>";
					depOptions += "<td>" + data[i].BlackApplydate + "</td>";
					depOptions += "<td>" + data[i].BlackState + "</td>";
					depOptions += "<td>" + data[i].BlackApplicant + "</td>";
					depOptions += "<td>" + data[i].BlackConPoint + "</td>";
					depOptions += "</tr>";
				}

				$("#blackListTable tr:not(:first)").html("");

				$("#blackListTable").append(depOptions);
			}
		});
		
        $("#sear").click(function() {
		$.ajax({
			url : "BlackListQueryAction",
			type : "get",
			dataType : "json",
			data : {"Blackcarnumber":$("#CarNum").val()},
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data){
			
			try {
					data = JSON.parse(data);
				} catch (e) {
				}
				
				var depOptions = "";
				for (var i = 0; i < data.length; i++) {
				    depOptions += "<tr>";
					depOptions += "<td></td>";
					depOptions += "<td>" + data[i].BlackId + "</td>";
					depOptions += "<td>" + data[i].BlackFlowId + "</td>";
					depOptions += "<td>" + data[i].Blackcarnumber + "</td>";
					depOptions += "<td>" + data[i].BlackApplydate + "</td>";
					depOptions += "<td>" + data[i].BlackState + "</td>";
					depOptions += "<td>" + data[i].BlackApplicant + "</td>";
					depOptions += "<td>" + data[i].BlackConPoint + "</td>";
					depOptions += "</tr>";
				}
					$("#blackListTable tr:not(:first)").html("");
				    $("#blackListTable").append(depOptions);
				}
			});
			

		});
		

	});
</script>

</head>

<body>
	<br />
	<br />
	<div style="text-align:center;">
		<a>输入车牌号：</a> <input type="text" id="CarNum" />
		<button id="sear">&nbsp;&nbsp;查询</button>
	</div>
	<br />
	<br />
	<div id="BlackList" >
		<table border="1" cellpadding="2" cellspacing="1" align=center style="overflow:auto;width:90%;table-layout:fixed;word-wrap:break-word;">
		<thead>
		    <tr>
				<th colspan='8'><label class="">&nbsp;&nbsp;黑名单表</label></th>
			</tr>
			
		</thead>
		<tbody id="blackListTable">
		    <tr>
				<th width="10%"><label id="BlackNum">数量</label></th>
				<th width="10%"><label id="BlackId">编号</label></th>
				<th width="30%"><label id="BlackFlowId">车流编号</label></th>
				<th width="10%"><label id="Blackcarnumber">号牌号码</label></th>
				<th width="10%"><label id="BlackApplydate">申请时间</label></th>
				<th width="10%"><label id="BlackState">状态</label></th>
				<th width="10%"><label id="BlackApplicant">申请人</label></th>
				<th width="10%"><label id="BlackConPoint">布控点位</label></th>
				
			</tr>
		    <tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>不知道</td>
			</tr>
		</tbody>
			
		</table>
	</div>

</body>
</html>
