<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>业务信息设置</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui-lang-zh_CN.js"></script>
</head>

<body>
   <div id="p" class="easyui-panel" title="业务信息查询" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
      <td class="bgc">查验项目名称</td><td><select class="easyui-combobox" style="width:60%;" id="proName">
      	<option value="">--- 请选择 ---</option>
      	<c:forEach items="${ckPro }" var="cp" >
	      	<option value="${cp.id }">${cp.proName }</option>
      	</c:forEach>
      </select></td>    
      <td class="bgc" width="140">启用/禁用：</td><td ><input type="checkbox" value="1"/><label style="padding-right:20px;">启用</label><input type="checkbox" value="0"/><label style="padding-right:20px;">禁用</label></td>

     <%--  <td class="bgc">代码</td><td><select class="easyui-combobox" style="width:70%;" id="anncId">
      	<c:forEach items="${annc}" var="an">
      		<option value="${an.id }">${an.id}</option>
      	</c:forEach>	
      
      </select>
      </td> --%>
     </tr>
     </table>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
              <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-reload'"  onclick="reset();">重置</button>
       </div>
      </div>
      
    <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='<%=request.getContextPath()%>/system/operaSetting/toAddOpera'">新增</button>
<!--      <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-using'"  onclick="using(0);">启用</button>
     <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-forbidden'"  onclick="using(1);">禁用</button> -->
     <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-edit'"  onclick="udp();">修改</button>
     <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="del();">删除</button>
     </div>
  
  <div id="p" class="easyui-panel" title="业务信息设置一览表" style="width:100%;"> 
  <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0" id="tab">
     <thead height="35"><th>选择</th><th>查验项目名称</th><th>启用/禁用</th><th>查验项业务要求</th></thead>
     
     <tbody id="ddInfo">
     
     
     </tbody>
  </table>
</div>
<div style=" width:100%; background:#eff6fe;"  class="easyui-panel">
      <div id="pp"   class="easyui-pagination" data-options="
					total:0, 
					pageSize: 20,
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'Page',
                    onSelectPage:function(pageNumber){ page(pageNumber);}"></div>
    </div>

</body>
<script type="text/javascript">
	$(function(){
		if('${flag11}' == "dele"){
			$.messager.alert("提示","业务信息删除成功！");
		<%session.setAttribute("flag11", null);%>
		}
		page(1);
	});
	var b = null;	// 用来接收可能的条件
	
	// 分页
	function page(num){
		var proId = $("#proName").combobox("getValue");
		var pageSize = 20;
		var statu = [];
		$("input[type=checkbox]:checked").each(function(){
			statu.push($(this).val());
		})
		if(statu.length == 2){
			b = null;
		}else{
			b = statu[0];
		}
		var dd = {pageNo : num, pageSize : pageSize, proId : proId,anncStatu : b};
		$.ajax({
			url : '<%=request.getContextPath()%>/system/operaSetting/opertaion',
			type : 'post',
			data : dd,
			success : function(data){
				if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				$("#ddInfo").empty();
				var total = data[0];
				data = data[1];
				function open(){
 
					  window.location.href="<%=request.getContextPath()%>/system/operaSetting/toUpdateOpera?id="+id;
				  }
				$.each(data,function(index,dd){
					
					
					var info;
					if(index % 2 == 1){
						info = "<tr class='rowbgcolor' ondblclick='open("+dd.id+");'>"
						 +"<td class='texcenter'><input type='radio' name='sel' value='"+dd.id +"'/></td><td>"+dd.proName +"</td>"
					     +"<td>"+dd.anncStatu+"</td>"
					     +"<td>"+dd.addcContent+"</td>"
					     +" </tr>";
					}else{
						info = "<tr ondblclick='open();'>"
							 +"<td class='texcenter'><input type='radio' name='sel' value='"+dd.id +"'/></td><td>"+dd.proName +"</td>"
						     +"<td>"+dd.anncStatu+"</td>"
						     +"<td>"+dd.addcContent+"</td>"
						     +" </tr>";
					}
						$("#ddInfo").append(info);
				})
				$("#pp").pagination({
					  total : total,
					  
				  });
			}
		})
		
	}
	
	function using(a){
		b = a;
		page(1);
	}
	
	// 条件查询
	function search(a,b){
		page(1);
	}
	
	// 重置查询条件
	function reset(){
		var proId = $("#proName").combobox("setValue","");
		$("input[type=checkbox]").attr("checked",false);
	}
	function del(){
		var id =$("input[name='sel']:checked").val();
		if(id != null && id != ''){
			window.location.href="<%=request.getContextPath()%>/system/operaSetting/deleteOpera?id="+id;
		}else{
			$.messager.alert("提示","请选择业务信息删除项！");
		}
	}
	function udp(){
		var id = $("input[name='sel']:checked").val();
		if(id != null && id != ''){
			window.location.href="<%=request.getContextPath()%>/system/operaSetting/toUpdateOpera?id="+id;
		}else{
			$.messager.alert("提示","请选业务信息择修改项！");
		}
  
		
	}
</script>
</html>