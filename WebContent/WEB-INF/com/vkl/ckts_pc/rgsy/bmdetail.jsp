<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>车辆黑名单详情</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
    <style>
      dl{ float:left; margin:10px;}
	  dt,dd{ margin:5px;}
    
    </style>
</head>
  

<body>

  <div class="easyui-accordion" style="width:100%; margin-bottom:5px;">
 <div title="车辆黑名单详情"  >
 <table width="100%" cellspacing="0" cellpadding="0">
    <tr>
    <td style="width:160px;" class="bgc">车辆品牌</td><td>${detail.clpp }</td> 
    <td style="width:160px;" class="bgc">车牌种类</td><td>${detail.hpzl }</td>
    <td style="width:160px;" class="bgc">业务类型</td><td>${detail.operName }</td>
    </tr>
    <tr>
    <td class="bgc">车牌号码</td><td>${detail.hbhm }</td>
    <td class="bgc">车辆识别代码</td><td width="">${detail.clsbdh }</td><td class="bgc" >车辆类型</td><td>${detail.parentName }</td>
    <!-- <td class="bgc">车辆车架号</td><td width="">132465</td> -->
    </tr>
    <tr>
    <td class="bgc">重点查验项：</td><td colspan="6">${detail.implCkPros }</td>
    </tr>
    
    
 </table>
 </div>
 </div>
 
 <div class="easyui-accordion" style="width:100%; ">
 <div title="车辆查验记录" >
  <table width="100%" cellspacing="0" cellpadding="0" id="tab">
     <thead><th>查验业务</th><th>查验时间</th><th>查验员</th><th>查验区名称</th><th>车辆类型</th><th>不通过项</th><th>原因说明</th></thead>
     <c:forEach items="${record }" var ="r">
     	<tr><td>${r.operName }</td><td>${r.chektTime }</td><td>${r.viewer }</td><td>${r.organName }</td><td>${r.parentName }</td><td>${r.proName }</td><td>${r.unqualAnnc }</td></tr>
  	 </c:forEach>
  </table>
 </div>
 </div>
 
 
 <div style="clear:both; background:#eff6fe; width:100%;" class="easyui-panel">
  <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" style="float:right; margin:5px 10px ; height:30px; cursor:pointer;"  onclick="window.history.go(-1)">返回</button>
  <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="float:right; margin:5px 10px ; height:30px; cursor:pointer;"  onclick="change();">确定</button>
  <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-print'" style="float:right; margin:5px 10px ; height:30px; width:100px; cursor:pointer;"  onclick="dy()">打印表单</button>
  
  <div style="float:right; margin:0px 20px; line-height:40px; color:#005aa9; font-size:14px;">是否解除黑名单：<input type="radio" name="statu" value="1" /><label style="padding-right:20px;">是</label><input type="radio" checked="checked" value="0" name="statu" /><label style="padding-right:20px;">否</label></div>
 </div>
 
 



</body>
<script type="text/javascript">
	function change(){
		var b = $("input[name=statu]:checked").val();
		if(b==1){
			if($.messager.confirm("提示","确定解除改黑名单？")){
				window.location.href="<%=request.getContextPath()%>/veh/blackName/changeWhite?clsbdh=${detail.clsbdh }";				
			}
		}
	}
</script>
</html>