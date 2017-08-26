<%@ page language="java" contentType="text/html; charset=utf-8"  import="com.vkl.ckts.common.entity.UserEntity,com.vkl.ckts.common.constr.Constrant,com.vkl.ckts.common.utils.Base64Utils"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	UserEntity user = (UserEntity)session.getAttribute(Constrant.S_USER_SESSION);
	if(user != null){
		user.setUserName(Base64Utils.decode(user.getUserName()));
	}
	
%>
<!doctype html>
<html>
<head>
	<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
	<meta http-equiv="Expires" content="0" />	
</head>

<body>
<div >
	<div id="divPlugin" class="plugin"></div>	
</div>
<input value="${videoConfig.nvrIp }" id="nvrIp" type="hidden"/>
<input value="${videoConfig.nvrUser }" id="nvrUser" type="hidden"/>
<input value="${videoConfig.nvrPwd }" id="nvrPwd" type="hidden"/>
<input value="${videoConfig.nvrChannel }" id="nvrChannel" type="hidden"/>
<input value="${videoConfig.nvrPort }" id="nvrPort" type="hidden"/>
<script
	src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/static/js/webVideoCtrl.js"></script>
<script
	src="<%=request.getContextPath()%>/static/js/loadVideo.js"></script>
</body>
</html>