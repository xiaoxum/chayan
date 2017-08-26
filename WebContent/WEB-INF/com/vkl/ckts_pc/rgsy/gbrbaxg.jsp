<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>外廓备案修改</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="外廓备案修改">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc"><span class="star">*</span>查验装置备案编号</TD><TD >
             <input id="fileId" value='${gbr.fileId}' class="easyui-textbox" style="width:80%;"/>
             </TD>
             <TD width="200" class="bgc"><span class="star">*</span>最大测量长度</TD><TD><input value='${gbr.meaLg}' class="easyui-textbox" id="meaLg" style="width:80%;"/><label >米</label></TD>
           </tr>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验装置生产企业</TD><TD><input id="meaFact" value='${gbr.meaFact}' class="easyui-textbox" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>最大测量宽度</TD><TD><input id="meaWg" value='${gbr.meaWg}' class="easyui-textbox" style="width:80%;"/><label >米</label></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验装置型号</TD><TD><input id="deviceType" value='${gbr.deviceType}' class="easyui-textbox" style="width:80%;"/></TD>
            <TD class="bgc"><span class="star">*</span>最大测量高度</TD><TD><input id="meaHt"  value='${gbr.meaHt}' class="easyui-textbox" style="width:80%;"/><label >米</label></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>本地使用状态</TD><TD><select class="easyui-combobox" id="localStatu" data-options="editable:false" panelmaxheight="auto" style="width: 80%; ">
				<c:forEach items="${requestScope.localStatu}" var="localStatu">
					<c:choose>
					<c:when test="${requestScope.gbr.localStatu==localStatu.dictKey}">
					<option value="${localStatu.dictKey }" selected>${localStatu.ldictionaryAbel }</option>
					</c:when>
					<c:otherwise>
					<option value="${localStatu.dictKey }">${localStatu.ldictionaryAbel }</option>
					</c:otherwise>
					</c:choose>
				</c:forEach>
              </select> </TD>
            <TD class="bgc"><span class="star">*</span>备案状态</TD><td >
             <select class="easyui-combobox" id="fileStatu" data-options="editable:false" panelmaxheight="auto" style="width: 80%;">
				<c:forEach items="${requestScope.fileStatu}" var="fileStatu">
							<option value="${fileStatu.dictKey }" ${requestScope.gbr.fileStatu==fileStatu.dictKey?'selected=selected':''} >${fileStatu.ldictionaryAbel }</option>
				</c:forEach>
              </select>
            </td>
           </TR>
           <TR>
            <TD class="bgc" rowspan="2"><span class="star">*</span>可查验车辆类型</TD><TD colspan="3" rowspan="2">
            <c:forEach items="${ckCllxList}" var="ckCllxEntity">
            <c:choose>
            	<c:when test="${fn:contains(requestScope.gbr.ckVehs,ckCllxEntity.parentCllx)}">
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

            </TR>
           
          
           <TR>
            <TD class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton" id="update"  data-options="iconCls:'icon-ok'"  onclick="updateGbrFile();">修改</button>
            <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-undo'"  onclick="cancel();">取消</button>
            </TD>
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">
//提交
function updateGbrFile(){
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
    $("#update").attr("disabled",true);
	$.ajax({
		url:"<%=request.getContextPath() %>/module/gbr/updateGbrFile",
		data:{
			id: '${gbr.id }',
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
            // 设置按钮可以点击
            $("#update").removeAttr("disabled");
            // 取出结果中的空格
            result = $.trim(result);
			if (result == "true") {
                $.messager.confirm("提示", "修改成功",
                function(r) {
                    if (r){
                    	window.location = '<%=request.getContextPath()%>/module/gbr/gbrba';
                    }else{
                    	window.location = '<%=request.getContextPath()%>/module/gbr/gbrba';
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
    window.location = '<%=request.getContextPath()%>/module/gbr/gbrba';
}
</script>
</html>