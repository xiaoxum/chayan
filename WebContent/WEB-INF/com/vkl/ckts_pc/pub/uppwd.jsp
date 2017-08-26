<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>密码修改</title>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDrag.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDialog.js"></script>
</head>
<body>
     <p style="margin-top: 20px;"> 
     <label  > 原密码 </label>
     <input class="easyui-textbox" style="width:90%; height:30px;" id="password"   type="password" placeholder="请输入原密码"/>
    </p>
     <p> 
     <label > 新密码 </label>
     <input class="easyui-textbox" style="width:90%; height:30px;" id="newpassword1" type="password"  placeholder="请输入新密码" /> 
     </p>
     <p> 
     <label > 确认新密码 </label>
     <input class="easyui-textbox" style="width:90%; height:30px;"  id="newpassword2" type="password" placeholder="请输入新密码" /> 
     </p> 
     <div style="  text-align:right;margin:20px;"  >
     <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-ok'"  onclick="up()">提交</button>
     <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-redo'" onclick="back()">取消</button>                         
     </div>
</body>
<script type="text/javascript">
  function back(){
	  
	  parentDialog.close();
	  
  }
  
  function up(){
	  
	  var pass1 = $("#newpassword1").textbox('getValue');
	  var pass2 = $("#newpassword2").textbox('getValue');
	  if(pass1!=pass2){
		  $.messager.alert("提示！", "2次输入新密码不一致！");
		  
		  return;
	  }
	 
	  $.ajax({
		    cache: true,
		    type: "POST",
			url: "<%=request.getContextPath()%>/module/base/uppwdAction",
			data:{
				 "password" : $("#password").textbox('getValue'), 
				 "newpassword" : pass2
			      }, 
		    async: false,
		    dataType:"text",
			error: function(request) {
				alert("系统操作繁忙请稍后！")
			    },
			success: function (data) {
				 if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
		         }
				 if(data=="密码修改成功!"){
					 parentDialog.close();
				 }else{
					 alert(data);
				 }
			}
	  });
				
  }
  
  
  
</script>
</html>