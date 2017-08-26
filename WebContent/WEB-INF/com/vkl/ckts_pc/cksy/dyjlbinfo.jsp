<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>车辆审核</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/demo.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/static/css1/css.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
<script
	src="<%=request.getContextPath()%>/static/js/echarts.common.min.js"></script>
<!-- 自定义数据验证js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/custom-validate.js"></script>
<style type="text/css">
.blue {
	background-color: #7FFFD4;
	cursor: hand;
}
</style>
</head>
<body onunload="unload()">
	<div class="easyui-accordion" style="width: 100%;" id="ser">
		<div title="打印信息查询">
			<table width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="140" class="bgc">车辆识别代号</td>
					<td width="240px"><input id="clsbdh" class="easyui-textbox"
						style="width: 240px; height: 30px;" /></td>
					<td width="140" class="bgc">查验状态</td>
					<td width="250px"><select class="easyui-combobox" id="operStatu"
						style="width: 250px; height: 30px;">
							<option value="5">已完成</option>
							<option value="3">待查验</option>
							<option value="4">审核中</option>
							<option value="7">照片补拍</option>
							<option value="">全部</option>
					</select></td>
					<td width="140" class="bgc">业务类型</td>
					<td width="150px"><select class="easyui-combobox" id="mySelect"
						style="width: 150px; height: 30px;">
							<option value="">全部</option>
							<c:forEach items="${operType }" var="list">
								<option value="${list.id }">${list.typeName }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="bgc">号牌号码</td>
					<td width="240px"><input id="hphm" class="easyui-textbox"
						style="width: 240px;" /></td>
					<td class="bgc" >打印状态</td>
					<td width="250px"><select class="easyui-combobox" id="sfdy"
						style="width: 250px; height: 30px;">
							<option value="0">未打印</option>

							<option value="1">已打印</option>
							<option value="">全部</option>
					</select></td>
					<td class="bgc">启用自动打印</td>
					<td width="150px"> <input type="radio" name="zddy" value="1" style="width: 30px; "/>启动 <input
						name="zddy" type="radio" value="0" checked="checked" id="gb" style="width: 30px; " />关闭</td>

				</tr>
				<tr>
					<td class="bgc">受理时间</td>
					<td colspan="3"><input class="easyui-datebox" id=startTime
						style="width: 170px; height: 30px"> <label
						style="width: 170px; height: 30px;">至</label> <input
						class="easyui-datebox" id="endTime"
						style="width: 170px; height: 30px"></td>
					<td class="bgc star" id="ts"></td>
					<td></td>
				</tr>


			</table>
			<div
				style="width: 100%; background: #eff6fe; padding: 2px; text-align: right;"
				class="easyui-panel">
				<button type="button" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'" onclick="search();">查询</button>
				<button type="button" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'" onclick="updata();">重置</button>
			</div>
		</div>
	</div>
	<!-- <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='yhadd.html'">打印申请单</button>
     </div>-->
	<div
		style="width: 100%; overflow: hidden; background: #eff6fe; padding: 2px;"
		class="easyui-panel">
		<!-- <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-edit'"  onclick="updateCkInfo();">修改</button>
		<button type="button" class="easyui-linkbutton"
			data-options="iconCls:'icon-no'" onclick="deleteCkInfo()">删除</button> -->
	</div>
	<div class="easyui-accordion" style="width: 100%; margin-top: 5px;">
		<div title="打印信息一览表">
			<table width="99%" style="" id="tab" cellspacing="0" cellpadding="0">
				<tr>
					<th width="5%">选择</th>
					<th width="15%">车辆识别代号</th>
					<th width="10%">号牌号码</th>
					<th width="8%">业务类型</th>
					<th width="10%">审核标识</th>
					<!-- <th>查验点</th> -->
					<th width="8%">查验状态</th>
					<!-- <th>业务受理人</th> -->
					<th width="12%">车辆类型</th>
					<th width="7%">是否打印</th>
					<th width="25%" align="center">打印操作</th>
				</tr>
				<tbody id="myTbody">

				</tbody>

			</table>
			<!--分页开始-->
			<div style="width: 100%;" class="easyui-panel">
				<div id="pp" style="display: none;" class="easyui-pagination"
					data-options="
					total:0, 
					pageSize:20,
					layout:['first','prev','links','next','last','manual'],
                    beforePageText:'当前页',
                    onSelectPage:function(pageNo){
                   search(pageNo);
                     
				}
                   
					"></div>
			</div>
			<!--分页结束-->
			
				<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs sybg"
			style="width: 100%; height: 100%; background-color: #eff7fe;">

		</div>


	</div>
		</div>
	</div>

</body>
<script type="text/javascript">
var reTime=6;
var reInTime;
var addyNum=11;
var wdapp;
$(function(){
	$('#startTime').datebox('setValue', formatterDate(new Date()));
	$('#endTime').datebox('setValue', formatterDate(new Date()));
	search(1);
	$("#tab tr:gt(0)").hover(function() {
		$(this).addClass('blue');
	}, function () {
	  $(this).removeClass('blue');
	});
	$('#ser').accordion({
		 animate:false
	})  
	initPrinterdoc();
	//reInTime=setInterval("refreshPage()",1000);
	$("#clsbdh").textbox({onChange:function(){
		 $(this).textbox("getValue");
		var clsbdh = $(this).textbox("getValue").toUpperCase();
		$(this).textbox("setValue",clsbdh);
		
	 }});
	$("#hphm").textbox({onChange:function(){
		 $(this).textbox("getValue");
		var hphm = $(this).textbox("getValue").toUpperCase();
		$(this).textbox("setValue",hphm);
		
	 }});
})
var zddy;
//加载页面自动判断是否自动打印
var time;
formatterDate = function(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};
 
	$(":radio").click(function(){
		    var zddy=$('input:radio[name="zddy"]:checked').val();
			if(zddy==1){
				reInTime=setInterval("refreshPage()",1000);
			}else{
				clearInterval(reInTime);
				$("#ts").html("");
			}
	});

	function initPrinterdoc(){
		
		 try {
	                //获取Word 过程  
	                //请设置IE的可信任站点  
	                wdapp = new ActiveXObject("Word.Application");
	            }
	            catch (e) {
	                alert("无法调用Office对象，请确保您的机器已安装了Office并已将本系统的站点名加入到IE的信任站点列表中！");
	                wdapp = null;
	                return;
	                        }
		
		}
function refreshPage(){
	startRequest();
	reTime=reTime-1;
	addyNum=addyNum-1;
	$("#ts").html(reTime+"秒后自动刷新");
	if(reTime==0){
		//clearInterval(reInTime);
		search(1);
		reTime=6;
	}
	if(addyNum==0){
	    startRequest()
		 addyNum=11;
	}
}



/*定时器 定时打印  */
function startRequest(){
	$.ajax({
		  url: "<%=request.getContextPath()%>/module/vehicleinfo/doPrintList1",
		  cache: false,
		  async: false,
		  success: function(data){
			  if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
			  if(data!=null){
					$.each(data, function(index,da){
						printDoc(da,"recordPrinter");
						deletePrinter(da);
					})
				}
			  
			  
		  }
	});
}


function jump(srln,rckCount) {
	//var parentw=$('#父窗口中的元素ID', parent.document);
	var url="<%=request.getContextPath()%>/module/vehicleinfo/dyvehinfo?srln="+srln+"&rckCount="+rckCount;
	parent.addTab1("打印信息查看",url);
}


    function printDoc(url,printerName){
          //  var wdapp;
        /*     try {
                //获取Word 过程  
                //请设置IE的可信任站点  
                wdapp = new ActiveXObject("Word.Application");
            }
            catch (e) {
                alert("无法调用Office对象，请确保您的机器已安装了Office并已将本系统的站点名加入到IE的信任站点列表中！");
                wdapp = null;
                return;
           } */
           
           //wdapp.Documents.Open(url);
           wdapp.Documents.Open(url,false,true,null);
           wdapp.Application.Visible = false;  
           wdapp.visible=false; 
		   wdapp.ActivePrinter=printerName;
           wdapp.Application.Printout(); //调用自动打印功能  
  }

function unload(){
	 wordApp.ActiveDocument.close();
	 wdapp.quit();
     wdapp = null;
}


 function deletePrinter(docurl){
	 $.ajax({
		  url: "<%=request.getContextPath()%>/module/vehicleinfo/deletedocfile",
		  cache: false,
		  async: false,
		  data:{"url":docurl},
		  success: function(data){
			  if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	          }
		  }
	});
	 
 }


// 详情跳转
<%-- function jump(srln,rckCount){
	window.location.href="<%=request.getContextPath()%>/module/vehicleinfo/loadcheckaudit?srln="+srln+"&rckCount="+rckCount;
	
}; --%>
//打印受理单
function dy1(srln,statu,rckCount){
	if(statu=="5"){
		alert("当前业务已结束，不能打印受理单");
		return;
	}
	$.ajax({
		type: "POST",
	    async: false,
		url: "<%=request.getContextPath()%>/module/servacpt/print1",
		data:{"srln":srln,
			  "rckCount":rckCount
		     }, // 号牌号码 车辆识别代号任意参数
		dataType:"text",
		success: function (data) {
			
			if(data=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
			if(data!=null){
				$.each(data, function(index,da){
					printDoc(da,"recordPrinter");
					deletePrinter(da)
				})
			}
		}
     });

}
<%-- 	
--%>

//打印照片
function dy2(srln,statu,rckCount){
	if(statu=="3"){
		alert("当前业务未查验完，不能打印照片");
		return;
	}
	$.ajax({
		type: "POST",
	    async: false,
		url: "<%=request.getContextPath()%>/module/vehicleinfo/printCkPhoto1",
		data:{"srln":srln,
		    "rckCount":rckCount
	        }, // 号牌号码 车辆识别代号任意参数
		dataType:"json",
		success: function (data) {
			if(data=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
			if(data!=null){
				$.each(data, function(index,da){
					printDoc(da,"photoPrinter");
					deletePrinter(da)
				})
			}
		}
  });
	
}
	
//打印结果单	
function dy3(srln,statu,rckCount){
	if(statu=="3"){
		alert("当前业务未查验完，不能打印查验记录");
		return;
	}
	$.ajax({
		type: "POST",
	    async: false,
		url: "<%=request.getContextPath()%>/module/vehicleinfo/printCkData1",
		data:{"srln":srln,
			  "rckCount":rckCount
			}, // 号牌号码 车辆识别代号任意参数
		dataType:"text",
		error:function(result){
			alert(result)
		},
		success: function (data) {
			if(data=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
			//printDoc1("d:/xiaoxu3.dot",data,"recordPrinter")
			 if(data!="false"){
				printDoc(data,"recordPrinter");
				deletePrinter(data)
			}
			 search(1); 
		}
  });
	
}

function printDoc1(url,data,printerName){ 
    wdapp.Documents.add(url);
    $.each(data,function(index,dt){
    	if("img5"==dt.text||"img6"==dt.text){
    		var range3=wdapp.ActiveDocument.Bookmarks(dt.text).Range;
    		range3.InlineShapes.AddPicture(dt.value);
    	}else if(dt.text!="srln"){
    		var hphm=wdapp.ActiveDocument.Bookmarks(dt.text).Range;
        	hphm.text=dt.value;
    	}
    })
    
   //wdapp.Documents.Open("http://192.168.15.130:8080/photo/67.docx"); 
   wdapp.visible = false; 
   wdapp.ActivePrinter=printerName;
   wdapp.Application.Printout(); //调用自动打印功能  
   
	
}





//复检申请	
function fj(srln,statu,rckCount){
	if(statu=="3"){
		alert("当前业务未开始 ，不能复检");
		return;
	}
	$.ajax({
		type: "POST",
	    async: false,
		url: "<%=request.getContextPath()%>/module/servacpt/reCheck",
		data:{"srln":srln,
			  "rckCount":rckCount
			}, // 号牌号码 车辆识别代号任意参数
		dataType:"text",
		success: function (data) {
			if(data=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
			if(data=="true"){
          	  $.messager.alert("提示！", "复检受理成功！");
          	   
          	}else{
          		 $.messager.alert("提示！", "复检受理失败！");
             	  
          	}
			
		}
  });
	
}

function updata(){
	 $("#clsbdh").textbox("setValue" , "");
	 $('#startTime').datebox('setValue', formatterDate(new Date()));
	 $('#endTime').datebox('setValue', formatterDate(new Date()));
	 $("#mySelect").combobox('setValue' , "");
	 $("#auditFlag").combobox('setValue' , "");
	 $("#hphm").textbox("setValue" , "");
}

// 查询
var pageNo = 1;
function search(pageNo){
	var clsbdh = $("#clsbdh").textbox("getValue");
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var parentId = $("#mySelect").combobox('getValue');
	var operStatu =$("#operStatu").combobox('getValue');
	var hphm = $("#hphm").textbox("getValue");
	var sfdy = $("#sfdy").combobox('getValue');
	$.ajax({
        cache: true,
        type: "POST",
        url:'<%=request.getContextPath() %>/module/vehicleinfo/loadinfo',
        data:{
        	    clsbdh:clsbdh,
	        	startTime:startTime,
	        	endTime:endTime,
	        	pageNo:pageNo,
	        	parentId:parentId,
	        	operStatu:operStatu,
	        	hphm:hphm,
	        	sfdy:sfdy
	        	
        	},
        async: false,
        error: function(request) {
            alert("系统操作繁忙请稍后！");
        },
        success: function(data) {
        	if(data=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
        	// 清空table内容
        	$("#myTbody").empty();
        	for(var i=0;i<data.list.length;i++){
        		// 将时间戳转成时间
        		if(data.list[i].hphm==null){
        			data.list[i].hphm=""
        		}
        		if(data.list[i].owName==null){
        			data.list[i].owName=""
        		}
        		
        		if(data.list[i].auditFlag==null){
        			data.list[i].owName=""
        		}
        		
        		var date = new Date(data.list[i].createDate);
        		Y = date.getFullYear() + '-';
        		M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        		D = date.getDate() + ' ';
        		if(data.list[i].createDate==null){
        			Y = "";
            		M = "";
            		D = "";
        		}
        		
        		var auditerFlag=data.list[i].auditFlag;
        		var auditName=auditerFlag=='0'?"已通过":auditerFlag=='1'?"未通过":auditerFlag=='2'?"审核中":"";
        		if(data.list[i].operStatu !='5' ){
        			auditName="查验中";
        		}
        		var sji=data.list[i].sfdy==0?"未打印":"已打印";
        		if(i % 2 == 1){
					var tr="<tr ondblclick='jump("+data.list[i].srln+","+data.list[i].rckCount+")'class='rowbgcolor '>"
					    +"<td class='texcenter'><input  type='radio' name='radio' value="+data.list[i].srln+":"+data.list[i].rckCount+":"+data.list[i].operStatu+" /></td>"
			         	+"<td >"+data.list[i].clsbdh+"</td>"
			         	+"<td>"+data.list[i].hphm+"</td>"
			         	+"<td>"+data.list[i].typeName+"</td>"
			         	+"<td>"+auditName+"</td>"
			         /* 	+"<td>"+data.list[i].organName+"</td>" */
			         	+"<td>"+toStr(data.list[i].operStatu)+"</td>"
			         /* 	+"<td>"+data.list[i].userName+"</td>" */
			         	+"<td>"+data.list[i].cllxName+"</td>"
			         	+"<td>"+sji+"</td>"
			         	+"<td align='center'><button  type='button' onClick='dy2("+data.list[i].srln+","+data.list[i].operStatu+","+data.list[i].rckCount+")'>照片打印</button><button  type='button' onClick='dy3("+data.list[i].srln+","+data.list[i].operStatu+","+data.list[i].rckCount+")'>查验记录表</button></td>"
			         	+"</tr>";
				}else{
					var tr="<tr ondblclick='jump("+data.list[i].srln+","+data.list[i].rckCount+")'  >"
					    +"<td class='texcenter'><input  type='radio' name='radio' value="+data.list[i].srln+":"+data.list[i].rckCount+":"+data.list[i].operStatu+" /></td>"
			         	+"<td >"+data.list[i].clsbdh+"</td>" 
			         	+"<td>"+data.list[i].hphm+"</td>"
			         	+"<td>"+data.list[i].typeName+"</td>"
			         	+"<td>"+auditName+"</td>"
			         	/* +"<td>"+data.list[i].organName+"</td>" */
			         	+"<td>"+toStr(data.list[i].operStatu)+"</td>"
			         	/* +"<td>"+data.list[i].userName+"</td>" */
			         	+"<td>"+data.list[i].cllxName+"</td>"
			         	+"<td>"+sji+"</td>"
			         	+"<td align='center'><button  type='button' onClick='dy2("+data.list[i].srln+","+data.list[i].operStatu+","+data.list[i].rckCount+")'>照片打印</button><button  type='button' onClick='dy3("+data.list[i].srln+","+data.list[i].operStatu+","+data.list[i].rckCount+")'>查验记录表</button></td>"
			         	+"</tr>";
				}
        		// 添加tr
        		$("#myTbody").append(tr);
        	}
        	// 显示分页栏
        	$("#pp").show();
        	// 设置总条数
        	
        	$("#pp").pagination({
       		  total:data.totalCount
       		});
        	//reTime=5;
        	// reInTime=setInterval("refreshPage()",1000);
        }
    });
	
	$("#tab tr:gt(0)").hover(function() {
		$(this).addClass('blue');
	}, function () {
	  $(this).removeClass('blue');
	});
	
	
}



function toStr(str){
	switch(str){
	case"3":
		return "查验";

	case"4":
		return "审核中";
	case"5":
		return "业务结束";
	
	case"7":
		return "照片补拍";
	}
}
 function deleteCkInfo(){
	    var  ckid=$("[name=radio]:checked").val();
		if(validateEmpty(ckid, "请选择需要删除的预录入信息！")){
			return ;
		}
		var operStatu=ckid.split(":");
		if(operStatu[2]=="5"||operStatu[2]=="7"){
			$.messager.alert("提示！", "当前业务已受理，不能删除");
			return;
		}
		parent.$.messager.confirm('询问', '您是否删除此备案？',
				function(b) {
					if (b) {
					pageNumber=$("#pp").pagination('options').pageNumber;
				   
					$.ajax({
						url:"<%=request.getContextPath()%>/module/vehicleinfo/deleteckinfo",
						type:"post",
						data:{
							   ckid: ckid
						},
						success:function(result){
							if(result=="no-login"){
								top.location="<%=request.getContextPath() %>/login.jsp"
				        	}
							if(result=="true"){
								search(1);
							}else{
								$.messager.alert("提示！", "系统异常，删除失败");
							}
								
						}
						
					});
			        }
			    });
 }
 
 function updateCkInfo(){
	    var  ckid=$("[name=radio]:checked").val();
		if(validateEmpty(ckid, "请选择需要删除的预录入信息！")){
			return ;
		}
		var operStatu=ckid.split(":");
		if(operStatu[2]=="5"||operStatu[2]=="7"){
			$.messager.alert("提示！", "当前业务已受理，不能修改");
			return;
		}
		window.location='<%=request.getContextPath()%>'+"/module/servacpt/toCkUpdatejsp?ckid="+ckid;   
}


 
 
 
 
 
</script>
</html>
