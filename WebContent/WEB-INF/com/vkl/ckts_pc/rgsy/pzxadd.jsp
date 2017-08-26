<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>查验拍照新增</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
</head>

<body>
 <div id="p" class="easyui-panel" title="查验拍照新增" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
     <td class="bgc" width="120">业务类型</td>
     <td><select class="easyui-combobox" style="width:85%;" id="type"><option value="">---请选择业务类型---</option>
     <c:forEach items="${operType }" var="type">
     <c:if test="${tp == type.id }"><option value="${type.id }" selected="selected">${type.typeName }</option></c:if>
     <option value="${type.id }">${type.typeName }</option>
     </c:forEach>
     </select></td>
      <td class="bgc" width="120">车辆类型</td><td><select class="easyui-combobox" style="width:85%;" id="cllx"><option value="">---请选择车辆类型---</option>
      <c:forEach items="${cllx }" var="cc">
   		<c:if test="${cl == cc.parentCllx }"><option value="${cc.parentCllx }" selected="selected">${cc.parentName }</option></c:if>
     	<option value="${cc.parentCllx }">${cc.parentName }</option>
     </c:forEach>
     </select>
     </td>
     <td class="bgc" width="120">车辆使用性质</td><td><select class="easyui-combobox" style="width:85%;" id="syxz"><option value="">---请选择使用性质---</option>
      <c:forEach items="${ceh }" var="ce">
     	<option value="${ce.parentId}">${ce.parentName}</option>
     </c:forEach>
     </select>
     </td>
     </tr>
              <c:set var="flag" value="${0}"/>
     <tr> <td class='texcenter' rowspan="${flag /2}" class="bgc" ><span class="star">*</span>拍照项目</td>  
         <td colspan="6">
         <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
             <thead><th width="5%">选择</th><th width="35%">拍照项目</th><th width="10%">打印数量</th><th width="1%">&nbsp;</th><th width="5%">选择</th><th width="35%">拍照项目</th><th width="10%">打印数量</th></thead>
              <tbody>
   				<c:forEach items="${pic }" var="pic" varStatus="status">
             		<c:set var="flag" value="${flag+1 }"/>
             		<c:if test="${flag%2==1 }">
             		<tr>
             		</c:if>
             		<td class='texcenter'><input id="picId" type="checkbox" value="${pic.id}"/></td>
             		<td>${pic.picName}</td>
             		<td class='texcenter'>
             			<select class="easyui-combobox" id="printNum${pic.id}" data-options="editable:false" style="width: 80%; height: 30px;">
							<option value="0">0</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
						</select>
             		</td>
             		<td></td>
             	</c:forEach>
             		<c:if test="${flag%2==1 }"><td>&nbsp;</td><td>&nbsp;</td></c:if>         			
           
             </tbody>
          </table>
         </td>
     </tr>
<%--    	<c:set var="flag" value="${0 }"/>
   	<c:forEach items="${pic }" var="pic" varStatus="status">
   	 <c:set var="flag" value="${flag+1 }"/>

	   	 <c:if test="${flag%2==1 }">
	     <tr></c:if >
	     	<td><input type="checkbox" value="${pic.id }"/></td><td colspan="2"><label class="labels">${pic.picName}</label></td>

  	</c:forEach> --%>
     </table>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
         <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="insert();">提交</button>
         <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-redo'"  onclick="window.location='<%=request.getContextPath()%>/system/photo/photoIndex'">取消</button>
     </div>

</div>
</body>
<script type="text/javascript">
	function insert(){
		var operType = $("#type").combobox("getValue");
		var cllx = $("#cllx").combobox("getValue");
		var syxz = $("#syxz").combobox("getValue"); 
		var value = [];
		var pic = "";
		$("input[type=checkbox]:checked").each(function(){
			var picId = $(this).val();
			var printNum = $("#printNum" + picId).combobox("getValue");
			value.push($(this).val() + "_" + printNum);
		});
		pic=value.join(",");
		if(cllx != ''  && pic != ''){
			$.ajax({
				url:'<%=request.getContextPath()%>/system/photo/insertPhotoSet?cllx='+cllx+'&pic='+pic+'&syxz='+syxz+'&operType='+operType,
				type:'post',
				data:null,
				success:function(data){
					if(data=="no-login"){
						top.location="<%=request.getContextPath() %>/login.jsp"
		        	}
					if(data=="success"){
						$.messager.confirm("查验项目新增","查验项目添加成功！是否继续添加？",function(r){
							if(r){
								window.location.href="<%=request.getContextPath()%>/system/photo/addPhotoSet";
							}else{
								window.location.href='<%=request.getContextPath()%>/system/photo/photoIndex';
								//this.history.go(0);
							}
						})
					}else if(data=="insertF"){
						$.messager.alert("查验项目新增","该查验项目已经设置,您可以选择修改设置！");
					}
				}
			})
			
			
			
			//window.location.href="<%=request.getContextPath()%>/system/photo/insertPhotoSet?cllx="+cllx+"&pic="+pic+"&syxz="+syxz+"&operType="+operType;
			//window.location.href="<%=request.getContextPath()%>/system/photo/insertPhotoSet1?cllx="+cllx+"&pic="+pic;
		}else{
			$.messager.alert("提示","请填写完整数据！");
		}
	}
	
	<%-- window.location.href="<%=request.getContextPath()%>/system/photo/insertPhotoSet?operType="+operType+"&cllx="+cllx+"&pic="+pic+"&syxz="+syxz; --%>
</script>
</html>
