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
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/sm.js"></script>
</head>
<object id="vehPrinter" classid="clsid:F1EFB37B-C778-4AF8-B0C2-B647C9E89E2D"></object>
<body >
<form method="post" id="myform" action="">
  <div class="easyui-tabs" style="width:100%; height:auto;">
    <P style="font-size:14px; font-weight:bold; text-align:center; color:#003D71; height:40px; line-height:40px;">机动车查验申请</P>
    <div title="申请表"  style="overflow:auto;padding:0px;">
      <table width="100%" cellspacing="0" cellpadding="0">
        <tr>
          <td  class="bgc"><span class="star">*</span>业务类型</td>
          <td><select class="easyui-combobox" style="width:90%; height:30px; " name="operType" id="operType"  editable="false">
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
          <td><select class="easyui-combobox" style="width:90%; height:30px;" name="syxz" editable="false" id="syxzw">
              <c:forEach items="${cheUse }" var="list">
                <option value="${list.syxz}">${list.syxzName }</option>
              </c:forEach>
            </select></td>
         
        </tr>
        <tr>
        <td class="bgc"><span class="star">*</span>车辆识别代号/车架号</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="clsbdh" id="clsbdh"/></td>
          <td  class="bgc"><span class="star" id="hphmspan"></span>号牌号码</td>
          <td >
           <select class="easyui-combobox" style="width:40px; height:30px;"  name="xzqh" id="xzqh">
                 <option value="赣">赣 </option>
                 <option value="粤">粤</option>
                 <option value="沪">沪</option>
                 <option value="浙">浙</option>
                 <option value="蒙">蒙</option>
                 <option value="苏">苏</option>
                 <option value="皖">皖</option>
                 <option value="闽">闽</option>
                 <option value="鲁">鲁</option>
                 <option value="鄂">鄂</option>
                 <option value="湘">湘</option>
                 <option value="豫">豫</option>
                 <option value="京">京</option>
                 <option value="津">津</option>
                 <option value="冀">冀</option>
                 <option value="晋">晋</option>
                 <option value="辽">辽</option>
                 <option value="吉">吉</option>
                 <option value="黑">黑</option>
                 <option value="闽">闽</option>
                 <option value="新">新</option>
                 <option value="桂">桂</option>
                 <option value="琼">琼</option>
                 <option value="渝">渝</option>
                 <option value="川">川</option>  
                 <option value="蜀">蜀</option>
                 <option value="黔">黔</option> 
                 <option value="贵">贵</option>
                 <option value="云">云</option>  
                 <option value="陕">陕</option>  
                 <option value="甘">甘</option>
                 <option value="青">青</option>
                 <option value="宁">宁</option>
                 <option value="新">新</option>
                 <option value="藏">藏</option> 
                 <option value="陇">陇</option>
                 <option value="秦">秦</option>
                 <option value="滇">滇</option> 
            </select>
              <select class="easyui-combobox" style="width:40px; height:30px;"   name="pinyin" id="pinyin">
                 <option value="A">A</option>
                 <option value="M">M</option>
                 <option value="B">B</option>
                 <option value="C">C</option>
                 <option value="D">D</option>
                 <option value="E">E</option>
                 <option value="F">F</option>
                 <option value="G">G</option>
                 <option value="H">H</option>
                 <option value="J">J</option>
                 <option value="K">K</option>
                 <option value="L">L</option>
                 <option value="N">N</option>
                 <option value="O">O</option>
                 <option value="P">P</option>
                 <option value="Q">Q</option>
                 <option value="R">R</option>
                 <option value="S">S</option>
                 <option value="T">T</option>
                 <option value="U">U</option>
                 <option value="V">V</option>
                 <option value="W">W</option>
                 <option value="X">X</option>
                 <option value="Y">Y</option>
                 <option value="Z">Z</option>
            </select>
          <input class="easyui-textbox" style="width:40%; height:30px;" name="hphm" id="hphm"  value=""/>  </td>
          <td  class="bgc"><span class="star">*</span>号牌种类</td>
          <td ><select class="easyui-combobox" style="width:90%; height:30px;" name="hpzl" editable="false" id="hpzl">
               <option value="">--请选择--</option>
              <c:forEach items="${hpzl}" var="list">
                <option value="${list.dictKey}">${list.ldictionaryAbel }</option>
              </c:forEach>
            </select></td>
          
        </tr>
        <tr>
        <td class="bgc">车辆品牌/车辆名称</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="clpp1" id="clpp1"/></td>
          
          <%--< td class="bgc"><span class="star"></span>注销登记原因</td>
          <td><select class="easyui-combobox" style="width:92%;  height:30px;" name="logoffCause" id="logoffCause">
              <option ></option>
              <c:forEach items="${djyy }" var="list">
                <option value="${list.dictKey}">${list.ldictionaryAbel }</option>
              </c:forEach>
            </select></td> --%>
           <td class="bgc"><span class="star" id="ysspan">*</span>车身颜色</td>
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
          <td class="bgc"><span class="star" id="hdzkspan">*</span>核定载客数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="hdzk"  id="hdzk1"/></td>
        </tr>
        <tr>
          <td class="bgc"><span class="star"></span>车主姓名/名称</td>
          <td><input class="easyui-textbox" style="width:50%; height:30px;" name="owName" id="owName"/><!-- <button type="button" class="easyui-linkbutton" data-options="iconCls:'icon-cards__arrow'"  onclick="search();">证件扫描</button> --></td>
          <td class="bgc"><span class="star"></span>联系手机</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="owPhone" id="owPhone"/></td>
          <td class="bgc"><span class="star"></span>邮寄地址</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="owAddr"  id="owAddr"/></td>
        </tr>
        <tr>
          <td class="bgc"><span class="star"></span>身份证号码</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="owIdentity" id="owIdentity"/></td>
          <td class="bgc"><span class="star"></span>邮政编码</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="owPostcode" id="owPostcode"/></td>
          <td class="bgc"><span class="star"></span>电子信箱</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="owEmial" id="owEmial"/></td>
        </tr>
        <tr>
          <td class="bgc">移出车辆管理所</td>
          <td colspan="5" ><label style="width:50px; height:30px;">转入</label>
            <input class="easyui-textbox marginl20" style=" width:300px; height:30px;"id="zrs"/>
            <label style="width:50px; height:30px;">省（自治区、直辖市）</label>
            <input class="easyui-textbox" style=" width:300px; height:30px;" id="zrs"/>
            <label style="">（县、州）车辆管理所</label></td>
        </tr>
        <tr>
          <td class="bgc">代理人姓名/名称</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;" name="proxyName" id="proxyName"/></td>
          <td class="bgc">联系手机</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="proxyPhone"  id="proxyPhone"/></td>
          <td class="bgc">邮寄地址</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="proxyAddr" id="proxyAddr"/></td>
        </tr>
        <tr>
          <td class="bgc">联系电话</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="" id="lxdh"/></td>
          <td class="bgc">邮政编码</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="proxyPostcode" id="proxyPostcode"/></td>
          <td class="bgc">电子信箱</td>
          <td><input class="easyui-textbox" style="width:90%; height:30px;"name="proxyEmail" id="proxyEmail"/></td>
        </tr>
        <tr>
         
          <td class="bgc" colspan="6">
         <span style="color:red;margin-right:20px;text-align: left;"id="hbhmts"></span>
           <!--  <button  type="button"   onclick="ylu();" style="width:70px; height: 30px; background:DeepSkyBlue;border-radius:10px; color:white;">保  存</button> -->
         <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ruler'"  onclick="ylu();" style="width:70px;">保       存</button>
            <!-- <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-ruler'"  onclick="dy();" style="width:110px;">受理单打印</button> -->
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-application_detail'"  onclick="hqjdcxx();" style="width:150px;">查询机动车基本信息</button>
           <!--  <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-cards__arrow'"  onclick="gaopai();" style="width:120px;">业务材料高拍</button> -->
            <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'"  onclick="resertData()" style="width:60px;">清空</button>
            <!-- <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-undo'"  onclick="window.history.go(-1)" style="width:60px;">取消</button> -->
            </td>
        </tr>
      </table>
    </div>
    <div title="其他信息"  style="overflow:auto;padding:0px;">
      <table width="100%" cellspacing="0" cellpadding="0" id="hgzxx">
        <tr>
          <td  class="bgc">编号</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="xh" id="xh" /></td>
          <td  class="bgc">发证日期</td>
          <td ><input class="easyui-textbox" style="width:80%; height:30px;" name="fzTime" id="fzTime"/></td>
          <td  class="bgc">车辆制造企业名称</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zzcmc" id="zzcmc"/></td>
        </tr>
        <tr>
          <td class="bgc"><span class="star"></span>获得方式</td>
          <td><select class="easyui-combobox" style="width:80%; height:30px;"name="getWay" editable="false" id="getWay">
              <c:forEach items="${hqfs }" var="list">
                <option value="${list.dictKey}">${list.ldictionaryAbel }</option>
              </c:forEach>
            </select></td>
          <td class="bgc">车辆型号</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="copyClxh" id="copyClxh"/></td>
          <td class="bgc">车辆识别代号/车架号</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="copyClsbdh" id="copyClsbdh"/></td>
        </tr>
        <tr>
          <td class="bgc">车身颜色</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;"  id="csys"/></td>
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
          <td class="bgc">功率</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="gl20-h" id="gl20"/></td>
           <td class="bgc">排量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="pl-h" id="pl"/></td>
          <td class="bgc">排放标准</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;"  name="bfbz" id="bfbz"/></td>
         
        </tr>
        <tr>
         <td class="bgc">油耗</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="yh4" id="yh"/></td>
          <td class="bgc">外廓尺寸</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="wkcc" id="wkcc"/></td>
          <td class="bgc">货厢内部尺寸</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="hxnbc" id="hxnbc"/></td>
         
        </tr>
        <tr>
         <td class="bgc">钢板弹簧片数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="gbthps-4" id="gbthps"/></td>
          <td class="bgc">轮胎数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="lts" id="lts"/></td>
          <td class="bgc">轮胎规格</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="ltgg" id="ltgg"/></td>
         
        </tr>
        <tr>
         <td class="bgc">轮距（前/后）</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="hl" id="hl"/></td>
          <td class="bgc">轴荷</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zh" id="zh"/></td>
          <td class="bgc">轴数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zs-h" id="zs"/></td>
          
        </tr>
        <tr>
        <td class="bgc">轴距</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zj-h" id="zj"/></td>
          <td class="bgc">总质量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zzl-h" id="zzl"/></td>
          <td class="bgc">转向形式</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zxxs" id="zxxs"/></td>
         
        </tr>
        <tr>
         <td class="bgc">整备质量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zbzl" id="zbzl"/></td>
          <td class="bgc">额定载质量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="hdzzl" id="hdzzl"/></td>
          <td class="bgc">载质量利用系数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zzllyxs" id="zzllyxs"/></td>
        </tr>
        <tr>
        <td class="bgc">准牵引总质量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zqyzl-h" id="zqyzl"/></td>
          <td class="bgc">半挂车鞍座最大允许总质量</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="bkazzlt" id="bkazzl"/></td>
          <td class="bgc">驾驶室准乘人数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="qpk-h" id="qpk"/></td>
        </tr>
         <tr>
         <td class="bgc">额定载客数</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;"  id="hdzk"/></td>
          <td class="bgc">最高设计车速</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zgscs-h" id="zgscs"/></td>
          <td class="bgc">车辆制造日期</td>
          <td><input class="easyui-textbox" style="width:80%; height:30px;" name="zzrq" id="zzrq"/></td>
         
        </tr>
      </table>
    </div>
  </div>
</form>
<div style="display: block;width:0px;height: 0px;">
<!--判读简易合格证时候赋值  -->	
 <input id="hidhgzzt" type="text" style="display: block;width:0px;height: 0px;"/> 


    

</div>

</body>
<script type="text/javascript" >
$(function(){
	 /*  $("#sm").focus(); */
	// 省级 
	 $('#province').combobox({
		    valueField:'cllx', //值字段
		    textField:'cllxName', //显示的字段
		    url:'<%=request.getContextPath()%>/module/servacpt/allcllx',
		    panelHeight:'300px',
		    required:false,
		    editable:true,//不可编辑，只能选择
		    value:'K33',
		    onLoadSuccess:function(){
		    	 $('#province').next(".combo").find("input").focus(function(){
		    		 $('#province').combobox("setValue","");
			     })
		    }
		 });
	//县市区 

		 $("#clsbdh").textbox({onChange:function(){
			 $(this).textbox("getValue");
			var clsbdh = $(this).textbox("getValue").toUpperCase();
			$(this).textbox("setValue",clsbdh);
			
		 }});
	
		//号牌大写
		 $("#hphm").textbox({onChange:function(){
		 	 $(this).textbox("getValue");
		 	var hphm = $(this).textbox("getValue").toUpperCase();
		 	$(this).textbox("setValue",hphm);
		 	
		 }});
		$("#hpzl").combobox("setValue","02");
		setInterval("readQrtext()", 1500);//定时获取
		
})
  /*  function keysDown(e){
	var ev = e || window.event;
	
	if(ev.keyCode==231 || ev.keyCode==0){
		$("#sm").focus();
	}
	
    }
	
	function keyup(e){
		var ev = e || window.event;
		if(ev.keyCode==231 || ev.keyCode==0){
			keyss()
		}
	}
 */






//字符串转日期格式，strDate要转为日期格式的字符串
function getDate(str) {
	   var year = str.split('')[0]+str.split('')[1]+str.split('')[2]+str.split('')[3];
	    var month = str.split('')[5]+str.split('')[6];
	    var day = str.split('')[08]+str.split('')[9];
	    
    return year+"-"+month+"-"+day;
}


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


	
/* function keyss(){
	var strbarcode = vehPrinter.GetQrText();
	
	var str =  strbarcode;
	var strs = new Array();
	//var  str="ZCCCHGZ_V3.1 20091013|QX|WAE001202225419|上海大众汽车有限公司|乘用车及客车|朗逸牌/LAVIDA|SVW7167MSD|LSVAM4180C2198377||411039|CPJ|GB18352.3-2005国IV|1598|77||5|2012年11月02日|白/黑|A|1620|1245|";
	//var str1="ZCCCHGZ_V3.1 20091013|QX|WCT0XA700755000|一汽解放青岛汽车有限公司|货车|解放牌|CA5250CCYP1K2L7T3E5A80-1|LFNCRUMX8HAC11515||52846218|CA6DLD-26E5|GB17691-2005国Ⅴ,GB3847-2005|6600|194|14420||2017年05月07日|青闪银 |B|25000|10450|";
	strs = str.split("|"); 
	 //strs[0]//合格证版本号及合格证类型
	 //strs[1]//合格证的具体类型
	 if(strs.length==1){
		 $("#clsbdh").textbox("setValue",strs[0]);
	 }else{
		 $("#xh").textbox("setValue",strs[2]);//合格证编号
		 $("#zzcmc").textbox("setValue",strs[3]);//车辆制造企业名称
		// $("#clxh").textbox("setValue",strs[4]);//车辆类型
		 $("#clpp1").textbox("setValue",strs[5]);//车辆品牌
		 $("#copyClxh").textbox("setValue",strs[6]);//车辆型号
		 $("#clsbdh").textbox("setValue",strs[7]);//车辆识别代号、车架号
		 $("#copyClsbdh").textbox("setValue",strs[7]);//车辆识别代号、车架号
		 //strs[8]//车架号
		 $("#fdjh").textbox("setValue",strs[9]); //发动机号
		 $("#fdxh").textbox("setValue",strs[10]);//发动机型号
		 $("#bfbz").textbox("setValue",strs[11]);//排放标准
		 $("#gl20").textbox("setValue",strs[13]);//排量//功率Kw
		 $("#pl").textbox("setValue",strs[12]);//排量//功率Kw
		 $("#hdzzl").textbox("setValue",strs[14]);//额定载质量
		 $("#hdzk").textbox("setValue",strs[15]);//额定载客人数
		 $("#hdzk1").textbox("setValue",strs[15]);//额定载客人数
		 //$("#zzrq").textbox("setValue",getDate(strs[16]));//车辆制造日期
		 $("#csys").textbox("setValue",strs[17]);//车身颜色
		 $("#rlzl").textbox("setValue",strs[18]);//燃料种类
		 $("#zzl").textbox("setValue",strs[19]);//总质量
		 $("#zbzl").textbox("setValue",strs[20]);//整备质量
		 var yss=strs[17];
	       var ysa=$(".ys");
	       var csysz;
	       var ysav;
	       
			 $.each(ysa,function(index,ys){
				 var value=$(ys).val();
				 ysav=ysav+","+value;
			 });
			   
	      
	    var num=0; 
              var flag=true;
             var index=yss.indexOf("/");
            


	     for(var i=0;i<yss.length;i=i+1){
                      
		      var ys=yss.charAt(num);
                     // var ys=yss[num];
                      num=num+1;

		      if(ys.indexOf("金")>=0){
		    	     ys="黄";
			  }else if(ys.indexOf("银")>=0){
					 ys="灰";
			  }
		         
			  if(ysav.indexOf(ys)>=0){
				
				 var  csys1 = $("#csys1").combobox('getValue');
				
				 if(csys1==null||csys1==""){
					 $("#csys1").combobox('setValue',ys);
                                         flag=false;
					 csysz=ys;
                                         ys="";
                                         
				 }
                               var  csys2 = $("#csys2").combobox('getValue');
                                if(csys1!=null&&csys1!=""&&csys1!=ys){
                                          
					
					 if(csys2==null||csys2==""){
                                                   
						 $("#csys2").combobox('setValue',ys);
						 csysz=csysz+"/"+ys;
					 }
				 }

                       if(csys2!=null&&csys2!=""&&csys2!=ys&&ys!=csys1){
				       var  csys3 = $("#csys3").combobox('getValue');
				       if(csys3==null||csys3==""){
					    $("#csys3").combobox('setValue',ys);
					    csysz=csysz+"/"+ys;
				       }
				}



			  }
                     
	     }
		 $("#csys").textbox("setValue",csysz);//车身颜色
	 }
}
 */





/*业务类型改变 时 */
$("#operType").combobox({

	onChange: function (n,o) {

	if($("#operType").combobox('getValue')=="A"){
		$("#hdzkspan").html("*");
		$("#ysspan").html("*");
		
	}else{
		$("#hdzkspan").html("");
		$("#ysspan").html("");
	}

	}

	});
	



function ylu(){

	if($("#province").combobox("getValue") == "" || $("#province").combobox("getValue") == "--请选择--"||$("#province").combobox("getValue") == null){
		$.messager.alert("提示！", "车辆类型不能为空");
		return;
	}
	if (validateEmpty($("#clsbdh").val(),'车辆识别代号不能为空')){
		return;
	}
	if(!checkRegExp($("#clsbdh").val(), "minLength", '5', "请输入5位以上车辆识别代号")){
		return;
	}
	
	if (validateEmpty($("#hpzl").combobox("getValue"),'请选择号牌种类')){
		return;
	}
	 var csys1= $("#csys1").combobox('getValue');
	 var csys2= $("#csys2").combobox('getValue');
	 var csys3= $("#csys3").combobox('getValue');
	if($("#operType").combobox("getValue")=="A"){
		if((csys1==null&&csys2==null&&csys3==null)||(csys1==""&&csys2==""&&csys3=="")){
			$.messager.alert("提示！", "请选择车身颜色！");
			return;
		}
		if (validateEmpty($("#hdzk1").textbox("getValue"),'请输入核定载客数')){
			return;
		}
		$("#hphmspan").html("");
		
	}

	/*写入受理车辆信息  请求*/
	$.ajax({
        cache: true,
        type: "POST",
        url:'<%=request.getContextPath()%>/module/servacpt/registeredData',
        data:$('#myform').serialize(),  // 表单提交ID
        async: false,
        error: function(request) {
        	
        	$.messager.alert("提示！", "受理出错失败！");
           
        },
        success: function(data) {
        	if(data=="false"){
        	  $.messager.alert("提示！", "受理出错失败！");
        	}else if(data=="no-login"){
        		top.location="<%=request.getContextPath() %>/login.jsp"
        	} else if(data=="false2"){
          	  $.messager.alert("提示！", "当前车辆于当天已受理成功，不需重复受理！");
          	}else if(data=="false3"){
          		 $.messager.alert("提示！", "当前部门未备案，不能进行查验的业务！");
          	}else{
          		 $.messager.alert("提示！", "预录入成功！");
          		location.reload();
        	}
        }	
    });
}
	
	
	
	
	
	/* 写入受理数据并打印申请表 */ 
	function dy(){
		if($("#city").combobox("getValue") == " " || $("#city").combobox("getValue") == "--请选择--"||$("#city").combobox("getValue") == null){
			$.messager.alert("提示！", "车辆类型不能为空");
			return;
		}
		if (validateEmpty($("#clsbdh").val(),'车辆识别代号不能为空')){
			return;
		}
		
		if(!checkRegExp($("#clsbdh").val(), "length", '17', "车辆识别代号长度不匹配，请输入17位车辆识别代号")){
			return;
		}
		$.ajax({
            cache: true,
            type: "POST",
            url:'<%=request.getContextPath()%>/module/servacpt/registeredData',
            data:$('#myform').serialize(),  // 表单提交ID
            async: false,
            error: function(request) {
            	$.messager.alert("提示！", "受理出错失败！");
            },
            success: function(data) {
            	if(data=="false"){
            	  $.messager.alert("提示！", "信息录入异常！");
            	}else if(data=="no-login"){
            		top.location="<%=request.getContextPath() %>/login.jsp"
            	} else if(data=="false2"){
              	  $.messager.alert("提示！", "当前车辆于当天已受理成功，不需重复受理！");
              	}else{
                srln = data;
                print(srln);
             		
            	}
            	
            }	
          
        });
	}
	
	
	
	function print(srln){
		$.ajax({
			type: "POST",
		    async: true,
			url: "<%=request.getContextPath()%>/module/servacpt/print",
			data:{"srln":srln,
				   "rckCount":0 //受理时初始次数为0
				  }, // 号牌号码 车辆识别代号任意参数
			dataType:"text",
			success: function (data) {
				if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
			}
	  });
		
		
	}
	

	
	
	 function hqjdcxx(){
		 var xzqh  = $("#xzqh").combobox("getValue");
		 var pinyin = $("#pinyin").combobox("getValue");
		 var hphm = $("#hphm").textbox("getValue");
	     var hpzl = $("#hpzl").combobox("getValue");
	     //var cllx=$('#city').combobox("getValue");
	     var clsbdh = $("#clsbdh").val();
	      var time=new Date();
	     $.ajax({
				type: "POST",
			    async: true,
				url: "<%=request.getContextPath()%>/module/servacpt/getjdcjbxx?time="+time,
				data:{
					    clsbdh:clsbdh, 
					    hphm:xzqh+pinyin+hphm,
					    hpzl:hpzl
					   // cllx:cllx
					 }, // 号牌号码 车辆识别代号任意参数
				success: function (data) {
					if(data=="no-login"){
						top.location="<%=request.getContextPath() %>/login.jsp"
		        	}
					if(data!=null&&data!=""){
						resertData();
						$("#hbhmts").html("");
						$("#xh").textbox("setValue",data.xh);
						$("#owIdentity").textbox("setValue",data.sfzmhm);
						$("#owName").textbox("setValue",data.syr);
						$('#province').combobox("setValue",data.cllx);
						$("#syxzw").combobox("setValue",data.syxz);
						$("#hpzl").combobox("setValue",hpzl);
						$("#zzcmc").textbox("setValue",data.zzcmc);
						$("#clpp1").textbox("setValue",data.clpp1);
						$("#xzqh").combobox("setValue",xzqh)
		                 $("#pinyin").combobox("setValue",pinyin);
						$("#hphm").textbox("setValue",hphm);
						$("#copyClxh").textbox("setValue",data.clxh);
						$("#clsbdh").textbox("setValue",data.clsbdh);
						$("#copyClsbdh").textbox("setValue",data.clsbdh);
						$("#csys").textbox("setValue",data.csys);
						var ysa=$(".ys");
					       var csysz;
					       var ysav;
							 $.each(ysa,function(index,ys){
								 var value=$(ys).val();
								 ysav=ysav+","+value;
							 });
					   var yss=  data.csys;  
					     for(var i=0;i<yss.length;i++){
						      var ys=yss.charAt(i);
						      if(ys.indexOf("金")>=0){
						    	     ys="黄";
							  }else if(ys.indexOf("银")>=0){
									 ys="灰";
							  }
						    
							  if(ysav.indexOf(ys)>=0){
								 var  csys1 = $("#csys1").combobox('getValue');
								 if(csys1==null||csys1==""){
									 $("#csys1").combobox('setValue',ys);
								 }else{
									 var  csys2 = $("#csys2").combobox('getValue');
									 if(csys2==null||csys2==""){
										 $("#csys2").combobox('setValue',ys);
									 }else{
										 var  csys3 = $("#csys3").combobox('getValue');
										 if(csys3==null||csys3==""){
											 $("#csys3").combobox('setValue',ys);
										 }
									 }
								 }
							  }
					     }
						$("#fdxh").textbox("setValue",data.fdjxh);
						$("#rlzl").textbox("setValue",data.rlzl);
						$("#fdjh").textbox("setValue",data.fdjh);
						$("#gl20").textbox("setValue",data.gl);
						$("#pl").textbox("setValue",data.pl);
						$("#wkcc").textbox("setValue",data.cwkc+"*"+data.cwkk+"*"+data.cwkg);
						if(data.hxnbcd!=null){
							$("#hxnbc").textbox("setValue",data.hxnbcd+"*"+data.hxnbkd+"*"+data.hxnbgd);
						}
						$("#gbthps").textbox("setValue",data.gbthps);
						$("#lts").textbox("setValue",data.lts);
						$("#ltgg").textbox("setValue",data.ltgg);
						if(data.hxnbcd!=null){
							$("#hl").textbox("setValue",data.hxnbcd+"*"+data.hxnbkd);
						}
						$("#zs").textbox("setValue",data.zs);
						$("#zj").textbox("setValue",data.zj);
						$("#zzl").textbox("setValue",data.zzl);
						$("#zxxs").textbox("setValue",data.zxxs);
						$("#zbzl").textbox("setValue",data.zbzl);
						$("#zbzl").textbox("setValue",data.zbzl);
						$("#hdzzl").textbox("setValue",data.hdzzl);
						$("#zqyzl").textbox("setValue",data.zqyzl);
						$("#qpk").textbox("setValue",data.qpzk+data.hpzk);
						$("#hdzk").textbox("setValue",data.hdzk);
						$("#zbzl").textbox("setValue",data.zbzl);
						$("#zbzl").textbox("setValue",data.zbzl);
						 $("#fzTime").textbox("setValue",data.ccrqs);
						if(data.hdzk==null){
							$("#hdzk1").textbox("setValue",0);
						}else{
							$("#hdzk1").textbox("setValue",data.hdzk);
						}
						
					}else{
						$("#hbhmts").html(" 获取车辆信息失败");
						//$.messager.alert("提示！","当前车辆基本信息调用为空")
					}
				}
		  });
	 }
	 
	 function resertData(){
		    $("#hbhmts").html("");
		    /* $("#sm").val(""); */
		    $("#operType").combobox("setValue","A");
		    $("#province").combobox("setValue","K33");
		    $("#syxzw").combobox("setValue","A");
		    $("#clsbdh").textbox("setValue","");
		    $("#hphm").textbox("setValue","");
		    $("#xzqh").combobox("setValue","赣")
            $("#pinyin").combobox("setValue","A");
		    $("#hpzl").combobox("setValue","02");
		    $("#getWay").combobox("setValue","01");
		    $("#csys1").combobox("setValue","");
		    $("#csys2").combobox("setValue","");
		    $("#csys3").combobox("setValue","");
		    $("#hdzk1").textbox("setValue","");
		    $("#owName").textbox("setValue","");
		    $("#owPhone").textbox("setValue","");
		    $("#owAddr").textbox("setValue","");
		    $("#owIdentity").textbox("setValue","");
		    $("#owPostcode").textbox("setValue","");
		    $("#owEmial").textbox("setValue","");
		    $("#zrs").textbox("setValue","");
		    $("#zrs").textbox("setValue","");
		    $("#proxyName").textbox("setValue","");
		    $("#proxyPhone").textbox("setValue","");
		    $("#proxyAddr").textbox("setValue","");
		    $("#lxdh").textbox("setValue","");
		    $("#proxyPostcode").textbox("setValue","");
		    $("#proxyEmail").textbox("setValue","");
		    $("#xh").textbox("setValue","");
		    $("#fzTime").textbox("setValue","");
		    $("#zzcmc").textbox("setValue","");
		    $("#clpp1").textbox("setValue","");
		    $("#copyClxh").textbox("setValue","");
		    $("#copyClsbdh").textbox("setValue","");
		    $("#csys").textbox("setValue","");
		    $("#dpType").textbox("setValue","");
		    $("#dpId").textbox("setValue","");
		    $("#fdxh").textbox("setValue","");
		    $("#rlzl").textbox("setValue","");
		    $("#fdjh").textbox("setValue","");
		    $("#gl20").textbox("setValue","");
		    $("#pl").textbox("setValue","");
		    $("#bfbz").textbox("setValue","");
		    $("#yh").textbox("setValue","");
		    $("#wkcc").textbox("setValue","");
		    $("#hxnbc").textbox("setValue","");
		    $("#gbthps").textbox("setValue","");
		    $("#lts").textbox("setValue","");
		    $("#ltgg").textbox("setValue","");
		    $("#hl").textbox("setValue","");
		    $("#zh").textbox("setValue","");
		    $("#zs").textbox("setValue","");
		    $("#zj").textbox("setValue","");
		    $("#zzl").textbox("setValue","");
		    $("#zxxs").textbox("setValue","");
		    $("#zbzl").textbox("setValue","");
		    $("#hdzzl").textbox("setValue","");
		    $("#zzllyxs").textbox("setValue","");
		    $("#zqyzl").textbox("setValue","");
		    $("#bkazzl").textbox("setValue","");
		    $("#qpk").textbox("setValue","");
		    $("#hdzk").textbox("setValue","");
		    $("#zgscs").textbox("setValue","");
		    $("#zzrq").textbox("setValue","");
		   
	 }

	 
	 
	 
</script>

</body>
</html>
