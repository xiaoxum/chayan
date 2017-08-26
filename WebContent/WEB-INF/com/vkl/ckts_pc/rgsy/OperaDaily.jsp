<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >>
<title>操作日志</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui-lang-zh_CN.js"></script>
	 <!-- 自定义数据验证js -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/custom-validate.js"></script>
</head>

<body>
   <div id="p" class="easyui-panel" title="查询" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
     <td class="bgc">用户账号</td><td><input class="easyui-textbox" style="width:80%;" id="loginName"/></td>
     <td class="bgc">开始时间</td><td><input class="easyui-datetimebox" style="width:80%; height:30px;" id="start"/></td>
     <td class="bgc">结束时间</td><td><input class="easyui-datetimebox" style="width:80%; height:30px;" id="end"/></td>
     </tr>
     </table>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
              <button  type="button" class="easyui-linkbutton"   data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-reload'"  onclick="reset();">重置</button>
       </div>
      </div>
      
  
  <div id="p" class="easyui-panel" title="操作日志表单" style="width:100%;"> 
  <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0" id="tab">
     <thead >
         <th>序号</th>
         <th>用户账号</th>
         <th>操作人</th>
         <th>操作</th>
         <th>IP地址</th>
         <th>操作说明</th>
         <th>操作时间</th>
     </thead>
     
     <tbody id="main">
     
   <!--   
     <tr>
         <td><input type="radio" value="" /></td>
         <td>1</td>
         <td>zhangsan</td>
         <td>张三</td>
         <td>登录系统</td>
         <td>192.168.11.10</td>
         <td>南昌检测站</td>
         <td>2017-05-10 16:20</td>
     </tr> -->
     </tbody>
  </table>
</div>
<div style=" width:100%; background:#eff6fe; "  class="easyui-panel">
      <div id="pp"   class="easyui-pagination" data-options="
					total:0, 
					pageSize: 20,
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'Page',
                    onSelectPage:function(pageNumber){ page(pageNumber);}"></div>
    </div>

</body>
<script type="text/javascript">
	function page(num){
		var start = $("#start").textbox("getValue");
		var end = $("#end").textbox("getValue");
		var operName = $("#loginName").textbox("getValue");
		if(removeAllSpace(operName) != ""){
			if (/[\u4e00-\u9fa5]/.exec(operName)) {
				$.messager.alert("提示！","用户名格式不正确");
		        return;
		    }
		}
		var sdate;
		var edate;
		var date = new Date();
		Y = date.getFullYear() + '-';
		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		D = date.getDate() + ' ';
		var nowdate = Y+M+D+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		nowdate = sdate = new Date(nowdate.replace("-", "/").replace("-", "/"));
		if(removeAllSpace(start) !=""){
			sdate = new Date(start.replace("-", "/").replace("-", "/"));
			if(sdate>nowdate){
				$.messager.alert("提示","操作开始时间不能后于当前时间");
				return;
			}
		}
		if(removeAllSpace(end) !=""){
			edate = new Date(end.replace("-", "/").replace("-", "/"));
			if(edate>nowdate){
				$.messager.alert("提示","操作结束时间不能后于当前时间");
				return;
			}
		} 
		if(removeAllSpace(start) !="" && removeAllSpace(end) != ""){
			sdate = new Date(start.replace("-", "/").replace("-", "/"));
			edate = new Date(end.replace(/-/g,"/").replace("-", "/"));
			if(sdate > edate ){
				$.messager.alert("提示","操作开始时间不能前于结束时间");
				return;
			}
		}
		
		var total;
		$.ajax({
			url : '<%=request.getContextPath()%>/statistic/log/log',
			post : 'post',
			data : {pageNo : num, 
					pageSize : 20, 
					loginName:operName, 
					start : start,
					end : end},
			success : function(data){
				if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				$("#main").empty();
				total = data[0];
				data = data[1];
				$.each(data,function(index,dd){
					var info;
					if(index % 2 == 1){
						info = "<tr class='rowbgcolor'>"
				         +"<td>"+(index+1)+"</td>"
				         +"<td>"+dd.operName +"</td>"
				         +"<td>"+dd.userName+"</td>"
				         +"<td>"+dd.operation +"</td>"
				         +"<td>"+dd.ip +"</td>"
				         +"<td>"+dd.operIntr+"</td>"
				         +"<td>"+dd.date +"</td>"			        
				         +" </tr>"
					}else{
						info = "<tr>"
					         +"<td>"+(index+1)+"</td>"
					         +"<td>"+dd.operName +"</td>"
					         +"<td>"+dd.userName+"</td>"
					         +"<td>"+dd.operation +"</td>"
					         +"<td>"+dd.ip +"</td>"
					         +"<td>"+dd.operIntr+"</td>"
					         +"<td>"+dd.date +"</td>"			        
					         +" </tr>"
					}
				    $("#main").append(info);
				})
				$("#pp").pagination({
					  total:total,
					  
					  });
			
			}
		})
	}
	function search(){
		var start = $("#start").textbox("getValue");
		var end = $("#end").textbox("getValue");
		var operName = $("#loginName").textbox("getValue");
		if((start == ''|| start == null) && (end == ''||end == null) && (operName == ''||operName == null)){
			$.messager.alert("提示","请填写查询条件！");
		}else{
			page(1);
		}
		
	}
	function reset(){
		$("#start").textbox("setValue","");
		$("#end").textbox("setValue","");
		$("#loginName").textbox("setValue","");
	}
</script>
</html>
