<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>车辆信息详情</title>
<!-- 引入easyui框架样式表 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/easyui.css">
<!-- 引入图标样式表 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/icon.css">
<!-- 引入自定义样式表 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/css.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/demo.css">
<!-- 引入jQuery -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<!-- 扩展Jquery -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/extJquery.js"
	charset="utf-8"></script>
<!-- 引入EasyUI -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
<!-- 引入EasyUI中文脚本 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
<!-- 自定义数据验证js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/custom-validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/zDrag.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/zDialog.js"></script>
	
	
	 <script type="text/javascript" src="http://v3.faqrobot.org/hvb/com/js/jquery-1.11.3.min.js?dev=1"></script>    
    <script type="text/javascript" src="http://v3.faqrobot.org/hvb/com/js/base.js?dev=1"></script>    
<style>
dl {
	float: left;
	margin: 10px;
}

dt, dd {
	margin: 5px;
}
</style>
</head>


<body>

	<div class="easyui-accordion" style="width: 100%;">
		<div title="车辆信息">
			<table width="100%">
				<tr>
					<td width="25%" class="bgc">查验机构</td>
					<td width="25%">${obj.organName }</td>
					<td width="25%" class="bgc">查验人员</td>
					<td width="25%">${obj.cker }</td>
				</tr>
				<tr>
					<td class="bgc">车牌号码</td>
					<td>${obj.hphm }</td>
					<td class="bgc">车牌种类</td>
					<td>${obj.organName }</td>
				</tr>
				<tr>
					<td class="bgc">业务类型</td>
					<td>${obj.typeName }</td>
					<td class="bgc">车身颜色</td>
					<td>${obj.csys }</td>
				</tr>
				<tr>
					<td class="bgc">车辆品牌</td>
					<td>${obj.clpp }</td>
					<td class="bgc">车辆型号</td>
					<td>${obj.clxh }</td>
				</tr>

			</table>
		</div>
	</div>

	<div class="easyui-accordion" style="width: 100%;">
		<div title="查验项判定">
			<table width="100%" id="tab">
				<tr height="50">
					<th width="30%">类别</th>
					<th width="15%">序号</th>
					<th width="40%">查验项目</th>
					<th width="15%">判定</th>
				</tr>
				<tr>
					<td rowspan="9">通用项目</td>
					<td>1</td>
					<td>车辆识别代号</td>
					<td><img id="01"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>2</td>
					<td>发动机型号/号码</td>
					<td><img id="02"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>3</td>
					<td>车辆品牌/型号</td>
					<td><img id="03"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>4</td>
					<td>车身颜色</td>
					<td><img id="04"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>5</td>
					<td>核定载人数</td>
					<td><img id="05"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>6</td>
					<td>车辆类型</td>
					<td><img id="06"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>7</td>
					<td>号牌/车辆外观形态</td>
					<td><img id="07"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>8</td>
					<td>轮胎完好情况</td>
					<td><img id="08"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>9</td>
					<td>安全带、三角警告牌</td>
					<td><img id="09"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td rowspan="5">货车挂车</td>
					<td>10</td>
					<td>外廓尺寸、轴数、轴距</td>
					<td><img id="10"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>11</td>
					<td>整备质量</td>
					<td><img id="11"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>12</td>
					<td>轮胎规格</td>
					<td><img id="12"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>13</td>
					<td>侧后部防护装置</td>
					<td><img id="13"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>14</td>
					<td>车身反光标识和车辆尾部标示板、喷涂</td>
					<td><img id="14"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td rowspan="4">大中型客车、危险化学品运输车</td>
					<td>15</td>
					<td>灭火器</td>
					<td><img id="15"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>16</td>
					<td>行驶记录装置、车内外录像监控装置</td>
					<td><img id="16"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>17</td>
					<td>应急出口/应急锤、客车门</td>
					<td><img id="17"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>18</td>
					<td>外部标示/文字、喷涂</td>
					<td><img id="18"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td rowspan="2">其他</td>
					<td>19</td>
					<td>标志灯具、警报器</td>
					<td><img id="19"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
				<tr>
					<td>20</td>
					<td>安全技术检验合格证明</td>
					<td><img id="20"
						src="<%=request.getContextPath()%>/static/images/icons/wuxiao.png" /></td>
				</tr>
			</table>
		</div>
	</div>
<form id="myform">
<input id="srln" type="text" style="display:none;" value="${srln}"/>
	<div class="easyui-accordion" style="width: 100%;">
		<div title="查验照片">
			<table id="tab" width="100%" cellspacing="0" cellpadding="0"
				style="text-align: center;">
				<thead height="30">
					<th width="10%">序号</th>
					<th width="20%">照片内容</th>
					<th width="20%">审核要求</th>
					<th width="10%">审核结果</th>
					<th width="40%">审核说明</th>
				</thead>
				<tbody>
				<c:forEach items="${pic }" var="list" varStatus="status">
					<tr>
						<td>${status.index+1}</td>
						<td>${list.proName }</td>
						<td onClick="img(`${list.picUrl }`)" style="cursor: pointer;" title="能清晰显示车辆外观、前号牌能清晰显示车辆外观、前号牌能清晰显示车辆外观、前号牌"class="easyui-tooltip">能清晰显示车辆外观、前号牌 <img src="${list.picUrl }" width="30" height="30"style="margin: 0 10px;" />
						</td>
						<td><img src="<%=request.getContextPath()%>/static/images/icons/ok.png" /><input name="rad${ status.index+1}" value="1" type="radio" /> <img src="<%=request.getContextPath()%>/static/images/icons/no.png" /><input id="rad${ status.index+1}" name="rad${ status.index+1}" value="${list.picId }" type="radio" /></td><td><input  class="easyui-textbox"	style="display: none; width: 100%;" /></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</form>
	<div class="easyui-accordion" style="width: 100%;">
		<div title="查验视频和照片">
			<!--视频位置开始-->
			<div style="clear: both;">
			<c:forEach items="${vedio }" var="vedio">
				<dl onClick="video('${vedio.vedioUrl }')">
					<dt>
						<img src="${vedio.vedioUrl }" width="230" height="165" />
					</dt>
					<dd>${vedio.vedioAngle }</dd>
				</dl>
			</c:forEach>
			</div>
			<!--视频位置结束-->
		</div>
	</div>
 <div style="clear:both">
  <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" style="float:right; margin:10px; height:35px; width:100px; cursor:pointer;"  onclick="window.history.go(-1)">返回</button>
  <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="float:right; margin:10px; height:35px; width:100px; cursor:pointer;"  onclick="sub()">确定</button>
  <div style="height:35px;float:right; margin:10px;">查验结果：<select id="selected" class="easyui-combobox" style="width:200px; height:35px;"><option value="0">通过</option><option value="1">不通过</option></select></div>
 </div>
</body>

<script>
	// 提交表单信息
	function sub(){
		var i=0;
		var arr=[];
		$("input[type='radio']:checked").each(function(){
			 if($(this).val()!='1'){
				 arr[i]=$(this).val()+":"+$(this).parent().next().children().val();
			 }
		          i++;
		 });
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'<%=request.getContextPath() %>/module/vehicle/subinfo',
	        traditional: true,
	        data:{
	        		arr:arr,
	        		auditFlag:$("#selected").val(),
	        		srln:$("#srln").val()
	        	},
	        async: false,
	        success: function(result) {
	        	if(result=="success"){
	        		window.location="<%=request.getContextPath() %>/module/vehicle/loadview";
	        	}
	        }
	    });
		
		
	}




	$(function(){
		  // 隐藏所以输入框
		  $(":radio").parent().next().children().next().hide();
		  $(":radio").click(function(){
			  if($(this).val()=="1"){
				  $(this).parent().next().children().next().hide();
				  $(this).parent().next().children().textbox('setValue',"");
				  
			  }else{$(this).parent().next().children().next().show();}
				  
		  });
		  

	     
		  
		  
	 })
	 // 用来加载查验判定内容
	window.onload=function(){
		// 获取session里的map
		<%Map<String, String> mapObj = (Map<String, String>) session.getAttribute("map");
			for (Map.Entry<String, String> entry : mapObj.entrySet()) {
				// 取key
				String key = entry.getKey();
				// 取value
				String value = entry.getValue();%> 
				// 判断他的通过状态
			 	if(<%=value%>=="1"){
			 		 // 给这行添加黄色样式	
					 $("#<%=key%>").parent().addClass("rowbg-yc");
					 $("#<%=key%>").parent().prev().addClass("rowbg-yc");
					 $("#<%=key%>").parent().prev().prev().addClass("rowbg-yc");
					 // 改变他的图标
					 $("#<%=key%>").attr("src","<%=request.getContextPath()%>/static/images/icons/no.png");
				 }else{
					 $("#<%=key%>").attr("src","<%=request.getContextPath()%>/static/images/icons/ok.png");
					}
		<%
			}
		%>  
	}

<!--弹窗打印-->
	function dy(){
		var diag = new Dialog();
		diag.Width = 500;
		diag.Height = 500;
	    diag.ShowButtonRow=true;
		diag.Title = "照片查看";
	    diag.InvokeElementId="forlogin"
		diag.OKEvent = function(){
		doPrint();
		};//点击确定后调用的方法
		diag.show();
	}
	<!--图片弹窗-->
	function img(src){
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
	    diag.ShowButtonRow=true;
		diag.Title = "照片查看";
	    diag.InnerHtml="<img src='"+src+"' width='700' height='500'  />"
		diag.show();
	}
	<!--视频播放弹窗-->
	function video(src){
		var diag = new Dialog();
		diag.Width = 600;
		diag.Height = 400;
	    diag.ShowButtonRow=true;
		diag.Title = "视频播放";
	    diag.InnerHtml="<embed src='"+src+"' type='video/x-ms-wmv' width=600 height=400 autoStart='1' showControls='1' showstatusbar='1' />";
		<!--写入播放的标签目前只有IE能播放-->
		diag.show();
	}
</script>


</html>
