<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>外廓装置合格率统计</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/echarts.common.min.js"></script>
</head>

<body>
 <div id="p" class="easyui-panel" title="查询" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
    <tr>
     <td class="bgc">查验区</td>
     <td  ><select class="easyui-combobox" style="width:80%; height:30px;" id="chkpt">
                <option value="">---请选择查验区---</option>
                <c:forEach items="${allChkpt }" var="chkpt">
                	<option value="${chkpt.organCode }">${chkpt.organName }</option>
            	</c:forEach>
                </select>
                </td>
     
     <td class="bgc" >车辆类型</td><td>
     <select class="easyui-combobox" style="width:80%; height:30px;" id="cllx">
                <option value="">---请选择车辆类型---</option>
                <c:forEach items="${allCllx }" var="cllx">
                	<option value="${cllx.parentCllx }">${cllx.parentName }</option>
                </c:forEach>
                </select>
     </td>
      <td class="bgc" >业务类型</td><td>
     <select class="easyui-combobox" style="width:80%; height:30px;" id="type">
                <option value="">---请选择业务类型---</option>
                <c:forEach items="${allOType }" var="type">
                	<option value="${type.id }">${type.typeName }</option>
                </c:forEach>
                </select>
     </td>
     </tr>
     
     <tr>
     <td class="bgc">查验时间</td><td colspan="6"><input class="easyui-datebox" style="width:40%; height:30px;"id="start"/><label>至</label><input class="easyui-datebox" style="width:40%; height:30px;"id="end"/></td>
     </tr>
     </table>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
              <button  type="button" class="easyui-linkbutton"   data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-reload'"  onclick="reset();">重置</button>
       </div>
      </div>
      
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
      <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-print'"  onclick="window.location=''">打印统计表</button>
     </div>
  
  
  <div id="p" class="easyui-panel" title="合格率图表" style="width:100%;"> 
  <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0" id="tab">
     <thead >
         <th>查验区</th>
         <th>测量仪编号</th>
         <th>车辆类型</th>
         <th>业务类型</th> 
         <th>查验总计</th> 
         <th>合格总计</th>
         <th>合格率</th>  
     </thead>
     
     <tbody id="ddInfo">
     

     </tbody>
  </table>
</div>
<div style=" width:100%; background:#eff6fe; "  class="easyui-panel">
      <div id="pp"   class="easyui-pagination" data-options="
					total:0, 
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'Page',
                    onSelectPage:function(pageNumber){page(pageNumber)}"></div>
    </div>

<div style=" border:2px solid #95B8E7;">
 <div id="chart" style="height:400px;width:100%;"></div>  
</div>
</body>

<script>
// 重置
function reset(){
	$("#chkpt").combobox("setValue","");
	$("#cllx").combobox("setValue","");
	$("#type").combobox("setValue","");
	$("#start").textbox("setValue","");
	$("#end").textbox("setValue","");
}
// 查询统计
function search(){
	var chkpt = $("#chkpt").combobox("getValue");
	var cllx = $("#cllx").combobox("getValue");
	var operType = $("#type").combobox("getValue");
	var start = $("#start").textbox("getValue");
	var end = $("#end").textbox("getValue");
	if(chkpt == '' && cllx == '' && operType == '' && (start == null || start =='') && (end == '' || end == null)){
		$.messager.alert("提示","请填写统计查询条件！");
	}else{
		page(1);
	}
}
// 分页
function page(num){
	var chkpt = $("#chkpt").combobox("getValue");
	var cllx = $("#cllx").combobox("getValue");
	var operType = $("#type").combobox("getValue");
	var start = $("#start").textbox("getValue");
	var end = $("#end").textbox("getValue");
	var dd = {organCode : chkpt,
			cllx : cllx,
			operType : operType,
			start : start,
			end : end,
			pageNo : num,
			pageSize : 10};
	$.ajax({
		url : '<%=request.getContextPath()%>/count/overall/overallPage',
		type : 'post',
		data : dd,
		success : function(data){
			if(data=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
			$("#ddInfo").empty();
			var total = data[0];
			data = data[1];
			$.each(data,function(index,dd){
				var info;
				if(index %2 ==1){
					info = "<tr  class='rowbgcolor'>"       
						+" <td>"+dd.organName+"</td>"
						+" <td>"+dd.overall+"</td>"       
						+" <td>"+dd.parentName+"</td> "       
						+" <td>"+dd.typeName+"</td>"
						+" <th>"+dd.aCount+"</th>"
						+" <th>"+dd.cCount+"</th>"  
						+" <th>"+dd.rate+"%</th>"            
						+" </tr>"
				}else{
					info = "<tr>"       
						+" <td>"+dd.organName+"</td>"
						+" <td>"+dd.overall+"</td>"       
						+" <td>"+dd.parentName+"</td> "       
						+" <td>"+dd.typeName+"</td>"
						+" <th>"+dd.aCount+"</th>"
						+" <th>"+dd.cCount+"</th>"  
						+" <th>"+dd.rate+"%</th>"              
						+" </tr>"
				}
				$("#ddInfo").append(info);
			})
			$("#pp").pagination({
				total:total,
			});
			chart(data);
		}
	})
}
function chart(dt){
		var overall=[];
		var value=[];
		$.each(dt,function(index,dd){
			overall[index] = dd.overall;
			value[index] = dd.rate;
		});
		for(var i = overall.length; i < 10; i++){
			overall[i] = "";
		}
		for(var i = value.length; i < 10; i++){
			value[i] = 0;
		}
	//基于准备好的dom，初始化echarts实例
	   $("#chart").empty();
	    var myChart = echarts.init(document.getElementById('chart'));
	    var yMax = 100;
	    var dataShadow = [];	
	    option = {
	        title: {
	            text: '外廓尺寸合格率',
	        },
	        xAxis: {
	            data: overall,
	            axisLabel: {
	                inside: true,
	                textStyle: {
	                    color: '#fff'
	                }
	            },
	            axisTick: {
	                show: false
	            },
	            axisLine: {
	                show: false
	            },
	            z: 10	// 柱上显示名称
	        },
	        yAxis: {
	            axisLine: {
	                show: false
	            },
	            axisTick: {
	                show: false
	            },
	            axisLabel: {
	                textStyle: {
	                    color: '#666'
	                }
	            }
	        },
	        dataZoom: [
	            {
	                type: 'inside'
	            }
	        ],
	        series: [{
                type: 'bar',
                itemStyle: {
                    normal: {color: 'rgba(0,0,0,0.05)'}
                },
                barGap:'-100%',
                barCategoryGap:'40%',
                data: dataShadow,
                animation: true 
            },
	            {
	                type: 'bar',
	                itemStyle: {
	                    normal: {
	                        color: new echarts.graphic.LinearGradient(
	                            0, 0, 0, 1,
	                            [
	                                {offset: 0, color: '#83bff6'},
	                                {offset: 0.5, color: '#188df0'},
	                                {offset: 1, color: '#188df0'}
	                            ]
	                        )
	                    },
	                    emphasis: {
	                        color: new echarts.graphic.LinearGradient(
	                            0, 0, 0, 1,
	                            [
	                                {offset: 0, color: '#2378f7'},
	                                {offset: 0.7, color: '#2378f7'},
	                                {offset: 1, color: '#83bff6'}
	                            ]
	                        )
	                    }
	                },
	                data: value
	            }
	        ]
	    };
	
	    var zoomSize = 6;
	    myChart.on('click', function (params) {
	        console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
	        myChart.dispatchAction({
	            type: 'dataZoom',
	            startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
	            endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
	        });
	    });
		myChart.setOption(option); //填入参数
	    window.onresize = myChart.resize; 
	
}
</script>
</html>
