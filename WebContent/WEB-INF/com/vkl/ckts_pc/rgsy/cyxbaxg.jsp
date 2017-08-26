<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.vkl.ckts.common.entity.IdEntity"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>查验项备案修改</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="查验项备案修改">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc" ><span class="star">*</span>查验项目名称</TD>
             <TD ><select id="fileProName" data-options="editable:false,url:'<%=request.getContextPath()%>/module/usde/loadUpdateCkProject?id=${usde.proId }&proType=1&isEnable=2',valueField:'id',textField:'proName'" class="easyui-combobox" style="width:80%; height:30px;"></select></TD>
             <TD class="bgc"><span class="star">*</span>本地使用状态</TD><TD>
            <select class="easyui-combobox" id="localStatu" data-options="editable:false" panelMaxHeight="100px"   style="width: 80%; ">
						<c:forEach items="${requestScope.localStatu}" var="localStatu">
							<c:choose>
							<c:when test="${requestScope.usde.localStatu==localStatu.dictKey}">
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
             <select class="easyui-combobox" id="fileStatu" data-options="editable:false" panelMaxHeight="100px" style="width: 80%;">
						<c:forEach items="${requestScope.fileStatu}" var="fileStatu">
							<option value="${fileStatu.dictKey }" ${requestScope.usde.fileStatu==fileStatu.dictKey?'selected=selected':''} >${fileStatu.ldictionaryAbel }</option>
						</c:forEach>
				</select>
            </td>
             </tr>
             <tr>
             <TD width="200" class="bgc" ><span class="star">*</span>使用性质</TD><TD colspan="5">
            <c:forEach items="${cehUsnrList}" var="cehUsnrEntity">
            	<c:choose>
            	<c:when test="${fn:contains(requestScope.usde.usnr, cehUsnrEntity.parentId)}">
            	<input type="checkbox" name="usnr" value="${cehUsnrEntity.parentId}" checked="checked" /><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
            	</c:when>
            	<c:otherwise>
            	<input type="checkbox" name="usnr" value="${cehUsnrEntity.parentId}" /><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
            	</c:otherwise>
            	</c:choose>
            </c:forEach>
            </TD>
           </tr>
           <TR>
            <TD class="bgc" ><span class="star">*</span>可办理业务类型</TD><TD colspan="5">
            <c:forEach items="${operTypeList}" var="operTypeEntity">
            	<c:choose>
            	<c:when test="${fn:contains(requestScope.usde.operType,operTypeEntity.id)}">
            	<input type="checkbox" name="operType" id="${operTypeEntity.id}" checked="checked"/><label style="padding-right:20px;" for="${operTypeEntity.id}" >${operTypeEntity.typeName}</label>
            	</c:when>
            	<c:otherwise>
            	<input type="checkbox" name="operType" id="${operTypeEntity.id}" /><label style="padding-right:20px;" for="${operTypeEntity.id}" >${operTypeEntity.typeName}</label>
            	</c:otherwise>
            	</c:choose>
            </c:forEach>
            </TD>
            </TR>
            <TD class="bgc" ><span class="star">*</span>可查验车辆</TD>
            <TD colspan="5">
            <c:forEach items="${ckCllxList}" var="ckCllxEntity">
            <c:choose>
            	<c:when test="${fn:contains(requestScope.usde.ckVehs,ckCllxEntity.parentCllx)}">
            	<input type="checkbox" name="ckVehs" id="${ckCllxEntity.parentCllx}" checked="checked" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
            	</c:when>
            	<c:otherwise>
            	<input type="checkbox" name="ckVehs" id="${ckCllxEntity.parentCllx}" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
            	</c:otherwise>
            	</c:choose>
            </c:forEach>
            </TD>
           </TR>
          
           
           <TR>
            <TD class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton" id="update" data-options="iconCls:'icon-ok'"  onclick="update();">修改</button>
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'"  onclick="cancel();">取消</button>
            </TD>
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">
$('#fileProName').combobox({
    // 渲染下拉列表添加复选框
    formatter: function(row) {
        return '<label  >'+ row.id +': ' + row.proName + '</label>';
    },
 	// 加载完成后,设置选中
    onLoadSuccess: function() {   
    	$(this).combobox("setValue", '${usde.proId}');
        $(this).combobox("setText", '${usde.fileProName}');
    }
});

// 修改
function update(){
	// 项目名称
	var fileProName = removeAllSpace($("#fileProName").combobox("getText"));
	// 项目序号
	var proId = $("#fileProName").combobox("getValue");
    // 本地使用状态
    var localStatu = $("#localStatu").combobox("getValue");
	// 备案状态
	var fileStatu = $("#fileStatu").combobox("getValue");
	// 查验使用性质
	var usnr=[];
    // 可办理业务类型
	var operType=[];
	// 可查验车辆
	var ckVehs=[];
	$("input[name=usnr]:checked").each(function(){
		usnr.push($(this).val());
	});
	$("input[name=operType]:checked").each(function(){
		operType.push($(this).attr("id"));
	});
	$("input[name=ckVehs]:checked").each(function(){
		ckVehs.push($(this).attr("id"));
	});
	if (validateEmpty(fileProName,"项目名称不可为空")) {
        return;
    }
	if (validateEmpty(localStatu,"本地使用状态不可为空")) {
        return;
    }
	if (validateEmpty(fileStatu,"备案状态不可为空")) {
        return;
    }
	if (validateEmpty(usnr,"查验使用性质不可为空")) {
        return;
    }
	if (validateEmpty(operType,"可办理业务类型不可为空")) {
        return;
    }
	if (validateEmpty(ckVehs,"可查验车辆不可为空")) {
        return;
    }
    // 设置按钮不可点击
    $("#update").attr("disabled",true);
	$.ajax({
		url:"<%=request.getContextPath() %>/module/usde/updateUsdeFile",
		data:{
			fileProName: fileProName,
			proId: proId,
			localStatu: localStatu,
			fileStatu: fileStatu,
			usnr: usnr.toString(),
			operType: operType.toString(),
			ckVehs: ckVehs.toString(),
			id: '${usde.id}'
		},
		type:"post",
		success:function(result){
			if(result=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
            // 设置按钮可以点击
            $("#update").removeAttr("disabled");
			if($.trim(result)=="true"){
				$.messager.confirm("提示", "修改成功",
		                function(r) {
		                    if (r){
		                    	window.location = '<%=request.getContextPath()%>/module/usde/cyxba';
		                    }else{
		                    	window.location = '<%=request.getContextPath()%>/module/usde/cyxba';
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
// 取消
function cancel() {
    window.location = '<%=request.getContextPath()%>/module/usde/cyxba';
}
</script>
</html>