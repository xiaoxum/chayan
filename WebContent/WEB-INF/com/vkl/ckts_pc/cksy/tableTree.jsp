<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>机动车查验申请</title>
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

<body onkeydown="keysDown(event)" onkeyup="keyup(event)">
<form method="post" id="myform" action="">
  <div class="easyui-tabs" style="width:100%; height:auto;">
    <P style="font-size:14px; font-weight:bold; text-align:center; color:#003D71; height:40px; line-height:40px;">机动车查验申请</P>
    <div title="申请表"  style="overflow:auto;padding:0px;">
      <table width="100%" cellspacing="0" cellpadding="0">
        <tr>
          <td  class="bgc"><span class="star">*</span>业务类型</td>
          <td><select class="easyui-combobox" style="width:90%; height:30px; " name="operType" id="operType"  editable="false" >
              <c:forEach items="${operType }" var="list">
                <option value="${list.id}">${list.typeName}</option>
              </c:forEach>
            </select></td>
            <td class="bgc"><span class="star">*</span>车辆类型</td>
          <td><input type="text" id="province" style="width: 180px" name = "clxh"
                      class="easyui-validatebox" validType="selectValid['--请选择--']" />
              <!-- <input type="text" id="city" name = "clxh" style="width: 128px"
                      class="easyui-validatebox" validType="selectValid['']" /> -->
            </td>
           <td class="bgc"><span class="star">*</span>使用性质</td>
          <td><select class="easyui-combobox" style="width:90%; height:30px;" id="syxz" name="syxz"editable="false">
              <c:forEach items="${cheUse }" var="list">
                <option value="${list.syxz}">${list.syxzName }</option>
              </c:forEach>
            </select></td>
         
        </tr>
        <tr>
        <td class="bgc"><span class="star">*</span>车辆识别代号/车架号</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="clsbdh" id="clsbdh" value="${chinfo.clsbdh}"/></td>
          <td  class="bgc">号牌号码</td>
          <td ><input class="easyui-textbox" style="width:50%; height:30px;" name="hphm" id="hphm"  value="${chinfo.hphm}"/><span style="color:red;"id="hbhmts"></span> <!-- <button type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-search'"   onclick="platequery();">号牌号码/识别代号查询</button> --> </td>
          <td  class="bgc"><span class="star">*</span>号牌种类</td>
          <td ><select class="easyui-combobox" style="width:90%; height:30px;" name="hpzl" editable="false" id="hpzl">
               <option value="">--请选择--</option>
              <c:forEach items="${hpzl}" var="list">
                <option value="${list.dictKey}">${list.ldictionaryAbel }</option>
              </c:forEach>
            </select></td>
          
        </tr>
        <tr>
          
           <td class="bgc"><span class="star">*</span>车身颜色</td>
          <td>
          <select class="easyui-combobox" style="width:28%; height:30px;"  id="csys1" name="csys1">
                 <option value="">-请选择-</option>
              <c:forEach items="${csys}" var="list" >
                <option value="${list.ldictionaryAbel}" class="ys">${list.ldictionaryAbel }</option>
              </c:forEach>
            </select>
            <select class="easyui-combobox" style="width:28%; height:30px;"  id="csys2" name="csys2" >
                <option value="">-请选择-</option>
              <c:forEach items="${csys}" var="list">
                <option value="${list.ldictionaryAbel}" class="ys1">${list.ldictionaryAbel }</option>
              </c:forEach>
            </select>
             <select class="easyui-combobox" style="width:28%; height:30px;"  id="csys3" name="csys3" >
                <option value="">-请选择-</option>
              <c:forEach items="${csys}" var="list">
                <option value="${list.ldictionaryAbel}" class="ys2">${list.ldictionaryAbel }</option>
              </c:forEach>
            </select>
          </td>
          <td class="bgc"><span class="star">*</span>额定载客数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="hdzk"  id="hdzk1" value="${vehInfo.hdzk}"/></td>
        </tr>
         <tr>
          <td class="bgc" colspan="6">
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ruler'"  onclick="yluUpdate();" style="width:130px;">预录入信息修改</button>

            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'"  onclick="window.history.go(-1)" style="width:60px;">取消</button>
            </td>
        </tr>
      </table>
    </div>
    
  </div>
</form>
<div style="display: block;width:0px;height: 0px;"><input id="sm" type="text" style="display: block;width:0px;height: 0px;"/>
    <input type="hidden" value="${chinfo.cllx}"/> 

</div>
</body>
<script type="text/javascript" >
$(function(){
	// 省级 

	 $('#province').combobox({
		    valueField:'cllx', //值字段
		    textField:'cllxName', //显示的字段
		    url:'<%=request.getContextPath()%>/module/servacpt/allcllx',
		    panelHeight:'300px',
		    required:true,
		    editable:false,//不可编辑，只能选择
		    value:'${chinfo.cllx}'

		 });
	//县市区 
		 $("#clsbdh").textbox({onChange:function(){
			 $(this).textbox("getValue");
			var clsbdh = $(this).textbox("getValue").toUpperCase();
			$(this).textbox("setValue",clsbdh);
			
		 }});
		var hphm = $("#hphm").textbox("getValue");
		if(hphm==null||hphm==""){
			 $("#hphm").textbox("setValue","赣A");
		}
		var csys = '${vehInfo.csys}';
		var csyss=csys.split("/")
		if(csyss!=null){
			 $("#csys1").combobox('setValue',csyss[0]);
		}
		if(csyss.length>1){
			 $("#csys2").combobox('setValue',csyss[1]);
		}
		if(csyss.length>2){
			 $("#csys3").combobox('setValue',csyss[2]);
		}
		//号牌大写
		 $("#hphm").textbox({onChange:function(){
		 	 $(this).textbox("getValue");
		 	var hphm = $(this).textbox("getValue").toUpperCase();
		 	$(this).textbox("setValue",hphm);
		 	
		 }});
		$("#hpzl").combobox("setValue","${chinfo.hpzl}");
		$("#syxz").combobox("setValue","${chinfo.syxz}");
		$("#operType").combobox("setValue","${chinfo.operType}");
		$("#hdzk1").textbox("setValue","${vehInfo.hdzk}");
		
});


    function yluUpdate(){
    	if($("#province").combobox("getValue") == " " || $("#province").combobox("getValue") == "--请选择--"||$("#province").combobox("getValue") == null){
    		$.messager.alert("提示！", "车辆类型不能为空");
    		return;
    	}
    	if (validateEmpty($("#clsbdh").val(),'车辆识别代号不能为空')){
    		return;
    	}
    	if(!checkRegExp($("#clsbdh").val(), "length", '17', "车辆识别代号长度不匹配，请输入17位车辆识别代号")){
    		return;
    	}
    	
    	if (validateEmpty($("#hpzl").combobox("getValue"),'请选择号牌种类')){
    		return;
    	}
    	 var csys1= $("#csys1").combobox('getValue');
    	 var csys2= $("#csys2").combobox('getValue');
    	 var csys3= $("#csys3").combobox('getValue');
    	if((csys1==null&&csys2==null&&csys3==null)||(csys1==""&&csys2==""&&csys3=="")){
    		$.messager.alert("提示！", "请选择车身颜色！");
    		return;
    	}
    	
    	if (validateEmpty($("#hdzk1").textbox("getValue"),'请输入核定载客数')){
    		return;
    	}
    	  var srln='${chinfo.srln}';
    	  var rckCount='${chinfo.rckCount}'
	      var cllx=$('#province').combobox("getValue");
	      var hphm = $("#hphm").textbox("getValue");
	      var hpzl=$("#hpzl").combobox("getValue");
	      var clsbdh=$("#clsbdh").textbox("getValue");
	      var hdzk1=$("#hdzk1").textbox("getValue");
	      var operType=$("#operType").combobox("getValue");
	      var syxz=$("#syxz").combobox("getValue");
	  	$.ajax({
	        cache: true,
	        type: "POST",
	        url:'<%=request.getContextPath()%>/module/servacpt/updateckinfo',
	        data:{
	        	csys1:csys1,
	        	csys2:csys2,
	        	csys3:csys3,
	        	hdzk:hdzk1,
	        	clsbdh:clsbdh,
	        	cllx:cllx,
	        	hphm:hphm,
	        	operType:operType,
	        	syxz:syxz,
	        	hpzl:hpzl,
	        	srln:srln,
	        	rckCount:rckCount
	        	
	        },  // 表单提交ID
	        async: false,
	        error: function(request) {
	        	
	        	$.messager.alert("提示！", "预录入信息修改失败！");
	           
	        },
	        success: function(data) {
	        	if(data=="false"){
	        	  $.messager.alert("提示！", "预录入信息修改失败！");
	        	}else if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}else{
	          		 $.messager.alert("提示！", "预录入修改成功！");
	          		 window.location="<%=request.getContextPath() %>/module/vehicleinfo/loadlist"
	        	}
	        }	
	    });
      }
</script>

</body>
</html>
