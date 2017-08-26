<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>查验项目新增</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
</head>

<body>
 <div id="p" class="easyui-panel" title="查验项目新增" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
    <tr>
     <td class="bgc">业务类型</td>
     <td><select class="easyui-combobox" style="width:200px;" id="operType"><option value="">---请选择业务类型---</option>
     <c:forEach items="${operType }" var="type">
	     <option value="${type.id }">${type.typeName }</option>
     </c:forEach>
     </select></td>
      <td class="bgc">车辆类型</td><td><select class="easyui-combobox" style="width:200px;" id="cllx"><option value="">---请选择车辆类型---</option>
      <c:forEach items="${ckCllx }" var="cc">
	     	<option value="${cc.parentCllx}">${cc.parentName }</option>
     </c:forEach>
     </select></td>
     <td class="bgc">车辆使用性质</td><td><select class="easyui-combobox" style="width:300px;" id="syxz"><option value="">---请选择使用性质---</option>
      <c:forEach items="${user }" var="ce">
      	<option value="${ce.parentId}">${ce.parentName}</option>
     </c:forEach>
     </select>
     </td>
     </tr>
     <tr>
              <c:set var="flag" value="${0}"/>
          <td rowspan="${flag /2 }" class="bgc texcenter" ><span class="star">*</span>查验项目</td>  
         <td colspan="6">
         <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
             <thead><th width="10%">选择</th><th width="40%">查验项目</th><th width="10%">选择</th><th width="40%">查验项目</th></thead>
              <tbody>
             <c:forEach items="${ckPro }" var="ck"  varStatus="status">	
             		<c:set var="flag" value="${flag+1 }"/>
             		<c:if test="${flag%2==1 }">
             		<tr>
             		</c:if>
             		<td class='texcenter'><input type="checkbox" value="${ck.id }"/></td><td>${ck.proName }</td>          			
             	</c:forEach>
           		<c:if test="${flag%2==1 }"><td>&nbsp;</td><td>&nbsp;</td></c:if>   
             </tbody>
          </table>
         </td>
     </tr>
     </table>
     <input type="hidden" name="operVeh" value=""/>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
	 <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  onclick="search();">提交</button>   
	 <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-redo'"  onclick="window.location.href='<%=request.getContextPath()%>/pc/check/ckCheckSetting'">取消</button> </div>
	</div>

</body>
</html>

<script type="text/javascript">
	
	function search(){
		var value=[];
		var items;
		$("input[type=checkbox]:checked").each(function(){
			value.push($(this).val());
		});
		items=value.join(",");
		$("input[name=operVeh]").val(items);
		var cllx = $("#cllx").combobox('getValue');
		var operType = $("#operType").combobox('getValue');
		var operVeh = $("input[name=operVeh]").val();
		var isSb = $("#syxz").combobox('getValue');
		var data = {cllx : cllx, operType : operType, operVeh : operVeh, isSb:isSb};
		if(cllx != '' && operType != '' && operVeh != '' && isSb != ''){
			$.ajax({
				url:'<%=request.getContextPath()%>/pc/check/doCkCheckSetting',
				type:'post',
				data:data,
				success:function(data){
					if(data=="no-login"){
						top.location="<%=request.getContextPath() %>/login.jsp"
		        	}
					if(data=="success"){
						$.messager.confirm("查验项目新增","查验项目添加成功！是否继续添加？",function(r){
							if(r){
								history.go(0);
							}else{
								window.location.href="<%=request.getContextPath()%>/pc/check/ckCheckSetting";
							}
						})
					}else if(data=="error"){
						$.messager.alert("查验项目新增","该查验项目已经设置,您可以选择修改设置！");
					}
				}
			})			
		}else{
			$.messager.alert("查验项目新增","请完整填写查验项目后再保存！");
		}
	}
</script>
