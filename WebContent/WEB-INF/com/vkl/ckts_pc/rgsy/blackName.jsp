<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>车辆黑名单</title>
</head>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDrag.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDialog.js"></script>
    <style>
      dl{ float:left; margin:10px;}
	  dt,dd{ margin:5px;}
    
    </style>
<body>

</body>
<div class="easyui-accordion" style="width:100%; margin-bottom:5px;">
 <div title="黑名单查询" >
           <table width="100%" cellspacing="0" cellpadding="0" >
              <tr>
              <td width="140" class="bgc">车牌号码：</td><td width="" ><input class="easyui-textbox" style="width:100%;"/></td>
              <td width="140" class="bgc">车辆类型：</td><td width="">
              <select class="easyui-combobox" style="width:80%; height:30px;" id="cllx">
                <option value="">---请选择---</option>
                <c:forEach items="${allCllx }" var="ac">
                	<option value="${ac.parentCllx}">${ac.parentName }</option>
                </c:forEach>
                </select>
              </td>
              <td width="140" class="bgc">业务类型：</td><td width="">
               <select class="easyui-combobox" style="width:100%; height:30px; " id="oper">
                <option value="">---请选择---</option>
                <c:forEach items="${allOper }" var="ao">
                	<option value="${ao.id }">${ao.typeName }</option>
                </c:forEach>
              </select>
              </td>
              </tr>
              <tr >
              <td class="bgc">加入黑名单时间：</td>
              <td colspan="5">
              <input class="easyui-datebox" style="width:25%; height:30px;">
              <label style="width:50px;">至</label> <input class="easyui-datebox" style="width:25%; height:30px;">
              </td>
              </tr> 
            </table>
             <div style=" width:100%; background:#eff6fe; text-align:right; padding:2px;"  class="easyui-panel">
              <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-redo'"  onclick="search();">重置</button>
              </div>
            
  </div>
  </div>
  <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
              <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-print'"  onclick="dy()">打印申请单</button>
     </div>
  <div class="easyui-accordion" style="width:100%; margin-bottom:5px;">
		<div title="车辆黑名单表单" >
			<table width="100%" cellspacing="0" cellpadding="0" id="tab">
                <thead >
                <th>选择</th><th>流水号</th><th>车辆类型</th><th>车牌号码</th><th>业务类型</th><th>车主姓名</th>
                <th>查验点</th><th>操作人</th><th>加入黑名单时间</th><th>操作</th>
                </thead>
                <tbody id="tbody">
  				</tbody>
          </table>
          <!--分页开始-->
          <div style=" width:100%; background:#eff6fe;"  class="easyui-panel">
      <div id="pp"   class="easyui-pagination" data-options="
					total:0, 
					pageSize:15,
					layout:['first','prev','links','next','last','manual'],
                    beforePageText:'当前页',
                    onSelectPage:function(pageNo){page(pageNo);}"></div>
    </div>
     <!--分页结束-->
		</div>
    </div>
<script>
$(function(){
	if("${flag11}" == "upd"){
		$.messager.alert("提示","解除黑名单成功！");
		<%request.getSession().setAttribute("flag11", null);%>
	}
	search(1);
})
 function search(num){
	 var currOperaType = $("#oper").combobox("getValue");
	 var cllx = $("#cllx").combobox("getValue");
	 
	 var dd = {pageNo : num, pageSize : 15, currOperaType : currOperaType, cllx : cllx};
	 $.ajax({
		 url : '<%= request.getContextPath()%>/veh/blackName/blackPage',
		 type : 'post',
		 data : dd,
		 success : function(data){
			 var total = data[0];
			 data = data[1];
			 $("#tbody").empty();
			 $.each(data,function(index,dt){
				// 将时间戳转成时间
	        		var date = new Date(dt.joinBlackTime);
	        		Y = date.getFullYear() + ' -';
	        		M = ' '+(date.getMonth()+1) + ' -';
	        		D = ' '+date.getDate() + ' ';

				 var info;
				 if(index % 2 == 1){
					 info = "<tr class='rowbgcolor'>"
			                +"<td class='texcenter'><input type='radio' /></td>"
			                +"<td>"+dt.srln+"</td>"
			                +"<td>"+dt.parentName+"</td>"
			                +"<td>"+dt.hbhm+"</td>"
			                +"<td>"+dt.operName+"</td>"
			                +"<td>"+dt.vehOwer+"</td>"
			                +"<td>"+dt.organName+"</td>"
			                +"<td>"+dt.creater+"</td>"
			                +"<td>"+Y+M+D+"</td>"
			                +"<td onClick='window.location='<%=request.getContextPath()%>/veh/blackName/bNameDetail?id="+dt.id+" class='ztcolor texcenter'>详情</td>"
			                +"</tr>"
				 } else{
					 info = "<tr>"
			                +"<td class='texcenter'><input type='radio' value='"+dt.id+"' /></td>"
			                +"<td>"+dt.srln+"</td>"
			                +"<td>"+dt.parentName+"</td>"
			                +"<td>"+dt.hbhm+"</td>"
			                +"<td>"+dt.operName+"</td>"
			                +"<td>"+dt.vehOwer+"</td>"
			                +"<td>"+dt.organName+"</td>"
			                +"<td>"+dt.creater+"</td>"
			                +"<td>"+Y+M+D+"</td>"
			                +"<td onClick=window.location='<%=request.getContextPath()%>/veh/blackName/bNameDetail?id="+dt.id+"' class='ztcolor texcenter'>详情</td>"
			                +"</tr>"
				 }
				 $("#tbody").append(info);
				 
			 });
			 $("#pp").pagination({
				 total:total,
			 })
		 }
	 })
 }
 <!--弹窗打印-->
 function dy(){
		
		var diag = new Dialog();

	diag.Width = 500;

	diag.Height = 500;
    diag.ShowButtonRow=true;
	diag.Title = "打印申请";
    diag.InvokeElementId="forlogin"
	diag.OKEvent = function(){
		doPrint();
		};//点击确定后调用的方法

	diag.show();
	
    
		
		}
</script>
</html>
