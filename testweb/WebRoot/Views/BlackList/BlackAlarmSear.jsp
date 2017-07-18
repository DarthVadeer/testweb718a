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
    
    <title>My JSP 'BlackAlarmSear.jsp' starting page</title>
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
			url : "BlackListQueryAllAction",
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
				
				for (var i = 0; i < data.length; i++) {
					depOptions += "<tr>";
					depOptions += "<td>"+i+1+"</td>";
					depOptions += "<td>" + data[i].AlarmId + "</td>";
					depOptions += "<td>" + data[i].AlarmCarNumber + "</td>";
					depOptions += "<td>" + data[i].AlarmTime + "</td>";
					depOptions += "<td>" + data[i].AlarmState + "</td>";
					depOptions += "<td align=\"+center\"><a onclick=\"+check("+i+")\">查看</a></td>";
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
			data : {"AlarmCarNumber":$("#CarNum").val(),
			        "BeginTime":$("beginTime").val(),
			        "EndTime":$("endTime").val(),
			},
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data){
			
			try {
					data = JSON.parse(data);
				} catch (e) {
				}
				
				var depOptions = "";
				for (var i = 0; i < data.length; i++) {
				    depOptions += "<tr>";
					depOptions += "<td>"+i+1+"</td>";
					depOptions += "<td>" + data[i].AlarmId + "</td>";
					depOptions += "<td>" + data[i].AlarmCarNumber + "</td>";
					depOptions += "<td>" + data[i].AlarmTime + "</td>";
					depOptions += "<td>" + data[i].AlarmState + "</td>";
					depOptions += "<td align=\"+center\"><a onclick=\"+check("+i+")\">查看</a></td>";
					depOptions += "</tr>";
					}
					$("#blackListTable tr:not(:first)").html("");
				    $("#blackListTable").append(depOptions);		
				}			
			});		
		});
	});
	function check(i){
	
			/*$.ajax({
			url : "BlackListQueryAction",
			type : "get",
			dataType : "json",
			data : {"AlarmCarNumber":$("AlarmList").find("tr").eq(i).find("td").eq(2).text(),
			        "BeginTime":$("AlarmList").find("tr").eq(i).find("td").eq(3).text(),
			        "EndTime":$("AlarmList").find("tr").eq(i).find("td").eq(3).text(),
			        
			},
			contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
			success : function(data){
			
			try {
					data = JSON.parse(data);
				} catch (e) {
				}
				
				var depOptions = "";
				for (var i = 0; i < data.length; i++) {
				    $("#AlarmId").val(data[i].AlarmId);
				    $("#AlarmPastId").val(data[i].AlarmPastId);
				    $("#AlarmCarNumber").val(data[i].AlarmCarNumber);
				    $("#AlarmTime").val(data[i].AlarmTime);
				    $("#AlarmCollectionDate").val(data[i].AlarmCollectionDate);
				    $("#AlarmState").val(data[i].AlarmState);
				    $("#AlarmHandler").val(data[i].AlarmHandler);
				    $("#AlarmIntercept").val(data[i].AlarmIntercept);
				    $("#AlarmPhone").val(data[i].AlarmPhone);
				    $("#AlarmMemo").val(data[i].AlarmMemo);
				    $("#AlarmFeedBack").val(data[i].AlarmFeedBack);
				    $("#AlarmHandleProcess").val(data[i].AlarmHandleProcess);
				    $("#AlarmUninterceptedReason").val(data[i].AlarmUninterceptedReason);
					}
				}
				
			});*/
			
			 var tab1=document.getElementById("tab1");
             var tab2=document.getElementById("tab2");
 
             tab1.style.display=(tab1.style.display=="none"?"":"none");
             tab2.style.display=(tab2.style.display=="none"?"":"none");
		
		}
		function back(){
		     var tab1=document.getElementById("tab1");
             var tab2=document.getElementById("tab2");
 
             tab2.style.display=(tab2.style.display=="none"?"":"none");
             tab1.style.display=(tab1.style.display=="none"?"":"none");
             
		
		}
		


</script>

  </head>
  
  <body>
  <br />
	<br />
	<div id="tab1">
		<div style="text-align:center;">
			<a>输入车牌号：</a> <input type="text" id="CarNum" /> <a>起始时间：</a> <input
				type="date" id="beginTime" value="2017-07-14" /> <a>终止时间：</a> <input
				type="date" id="endTime" value="2017-07-16" />
			<button id="sear">&nbsp;&nbsp;查询</button>
		</div>
		<br />
	    <br />
		<table id="AlarmList" cellpadding="2" cellspacing="1" border=1 align=center style="overflow:hidden;width:90%;table-layout:fixed;word-wrap:break-word;">
		<thead>
		    <tr>
				<th colspan='6'>&nbsp;&nbsp;报警处理表</th>
			</tr>
			
		</thead>
		<tbody id="blackListTable">
		    <tr>
				<td width="10%"><label>数量</label></td>
				<td align="center" width="20%"><label>报警编号</label></td>
				<td align="center" width="20%"><label>号牌号码</label></td>
				<td align="center" width="20%"><label>报警时间</label></td>
				<td align="center" width="20%"><label>状态</label></td>
				<td align="center" width="10%"><label>操作</label></td>
			</tr>
		    <tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a onclick="check(1)">查看</a></td>
			</tr>
		</tbody>
		</table>
	</div>
	<div id="tab2" style="display:none;">
	    <button onclick="back()">返回</button>
	    <br/><br/>
	  <div id="fullInfo" style="border:1px solid #000;width:70%; height:1200px;">
		<a style="float:left;">报警编号:</a> <input type="text" style="border-style:none" id="AlarmId"/>
		<div style="float:right;"><a>车流编号:</a> <input type="text" style="border-style:none" id="AlarmPastId"/></div> <br/><br/>
		<a style="float:left;">号牌号码:</a> <input type="text" style="border-style:none" id="AlarmCarNumber"/>
		<div style="float:right;"><a>报警时间:</a> <input type="text" style="border-style:none" id="AlarmTime"/></div> <br/><br/>
		<a style="float:left;">处理时间:</a> <input type="text" style="border-style:none" id="AlarmCollectionDate"/>
		<div style="float:right;"><a>状态:</a> <input type="text" style="border-style:none" id="AlarmState"/></div> <br/><br/>
		<a style="float:left;">处理人:</a> <input type="text" style="border-style:none" id="AlarmHandler"/>
		<div style="float:right;"><a>是否拦截:</a> <input type="text" style="border-style:none" id="AlarmIntercept"/></div> <br/><br/>
		<a style="float:left;">联系电话:</a> <input type="text" style="border-style:none" id="AlarmPhone"/> <br/><br/>
		<a>备注信息:</a> <br/>
		<textarea rows="7" cols="40" id="AlarmMemo"></textarea> <br/><br/>
		<a>处理反馈:</a> <br/>
		<textarea rows="7" cols="40" id="AlarmFeedBack"></textarea> <br/><br/>
		<a>处理过程:</a>  <br/>
		<textarea rows="7" cols="40" id="AlarmHandleProcess"></textarea><br/><br/>
		<a>未拦截原因:</a> <br/> 
		<textarea rows="7" cols="40" id="AlarmUninterceptedReason"></textarea>
		
	  </div>
    </div>    
  
  </body>
</html>
