<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>高拍页面</title>

<%@include file="../pub/static.jsp"%>
</head>
<body onclick="start_preview()" style="height:700px;position: absolute;z-index:0;">

	<div style="margin-left:25px; position:relative;z-index: -2;">
		<object style="position:relative;z-index: -1;" classid="clsid:090457CB-DF21-41EB-84BB-39AAFC9E271A"
			id="ScanCtrl" codebase="*.cab#version=1,0,0,1" width="800"
			height="500"></object>
	</div>
	<div style="width: 800px; margin-left: 54px;position: absolute; ">
		<table>
			<tr>
				<td style="width: 100px;"><span class="star">*</span>资料照片</td>
				<td><select class="easyui-combobox" panelHeight="80px" p
					style="width: 90%; height: 30px;position: absolute;" name="respic" editable="false"
					id="respic">
						<c:forEach items="${respros }" var="list">
							<option value="${list.resId}">${list.resName }</option>
						</c:forEach>
				</select></td>
				<td>
					<button type="button" class="easyui-linkbutton"
						data-options="iconCls:'icon-application_detail'"
						onclick="start_preview()" style="width: 150px;">预览</button>



				</td>
				<td>
					<button type="button" class="easyui-linkbutton"
						data-options="iconCls:'icon-application_detail'"
						onclick="uploalPic()" style="width: 150px;">高怕上传</button>
				</td>

			</tr>
		</table>
	</div>

	<input id="srln" value="${srln}" style="display: none;" />
	<input id="localAddress" value="${localAddress}" style="display: none;" />
	<input id="xmm" value="<%=request.getContextPath() %>"
		style="display: none;" />
		<input id="rckCount" value="${rckCount}"
		style="display: none;" />

</body>
<script type="text/javascript">



			$(function(){
				start_preview();
			})
           function uploalPic(){
	           var srln=$("#srln").val();
	           var rckCount=$("#rckCount").val();
	           var respic=$("#respic").combobox("getValue");
	           var localAddress=$("#localAddress").val();
	           var xmm=$("#xmm").val();
        	   var url=localAddress+"/"+xmm+"/module/servacpt/uploadpic?srln="+srln+"&respicId="+respic+"&rckCount="+rckCount;
        	   var returnStr=ScanCtrl.ScanToHTTPServer2(url,"picfile","");
        	   if(returnStr==" "||returnStr==null){
        		   alert("高拍仪未初始化");
        		   return ;
        	   }
        	   if(returnStr=="success"){
        		   alert("高拍成功");
        	   }else{
        		   alert("高拍失败");
        	   }
        	   
           }
		    function uploalPic1(){
		           var srln=$("#srln").val();
		           var rckCount=$("#rckCount").val();
		           var respic=$("#respic").combobox("getValue");
		           var localAddress=$("#localAddress").val();
		           var xmm=$("#xmm").val();
	        	   var url=localAddress+"/"+xmm+"/module/servacpt/uploadpic?srln="+srln+"&respicId="+respic+"&rckCount="+rckCount;
	        	   var returnStr=ScanCtrl.UploadFileOfHTTP2(url,"d:/cesii.jpg","picfile");
	        	   if(returnStr==" "||returnStr==null){
	        		   alert("高拍仪未初始化");
	        		   return ;
	        	   }
	        	   if(returnStr=="success"){
	        		   alert("高拍成功");
	        	   }else{
	        		   alert("高拍失败");
	        	   }
	           }

	    function up_img()
			{
	    	
	    	
	    	    var respic=$("#respic").textbox("getValue");
	    	    alert("respic");
				  var tp=new Array();//图片数组
				  tp=thumbnailPath();
				  if(tp.length<=0){
					  alert("请选择要上传的图片！");
					  return;
				  }
				  
				  var  paths = tp.join(",");
				 
				 
				<!--调用上传-->
				$.ajax({
		            cache: true,
		            type: "POST",
		            url:'<%=request.getContextPath()%>/module/servacpt/upResource',
		            data:{"paths":paths, "srln":$("#srln").val()},  
		            async: false,
		            success: function(data) {
		            	
		            	if(data){
		            		
		            		alert("上传成功!");
		            		
		            	}
		            	
		            }
		        });
				
			}
			
	function thumbnailPath()//建立图片数组
			{
				var isOk,num,gfp=new Array();
				num=EThumbnails.GetDisplayCount();
				for(var i=0;i<num;i++){
					isOk=EThumbnails.IsChecked(i);
					if(isOk==1){
					gfp[i]=EThumbnails.GetFilePath(i);
					}	
				}
				return gfp;
			}


			
	        function TakePic(name)    
	        {
	        	start_preview();
			    var path="D:\\rtt.jpg";
				//ScanCtrl.EnableDateRecord(1);						   
				var flag=ScanCtrl.Scan(path);		
				if(flag)
				{
					EThumbnails.AddToDisplay(path);
					
					alert("拍照成功！")
					
				}else{
					alert("拍照失败！")
					}
				
				
	        }
	     function start_preview() 
			{
				
				ScanCtrl.StartPreviewEx();
				
			}
	      function stop_preview() 
			{   
				ScanCtrl.StopPreview();
				
			}
			
           

    
    
    </script>
</html>
