<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>打印示例</title>
 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/css.css">
<style type="text/css">
 tr {
	  padding-left: 10px; 
}
</style>
</head>

<body >
 <!--打印页面关键代码部分-->
    <OBJECT   id="WebBrowser"   classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"   height="0"   width="0"   VIEWASTEXT></OBJECT>
     <!--打印页面关键代码部分-->
<!--startprint-->
  <table  width="443" height="450" border="1" cellpadding="0" cellspacing="0" style="border:2px solid #000000;color: #000000; "  >
    <tr height="10%" >
      <td colspan="4" align="center" style="border:1px solid #000000; "><h3 >查验受理单</h3></td>
    </tr>
    <tr height="20%">
      <td colspan="4" align="center" style="border:1px solid #000000; "><img src="${img }" id="img"/></td>
    </tr>
    <tr height="10%">
      <td width="15%" style="border:1px solid #000000; ">姓名 </td>
      <td width="35%"  style="border:1px solid #000000; ">${ckInfoDto.name}</td> 
      <td width="15%" style="border:1px solid #000000; "  >电话 </td>
      <td width="35%"  style="border:1px solid #000000; ">${ckInfoDto.phone}</td>
    </tr>
    <tr height="10%">
      <td style="border:1px solid #000000; ">受理业务 </td>
      <td id="copyOperType" style="border:1px solid #000000; ">${ckInfoDto.operType }</td>
      <td style="border:1px solid #000000; ">受理时间</td>
      <td id="riqi" style="border:1px solid #000000; "><fmt:formatDate value="${ckInfoDto.createDate }" type="date" dateStyle="full" /></td> 
    </tr>
    <tr height="10%">
      <td colspan="2" style="border:1px solid #000000; " > 车辆品牌/型号</td>
      <td colspan="2" id="copyPpxh" style="border:1px solid #000000; ">${ckInfoDto.ppxh }</td>
    </tr>
    <tr height="10%">
      <td  colspan="2" style="border:1px solid #000000; ">车辆识别代号</td>
      <td colspan="3" id="cjh" style="border:1px solid #000000; ">${ckInfoDto.clsbdh }</td>
    </tr>
    <tr height="30%">
      <td  colspan="5" style="border:1px solid #000000; ">注意事项</td>
     
    </tr>
  </table>
  
  <!--endprint-->
  <button onclick="doPrint()">打印</button>    
</body>
<script type="text/javascript">
function doPrint() {    
	bdhtml=window.document.body.innerHTML;    
	sprnstr="<!--startprint-->";    
	eprnstr="<!--endprint-->";    
	prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);    
	prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));    
	window.document.body.innerHTML=prnhtml;    
	window.print();

	window.location.reload();//打印后返回页面
	} 
	 

</script>
</html>
