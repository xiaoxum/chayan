<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>异常处理</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/demo.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/css.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
	 <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui-lang-zh_CN.js"></script>
	 <!-- 自定义数据验证js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/custom-validate.js"></script>
</head>

<body>
	<div id="p" class="easyui-panel" title="查询"
		style="width: 100%; margin-bottom: 5px;">
		<table width="100%" height="100%" align="center" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="bgc">查验区</td>
				<td><input class="easyui-textbox" style="width: 80%;" id="area"/></td>
				<td class="bgc">查验员</td>
				<td><input class="easyui-textbox" style="width: 80%;" id="view"/></td>
				<td class="bgc">车辆类型</td>
				<td><select class="easyui-combobox"
					style="width: 80%; height: 30px;" id="cllx">
						<option value="">---请选择---</option>
						<c:forEach items="${cllx }" var="cl">
							<option value="${cl.parentCllx }">${cl.parentName}</option>
						</c:forEach>
				</select></td>
				<td class="bgc">业务类型</td>
     <td><select class="easyui-combobox" style="width:80%;" id="type"><option value="">---请选择---</option>
     <c:forEach items="${type }" var="type">
     <c:if test="${tp == type.id }"><option value="${type.id }" selected="selected">${type.typeName }</option></c:if>
     <option value="${type.id }">${type.typeName }</option>
     </c:forEach>
     </select></td>
			</tr>
			<tr>
				<td class="bgc">查验时间</td>
				<td colspan="7"><input class="easyui-datetimebox"
					style="width: 40%; height: 30px;" id="start"/><label>至</label><input
					class="easyui-datetimebox" style="width: 40%; height: 30px;" id="end" /></td>
			</tr>
		</table>
		     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
              <button  type="button" class="easyui-linkbutton"   data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-reload'"  onclick="reset();">重置</button>
       </div>
	</div>


	<div id="p" class="easyui-panel" title="查验异常表单" style="width: 100%;">
		<table width="100%" height="100%" align="center" cellspacing="0"
			cellpadding="0" id="tab">
			<thead>
				<th>序号</th>
				<th>查验时间</th>
				<th>查验员</th>
				<th>查验区</th>
				<th>车辆类型</th>
				<th>业务类型</th>
				<th>异常项</th>
				<th>操作</th>
			</thead>

			<tbody id="ddInfo">
				
			</tbody> 
		</table>
	</div>
	<div style="width: 100%; background: #eff6fe;" class="easyui-panel">
		<div id="pp" class="easyui-pagination"
			data-options="
					total:0, 
					pageSize:15,
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'Page',
                    onSelectPage:function(pageNumber){page(pageNumber);}"></div>
	</div>

</body>
<script type="text/javascript">
	$(function(){
		var flag = '${requestScope.del}';
		if(flag != '' && flag != null){
			if(flag == "dealExe"){
				$.messager.alert("提示","已终止查验！");
			}
		}
		page(1);
	})
	// 分页
	function page(num){
		var area = $("#area").textbox("getValue");
		var view = $("#view").textbox("getValue");
		var cllx = $("#cllx").combobox("getValue");
		var type = $("#type").combobox("getValue");
		var start = $("#start").textbox("getValue");
		var end = $("#end").datetimebox("getValue");
		if(removeAllSpace(area) != ""){
			if (!checkRegExp(area, "isChineseName", "","查验区格式不正确")) {
		        return;
		    }
		}
		if(removeAllSpace(view) != ""){
			if (!checkRegExp(view, "isChineseName", "","查验员格式不正确")) {
		        return;
		    }
		}
		var sdate;
		var edate;
		var date = new Date();
		Y = date.getFullYear() + '-';
		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		D = date.getDate() + ' ';
		var nowdate = Y+M+D+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		nowdate = sdate = new Date(nowdate.replace("-", "/").replace("-", "/"));
		if(removeAllSpace(start) !=""){
			sdate = new Date(start.replace("-", "/").replace("-", "/"));
			if(sdate>nowdate){
				$.messager.alert("提示","查验开始时间不能后于当前时间");
				return;
			}
		}
		if(removeAllSpace(end) !=""){
			edate = new Date(end.replace("-", "/").replace("-", "/"));
			if(edate>nowdate){
				$.messager.alert("提示","查验结束时间不能后于当前时间");
				return;
		}
		} 
		if(removeAllSpace(start) !="" && removeAllSpace(end) != ""){
			sdate = new Date(start.replace("-", "/").replace("-", "/"));
			edate = new Date(end.replace(/-/g,"/").replace("-", "/"));
			if(sdate > edate ){
				$.messager.alert("提示","查验开始时间不能前于结束时间");
				return;
			}
		}
		
		
		
		$.ajax({
			url : '<%=request.getContextPath()%>/statistic/except/exceptPage',
			type : 'post',
			data : {orginName : area, viewName : view, vehType : cllx, type : type, start :start, end : end, pageNo : num, pageSize : 20},
			success : function(data){
				if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				$("#ddInfo").empty();
				var total = data[0];
				data = data[1];
				$.each(data,function(index,dd){
					var info;
					if(index % 2 == 1){
						info = "<tr class='rowbgcolor'>"
							+"<td>"+(index+1)+"</td>"
							+"<td>"+dd.date +"</td>"
							+"<td>"+dd.viewName +"</td>"
							+"<td>"+dd.orginName +"</td>"
							+"<td>"+dd.cllxName +"</td>"
							+"<td>"+dd.operTypeName +"</td>"
							+"<td>"+dd.exceptName +"</td>"
							+"<td onClick=window.location='<%=request.getContextPath()%>/statistic/except/exceptDetail?excId="+dd.excId +"' class='ztcolor'>查看详情</td>"
							+"</tr>";
						
					}else{
						info = "<tr>"
							+"<td>"+(index+1)+"</td>"
							+"<td>"+dd.date +"</td>"
							+"<td>"+dd.viewName +"</td>"
							+"<td>"+dd.orginName +"</td>"
							+"<td>"+dd.cllxName +"</td>"
							+"<td>"+dd.operTypeName +"</td>"
							+"<td>"+dd.exceptName +"</td>"
							+"<td onClick=window.location='<%=request.getContextPath()%>/statistic/except/exceptDetail?excId="+dd.excId +"' class='ztcolor'>查看详情</td>"
							+"</tr>";
					}
						$("#ddInfo").append(info);
				})
				$("#pp").pagination({
					total:total,
				})
			}
		})
	}
	
	// 条件查询
	function search(){
		page(1);
	}
	
	// 重置查询条件 
	function reset(){
		var area = $("#area").textbox("setValue","");
		var view = $("#view").textbox("setValue","");
		var cllx = $("#cllx").combobox("setValue","");
		var type = $("#type").combobox("setValue","");
		var start = $("#start").textbox("setValue","");
		var end = $("#end").datetimebox("setValue","");
	}
</script>
</html>
