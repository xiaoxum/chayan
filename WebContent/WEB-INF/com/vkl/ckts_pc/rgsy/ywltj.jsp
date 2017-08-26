<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8">
<title>查验区合格率统计</title>
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
<script
	src="<%=request.getContextPath()%>/static/js/echarts.common.min.js"></script>
</head>

<body >
	<div id="p" class="easyui-panel" title="查询"
		style="width: 100%; margin-bottom: 5px;">
		<table width="100%" height="100%" align="center" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="bgc">查验区</td>
				<td><select class="easyui-combobox"
					style="width: 350px; height: 30px;" id="chkpt">
						<option value="">---请选择查验区---</option>
						<c:forEach items="${allChkpt }" var="chkpt">
							<option value="${chkpt.organCode }">${chkpt.organName }</option>
						</c:forEach>
				</select></td>

              <td class="bgc">业务类型</td>
				<td><select class="easyui-combobox"
					style="width: 250px; height: 30px;" id="operType">
					    <option value="">--请选择--</option>
						<c:forEach items="${allOType }" var="operType">
							<option value="${operType.id}">${operType.typeName }</option>
						</c:forEach>
				</select></td>
     
				<td class="bgc">使用性质</td>
				<td><select class="easyui-combobox"
					style="width: 200px; height: 30px;" id="syxz">
					    <option value="">--请选择--</option>
						<c:forEach items="${syxzs}" var="sy">
							<option value="${sy.parentId}">${sy.parentName }</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td class="bgc">查验时间</td>
				<td colspan="6"><input class="easyui-datebox"
					style="width: 200px; height: 30px;" id="start" />&nbsp;&nbsp;<label>至</label>&nbsp;&nbsp;<input 
					class="easyui-datebox" style="width: 200px; height: 30px;" id="end" />&nbsp;&nbsp;&nbsp;<input type="checkbox" name="timescape" id="timescape" value="1"/>&nbsp;&nbsp; 时间段</td>
			</tr>
		</table>
		<div
			style="width: 100%; overflow: hidden; background: #eff6fe; padding: 2px; text-align: right;"
			class="easyui-panel">
			<button type="button" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'" onclick="search();">查询</button>
			<button type="button" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload'" onclick="reset();">重置</button>
		</div>
	</div>




	<div id="p" class="easyui-panel" title="" style="width: 100%;">
		<table width="100%" height="100%" align="center" cellspacing="0"
			cellpadding="0" id="tab">
			<thead>
				<th>查验区</th>
				<th>使用性质</th>
				<th>业务类型</th>
				<th>受理时间</th>
				<th>业务总量</th>
				<th>合格总量</th>
				<th>合格率</th>
			</thead>

			<tbody id="ddInfo">

			</tbody>
		</table>
	</div>
	<div style="width: 100%; background: #eff6fe;" class="easyui-panel">
		<div id="pp" class="easyui-pagination"
			data-options="
					total:100, 
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'Page',
                    onSelectPage:function(pageNumber){page(pageNumber)}"></div>
	</div>

	<div style="border: 2px solid #95B8E7;">
		<div id="chart" style="height: 400px; width: 100%;"></div>
	</div>
</body>
<script>
//使输入框默认时间值
/* function setCurrentDate(){
	
  var myDate = new Date(); 
  var current=myDate.toLocaleDateString();
  document.getElementById("_easyui_textbox_input4").value=current; 
//  $("#end").val()=current;
  
  cdate =current;
} */

$(function(){
	$('#start').datebox('setValue', formatterDate(new Date()));
	$('#end').datebox('setValue', formatterDate(new Date()));
})
formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

// 重置
function reset(){
	$("#chkpt").combobox("setValue","");
	$("#operType").combobox("setValue","");
	$("#syxz").combobox("setValue","");
	$("#start").textbox("setValue","");
	$("#end").textbox("setValue","");
	$("#time").checked = "checked"/"";
	$("checkbox").attr("checked",false);
}

// 查询统计
function search(){
		page(1);
}
// 分页查询
function page(num){
	var chkpt = $("#chkpt").combobox("getValue");
	var syxz = $("#syxz").combobox("getValue");
	var start = $("#start").textbox("getValue");
	var operType = $("#operType").combobox("getValue");
	var end = $("#end").textbox("getValue");
	var time=$('input[name="timescape"]:checked').val();
	var dd = {organCode : chkpt,
			pSyxz : syxz,
			start : start,
			end : end,
			pageNo : num,
			time:time,
			operType:operType,
			pageSize : 10};
	$.ajax({
		url : '<%=request.getContextPath()%>/count/check/seacherbyClxx',
		type : 'post',
		data : dd,
		success : function(data){			
			  debugger;
			$("#ddInfo").empty();
			var total = data.totalCount;
            data =data.list;
			$.each(data,function(index,dt){
					var info;
					if(index % 2 == 1){
						info = "<tr class='rowbgcolor'>"
							+"<td>"+dt.organName+"</td>"
							+"<td>"+dt.syxzName+"</td>"
							+"<td>"+dt.typeName+"</td>"
							+"<td>"+dt.showTime+"</td>"
							+"<td align='center'>"+dt.aCount+"</td>"
							+"<td align='center'>"+dt.hgCount+"</td>"
							+"<td align='center'>"+toDecimal(dt.hgCount,dt.aCount)+"%"+"</td>"
							+"</tr>";
					}else{
						info = "<tr>"
							+"<td>"+dt.organName+"</td>"
							+"<td>"+dt.syxzName+"</td>"
							+"<td>"+dt.typeName+"</td>"
							+"<td>"+dt.showTime+"</td>"	
							+"<td align='center'>"+dt.aCount+"</td>"
							+"<td align='center'>"+dt.hgCount+"</td>"
							+"<td align='center'>"+toDecimal(dt.hgCount,dt.aCount)+"%"+"</td>"
							+"</tr>";
					}
						$("#ddInfo").append(info);
			});
			$("#pp").pagination({
				total : total,
			});	
		}
	})
}


function toDecimal(x,y){
	var f=parseFloat(x); 
	var g=parseFloat(y); 
	if (isNaN(f)||isNaN(g)) { 
		 return; 
    } 
	var fg=x/y;
	var hgv= Math.round(fg*10000)/100; 
	return hgv;
}
</script>
</html>

