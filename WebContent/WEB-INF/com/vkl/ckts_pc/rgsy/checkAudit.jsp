<%@ page language="java" contentType="text/html; charset=utf-8"  import="com.vkl.ckts.common.entity.UserEntity,com.vkl.ckts.common.constr.Constrant,com.vkl.ckts.common.utils.Base64Utils"  pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	UserEntity user = (UserEntity)session.getAttribute(Constrant.S_USER_SESSION);
	if(user != null){
		user.setUserName(Base64Utils.decode(user.getUserName()));
		pageContext.setAttribute("userNamess", Base64Utils.decode(user.getUserName()));
		pageContext.setAttribute("sfgly", user.getSfgly());
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<meta http-equiv="pragma"   content="no-cache">
<meta http-equiv="cache-control"   content="no-cache">
<meta http-equiv="expires"   content="0">
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jQueryRotate.2.2.js"></script>

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
		<div title="车辆信息" >
		   <div style="float: left;">
			<table width="400px;" >
				<tr>
					<td width="25%" class="bgc">查验机构</td>
					<td width="25%">${obj.organName }</td>
				</tr>
				
				<tr>
					<td class="bgc">车辆品牌</td>
					<td> ${obj.clpp }</td>
				</tr>
				 <tr>
               <td class="bgc">车辆型号</td>
					<td>${obj.clxh }</td>
               </tr>
               <tr>
               <td width="25%" class="bgc">查验人员</td>
					<td width="25%"> ${obj.cker }</td>
               </tr>
               <tr>
               <td class="bgc">号牌种类</td>
					<td><input type="checkbox" value="号牌种类" class="ggdb"/> ${obj.hpzlName }</td>
               </tr>
               <tr>
               <td class="bgc">车身颜色</td>
					<td><input type="checkbox" value="车身颜色" class="ggdb"/>${obj.csys }</td>
               </tr>
              
               <tr>
                   <td class="bgc">车辆识别代号</td>
					<td><input type="checkbox" value="车辆识别代号" class="ggdb"/>${obj.clsbdh }</td>
               </tr>
               <tr>
                   <td class="bgc">核定载客数</td>
					<td><input type="checkbox" value="核定载客数" class="ggdb"/>${obj.hdzk }</td>
               </tr>
                <tr>
					<td class="bgc">车辆类型</td>
					<td><input type="checkbox" value="车辆类型" class="ggdb"/>${obj.cllxName }</td>
				
				 </tr>
				 <tr>
				 	<td class="bgc">使用性质</td>
					<td><input type="checkbox" value="使用性质"class="ggdb"/>${obj.syxzName }</td>
				 </tr>
				 <tr>
					<td class="bgc">车牌号码</td>
					<td><input type="checkbox" value="车牌号码" class="ggdb"/> ${obj.hphm }</td>
				</tr>
				<tr>
					<td class="bgc">业务类型</td>
					<td><input type="checkbox" value="业务类型"class="ggdb"/> ${obj.typeName }</td>
				</tr>
			</table>
			</div>
			<div class="bgc" style="float: left;width:350px;height:330px;margin-left: 15px;position: relative;" id="zpft">
			 <a href="javascript:void(0)" style="display:none;"  id="picIdqm" class="pic" onclick="fdzp('qm','${qm}')"><img style="width:340px;height:250px;margin: auto auto;position: absolute;top: 0; bottom: 0;left:0;right:0; margin: auto;" src="${qm }" ></a>
			 <c:forEach items="${pic }" var="list" varStatus="statu" >
			 <c:choose>
			 		<c:when test="${statu.index==0 }">
			 		<a href="javascript:void(0)" style="display:block;"  id="picId${list.picId}" class="pic" onclick="fdzp('${list.picId }','${list.picUrl }')"><img  style="width:340px;height:250px;margin: auto auto;position: absolute;top: 0; bottom: 0;left:0;right:0; margin: auto;" src="${list.picUrl}" ></a>
			 		</c:when>
			       <c:otherwise>
			        <a href="javascript:void(0)" style="display:none;"  id="picId${list.picId}" class="pic" onclick="fdzp('${list.picId }','${list.picUrl }')"><img style="width:340px;height:250px;margin: auto auto;position: absolute;top: 0; bottom: 0;left:0;right:0; margin: auto;" src="${list.picUrl}" ></a>
			       </c:otherwise>
			 </c:choose>
			</c:forEach>
			 <c:forEach items="${resPics }" var="list" >
				  	 <a href="javascript:void(0)"  style="display:none;" class="pic" onclick="fdzp('${list.resId }','${list.repicUrl }')" id="picId${list.resId}zl"><img src="${list.repicUrl }"  style="width:340px;height:250px;margin: auto auto;position: absolute;top: 0; bottom: 0;left:0;right:0; margin: auto;" /></a>
			</c:forEach>
			  	 <div style="display: none;position:fixed;top:10px;left:50px; z-index:2; margin: auto;overflow:scroll;width: 600px;height:460px;" id="fdtp" onMouseDown='dragImage(this)'>
				 </div>
				
				 <div style="display: none;position:fixed;top:170px;left:50px; z-index:2; margin: auto;overflow:scroll;width: 600px;height:460px;" id="fdtp1" onMouseDown='dragImage(this)'>
				 </div>
			</div>
			
		    <div style="float: left;width:200px;margin-left: 15px; width:200px;height:330px; " class="bgc">
		          <div style="line-height: 30px; text-align: left;"><input type="checkbox" name="bpzpPro" value="qm" class="pics"/><a href="#"  id="proNameqm" onclick="showzp('qm')">签名图片</a> </div>
		          <c:forEach items="${pic }" var="list" >
		          <div style="line-height: 30px; text-align: left;"><input type="checkbox" name="bpzpPro" value="${list.picId }" class="pics"/><a href="#"  id="proName${list.picId }" onclick="showzp('${list.picId }')">${list.proName }</a> </div>
		
				</c:forEach>
				<c:forEach items="${resPics }" var="list" >
				    <div style="line-height: 30px; text-align: left;"><input type="checkbox" name="bpzpPro"  value="${list.resId }zl" class="pics"/><a href="#"  id="proName${list.resId }zl"  onclick="showzp('${list.resId}zl')"> ${list.resName }</a></div>
				</c:forEach>
		    </div>
		    <div  style="float: left; ">
		    <a href="#" id="xzz" onclick="rotateAngle()" style="display: block;">向右转</a>
			<a href="#" id="xzz1" onclick="rotateAngleZ()" style="display: block;">向左转</a>
			</div>
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
	<div class="easyui-accordion" style="width: 100%;">
	
	</div>
<input id="srln" type="text" style="display:none;" value="${srln}"/>
<input id="rckCount" type="text" style="display:none;" value="${rckCount}"/>
	<div class="easyui-accordion" style="width: 100%;">
<%-- 		<div title="查验照片">
			<div id="tab" width="100%" cellspacing="0" cellpadding="0"
				style="text-align: center;">
				<c:forEach items="${pic }" var="list" >
				<div style="float:  left;width:250px;height:250px;margin-left:30px; margin-top:20px;padding-bottom: 0px;padding-top: 0px;">
				<dl >
				  <dt style="width:250px;height:190px;padding-bottom: 0px;">
				  <c:if test="${list.picId == '03'}">
				  	 <a href="javascript:void(0)" class="pic" onclick="fdzp('${list.picId }','${list.picUrl }')"><img src="${list.picUrl }" width="250" height="63"style="margin-top:60px;" /></a>
				  </c:if>
				  <c:if test="${list.picId != '03' }">
				  	 <a href="javascript:void(0)" class="pic" onclick="fdzp('${list.picId }','${list.picUrl }')"><img src="${list.picUrl }" width="250" height="188" /></a>
				  
				  </c:if>
				  </dt>
				  <dd>
				        <span><input type="checkbox" name="bpzpPro" value="${list.picId }" class="pics"/></span>
				 		 ${list.proName }
				  </dd>
				</dl>
				</div>
				</c:forEach>
				 <!-- <div style="display: none;position:fixed;top:10px;left:50px; z-index:2; margin: auto;" id="fdtp">
				 </div>
				
				 <div style="display: none;position:fixed;top:170px;left:50px; z-index:2; margin: auto;" id="fdtp1">
				 </div> -->
			</div>
		</div> --%>
	</div>
</form>
<div class="easyui-accordion" style="width: 100%;height:300px;">
				<div title="查验视频">
				<!--视频位置开始-->
					<div style="clear: both;">
					<c:forEach items="${vedio }" var="vedio">
						<dl onClick="openJsp('${vedio.vedioUrl }')">
							<dt>
								<img src="<%=request.getContextPath()%>/static/images/center.jpg" width="230" height="165" />
							</dt>
							<dd>    <c:choose>
							       			<c:when test="${vedio.vedioAngle==1 }">
							       			        查验
							       			</c:when>
							                <c:otherwise>
							                                                                    拍照
							                
							                
							                </c:otherwise>
							       </c:choose></dd>
						</dl>
					</c:forEach>
					</div>
				<!--视频位置结束-->
				</div>
			</div>
 <div style="clear:both">
  <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="float:right; margin:10px; height:35px; width:130px; cursor:pointer;"  onclick="cancel()">返回</button>
  <c:choose>
  	<c:when test="${sfgly != '1' && obj.auditFlag !=  '2'  }">
	  <button  type="button" class="easyui-linkbutton" disabled="disabled" data-options="iconCls:'icon-edit'" style="float:right; margin:10px; height:35px; width:130px; cursor:pointer;"  onclick="examine(0)">审核通过</button>
	  <button  type="button" class="easyui-linkbutton" disabled="disabled"  data-options="iconCls:'icon-edit'" style="float:right; margin:10px; height:35px; width:130px; cursor:pointer;"  onclick="bh()">审核驳回</button>
  	</c:when>
    <c:otherwise>
       <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="float:right; margin:10px; height:35px; width:130px; cursor:pointer;"  onclick="examine(0)">审核通过</button>
       <button  type="button" class="easyui-linkbutton"   data-options="iconCls:'icon-edit'" style="float:right; margin:10px; height:35px; width:130px; cursor:pointer;"  onclick="bh()">审核驳回</button>
    </c:otherwise>
  </c:choose>
    
  <!-- <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="float:right; margin:10px; height:35px; width:130px; cursor:pointer;"  onclick="bp()">照片打回</button> -->
     <!--  <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" style="float:right; margin:10px; height:35px; width:130px; cursor:pointer;"  onclick="viewvideo()">查验视频</button> -->
   <span  style="float:right; margin:10px; " id="remarks2">备注信息:<input style="width:250px;"id="remarks" class="easyui-textbox" value="${obj.remaksInfo }"/></span> 
</div>


</body>

<script>

	// 新建窗口波放视屏
	function openJsp(url){
		parent.loadVideo2("<%=request.getContextPath()%>"+"/loadvideo.jsp?url="+url);
	}
	
	function cancel(){
		
		window.location="<%=request.getContextPath()%>/module/vehicle/loadview"
		
	}
	
	function hide(){
		$("#fdtp").hide();
	}
	
	// 审核事件
	function examine(auditFlag){
		var remarks=$("#remarks").textbox("getValue");
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'<%=request.getContextPath() %>/module/vehicle/examine',
	        traditional: true,
	        dataType:'text',
	        data:{
	        		auditFlag:auditFlag,
	        		srln:$("#srln").val(),
	        		rckCount:$("#rckCount").val(),
	        		remarks:""
	        	},
	        async: false,
	        success: function(result) {
	        	switch(result){
	        	case"success":
	        		alert("操作成功");
	        		parent.tabsClose();
	        		break;
	        	case"true":
	        		alert("操作成功");
	        		parent.tabsClose();
	        		break;
	        	case"false":
	        		alert("操作失败");
	        		parent.tabsClose();
	        		break;
	        	case"exe":
	        		alert("系统异常，请稍后再试");
	        		parent.tabsClose();
	        		break;
	        	}
	        }
	    });
	}
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
	        		srln:$("#srln").val(),
	        		rckCount:$("#rckCount").val()
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
			$(".pic").click(function(event){
				event.stopPropagation();
			})
			$("#fdtp").click(function(event){
				event.stopPropagation();
			})
			$("#fdtp1").click(function(event){
				event.stopPropagation();
			})
			$("#xzz").click(function(event){
				event.stopPropagation();
			})
			$("#xzz1").click(function(event){
				event.stopPropagation();
			})
			
			$(document).click(function(){
				$("#fdtp").hide();
				$("#fdtp1").hide();
			})
			var remarksInfos="";
			$(".ggdb").click(function(){
				/* var res=$(this).val();
				var checked=$(this).attr('checked');
				alert(res);
				if(remarksInfos==null ||remarksInfos==''){
					remarksInfos=res;
				}else{} */
				 remarksInfos="";
				var ggdbs =$(".ggdb:checked");
				$.each(ggdbs,function(index,ggdb){
					if(index==0){
						remarksInfos=$(ggdb).val()+"不符";
					}else{
						remarksInfos=remarksInfos+","+$(ggdb).val()+"不符";
					}
				});
				var pics =$(".pics:checked");
				var picS='';
				$.each(pics,function(index,pic){
					var proName=$("#proName"+$(pic).val()).html();
					if(remarksInfos==null||remarksInfos==''){  
						remarksInfos=proName+"不合格";
					}else{
						remarksInfos=remarksInfos+","+proName+"不合格";
					}
				})
				$("#remarks").textbox("setValue",remarksInfos);
			});
			
			$(".pics").click(function(){
				 remarksInfos="";
					var ggdbs =$(".ggdb:checked");
					$.each(ggdbs,function(index,ggdb){
						if(index==0){
							remarksInfos=$(ggdb).val()+"不符";
						}else{
							remarksInfos=remarksInfos+","+$(ggdb).val()+"不符";
						}
					});
					var pics =$(".pics:checked");
					var picS='';
					$.each(pics,function(index,pic){
						var proName=$("#proName"+$(pic).val()).html();
						if(remarksInfos==null||remarksInfos==''){  
							remarksInfos=proName+"不合格";
						}else{
							remarksInfos=remarksInfos+","+proName+"不合格";
						}
					})
					$("#remarks").textbox("setValue",remarksInfos);
			});
			
			
			
	 })
	 var value=0;
	 function rotateAngle(){
		    //$("#fdtp1").show();
		    //$("#fdtp").show();
		   value=90+value;
		   //$("#fdtp").rotate(value);
		   $("#zb").rotate({
			   angle:value ,
			   bind:{mousewheel:function(){
				             var o=$("#zb")[0];
								   var zoom=parseInt(o.style.zoom, 10)||100;
								   	 zoom+=event.wheelDelta/12;
								   	 if (zoom>0) o.style.zoom=zoom+'%';
								   	 return false;
				   
			                     }
					   ,mouseDown:function(){
						   var o=$("#fdtp")[0];
						   dragImage(o)
					   }
			   
			   },
		   });
	}
	 function rotateAngleZ(){
		    //$("#fdtp1").show();
		    //$("#fdtp").show();
		   value=value-90;
		   //$("#fdtp").rotate(value);
		   $("#zb").rotate({
			   angle:value ,
			   bind:{mousewheel:function(){
				   var o=$("#zb")[0];
				   var zoom=parseInt(o.style.zoom, 10)||100;
				   	 zoom+=event.wheelDelta/12;
				   	 if (zoom>0) o.style.zoom=zoom+'%';
				   	 return false;
				   
			   }},
		   });
	}
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
	
	
	
	
	//补拍
	function bh(){
		var remarks=$("#remarks").textbox("getValue");
		var auditFlag='1';
		if(remarks==null ||remarks==""){
			alert("备注信息不能为空");
			return;
		}
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'<%=request.getContextPath() %>/module/vehicle/examine',
	        traditional: true,
	        dataType:'text',
	        data:{
	        		auditFlag:auditFlag,
	        		srln:$("#srln").val(),
	        		rckCount:$("#rckCount").val(),
	        		remarks:remarks
	        	},
	        async: false,
	        success: function(result) {
	        	switch(result){
	        	case"success":
	        		window.location="<%=request.getContextPath() %>/module/vehicle/loadview";
	        		break;
	        	case"true":
	        		window.location="<%=request.getContextPath() %>/module/vehicle/loadview";
	        		break;
	        	case"false":
	        		window.location="<%=request.getContextPath() %>/module/vehicle/loadview";
	        		break;
	        	case"exe":
	        		window.location="<%=request.getContextPath() %>/module/vehicle/loadview";
	        		break;
	        	}
	        }
	    });
		bp();
	}
	
	
    function selectZp(picId,url){
		$("#zpft").empty();
		var img="<a href='javascript:void(0)'  onclick=\"fdzp('"+picId+"','"+url+"')\">"+
		          "<img style='width:340px;height:auto;margin: auto auto;position: absolute;top: 0; bottom: 0;left:0;right:0; margin: auto;' src='"+url+"' />"
		          +"</a>"
		$("#zpft").append(img);
	}
   
    function bbimg(o){ 
   	 var zoom=parseInt(o.style.zoom, 10)||100;
   	 zoom+=event.wheelDelta/12;
   	 if (zoom>0) o.style.zoom=zoom+'%';
   	 return false;
   	 }
   	 
    
	
	
	//补拍
	function bp(){
		var pics =$(".pics:checked");
		var picS='';
		$.each(pics,function(index,pic){ 
			if($(pic).val().indexOf('zl')>-1){
				return true;
			}
			if(index==0){
				picS=$(pic).val();
			}else{
				picS=picS+","+$(pic).val();
			}
		})
		var srln = '${srln}';
		var rckCount = $("#rckCount").val();
		$.ajax({
			   cache: true,
		        type: "POST",
		        url:'<%=request.getContextPath() %>/module/vehicleinfo/addpbphoto',
		        data:{
		    	     srln:srln,
		    	     rckCount:rckCount,
		    	     picId:picS
		    	},
		        async: false,
		        error: function(request) {
		            alert("系统操作繁忙请稍后！");
		        },
	        success: function(result) {
	        	if(result=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
	        	if(result=="true"){
	        		$.messager.alert("提示！","操作成功")
	        	}
	        }
	    });
	}
	function showzp(picId){
		$(".pic").hide();
		$("#picId"+picId).show();
	}
	function fdzp(picId,src){
		if(picId=="03"){
			$("#fdtp1").empty();
			$("#fdtp1").attr("style","display: none;position:fixed;top:10px;left:50px; z-index:2; margin: auto;width: 600px;height:460px; ");
			$("#fdtp").attr("style","display: none;position:fixed;top:10px;left:50px; z-index:2; margin: auto;width: 600px;height:460px;");
			$("#fdtp").empty();
			var img="<img src="+src+" style='margin:auto;  z-index:1;  width:600px;height:auto;' onmousewheel='return bbimg(this)' id='zb'/>";
			$("#fdtp1").append(img);
			$("#fdtp").hide();
			$("#fdtp1").show();
		}else{
			$("#fdtp1").attr("style","display: none;position:fixed;top:10px;left:50px; z-index:2; margin: auto;width: 600px;height:460px;");
			$("#fdtp").attr("style","display: none;position:fixed;top:10px;left:50px; z-index:2; margin: auto;width: 600px;height:460px;");
			$("#fdtp").empty();
			$("#fdtp1").empty();
			var img="<img src="+src+" style='margin:auto; z-index:1;  width:600px;height:auto;' onmousewheel='return bbimg(this)' id='zb'/>";
			$("#fdtp").append(img);;
			$("#fdtp").show();
			$("#fdtp1").hide();
		}
	}
	 down = false;
	 var x,y,imgID;
	 function dragImage(obj){
	       imgID = obj;
	 x = event.x - parseInt(imgID.style.left);
	 y = event.y - parseInt(imgID.style.top);
	 down=true;
	 }
	 function cancelDrag(){	down=false;	}
	 function moveImage(){
	 if(down){
	 imgID.style.left  = event.x - x;
	 imgID.style.top   = event.y - y;
	 event.returnValue = false;
	 }
	 }
	 document.onmousemove = moveImage;
	 document.onmouseup = cancelDrag;
</script>


</html>
