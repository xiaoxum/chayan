<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>业务信息新增</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
</head>

<body>
 <div id="p" class="easyui-panel" title="查验项目业务信息" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
         <td class="bgc">查验项目名称</td><td><select class="easyui-combobox" style="width:60%;" id="proName" disabled="disabled">
      	<c:forEach items="${ckPro }" var="cp" >
      		<c:choose>
      			<c:when test="${cp.id==pro.proId }">
      				<option value="${cp.id }" selected="selected">${cp.proName }</option>
      			</c:when>
      			<c:otherwise>
			   <option value="${cp.id }">${cp.proName }</option>
      			   	</c:otherwise>
      		</c:choose>
      	</c:forEach>
      </select></td>
         <td class="bgc" width="140">启用/禁用：</td><td >
         <c:if test="${pro.anncStatu =='0' }">
         <input type="radio" value="0" checked="checked" name="usering"/><label style="padding-right:20px;">启用</label>
         <input type="radio" value="1" name="usering"/><label style="padding-right:20px;">禁用</label>
         </c:if>
         <c:if test="${pro.anncStatu =='1' }">
         <input type="radio" value="0" name="usering"/><label style="padding-right:20px;">启用</label>
         <input type="radio" value="1" checked="checked" name="usering"/><label style="padding-right:20px;">禁用</label>
         </c:if></td>
     </tr>
     <tr  rowspan="2">
       <td class="bgc" width="140">查验项业务信息</td><td colspan="5"><input class="easyui-textbox" data-options="multiline:true"  style="width:95%;height:100px;" id="content" value="${pro.addcContent}"></td>
     </tr>
     </table>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
         <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-ok'"  onclick="search();">提交</button>
         <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-redo'"  onclick="window.location.href='<%=request.getContextPath()%>/system/operaSetting/operaIndex'">取消</button>
     </div>

</div>
</body>
<script type="text/javascript">
	function search(){
		var proId = $("#proName").combobox("getValue");
		var statu = $("input[type=radio]:checked").val();
		var content = $("#content").val();
		var id = "${pro.id}";
		var data = {id:id,proId:proId,anncStatu:statu,addcContent:content};
		if(!/[\u4e00-\u9fa5]/.exec(content)){
			$.messager.alert("提示","查验业务信息格式不正确");
			return;
		}
		$.ajax({
			url:'<%=request.getContextPath()%>/system/operaSetting/doUpdateOpera',
			type:'post',
			data:data,
			success:function(data){
				if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
					alert("修改成功！");
					window.location.href="<%=request.getContextPath()%>/system/operaSetting/operaIndex";
			}
		})
	}
</script>
</html>
