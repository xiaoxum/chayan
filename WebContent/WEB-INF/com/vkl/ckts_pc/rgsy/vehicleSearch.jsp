<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<body onunload="unload()">
	<div class="easyui-accordion" style="width: 100%;" id="ser">
		<div title="车辆信息查询">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="140" class="bgc">查验区</td>
					<td width="150px"><select class="easyui-combobox" id="chkpt"
						style="width: 150px; height: 30px;">
							<option value="">全部</option>
							<c:forEach items="${chkpts }" var="list">
								<option value="${list.organCode }">${list.organName }</option>
							</c:forEach>
					</select></td>
				
					<td width="140" class="bgc">业务类型</td>
					<td width="150px"><select class="easyui-combobox" id="mySelect"
						style="width: 150px; height: 30px;">
							<option value="">全部</option>
							<c:forEach items="${operType }" var="list">
								<option value="${list.id }">${list.typeName }</option>
							</c:forEach>
					</select></td>
				<td width="140" class="bgc">使用性质</td>
				<td width="150px"><select class="easyui-combobox" id="syxz"
						style="width: 150px; height: 30px;">
							<option value="">全部</option>
							<c:forEach items="${cheUse }" var="list">
								<option value="${list.syxz }">${list.syxzName }</option>
							</c:forEach>
				</select></td>
				</tr>
				<tr>
				<td  class="bgc">号牌种类</td>
              <td ><select class="easyui-combobox" style="width:90%; height:30px;" name="hpzl" editable="false" id="hpzl">
               <option value="">--请选择--</option>
              <c:forEach items="${hpzl}" var="list">
                <option value="${list.dictKey}">${list.ldictionaryAbel }</option>
              </c:forEach>
                </select></td>
				 
					<td width="140" class="bgc">车辆识别代号</td>
					<td width="240px" colspan="3"><input id="clsbdh" class="easyui-textbox"
						style="width: 240px; height: 30px;" /></td>
					

				</tr>
				<tr>
				    <td class="bgc">受理时间</td>
					<td colspan="5"><input class="easyui-datebox" id=startTime
						style="width: 170px; height: 30px"> <label
						style="width: 170px; height: 30px;">至</label> <input
						class="easyui-datebox" id="endTime"
						style="width: 170px; height: 30px"></td>
					
				</tr>


			</table>
			<div
				style="width: 100%; background: #eff6fe; padding: 2px; text-align: right;"
				class="easyui-panel">
				<button type="button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="search();">查询</button>
				<button type="button" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'" onclick="updata();">重置</button>
			</div>
		</div>
	</div>
	<!-- <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='yhadd.html'">打印申请单</button>
     </div>-->

	<div class="easyui-accordion" style="width: 100%; margin-top: 5px;">
		<div title="车辆信息查看一览表">
			<table width="99%" style="" id="tab" cellspacing="0" cellpadding="0">
				<tr>
					<th width="15%">车辆识别代号</th>
					<th width="10%">号牌种类</th>
					<th width="8%">业务类型</th>
					<th width="15%">查验点</th>
					<th width="8%">使用性质</th>
					<th width="8%">审核状态</th>
					<th width="8%">受理时间</th>
					<th width="8%">打印操作</th>
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

$(function(){
	
	$('#startTime').datebox('setValue', formatterDate(new Date()));
	$('#endTime').datebox('setValue', formatterDate(new Date()));
	search(1);
	$("#tab tr:gt(0)").hover(function() {
		$(this).addClass('blue');
	}, function () {
	  $(this).removeClass('blue');
	});
	$('#ser').accordion({
		 animate:false
	})  
	//reInTime=setInterval("refreshPage()",1000);
	$("#clsbdh").textbox({onChange:function(){
		 $(this).textbox("getValue");
		var clsbdh = $(this).textbox("getValue").toUpperCase();
		$(this).textbox("setValue",clsbdh);
		
	 }});
	$("#hphm").textbox({onChange:function(){
		 $(this).textbox("getValue");
		var hphm = $(this).textbox("getValue").toUpperCase();
		$(this).textbox("setValue",hphm);
		
	 }});
})
var zddy;
//加载页面自动判断是否自动打印
var time;
formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};
 


function jump(srln,rckCount) {
	//var parentw=$('#父窗口中的元素ID', parent.document);
	var url="<%=request.getContextPath()%>/module/vehicle/loadtoviewInfo?srln="+srln+"&rckCount="+rckCount;
	parent.addTab1("车辆查验结果信息",url);
}


function updata(){
	/*  $("#clsbdh").textbox("setValue" , ""); */
	 $('#startTime').datebox('setValue', formatterDate(new Date()));
	 $('#endTime').datebox('setValue', formatterDate(new Date()));
	 $("#mySelect").combobox('setValue' , "");
	 $("#chkpt").combobox('setValue' , "");
	 $("#syxz").combobox('setValue' , "");
	 $("#clsbdh").textbox("setValue" , "");
}

// 查询
var pageNo = 1;
function search(pageNo){
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var syxz = $("#syxz").combobox('getValue');
	var parentId = $("#mySelect").combobox('getValue');
	var clsbdh = $("#clsbdh").textbox("getValue");
	var orangeCode= $("#chkpt").combobox('getValue');
	var hpzl= $("#hpzl").combobox('getValue');
	$.ajax({
        cache: true,
        type: "POST",
        url:'<%=request.getContextPath() %>/module/vehicle/loadivehicles',
        data:{
        	    clsbdh:clsbdh, 
	        	startTime:startTime,
	        	endTime:endTime,
	        	pageNo:pageNo,
	        	parentId:parentId,
	        	hpzl:hpzl,
	        	syxz:syxz,
	        	organCode:orangeCode
        	},
        async: false,
        error: function(request) {
            alert("系统操作繁忙请稍后！");
        },
        success: function(data) {
        	if(data=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
        	// 清空table内容
        	$("#myTbody").empty();
        	for(var i=0;i<data.list.length;i++){
        		// 将时间戳转成时间
        		if(data.list[i].hpzlName==null){
        			data.list[i].hpzlName=""
        		}
        		var date = new Date(data.list[i].createDate);
        		Y = date.getFullYear() + '-';
        		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        		D = date.getDate() + ' ';
        		if(data.list[i].createDate==null){
        			Y = "";
            		M = "";
            		D = "";
        		}
        		var auditerFlag=data.list[i].auditFlag;
        		var auditN = auditerFlag=='0'?"已通过":auditerFlag=='1'?"未通过":auditerFlag=='2'?"审核中":"";
        		var sfdy=data.list[i].sfdy;
        		var sydyN=sfdy=='0'?"未打印 ":sfdy=='1'?"已打印":"";
        		if(i % 2 == 1){
					var tr="<tr ondblclick='jump("+data.list[i].srln+","+data.list[i].rckCount+")'class='rowbgcolor '>"
			         	+"<td >"+data.list[i].clsbdh+"</td>"
			         	+"<td>"+data.list[i].hpzlName+"</td>"
			         	+"<td>"+data.list[i].typeName+"</td>"
			           +"<td>"+data.list[i].organName+"</td>" 
			         	+"<td>"+data.list[i].syxzName+"</td>"
			            +"<td>"+auditN+"</td>" 
			         	+"<td>"+Y+M+D+"</td>"
			         	+"<td ><button  type='button' onClick='jump("+data.list[i].srln+","+data.list[i].rckCount+")'>详情</button></td>"
			         	+"</tr>";
				}else{
					var tr="<tr ondblclick='jump("+data.list[i].srln+","+data.list[i].rckCount+")'  >"
			         	+"<td >"+data.list[i].clsbdh+"</td>" 
			         	+"<td>"+data.list[i].hpzlName+"</td>"
			         	+"<td>"+data.list[i].typeName+"</td>"
			         	 +"<td>"+data.list[i].organName+"</td>" 
			         	+"<td>"+data.list[i].syxzName+"</td>"
			         	 +"<td>"+auditN+"</td>" 
			         	+"<td>"+Y+M+D+"</td>"
			         	+"<td ><button  type='button' onClick='jump("+data.list[i].srln+","+data.list[i].rckCount+")'>详情</button></td>"
			         	+"</tr>";
				}
        		// 添加tr
        		$("#myTbody").append(tr);
        	}
        	// 显示分页栏
        	$("#pp").show();
        	// 设置总条数
        	
        	$("#pp").pagination({
       		  total:data.totalCount
       		});
        	//reTime=5;
        	// reInTime=setInterval("refreshPage()",1000);<button  type='button' onClick='dy1("+data.list[i].srln+","+data.list[i].operStatu+","+data.list[i].rckCount+")'  >凭证打印</button><button  type='button' onClick='dy2("+data.list[i].srln+","+data.list[i].operStatu+","+data.list[i].rckCount+")'>照片打印</button>
        }
    });
	
	$("#tab tr:gt(0)").hover(function() {
		$(this).addClass('blue');
	}, function () {
	  $(this).removeClass('blue');
	});
	
	
}




 

 
 
 
 
</script>
</html>
