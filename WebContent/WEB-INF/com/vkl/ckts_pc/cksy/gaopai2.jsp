<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>高拍页面</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
</head>
<body style="background-color:#CCC;">
 
 <div style="background-color:#CCC; width: 100%;"  >
       <div style=" float:left; ">
		<object classid="clsid:090457CB-DF21-41EB-84BB-39AAFC9E271A"
								id="ScanCtrl" codebase="*.cab#version=1,0,0,1" width="380px;" height="350px;"
								></object>
                            </div>
          <div style=" float:left;">                  
         <OBJECT ID="EThumbnails"  CLASSID="CLSID:E8B3DD46-A440-4C3C-AB0A-DC689EEBDA84" width="380px;" height="350px;" ></OBJECT>
                </div>

   <div style="clear:both; padding:10px;">
 <input class="submit_01" type="button" value="开始预览"onclick="start_preview()" /> 
 <input class="submit_01" type="button" value="停止预览" onclick="stop_preview()" />
 <input class="submit_01" type="button" value="机动车牌证申请表拍照" onclick="TakePic('机动车牌证申请表')" />
 <input class="submit_01" type="button" value="机动车申请表拍照" onclick="TakePic('机动车申请表')" />
 <input class="submit_01" type="button" value="机动车行驶证拍照" onclick="TakePic('机动车行驶证')" />
 <input class="submit_01" type="button" value="交通事故责任强制保险单拍照" onclick="TakePic('交通事故责任强制保险单')" />
 <input class="submit_01" type="button" value="安全技术检验合格证明拍照" onclick="TakePic('安全技术检验合格证明')" />
 <input class="submit_01" type="button" value="车船税纳税或者免税证明拍照" onclick="TakePic('车船税纳税或者免税证明')" />
 <input class="submit_01" type="button" value="机动车销售统一发票等机动车来历证明拍照" onclick="TakePic('机动车销售统一发票等机动车来历证明')" />
 <input class="submit_01" type="button" value="机动车所有人身份证明拍照" onclick="TakePic('机动车所有人身份证明')" />
 <input class="submit_01" type="button" value="货物进口证明书或海关没收走私汽车、摩托车证明书拍照" onclick="TakePic('货物进口证明书或海关没收走私汽车、摩托车证明书')" />
 <input class="submit_01" type="button" value="机动车整车出厂合格证、机动车底盘出厂合格证拍照" onclick="TakePic('机动车整车出厂合格证、机动车底盘出厂合格证')" />
 <input class="submit_01" type="button" value="报废机动车回收证明副本拍照" onclick="TakePic('报废机动车回收证明副本')" />
 <input class="submit_01" type="button" value="操纵辅助装置产品的合格证明拍照" onclick="TakePic('操纵辅助装置产品的合格证明')" />
 <input class="submit_01" type="button" value="县级或者设区的市级人民政府批准的校车使用许可拍照" onclick="TakePic('县级或者设区的市级人民政府批准的校车使用许可')" />
 <input class="submit_01" type="button" value="机动车登记证书拍照" onclick="TakePic('机动车登记证书')" />
 <input class="submit_01" type="button" value="上传图片" onclick="up_img()" />
 <input id="srln" value="${srln}" style="display: none;"/>
</div>
                                
	</div>
    
</body>
<script type="text/javascript">




	function up_img()
			{
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
			    var path="D:\\"+name+".jpg";
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
				
				//ScanCtrl.SetCurDev(0);
				//ScanCtrl.StartPreviewEx();
				ScanCtrl.StartPreview();
				fun();
			}
	function stop_preview() 
			{   
				ScanCtrl.StopPreview();
				fun();
			}
			
    

    
    
    </script>
</html>    
