<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.vkl.ckts.common.entity.IdEntity"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>查验项备案申请</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="查验项备案申请">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc" ><span class="star">*</span>查验项目名称</TD>
             <TD ><select id="fileProName" data-options="editable:false,url:'<%=request.getContextPath()%>/module/usde/loadCkProject?proType=1&isEnable=2',valueField:'id',textField:'proName'" class="easyui-combobox" style="width:80%; height:30px;"></select></TD>
             <TD class="bgc"><span class="star">*</span>本地使用状态</TD><TD>
            <select class="easyui-combobox" id="localStatu" data-options="editable:false" panelmaxheight="auto" style="width: 80%; "> <option value="1">正常</option> <option value="2">暂停</option> </select> 
            </TD>
            <TD class="bgc"><span class="star">*</span>备案状态</TD><td>
			<select class="easyui-combobox" id="fileStatu" data-options="editable:false" panelmaxheight="auto" style="width: 80%;"> <option value="1">正常</option> <option value="2">停用</option> <option value="3">撤销</option> <option value="4">首次备案申请</option> <option value="5">已过有效期</option> </select>
            </td>
             </tr>
             <tr>
             <TD width="200" class="bgc" ><span class="star">*</span>使用性质</TD><TD colspan="5">
            <c:forEach items="${cehUsnrList}" var="cehUsnrEntity">
            	<input type="checkbox" name="usnr" value="${cehUsnrEntity.parentId}"/><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
            </c:forEach>
            </TD>
           </tr>
           <TR>
            <TD class="bgc" ><span class="star">*</span>可办理业务类型</TD><TD colspan="5">
            <c:forEach items="${operTypeList}" var="operTypeEntity">
            <input type="checkbox" name="operType" id="${operTypeEntity.id}" /><label style="padding-right:20px;" for="${operTypeEntity.id}" >${operTypeEntity.typeName}</label>
            </c:forEach>
            </TD>
            </TR>
            <TD class="bgc" ><span class="star">*</span>可查验车辆</TD>
            <TD colspan="5">
            <c:forEach items="${ckCllxList}" var="ckCllxEntity">
           	<input type="checkbox" name="ckVehs" id="${ckCllxEntity.parentCllx}" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
            </c:forEach>
            </TD>
           </TR>
          
           
           <TR>
            <TD class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton"  id="submit" data-options="iconCls:'icon-ok'"  onclick="submit();">提交</button>
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
    }
});

// 提交
function submit(){
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
	alert(fileProName+""+proId+""+localStatu+""+fileStatu+""+usnr+""+operType+""+ckVehs);
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
	$("#submit").attr("disabled",true);
	$.ajax({
		url:"<%=request.getContextPath() %>/module/usde/submit",
		data:{
			fileProName: fileProName,
			proId: proId,
			localStatu: localStatu,
			fileStatu: fileStatu,
			usnr: usnr.toString(),
			operType: operType.toString(),
			ckVehs: ckVehs.toString()
		},
		type:"post",
		success:function(result){
			if(result=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
        	// 设置按钮可点击
        	$("#submit").removeAttr("disabled");
			if(result.data=="true"){
				$.messager.confirm("提示！", "备案成功，是否继续备案？",
		                function(r) {
		                    if (r) {
		                    	window.location = '<%=request.getContextPath()%>/module/usde/cyxbasq';
		                    } else {
		                        window.location = '<%=request.getContextPath()%>/module/usde/cyxba';
		                    }
		                });
			}else{
				$.messager.alert("错误！",result.errorMsg+"，备案失败");
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