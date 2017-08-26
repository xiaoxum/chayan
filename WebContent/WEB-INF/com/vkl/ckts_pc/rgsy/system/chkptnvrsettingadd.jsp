<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8">
<title>角色新增</title>
<!-- 引入easyui框架样式表 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/easyui.css">
<!-- 引入图标样式表 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/icon.css">
<!-- 引入自定义样式表 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/css.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/demo.css">
<!-- 引入jQuery -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<!-- 扩展Jquery -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/extJquery.js"
	charset="utf-8"></script>
<!-- 引入EasyUI -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
<!-- 引入EasyUI中文脚本 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
<!-- 自定义数据验证js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/custom-validate.js"></script>
</head>
<body>

	<div class="easyui-panel" style="width: 100%; height: auto;"
		title="视频配置">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<TD width="167" class="bgc"><span class="star">*</span>查验线</TD>
				<TD><input id="checkLineName" class="easyui-textbox"
					style="width: 200px;" value="${videoConfig.checkLineName}" /></TD>
				<TD class="bgc"><span class="star">*</span>所属查验区</TD>
				<TD><select class="easyui-combobox" panelmaxheight="auto"
					data-options="editable:false,url:'<%=request.getContextPath()%>/module/nvrsetting/loadChkpt',valueField:'organCode',textField:'organName'"
					id="organ" style="width: 60%; height: 30px;">
				</select></TD>
			</tr>
			<TR>
				<TD width="167" class="bgc"><span class="star">*</span>录像ip</TD>
				<TD><input id="nvrIp" class="easyui-textbox"
					style="width: 200px;" value="${videoConfig.nvrIp}" /></TD>
				<TD width="167" class="bgc"><span class="star">*</span>通道号</TD>
				<TD><input id="nvrChannel" class="easyui-textbox"
					style="width: 200px;" value="${videoConfig.nvrChannel}" /></TD>

			</TR>
			<TR>
				<TD width="167" class="bgc"><span class="star">*</span>用户名</TD>
				<TD><input id="nvrUser" class="easyui-textbox"
					style="width: 200px;" value="${videoConfig.nvrUser}" /></TD>
				<TD width="167" class="bgc"><span class="star">*</span>用户密码</TD>
				<TD><input id="nvrPwd" class="easyui-textbox"
					style="width: 200px;" value="${videoConfig.nvrPwd}" /></TD>
			</TR>
			
             <tr>
				<TD width="167" class="bgc"><span class="star">*</span>视频下载端口</TD>
				<TD ><input id="servPort" class="easyui-textbox"
					style="width: 200px;" value="${videoConfig.servPort}" /></TD>
				<TD width="167" class="bgc"><span class="star">*</span>视频下载通道号</TD>
				<TD ><input id="videoDownLoadChannel" class="easyui-textbox"
					style="width: 200px;" value="${videoConfig.videoDownLoadChannel}" /></TD>
			 </tr>
             <tr>
				<TD width="167" class="bgc"><span class="star">*</span>http端口</TD>
				<TD colspan="3"><input id="nvrPort" class="easyui-textbox"
					style="width: 200px;" value="${videoConfig.nvrPort}" /></TD>
			</tr>
			<TR>
				<TD class="bgc" colspan="6">
					<button type="button" class="easyui-linkbutton" id="submit"
						data-options="iconCls:'icon-ok'" onclick="submit();">提交</button>
					<button type="button" class="easyui-linkbutton"
						data-options="iconCls:'icon-undo'" onclick="cancel();">取消</button>
				</TD>

			</TR>
		</table>
		<input type="hidden" value="${videoConfig.checkLineId}"
			id="checkLineId" /> <input type="hidden"
			value="${videoConfig.organCode}" id="organCode" />
	</div>
</body>
<script type="text/javascript">
$(function() {
    var organ = $("#organCode").val();
    var permissionFlag='${requestScope.vfe.permissionFlag}';
    $("#organ").combobox({
    	// 加载完成后,设置选中第一项
        onLoadSuccess: function() {   
            $(this).combobox("setValues", organ);
        }
    });
});



function submit(){
	// 角色名称
	var checkLineName = removeAllSpace($("#checkLineName").textbox("getValue"));
	var nvrIp = removeAllSpace($("#nvrIp").textbox("getValue"));
	var nvrChannel = removeAllSpace($("#nvrChannel").textbox("getValue"));
	var nvrPort = removeAllSpace($("#nvrPort").textbox("getValue"));
	var nvrUser = removeAllSpace($("#nvrUser").textbox("getValue"));
	var nvrPwd = removeAllSpace($("#nvrPwd").textbox("getValue"));
	var checkLineId = removeAllSpace($("#checkLineId").val());
	var organCode = removeAllSpace($("#organ").textbox("getValue"));
	var servPort = removeAllSpace($("#servPort").textbox("getValue"));
	var videoDownLoadChannel = removeAllSpace($("#videoDownLoadChannel").textbox("getValue"));
	var url='<%=request.getContextPath()%>/module/nvrsetting/addchkptconfigdto';
	if(checkLineId!=null&&checkLineId!=""){
		url='<%=request.getContextPath()%>/module/nvrsetting/updatechkptconfig';
	}
	if (validateEmpty(checkLineName,"查验线名不可为空")) {
        return;
    }
	if (validateEmpty(nvrIp,"ip地址不可为空")) {
        return;
    }
	if (validateEmpty(nvrChannel,"通道号不可为空")) {
        return;
    }
	if (validateEmpty(nvrPort,"端口不可为空")) {
        return;
    }
	
	if (validateEmpty(nvrUser,"用户名不可为空")) {
        return;
    }
	if (validateEmpty(nvrPwd,"用户密码不可为空")) {
        return;
    }
	// 设置按钮不可点击
	$("#submit").attr("disabled",true);
	$.ajax({
		url:url,
		data:{
			checkLineName:checkLineName,
			nvrIp:nvrIp,
			nvrChannel:nvrChannel,
			nvrPort:nvrPort,
			nvrUser:nvrUser,
			nvrPwd:nvrPwd,
			checkLineId:checkLineId,
			organCode:organCode,
			videoDownLoadChannel:videoDownLoadChannel,
			servPort:servPort
		    },
		type:'post',
		success:function(result){
			// 设置按钮可点击
        	$("#submit").removeAttr("disabled");
			if("no-authority"==result){
				alert("当前用户没有权限");
			}
			if("sys-error"==result){
				alert("系统异常 ，请稍候再试");
			}
			if("success"==result){
				$.messager.confirm("提示！", "配置成功，是否继续配置视频信息？",
		                function(r) {
		                    if (r) {
		                    	window.location = '<%=request.getContextPath()%>/module/nvrsetting/toaddchkptnvrconfig';
		                    } else {
		                        window.location = '<%=request.getContextPath()%>/module/nvrsetting/tosettingpage';
		                    }
		                });
			}
		}
	});
}

//取消
function cancel() {
	window.location = '<%=request.getContextPath()%>/module/nvrsetting/tosettingpage';
}
</script>
</html>