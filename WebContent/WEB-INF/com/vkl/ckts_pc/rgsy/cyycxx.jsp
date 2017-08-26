<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>车辆查验异常详情</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/zDrag.js"></script>
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/zDialog.js"></script>
    <style>
      dl{ float:left; margin:10px;}
	  dt,dd{ margin:5px;}
    
    </style>
</head>
  

<body>

  <div class="easyui-accordion" style="width:100%; margin-bottom:5px;">
 <div title="车辆查验异常信息"  >
 <table width="100%" cellspacing="0" cellpadding="0">
    <tr>
    <td style="width:200px;" class="bgc">业务类型</td><td>${exc.operTypeName }</td>
    <td style="width:200px;" class="bgc">查验员</td><td>${exc.viewName }</td>
    <td style="width:200px;" class="bgc">查验区</td><td>${exc.orginName }</td>
    </tr>
    <tr>
    <td class="bgc">测验时间</td><td>${exc.date }</td>
    <td class="bgc">车辆类型</td><td width="">${exc.cllxName }</td>
    <td class="bgc">异常项</td><td width="">${exc.exceptName }</td>
    </tr>
 
    
 </table>
 </div>
 </div>
  <div class="easyui-accordion" style="width:100%;">
 <div title="照片和视频查看"  >
 <!--照片位置开始-->
 
 <div >
 	<c:forEach items="${allPic }" var="pic">
	   <dl>
	     <dt><img src="${pic.picUrl }"   width="230" height="165"/></dt>
	     <dd>${pic.picName }</dd>
	   </dl>
   </c:forEach>
 </div>
  <!--照片位置结束-->
  
  
  <!--视频位置开始-->
 <div style="clear:both;">
 <c:forEach items="${allVedio}" var="vedio">
   <dl>
     <dt onclick="video('${vedio.vedioUrl }');">
     	<img src="" width="230" height="165"/>
     	 
     </dt>
     <dd>${vedio.srln}${vedio.vedioType}</dd>
   </dl>
   </c:forEach>
 </div>
  <!--视频位置结束-->
 </div>
 </div>
 <div style="clear:both; background:#eff6fe; width:100%;" class="easyui-panel">
  <%-- <button  type="button" style="float:right; margin:5px 10px ; height:30px; cursor:pointer;"  onclick="deal('${exc.excId }');">终止查验</button> --%>
    <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-undo'" style="float:right; margin:5px 10px ; height:30px; cursor:pointer;"  onclick="window.history.go(-1)">返回</button>
 </div>
 
 



</body>
<script type="text/javascript">

<!--视频播放弹窗-->
function video(src){
	alert(src)
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 400;
    diag.ShowButtonRow=true;
	diag.Title = "视频播放";
    diag.InnerHtml="<embed src='"+src+"' type='video/x-ms-wmv' width=600 height=400 autoStart='1' showControls='1' showstatusbar='1' />";
	<!--写入播放的标签目前只有IE能播放-->
	diag.show();
}

	//处理异常
	function deal(id){
		window.location.href="<%=request.getContextPath()%>/statistic/except/dealExcept?excId="+id;
	}
</script>
</html>
