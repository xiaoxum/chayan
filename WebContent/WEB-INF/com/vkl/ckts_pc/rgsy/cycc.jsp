<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>查验抽查</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDrag.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDialog.js"></script>
</head>
<body>
<div class="easyui-accordion" style="width:100%; margin-bottom:5px;">
 <div title="抽查查询" >
           <table width="100%" cellspacing="0" cellpadding="0">
              <tr>
              <td width="140" class="bgc">审核结果：</td><td width="" > 
              <select class="easyui-combobox" style="width:90%; height:30px; "   id="auditFlag">
                <option value="">全部</option> 
                <option value="0">已通过</option>
                <option value="1">不通过</option>
              </select></td>
              <td width="140" class="bgc">审核人：</td><td width="16%"><input class="easyui-textbox" style="width:90%;"  id="pAuditerName"/></td>
              <td width="140" class="bgc">业务类型：</td><td width="16%">
               <select class="easyui-combobox" style="width:90%; height:30px;  "  id="typeName">
                 <option value="">全部</option>
                <c:forEach items="${operType }" var="list">
                <option value="${list.id}">${list.typeName }</option>
              </c:forEach>
              </select>
              </td>
              </tr>
              <tr >
              <td class="bgc">抽查比例：</td><td width="16%" ><input class="easyui-textbox" style="width:50%; "  id="proportion"  />%</td>
              <td class="bgc">机构名称：</td><td width="16%" ><input class="easyui-textbox" style="width:90%;"   id="organName"/></td>
              <td colspan="2">&nbsp;</td>
              </tr>
              <tr>
              <td class="bgc">业务办理时间：</td>
              <td colspan="5">
              <input class="easyui-datebox" style="width:16%; height:30px;" id="startTime" >
              <label style="width:50px;">至</label> <input class="easyui-datebox" style="width:16%; height:30px;"  id="endTime">
              </td>
              </tr> 
            </table>
             <div style=" width:100%; background:#eff6fe; text-align:right; padding:2px;"  class="easyui-panel">
              <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-redo'"  onclick="reset();">重置</button>
             </div>
             
  </div>
  </div>
   <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
             <!-- <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='cyqbasq.html'">打印</button>-->
              <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-print'"  onclick="print()">打印报告单</button>
     </div>
  <div class="easyui-accordion" style="width:100%; margin-bottom:5px;">
		<div title="抽查表单" >
			<table width="100%" cellspacing="0" cellpadding="0" id="tab">
                <thead >
                <th>选择</th><th>流水号</th><th>车辆识别代号</th><th>业务类型</th><th>号牌种类</th><th>号牌号码</th><th>机构名称</th>
                <th>业务办理时间</th><th>业务办理人</th><th>审核人</th><th>审核时间</th><th>审核结果</th><th>操作</th>
                </thead>
                 <tbody id="myTbody">
      
               </tbody>
               
          </table>
          <!--分页开始-->
          <div style=" width:100%; background:#eff6fe;"  class="easyui-panel">
      <div id="pp"  style="display:none;"  class="easyui-pagination" data-options="
					total:0,
					layout:['first','prev','links','next','last','manual'],
                    beforePageText:'当前页',
                    onSelectPage:function(pageNumber){search(pageNumber)}"></div>
    </div>
     <!--分页结束-->
		</div>
    </div>
  </body>
<script>


//详情跳转
function jump(srln,rckCount){
	window.location.href="<%=request.getContextPath()%>/module/vehicleinfo/loadcheckaudit?srln="+srln+"&rckCount="+rckCount;
};

  // 重置
function reset(){
	$("#auditFlag").combobox("setValue","");
	$("#typeName").combobox("setValue","");
	$("#proportion").textbox("setValue","");
	$("#pAuditerName").textbox("setValue","");
	$("#organName").textbox("setValue","");
	$("#startTime").datebox("setValue","");
	$("#endTime").datebox("setValue","");
}
 
  var pageNo=1;
//分页查询
function search(pageNo){
	
	$.ajax({
	    cache: true,
	    type: "POST",
		url: "<%=request.getContextPath()%>/module/spotcheck/datelist",
		data:{
			 "auditFlag" : $("#auditFlag").combobox('getValue'), 
			 "parentId" : $("#typeName").combobox('getValue'),
			 "proportion" : $("#proportion").textbox('getValue'),
			 "pAuditerName" :  $("#pAuditerName").textbox('getValue'),
			 "organName" :  $("#organName").textbox('getValue'),
			 "startTime" : $("#startTime").datebox('getValue'),
			 "endTime" : $("#endTime").datebox('getValue'),
			 "pageNo" : pageNo,
			 "pageSize" : 10
		      }, 
	    async: false,
		error: function(request) {
		            alert("系统操作繁忙请稍后！");
		        },
		success: function (data) {
			 // 清空table内容
			$("#myTbody").empty();
			for(var i=0;i<data.list.length;i++){
				// 将时间转成日期
        		var date = new Date(data.list[i].createDate);
        		Y1 = date.getFullYear() + '-';
        		M1 = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        		D1 = date.getDate() + ' ';
        		// 将时间转成日期
        		var date2 = new Date(data.list[i].auditTime);
        		Y2 = date2.getFullYear() + '-';
        		M2 = (date2.getMonth()+1 < 10 ? '0'+(date2.getMonth()+1) : date2.getMonth()+1) + '-';
        		D2 = date2.getDate() + ' ';
					  

				if(i % 2 == 1){
					var tr="<tr class='rowbgcolor texcenter'>"
						+"<td><input type='radio' value='"+data.list[i].srln+"'/></td>"
						+"<td>"+data.list[i].srln+"</td>"
						+"<td>"+data.list[i].clsbdh+"</td>"
						+"<td>"+data.list[i].typeName+"</td>"
						+"<td>"+data.list[i].hpzl+"</td>"
						+"<td>"+data.list[i].hphm+"</td>"
						+"<td>"+data.list[i].organName+"</td>"
						+"<td>"+Y1+M1+D1+"</td>"
						+"<td>"+data.list[i].operaerName+"</td>"
						+"<td>"+data.list[i].pAuditerName+"</td>"
						+"<td>"+Y2+M2+D2+"</td>"
						+"<td>"+data.list[i].auditFlag+"</td>"
			         	+"<td onClick='jump("+data.list[i].srln+","+data.list[i].rckCount+")' class='ztcolor texcenter'>详情</td>"
			         	+"</tr>";
				}else{
					var tr="<tr class='texcenter'>"
						+"<td><input type='radio' /></td>"
						+"<td>"+data.list[i].srln+"</td>"
						+"<td>"+data.list[i].clsbdh+"</td>"
						+"<td>"+data.list[i].typeName+"</td>"
						+"<td>"+data.list[i].hpzl+"</td>"
						+"<td>"+data.list[i].hphm+"</td>"
						+"<td>"+data.list[i].organName+"</td>"
						+"<td>"+Y1+M1+D1+"</td>"
						+"<td>"+data.list[i].operaerName+"</td>"
						+"<td>"+data.list[i].pAuditerName+"</td>"
						+"<td>"+Y2+M2+D2+"</td>"
						+"<td>"+data.list[i].auditFlag+"</td>"
			         	+"<td onClick='jump("+data.list[i].srln+","+data.list[i].rckCount+")' class='ztcolor texcenter'>详情</td>"
			         	+"</tr>";
			         	
				}
				// 添加tr
				$("#myTbody").append(tr);
			}
			// 显示分页栏
        	$("#pp").show();
        	// 设置总条数
        	$("#pp").pagination({
       		  total:data.totalCount,
       		});

		}
  });
			

}

</script>
</html>

