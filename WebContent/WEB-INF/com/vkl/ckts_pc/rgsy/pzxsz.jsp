<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>查验拍照设置</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui-lang-zh_CN.js"></script>
</head>

<body>
   <div id="p" class="easyui-panel" title="查验拍照查询" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
     <td class="bgc">业务类型</td>
     <td><select class="easyui-combobox" style="width:300px;" id="type"><option value="">---请选择---</option>
     <c:forEach items="${operType }" var="type">
     <option value="${type.id }">${type.typeName }</option>
     </c:forEach>
     </select></td>
      <td class="bgc">车辆类型</td><td><select class="easyui-combobox" style="width:300px;" id="cllx"><option value="" >---请选择---</option>
      <c:forEach items="${cllx }" var="cc">
      	<option value="${cc.parentCllx }">${cc.parentName }</option>
     </c:forEach>
     </select>
     </td>
     </tr>
     </table>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
              <button  type="button" class="easyui-linkbutton"   data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton"   data-options="iconCls:'icon-reload'"  onclick="reset();">重置</button></div>
      </div>
      
    <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-add'"   onclick="window.location='<%=request.getContextPath()%>/system/photo/addPhotoSet'">新增</button>
     <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-edit'"  onclick="udp();">修改</button>
     <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="del();">删除</button>
     </div>
  
  <div id="p" class="easyui-panel" title="查验拍照设置一览表" style="width:100%;"> 
  <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0" id="tab">
     <thead height="35"><th>选择</th><th>业务类型</th><th>使用性质</th><th>车辆类型</th><th>拍照项数目</th></thead>
     
     <tbody id="ddInfo">
 
     
     </tbody>
  </table>
</div>
<div style=" width:100%; background:#eff6fe;"  class="easyui-panel">
      <div id="pp"   class="easyui-pagination" data-options="
					total:100, 
					pageSize:15,
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'Page',
                    onSelectPage:function(pageNumber){page(pageNumber);}"></div>
    </div>

</body>
<script type="text/javascript">
$(function(){
	var flag = "${flag11}";
	if(flag != null && flag != ''){
		if(flag =='del') $.messager.alert("提示","删除成功！");
		//if(flag == "insert") $.messager.alert("提示","添加成功！");
		if(flag =='insertF') $.messager.alert("提示","该设置已存在，您可以选择修改！");
		if(flag == 'udp') $.messager.alert("提示","修改成功！");
	}
	<%session.setAttribute("flag11",null);%>
	page(1);
})
	function page(num){
		var pageSize = 15;
		var type = $("#type").combobox("getValue");
		var cllx = $("#cllx").combobox("getValue");
		var dd = {pageNo : num, pageSize : pageSize, cllx : cllx,operType:type};
		$.ajax({
			url : '<%=request.getContextPath()%>/system/photo/photoPage',
			type : 'post',
			data : dd,
			success : function(data){
				if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				$("#ddInfo").empty();
				var total = data[0];
				var data = data[1];
				$.each(data,function(index,dd){
					var info;
					if(index>=0){
						if(index % 2 == 1){
							info = "<tr class='rowbgcolor'>"
								+"<td class='texcenter'><input type='radio' name='sel' value='"+dd.cllx+","+dd.operType +","+dd.syxz + "'></td>"
						     	+"<td>"+dd.typeName+"</td>"
						     	+"<td>"+dd.syxzName+"</td>" 
						     	+"<td>"+dd.parentName+"</td>"
						     	+"<td>"+dd.isMust+"</td>"	
						     	+"</tr>";
						}else{
							info = "<tr>"
								+"<td class='texcenter'><input type='radio' name='sel' value='"+dd.cllx+ ","+dd.operType +","+dd.syxz +"'></td>"
						     	 +"<td>"+dd.typeName+"</td>"
						     	+"<td>"+dd.syxzName+"</td>" 
						     	+"<td>"+dd.parentName+"</td>"
						     	+"<td>"+dd.isMust+"</td>"	
						     	+"</tr>";
						}
					}
					$("#ddInfo").append(info);
				})
				$("#pp").pagination({
					  total:total,
					  
					  });
			}
		})
}
	function del(){
		var str = $("input[name='sel']:checked").val();
		if(str != null && str !=''){
			var dd= str.split(",");	
			 parent.$.messager.confirm('询问', '您确定要删除选择项？',
					    function(b) {
				 if(b){
					 //window.location.href="<%=request.getContextPath()%>/system/photo/delPhotoSet1?cllx="+str;
					 window.location.href="<%=request.getContextPath()%>/system/photo/delPhotoSet?cllx="+dd[0]+"&operType="+dd[1]+"&syxz="+dd[2];
				 }
			 })
		}else{
			$.messager.alert("提示","请选择删除项！");
		}
	}
	
	function udp(){
		var str = $("input[name='sel']:checked").val();
		if(str != null && str !=''){
			var dd= str.split(",");	
			//window.location.href="<%=request.getContextPath()%>/system/photo/toUpdPhotoSet1?cllx="+str;
			window.location.href="<%=request.getContextPath()%>/system/photo/toUpdPhotoSet?cllx="+dd[0]+"&operType="+dd[1]+"&syxz="+dd[2];
		}else{
			$.messager.alert("提示","请选择修改项！");
		}
	}
	function search(){
		page(1);
	}
	function reset(){
		var type = $("#type").combobox("setValue","");
		var cllx = $("#cllx").combobox("setValue","");
	}
</script>
</html>
