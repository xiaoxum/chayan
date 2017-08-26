<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>PDA备案申请</title>
<!-- 引入easyui框架样式表 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/easyui.css">
<!-- 引入图标样式表 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/icon.css">
<!-- 引入自定义样式表 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/css.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/demo.css">
<!-- 引入jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<!-- 扩展Jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/extJquery.js" charset="utf-8"></script>
<!-- 引入EasyUI -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
<!-- 引入EasyUI中文脚本 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
<!-- 自定义数据验证js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/custom-validate.js"></script>
</head>
<body>
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="PDA备案申请">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc"><span class="star">*</span>系统说明</TD><TD colspan="3"><input id="sysExp" class="easyui-textbox" style="width:90%;"/></TD>
           </tr>
           <TR>
            <TD class="bgc"><span class="star">*</span>mac地址</TD>
            
            <TD>
            <input id="deviceMac" class="easyui-textbox" style="width:80%;"/>
            
            </TD>
            <!-- <TD class="bgc"><span class="star">*</span>系统备案编号</TD><TD><input id="fileId" class="easyui-textbox" style="width:80%;"/></TD> -->
            <TD class="bgc"><span class="star">*</span>终端品牌型号</TD><TD><input id="deviceUt" class="easyui-textbox" style="width:80%;"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>系统名称</TD><TD><input id="sysName" class="easyui-textbox" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>操作系统</TD><TD><input id="operSys" class="easyui-textbox" style="width:80%;"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>系统版本</TD><TD><input id="verNo" class="easyui-textbox" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>开发单位</TD><TD><input id="deviceVender" class="easyui-textbox" style="width:80%;"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>验收时间</TD><TD><input id="caTime" class="easyui-datebox" data-options="editable:false " panelWidth="300" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>查验区序号范围</TD><TD>
            <select class="easyui-combobox"  data-options="url:'<%=request.getContextPath()%>/module/pda/loadChkpt',valueField:'organCode',textField:'organName'" id="serial" style="width:80%; height:30px;">
             </select></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>本地使用状态</TD><TD>
            <select class="easyui-combobox" id="localStatu" data-options="editable:false" panelmaxheight="100px" style="width: 80%; "> <option value="1">正常</option> <option value="2">暂停</option> </select>
            </TD>
            <TD class="bgc"><span class="star">*</span>备案状态</TD><td>
             <select class="easyui-combobox" id="fileStatu" data-options="editable:false"  style="width: 80%;"> <option value="1">正常</option> <option value="2">停用</option> <option value="3">撤销</option> <option value="4">首次备案申请</option> <option value="5">已过有效期</option> </select>
            </td>
          
           <TR>
          <!--  <TR>
            <TD class="bgc"><span class="star">*</span>mac地址</TD>
            
            <TD>
            <input id="deviceMac" class="easyui-textbox" style="width:80%;"/>
            
            </TD>
            <TD class="bgc">
            </td>
          
           <TR> -->
            <TD class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton" id="submit"  data-options="iconCls:'icon-ok'"  onclick="submit();">提交</button>
            <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-undo'"  onclick="cancel();">取消</button>
            </TD>
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">

$('#serial').combobox({
    // 渲染下拉列表添加复选框
    formatter: function(row) {
        return '<input id=' + row.organCode + ' type="checkbox"/><label  >'+ row.organCode +': ' + row.organName + '</label>';
    },
    onChange: function(newValue, oldValue) {
        // 如果下拉框中新的值大于旧的值表示差异的字符为选中的选项的id，否则为取消选中的id
        if (newValue.length > oldValue.length) {
            for (var i = 0; i < newValue.length; i++) {
                if (newValue[i] != oldValue[i]) {
                    $("#" + newValue[i]).prop("checked", true);
                    break;
                }
            }
        } else {
            for (var i = 0; i < oldValue.length; i++) {
                if (newValue[i] != oldValue[i]) {
                    $("#" + oldValue[i]).prop("checked", false);
                    break;
                }
            }
        }

    }
});

// 提交
function submit(){
	// 系统说明
	var sysExp = $("#sysExp").textbox("getValue");
	/* // 系统备案编号
	var fileId = $("#fileId").textbox("getValue"); */
	// 终端品牌型号
	var deviceUt = $("#deviceUt").textbox("getValue");
	// 系统名称
	var sysName = removeAllSpace($("#sysName").textbox("getValue"));
	// 操作系统
	var operSys = $("#operSys").textbox("getValue");
	// 系统版本
	var verNo = $("#verNo").textbox("getValue");
	// 开发单位
	var deviceVender = $("#deviceVender").textbox("getValue");
	// 验收时间
	var caTime = $("#caTime").datebox("getValue");
	// 查验区序号范围
	var serial = $("#serial").combobox("getValues");
	// 本地使用状态
	var localStatu = $("#localStatu").combobox("getValue");
	// 备案状态
	var fileStatu = $("#fileStatu").combobox("getValue");
	
	var deviceMac =  $("#deviceMac").textbox("getValue");
	
	if (validateEmpty(sysExp,"系统说明不可为空")) {
        return;
    }
	/* if (validateEmpty(fileId,"系统备案编号不可为空")) {
        return;
    } */
	if (validateEmpty(deviceUt,"终端品牌型号不可为空")) {
        return;
    }
	if (validateEmpty(sysName,"系统名称不可为空")) {
        return;
    }
	if (validateEmpty(operSys,"操作系统不可为空")) {
        return;
    }
	if (validateEmpty(verNo,"系统版本不可为空")) {
        return;
    }
	if (validateEmpty(deviceVender,"开发单位不可为空")) {
        return;
    }
	if (!checkRegExp(deviceVender, "isChinese", "","开发单位格式不正确")) {
        return;
    }
	if (validateEmpty(caTime,"验收时间不可为空")) {
        return;
    }
	if (validateEmpty(serial,"查验区序号范围不可为空")) {
        return;
    }
	if (validateEmpty(localStatu,"本地使用状态不可为空")) {
        return;
    }
	if (validateEmpty(fileStatu,"备案状态不可为空")) {
        return;
    }
	if (validateEmpty(deviceMac,"pda Mac地址不能为空")) {
        return;
    }
	// 设置按钮不可点击
	$("#submit").attr("disabled",true);
	$.ajax({
		url:"<%=request.getContextPath() %>/module/pda/submit",
		data:{
			sysExp: sysExp,
			/* fileId: fileId, */
			deviceUt: deviceUt,
			sysName: sysName,
			operSys: operSys,
			verNo: verNo,
			deviceVender: deviceVender,
			caTime: caTime,
			serial: serial.toString(),
			localStatu: localStatu,
			fileStatu: fileStatu,
			deviceMac:deviceMac
		},
		type:"post",
		success:function(result){
			
			if(result=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
        	// 设置按钮可点击
        	$("#submit").removeAttr("disabled");
			if(result.data=="success"){
				$.messager.confirm("提示！", "备案成功，是否继续备案？",
		                function(r) {
		                    if (r) {
		                    	window.location = '<%=request.getContextPath()%>/module/pda/pdabasq';
		                    } else {
		                        window.location = '<%=request.getContextPath()%>/module/pda/pdaba';
		                    }
		                });
			}else if(result.data=="already"){
				alert("当前手机已经备案，不能重复备案");
			}else{
				$.messager.alert("错误！",result.errorMsg+"，备案失败");
			}
		}
	})
	
}


//取消
function cancel() {
    window.location = '<%=request.getContextPath()%>/module/pda/pdaba';
}
</script>
</html>