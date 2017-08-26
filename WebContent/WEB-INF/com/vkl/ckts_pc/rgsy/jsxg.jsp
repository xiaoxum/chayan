<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8">
<title>角色修改</title>
<!-- 引入easyui框架样式表 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/easyui.css">
<!-- 引入图标样式表 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/icon.css">
<!-- 引入自定义样式表 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/css.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/demo.css">
<!-- 引入jQuery -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<!-- 扩展Jquery -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/extJquery.js"
	charset="utf-8"></script>
<!-- 引入EasyUI -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
<!-- 引入EasyUI中文脚本 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
<!-- 自定义数据验证js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/custom-validate.js"></script>
</head>
<body>

	<div class="easyui-panel" style="width: 100%; height: auto;"
		title="角色修改">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<TD width="200" class="bgc"><span class="star">*</span>角色名称</TD>
				<TD colspan="3"><input id="roleName"
					value="${requestScope.role.roleName }" class="easyui-textbox"
					style="width: 80%;" /></TD>
			</tr>
			<TR>
				<TD class="bgc"><span class="star">*</span>任务描述</TD>
				<TD colspan="3"><input id="roleIntr"
					value="${requestScope.role.roleIntr }" class="easyui-textbox"
					style="width: 80%;" /></TD>
			</TR>
			<TR>
				<TD class="bgc"><span class="star">*</span>权限名称</TD>
				<TD colspan="3"><select class="easyui-combotree"
					data-options="multiple: true,cascadeCheck:false,onCheck:checkNode"
					url="<%=request.getContextPath()%>/module/role/findMenuList?parentMenu=0"
					id="menuId" style="width: 80%;"></select></TD>
			</TR>
			<TR>
				<TD class="bgc" colspan="6">
					<button type="button" class="easyui-linkbutton" id="submit"
						data-options="iconCls:'icon-ok'" onclick="update();">提交</button>
					<button type="button" class="easyui-linkbutton"
						data-options="iconCls:'icon-undo'" onclick="cancel();">取消</button>
				</TD>
			</TR>
		</table>


	</div>
</body>
<script type="text/javascript">
$(function(){
	
	$("#menuId").combotree({
	       onCheck:function(node,checked){  
	    	   var tree = $('#menuId').combotree('tree');
	    	   if(checked){
		    	   //当点击 checkbox 时触发
		    	   var  node1=$(tree).tree('getParent',node.target); 
		    	   if(node1!=null){
		    		   $(tree).tree('check', node1.target);     
		    	   }
		       /*  var nodes=$(tree).tree("getChildren",node.target);
		    		  //选中父节点
		    	  $.each(nodes,function(index,node2){
		    		 $(tree).tree('check', node2.target);
		    	  })  */
	    	   }else{
	    		   var pnode=$(tree).tree('getParent',node.target);
	    		    if(pnode!=null){
	    			  var che=false;
		    		  var nodes=$(tree).tree("getChildren",pnode.target)
		    		   $.each(nodes,function(index,node1){
		    			 if(node1.checked){
		    				 che=true;
		    			 }
		    		   })
		    		   if(!che){
		    			   $(tree).tree('uncheck', pnode.target);
		    		   } 
	    		     }
	    		    //获取子节点
	    		    var nodes=$(tree).tree("getChildren",node.target);
		    		  //选中父节点
		    	    $.each(nodes,function(index,node2){
		    	    	 if(node2.checked){
		    	    		 $(tree).tree('check', node.target);
		    	    		 return;
		    			 }
		    	      }) 
	    	   }
	      }
	})
	
	$("#menuId").combotree({
		onLoadSuccess: function () { 
			//加载完成后,设置选中项
			var cdid = '${requestScope.menuId}';
			$("#menuId").combotree('setValues',cdid);
		}
	});
	
	
});
/* $("#menuId").combotree({
	onLoadSuccess: function () { 
		//加载完成后,设置选中项
		var menuId = '${requestScope.menuId}';
		$("#menuId").combotree('setValues',menuId);
	}
}); */

function checkNode(node){
	  var  node1=$(tree).tree('getParent',node.target);          //得到父节点
	    $("#cdid").tree('check', node1.target); 
	
}

function update(){
	// 角色名称
	var roleName = removeAllSpace($("#roleName").textbox("getValue"));
	// 任务描述
	var roleIntr = $("#roleIntr").textbox("getValue");
	// 权限名称
	var menuId = $("#menuId").combotree("getValues");
	if (validateEmpty(roleName,"角色名称不可为空")) {
        return;
    }
	if (validateEmpty(roleIntr,"任务描述不可为空")) {
        return;
    }
	if (validateEmpty(menuId,"权限名称不可为空")) {
        return;
    }
	// 设置按钮不可点击
	$("#submit").attr("disabled",true);
	if(roleName != '${role.roleName}'){
		$.ajax({
			url:'<%=request.getContextPath()%>/module/role/ckRoleExist',
			data:{roleName:roleName},
			type:'post',
			success:function(result){
				if(result=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				// 设置按钮可点击
	        	$("#submit").removeAttr("disabled");
				if(result.data=="true"){
					$.messager.alert("提示！","该角色已存在！");
					return;
				}else if(result.data=="error"){
					$.messager.alert("错误！",result.errorMsg+"，添加失败");
				}else if(result.data=="false"){
					$.ajax({
						url:'<%=request.getContextPath()%>/module/role/updateRole',
						data:{
							id: '${role.id}',
							roleName: roleName,
							roleIntr: roleIntr,
							menuId: menuId.toString()
						},
						type:'post',
						success:function(result){
							if(result=="no-login"){
								top.location="<%=request.getContextPath() %>/login.jsp"
				        	}
							if(result.data=="true"){
								$.messager.confirm("提示！", "角色修改成功",
						                function(r) {
						                    if (r) {
						                    	window.location = '<%=request.getContextPath()%>/module/role/jsgl';
						                    } else {
						                        window.location = '<%=request.getContextPath()%>/module/role/jsgl';
						                    }
						                });
							}else{
								$.messager.alert("错误！",result.errorMsg+"，修改失败");
							}
						}
						
					});
				}
			}
		});
	}else{
		$.ajax({
			url:'<%=request.getContextPath()%>/module/role/updateRole',
			data:{
				id: '${role.id}',
				roleName: roleName,
				roleIntr: roleIntr,
				menuId: menuId.toString()
			},
			type:'post',
			success:function(result){
				if(result=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				if(result.data=="true"){
					$.messager.confirm("提示！", "角色修改成功",
			                function(r) {
			                    if (r) {
			                    	window.location = '<%=request.getContextPath()%>/module/role/jsgl';
			                    } else {
			                        window.location = '<%=request.getContextPath()%>/module/role/jsgl';
			                    }
			                });
				}else{
					$.messager.alert("错误！",result.errorMsg+"，修改失败");
				}
			}
			
		});
	}
}

//取消
function cancel() {
	window.location = '<%=request.getContextPath()%>/module/role/jsgl';
}
</script>
</html>