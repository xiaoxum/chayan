<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>PDA备案修改</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="PDA备案修改">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc"><span class="star">*</span>系统说明</TD><TD colspan="3"><input id="sysExp" class="easyui-textbox" value="${pda.sysExp }" style="width:90%;"/></TD>
           </tr>
           <TR>
            <TD class="bgc"><span class="star">*</span>系统备案编号</TD><TD><input id="fileId" class="easyui-textbox" value="${pda.fileId }" style="width:80%;" readonly="readonly"/></TD>
            <TD class="bgc"><span class="star">*</span>终端品牌型号</TD><TD><input id="deviceUt" class="easyui-textbox" value="${pda.deviceUt }" style="width:80%;"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>系统名称</TD><TD><input id="sysName" class="easyui-textbox" value="${pda.sysName }" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>操作系统</TD><TD><input id="operSys" class="easyui-textbox" value="${pda.operSys }" style="width:80%;"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>系统版本</TD><TD><input id="verNo" class="easyui-textbox" value="${pda.verNo }" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>开发单位</TD><TD><input id="deviceVender" class="easyui-textbox" value="${pda.deviceVender }" style="width:80%;"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>验收时间</TD><TD><input id="caTime" class="easyui-datebox" data-options="editable:false " value='<fmt:formatDate value="${pda.caTime}"/>' panelWidth="300" style="width:80%;"/></TD>
           
            <TD class="bgc"><span class="star">*</span>查验区序号范围</TD><TD>
            <select class="easyui-combobox"   data-options="url:'<%=request.getContextPath()%>/module/pda/loadChkpt',valueField:'organCode',textField:'organName'" id="serial" style="width:80%; height:30px;">
             </select></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>本地使用状态</TD><TD>
            <select class="easyui-combobox" id="localStatu" data-options="editable:false" panelmaxheight="100px" style="width: 80%; ">
             			<c:forEach items="${requestScope.localStatu}" var="localStatu">
							<c:choose>
							<c:when test="${requestScope.pda.localStatu==localStatu.dictKey}">
							<option value="${localStatu.dictKey }" selected>${localStatu.ldictionaryAbel }</option>
							</c:when>
							<c:otherwise>
							<option value="${localStatu.dictKey }">${localStatu.ldictionaryAbel }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
             </select>
            </TD>
            <TD class="bgc"><span class="star">*</span>备案状态</TD><td>
             <select class="easyui-combobox" id="fileStatu" data-options="editable:false"  style="width: 80%;">
              			<c:forEach items="${requestScope.fileStatu}" var="fileStatu">
							<c:choose>
							<c:when test="${requestScope.pda.fileStatu==fileStatu.dictKey}">
							<option value="${fileStatu.dictKey }" selected>${fileStatu.ldictionaryAbel }</option>
							</c:when>
							<c:otherwise>
							<option value="${fileStatu.dictKey }">${fileStatu.ldictionaryAbel }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
              </select>
            </td>
          
           <TR>
            <TD class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton" id="update" data-options="iconCls:'icon-ok'"  onclick="updatePdaFile();">修改</button>
            <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-undo'"  onclick="cancel();">取消</button>
            </TD>
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">
var serial = '${pda.serial }';
$('#serial').combobox({
    // 渲染下拉列表添加复选框
    formatter: function(row) {
        // 设置默认选中项
        if (serial.indexOf(row.organCode) != -1 ) {
            return '<input id=' + row.organCode + ' type="checkbox" checked/><label  >' + row.organCode +': ' + row.organName + '</label>';
        }else{
        	return '<input id=' + row.organCode + ' type="checkbox"/><label  >'+ row.organCode +': ' + row.organName + '</label>';
        }	
    },
	// 加载完成后,设置选中
    onLoadSuccess: function() {   
        $(this).combobox("setValues", serial);
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

// 修改
function updatePdaFile(){
	// 系统说明
	var sysExp = $("#sysExp").textbox("getValue");
	// 系统备案编号
	var fileId = $("#fileId").textbox("getValue");
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
	if (validateEmpty(sysExp,"系统说明不可为空")) {
        return;
    }
	if (validateEmpty(fileId,"系统备案编号不可为空")) {
        return;
    }
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
    // 设置按钮不可点击
    $("#update").attr("disabled",true);
	$.ajax({
		url:"<%=request.getContextPath() %>/module/pda/updatePdaFile",
		data:{
			id: '${pda.id }',
			sysExp: sysExp,
			fileId: fileId,
			deviceUt: deviceUt,
			sysName: sysName,
			operSys: operSys,
			verNo: verNo,
			deviceVender: deviceVender,
			caTime: caTime,
			serial: serial.toString(),
			localStatu: localStatu,
			fileStatu: fileStatu
		},
		type:"post",
		error:function(result){
			
			
		},
		success:function(result){
			if(result=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
            // 设置按钮可以点击
            $("#update").removeAttr("disabled");
            // 取出结果中的空格
            result = $.trim(result);
            if (result == "true") {
                $.messager.confirm("提示", "修改成功",
                function(r) {
                    if (r){
                    	window.location = '<%=request.getContextPath()%>/module/pda/pdaba';
                    }else{
                    	window.location = '<%=request.getContextPath()%>/module/pda/pdaba';
                    }
                });
            } else {
                $.messager.confirm("提示", "修改失败",
                function(r) {
				});
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