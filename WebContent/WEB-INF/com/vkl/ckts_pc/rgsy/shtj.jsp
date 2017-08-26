<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>当天审核量统计</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/echarts.common.min.js"></script>
</head>

<body>

  

   
  
  <div id="p" class="easyui-panel" title="审核统计" style="width:100%;"> 
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0" id="tab">
     <thead >
         <th width="170">未审核(俩次)</th>
         <th width="170">已审核（俩次）</th>
         <th width="170">总计</th>
         <th width="170">审核通过（俩次）</th>
         <th width="170">审核不通过（俩次</th>
     </thead>
     
      <tbody id="ddInfo">
	      <tr>
	        <td>${shtj.wshsl }</td>
	        <td>${shtj.yshhsl }</td>
	        <td>${shtj.zsl }</td>
	        <td>${shtj.tgsl }</td>
	        <td>${shtj.wtgsl }</td>
	      </tr>
     </tbody>
  </table>
</div>



</body>
<script>

</script>
</html>

