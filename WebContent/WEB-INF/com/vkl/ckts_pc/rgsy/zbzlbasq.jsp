<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>整备质量备案申请</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="整备质量备案申请">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc"><span class="star">*</span>备案编号</TD><TD >
             <input id="fileId" class="easyui-textbox" style="width:80%;"/>
             </TD>
             <TD width="200" class="bgc"><span class="star">*</span>生产企业</TD><TD><input class="easyui-textbox" id="clyscqy" style="width:80%;"/></TD>
           </tr>
           <TR>
            <TD class="bgc"><span class="star">*</span>型号</TD><TD><input id="clyxh" class="easyui-textbox" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>检定有效期止</TD><TD><input id="clyjdyxqz" data-options="editable:false" class="easyui-datebox" style="width:80%;"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>设备编号</TD><TD><input id="clybh" class="easyui-textbox" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>启用时间</TD><TD><input id="clyqysj" data-options="editable:false" class="easyui-datebox" style="width:80%;"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>设备检定/校准证书编号</TD><TD><input id="clyjdzsbh" class="easyui-textbox" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>经办人</TD><TD><input id="jbr" class="easyui-textbox" style="width:80%;"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>测量范围上限值</TD><TD><input id="clfwsx" class="easyui-textbox" style="width:80%;"/><label>KG</label></TD>
            <TD class="bgc"><span class="star">*</span>测量范围下限值</TD><TD><input id="clfwxx" class="easyui-textbox" style="width:80%;"/><label>KG</label></TD>
           </TR>
           
           <TR>
            <TD class="bgc"><span class="star">*</span>本地使用状态</TD><TD><select class="easyui-combobox" id="localStatu" data-options="editable:false" panelmaxheight="auto" style="width: 80%; "> <option value="1">正常</option> <option value="2">暂停</option> </select> </TD>
            <TD class="bgc"><span class="star">*</span>备案状态</TD><td >
             <select class="easyui-combobox" id="fileStatu" data-options="editable:false" panelmaxheight="auto" style="width: 80%;"> <option value="1">正常</option> <option value="2">停用</option> <option value="3">撤销</option> <option value="4">首次备案申请</option> <option value="5">已过有效期</option> </select>
            </td>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>管理部门</TD><TD><select class="easyui-combotree" url="<%=request.getContextPath()%>/module/dept/findAllDeptList?parentId=0" id="glbm" style="width:80%;"></select></TD>
            <TD class="bgc"></TD><TD></TD>
           </TR>
           <TR>
            <TD class="bgc" rowspan="2"><span class="star">*</span>可查验车辆类型</TD><TD colspan="3" rowspan="2">
            <c:forEach items="${ckCllxList}" var="ckCllxEntity">
           	<input type="checkbox" name="cycllx" id="${ckCllxEntity.parentCllx}" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
            </c:forEach>
            </TD>
            </TR>
            <TR>
            </TR>
           <TR>
            <TD class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton" id="submit" data-options="iconCls:'icon-ok'"  onclick="submit();">提交</button>
            <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-undo'"  onclick="cancel();">取消</button>
            </TD>
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">
$(function(){
	// 这是只允许选择今后的日期
	$('#clyjdyxqz').datebox('calendar').calendar({
	    validator: function(date){
	        var now = new Date();
	        var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
	        return d1<=date ;
	    }
	});
});

//提交
function submit(){
	// 查验装置备案编号
	var fileId = $("#fileId").textbox("getValue");
	// 生产企业
	var clyscqy = removeAllSpace($("#clyscqy").textbox("getValue"));
	// 型号
	var clyxh = $("#clyxh").textbox("getValue");
	// 检定有效期止
	var clyjdyxqz = $("#clyjdyxqz").datebox("getValue");
	// 设备编号
	var clybh = $("#clybh").textbox("getValue");
	// 启用时间
	var clyqysj = $("#clyqysj").datebox("getValue");
	// 设备检定/校准证书编号
	var clyjdzsbh = $("#clyjdzsbh").textbox("getValue");
	// 经办人
	var jbr = removeAllSpace($("#jbr").textbox("getValue"));
	// 测量范围上限值
	var clfwsx = $("#clfwsx").textbox("getValue");
	// 测量范围下限值
	var clfwxx = $("#clfwxx").textbox("getValue");
	// 本地使用状态
	var localStatu = $("#localStatu").combobox("getValue");
	// 备案状态
	var fileStatu = $("#fileStatu").combobox("getValue");
	// 管理部门
	var glbm = $("#glbm").combobox("getValue");
	// 可查验车辆
	var cycllx=[];
	$("input[name=cycllx]:checked").each(function(){
		cycllx.push($(this).attr("id"));
	});
	if (validateEmpty(fileId,"备案编号不可为空")) {
        return;
    }
	if (validateEmpty(clyscqy,"生产企业不可为空")) {
        return;
    }
	if (validateEmpty(clyxh,"型号不可为空")) {
        return;
    }
	if (validateEmpty(clyjdyxqz,"检定有效期止不可为空")) {
        return;
    }
	if (validateEmpty(clybh,"设备编号不可为空")) {
        return;
    }
	if (validateEmpty(clyqysj,"启用时间不可为空")) {
        return;
    }
	if (validateEmpty(clyjdzsbh,"设备检定/校准证书编号不可为空")) {
        return;
    }
	if (validateEmpty(jbr,"经办人不可为空")) {
        return;
    }
	if (!checkRegExp(jbr, "isChineseName", "","经办人格式不正确")) {
        return;
    }
	if (validateEmpty(clfwsx,"测量范围上限值不可为空")) {
        return;
    }
	if (!checkRegExp(clfwsx, "twoDecimal", "","测量范围上限值格式不正确")) {
        return;
    }
	if (validateEmpty(clfwxx,"测量范围下限值不可为空")) {
        return;
    }
	if (!checkRegExp(clfwxx, "twoDecimal", "","测量范围下限值格式不正确")) {
        return;
    }
	if (validateEmpty(localStatu,"本地使用状态不可为空")) {
        return;
    }
	if (validateEmpty(fileStatu,"备案状态不可为空")) {
        return;
    }
	if (validateEmpty(glbm,"管理部门不可为空")) {
        return;
    }
	if (validateEmpty(cycllx,"可查验车辆不可为空")) {
        return;
    }
	// 设置按钮不可点击
	$("#submit").attr("disabled",true);
	$.ajax({
		url:"<%=request.getContextPath() %>/module/zbzl/submit",
		data:{
			fileId: fileId,
			clyscqy: clyscqy,
			clyxh: clyxh,
			clyjdyxqz: new Date(clyjdyxqz.replace(/-/,"/")),
			clybh: clybh,
			clyqysj: new Date(clyqysj.replace(/-/,"/")),
			clyjdzsbh: clyjdzsbh,
			jbr: jbr,
			clfwsx: clfwsx,
			clfwxx: clfwxx,
			localStatu: localStatu,
			fileStatu: fileStatu,
			glbm: glbm,
			cycllx: cycllx.toString()
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
		                    	window.location = '<%=request.getContextPath()%>/module/zbzl/zbzlbasq';
		                    } else {
		                        window.location = '<%=request.getContextPath()%>/module/zbzl/zbzlba';
		                    }
		                });
			}else{
				$.messager.alert("错误！",result.errorMsg+"，备案失败");
			}
		}
	})
	
}


//取消
function cancel() {
    window.location = '<%=request.getContextPath()%>/module/zbzl/zbzlba';
}
</script>
</html>