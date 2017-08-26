<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>查验员备案申请</title>
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
<div class="easyui-panel" style="width:100%; height:auto;" title="查验员备案申请">
			<table width="100%" cellspacing="0" cellpadding="0">
			
            <tr>
             <TD width="200" class="bgc"><span class="star">*</span>查验员姓名</TD><TD ><input class="easyui-textbox" id="viewName" style="width: 30%;" /></TD>
             <TD width="200" class="bgc"><span class="star">*</span>是否民警</TD><TD><input type="radio" name="isPolice" value="1" id="isPolice" checked/><label style="padding-right: 10px;" for="isPolice">是</label><input type="radio" name="isPolice" value="0" id="noPolice" /><label style="padding-right: 10px;" for="noPolice">否</label></TD>
           </tr>
           
           <TR>
            <TD class="bgc"><span class="star">*</span>身份证号码</TD><TD><input class="easyui-textbox" style="width: 50%;" id="identity" /></TD>
            <TD class="bgc"><span class="star">*</span>警员编号</TD><TD><input class="easyui-textbox" style="width: 60%;" id="policeId" /></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>资格证发放单位</TD><TD><input id="validIntrName" class="easyui-textbox" style="width:60%;"/></TD>
             <TD class="bgc"><span class="star">*</span>查验资格证编号</TD><TD><input class="easyui-textbox" style="width: 60%;" id="validId" /></TD>
           </TR>
           <TR>
            <TD class="bgc"><span class="star">*</span>查验员等级</TD>
            <TD>
            <select class="easyui-combobox" id="viewerRank" data-options="editable:false" panelmaxheight="100px" style="width: 30%;"> <option value="001">初级</option> <option value="002">中级</option> <option value="003">高级</option> </select>
            </TD>
            <TD class="bgc"><span class="star">*</span>资格证有效期止</TD><TD><input id="validEndTime" data-options="editable:false" class="easyui-datebox" style="width:60%;"/></TD>
           </TR>
           <tr>
            <TD width="200" class="bgc"><span class="star">*</span>用户名 : </TD><TD ><input class="easyui-textbox" id="loginName" style="width: 30%;" id="loginName"/></TD>
            <TD class="bgc"><span class="star">*</span>备案状态</TD><td>
             <select class="easyui-combobox" id="fileStatu" data-options="editable:false"  style="width: 60%;"> <option value="1">正常</option> <option value="2">停用</option> <option value="3">撤销</option> <option value="4">首次备案申请</option> <option value="5">已过有效期</option> </select>
            </td>
           </TR>
            <TR>
           
            <TD class="bgc"><span class="star">*</span>所属查验区</TD><TD >
            <select class="easyui-combobox" panelmaxheight="auto"  data-options="editable:false,url:'<%=request.getContextPath()%>/module/pda/loadChkpt',valueField:'organCode',textField:'organName'" id="organ" style="width:60%; height:30px;">
             </select></TD>
             <TD class="bgc"><span class="star">*</span>本地使用状态</TD>
            <TD>
            <select class="easyui-combobox" id="localStatu" data-options="editable:false" panelmaxheight="100px" style="width: 60%; "> <option value="1">正常</option> <option value="2">暂停</option> </select>
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
           <TD class="bgc" rowspan="2"><span class="star">*</span>使用性质</TD>
            <TD colspan="5" rowspan="2">
            <c:forEach items="${cehUsnrList}" var="cehUsnrEntity">
            	<input type="checkbox" name="usnrs" value="${cehUsnrEntity.parentId}"/><label style="padding-right:20px;" >${cehUsnrEntity.parentName}</label>
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
            <TD class="bgc" rowspan="2"><span class="star">*</span>可查验车辆</TD><TD colspan="5" rowspan="2">
            <c:forEach items="${ckCllxList}" var="ckCllxEntity">
           	<input type="checkbox" name="cCllxs" id="${ckCllxEntity.parentCllx}" /><label style="padding-right:20px;" for="${ckCllxEntity.parentCllx}" >${ckCllxEntity.parentName}</label>
            </c:forEach>
            </TD>
           </TR>
            <tr>
           
           <TR>
            <td class="bgc" colspan="6">
            <button type="button" class="easyui-linkbutton" id="submit" data-options="iconCls:'icon-ok'" onclick="submit();" style="width:90px;">提交</button>
            <button type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" onclick="cancel();" style="width:90px;">取消</button> </td> 
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">
$(function() {
	// 这是只允许选择今后的日期
	$('#validEndTime').datebox('calendar').calendar({
	    validator: function(date){
	        var now = new Date();
	        var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
	        return d1<=date ;
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

  
   
});


$('#organ').combobox({
    // 渲染下拉列表添加复选框
    formatter: function(row) {
        return '<label  >'+ row.organCode +': ' + row.organName + '</label>';
    }
});


// 提交申请
function submit() {
	var loginName=$("#loginName").textbox('getValue');
	if (validateEmpty(loginName,"用户名不可为空")) {
        return;
    }
	 $.ajax({
	        url: "<%=request.getContextPath() %>/module/record/validateloginname",
	        data: {
	        	loginName:loginName
	        },
	        type: "post",
	        success: function(result) {
	        	if(result=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
	        	if(result != "true"){
	        		alert("用户名存在 ");
	        		return ;
	        	}else{
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
	        		    if (validateEmpty(permissionFlag,"权限标识必选")) {
	        		        return;
	        		    }
	        		    if (!checkRegExp(identity, "idCard", "","身份证格式不正确")) {
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
	        			$("#submit").attr("disabled",true);
	        		    $.ajax({
	        		        url: "<%=request.getContextPath() %>/module/record/submit",
	        		        data: {
	        		            viewName: viewName,
	        		            isPolice: isPolice,
	        		            policeId: policeId,
	        		            identity: identity,
	        		            validId: validId,
	        		            loginName:loginName,
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
	        		        	// 设置按钮可点击
	        		        	$("#submit").removeAttr("disabled");
	        		            // 取出结果中的空格
	        		            result = $.trim(result);
	        		            if (result == "true") {
	        		                $.messager.confirm("提示！", "备案成功，是否继续备案？",
	        		                function(r) {
	        		                    if (r) {
	        		                    	window.location = '<%=request.getContextPath()%>/module/record/cyybasq';
	        		                    } else {
	        		                        window.location = '<%=request.getContextPath()%>/module/record/cyyba';
	        		                    }
	        		                });
	        		            }else {
	        		                $.messager.alert("提示", "备案失败");
	        		            }
	        		        }
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
