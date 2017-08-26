<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>查验项目修改</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
</head>

<body>
 <div id="p" class="easyui-panel" title="查验项目修改" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
    <tr>
     <td class="bgc">业务类型</td>
     <td><select class="easyui-combobox" style="width:200px;" id="operType" disabled="disabled">

     <c:forEach items="${operType }" var="type">
	   <c:set var="isDone" value="${0 }" scope="page"></c:set>
      <c:forEach items="${item}" var="c">
      	<c:if test="${c.operType == type.id }">
      	<c:if test="${isDone != 1 }">
      	<option value="${type.id }" selected="selected">${type.typeName }</option>
      	<c:set var="isDone" value="${1 }" scope="page"></c:set>
      	</c:if></c:if>
      </c:forEach>
     </c:forEach>
     </select></td>
      <td class="bgc">车辆类型</td><td><select class="easyui-combobox" style="width:200px;" id="cllx" disabled="disabled">
      <c:forEach items="${ckCllx }" var="cc">
      <c:set var="isDone" value="${0 }" scope="page"></c:set>
      <c:forEach items="${item}" var="c">
      	<c:if  test="${cc.parentCllx == c.cllx }">   	
      	<c:if test="${isDone != 1 }">
				<option value="${cc.parentCllx}" selected="selected">${cc.parentName }</option>
				<c:set var="isDone" value="${1}" scope="page"></c:set>
			</c:if>
			</c:if>
		</c:forEach>
     	</c:forEach>
     </select></td>
     
      <td class="bgc">车辆使用性质</td><td><select class="easyui-combobox" style="width:200px;" id="isSb" disabled="disabled">
      <option value="">---请选择---</option>
      <c:forEach items="${user }" var="u">${u.parentId}
      <c:set var="isDone" value="${0 }" scope="page"></c:set>
      <c:forEach items="${item}" var="c">
      	<c:if test="${u.parentId == c.isSb }">
      		<c:if test="${isDone != 1 }">
 				<option value="${u.parentId}" selected="selected">${u.parentName }</option>
 				<c:set var="isDone" value="${1 }" scope="page"></c:set>			
 		</c:if>	</c:if>
 		</c:forEach>
     	</c:forEach>
     	
     </select></td>
     </tr>
              <c:set var="flag1" value="${0}"/>
     <tr> <td rowspan="${flag1 /2 }" class="bgc texcenter" ><span class="star">*</span>查验项目</td>  
         <td colspan="6">
         <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
             <thead><th width="10%">选择</th><th width="40%">查验项目</th><th width="10%">选择</th><th width="60%">查验项目</th></thead>
              <tbody>
             <c:forEach items="${ckPro }" var="ck"  varStatus="status">	
             		<c:set var="flag1" value="${flag1+1 }"/>
             		<c:if test="${flag1%2==1 }">
             		<tr>
             		</c:if>
	              	<c:set var="isDone" value="0" scope="page"></c:set>
              			
              			<c:forEach items="${item }" var="it">
	              			<c:if test="${it.proId == ck.id}">
	              				<td class='texcenter'><input type="checkbox" value="${ck.id }" checked="checked"/></td><td>${ck.proName }</td>
	              			<c:set var="isDone" value="1" scope="page"></c:set>	
	              			</c:if>
              			</c:forEach>
              			<c:if test="${isDone != 1}">
	              			<td class='texcenter'><input type="checkbox" value="${ck.id }"/></td><td>${ck.proName }</td>         		
	              			<c:set var="isDone" value="1" scope="page"></c:set>
              			</c:if>             				
              		</c:forEach>
              		<c:if test="${flag1%2==1 }"><td>&nbsp;</td><td>&nbsp;</td></c:if>   
             </tbody>
          </table>
         </td>
     </tr>
     </table>
     <input type="hidden" name="operVeh"/>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
 		<button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="search();">提交</button>    
 		<button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-redo'"  onclick="window.location.href='<%=request.getContextPath()%>/pc/check/ckCheckSetting'">取消</button> </div>
	 </div>
 
</body>
<script type="text/javascript">
var oldOperType;
var oldCllx;
$(function(){
	oldOperType=$("#operType").combobox("getValue");
	oldCllx=$("#cllx").combobox("getValue");
	//alert(oldCllx);
	 $("input[type=combox]").attr("readOnly",false);
})
	function search(){
		var value=[];
		var items;
		$("input[type=checkbox]:checked").each(function(){
			value.push($(this).val());
		});
		items=value.join(",");
		$("input[name=operVeh]").val(items);
		var data = {cllx:$("#cllx").combobox('getValue'),operType:$("#operType").combobox('getValue'),operVeh:$("input[name=operVeh]").val(),isSb:$("#isSb").combobox('getValue'),oldOperType:oldOperType
			,oldCllx:oldCllx};
		$.ajax({
			url:'<%=request.getContextPath()%>/pc/check/updateCheck',
			type:'post',
			data:data,
			success:function(data){
				if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				if(data=="success"){
					$.messager.alert("提示","查验项目修改成功！");
					window.location.href="<%=request.getContextPath()%>/pc/check/ckCheckSetting";
				}	
			}
		})
	}
</script>
</html>

