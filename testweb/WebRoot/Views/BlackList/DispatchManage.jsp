<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'DispatchManage.jsp' starting page</title>
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
<script type="text/javascript">

	var Dispatch = new Array();

	$(document).ready(function() {}) 


	function SerchVerify() {   // 查询
	
		var blackCarNum = $("#BlackCarNum").val();
		var selectState = $("#SelectState").val();
		//var x = document.getElementById("BeginDate").value;
		//var y = document.getElementById("EndDate").value;

alert(blackCarNum);
alert(selectState);

		$.ajax({
			url : "BlackBKManageAction",
			type : "post",
			dataTpe : "json",
			data : {
				"BlackCarNum" : blackCarNum,
				"SelectState" : selectState
			},
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data) {

				try {
					data = JSON.parse(data);
				} catch (e) {
					console.log("异常： Serch Verify Info");
				}

				var depOptions = "";
				for (var i = 0; i < data.length; i++) {
					Dispatch.push(data[i]);

					if (data[i].blackState == 3) {
						depOptions += "<tr>";
						depOptions += "<td></td>";
						depOptions += "<td>" + data[i].blackCarNum + "</td>";
						depOptions += "<td>" + data[i].blackDcoDate + "</td>";
						depOptions += "<td>" + data[i].blackApplicant + "</td>";
						depOptions += "<td>" + data[i].blackReason + "</td>";
						depOptions += "<td>" + "一步空" + "</td>";
						depOptions += "<td>查看详情</td>";
						depOptions += "</tr>";
					} else {
						if (data[i].blackState == 2) {
							depOptions += "<tr>";
							depOptions += "<td></td>";
							depOptions += "<td>" + data[i].blackCarNum + "</td>";
							depOptions += "<td>" + data[i].blackDcoDate + "</td>";
							depOptions += "<td>" + data[i].blackApplicant + "</td>";
							depOptions += "<td>" + data[i].blackReason + "</td>";
							depOptions += "<td><a id = \"Verify\">" + "待布控" + "</a></td>";
							depOptions += "<td><a onclick=\"+toDispatch (" + i + ")\">布控</a><a onclick=\"+Publish(" + i + ")\">发布</a></td>";
							depOptions += "</tr>";
						} else {
							alert("状态标识值问题");
						}
					}
				}

				$("#BlackDispatchList tr:not(:first)").remove();
				$("#BlackDispatchList").append(depOptions);
			}
		})
	}


	function Publish(i) {  // 发布
		var blackId = Dispatch[i].BlackId;
		if (document.getElementById("Verify").valu == "待布控") {
			alert("请先选择布控");
		} else {
			var blackConPoint = document.getElementById("Verify").value;

			$.ajax({
				url : "BlackBKUpdateAction",
				type : "post",
				dataType : "json",
				data : {
					"BlackConPoint" : blackConPoint,
					"BlackId" : blackId,
				},
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				success : function(data) {
					if (data == 1) {
						alert("发布成功！");
						window.location.reload();
					}
					else
						alert("发布失败！");
				}
			})
		}
	}




	function toPublish() { // 传出文本框值

		$("#Verify").text($("#VerifyPoint").val());


		$("#Dispatchmap").fadeOut();
		$("#BlackVerify").fadeIn();

	}

	function tohide() { // 隐藏地图及文本框
		$("#Dispatchmap").fadeOut();
		$("#BlackVerify").fadeIn();

	}

	function toDispatch() { //调出地图
	
	
		$("#VerifyPoint").val($("#Verify").text());
	
		$("#Dispatchmap").fadeIn();
		$("#BlackVerify").fadeOut();

	}
</script>








<body>
	<br>


	<div id="Dispatchmap" style="display : none">


		<!-- 地图 -->








		<table frame="box" width="280px" height="100px">

			<tr>
				<td align=center><input type="text" id="VerifyPoint"
					placeholder="布控点位"></input></td>
			</tr>
			<tr>
				<td align=center><button onclick="tohide()" class="">&nbsp;返回&nbsp;</button>
					<button id="sub" onclick="toPublish()" class="">&nbsp;确定&nbsp;</button></td>

			</tr>
		</table>
	</div>





	<div id="BlackVerify" style="width:100%; height:100%;">

		<form action="" method="post">

			<div>
				<select id="SelectState">
					<option value="100">全部</option>
					<option value="2" selected="selected">未发布</option>
					<option value="3">已发布</option>
				</select>
				<!--  <input type="date" id="BeginDate" required min="2001-01-01" max=""
					value="2014-06-01"> - <input type="date" id="EndDate"
					required min="2001-01-01" max="" value="2016-07-14">-->
				<input type="text" id="BlackCarNum" placeholder="车牌号码"></input>
				<button type="button" class="" id="Serch" onclick="SerchVerify()">&nbsp;&nbsp;查询</button>

			</div>

		</form>

		<table border="1" cellspacing="0">
			<thead>
				<tr>
					<th colspan='7'><label class="" id="">&nbsp;&nbsp;布控管理</label></th>
				</tr>


			</thead>
			<tbody id="BlackDispatchList">
				<tr>
					<td width="10%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td width="20%"><label class="" id="Blackcarnumber">号牌号码</label></td>
					<td width="10%"><label class="" id="BlackApplydate">申请时间</label></td>
					<td width="10%"><label class="" id="BlackApplicant">申请人</label></td>
					<td width="10%"><label class="" id="BlackReason">申请原因</label></td>
					<td width="20%"><label class="" id="BlackConPoint">布控</label></td>
					<td align=center><label class="" id="">操作</label></td>
				</tr>



				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>

						<button class="" onclick="">&nbsp;&nbsp;发布&nbsp;&nbsp;</button>

					</td>

				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
