<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>机动车查验受理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
</head>

<body>
 <table width="100%;"  border="1" cellpadding="0" cellspacing="0" align="center" bordercolor="#95B8E7" >
  <tr>
       <td height="75" align="center" style=" font-size:16px; color:#003D71; font-weight:bold;" >机动车注册、转移、注销登记/转入申请表</td>
    <td height="75" align="center" style=" font-size:16px; color:#003D71; font-weight:bold;">机动车抵押登记/质押备案申请表 </td>
    <td height="75" align="center" style=" font-size:16px; color:#003D71; font-weight:bold;">机动车变更登记/备案申请表</td>
  </tr>
  <tr bgcolor="#eff6fe">
     <td height="75" align="center"><div class="sqbtn" onClick="window.location='<%=request.getContextPath()%>/module/servacpt/tableone'">填写申请</div></td>
    <td height="75" align="center"><div class="sqbtn" onClick="window.location='<%=request.getContextPath()%>/module/servacpt/tabletwo'">填写申请</div></td>
    <td height="75" align="center"><div class="sqbtn" onClick="window.location='<%=request.getContextPath()%>/module/servacpt/tablethree'">填写申请</div></td>
  </tr>
  
</table>
 
 




</body>
</html>
