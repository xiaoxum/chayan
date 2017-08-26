<%@ page language="java" contentType="text/html; charset=utf-8"  import="com.vkl.ckts.common.entity.UserEntity,com.vkl.ckts.common.constr.Constrant,com.vkl.ckts.common.utils.Base64Utils"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	UserEntity user = (UserEntity)session.getAttribute(Constrant.S_USER_SESSION);
	if(user != null){
		user.setUserName(Base64Utils.decode(user.getUserName()));
		pageContext.setAttribute("userNamess", Base64Utils.decode(user.getUserName()));
		pageContext.setAttribute("sfgly", user.getSfgly());
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>车辆审核一览表</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/demo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/css.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
<script src="<%=request.getContextPath()%>/static/js/echarts.common.min.js"></script>
<!-- 自定义数据验证js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/custom-validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/zDrag.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/zDialog.js"></script>
</head>
<body>
	<div class="easyui-accordion" style="width: 100%;">
		<div title="车辆信息查询">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="140" class="bgc">车辆识别代号</td>
					<td width="240px"><input id="clsbdh" class="easyui-textbox"
						style="width: 240px; height: 30px;" /></td>
					<td width="140" class="bgc">查验状态</td>
					<td width="250px">
				    <select class="easyui-combobox"	style="width: 150px; height: 30px;" id="operStatu">
				   <option value="4">待审核</option>
				    <option value="">全部</option>
					<option value="5">已完成</option>		
					</select>
					</td>
					<td width="140" class="bgc">业务类型</td>
					<td width="100">
						<select class="easyui-combobox"	id="mySelect" style="width: 150px; height: 30px;">
						<option value="">全部</option>
								<c:forEach items = "${operType }" var = "list">
									<option value = "${list.id }" >${list.typeName } </option>
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
				<td class="bgc">号牌号码</td>
					<td width="240px"><input id="hphm" class="easyui-textbox"
						style="width: 240px;" /></td>
			     <td width="140" class="bgc">审核状态:</td>
				 <td width="250px">
				    <select class="easyui-combobox"	style="width: 150px; height: 30px;" id="auditFlag">
				      <option value="">全部</option>
				      <option value="0">通过</option>
				      <option value="1">不通过</option>
					  <option value="2">审核中</option>
					</select>
					</td>
				<td  class="bgc"><span class="star"></span>号牌种类</td>
               <td ><select class="easyui-combobox" style="width:130px; height:30px;" name="hpzl" editable="false" id="hpzl">
               <option value="">--请选择--</option>
                <c:forEach items="${hpzl}" var="list">
                <option value="${list.dictKey}">${list.ldictionaryAbel }</option>
              </c:forEach>
                </select></td>
				</tr>
               <tr>
                 <td class="bgc">自动审核(当前页):</td>
					<td width="150px"> <input type="radio" name="zdsh" value="1" style="width: 30px; "/>启动 <input
						name="zdsh" type="radio" value="0" checked="checked" id="gb" style="width: 30px; " />关闭</td>
               <td class="bgc star" id="ts" colspan="4"></td>
               </tr>
               	<tr>
					<td class="bgc">受理时间</td>
					<td colspan="2"><input class="easyui-datebox" id=startTime
						style="width: 170px; height: 30px"> <label
						style="width: 170px; height: 30px;">至</label> <input
						class="easyui-datebox" id="endTime"
						style="width: 170px; height: 30px"></td>
					
					<td width="140" class="bgc">查验区</td>
					<td width="150px"><select class="easyui-combobox" id="chkpt"
						style="width: 150px; height: 30px;">
							<option value="">全部</option>
							<c:forEach items="${chkpts }" var="list">
								<option value="${list.organCode }">${list.organName }</option>
							</c:forEach>
					</select></td>
					<td class="bgc star" id="ts"></td>
				</tr>
			</table>
			<div
				style="width: 100%; background: #eff6fe; padding: 2px; text-align: right;"
				class="easyui-panel">
				<span style="margin-left:320px;float:left;color:red;">&nbsp;&nbsp;当前审核信息(辆次):&nbsp;&nbsp;<label>&nbsp;&nbsp;未审核:&nbsp;&nbsp; </label><span id="wshsl"></span><label>&nbsp;&nbsp;已审核:&nbsp;&nbsp;</label><span id="yshhsl"></span><label>&nbsp;&nbsp;总计:&nbsp;&nbsp;</label><span id="zsl"></span></span>
				
				<button type="button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="search();" >查询</button>
				<button type="button" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'" onclick="search();" >重置</button>
				<!-- <button type="button" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'" onclick="tj();">审核统计</button> -->
			</div>
		</div>
	</div>
	<!-- <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='yhadd.html'">打印申请单</button>
     </div>-->
	<div class="easyui-accordion" style="width: 100%; margin-top: 5px;">
		<div title="车辆信息查看一览表">
		   
			<table width="99%" style="" id="tab" cellspacing="0" cellpadding="0" >
				<tr>
					<th>车架号</th>
					<th>车牌号码</th>
					<th>业务类型</th>
					<th>查验点</th>
					<th>查验状态</th>
					<th>受理时间</th>
					<th>审核状态</th>
					<th>审核员</th>
					<th>号牌种类</th>
					<th>操         作</th>
				</tr>
			<tbody id="myTbody">
				
			</tbody>

			</table>
			<!--分页开始-->
			<div style="width:100%;" class="easyui-panel" >
				<div id="pp" style="display:none;" class="easyui-pagination"
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
		</div>
	</div>

</body>
<script type="text/javascript">
// 详情跳转
function jump(srln,rckCount){
	var url = "<%=request.getContextPath()%>/module/vehicle/loadcheckaudit?srln="+srln+"&rckCount="+rckCount;
	parent.addTab1("审核信息",url);
	//window.location.href="<%=request.getContextPath()%>/module/vehicle/loadcheckaudit?srln="+srln+"&rckCount="+rckCount;
};

function toStr(str){
	switch(str){
	case"1":
		return "入场";
	case"2":
		return "查验";
	case"3":
		return "监销";
	case"4":
		return "审核中";
	case"5":
		return "业务结束";
	case"6":
		return "车架号拍照";
	case"7":
		return "照片补拍";
	}
}

// 查询
function search(pageNo){
	var sfgly='${sfgly}';
	/* var srln = $("#srln").val(); */
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var hphm = $("#hphm").textbox("getValue");
	var parentId = $("#mySelect").combobox('getValue');
	var operStatu=$("#operStatu").combobox('getValue');
	var clsbdh = $("#clsbdh").textbox("getValue");
	var auditFlag=$("#auditFlag").combobox('getValue');
	var hpzl=$("#hpzl").combobox("getValue");
	var orangeCode= $("#chkpt").combobox('getValue');
	$.ajax({
        cache: true,
        type: "POST",
        url:'<%=request.getContextPath() %>/module/vehicle/loaddata',
        data:{
	        	/* srln:srln,*/
	        	startTime:startTime,
	        	endTime:endTime, 
	        	pageNo:pageNo,
	        	parentId:parentId,
	        	operStatu:operStatu,
	        	clsbdh:clsbdh,
	        	hphm:hphm,
	        	auditFlag:auditFlag,
	        	hpzl:hpzl,
	        	organCode:orangeCode
	        	
        	},
        async: false,
        error: function(request) {
            alert("系统操作繁忙请稍后！");
        },
        success: function(data) {
        	// 清空table内容
        	$("#myTbody").empty();
        	for(var i=0;i<data.list.length;i++){
        		// 将时间戳转成时间
        		var date = new Date(data.list[i].createDate);
        		Y = date.getFullYear() + '-';
        		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        		D = date.getDate() + ' ';
        	    var clsbdh = data.list[i].clsbdh;
        	    clsbdh=(clsbdh==null)?"":clsbdh;        
        	    var cls=clsbdh.substring(clsbdh.length-7);
        	    var hbhms=(data.list[i].typeName =='注册登记')?cls:data.list[i].hphm;
        	    var auditerNameBase=data.list[i].auditerNameBase==null?"":data.list[i].auditerNameBase;
        	    var auditFlag=data.list[i].auditFlag;
        	    var auditName=auditFlag=='0'?"已通过":auditFlag=='1'?"未通过":auditFlag=='2'?"审核中":"";
        	    var zz="";
				if (auditFlag!='2'&&sfgly!='1') {
					zz="<td ><button  type='button' style='background-color:gray; ' onClick='jump("+data.list[i].srln+"," + data.list[i].rckCount+")'>审核</button></td>";
				}else{
					zz="<td ><button  type='button' onClick='jump("+data.list[i].srln+"," + data.list[i].rckCount+")'>审核</button></td>";
				}
        		if(i % 2 == 1){
					var tr="<tr   ondblclick='jump("+data.list[i].srln+"," + data.list[i].rckCount+")'class='rowbgcolor texcenter'>"
			         	+"<td >"+data.list[i].clsbdh+"</td>"
			         	+"<td>"+data.list[i].hphm+"</td>"
			         	+"<td>"+data.list[i].typeName+"</td>"
			         	+"<td>"+data.list[i].organName+"</td>"
			         	+"<td>"+toStr(data.list[i].operStatu)+"</td>"
			         	+"<td>"+Y+M+D+"</td>"
			         	+"<td>"+auditName+"</td>"
			         	+"<td>"+auditerNameBase+"</td>"
			         	+"<td>"+data.list[i].hpzlName+"</td>"
			         	+zz
			         	/* +"<td onClick='jump("+data.list[i].srln+","+data.list[i].rckCount+")' class='ztcolor texcenter'>详情</td>" */
			         	+"</tr>";
				}else{
					var tr="<tr ondblclick='jump("+data.list[i].srln+"," + data.list[i].rckCount+")'class='texcenter'>"
			         	+"<td >"+data.list[i].clsbdh+"</td>"
			         	+"<td>"+data.list[i].hphm+"</td>"
			         	+"<td>"+data.list[i].typeName+"</td>"
			         	+"<td>"+data.list[i].organName+"</td>"
			         	+"<td>"+toStr(data.list[i].operStatu)+"</td>"
			         	+"<td>"+Y+M+D+"</td>"
			         	+"<td>"+auditName+"</td>"
			         	+"<td>"+auditerNameBase+"</td>"
			         	+"<td>"+data.list[i].hpzlName+"</td>"
			         	+zz
			         	/* +"<td onClick='jump("+data.list[i].srln+","+data.list[i].rckCount+")' class='ztcolor texcenter'>详情</td>" */
			         	+"</tr>";
				}
        		// 添加tr
        		$("#myTbody").append(tr);
        	}
        	// 显示分页栏
        	$("#pp").show();
        	// 设置总条数
        	$("#pp").pagination({
       		  total:data.totalCount,
       		});
        }
    });
}


function upvideo(srln){
	var url="<%=request.getContextPath()%>/module/vehicleinfo/upvideo?srln="+srln;
	parent.addTab1("视频信息",url);
}
var time;
formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};
$(function(){
	$('#startTime').datebox('setValue', formatterDate(new Date()));
	$('#endTime').datebox('setValue', formatterDate(new Date()));
	search(1);
	shtj();
	
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
	setInterval("refreshPage()",1000);
})

var reTime=6;
function refreshPage(){
	reTime=reTime-1;
	var zddy=$('input:radio[name="zdsh"]:checked').val();
	
	$("#ts").html(reTime+"秒后自动刷新");
	if(reTime==0){
		//clearInterval(reInTime);
		if(zddy==1){
			zdsh();
			 shtj();
		}
		search(1);
		reTime=6;
	}
	
}
//查询
function zdsh(){
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var hphm = $("#hphm").textbox("getValue");
	var parentId = $("#mySelect").combobox('getValue');
	var operStatu=$("#operStatu").combobox('getValue');
	var clsbdh = $("#clsbdh").textbox("getValue");
	var auditFlag=$("#auditFlag").combobox('getValue');
	var hpzl=$("#hpzl").combobox("getValue");
	var pageNo = $('#pp').pagination('options').pageNumber;
	var pageSize= $('#pp').pagination('options').pageSize;
	var orangeCode= $("#chkpt").combobox('getValue');
	$.ajax({
        cache: true,
        type: "POST",
        url:'<%=request.getContextPath() %>/module/vehicle/zdsh',
        data:{
	        	/* srln:srln, */
	        	startTime:startTime,
	        	endTime:endTime,
	        	pageNo:pageNo,
	        	parentId:parentId,
	        	operStatu:operStatu,
	        	clsbdh:clsbdh,
	        	hphm:hphm,
	        	auditFlag:auditFlag,
	        	hpzl:hpzl,
	        	organCode:orangeCode
        	},
        async: false,
        error: function(request) {
            alert("系统操作繁忙请稍后！");
        },
        success: function(data) {
        	
        }
    });
}


function tj(){
	 var diag = new Dialog(); // 初始弹出层
	    diag.Width = 900; // 弹出层宽度 
	    diag.Height = 900; // 弹出层高度 
	    diag.Title = "当天审核量统计"; // 弹出层标题
	    diag.URL = "<%=request.getContextPath()%>/module/vehicle/shtj" // 引入修改页面
	    diag.show();
	//window.location.href="<%=request.getContextPath()%>/module/vehicle/shtj";
	
}
function shtj(){
	
	$.ajax({
        cache: true,
        type: "POST",
        url:'<%=request.getContextPath() %>/module/vehicle/shtj11',
        data:null,
        async: false,
        error: function(request) {
            alert("系统操作繁忙请稍后！");
        },
        success: function(data) {
        	$("#wshsl").html(data.wshsl);
        	$("#yshhsl").html(data.yshhsl);
        	$("#zsl").html(data.zsl);
        	
        }
    });
	
	
}


</script>
</html>
