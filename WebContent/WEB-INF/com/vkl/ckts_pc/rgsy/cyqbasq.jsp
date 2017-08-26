<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>查验区备案申请</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="查验区备案申请">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <!-- <TD width="200" class="bgc"><span class="star">*</span>查验区序号</TD><TD > <input id="organCode" class="easyui-textbox" style="width:60%;"/></TD> -->
          <!--    <TD width="200" class="bgc"><span class="star">*</span>电子围栏中心位置</TD><TD><label >经度：</label><input ID="ckCentLg" class="easyui-textbox marginl20" style=" width:150px;text-align:right"/><label ><br/>纬度：</label><input id="ckCentLt" class="easyui-textbox marginl20" style=" width:150px;text-align:right"/></TD> -->
             
              <TD style="width: 250px;" class="bgc"><span class="star">*</span>电子围栏中心经度</TD><TD><input ID="ckCentLg" class="easyui-textbox marginl20" style=" width:150px;text-align:right"/></TD>
               <TD style="width: 250px;" class="bgc"><span class="star">*</span>电子围栏中心纬度</TD><TD><input id="ckCentLt" class="easyui-textbox marginl20" style=" width:150px;text-align:right"/></TD>
           
           </tr>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验区名称</TD><TD><input id="organName" class="easyui-textbox" style="width:60%;"/></TD>
            <TD class="bgc"><span class="star">*</span>电子围栏半径（米）</TD><TD><input id="ckCentR" class="easyui-textbox" style="width:30%;text-align:right"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验区类型</TD>
            <TD>
            <select class="easyui-combobox" id="organType" data-options="editable:false" style="width:60%;">
            <option value="001">车管所</option>
            <option value="002">机动车销售单位</option>
            <option value="003">二手车交易市场</option>
            <option value="004">机动车回收企业</option>
            </select>
            </TD>
            <TD class="bgc"><span class="star">*</span>电子围栏启用状态</TD><TD>
            <select id="userDz" class="easyui-combobox" data-options="editable:false" style="width:30%;">
            <option value="1">启用</option>
            <option value="0">禁用</option>
            </select>
            </TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验区所属部门</TD><td><select class="easyui-combotree" url="<%=request.getContextPath()%>/module/dept/findDeptList?parentId=0" id="parentDeptId" style="width:60%;"></select></td>
            <TD class="bgc"><span class="star">*</span>查验区负责人</TD><TD><input id="pripName" class="easyui-textbox" style="width:30%"/></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>联系电话</TD><TD><input id="pripPhone" class="easyui-textbox" style="width:40%;"/></TD>
             <TD class="bgc"><span class="star">*</span>备案状态</TD><TD>
            <select class="easyui-combobox" id="fileStatu" data-options="editable:false" panelmaxheight="auto" style="width: 40%;"> <option value="1">正常</option> <option value="2">停用</option> <option value="3">撤销</option> <option value="4">首次备案申请</option> <option value="5">已过有效期</option> </select>
            </TD>
           </TR>
           <TR>
            
            <TD class="bgc"><span class="star">*</span>本地使用状态</TD><TD>
            <select class="easyui-combobox" id="localStatu" data-options="editable:false" panelmaxheight="auto" style="width: 30%; "> <option value="1">正常</option> <option value="2">暂停</option> </select> 
            </TD>
              <TD class="bgc"><span class="star">*</span>是否允许自动审核</TD><TD>
                <input type="radio" value="0" name='isAutoAudit' />否   <input type="radio" value="1" name='isAutoAudit' checked="checked"/>是  
            </TD>
           </TR>
            <TR>
            
            <TD class="bgc"><span class="star"></span>视频存储ip地址</TD><TD>
            <input id="videoIp" class="easyui-textbox" style="width:50%"/>
            </TD>
              <TD class="bgc"><span class="star"></span>视频存储端口</TD><TD>
              <input id="videoPort" class="easyui-textbox" style="width:50%"/>
            </TD>
           </TR>
               <tr> 
             <TD class="bgc" rowspan="2"><span class="star">*</span>查验使用性质</TD>
             <TD  colspan="5" rowspan="2">
            <c:forEach items="${cehUsnrList}" var="cehUsnrEntity">
            	<input type="checkbox" name="syxzs" value="${cehUsnrEntity.parentId}"/><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
            </c:forEach>
            </TD>
            </tr>
            <tr></tr>
            <TR >
            <TD class="bgc" rowspan="2"><span class="star">*</span>可办理业务类型</TD><TD colspan="5" rowspan="2">
            <c:forEach items="${operTypeList}" var="operTypeEntity">
                 <input type="checkbox" name="operTypes" id="${operTypeEntity.id}" /><label style="padding-right:20px;" for="${operTypeEntity.id}" >${operTypeEntity.typeName}</label>
            </c:forEach>
            </TD>
            </TR>
            <TR>
        
            <tr>
            <TD class="bgc" rowspan="2"><span class="star">*</span>可查验车辆类型</TD><TD colspan="5" rowspan="2">
            <c:forEach items="${ckCllxList}" var="ckCllxEntity">
           	<input type="checkbox" name="ckCllxs" id="${ckCllxEntity.parentCllx}" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
            </c:forEach>
            </TD>
           </TR>
            <tr>
          
           </TR>
           <TR>
            <TD class="bgc" colspan="5">
            <button  type="button" class="easyui-linkbutton" id="submit"  data-options="iconCls:'icon-ok'"  onclick="submit();" style="width:90px;">提交</button>
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'"  onclick="cancel();" style="width:90px;">取消</button>
            </TD>
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">

// 提交
function submit(){
	
	 var isAutoAudit = $("[name=isAutoAudit]:checked").val();
	// 查验区序号
/* 	var organCode=$("#organCode").textbox("getValue"); */
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
	$("input[name=syxzs]:checked").each(function(){
		syxzs.push($(this).val());
	});
	$("input[name=ckCllxs]:checked").each(function(){
		ckCllxs.push($(this).attr("id"));
	});
	$("input[name=operTypes]:checked").each(function(){
		operTypes.push($(this).attr("id"));
	});
	/* if (validateEmpty(organCode,"查验区序号不可为空")) {
        return;
    } */
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
	/* if (!checkRegExp(organName, "isChinese", "","查验区名称格式不正确")) {
        return;
    } */
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
	$("#submit").attr("disabled",true);
	$.ajax({
		url:"<%=request.getContextPath() %>/module/chkpt/submit",
		data:{
			/* organCode:organCode, */
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
        	// 设置按钮可点击
        	$("#submit").removeAttr("disabled");
			if(result.data=="true"){
				$.messager.confirm("提示！", "备案成功，是否继续备案？",
		                function(r) {
		                    if (r) {
		                    	window.location = '<%=request.getContextPath()%>/module/chkpt/cyqbasq';
		                    } else {
		                        window.location = '<%=request.getContextPath()%>/module/chkpt/cyqba';
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
    window.location = '<%=request.getContextPath()%>/module/chkpt/cyqba';
}
</script>
</html>