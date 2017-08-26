<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
	<title>用户新增</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="新增用户">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc"><span class="star">*</span>用户账号</TD><TD > <input class="easyui-textbox" style="width:80%;" id="loginName"/></TD>
             <TD width="200" class="bgc"><span class="star">*</span>用户角色<TD>
              <select class="easyui-combobox"  data-options="multiple:true,editable:false,url:'<%=request.getContextPath()%>/module/role/findRoleList',valueField:'id',textField:'roleName'" id="serial" style="width:80%; height:30px;">
              </select></TD>
           </tr>
           <TR>
            <TD class="bgc"><span class="star">*</span>用户名称</TD><TD><input class="easyui-textbox" style="width:80%;" id="userName"/></TD>
            <TD class="bgc"><span class="star">*</span>用户部门</TD>
             <TD >
              <select class="easyui-combotree"  data-options="cascadeCheck:false"
					url="<%=request.getContextPath()%>/module/dept/findAllDeptList?id=${userDept}&flag=1"
					id="userDept" style="width: 80%;">
              </select>
            </TD>
           
          
           <!--  <TD class="bgc"><span class="star">*</span>密码</TD><TD><input type="p" class="easyui-textbox" style="width:80%;" id="loginPwd"/></TD> -->
           </TR>
           <TR>
            <TD class="bgc">身份证号码</TD><TD><input class="easyui-textbox" style="width:80%;" id="identityCard"/></TD>
            <TD class="bgc">电话</TD><TD><input class="easyui-textbox" style="width:80%;" id="userTele"/></TD>
          	</TR>
           <TR>
            <TD class="bgc">联系地址</TD><TD><input class="easyui-textbox" style="width:80%;" id="userAddr"/></TD>
            <TD class="bgc">警员编号</TD><TD><input class="easyui-textbox" style="width:80%;" id="fuzz"/></TD>           
           </TR>
           <TR>
            <TD class="bgc">手机号</TD><TD><input class="easyui-textbox" style="width:80%;" id="userPhone"/></TD>
            <TD class="bgc">性别</TD><TD><input type="radio"  name="userSex" value="女"/><label style="padding-right:20px;">女</label><input type="radio"  name="userSex" value="男"/><label style="padding-right:20px;">男</label></TD>           
           </TR><tr>
            <TD class="bgc"><span class="star">*</span>使用状态</TD><TD><input type="radio" name="userStatu" value="0" checked="checked"/><label style="padding-right:20px;">开启</label><input type="radio"  name="userStatu" value="1"/><label style="padding-right:20px;">禁用</label></TD>
            <TD class="bgc"><!-- <span class="star">*</span>用户部门 --></TD>
            <TD >
             <%--  <select class="easyui-combotree"  data-options="cascadeCheck:false"
					url="<%=request.getContextPath()%>/module/dept/findAllDeptList?parentId=${parentId}"
					id="userDept" style="width: 80%;">
              </select> --%>
            </TD>
           </TR>
           <TR>
            <TD class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-ok'"  onclick="submit();">提交</button>
            <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-undo'"  onclick="window.location='<%=request.getContextPath()%>/userAdmin/index'">取消</button>
            </TD>
           </TR>
        </table>
	</div>
</body>
<script type="text/javascript">
$(function(){
	
	$("#userDept").combotree({
			onLoadSuccess: function () { 
				//加载完成后,设置选中项
				var deptId = '${userDept}';
				$("#userDept").combotree('setValues',deptId);
			}
	  
	})

});



$("#serial").combobox({
    // 渲染下拉列表添加复选框
    formatter: function(row) {
        return '<input id=' + row.id+ ' type="checkbox"/><label  >'+ row.roleName + '</label>';
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
	
})

	function submit(){
		var loginName = $("#loginName").textbox("getValue");
		var serial = $("#serial").combobox("getValues");		
		var userName = $("#userName").textbox("getValue");
	/* 	var loginPwd = $("#loginPwd").textbox("getValue"); */
		var identityCard = $("#identityCard").textbox("getValue");
		var userTele = $("#userTele").textbox("getValue");
		var userAddr = $("#userAddr").textbox("getValue");
		var fuzz = $("#fuzz").textbox("getValue");
		var userPhone = $("#userPhone").textbox("getValue");
		var userSex = $("input[name='userSex']:checked").val();
		var userStatu = $("input[name='userStatu']:checked").val();
		var deptId = $("#userDept").combotree("getValues");
		if(validateEmpty(loginName,"用户账号不能为空")){
			return;
		}
		if(validateEmpty(loginName,"用户账号不能为空")){
			return;
		}else if (/[\u4e00-\u9fa5]/.exec(loginName)) {
	        $.messager.alert("账号格式不正确");
			return;
	    }
		if(validateEmpty(serial,"用户角色不能为空")){
			return;
		}
		if(validateEmpty(userName,"用户名不能为空")){
			return;
		}/* else if (!checkRegExp(userName, "isChineseName", "","用户名格式不正确")) {
	        return;
	    } */
	/* 	if(validateEmpty(loginPwd,"密码不能为空")){
			return;
		}else if (/[\u4e00-\u9fa5]/.exec(loginName)) {
	        $.messager.alert("密码格式不正确");
			return;
	    } */
		if(identityCard != null && identityCard !=""){
			if (!checkRegExp(identityCard, "idCard", "","身份证号格式不正确")) {
		        return;
		    }
		}
		if(userTele != null && userTele !=""){
			 if (!checkRegExp(userTele, "phoneNumber", "","电话号码格式不正确")) {
		        return;
		     }
	    }
		if(userPhone != null && userPhone !=""){
			if (!checkRegExp(userPhone, "phoneNumber", "","手机号码格式不正确")) {
		        return;
		    }
		}
		if(fuzz != null && fuzz !=''){
			 if (!checkRegExp(fuzz, "length", "6","警员编号格式不正确")) {
			        return;
			     }
		    }
		if(validateEmpty(userStatu,"用户状态不能为空")){
			return;
		}
		
		if(validateEmpty(deptId,"请选择用户部门")){
			return;
		}
		$.ajax({
			url : '<%=request.getContextPath()%>/userAdmin/add',
			type : 'post',
			data : {loginName:loginName, uRole:serial.join(","), userName:userName,/*  loginPwd:loginPwd,  */identityCard:identityCard,
				userTele:userTele, userAddr:userAddr, fuzz:fuzz, userPhone:userPhone, userSex:userSex, userStatu:userStatu,userDept:deptId.toString()},
		    error:function(data){
		    	$.messager.alert("提示","请求异常，请重新请求");
		    },
			success : function(data){
				if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				if(data.errorMsg == "success"){
					$.messager.confirm("提示","用户添加成功，继续添加？",function(a){
						if(a){
							location.reload();
						}else{
							window.location.href="<%=request.getContextPath()%>/userAdmin/index";
						}
					})											
				}else{
					$.messager.alert("提示",data.errorMsg);
				}
				
			}
			
		})
		
	}
</script>
</html>
