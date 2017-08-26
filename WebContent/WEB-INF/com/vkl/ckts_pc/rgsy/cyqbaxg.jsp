<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <meta http-equiv="X-UA-Compatible" content="IE=8" > 
<title>查验区备案修改</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="查验区备案修改">
			<table width="100%" cellspacing="0" cellpadding="0">
          <%--   <tr>
             <TD width="200" class="bgc"><span class="star">*</span>查验区序号</TD><TD > <input id="organCode" class="easyui-textbox" value="${requestScope.cfe.organCode}" readonly="readonly" style="width:60%;"/></TD>
             <TD width="200" class="bgc"><span class="star">*</span>电子围栏中心位置</TD><TD><label >经度：</label><input ID="ckCentLg" value="${requestScope.cfe.ckCentLg}" class="easyui-textbox marginl20" style=" width:150px;text-align:right"/><label >纬度：</label><input id="ckCentLt" value="${requestScope.cfe.ckCentLt}" class="easyui-textbox marginl20" style=" width:150px;text-align:right"/></TD>
           </tr> --%>
           
           
           <tr>
             <TD style="width: 250px;"class="bgc"><span class="star">*</span>查验区序号</TD><TD > <input id="organCode" class="easyui-textbox" value="${requestScope.cfe.organCode}" readonly="readonly" style="width:60%;"/></TD>
             <TD style="width: 250px;" class="bgc"><span class="star">*</span>电子围栏中心经度</TD><TD><input ID="ckCentLg" value="${requestScope.cfe.ckCentLg}" class="easyui-textbox marginl20" style=" width:150px;text-align:right"/></TD>
           </tr>
           <TR>
                <TD class="bgc"><span class="star">*</span>本地使用状态</TD><TD>
            <select class="easyui-combobox" id="localStatu" data-options="editable:false" panelMaxHeight="100px"   style="width: 30%; ">
						<c:forEach items="${requestScope.localStatu}" var="localStatu">
							<c:choose>
							<c:when test="${requestScope.cfe.localStatu==localStatu.dictKey}">
							<option value="${localStatu.dictKey }" selected>${localStatu.ldictionaryAbel }</option>
							</c:when>
							<c:otherwise>
							<option value="${localStatu.dictKey }">${localStatu.ldictionaryAbel }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
            </TD>
            <TD width="200" class="bgc"><span class="star">*</span>电子围栏中心纬度</TD><TD><input id="ckCentLt" value="${requestScope.cfe.ckCentLt}" class="easyui-textbox marginl20" style=" width:150px;text-align:right"/></TD>
           </TR>
           
           
           <TR>
            <TD class="bgc"><span class="star">*</span>查验区名称</TD><TD><input id="organName" value="${requestScope.cfe.organName}" class="easyui-textbox" style="width:60%;"/></TD>
            <TD class="bgc"><span class="star">*</span>电子围栏半径（米）</TD><TD><input id="ckCentR" value="${requestScope.cfe.ckCentR}" class="easyui-textbox" style="width:30%; text-align:right"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验区类型</TD>
            <TD>
            <select class="easyui-combobox" id="organType" data-options="editable:false" style="width:60%;">
            <c:forEach items="${requestScope.organTypes }" var="organType" >
            	<option value="${organType.dictKey}" ${requestScope.cfe.organType==organType.dictKey?'selected=selected':''}>${organType.ldictionaryAbel}</option>
            </c:forEach>
            </select>
            </TD>
            <TD class="bgc"><span class="star">*</span>电子围栏启用状态</TD><TD>
            <select id="userDz" class="easyui-combobox" data-options="editable:false" style="width:30%;">
            <option value="1" ${requestScope.cfe.userDz eq '1'?'selected':'' }>启用</option>
            <option value="0" ${requestScope.cfe.userDz eq '0'?'selected':'' }>禁用</option>
            </select>
            </TD>
            </TR>
           <TR>
           	<TD class="bgc"><span class="star">*</span>查验区所属部门</TD><td><select class="easyui-combotree" url="<%=request.getContextPath()%>/module/dept/findDeptList?parentId=0" id="parentDeptId" style="width:60%;"></select></td>
            <TD class="bgc"><span class="star">*</span>查验区负责人</TD><TD><input id="pripName" value="${requestScope.cfe.pripName}" class="easyui-textbox" style="width:30%"/></TD>
           </TR>
           <TR>
           	<TD class="bgc"><span class="star">*</span>联系电话</TD><TD><input id="pripPhone"  value="${requestScope.cfe.pripPhone}" class="easyui-textbox" style="width:60%;"/></TD>
            <TD class="bgc"><span class="star">*</span>备案状态</TD><TD>
            <select class="easyui-combobox" id="fileStatu" data-options="editable:false" panelMaxHeight="100px" style="width: 30%;">
						<c:forEach items="${requestScope.fileStatu}" var="fileStatu">
							<option value="${fileStatu.dictKey }" ${requestScope.cfe.fileStatu==fileStatu.dictKey?'selected=selected':''} >${fileStatu.ldictionaryAbel }</option>
						</c:forEach>
				</select>
            </TD>
           </TR>
         
           <TR>
            
            <TD class="bgc"><span class="star"></span>视频存储ip地址</TD><TD>
            <input id="videoIp" class="easyui-textbox" style="width:50%" value="${requestScope.cfe.videoIp}"/>
            </TD>
              <TD class="bgc"><span class="star"></span>视频存储端口</TD><TD>
              <input id="videoPort" class="easyui-textbox" style="width:50%" value="${requestScope.cfe.videoPort}"/>
            </TD>
           </TR>
             <TR>
           
              <TD class="bgc"><span class="star">*</span>是否允许自动审核</TD><TD colspan="3">
                <input type="radio" value="0" name='isAutoAudit' <c:if test="${requestScope.cfe.isAutoAudit eq '0'}">checked="checked"</c:if>/>否   <input type="radio" value="1" name='isAutoAudit' <c:if test="${requestScope.cfe.isAutoAudit eq '1'}">checked="checked"</c:if>/>是  
            </TD>
            
              
            
           </TR>
           
           
           
       
            
            
           <!--  <TD class="bgc"><span class="star">*</span>本地使用状态</TD><TD> -->
         <%--    <select class="easyui-combobox" id="localStatu" data-options="editable:false" panelMaxHeight="100px"   style="width: 30%; ">
						<c:forEach items="${requestScope.localStatu}" var="localStatu">
							<c:choose>
							<c:when test="${requestScope.cfe.localStatu==localStatu.dictKey}">
							<option value="${localStatu.dictKey }" selected>${localStatu.ldictionaryAbel }</option>
							</c:when>
							<c:otherwise>
							<option value="${localStatu.dictKey }">${localStatu.ldictionaryAbel }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> --%>
        <!--     </TD>
           </TR> -->
           
           <tr>
           	   <TD class="bgc" rowspan="2"><span class="star">*</span>查验使用性质</TD>
           	   <TD colspan="5" rowspan="2">
                <c:forEach items="${cehUsnrList}" var="cehUsnrEntity">
            	<c:choose>
            	<c:when test="${fn:contains(requestScope.cfe.syxzs, cehUsnrEntity.parentId)}">
            	<input type="checkbox" name="syxzs" value="${cehUsnrEntity.parentId}" checked="checked" /><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
            	</c:when>
            	<c:otherwise>
            	<input type="checkbox" name="syxzs" value="${cehUsnrEntity.parentId}" /><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
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
            	<c:when test="${fn:contains(requestScope.cfe.operTypes,operTypeEntity.id)}">
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
            	<c:when test="${fn:contains(requestScope.cfe.ckCllxs,ckCllxEntity.parentCllx)}">
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
          
           </TR>
           <TR>
            <TD class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton"  id="update" data-options="iconCls:'icon-ok'"  onclick="update();" style="width:90px;">提交</button>
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'"  onclick="cancel();" style="width:90px;">取消</button>
            </TD>
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">
$("#parentDeptId").combotree({
	onLoadSuccess: function () { 
		//加载完成后,设置选中项
		$("#parentDeptId").combotree('setValue','${requestScope.cfe.parentDeptId}');
		$("#parentDeptId").combotree('setText','${requestScope.cfe.parentDeptName}');
	}
});

//修改
function update(){
	// 查验区序号
	var organCode=$("#organCode").textbox("getValue");
	// 经度
	var ckCentLg=$("#ckCentLg").textbox("getValue");
	// 纬度
	var ckCentLt=$("#ckCentLt").textbox("getValue");
	// 查验区名称
	var organName=removeAllSpace($("#organName").textbox("getValue"));
	// 电子围栏半径
	var ckCentR=$("#ckCentR").textbox("getValue");
	// 查验区类
	var organType=$("#organType").combobox("getValue");
	// 查验区所属部门
	var parentDeptId=$("#parentDeptId").combotree("getValue");
	var parentDeptName=$("#parentDeptId").combotree("getText");
	// 联系电话
	var pripPhone=$("#pripPhone").textbox("getValue");
	// 查验区负责人
	var pripName=removeAllSpace($("#pripName").textbox("getValue"));
	// 查验使用性质
	var syxzs=[];
	// 备案状态
	var fileStatu=$("#fileStatu").combobox("getValue");
    // 电子围栏
    var userDz=$("#userDz").combobox("getValue");
    // 本地使用状态
    var localStatu=$("#localStatu").combobox("getValue");
    var videoIp=$("#videoIp").textbox("getValue");
    var videoPort=$("#videoPort").textbox("getValue");
    // 可办理业务类型
	var operTypes=[];
	// 可查验车辆
	var ckCllxs=[];
	 var isAutoAudit = $("[name=isAutoAudit]:checked").val();
	$("input[name=syxzs]:checked").each(function(){
		syxzs.push($(this).val());
	});
	$("input[name=ckCllxs]:checked").each(function(){
		ckCllxs.push($(this).attr("id"));
	});
	$("input[name=operTypes]:checked").each(function(){
		operTypes.push($(this).attr("id"));
	});
	if (validateEmpty(organCode,"查验区序号不可为空")) {
        return;
    }
	if (validateEmpty(ckCentLg,"经度不可为空")) {
        return;
    }
	if (!checkRegExp(ckCentLg, "longitude", "","经度格式不正确")) {
        return;
    }
	if (validateEmpty(ckCentLt,"纬度不可为空")) {
        return;
    }
	if (!checkRegExp(ckCentLt, "latitude", "","纬度格式不正确")) {
        return;
    }
	if (validateEmpty(organName,"查验区名称不可为空")) {
        return;
    }
	if (!checkRegExp(organName, "isChinese", "","查验区名称格式不正确")) {
        return;
    }
	if (validateEmpty(ckCentR,"电子围栏半径不可为空")) {
        return;
    }
	if (!checkRegExp(ckCentR, "positiveInteger", "","电子围栏半径格式不正确")) {
        return;
    }
	if (validateEmpty(parentDeptId,"查验区所属部门不可为空")) {
        return;
    }
	if (validateEmpty(pripPhone,"联系电话不可为空")) {
        return;
    }
	if (!checkRegExp(pripPhone, "phoneNumber", "","联系电话不正确")) {
        return;
    }
	if (validateEmpty(pripName,"查验区负责人不可为空")) {
        return;
    }
	if (!checkRegExp(pripName, "isChineseName", "","姓名格式不正确")) {
        return;
    }
	if (validateEmpty(syxzs,"查验使用性质不可为空")) {
        return;
    }
	if (validateEmpty(operTypes,"可办理业务类型不可为空")) {
        return;
    }
	if (validateEmpty(ckCllxs,"可查验车辆不可为空")) {
        return;
    }
    // 设置按钮不可点击
    $("#update").attr("disabled",true);
	$.ajax({
		url:"<%=request.getContextPath() %>/module/chkpt/updateChkptFile",
		data:{
			organCode:organCode,
			ckCentLg:ckCentLg,
			ckCentLt:ckCentLt,
			organName:organName,
			ckCentR:ckCentR,
			organType:organType,
			parentDeptId:parentDeptId,
			parentDeptName:parentDeptName,
			pripPhone:pripPhone,
			pripName:pripName,
			syxzs:syxzs.toString(),
			fileStatu:fileStatu,
			userDz:userDz,
			localStatu:localStatu,
			operTypes:operTypes.toString(),
			ckCllxs:ckCllxs.toString(),
			isAutoAudit:isAutoAudit,
			videoIp:videoIp,
			videoPort:videoPort
		},
		type:"post",
		success:function(result){
			if(result=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
            // 设置按钮可以点击
            $("#update").removeAttr("disabled");
			result = $.trim(result);
			if (result == "true") {
                $.messager.confirm("提示", "修改成功",
                function(r) {
                    if (r){
                    	window.location = '<%=request.getContextPath()%>/module/chkpt/cyqba';
                    }else{
                    	window.location = '<%=request.getContextPath()%>/module/chkpt/cyqba';
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
    window.location = '<%=request.getContextPath()%>/module/chkpt/cyqba';
}
</script>
</html>