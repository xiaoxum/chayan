<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>高拍项新增</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
</head>

<body>
 <div id="p" class="easyui-panel" title="资料拍照修改" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
      <tr>
     <td class="bgc">业务类型</td>
     <td><select class="easyui-combobox" style="width:300px; height:30px;" id="type" disabled="disabled">
     <c:forEach items="${allORes}" var="ores" varStatus="status">
     	<c:if test="${status.index == 0 }">
     		<option value="${ores.operType }" selected="selected">${ores.operName}</option>
     	</c:if>
	 </c:forEach>
     </select></td>
     </td>
     <c:set var="flag" value="${0 }"/>
     <tr>
     <td class="bgc texcenter" rowspan="${flag }"><span class="star">*</span>拍照资料</td>
     <td colspan="3">
     <table>
     <c:forEach items="${allResP }" var="res" varStatus="status">
     	<c:if test="${status.index%4 == 0 }">
     	<tr><td>
     	<c:set var="flag" value="${flag+1 }"/>
     	</c:if> 
     	<c:set var="isDone" value="0" scope="page"></c:set>
     	<c:forEach items="${allORes}" var="ors">	
     	<c:if test="${ors.resId == res.resId  }">
     		&nbsp;<input type="checkbox" value="${res.resId }" checked="checked"/>&nbsp;<label class="labels" style="width: 220px;">${res.resName }</label>
     		<c:set var="isDone" value="1" scope="page"></c:set>
     	</c:if>
     	</c:forEach> 
     	<c:if test="${isDone != 1 }">
     		&nbsp;<input type="checkbox" value="${res.resId }"/>&nbsp;<label class="labels" style="width: 220px;">${res.resName }</label>
     	</c:if>  
     </c:forEach>
     </table>
     </td>
     </tr>

     
     
     </table>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
         <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-ok'"  onclick="upd();">提交</button>
            <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-undo'"  onclick="window.location.href='<%=request.getContextPath()%>/system/respro/highPicIndex'">取消</button>
     </div>
</div>
</body>
<script type="text/javascript">
	function upd(){
		var type = $("#type").combobox("getValue");
		var hPic ;
		var value=[];
		$("input[type=checkbox]:checked").each(function(){
			value.push($(this).val());
		})
		hPic = value.join(",");
		
		window.location.href="<%=request.getContextPath()%>/system/respro/doUpd?resStr="+hPic+"&operType="+type; 

	}
</script>
</html>