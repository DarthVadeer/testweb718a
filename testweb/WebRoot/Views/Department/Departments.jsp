<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="model.Department"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- Bootstrap -->
<link href="../../Resources/css/bootstrap_3.0.3/css/bootstrap.min1.css" rel="stylesheet">
<link rel="stylesheet"
	href="../../Resources、css/bootstrap_3.0.3/css/font-awesome.min1.css">
<link rel="stylesheet"
	href="../../Resources/css/style/common.css">
<script src="Resources/css/bootstrap_3.0.3/js/jquery-1.8.2.min.js"></script>
<script src="Resources/css/bootstrap_3.0.3/js/bootstrap.min1.js"></script>
<script src="Resources/lib/jquery-1.11.1.min.js"></script>
<base href="<%=basePath%>">

<title>Departments</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->



<link rel="stylesheet" type="text/css"
	href="../Resources/css/tree_themes/SimpleTree.css" />
<script type="text/javascript" src="Resources/lib/jquery-1.6.min.js"></script>
<script type="text/javascript" src="Resources/js/SimpleTree.js"></script>
<script type="text/javascript">

var derInfos = new Array();
	$(function() {
		$(".st_tree").SimpleTree({

		});
	});

	//function ShowSubDepinfo() {
	//}
	
	function ShowSubInfo(DepId) {
	
	ShowDepInfo(DepId);
				$.ajax({
			url : "DepartSubQueryAction",
			type : "get",
			 dataType: "json",
			data:
{
"DepId":DepId
},
contentType: 'application/x-www-form-urlencoded; charset=UTF-8', 
			success : function(data) {
			
			var dep="<ul show=\"true\">";
                //i表示在data中的索引位置，n表示包含的信息的对象
                $.each(data,function(i,n){
              
               derInfos.push(n);
      
                    //获取对象中属性为optionsValue的值
                    dep+="<li id = \""+n.departID+"\"><a onclick=\"ShowSubInfo("+n.departID+")\">"+n.departName+"</a></li>";
                });
                dep+="</ul >";
                
                
               $("#"+DepId).find("ul").remove();
               
               $("#"+DepId).find("a").attr("onclick","ShowDepInfo("+DepId+")");
          
               $("#"+DepId).after(dep);
                  
                 
	
			}
		});

	};
	
	function ShowDepInfo(DepId){
	$("#depForm").fadeIn();
	for( var i = 0; i<derInfos.length;i++)
	{
	if(derInfos[i].departID==DepId )
	{
 $("#DepId").val(derInfos[i].departID);
  $("#SubDep").val(derInfos[i].departLevel);
   $("#DepName").val(derInfos[i].departName);
   
  if(derInfos[i]. departType==1){
   $("#DepType2").removeAttr("checked");
   $("#DepType1").prop("checked",true);
  }
  else if(derInfos[i]. departType==0){
  $("#DepType1").removeAttr("checked");
    $("#DepType2").prop("checked",true);
  }
    $("#DepLevel").val(derInfos[i].departLevel);
    if(parseInt($("#DepLevel").val())>3)
    $("#depAdd").fadeOut();
    else
    $("#depAdd").fadeIn();
    $("#DepLevelName").val(showLevelName( parseInt($("#DepLevel").val())));
    
    var strs = derInfos[i].departParent.split(",");
     $("#ParentDep").val(strs[0]);
     $("#ParentDepName").val(strs[1]);
      $("#DepPrincipal").val(derInfos[i].departPrincipal);
       $("#DepPhone").val(derInfos[i].departPhone);
        $("#DepDesc").val(derInfos[i].departRemarks);
        $("#DepAbb").val(derInfos[i].departSName);
        $("#DepOfficer").val(derInfos[i].departOfficer);
         $("#Depcode").val(derInfos[i].departCode);
        }
        }
         $("#depSummit").text("修改");
         $("#depSummit").attr("onclick","modifyDep()");
	};
	
	function ToCancel(){

	ShowDepInfo($("#DepId").val());
	

	};
	
	function showLevelName(level){
	switch(level){
	case 1: return "总队";
	case 2: return "支队";
	case 3: return "大队";
	case 4: return "中队";
	}
	}
	
	function toAddSub(){
	
	 $("#ParentDep").val($("#DepId").val());
	 $("#ParentDepName").val($("#DepName").val());
  $("#SubDep").val("");
   $("#DepName").val("");
    $("#DepLevel").val((parseInt($("#DepLevel").val())+1).toString());
     $("#DepLevelName").val(showLevelName( parseInt($("#DepLevel").val())));
      $("#DepPrincipal").val("");
       $("#DepPhone").val("");
        $("#DepDesc").val("");
        $("#DepAbb").val("");
        $("#DepOfficer").val("");
         $("#Depcode").val("");
         
         $("#depAdd").fadeOut();
          $("#depSummit").attr("onclick","depAddTo()");
         $("#depSummit").text("提交");
	 
	 
	}
	
	function depAddTo(){
	 $("#depSummit").attr('disabled',"true");
	$.ajax({
			url : "DepartAddAction",
			type : "get",
			datatype:"json",
			data:encodeURI($('#depForm').serialize()),
			success : function(data) {
			    
			 var obj = JSON.parse(data);
			  
			var  dep,flag=false;
			var DepId =$("#DepId").val();
			if($("#"+DepId).next().prop("tagName")!='ul'){
			 dep="<ul show=\"true\">";
			 flag = true;
			 }
			 else
			 dep="";   
             $("#DepId").val(obj[0].departID);
               derInfos.push(obj[0]);
      
                    //获取对象中属性为optionsValue的值
                    dep+="<li id = \""+obj[0].departID+"\"><a onclick=\"ShowSubInfo("+obj[0].departID+")\">"+obj[0].departName+"</a></li>";
               
                if(flag==true)
                dep+="</ul >";
                
               if(flag==true)
               $("#"+DepId).after(dep);
                  else
                  $("#"+DepId).next("ul").append(dep);
	$("#depSummit").removeAttr("disabled");
	 $("#depSummit").attr("onclick","modifyDep()");
	
		}
     
		});
	};
	
	function depDelete(){
	
	$.ajax({
			url : "DepartDeleteAction",
			type : "get",
			datatype:"json",
			data:encodeURI($('#depForm').serialize()),
			success : function(data) {
			    
			 var obj = JSON.parse(data);
			  
			var  dep,flag=false;
			var DepId =$("#DepId").val();
			if($("#"+DepId).next().prop("tagName")!='ul'){
			 dep="<ul show=\"true\">";
			 flag = true;
			 }
			 else
			 dep="";   
             $("#DepId").val(obj[0].departID);
               derInfos.push(obj[0]);
      
                    //获取对象中属性为optionsValue的值
                    dep+="<li id = \""+obj[0].departID+"\"><a onclick=\"ShowSubInfo("+obj[0].departID+")\">"+obj[0].departName+"</a></li>";
               
                if(flag==true)
                dep+="</ul >";
                alert(dep);
               if(flag==true)
               $("#"+DepId).after(dep);
                  else
                  $("#"+DepId).next("ul").append(dep);
	
	
		}
     
		});
	};
	
function	 deleteDep(){
$.ajax({
			url : "DepartDeleteAction",
			type : "get",

			data:{
			"DepId":$("#DepId").val()
			
},
success : function(data) {

if(data==1){
alert("删除成功");
deleteFromTree($("#DepId").val());
}
else
alert("删除失败");


}
});
};

function deleteFromTree(DepId){
$("#"+DepId).remove();
};
	
function modifyDep(){
$.ajax({
			url : "DepartUpdateAction",
			type : "get",
			data:encodeURI($('#depForm').serialize()),
			success : function(data) {
	
		
			if(data==1)
									
				for( var i = 0; i<derInfos.length;i++)
	{
	
	if(derInfos[i].departID==$("#DepId").val() )
	{
	 
  derInfos[i].departLevel = $("#DepLevel").val();
  derInfos[i].departName= $("#DepName").val();
   derInfos[i].departType=$("input[name='DepType']:checked").val();
   derInfos[i].departLevel= $("#DepLevel").val();
     derInfos[i].departParent=$("#ParentDep").val()+","+$("#ParentDepName").val();
      derInfos[i].departPrincipal=$("#DepPrincipal").val();
     derInfos[i].departPhone=  $("#DepPhone").val();
       derInfos[i].departRemarks= $("#DepDesc").val();
       derInfos[i].departSName= $("#DepAbb").val();
        derInfos[i].departOfficer=$("#DepOfficer").val();
        derInfos[i].departCode= $("#Depcode").val();
     
       $("#"+ $("#DepId").val()).find("a").text(derInfos[i].departName);
	
	}//end if
	
	
	}//end for
		
			
		}
     
		});

}



</script>






</head>


<body class="row tableHeader" >
	<div class="well" style="background-color:#333"><h2>部门管理</h2> </div>
	<br>




	<form  id = "depForm"class="vote" action="" method="post">

		<div style="float:right" >


			<table  class="check-div1" frame="box" width="480px" height="580px">

				<tr>
					<td align=center style="display:none;"><br> <label
						for="DepId" class=" ">部门ID：</label> <input name="DepId"
						type="text" class="form-control" id="DepId" value=""></td>

				</tr>

				<tr>
					<td align=center style="display:none;"><br> <label
						for="SubDep" class=" ">子部门：</label> <input name="SubDep"
						type="text" class="form-control" id="SubDep" value=""></td>

				</tr>
				<tr>
					<td align=center><br> <label for="DepName" class=" ">部门名称：</label>
						<input name="DepName" type="text" class="form-control"
						id="DepName" value=""></td>

				</tr>

				<tr>
					<td align=center style="display:none;"><label for="DepLevel"  class=" ">数字级别：</label> <input
						name="DepLevel" class="form-control" id="DepLevel"
						value=""></td>
				</tr>
				
					<tr>
					<td align=center><label for="DepLevelName" class=" ">级别：</label> <input
						name="DepLevelName" class="form-control" id="DepLevelName" disabled="disabled"
						value=""></td>
				</tr>
				
				<tr>
					<td align=center><label for="DepType" class=" ">类别：</label>
					<label><input id = "DepType1"name="DepType" type="radio" value="1"  />业务部门</label> 
<label><input id = "DepType0"name="DepType" type="radio" value="0" />行政单位 </label> 
					</td>
				</tr>


				<tr>
					<td align=center  style="display:none;"><label for="ParentDep"  class=" ">所属部门：</label>
						<input name="ParentDep" class="form-control" id="ParentDep"
						value=""></td>
				</tr>
				
					<tr>
					<td align=center><label for="ParentDep" class=" ">所属部门：</label>
						<input name="ParentDepName" class="form-control" id="ParentDepName"
						value="" disabled="disabled"></td> 
				</tr>


				<tr>
					<td align=center><label for="DepPrincipal" class=" ">负
							责 人 &nbsp;：</label> <input name="DepPrincipal" class="form-control"
						id="DepPrincipal" value=""></td>
				</tr>

				<tr>
					<td align=center><label for="DepPhone" class=" ">电话：</label> <input
						name="DepPhone" class="form-control" id="DepPhone"
						value=""></td>

				</tr>

				<tr>
					<td align=center><label for="Depcode" class=" ">部门代码：</label>
						<input name="Depcode" class="form-control" id="Depcode"
						value=""></td>

				</tr>

				<tr>
					<td align=center><label for="DepAbb" class=" ">部门简称：</label> <input
						name="DepAbb" class="form-control" id="DepAbb" value=""></td>

				</tr>

				<tr>
					<td align=center><label for="DepOfficer" class=" ">部门负责人：</label>
						<input name="DepOfficer" class="form-control" id="DepOfficer"
						value=""></td>

				</tr>



				<tr>
					<td align=left>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class=" ">
							部门描述：</label>
				</tr>

				<tr>
					<td align=center height="98"><textarea rows="5" cols="27"
						name = "DepDesc"	id="DepDesc"></textarea></td>

				</tr>


				<tr>

					<td align=right>
						<button type="button" id="depCancel" onclick="ToCancel()" class="btn btn-default">&nbsp;重置&nbsp;</button>
						<button type="button" id ="delete" onclick="deleteDep()" class="btn btn-default">&nbsp;删除&nbsp;</button>
						<button type="button" id = "depSummit" onclick = "modifyDep()"class="btn btn-default">&nbsp;修改&nbsp;</button>
						<button class="btn btn-info"type="button" id = "depAdd" onclick="toAddSub()">&nbsp;添加子属&nbsp;</button>
					</td>

				</tr>

			</table>



		</div>


	</form>



		<table class="check-div1" frame="box" width="480px" height="580px">


			<tr>
				<td>

					<div class="st_tree">
						<ul>


<%List<Department> Deplist =(List<Department>)request.getAttribute("Deplist"); 
for(int i = 0; i<Deplist.size();i++) {%>
		
							
				<li id = "<%=Deplist.get(i).getDepartID()%>"><a onclick="ShowSubInfo('<%=Deplist.get(i).getDepartID()%>')"><%=Deplist.get(i).getDepartName() %></a></li>
				<script type="text/javascript">
				
				var depInfo = new Object();
				depInfo.departID='<%=Deplist.get(i).getDepartID()%>';
				depInfo.departCode='<%=Deplist.get(i).getDepartCode()%>';
				depInfo.departLevel='<%=Deplist.get(i).getDepartLevel()%>';
				depInfo.departName='<%=Deplist.get(i).getDepartName()%>';
				depInfo.departOfficer='<%=Deplist.get(i).getDepartOfficer()%>';
				depInfo.departParent='<%=Deplist.get(i).getDepartParent()%>';
				depInfo.departPhone='<%=Deplist.get(i).getDepartPhone()%>';
				depInfo.departPrincipal='<%=Deplist.get(i).getDepartPrincipal()%>';
				depInfo.departRemarks='<%=Deplist.get(i).getDepartRemarks()%>';
				depInfo.departSName='<%=Deplist.get(i).getDepartSName()%>';
				depInfo.departType='<%=Deplist.get(i).getDepartType()%>';
				derInfos.push(depInfo);
		
				</script>
				<%}%>

						</ul>


					</div>
				</td>
			</tr>


		</table>



<script type="text/javascript">
 $("#depForm").hide();
	</script>
	
	
	

</body>
</html>
