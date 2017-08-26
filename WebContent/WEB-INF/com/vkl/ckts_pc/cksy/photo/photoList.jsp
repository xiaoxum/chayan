<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>车辆审核</title>
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
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/zDrag.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/zDialog.js"></script>
<!-- 自定义数据验证js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/custom-validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/CreateControl.js"></script>

<style type="text/css">
.blue {
	background-color: #7FFFD4;
	cursor: hand;
}
</style>
</head>
<body >

    <input type="hidden" value="<%=request.getContextPath()%>" id="xmlj" />
	<div class="easyui-accordion" style="width: 100%;" id="ser">
		<div title="车辆信息查询">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="140" class="bgc">车辆识别代号</td>
					<td width="240px"><input id="clsbdh" class="easyui-textbox"
						style="width: 240px; height: 30px;" /></td>
					
					<td width="140" class="bgc">业务类型</td>
					<td width="150px"><select class="easyui-combobox" id="mySelect"
						style="width: 150px; height: 30px;">
							<option value="">全部</option>
							<c:forEach items="${operType }" var="list">
								<option value="${list.id }">${list.typeName }</option>
							</c:forEach>
					</select></td>
					<td class="bgc">号牌号码</td>
					<td width="240px"><input id="hphm" class="easyui-textbox"
						style="width: 240px;" /></td>
					
				</tr>
				<tr>
					
					<td class="bgc" >打印状态</td>
					<td width="250px">
					<select class="easyui-combobox" id="sfdy"
						style="width: 240px; height: 30px;">
						<option value="0">未打印</option>
						   <option value="">全部</option>
							<option value="1">已打印</option>
					</select></td>
					<td class="bgc">受理时间</td>
					<td colspan="3"><input class="easyui-datebox" id=startTime
						style="width: 170px; height: 30px"> <label
						style="width: 170px; height: 30px;">至</label> <input
						class="easyui-datebox" id="endTime"
						style="width: 170px; height: 30px"></td>
					<td class="bgc star" id="ts"></td>
					<td></td>

				</tr>
				
              <tr>
              <td class="bgc">启用自动打印</td>
			
              <td width="550px" colspan="3">
              <span>
                  <label>照片</label>
				<select class="easyui-combotree" data-options="multiple: true,cascadeCheck:false,onCheck:checkNode" url="<%=request.getContextPath()%>/module/photoinfo/getpicprojectlist" id="menuId" style="width:200px;"></select>
					</span>
					<label>数量</label>
					<input type="text" name="printNum" value="1" id ="printNum" style="width: 30px; " class="easyui-textbox"/>
					 <input type="radio" name="zddy" value="1"  style="width: 30px; "/>启动 <input
						name="zddy" type="radio" value="0" checked="checked" id="gb" style="width: 30px; " />关闭
					</td>
					<td class="star" id="tss" colspan="2"></td>
              </tr>
			</table>
			<div
				style="width: 100%; background: #eff6fe; padding: 2px; text-align: right;"
				class="easyui-panel">
				<button type="button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="findInfo();">查询</button>
				<button type="button" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'" onclick="updata();">重置</button>
			</div>
		</div>
	</div>
	<!-- <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='yhadd.html'">打印申请单</button>
     </div>-->
	<div
		style="width: 100%; overflow: hidden; background: #eff6fe; padding: 2px;"
		class="easyui-panel">

	</div>
	<div class="easyui-accordion" style="width: 100%; margin-top: 5px;">
		<div title="车辆信息查看一览表">
			<table width="99%" style="" id="tab" cellspacing="0" cellpadding="0">
				<tr>
					<th width="14%">车辆识别代号</th>
					<th width="8%">号牌号码</th>
					<th width="10%">车辆类型</th>
					<th width="8%">业务类型</th>
				<!-- 	<th width="6%">图片路径</th> -->
					<!-- <th>查验点</th> -->
					<th width="7%">图片名称</th>
					<!-- <th>业务受理人</th> -->
					<th width="7%">是否打印</th>
					<th width="7%">上传时间</th>
					<th width="13%">打印操作</th>
				</tr>
				<tbody id="myTbody">

				</tbody>

			</table>
			<!--分页开始-->
			<div style="width: 100%;" class="easyui-panel">
				<div id="pp" style="display: none;" class="easyui-pagination"
					data-options="
					total:0, 
					pageSize:20,
					layout:['first','prev','links','next','last','manual'],
                    beforePageText:'当前页',
                    onSelectPage:function(pageNo){
                   search(pageNo);
                     
				}
                   
					"></div>
			</div>
			<!--分页结束-->
			
				<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs sybg"
			style="width: 100%; height: 100%; background-color: #eff7fe;">

		</div>


	</div>
		</div>
	</div>

</body>
<script type="text/javascript">

var xmlj = $('#xmlj').val();
var reTime=6;
var reInTime;
var addyNum=11;
var wdapp;


//加载页面自动判断是否自动打印
var time;
formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};
 
	








function updata(){
	 $("#clsbdh").textbox("setValue" , "");
	 $('#startTime').datebox('setValue', formatterDate(new Date()));
	 $('#endTime').datebox('setValue', formatterDate(new Date()));
	 $("#mySelect").combobox('setValue' , "");
	 $("#auditFlag").combobox('setValue' , "");
	 $("#hphm").textbox("setValue" , "");
}

// 查询
var pageNo = 1;


function viewPic(url){
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 400;
   // diag.ShowButtonRow=true;
	diag.Title = "照片查看";
    diag.InnerHtml="<img src='"+url+"' width='600' height='400'  />"
	diag.show();
}



function toStr(str){
	switch(str){
	case"3":
		return "查验";

	case"4":
		return "审核中";
	case"5":
		return "业务结束";
	
	case"7":
		return "照片补拍";
	}
}

 
 
 
 
 
</script>





	<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/cksy/photo/photo.js"></script>
</html>
