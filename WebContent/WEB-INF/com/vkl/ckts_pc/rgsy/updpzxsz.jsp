<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>查验拍照修改</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
</head>
<body>
 <div id="p" class="easyui-panel" title="查验拍照修改" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
     <td class="bgc">业务类型</td>
     <td><select class="easyui-combobox" style="width:300px;" id="type" disabled="disabled">
     <c:forEach items="${operType}" var="type">
     <c:if test="${pic.operType == type.id}"><option value="${type.id}" selected="selected">${type.typeName}</option></c:if>
     </c:forEach>
     </select></td>
      <td class="bgc">车辆类型</td><td><select class="easyui-combobox" style="width:300px;" id="cllx" disabled="disabled">
      <c:forEach items="${cllx}" var="cc">
   		<c:if test="${pic.cllx == cc.parentCllx}"><option value="${cc.parentCllx}" selected="selected">${cc.parentName}</option></c:if>
     </c:forEach>
     </select>
     </td>
     <td class="bgc">车辆使用性质</td><td><select class="easyui-combobox" style="width:300px;" id="syxz" disabled="disabled">
      <c:forEach items="${ceh}" var="ce">
	   		<c:if test="${pic.syxz == ce.parentId}"><option value="${ce.parentId}" selected="selected">${ce.parentName}</option></c:if>
     </c:forEach>
     </select>
     </td>
     </tr>
              <c:set var="flag1" value="${0}"/>
      <tr> <td rowspan="${falg1 /2}" class="bgc texcenter" ><span class="star">*</span>拍照项目</td>  
         <td colspan="6">
         <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
             <thead><th width="5%">选择</th><th width="35%">拍照项目</th><th width="10%">打印数量</th><th width="1%">&nbsp;</th><th width="5%">选择</th><th width="35%">拍照项目</th><th width="10%">打印数量</th></thead>
              <tbody>
             <c:forEach items="${p}" var="p" varStatus="status">	
             		<c:set var="flag1" value="${flag1+1}"/>
             		<c:if test="${flag1%2==1}">
             		<tr>
             		</c:if>
	              	<c:set var="isDone" value="0" scope="page"></c:set>            			
              			<c:forEach items="${pic.ppe}" var="pp">
	     					<c:if test="${pp.id == p.id}">
	              				<td class='texcenter'><input type="checkbox" value="${p.id}" checked="checked"/></td><td>${p.picName}</td>
	              				<td class='texcenter'>
	              					<select class="easyui-combobox" id="printNum${p.id}" data-options="editable:false" style="width: 80%; height: 30px;">
										<option value="0" ${pp.printNum==0?'selected':''}>0</option>
										<option value="1" ${pp.printNum==1?'selected':''}>1</option>
										<option value="2" ${pp.printNum==2?'selected':''}>2</option>
										<option value="3" ${pp.printNum==3?'selected':''}>3</option>
										<option value="4" ${pp.printNum==4?'selected':''}>4</option>
										<option value="5" ${pp.printNum==5?'selected':''}>5</option>
										<option value="6" ${pp.printNum==6?'selected':''}>6</option>
									</select>
	              				</td>
	              				<td></td>
	              			<c:set var="isDone" value="1" scope="page"></c:set>	
	              			</c:if>
              			</c:forEach>
              			<c:if test="${isDone != 1}">
	              			<td class='texcenter'><input type="checkbox" value="${p.id}"/></td><td>${p.picName}</td>
	              			<td class='texcenter'>
              					<select class="easyui-combobox" id="printNum${p.id}" data-options="editable:false" style="width: 80%; height: 30px;">
									<option value="0" ${pp.printNum==0?'selected':''}>0</option>
									<option value="1" ${pp.printNum==1?'selected':''}>1</option>
									<option value="2" ${pp.printNum==2?'selected':''}>2</option>
									<option value="3" ${pp.printNum==3?'selected':''}>3</option>
									<option value="4" ${pp.printNum==4?'selected':''}>4</option>
									<option value="5" ${pp.printNum==5?'selected':''}>5</option>
									<option value="6" ${pp.printNum==6?'selected':''}>6</option>
								</select>
              				</td>
	              			<td></td>      		
	              			<c:set var="isDone" value="1" scope="page"></c:set>
              			</c:if>             				
              		</c:forEach>
              		<c:if test="${flag1%2==1 }"><td>&nbsp;</td><td>&nbsp;</td><td></td></c:if>   
             </tbody>
          </table>
         </td>
     </tr>
   	<%-- <c:set var="flag" value="${0 }"/>
   	<c:forEach items="${p }" var="p" varStatus="status">
   	 <c:set var="flag" value="${flag+1 }"/>
	   	 <c:if test="${flag%4==1 }">
	     <tr><td colspan="6"></c:if >
	     
	        <c:set var="isDone" value="0" scope="page"></c:set>	     	
	     	<c:forEach items="${pic.ppe }" var="pp">
	     	<c:if test="${pp.id == p.id }">
	     		<input type="checkbox" value="${p.id }" checked="checked"/><label class="labels">${p.picName}</label>
	     		<c:set var="isDone" value="1" scope="page"></c:set>	
	     	</c:if>
			</c:forEach>
             <c:if test="${isDone != 1}">
	     		<input type="checkbox" value="${p.id}"/><label class="labels">${p.picName}</label>
				<c:set var="isDone" value="1" scope="page"></c:set>	
			</c:if>
  	</c:forEach>
  	<c:if test="${flag%2==1 }"><td>&nbsp;</td><td>&nbsp;</td></c:if>   --%> 
     </table>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
         <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="update();">提交</button>
         <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-redo'"  onclick="window.location='<%=request.getContextPath()%>/system/photo/photoIndex'">取消</button>
     </div>
     
</div>
</body>
<script type="text/javascript">
	function update(){
		
		var operType = $("#type").combobox("getValue");
		var cllx = $("#cllx").combobox("getValue");
		var syxz = $("#syxz").combobox("getValue"); 
		var value = [];
		var pic;
		$("input[type=checkbox]:checked").each(function(){
			var picId = $(this).val();
			var printNum = $("#printNum" + picId).combobox("getValue");
			value.push(picId + "_" + printNum);
		});
		
		pic=value.join(",");
		window.location.href="<%=request.getContextPath()%>/system/photo/doUpdateSet?cllx="+cllx+"&pic="+pic+"&syxz="+syxz+"&operType="+operType;
		//window.location.href="<%=request.getContextPath()%>/system/photo/doUpdateSet1?cllx="+cllx+"&pic="+pic;
	}
</script>
</html>
