<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>外廓备案申请</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="外廓备案申请">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc"><span class="star">*</span>查验装置备案编号</TD><TD >
             <input id="fileId" class="easyui-textbox" style="width:80%;"/>
             </TD>
             <TD width="200" class="bgc"><span class="star">*</span>最大测量长度</TD><TD><input class="easyui-textbox" id="meaLg" style="width:80%;"/><label >米</label></TD>
           </tr>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验装置生产企业</TD><TD><input id="meaFact" class="easyui-textbox" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>最大测量宽度</TD><TD><input id="meaWg" class="easyui-textbox" style="width:80%;"/><label >米</label></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验装置型号</TD><TD><input id="deviceType" class="easyui-textbox" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>最大测量高度</TD><TD><input id="meaHt" class="easyui-textbox" style="width:80%;"/><label >米</label></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>本地使用状态</TD><TD><select class="easyui-combobox" id="localStatu" data-options="editable:false" panelmaxheight="auto" style="width: 80%; "> <option value="1">正常</option> <option value="2">暂停</option> </select> </TD>
            <TD class="bgc"><span class="star">*</span>备案状态</TD><td >
             <select class="easyui-combobox" id="fileStatu" data-options="editable:false" panelmaxheight="auto" style="width: 80%;"> <option value="1">正常</option> <option value="2">停用</option> <option value="3">撤销</option> <option value="4">首次备案申请</option> <option value="5">已过有效期</option> </select>
            </td>
           </TR>
           <TR>
            <TD class="bgc" rowspan="2"><span class="star">*</span>可查验车辆类型</TD><TD colspan="3" rowspan="2">
            <c:forEach items="${ckCllxList}" var="ckCllxEntity">
           	<input type="checkbox" name="ckVehs" id="${ckCllxEntity.parentCllx}" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
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
//提交
function submit(){
	// 查验装置备案编号
	var fileId = $("#fileId").textbox("getValue");
	// 测量范围（长）
	var meaLg = $("#meaLg").textbox("getValue");
	// 查验装置生产企业
	var meaFact = removeAllSpace($("#meaFact").textbox("getValue"));
	// 测量范围（宽）
	var meaWg = $("#meaWg").textbox("getValue");
	// 查验装置型号
	var deviceType = $("#deviceType").textbox("getValue");
	// 测量范围（高）
	var meaHt = $("#meaHt").textbox("getValue");
	// 本地使用状态
	var localStatu = $("#localStatu").combobox("getValue");
	// 备案状态
	var fileStatu = $("#fileStatu").combobox("getValue");
	// 可查验车辆
	var ckVehs=[];
	$("input[name=ckVehs]:checked").each(function(){
		ckVehs.push($(this).attr("id"));
	});
	if (validateEmpty(fileId,"备案编号不可为空")) {
        return;
    }
	if (validateEmpty(meaLg,"最大测量长度不可为空")) {
        return;
    }
	if (!checkRegExp(meaLg, "twoDecimal", "","测量长度格式不正确")) {
        return;
    }

	if (validateEmpty(meaFact,"生产企业不可为空")) {
        return;
    }
	if (validateEmpty(meaWg,"最大测量宽度不可为空")) {
        return;
    }
	if (!checkRegExp(meaWg, "twoDecimal", "","测量宽度格式不正确")) {
        return;
    }

	if (validateEmpty(deviceType,"装置型号不可为空")) {
        return;
    }
	if (validateEmpty(meaHt,"最大测量高度不可为空")) {
        return;
    }
	if (!checkRegExp(meaHt, "twoDecimal", "","测量高度格式不正确")) {
        return;
    }

	if (validateEmpty(localStatu,"本地使用状态不可为空")) {
        return;
    }
	if (validateEmpty(fileStatu,"备案状态不可为空")) {
        return;
    }
	if (validateEmpty(ckVehs,"可查验车辆不可为空")) {
        return;
    }
	// 设置按钮不可点击
	$("#submit").attr("disabled",true);
	$.ajax({
		url:"<%=request.getContextPath() %>/module/gbr/submit",
		data:{
			fileId: fileId,
			meaLg: meaLg,
			meaFact: meaFact,
			meaWg: meaWg,
			deviceType: deviceType,
			meaHt: meaHt,
			localStatu: localStatu,
			fileStatu: fileStatu,
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
		                    	window.location = '<%=request.getContextPath()%>/module/gbr/gbrbasq';
		                    } else {
		                        window.location = '<%=request.getContextPath()%>/module/gbr/gbrba';
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
    window.location = '<%=request.getContextPath()%>/module/gbr/gbrba';
}
</script>
</html>