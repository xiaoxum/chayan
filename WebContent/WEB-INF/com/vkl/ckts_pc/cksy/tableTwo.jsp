<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>机动车抵押登记/质押备案申请表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDrag.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDialog.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/custom-validate.js"></script>
</head>

<body>
<form method="post" id="myform" action="">
  <div class="easyui-tabs" style="width:100%; height:auto;">
    <P style="font-size:14px; font-weight:bold; text-align:center; color:#003D71; height:40px; line-height:40px;">机动车抵押登记/质押备案申请表</P>
    <div title="申请表"  style="overflow:auto;padding:0px;">
      <table width="100%" cellspacing="0" cellpadding="0">
        <tr>
          <td class="bgc"><span class="star">*</span>申请事项</td>
          <td ><select class="easyui-combobox" style="width:90%; height:30px;" name="operType" id="operType">
              <c:forEach items="${operType }" var="list">
                <option value="${list.id}">${list.typeName }</option>
              </c:forEach>
            </select></td>
          <td  class="bgc"><span class="star">*</span>号牌种类</td>
          <td  ><select class="easyui-combobox" style="width:90%; height:30px;" name="hpzl">
              <c:forEach items="${hpzl }" var="list">
                <option value="${list.dictKey}">${list.ldictionaryAbel }</option>
              </c:forEach>
            </select></td>
          <td class="bgc"><span class="star">*</span>使用性质</td>
          <td><select class="easyui-combobox" style="width:90%;height:30px;" name="syxz">
              <c:forEach items="${cheUse }" var="list">
                <option value="${list.syxz}">${list.syxzName }</option>
              </c:forEach>
            </select></td>
        </tr>
        <tr>
           <td class="bgc"><span class="star">*</span>车辆类型</td>
           <td><select class="easyui-combobox" style="width:90%; height:30px;" name="clxh">
              <c:forEach items="${cllxType }" var="list">
                <option value="${list.cllx}">${list.cllxName }</option>
           </c:forEach>
            </select></td>
          <td  class="bgc"><span class="star">*</span>号牌号码</td>
          <td ><input class="easyui-textbox" style="width:50%; height:30px;" name="hphm" id="hphm"/><button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-search'"  onclick="platequery();">号牌查询</button></td>
          <td  class="bgc"></td>
          <td ></td>
        </tr>
        <tr>
          <td class="bgc"><span class="star">*</span>车主姓名/名称</td>
          <td><input class="easyui-textbox" style="width:50%; height:30px;" name="owName" id="owName"/><button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-cards__arrow'"  onclick="search();">扫描证件</button></td>
          <td class="bgc"><span class="star">*</span>邮寄地址</td>
          <td colspan="3"><input class="easyui-textbox" style="width:90%; height:30px;"name="owAddr" id="owAddr"/></td>
        </tr>
        <tr>
          <td class="bgc"><span class="star">*</span>身份证号码</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="owIdentity" id="owIdentity"/></td>
          <td class="bgc"><span class="star">*</span>联系手机</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="owTele" id="owPhone"/></td>
          <td class="bgc">邮政编码</td>
          <td><input class="easyui-textbox" style="width:90%;height:30px;" name="owPostcode" id="owPostcode"/></td>
        </tr>
        <tr>
          <td class="bgc">代理人姓名/名称</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="proxyName"/></td>
          <td class="bgc">邮寄地址</td>
          <td colspan="3"><input class="easyui-textbox" style="width:90%; height:30px;" name="proxyAddr"/></td>
        </tr>
        <tr>
          <td class="bgc">联系手机</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="proxyTele"/></td>
          <td class="bgc">邮政编码</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="proxyPostcode"/></td>
          <td class="bgc"></td>
          <td></td>
        </tr>
        <tr>
          <td class="bgc"><span class="star">*</span>抵押权人或典当行姓名/名称</td>
          <td><input class="easyui-textbox" style="width:50%; height:30px;" name="plerName" id="plerName"/><button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-cards__arrow'"  onclick="search();">扫描证件</button></td>
          <td class="bgc"><span class="star">*</span>抵押权人邮寄地址</td>
          <td colspan="3"><input class="easyui-textbox" style="width:90%; height:30px;" name="plerAddr" id="plerAddr"/></td>
        </tr>
        <tr>
          <td class="bgc"><span class="star">*</span>抵押权人身份证号码或工商注册号</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="plerIdenIndus" id="plerIdenIndus"/></td>
          <td class="bgc"><span class="star">*</span>抵押权人联系手机</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="plerTele" id="plerTele"/></td>
          <td class="bgc"><span class="star"></span>抵押权人邮政编码</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="plerPostcode"/></td>
        </tr>
        <tr>
          <td class="bgc">抵押权人或典当行代理人姓名/名称</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="pleProxyName"/></td>
          <td class="bgc">邮寄地址</td>
          <td colspan="3"><input class="easyui-textbox" style="width:90%; height:30px;" name="plerAddr"/></td>
        </tr>
        <tr>
          <td class="bgc">联系手机</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="pleProxyTele"/></td>
          <td class="bgc">邮政编码</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="pleProxyPostcode"/></td>
          <td class="bgc"></td>
          <td></td>
        </tr>
        <tr>
          <td class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ruler'"  onclick="dy();">受理单打印</button>
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-application_detail'"  onclick="search();">合格证扫描</button>
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-application_form'"  onclick="search();">国外合格证扫描</button>
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-cards__arrow'"  onclick="gaopai();">业务材料高拍</button>
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'"  onclick="window.history.go(-1)">取消</button>
            </td>
        </tr>
      </table>
    </div>
    <div title="合格证"  style="overflow:auto;padding:0px;">
      <table width="100%" cellspacing="0" cellpadding="0" id="hgzxx">
        <tr>
          <td  class="bgc">合格证编号</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="xh" id="xh" /></td>
          <td  class="bgc">发证日期</td>
          <td ><input class="easyui-textbox" style="width:80%; height:30px;" name="fzTime" id="fzTime"/></td>
          <td  class="bgc">车辆制造企业名称</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zzcmc" id="zzcmc"/></td>
        </tr>
        <tr>
          <td class="bgc">车辆品牌/车辆名称</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="clpp1" id="clpp1"/></td>
          <td class="bgc">车辆型号</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="copyClxh" id="clxh"/></td>
          <td class="bgc">车辆识别代号/车架号</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="copyClsbdh" id="clsbdh"/></td>
        </tr>
        <tr>
          <td class="bgc">车身颜色</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="csys" id="csys"/></td>
          <td class="bgc">底盘型号/底盘ID</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;"  name="dpType" id="dpType"/></td>
          <td class="bgc">底盘合格证编号</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="dpId" id="dpId"/></td>
        </tr>
        <tr>
          <td class="bgc">发动机型号</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="fdxh" id="fdxh"/></td>
          <td class="bgc">燃料种类</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;"  name="rlzl" id="rlzl"/></td>
          <td class="bgc">发动机号</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="fdjh" id="fdjh"/></td>
        </tr>
        <tr>
          <td class="bgc">排量和功率</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="gl20" id="gl20"/></td>
          <td class="bgc">排放标准</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;"  name="bfbz" id="bfbz"/></td>
          <td class="bgc">油耗</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="yh" id="yh"/></td>
        </tr>
        <tr>
          <td class="bgc">外廓尺寸</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="wkcc" id="wkcc"/></td>
          <td class="bgc">货厢内部尺寸</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="hxnbc" id="hxnbc"/></td>
          <td class="bgc">钢板弹簧片数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="gbthps" id="gbthps"/></td>
        </tr>
        <tr>
          <td class="bgc">轮胎数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="lts" id="lts"/></td>
          <td class="bgc">轮胎规格</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="ltgg" id="ltgg"/></td>
          <td class="bgc">轮距（前/后）</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="hl" id="hl"/></td>
        </tr>
        <tr>
          <td class="bgc">轴荷</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zh" id="zh"/></td>
          <td class="bgc">轴数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zs" id="zs"/></td>
          <td class="bgc">轴距</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zj" id="zj"/></td>
        </tr>
        <tr>
          <td class="bgc">总质量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zzl" id="zzl"/></td>
          <td class="bgc">转向形式</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zxxs" id="zxxs"/></td>
          <td class="bgc">整备质量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zbzl" id="zbzl"/></td>
        </tr>
        <tr>
          <td class="bgc">额定载质量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="hdzzl" id="hdzzl"/></td>
          <td class="bgc">载质量利用系数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zzllyxs" id="zzllyxs"/></td>
          <td class="bgc">准牵引总质量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zqyzl" id="zqyzl"/></td>
        </tr>
        <tr>
          <td class="bgc">半挂车鞍座最大允许总质量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="bkazzl" id="bkazzl"/></td>
          <td class="bgc">驾驶室准乘人数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="qpk" id="qpk"/></td>
          <td class="bgc">额定载客数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="hdzk" id="hdzk"/></td>
        </tr>
        <tr>
          <td class="bgc">最高设计车速</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zgscs" id="zgscs"/></td>
          <td class="bgc">车辆制造日期</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zzrq" id="zzrq"/></td>
          <td></td>
          <td></td>
        </tr>
      </table>
    </div>
  </div>
</form>
</body>
<script type="text/javascript" >

var srln; // 流水号变量

/*调用高拍页面,传递流水号进行高拍  */
function gaopai(){
	
	if(srln == undefined || srln.length <= 0 || srln == ""){
		
		 $.messager.alert("提示！", "请先进行业务受理打印");
	}else{
		
	
	var diag = new Dialog(); // 初始弹出层

   	diag.Width = 820;        // 弹出层宽度 

	diag.Height = 500;       // 弹出层高度 

	diag.Title = "高拍仪";     // 弹出层标题

	diag.URL = "<%=request.getContextPath()%>/module/servacpt/gaopai?srln="+srln;   //引入高拍页面并传递流水号

	diag.show();            //弹出层显示
	
	}
}


//写入受理数据并打印申请表
function dy(){
	
	if(!checkRegExp($("#owPostcode").val(), 'length', 6, "邮政编码长度不符")){
		return false
	}
	
	if(!checkRegExp($("#owName").val(), 'isChineseName', 6, "输入中文姓名")){
		return false
	}
	
	if(!checkRegExp($("#owPhone").val(), 'length', 11, "手机号长度不符")){
		return false
	}
	
	if(!checkRegExp($("#owPostcode").val(), 'length', 6, "邮政编码长度不符")){
		return false
	}
	
	
	
	if(!checkRegExp($("#plerTele").val(), 'length', 11, "手机号长度不符")){
		return false
	}
	
	if(!checkRegExp($("#owPostcode").val(), 'maxLength', 6, "邮政编码长度不符")){
		return false
	}
	
	if(!isCardID($("#owIdentity").val())){
		return false;
	}
	
	if (validateEmpty($("#hphm").val(),'号牌号码不能为空')){
		return false;
	}
	if (validateEmpty($("#owName").val(),'车主姓名不能为空')){
		return false;
	}
	if (validateEmpty($("#owAddr").val(),'邮寄地址不能为空')){
		return false;
	}
	if (validateEmpty($("#owIdentity").val(),'身份证号不能为空')){
		return false;
	}
	
	if (validateEmpty($("#owPhone").val(),'联系手机不能为空')){
		return false;
	}
	
	if (validateEmpty($("#plerName").val(),'抵押权人姓名不能为空')){
		return false;
	}
	
	if (validateEmpty($("#plerAddr").val(),'抵押权人邮寄地址不能为空')){
		return false;
	}
	
	if (validateEmpty($("#plerIdenIndus").val(),'抵押权人身份证号不能为空')){
		return false;
	}
	
	if (validateEmpty($("#plerTele").val(),'抵押权人联系手机不能为空')){
		return false;
	}
	
	if (validateEmpty($("#xh").val(),'合格证编号不能为空')){
		return false;
	}
	
	if (validateEmpty($("#clpp1").val(),'车辆名称不能为空')){
		return false;
	}
	
	if (validateEmpty($("#csys").val(),'车身颜色不能为空')){
		return false;
	}
	if (validateEmpty($("#fdxh").val(),'发动机型号不能为空')){
		return false;
	}
	if (validateEmpty($("#clxh").val(),'车辆型号不能为空')){
		return false;
	}
	if (validateEmpty($("#fdjh").val(),'发动机号不能为空')){
		return false;
	}
	if (validateEmpty($("#clsbdh").val(),'车辆识别代号不能为空')){
		return false;
	}
	
	$.ajax({
        cache: true,
        type: "POST",
        url:'<%=request.getContextPath()%>/module/servacpt/pledgeData',
        data:$('#myform').serialize(),  // 表单提交ID
        async: false,
        error: function(request) {
        	
        	$.messager.alert("提示！", "受理出错失败！");
            
        },
        success: function(data) {
        	if(data=="false"){
        		
            	  $.messager.alert("提示！", "受理出错失败！");
            	  
            	   
            	}else{
    
         	 srln = data;  // 传递流水号
             var diag = new Dialog();         // 弹出层初始化
 	      	diag.Width = 500;                // 宽度
 	      	diag.Height = 650;               // 高度
 	      	diag.Title = "打印申请";            // 标题
 	      	diag.URL = "<%=request.getContextPath()%>/module/servacpt/print?srln="+srln;   //引入高拍页面并传递流水号
 	      	diag.show();
           
            	}	
        	
        }
    });
}


//号牌查询
function platequery(){
	 
	
	$.ajax({
		type: "POST",
	    async: true,
		url: "<%=request.getContextPath()%>/module/servacpt/policeMessages",
		data:{ "hphm":$("#hphm").val()},
		dataType:"text",
		success: function (data) {
			 var json=JSON.parse(data); //将json字符串转为json对象	    	    
    	     var jsondata = json.data;  //得到结果集 
			if(json.errorCode=="0000"){
				$.messager.alert("提示！", "查询成功！");
				
				for (var key in jsondata)
    	        {
					
    	            $("#"+key).textbox("setValue",jsondata[key]);
    	            
    	        } 
				
			}else{
				$("#hgzxx input").val("");
				
				$.messager.alert("提示！", json.errorMsg);
			}
    	     
		}
  });
	
}




</script>
</html>
