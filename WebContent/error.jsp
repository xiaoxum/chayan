<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
   var error='<%=request.getParameter("msg")%>';
   $(document).ready(function(){
	    if("no-login"==error){
	    	/* alert("当前未登录,请先登录"); */
	    	top.location='<%=request.getContextPath()%>/login.jsp';
	    }
	    if("no-author"==error){
	    	alert("当前未没有权限");
	    	window.location='<%=request.getContextPath()%>/module/base/home';
	    }
	    if("sys-error"==error){
	    	alert("系统异常");
	    	window.location='<%=request.getContextPath()%>/module/base/to500';
	    }
	    
   });
</script>
</head>

<body>

</body>
</html>