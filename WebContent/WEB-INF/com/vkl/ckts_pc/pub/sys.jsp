<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.lang.*" %>
<%@ page import="com.vkl.ckts.wss.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>系统参数设置</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDrag.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDialog.js"></script>
</head>
<body>

	<c:forEach items="${set}" var="cSet">
	
<%-- 	<c:if test="${cSet.key == 'seniorAuditerAloit' }">
     <p style="margin-top: 20px;">
     <label >审核员随机分配：&nbsp;</label>
     <c:choose>
     <c:when test="${cSet.value == '0' }">
     <input type="radio" name="seniorAuditerAloit" value="0" checked="checked" disabled="disabled">是&nbsp;&nbsp;
     <input type="radio" name="seniorAuditerAloit" value="1" disabled="disabled">否 
     </c:when>
     <c:otherwise>   	
     <input type="radio" name="seniorAuditerAloit" value="0" disabled="disabled">是&nbsp;&nbsp;
     <input type="radio" name="seniorAuditerAloit" value="1" checked="checked"  disabled="disabled"> 否 
     </c:otherwise>
     </c:choose>
     </p>
     </c:if> --%>
<%--      <c:if test="${cSet.key == 'videoInfoReadWay' }">
     <p style="margin-top: 20px;">
     <label >视频存储位置：&nbsp;</label>
     <c:choose>
     <c:when test="${cSet.value == '0' }">
     <input type="radio" name="videoInfoReadWay" value="0" checked="checked" disabled="disabled">本地&nbsp;&nbsp;
     <input type="radio" name="videoInfoReadWay" value="1" disabled="disabled">服务器
     </c:when>
     <c:otherwise>  	
     <input type="radio" name="videoInfoReadWay" value="0" disabled="disabled" disabled="disabled">本地&nbsp;&nbsp;
     <input type="radio" name="videoInfoReadWay" value="1" checked="checked" disabled="disabled">服务器
     </c:otherwise>
     </c:choose>
     </p>
     </c:if> --%>
 <%-- 	 <c:if test="${cSet.key == 'fileInfoReadWay' }" >    
     <p style="margin-top: 20px;">
     <label >公告信息读取方式 ：&nbsp;</label>
     <c:choose>
     
     <c:when test="${cSet.value == '0' }">
     	<input type="radio" name="fileInfoReadWay" value="0" checked="checked" >本地读取&nbsp;&nbsp;
     	 <input type="radio" name="fileInfoReadWay" value="1" >接口访问
     </c:when>
     <c:otherwise>
     	<input type="radio" name="fileInfoReadWay" value="0" >本地读取&nbsp;&nbsp;
	     <input type="radio" name="fileInfoReadWay" value="1" checked="checked" >接口访问
     </c:otherwise>
     </c:choose>
     </p>
     </c:if> --%>
      	 <c:if test="${cSet.key == 'fjcyx' }">    
     <p style="margin-top: 20px;">
     <label >复检查验项设置：&nbsp;</label>
     <c:choose>
     
     <c:when test="${cSet.value == '0' }">
     	<input type="radio" name="fjcyx" value="0" checked="checked">全项查验 &nbsp;&nbsp;
     	 <input type="radio" name="fjcyx" value="1">不合格项设置
     </c:when>
     <c:otherwise>
     	<input type="radio" name="fjcyx" value="0">全项查验 &nbsp;&nbsp;
	     <input type="radio" name="fjcyx" value="1" checked="checked">不合格项设置
     </c:otherwise>
     </c:choose>
     </p>
     </c:if>
     
     <c:if test="${cSet.key == 'GbsRefreshFre' }">
     <p style="margin-top: 20px;">
     <label  >GPS刷新频率</label>
     	<input class="easyui-textbox" style="width:20%; height:30px;" id="GbsRefreshFre" value="${cSet.value }" disabled="disabled" />毫秒
     </p>
     </c:if>
     <c:if test="${cSet.key == 'CkRefreshFre' }">
     <p style="margin-top: 20px;"> 
     <label >查验车辆信息刷新频率</label>
     <input class="easyui-textbox" style="width:20%; height:30px;" id="CkRefreshFre" value="${cSet.value }" disabled="disabled"/>毫秒
     </p>
     </c:if>
     
		<c:if test="${cSet.key == 'sfrgsh' }">
			<p style="margin-top: 20px;">
				<label>是否自动审核</label>
				<span style="margin-left:20px;">
				<c:choose>
					<c:when test="${cSet.value == '1' }">
						<input type="radio" name="sfrgsh" value="1" checked="checked">是 &nbsp;&nbsp;
	     <input type="radio" name="sfrgsh" value="2" style="margin-left:30px;">否
     </c:when>
					<c:otherwise>
						<input type="radio" name="sfrgsh" value="1">是&nbsp;&nbsp;
	     <input type="radio" name="sfrgsh" value="2" checked="checked" style="margin-left:30px;">否
     </c:otherwise>
				</c:choose>
		     </span>
			</p>
		</c:if>
     
     </c:forEach>
     <div style="  text-align:right;margin:20px;"  >
     <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-ok'"  onclick="up()">提交</button>
     <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-redo'" onclick="back()">取消</button>                         
     </div>
</body>
<script type="text/javascript">

  function back(){
	  
	  parentDialog.close();
	  
  }
   
  function up(){
   var seniorAuditerAloit = $("[name='seniorAuditerAloit']:checked").val();
  /*  var videoInfoReadWay = $("[name='videoInfoReadWay']:checked").val();
   var fileInfoReadWay = $("[name='fileInfoReadWay']:checked").val(); */
   var fjcyx = $("[name='fjcyx']:checked").val();
   var GbsRefreshFre = $("#GbsRefreshFre").textbox("getValue");
   var CkRefreshFre = $("#CkRefreshFre").textbox("getValue");
   var sfrgsh = $("[name='sfrgsh']:checked").val();
   	var zz = /^[0-9]*$/;
		  if(!zz.exec(GbsRefreshFre) || !zz.exec(CkRefreshFre)){
			  $.messager.alert("提示","输入不能为空且必须为纯数字！");
			  return;
		  }
	 var key ="seniorAuditerAloit,GbsRefreshFre,CkRefreshFre,fjcyx,sfrgsh";
	 var val = seniorAuditerAloit+","+GbsRefreshFre+","+CkRefreshFre+","+fjcyx+","+sfrgsh;
	  $.ajax({
		    cache: true,
		    type: "POST",
			url: "<%=request.getContextPath()%>/module/base/sysup",
			data:{
				 "key" : key, 
				 "value" : val
			      }, 
		    async: false,
		    dataType:"text",
			error: function(request) {
				 $.messager.alert("提示！","系统繁忙请稍后再试！");
			        },
			success: function (data) {
				if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				 alert("系统参数设置修改成功！");
				 parentDialog.close();
			}
	  });
				
  }
  
  
  
</script>
</html>