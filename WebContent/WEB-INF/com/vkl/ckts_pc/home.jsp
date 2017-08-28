
<%@ page language="java" contentType="text/html; charset=utf-8"  import="com.vkl.ckts.common.entity.UserEntity,com.vkl.ckts.common.constr.Constrant,com.vkl.ckts.common.utils.Base64Utils"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	UserEntity user = (UserEntity)session.getAttribute(Constrant.S_USER_SESSION);
	if(user != null){
		user.setUserName(Base64Utils.decode(user.getUserName()));
		pageContext.setAttribute("userNamess", Base64Utils.decode(user.getUserName()));
		pageContext.setAttribute("sfgly", user.getSfgly());
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>智能查验监管系统 --- VKL2.01</title>
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
<script
	src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
<script
	src="<%=request.getContextPath()%>/static/js/zDialog.js"></script>
<script
	src="<%=request.getContextPath()%>/static/js/zDrag.js"></script>
<script
	src="<%=request.getContextPath()%>/static/js/clock.js"></script>
</head>
<!--因为a标签去掉了href无法显示鼠标悬浮样式，所以添加鼠标悬浮样式-->
<style type="text/css">
a:hover {
	cursor: pointer;
}
</style>
<script type="text/javascript">

function init(){
	$.ajax({
		url : '<%=request.getContextPath()%>/module/base/findmdxx',
		type : 'post',
		data : null,
		success : function(data){
			if(data=="no-login"){
        		window.location="<%=request.getContextPath() %>/login.jsp";
        	}else{
			$.each(data,function(index,dt){
				var menue; 
				if(1==dt.id){
					menue="<a href=\"#\" class=\"easyui-linkbutton topbtn l-btn l-btn-small\" style=\"width: 118px; margin-left: 5px; height: 30px;\" onclick=\"changemune2(\'"+dt.id+"\',\'"+dt.menuName+"\')\" group=\"\" id=\"\"><span class=\"l-btn-left\" style=\"margin-top: -1px;\"><span class=\"l-btn-text\"><span class=\"icon-yw\">"+dt.menuName+"</span></span></span></a>";
				}if(3==dt.id){
					menue="<a href=\"#\" class=\"easyui-linkbutton topbtn l-btn l-btn-small\" style=\"width: 118px; margin-left: 5px; height: 30px;\" onclick=\"changemune2(\'"+dt.id+"\',\'"+dt.menuName+"\')\" group=\"\" id=\"\"><span class=\"l-btn-left\" style=\"margin-top: -1px;\"><span class=\"l-btn-text\"><span class=\"icon-xt\">"+dt.menuName+"</span></span></span></a>";
				}
				if(5==dt.id){
					menue="<a href=\"#\" class=\"easyui-linkbutton topbtn l-btn l-btn-small\" style=\"width: 118px; margin-left: 5px; height: 30px;\" onclick=\"changemune2(\'"+dt.id+"\',\'"+dt.menuName+"\')\" group=\"\" id=\"\"><span class=\"l-btn-left\" style=\"margin-top: -1px;\"><span class=\"l-btn-text\"><span class=\"icon-tj\">"+dt.menuName+"</span></span></span></a>";
				}
				if(7==dt.id){
					menue="<a href=\"#\" class=\"easyui-linkbutton topbtn l-btn l-btn-small\" style=\"width: 118px; margin-left: 5px; height: 30px;\" onclick=\"changemune2(\'"+dt.id+"\',\'"+dt.menuName+"\')\" group=\"\" id=\"\"><span class=\"l-btn-left\" style=\"margin-top: -1px;\"><span class=\"l-btn-text\"><span class=\"icon-ba\">"+dt.menuName+"</span></span></span></a>";
				}
				if(44==dt.id){
					menue="<a href=\"#\" class=\"easyui-linkbutton topbtn l-btn l-btn-small\" style=\"width: 118px; margin-left: 5px; height: 30px;\" onclick=\"changemune2(\'"+dt.id+"\',\'"+dt.menuName+"\')\" group=\"\" id=\"\"><span class=\"l-btn-left\" style=\"margin-top: -1px;\"><span class=\"l-btn-text\"><span class=\"icon-ba\">"+dt.menuName+"</span></span></span></a>";
				}
				if(4==dt.id){
					$("#bdsz").show();
				}
				$("#zcd").append(menue);
				if(index==0){
					changemune2(dt.id,dt.menuName);
				}
			});
        	}
		}
	})
}

function changemune2(fcdid,title){
	 $(".panel-title").html(title);
	   $.ajax({
			url : '<%=request.getContextPath()%>/module/base/findzcdxx',
			type : 'post',
			data : {cdId:fcdid},
			success : function(data){
				if(data=="no-login"){
					window.location = "<%=request.getContextPath() %>/login.jsp"
	        	}else{
	        		$("#zhcd").empty();
					$.each(data,function(index,dts){
						var menue="<div class=\"meunbtn\" onclick=\'addTab(\""+dts.menuName+"\",\"<%=request.getContextPath()%>/"+ dts.menuUrl+"\")\'>"+dts.menuName+"</div>"
						$("#zhcd").append(menue);
					});
				
	        	}
			}
		})
}




</script>

<body class="easyui-layout">
	<!--头部-->
	<div data-options="region:'north',border:false"
		style="height:108px;background:url(<%=request.getContextPath()%>/static/images/new/headerbg.jpg) repeat-x; ">
		<div class="top">
			<div class="logo" style="width:500px;"></div>
			<div class="list" style="margin-left: 0px;">
				<div style="padding: 3px -3px;margin-left: -3px;">
					<ul>
						<li >
						
						<c:choose>
							<c:when test="${sfgly=='1' }">
							   <div class="list_home">超级管理员</div>
							</c:when>
						   <c:otherwise>
						      <div class="list_home">${sessionScope.userDeptName}</div>
						   </c:otherwise>
						</c:choose>
						</li>
						<li><div class="list_pople">
							<%-- ${userName} --%>
						</div></li>
						<li><div class="list_uppwd" onclick="uppwd()" style="cursor:pointer">修改密码</div></li>
						<li><div class="list_uppwd" onclick="sys()" style="cursor:pointer;display:none;" id="bdsz">本地设置</div></li>
						<li><div class="list_uppwd" onclick="downPlugin()" style="cursor:pointer">插件下载</div></li>
						<li><div class="list-date" id="clock"></div></li>
						<li><a id="exit" class="btnexit">退出</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="topmenu">
			<div style="margin: 2px 10px 0px" id="zcd">
				<!-- <a href="#" class="easyui-linkbutton topbtn "
					style="width: 120px; margin-left: 4px; height: 32px;"
					onclick="changemune(0,'系统设置')"><span class="icon-xt">系统设置</span></a>
				<a href="#" class="easyui-linkbutton topbtn"
					style="width: 120px; margin-left: 10px; height: 32px;"
					onclick="changemune(1,'业务管理')"><span class="icon-yw">业务管理</span></a>
				<a href="#" class="easyui-linkbutton topbtn"
					style="width: 120px; margin-left: 10px; height: 32px;"
					onclick="changemune(2,'统计查询')"><span class="icon-tj">统计查询</span></a>
				<a href="#" class="easyui-linkbutton topbtn"
					style="width: 120px; margin-left: 10px; height: 32px;"
					onclick="changemune(3,'备案管理')"><span class="icon-ba">备案管理</span></a>
				<div class="ycbj" onclick="addTab('异常查验处理','jjcl.html')">出现异常查验需要紧急处理！</div> -->
			</div>
		</div>
	</div>
	<!--左侧菜单开始-->
	<!--<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;"> </div>-->
	<div region="west" split="false" title="系统设置" class="menubg"
		style="width: 240px; padding: 10px; overflow: hidden;" id="left">

		<!--系统设置菜单开始-->
		<div id="zhcd">

			<%-- <div class="meunbtn" onclick="addTab('权限管理','')">权限管理</div>

			<div class="meunbtn" onclick="addTab('用户管理','<%=request.getContextPath()%>/userAdmin/index')">用户管理</div>

			<div class="meunbtn" onclick="addTab('角色管理','<%=request.getContextPath()%>/module/role/jsgl')">角色管理</div>
			<div class="meunbtn" onclick="addTab('部门管理','<%=request.getContextPath()%>/module/dept/bmgl')">部门管理</div>
			<div class="meunbtn" onclick="addTab('资料拍照设置','<%=request.getContextPath()%>/system/respro/highPicIndex')">资料拍照设置</div>

			<div class="meunbtn"
				onclick="addTab('查验拍照设置','<%=request.getContextPath()%>/system/photo/photoIndex')">查验拍照设置</div>

			<div class="meunbtn" onclick="addTab('查验项目设置','<%=request.getContextPath()%>/pc/check/ckCheckSetting')">查验项目设置</div>

			<div class="meunbtn" onclick="addTab('业务信息设置','<%=request.getContextPath()%>/system/operaSetting/operaIndex')">业务信息设置</div> --%>

		</div>
		<!--系统设置置菜单结束-->

		<%-- <!--业务管理菜单开始-->
		<div style="display: none;">

			<div class=" meunbtn"
				onclick="addTab('查验申请受理','<%=request.getContextPath()%>/module/servacpt/tableone')">查验受理</div>

			<div class=" meunbtn" onclick="addTab('车辆信息','<%=request.getContextPath()%>/module/vehicleinfo/loadlist')">车辆信息</div>

			<div class=" meunbtn" 
				onclick="addTab('查验审核','<%=request.getContextPath()%>/module/vehicle/loadview')">查验审核</div>

			<div class=" meunbtn" onclick="addTab('查验抽查','<%=request.getContextPath()%>/module/spotcheck/date')">查验抽查</div>

			<div class=" meunbtn" onclick="addTab('车辆黑名单','<%=request.getContextPath()%>/veh/blackName/blackIndex')">车辆黑名单</div>

		</div>
		<!--业务管理菜单结束-->

		<!--系统查询菜单开始-->
		<div style="display: none;">

			<div class="meunbtn" onclick="addTab('查验区合格率统计','<%=request.getContextPath()%>/count/check/checkCountIndex')">查验区合格率统计</div>

			<div class="meunbtn" onclick="addTab('查验员合格率统计','<%=request.getContextPath()%>/count/viewer/viewerIndex')">查验员合格率统计</div>

			<div class="meunbtn" onclick="addTab('外扩装置合格率统计','<%=request.getContextPath()%>/count/overall/overallIndex')">外廓装置合格率统计</div>

			<div class="meunbtn"
				onclick="addTab('异常处理','<%=request.getContextPath()%>/statistic/except/exceptIndex')">异常处理</div>

			<div class="meunbtn"
				onclick="addTab('操作日志','<%=request.getContextPath()%>/statistic/log/logIndex')">操作日志</div>

		</div>
		<!--系统查询菜单结束-->

		<!--备案管理菜单开始-->
		<div style="display: none;">
			<div class="meunbtn"
				onclick="addTab('查验区备案','<%=request.getContextPath()%>/module/chkpt/cyqba')">查验区备案</div>
			<div class="meunbtn"
				onclick="addTab('查验员备案','<%=request.getContextPath()%>/module/record/cyyba')">查验员备案</div>


			<div class="meunbtn" onclick="addTab('PDA设备备案','<%=request.getContextPath()%>/module/pda/pdaba')">PDA设备备案</div>

			<div class="meunbtn" onclick="addTab('外廓设备备案','<%=request.getContextPath()%>/module/gbr/gbrba')">外廓设备备案</div>
			
			<div class="meunbtn" onclick="addTab('整备质量备案','<%=request.getContextPath()%>/module/zbzl/zbzlba')">整备质量备案</div>

			<div class="meunbtn" onclick="addTab('上门查验备案','<%=request.getContextPath()%>/module/visitCk/smcyba')">上门查验备案</div>

			<div class="meunbtn" onclick="addTab('查验项备案','<%=request.getContextPath()%>/module/usde/cyxba')">查验项备案</div>

		</div> --%>
		<!--备案管理菜单结束-->

	</div>
	<!--左侧菜单结束-->
	<!--底部-->
	<div data-options="region:'south',border:false"
		style="height: 10px; background: #005aa9;"></div>
<!--内容部分-->
	<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs sybg"
			style="width: 100%; height: 100%; background-color: #eff7fe;">

		</div>


	</div>
</body>

<script>
$(function() {
    var clock = new Clock();
    clock.display(document.getElementById("clock"));
    init();
});
function changemune(index, title) {
    $(".panel-title").html(title);
    $("#left").children("div").hide();
    $("#left").children("div:eq(" + index + ")").show();
}
$(".meunbtn").click(function() {
    $(".meunbtn").css("background-image", "url(<%=request.getContextPath()%>/static/images/menubtn.png)");
    $(this).css("background-image", "url(<%=request.getContextPath()%>/static/images/menubtnav.png)");
});

//添加选项卡
function addTab(title, url) {
    if ($('#tt').tabs('exists', title)) {
        $('#tt').tabs('select', title);

    } else {
        var content = '<iframe scrolling="auto" frameborder="0"   src="' + url + '" style="width:100%;height:100%; "></iframe>';
        $('#tt').tabs('add', {
            title: title,
            content: content,
            closable: true
        });

    }
}

//添加选项卡
function addTab1(title, url) {
	 var content = '<iframe scrolling="auto" frameborder="0"   src="' + url + '" style="width:100%;height:100%; "></iframe>';
    if ($('#tt').tabs('exists', title)) {
        $('#tt').tabs('close', title);
        $('#tt').tabs('add', {
            title: title,
            content: content,
            closable: true
        });
    } 
    else {
        $('#tt').tabs('add', {
            title: title,
            content: content,
            closable: true
        });

    }
   
}





$("#exit").click(function() {
	var userId = '${userId}'
    $.messager.confirm("确认", '是否退出？',
    function(r) {
        if (r) {
            window.location.href = "<%=request.getContextPath()%>/module/login/exit?id="+userId;
        }
    });
});

function uppwd() {
    var diag = new Dialog(); // 初始弹出层
    diag.Width = 400; // 弹出层宽度 
    diag.Height = 260; // 弹出层高度 
    diag.Title = "修改密码"; // 弹出层标题
    diag.URL = "<%=request.getContextPath()%>/module/base/uppwd" // 引入修改页面
    diag.show(); // 弹出层显示
}


function sys() {
    var diag = new Dialog(); // 初始弹出层
    diag.Width = 400; // 弹出层宽度 
    diag.Height = 305; // 弹出层高度 
    diag.Title = "本地设置"; // 弹出层标题
    diag.URL = "<%=request.getContextPath()%>/module/base/sys" // 引入本地设置页面
    diag.show(); // 弹出层显示
}


function tabsClose(){  
    var tab=$('#tt').tabs('getSelected');//获取当前选中tabs  
    var index = $('#tt').tabs('getTabIndex',tab);//获取当前选中tabs的index  
    $('#tt').tabs('close',index);//关闭对应index的tabs  
} 



function loadVideo(organCode,checkLineId){
	var diag = new Dialog(); // 初始弹出层
   	diag.Width = 1014;        // 弹出层宽度 

	diag.Height = 900;       // 弹出层高度 

	diag.Title = "视频观看";     // 弹出层标题

	diag.URL = "<%=request.getContextPath()%>/module/nvrsetting/loadvideo?organCode="+organCode+"&checkLineId="+checkLineId;   

	diag.show();            //弹出层显示
}

function loadVideo2(url){
	var diag = new Dialog(); // 初始弹出层
   	diag.Width = 1014;        // 弹出层宽度 

	diag.Height = 900;       // 弹出层高度 

	diag.Title = "视频观看";     // 弹出层标题

	diag.URL = url;   

	diag.show();            //弹出层显示
}
function downPlugin() {
    var diag = new Dialog(); // 初始弹出层
    diag.Width = 300; // 弹出层宽度 
    diag.Height = 300; // 弹出层高度 
    diag.Title = "插件下载"; // 弹出层标题
    diag.URL = "<%=request.getContextPath()%>/module/base/downplugin" // 引入修改页面
    diag.show(); // 弹出层显示
}
function gaopai(srln){
	
	if(srln == undefined || srln.length <= 0 || srln == ""){
		
		 $.messager.alert("提示！", "请先进行业务受理打印");
	}else{
	
	var diag = new Dialog(); // 初始弹出层

   	diag.Width = 1500;        // 弹出层宽度 

	diag.Height = 900;       // 弹出层高度 

	diag.Title = "高拍仪";     // 弹出层标题

	diag.URL = "<%=request.getContextPath()%>/module/servacpt/gaopai?srln="+srln;   //引入高拍页面并传递流水号

	diag.show();            //弹出层显示
	
	}
}

function gaopai(srln,rckCount){
	
	if(srln == undefined || srln.length <= 0 || srln == ""){
		
		 $.messager.alert("提示！", "请先进行业务受理打印");
	}else{
	
	var diag = new Dialog(); // 初始弹出层

   	diag.Width = 850;        // 弹出层宽度 

	diag.Height = 630;       // 弹出层高度 

	diag.Title = "高拍仪";     // 弹出层标题

	diag.URL = "<%=request.getContextPath()%>/module/servacpt/gaopai?srln="+srln+"&rckCount="+rckCount;   //引入高拍页面并传递流水号

	diag.show();            //弹出层显示
	
	}
}
</script>

</html>