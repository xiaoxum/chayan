<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.vkl.ckts.common.entity.IdEntity"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>上门查验备案修改</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="上门查验备案修改">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc"><span class="star">*</span>上门查验部门名称</TD><TD >
             <select class="easyui-combobox"  data-options="editable:false,url:'<%=request.getContextPath()%>/module/pda/loadChkpt',valueField:'organCode',textField:'organName'" id="ckDept" style="width:80%; height:30px;">
             </select></TD>
             <TD width="200" class="bgc"><span class="star">*</span>上门查验管理部门</TD><TD><input id="adminIntrName" data-options="editable:false" class="easyui-textbox" style="width:80%;"/></TD>
           </tr>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验员</TD><TD><input id="ckFileId" data-options="multiple:true,editable:false,valueField:'fileId',textField:'viewName'" class="easyui-textbox" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>上门查验服务单位名称</TD><TD><input id="serFileName" value="${visitCk.serFileName}"  class="easyui-textbox" style="width:80%;"/></TD>
           </TR>
          
           <TR>

            <TD class="bgc"><span class="star">*</span>备案状态</TD><td>
             <select class="easyui-combobox" id="fileStatu" data-options="editable:false"  style="width: 80%;"> 
						<c:forEach items="${requestScope.fileStatu}" var="fileStatu">
							<c:choose>
							<c:when test="${requestScope.visitCk.fileStatu==fileStatu.dictKey}">
							<option value="${fileStatu.dictKey }" selected>${fileStatu.ldictionaryAbel }</option>
							</c:when>
							<c:otherwise>
							<option value="${fileStatu.dictKey }">${fileStatu.ldictionaryAbel }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
             </select>
            </td>
           </TR>
            <TR>
            <TD class="bgc" >使用性质</TD><TD colspan="3">
            <c:forEach items="${requestScope.cehUsnrList}" var="cehUsnrEntity">
                  <c:choose>
            	<c:when test="${fn:contains(requestScope.visitCk.usnr,cehUsnrEntity.parentId)}">
            	<input type="checkbox" name="usnr" value="${cehUsnrEntity.parentId}" checked="checked"/><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
            	</c:when>
            	<c:otherwise>
            	<input type="checkbox" name="usnr" value="${cehUsnrEntity.parentId}"/><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
            	</c:otherwise>
            	</c:choose>

            </c:forEach>
            </TD>
            </TR>
            <TR>
            <TD class="bgc" >可查验车辆类型</TD><TD colspan="3">
            <c:forEach items="${ckCllxList}" var="ckCllxEntity">
            <c:choose>
            	<c:when test="${fn:contains(requestScope.visitCk.ckCllxs,ckCllxEntity.parentCllx)}">
            	<input type="checkbox" name="ckCllxs" id="${ckCllxEntity.parentCllx}" checked="checked" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
            	</c:when>
            	<c:otherwise>
            	<input type="checkbox" name="ckCllxs" id="${ckCllxEntity.parentCllx}" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
            	</c:otherwise>
            	</c:choose>
            </c:forEach>
            </TD>
           </TR>
           <tr>
            <TD class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton" id="update" data-options="iconCls:'icon-ok'"  onclick="update();">修改</button>
            <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-undo'"  onclick="cancel();">取消</button>
            </TD>
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">
$("#ckDept").combobox({
	// 加载完成后,设置选中
    onLoadSuccess: function() {   
        $(this).combobox("setValue",'${visitCk.ckDeptId}');
    },
	onSelect: function (row) {
		if(row != null) {
			$("#adminIntrName").textbox("setValue",row.parentDeptName);
			$("#ckFileId").combobox({
				url:"<%=request.getContextPath()%>/module/record/loadViewer?auditFlag=<%=IdEntity.AUDIT_FLAG_NORMAL%>&organ="+row.organCode
			});
		}
	}
});
var ckFileId = '${visitCk.ckFileId }';
$("#ckFileId").combobox({
    // 渲染下拉列表添加复选框
    formatter: function(row) {
    	 // 设置默认选中项
        if (ckFileId.indexOf(row.fileId) != -1) {
            return '<input id=' + row.fileId + ' type="checkbox" checked/><label  >'+ row.fileId +': ' + row.viewName + '</label>';
        }
        return '<input id=' + row.fileId + ' type="checkbox"/><label  >'+ row.fileId +': ' + row.viewName + '</label>';
    },
 	// 加载完成后,设置选中
    onLoadSuccess: function() { 
    	var ckDeptId = $("#ckDept").combobox("getValue");
 		if(ckDeptId == '${visitCk.ckDeptId}'){
 			$(this).combobox("setValues", ckFileId);
 		} 
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
function update(){
	// 上门查验部门名称
	var ckDeptName = $("#ckDept").combobox("getText");
	// 上门查验部门编号
	var ckDeptId = $("#ckDept").combobox("getValue");
	// 上门查验管理部门
	var adminIntrName = removeAllSpace($("#adminIntrName").textbox("getValue"));
	// 查验员编号
	var ckFileId = $("#ckFileId").combobox("getValues");
	// 上门查验服务单位名称
	var serFileName = $("#serFileName").textbox("getValue");
	// 备案状态
	var fileStatu = $("#fileStatu").combobox("getValue");
	// 使用性质
	var usnr = [];
	// 可查验车辆类型
	var ckCllxs = [];
	$("[name=usnr]:checked").each(function(){
		usnr.push($(this).val());
	});
	$("[name=ckCllxs]:checked").each(function(){
		ckCllxs.push($(this).attr("id"));
	});
	if (validateEmpty(ckDeptName,"上门查验部门名称不可为空")) {
        return;
    }
	if (validateEmpty(adminIntrName,"上门查验管理部门不可为空")) {
        return;
    }
	if (validateEmpty(ckFileId,"查验员不可为空")) {
        return;
    }
	if (validateEmpty(serFileName,"上门查验服务单位名称不可为空")) {
        return;
    }
	if (validateEmpty(fileStatu,"备案状态不可为空")) {
        return;
    }
	if (validateEmpty(usnr,"使用性质不可为空")) {
        return;
    }
	if (validateEmpty(ckCllxs,"可查验车辆类型不可为空")) {
        return;
    }
    // 设置按钮不可点击
    $("#update").attr("disabled",true);
	$.ajax({
		url:"<%=request.getContextPath() %>/module/visitCk/updateVisitCkFile",
		data:{
			id: '${visitCk.id}',
			ckDeptName: ckDeptName,
			ckDeptId: ckDeptId,
			adminIntrName: adminIntrName,
			ckFileId: ckFileId.toString(),
			serFileName: serFileName,
			fileStatu: fileStatu,
			usnr: usnr.toString(),
			ckCllxs: ckCllxs.toString()
		},
		type:"post",
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
                    	window.location = '<%=request.getContextPath()%>/module/visitCk/smcyba';
                    }else{
                    	window.location = '<%=request.getContextPath()%>/module/visitCk/smcyba';
                    }
                });
            } else {
                $.messager.confirm("提示", "修改失败",
                function(r) {
				});
            }
		}
	});
	
}


//取消
function cancel() {
    window.location = '<%=request.getContextPath()%>/module/visitCk/smcyba';
}
</script>
</html>