<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>查验员备案修改</title>
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
<div class="easyui-panel" style="width:100%; height:auto;" title="查验员备案修改">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc"><span class="star">*</span>查验员姓名</TD><TD ><input class="easyui-textbox" id="viewName" style="width: 30%;" value="${requestScope.vfe.viewName}"/></TD>
             <TD width="200" class="bgc"><span class="star">*</span>是否民警</TD><TD>
				<c:forEach items="${requestScope.isPolice}" var="isPolice">
					<c:choose>
						<c:when test="${requestScope.vfe.isPolice eq isPolice.dictKey}">
						<input type="radio" name="isPolice" id="isPolice" value="${isPolice.dictKey }"  checked="checked" >
						<label style="padding-right: 10px;" for="isPolice">${isPolice.ldictionaryAbel }</label>
						</c:when>
						<c:otherwise>
						<input type="radio" name="isPolice" id="noPolice" value="${isPolice.dictKey}" >
						<label style="padding-right: 10px;" for="noPolice">${isPolice.ldictionaryAbel }</label>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</TD>
           </tr>
           
           <TR>
            <TD class="bgc"><span class="star">*</span>身份证号码</TD><TD><input class="easyui-textbox" value="${requestScope.vfe.identity}" style="width: 50%;" id="identity"/></TD>
            <TD class="bgc"><span class="star">*</span>警员编号</TD><TD><c:if test="${requestScope.vfe.isPolice eq '0'}">
					<input class="easyui-textbox" style="width: 60%;" data-options="editable:false" value="${requestScope.vfe.policeId}" id="policeId"/>
					</c:if>
					<c:if test="${requestScope.vfe.isPolice eq '1'}">
					<input class="easyui-textbox" style="width: 60%;" value="${requestScope.vfe.policeId}" id="policeId"/>
					</c:if>
				</TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>资格证发放单位</TD><TD><input id="validIntrName" class="easyui-textbox" value="${requestScope.vfe.validIntrName}" style="width:60%;"/></TD>
             <TD class="bgc"><span class="star">*</span>查验资格证编号</TD><TD><input class="easyui-textbox" style="width: 60%;" value="${requestScope.vfe.validId}" id="validId" /></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验员等级</TD>
            <TD><select class="easyui-combobox" id="viewerRank" data-options="editable:false" panelMaxHeight="100px"  style="width: 30%;">
						<c:forEach items="${requestScope.viewerRank}" var="viewerRank">
							<c:choose>
							<c:when test="${requestScope.vfe.viewerRank==viewerRank.dictKey}">
							<option value="${viewerRank.dictKey }" selected>${viewerRank.ldictionaryAbel }</option>
							</c:when>
							<c:otherwise>
							<option value="${viewerRank.dictKey }">${viewerRank.ldictionaryAbel }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></TD>
            <TD class="bgc"><span class="star">*</span>资格证有效期止</TD><TD><input id="validEndTime" data-options="editable:false" class="easyui-datebox" value="<fmt:formatDate value="${requestScope.vfe.validEndTime}" pattern="yyyy-MM-dd" />" style="width:60%;"/></TD>
           </TR>
           <tr>
            <TD width="200" class="bgc"><span class="star">*</span>用户名 : </TD><TD ><input class="easyui-textbox" id="loginName" style="width: 30%;" id="loginName" value="${requestScope.vfe.loginName}" readonly="readonly"/></TD>
            <TD class="bgc"><span class="star">*</span>备案状态</TD><td>
            <select class="easyui-combobox" id="fileStatu" data-options="editable:false" panelMaxHeight="100px" style="width: 60%;">
						<c:forEach items="${requestScope.fileStatu}" var="fileStatu">
							<c:choose>
							<c:when test="${requestScope.vfe.fileStatu==fileStatu.dictKey}">
							<option value="${fileStatu.dictKey }" selected>${fileStatu.ldictionaryAbel }</option>
							</c:when>
							<c:otherwise>
							<option value="${fileStatu.dictKey }">${fileStatu.ldictionaryAbel }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>
           </TR>
            <TR>
           
            <TD class="bgc"><span class="star">*</span>所属查验区</TD><TD >
            <select class="easyui-combobox" panelmaxheight="auto"  data-options="editable:false,url:'<%=request.getContextPath()%>/module/pda/loadChkpt',valueField:'organCode',textField:'organName'" id="organ" style="width:60%; height:30px;">
             </select></TD>
             <TD class="bgc"><span class="star">*</span>本地使用状态</TD>
            <TD><select class="easyui-combobox" id="localStatu" data-options="editable:false" panelMaxHeight="100px"   style="width: 60%; ">
						<c:forEach items="${requestScope.localStatu}" var="localStatu">
							<c:choose>
							<c:when test="${requestScope.vfe.localStatu==localStatu.dictKey}">
							<option value="${localStatu.dictKey }" selected>${localStatu.ldictionaryAbel }</option>
							</c:when>
							<c:otherwise>
							<option value="${localStatu.dictKey }">${localStatu.ldictionaryAbel }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</TD>
          
           </TR>
              <TR>
           
            <TD class="bgc"><span class="star">*</span>权限标识：</TD><TD >
            <select class="easyui-combobox" id="permissionFlag" data-options="editable:false" panelmaxheight="100px" style="width: 60%; "> 
           	    <option value="1">公告比对</option> 
            	<option value="2">拍照</option>
            	<option value="3">全部权限</option>  
            </select></TD>
             <TD class="bgc"></TD>
            <TD>
           
            </TD>
          
           </TR>
           
           
           
           
             <tr>
             
            <TD class="bgc"  rowspan="2"><span class="star">*</span>使用性质</TD>
            <TD colspan="5" rowspan="2"><c:forEach items="${requestScope.cehUsnrList}" var="cehUsnrEntity">
                  <c:choose>
            	<c:when test="${fn:contains(requestScope.vfe.usnrs,cehUsnrEntity.parentId)}">
            	<input type="checkbox" name="usnrs" value="${cehUsnrEntity.parentId}" checked="checked"/><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
            	</c:when>
            	<c:otherwise>
            	<input type="checkbox" name="usnrs" value="${cehUsnrEntity.parentId}"/><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
            	</c:otherwise>
            	</c:choose>

            </c:forEach>
				</TD>
           </tr>
           
           <tr></tr>
           
            <TR >
            <TD class="bgc" rowspan="2"><span class="star">*</span>可办理业务类型</TD><TD colspan="5" rowspan="2">
             <c:forEach items="${operTypeList}" var="operTypeEntity">
            	<c:choose>
            	<c:when test="${fn:contains(requestScope.vfe.operTypes,operTypeEntity.id)}">
            	<input type="checkbox" name="operTypes" id="${operTypeEntity.id}" checked="checked"/><label style="padding-right:20px;" for="${operTypeEntity.id}" >${operTypeEntity.typeName}</label>
            	</c:when>
            	<c:otherwise>
            	<input type="checkbox" name="operTypes" id="${operTypeEntity.id}" /><label style="padding-right:20px;" for="${operTypeEntity.id}" >${operTypeEntity.typeName}</label>
            	</c:otherwise>
            	</c:choose>
            </c:forEach>
            </TD>
            </TR>
            <TR>
            
            <tr>
            <TD class="bgc" rowspan="2"><span class="star">*</span>可查验车辆</TD><TD colspan="5" rowspan="2">
            <c:forEach items="${ckCllxList}" var="ckCllxEntity">
            <c:choose>
            	<c:when test="${fn:contains(requestScope.vfe.cCllxs,ckCllxEntity.parentCllx)}">
            	<input type="checkbox" name="cCllxs" id="${ckCllxEntity.parentCllx}" checked="checked" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
            	</c:when>
            	<c:otherwise>
            	<input type="checkbox" name="cCllxs" id="${ckCllxEntity.parentCllx}" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
            	</c:otherwise>
            	</c:choose>
            </c:forEach>
            </TD>
           </TR>
            <tr>
           
           <TR>
            <td class="bgc" colspan="6">
            <button type="button" class="easyui-linkbutton" id="update" data-options="iconCls:'icon-ok'" onclick="updateViewerFile();" style="width:90px;">提交</button>
            <button type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="cancel();" style="width:90px;">取消</button> </td> 
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">
$(function() {
    var organ = '${requestScope.vfe.organ}';
    var permissionFlag='${requestScope.vfe.permissionFlag}';
	// 这是只允许选择今后的日期
	$('#validEndTime').datebox('calendar').calendar({
	    validator: function(date){
	        var now = new Date();
	        var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
	        return d1<=date ;
	    }
	});
    $("#organ").combobox({
    	// 加载完成后,设置选中第一项
        onLoadSuccess: function() {   
            $(this).combobox("setValues", organ);
        }
    });
 	// 给是单选按钮绑定点击事件
 	$("input[type=radio][value=1]").click(function() {
        $("#policeId").textbox("textbox").attr("readonly",false);
    });
    // 给否单选按钮绑定点击事件，当选择不是警员时，清空警员编号且警员编号不可编辑
    $("input[type=radio][value=0]").click(function() {
        $("#policeId").textbox("setValue", "");
        $("#policeId").textbox("textbox").attr("readonly",true);
    });
    $('#organ').combobox({
        // 渲染下拉列表添加复选框
        formatter: function(row) {
            return '<label  >'+ row.organCode +': ' + row.organName + '</label>';
        }
    });
   
    $("#permissionFlag").combobox("setValue",permissionFlag);
    
   
});





// 修改
function updateViewerFile() {
	var id= '${requestScope.vfe.id}';
    var viewName = removeAllSpace($("#viewName").textbox('getValue'));
    var isPolice = $("[name=isPolice]:checked").val();
    var policeId = $("#policeId").textbox('getValue');
    var identity = $("#identity").textbox('getValue');
    var validId = $("#validId").textbox('getValue');
    var validIntrName = removeAllSpace($("#validIntrName").textbox('getValue'));
    var validEndTime = $("#validEndTime").datebox('getValue');  
   
    var viewerRank = $("#viewerRank").textbox('getValue');
    var fileStatu = $("#fileStatu").textbox('getValue');
    var localStatu = $("#localStatu").textbox('getValue');
    var organ = $("#organ").combobox('getValue');  
    var permissionFlag= $("#permissionFlag").combobox('getValue');
	// 查验使用性质
	var usnrs=[];
    // 可办理业务类型
	var operTypes=[];
	// 可查验车辆
	var cCllxs=[];
	$("input[name=usnrs]:checked").each(function(){
		usnrs.push($(this).val());
	});
	$("input[name=cCllxs]:checked").each(function(){
		cCllxs.push($(this).attr("id"));
	});
	$("input[name=operTypes]:checked").each(function(){
		operTypes.push($(this).attr("id"));
	});
    if (validateEmpty(viewName,"查验员姓名不可为空")) {
        return;
    }
    if (!checkRegExp(viewName, "isChineseName", "","姓名格式不正确")) {
        return;
    }
    if (isPolice == '1') {
        if (validateEmpty(policeId,"警员编号不可为空")) {
            return;
        }
        if (!checkRegExp(policeId, "length", "6","警员编号格式不正确")) {
            return;
        }
    }
    if (validateEmpty(identity,"身份证号码不可为空")) {
        return;
    }
    if (!checkRegExp(identity, "idCard", "","身份证格式不正确")) {
        return;
    }
    if (validateEmpty(permissionFlag,"权限标识必选")) {
        return;
    }
    if (validateEmpty(validId,"资格证编号不可为空")) {
        return;
    }
    if (validateEmpty(validIntrName,"资格证发放单位不可为空")) {
        return;
    }
    if (validateEmpty(validEndTime,"资格证有效期止不可为空")) {
        return;
    }
    if (validateEmpty(viewerRank,"查验员等级不可为空")) {
        return;
    }
    if (validateEmpty(fileStatu,"备案状态不可为空")) {
        return;
    }
    if (validateEmpty(usnrs,"使用性质不可为空")) {
        return;
    }
    if (validateEmpty(localStatu,"本地使用状态不可为空")) {
        return;
    }
    if (validateEmpty(organ,"所属查验区不可为空")) {
        return;
    }
    if (validateEmpty(operTypes,"业务类型不可为空")) {
        return;
    }
    if (validateEmpty(cCllxs,"车辆类型不可为空")) {
        return;
    }
    // 设置按钮不可点击
    $("#update").attr("disabled",true);
      $.ajax({
        url: "<%=request.getContextPath() %>/module/record/updateViewerFile",
        data: {
        	id: id,
            viewName: viewName,
            isPolice: isPolice,
            policeId: policeId,
            identity: identity,
            validId: validId,
            validIntrName: validIntrName,
            validEndTime: validEndTime,
            viewerRank: viewerRank,
            fileStatu: fileStatu,
            usnrs: usnrs.toString(),
            localStatu: localStatu,
            organ: organ,
            operTypes: operTypes.toString(),
            cCllxs: cCllxs.toString(),
            permissionFlag:permissionFlag
        },
        type: "post",
        success: function(result) {
        	if(result=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
            // 取出结果中的空格
            result = $.trim(result);
            // 设置按钮可以点击
            $("#update").removeAttr("disabled");
            if (result == "true") {
                $.messager.confirm("提示", "修改成功",
                function(r) {
                    if (r){
                    	window.location = '<%=request.getContextPath()%>/module/record/cyyba';
                    }else{
                    	window.location = '<%=request.getContextPath()%>/module/record/cyyba';
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

// 取消
function cancel() {
    window.location = '<%=request.getContextPath()%>/module/record/cyyba';
}

</script>
</html>
