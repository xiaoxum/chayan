<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>新增部门</title>
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
	
	<div class="easyui-panel" style="width:100%; height:auto;" title="新增部门">
			<table width="100%" cellspacing="0" cellpadding="0">
            <tr>
             <TD width="200" class="bgc"><span class="star">*</span>部门类型</TD><TD>
              	<select class="easyui-combobox" id="deptType" data-options="editable:false" style="width:200px; height:30px;">
	                <option value="1">市车管所</option>
	                <option value="2">县/区车管所</option>
	                <option value="3">查验点</option>
                </select>
             </TD>
             <TD width="200" class="bgc"><span class="star">*</span>部门名称</TD>
             <TD>
             <div id="div1"><input id="deptName1" class="easyui-textbox" style=" width:80%;"/></div>
             <div id="div2"><select id="deptName2" class="easyui-combobox"  data-options="editable:false,url:'<%=request.getContextPath()%>/module/pda/loadChkpt',valueField:'organCode',textField:'organName'" style=" width:80%"></select></div>
             </TD>
           </tr>
           <TR>
            <TD class="bgc">上级部门</TD><TD>
            <select class="easyui-combotree" url="<%=request.getContextPath()%>/module/dept/findDeptList?id=${deptId}&flag=1" id="parentId" style="width:200px;"></select></TD>
            <TD class="bgc"><span class="star">*</span>行政区划</TD><TD>
             <select class="easyui-combobox" id="asts" data-options="editable:false" style="width:80%; height:30px;">
             	<c:forEach items="${areaList }" var="area">
             		<option value="${area.id}">${area.areaName}</option>
             	</c:forEach>
             </select>
            </TD>
           </TR>
           <TR>
           <TR>
            <TD class="bgc">联系电话</TD><TD><input id="deptPhone" class="easyui-textbox" style="width:200px;"/></TD>
            <TD class="bgc">联系地址</TD><TD><input id="deptAddr" class="easyui-textbox" style="width:80%"/></TD>
           </TR>
           <TR>
            <TD class="bgc">邮箱</TD><TD><input id="deptEmail" class="easyui-textbox" style="width:200px;"/></TD>
            <TD class="bgc">网址</TD><TD><input id="deptUrl" class="easyui-textbox" style="width:80%"/></TD>
           </TR>
           <TR>
            <TD class="bgc">部门描述</TD><TD colspan="3"><input id="deptIntr" class="easyui-textbox" style="width:80%;"/></TD>
           </TR>
           
           
           <TR>
            <TD class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton" id="submit" data-options="iconCls:'icon-ok'"  onclick="submit();">提交</button>
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'"  onclick="cancel();">取消</button>
            </TD>
           </TR> 
        </table>
		
		
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#parentId").combotree({
		onLoadSuccess: function () { 
			//加载完成后,设置选中项
			var deptId = '${deptId}';
			$("#parentId").combotree('setValues',deptId);
		}
	});
	// 部门类型
	$("#deptType").combobox({
		 onSelect:function(row){
			 // 如果当前部门类型为查验点，则修改部门名称为下拉框
			if (row.value == 3){
				$("#deptName1").textbox("setValue","");
				$("#div1").css("display","none");
				$("#div2").css("display","block");
				$("#deptName2").combobox("setValue","");
				$("#deptName2").combobox({
					// 判断部门是否存在
					onSelect: function(row){
						$.ajax({
							url:"<%=request.getContextPath()%>/module/dept/ckDeptExist",
							data:{fileId: row.organCode},
							type:"post",
							success:function(result){
								if(result =="no-login"){
									top.location="<%=request.getContextPath() %>/login.jsp"
					        	}
								if($.trim(result.data)=="true"){
									$.messager.alert("提示！","该部门已存在,请选择其他部门!")
									$("#deptName2").combobox("setValue","");
									$("#parentId").combotree("setValue","");
									return ;
								}else{
									$.ajax({
										url:"<%=request.getContextPath()%>/module/dept/findChkptFileById",
										data:{organCode: row.organCode},
										type:"post",
										success:function(result){
											if(result=="no-login"){
												top.location="<%=request.getContextPath() %>/login.jsp"
								        	}
											$("#deptPhone").textbox("setValue",result.pripPhone);
											$("#deptAddr").textbox("setValue",result.organAddr);
										}
									})
									$("#parentId").combotree("setValue",row.parentDeptId);
								}
							}
						})
						
						
						
						
					}
				})
			}else {
				// 如果部门类型不是查验点，显示部门名称为输入框
				$("#div1").css("display","block");
				$("#div2").css("display","none");
				
			}
			if (row.value == 1) {
				$("#parentId").combotree("setValue","");
				$("#parentId").combotree("disable");
			}else {
				$("#parentId").combotree("enable");
				$("#parentId").combotree("setValue","");
				$("#parentId").combotree({
					onSelect: function(row){
						$("#deptName2").combobox({
							url:"<%=request.getContextPath()%>/module/pda/loadChkpt?parentDeptId="+row.id
						});
					}
				});
			}
		} 
	});
	
	
	
});

// 提交
function submit() {
	// 备案编号
	var fileId = "";
	// 是否是备案部门
	var isFileDept = "0";
	// 部门类型
	var deptType = $("#deptType").combobox("getValue");
	// 部门名称
	if(deptType == "3") {
		var deptName = $("#deptName2").combobox("getText");
		var fileId = $("#deptName2").combobox("getValue");
		var isFileDept = "1";
	}else {
		var deptName = removeAllSpace($("#deptName1").textbox("getValue"));
	}
	// 上级部门
	if(deptType == "1") {
		var parentId = "0";
	}else {
		var parentId = $("#parentId").combotree("getValue");
	}
	// 行政区划
	var asts = $("#asts").combobox("getValue");
	// 联系电话
	var deptPhone = $("#deptPhone").textbox("getValue");
	// 联系地址
	var deptAddr = $("#deptAddr").textbox("getValue");
	// 邮箱
	var deptEmail = $("#deptEmail").textbox("getValue");
	// 网址
	var deptUrl = $("#deptUrl").textbox("getValue");
	// 部门描述
	var deptIntr = $("#deptIntr").textbox("getValue");
	if (validateEmpty(deptName,"部门名称不可为空")) {
        return;
    }
	if (deptType != "1"){
		if (validateEmpty(parentId,"上级部门不可为空")) {
	        return;
	    }
	}
	if (validateEmpty(asts,"行政区划不可为空")) {
	    return;
	}
	if (deptPhone != "" && deptPhone !=null) {
	    if (!checkRegExp(deptPhone, "phoneNumber", "","联系电话格式不正确")) {
	        return;
	    }
	}
	if (deptEmail != "" && deptEmail !=null) {
	    if (!checkRegExp(deptEmail, "email", "","邮箱格式不正确")) {
	        return;
	    }
	}
	if (deptUrl != "" && deptUrl !=null) {
	    if (!checkRegExp(deptUrl, "url", "","网址格式不正确")) {
	        return;
	    }
	}
	//alert(deptType+"--"+deptName+"--"+fileId+"--"+parentId+"--"+asts+"---"+deptPhone+"--"+deptAddr+"--"+deptEmail+"--"+deptUrl+"--"+deptIntr);
	// 设置按钮不可点击
	$("#submit").attr("disabled",true);

	
	
	
	
	
	
	
	$.ajax({
		url:"<%=request.getContextPath() %>/module/dept/ckDeptExist",
		data:{fileId: fileId},
		type:'post',
		success:function(result){
			if(result=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
			// 设置按钮可点击
        	$("#submit").removeAttr("disabled");
			if(result.data=="true"){
				$.messager.alert("提示！","该部门已存在,请选择其他部门!");
				$("#deptName2").combobox("setValue","");
				$("#parentId").combotree("setValue","");
				return ;
			}else if (result.data=="false"){
				
				
				
				$.ajax({
					url:"<%=request.getContextPath() %>/module/dept/submit",
					data:{
						fileId: fileId,
						isFileDept: isFileDept,
						deptType: deptType,
						deptName: deptName,
						parentId: parentId,
						asts: asts,
						deptPhone: deptPhone,
						deptAddr: deptAddr,
						deptEmail: deptEmail,
						deptUrl: deptUrl,
						deptIntr: deptIntr
					},
					type:"post",
					success:function(result){
						if(result == "no-login"){
							top.location="<%=request.getContextPath() %>/login.jsp"
			        	}
						if(result.data=="true"){
							$.messager.confirm("提示！", "部门添加成功，是否继续添加？",
					                function(r) {
					                    if (r) {
					                    	window.location = '<%=request.getContextPath()%>/module/dept/bmadd';
					                    } else {
					                        window.location = '<%=request.getContextPath()%>/module/dept/bmgl';
					                    }
					                });
						}else{
							$.messager.alert("错误！",result.errorMsg+"，添加失败");
						}
					}
				});
				
				
				
				
				
				
				<%-- $.ajax({
					url:"<%=request.getContextPath() %>/module/dept/ckDeleteDeptExist",
					data:{deptName: deptName},
					type:'post',
					success:function(result){
						if(result.data=="true"){
							$.messager.confirm("提示！","该部门已被删除是否恢复？",function(r){
								if(r){
									location.href="<%=request.getContextPath() %>/module/dept/recoverDeleteDept?deptName="+deptName;
								}else{
									return; 
								}
							});
						}else if (result.data=="false"){
							$.ajax({
								url:"<%=request.getContextPath() %>/module/dept/submit",
								data:{
									fileId: fileId,
									isFileDept: isFileDept,
									deptType: deptType,
									deptName: deptName,
									parentId: parentId,
									asts: asts,
									deptPhone: deptPhone,
									deptAddr: deptAddr,
									deptEmail: deptEmail,
									deptUrl: deptUrl,
									deptIntr: deptIntr
								},
								type:"post",
								success:function(result){
									if(result.data=="true"){
										$.messager.confirm("提示！", "部门添加成功，是否继续添加？",
								                function(r) {
								                    if (r) {
								                    	window.location = '<%=request.getContextPath()%>/module/dept/bmadd';
								                    } else {
								                        window.location = '<%=request.getContextPath()%>/module/dept/bmgl';
								                    }
								                });
									}else{
										$.messager.alert("错误！",result.errorMsg+"，添加失败");
									}
								}
								
								
							});
						}else if(result.data=="error"){
							$.messager.alert("错误！",result.errorMsg+"，添加失败");
						}
					}
					
				}); --%>
			}else if(result.data=="error"){
				$.messager.alert("错误！",result.errorMsg+"，添加失败");
			}
		}
	});
	
	
	
	
	
}

// 取消
function cancel() {
	window.location = '<%=request.getContextPath()%>/module/dept/bmgl';
}

</script>
</html>