<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>资料拍照设置</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
</head>

<body>
   <div id="p" class="easyui-panel" title="查询" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
     <td class="bgc">业务类型</td>
     <td><select class="easyui-combobox" style="width:300px; height:30px;" id="type">
     <option value="">---请选择---</option>
     <c:forEach items="${allOper}" var="type">
     	<option value="${type.id }">${type.typeName}</option>
	 </c:forEach>
     </select></td>
     </td>
     </tr>
     </table>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
              <button  type="button" class="easyui-linkbutton"   data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-reload'"  onclick="reset();">重置</button>
      </div>
      
    <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='<%=request.getContextPath()%>/system/respro/toAddHP'">新增</button>
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'"  onclick="upd();">修改</button>
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="del();">删除</button>
     </div>
  
  <div id="p" class="easyui-panel" title="资料拍照设置一览表" style="width:100%;"> 
  <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0" id="tab">
     <thead><th width="5%">选择</th><th width="25%">业务类型</th><th width="70%">资料名称</th></thead>
     
     <tbody id="ddInfo">
    
     
     </tbody>
  </table>
</div>
</div> 
<div style=" width:100%; background:#eff6fe;"  class="easyui-panel">
      <div id="pp"   class="easyui-pagination" data-options="
					total:0, 
					pageSize:15,
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'Page',
                    onSelectPage:function(pageNumber){page(pageNumber)}"></div>
    </div>

</body>
<script type="text/javascript">
	$(function(){
		var flag='${Flag11}';
		if(flag=='del'){
			$.messager.alert("提示","删除成功！");
		}
		if(flag=='upd'){
			$.messager.alert("提示","修改成功！");
		}
			<%request.getSession().setAttribute("Flag11", null);%>
		page(1);
	})
	// 搜索
	function search(){
		page(1);
	}
	
	// 分页
	function page(num){
		var type = $("#type").combobox("getValue");
		var dd = {operType : type, pageNo : num, pageSize : 15};
		$.ajax({
			url : '<%=request.getContextPath()%>/system/respro/highPicPage',
			type : 'post',
			data : dd,
			success : function(data){
				if(result=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				var total = data[0];
				data = data[1];
				$("#ddInfo").empty();
				$.each(data,function(index,dd){
					
					var info;
					if(index%2==1){
						info = "<tr class='rowbgcolor'>"
						+"<td class='texcenter'><input type='radio' name= 'radio' value='"+dd.operType+"'></td>"
						+"<td class='texcenter'>"+dd.operName+"</td><td>"+dd.resName+"</td>"
										     
						+"</tr>"
					}else{
						info = "<tr>"
							+"<td class='texcenter'><input type='radio' name='radio' value='"+dd.operType+"'></td>"
							+"<td class='texcenter'>"+dd.operName+"</td><td>"+dd.resName+"</td>"
											     
							+"</tr>"
					}
					$("#ddInfo").append(info);
				});
				$("#pp").pagination({
					total : total,		// 总记录数
				})
			}
		})
	}
	
	// 删除
	function del(){
		var resId = $("input[type=radio]:checked").val();	// 得到删除条件业务类型
		if(resId != null && resId != ''){
			window.location.href="<%=request.getContextPath()%>/system/respro/delHP?resId="+resId;
		}else{
			$.messager.alert("提示","请选择删除项");
		}
	}
	
	// 修改
	function upd(){
		var type = $("input[type=radio]:checked").val();	// 得到修改条件业务类型
		if(type != null && type != ''){
			window.location.href="<%=request.getContextPath()%>/system/respro/toUpd?operType="+type;
		}else{
			$.messager.alert("提示","请选择修改项");
		}
	}
	// 重置查询条件
	function reset(){
		var type = $("#type").combobox("setValue","");
	}
</script>
</html>