<%@ page language="java" contentType="text/html; charset=utf-8" import="com.vkl.ckts.common.entity.UserEntity,com.vkl.ckts.common.constr.Constrant"  pageEncoding="utf-8"%>
<%
	UserEntity user = (UserEntity)session.getAttribute(Constrant.S_USER_SESSION);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>查验项目设置</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui-lang-zh_CN.js"></script>
</head>
<body>
   <div id="p" class="easyui-panel" title="查验项目查询" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
     <td class="bgc">业务类型</td>
     <td><select class="easyui-combobox" style="width:300px;" id="type"><option value="">---请选择---</option>
     <c:forEach items="${operType }" var="type">
     <c:if test="${tp == type.id }"><option value="${type.id }" selected="selected">${type.typeName }</option></c:if>
     <option value="${type.id }">${type.typeName }</option>
     </c:forEach>
     </select></td>
      <td class="bgc">车辆类型</td><td><select class="easyui-combobox" style="width:300px;" id="cllx"><option value="">---请选择---</option>
      <c:forEach items="${ckCllx }" var="cc">
   		<c:if test="${cl == cc.parentCllx }"><option value="${cc.parentCllx }" selected="selected">${cc.parentName }</option></c:if>
     	<option value="${cc.parentCllx }">${cc.parentName }</option>
     </c:forEach>
     </select></td>
     </tr>
     </table>
       <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
               <button  type="button" class="easyui-linkbutton"   data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-reload'"  onclick="reset();">重置</button>
       </div>
      </div>
      
    <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='<%=request.getContextPath()%>/pc/check/toCkCheckSetting'">新增</button>
     <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-edit'"  onclick="upd();">修改</button>
     <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="del();">删除</button>
     </div>
  
  <div id="p" class="easyui-panel" title="查验项目设置一览表" style="width:100%;"> 
  <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <thead height="35"><th>选择</th><th>业务类型</th><th>车辆类型</th><th>车辆使用性质</th><th>查验项数目</th></thead>
     
     <tbody id="data">
     
     
     </tbody>
  </table>
</div>
<div style=" width:100%; background:#eff6fe;"  class="easyui-panel">
      <div id="pp"   class="easyui-pagination" data-options="
					 total:0, 
					 pageSize:20,
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'Page',
                    onSelectPage:function(pageNumber){
                      page(pageNumber);  
                    }"></div>
    </div>

</body>
<script type="text/javascript">
	$(function(){
		var flag = '${flag11}';
		if(flag != null && flag != ''){
			if(flag == 'del') $.messager.alert("提示","删除成功！");			
			<%request.getSession().setAttribute("flag11", null);%>
		}
		page(1);
	})
	
	//分页
	function page(num){
		var tatol ;
		var operType =$("#type").combobox("getValue");
		var cllx = $("#cllx").combobox("getValue");
		var chkpt = "<%=user.getUserDept()%>";
		var pageSize = 20;
		$.ajax({
			url : '<%=request.getContextPath()%>/pc/check/ckCheckPage',
			post : 'post',
			data : {pageNo : num, pageSize : pageSize, operType : operType, cllx : cllx,time:new Date() },
			success : function(data){
				if(data == "no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				$("#data").empty();
				tatol = data[0];
				data = data[1];
				
				$.each(data,function(index,dd){
					var info;
					if(index % 2 == 1){
						info = "<tr class='rowbgcolor'>"
							+"<td class='texcenter'><input type='radio' name='sel' value='"+dd.operType+","+dd.cllx +","+dd.isSb+"'></td>"
					     	+"<td>"+dd.typeName+"</td>"
					     	+"<td>"+dd.parentName+"</td>"
					     	+"<td>"+dd.syxz+"</td>"
					     	+"<td>"+dd.num+"</td>"
					     	+"</tr>"
					}else{
						info = "<tr>"
							+"<td class='texcenter'><input type='radio' name='sel' value='"+dd.operType+","+dd.cllx +","+dd.isSb+"'></td>"
					     	+"<td>"+dd.typeName+"</td>"
					     	+"<td>"+dd.parentName+"</td>"
					     	+"<td>"+dd.syxz+"</td>"
					     	+"<td>"+dd.num+"</td>"
					     	+"</tr>"
					}
			     	$("#data").append(info);
				})
			
			
				$("#pp").pagination({
					  total:tatol,
					  });
			
			}
			
		})	
		
		
	}
	
	// 条件查询
	function search(){
		page(1);
	}
	 
	// 重置搜索条件
	function reset(){
		var operType =$("#type").combobox("setValue","");
		var cllx = $("#cllx").combobox("setValue","");
	}
	// 删除
	function del(){
		var str = $("input[name='sel']:checked").val();
		if(str != null && str !=''){
			var dd= str.split(",");	
			 parent.$.messager.confirm('询问', '您确定要删除选择项？',
					    function(b) {
				 if(b){
					 window.location.href="<%=request.getContextPath()%>/pc/check/deleteCheck?operType="+dd[0]+"&cllx="+dd[1]+"&isSb="+dd[2]; 
				 }
			 })
			
		}else{
			$.messaager.alert("提示","请选择删除项！");
		}
	}
	
	// 修改
	function upd(){
		var str = $("input[name='sel']:checked").val();
		if(str != null && str !=''){
			var dd= str.split(",");
			window.location.href="<%=request.getContextPath()%>/pc/check/toUpdateCheck?operType="+dd[0]+"&cllx="+dd[1]+"&isSb="+dd[2];			
		}else{
			$.messager.alert("提示","请选择修改项！");
		}
	}
</script>
</html>

