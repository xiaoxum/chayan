<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<style>
table {
	font-size: 14px
}
p {
	margin: 0
}
table {
	border: 2px solid #000000;
	margin-top: 5px;
}
td {
	border: 1px solid #000000;
	text-align: center;
	padding: 0px;
}
input {
	margin-bottom: -5px;
}
.left {
	text-align: left;
	text-indent: 5px
}
</style>
    <script type="text/jscript" src="jquery-2.2.4.min.js"></script>
</head>
<body>
<div style="width:728px;  padding:50px; margin:auto;"  >
  <div>
    <h2 align="center">机&nbsp;动&nbsp;车&nbsp;查&nbsp;验&nbsp;记&nbsp;录&nbsp;表</h2>
    <div style="font-size:14px; float:left; width:400px;">号牌号码（流水号或其他与车辆能对应的号码）：<span name="carno"></span> </div>
    <div style="font-size:14px; float:left; width:150px;"  > 号牌种类：<span name="cardkind"></span> </div>
    <div style="font-size:14px;"> 使用性质： </div>
    <table width="728" height="866"  cellpadding="2" cellspacing="0" >
      <tbody>
        <tr>
          <td height="93" colspan="8" class="left" style=" font-size:12px;  "> 业务类型：
            <input type="checkbox" name="ywlx" value="注册登记"/>
            注册登记
            <input type="checkbox" name="ywlx" value="转入"/>
            转入
            <input type="checkbox" name="ywlx" value="转移登记"/>
            转移登记
            <input type="checkbox" name="ywlx" value="变更迁出"/>
            变更迁出
            <input type="checkbox" name="ywlx" value="变更车身颜色"/>
            变更车身颜色
            <input type="checkbox" name="ywlx" value="更换车身或者车架"/>
            更换车身或者车架<br>
            <br>
            <input type="checkbox" name="ywlx" value="更换整车"/>
            更换整车
            <input type="checkbox" name="ywlx" value="更换发动机"/>
            更换发动机
            <input type="checkbox" name="ywlx" value="变更使用性质"/>
            变更使用性质
            <input type="checkbox" name="ywlx" value="重新打刻VIN"/>
            重新打刻VIN
            <input type="checkbox" name="ywlx" value="重新打刻发动机"/>
            重新打刻发动机<br>
            <br>
            <input type="checkbox" name="ywlx" value="加装/拆除操纵辅助装置"/>
            加装/拆除操纵辅助装置
            <input type="checkbox" name="ywlx" value="审领登记证书"/>
            审领登记证书
            <input type="checkbox" name="ywlx" value="补领登记证书"/>
            补领登记证书
            <input type="checkbox" name="ywlx" value="监督解体"/>
            监督解体
            <input type="checkbox" name="ywlx" value="其他"/>
            其他 </td>
        </tr>
        <tr>
          <td width="40">类别</td>
          <td width="36">序号</td>
          <td width="153">查验项目</td>
          <td width="68">判定</td>
          <td width="74">类别</td>
          <td width="44">序号</td>
          <td width="199">查验项目</td>
          <td width="78">判定</td>
        </tr>
        <tr>
          <td rowspan="9"> 通&nbsp;过<br>
            <br>
            项&nbsp;目 </td>
          <td height="37">1</td>
          <td class="left">车辆识别代号</td>
          <td id="item1">&nbsp;</td>
          <td rowspan="4">大&nbsp;中&nbsp;型<br>
            客&nbsp;车&nbsp;、<br>
            危&nbsp;险&nbsp;化<br>
            学&nbsp;品&nbsp;运<br>
            输&nbsp;&nbsp;&nbsp;车</td>
          <td>14</td>
          <td class="left">灭火器</td>
          <td  id="item14">&nbsp;</td>
        </tr>
        <tr>
          <td height="37">2</td>
          <td class="left">发动机型号/号码</td>
          <td  id="item2">&nbsp;</td>
          <td>15</td>
          <td class="left">行驶记录装置、车内外<br>
            &nbsp;录像监控装置</td>
          <td  id="item15">&nbsp;</td>
        </tr>
        <tr>
          <td height="37">3</td>
          <td class="left">车辆品牌/型号</td>
          <td  id="item3">&nbsp;</td>
          <td>16</td>
          <td class="left">应急出口/应急锤、乘客门</td>
          <td  id="item16">&nbsp;</td>
        </tr>
        <tr>
          <td height="37">4</td>
          <td class="left">车身颜色</td>
          <td  id="item4">&nbsp;</td>
          <td>17</td>
          <td class="left">外部标志/文字、喷涂</td>
          <td  id="item17">&nbsp;</td>
        </tr>
        <tr>
          <td height="37">5</td>
          <td class="left">核定载人数</td>
          <td  id="item5">&nbsp;</td>
          <td rowspan="2">其&nbsp;他</td>
          <td>18</td>
          <td class="left">标志灯具、报警器</td>
          <td  id="item18">&nbsp;</td>
        </tr>
        <tr>
          <td height="37">6</td>
          <td class="left">车辆类型</td>
          <td  id="item6">&nbsp;</td>
          <td>19</td>
          <td class="left">安全技术检验合格证明</td>
          <td  id="item19">&nbsp;</td>
        </tr>
        <tr>
          <td height="37">7</td>
          <td class="left">号牌/车辆外观形状</td>
          <td  id="item7">&nbsp;</td>
          <td colspan="4" rowspan="3" class="left">查验结论：<br>
            <br>
            <br>
            <br></td>
        </tr>
        <tr>
          <td height="37">8</td>
          <td class="left">轮胎完好情况</td>
          <td  id="item8">&nbsp;</td>
        </tr>
        <tr>
          <td height="37">9</td>
          <td class="left">安全带、三角警告牌</td>
          <td  id="item9">&nbsp;</td>
        </tr>
        <tr>
          <td rowspan="4">货&nbsp;车<br>
            <br>
            挂&nbsp;车</td>
          <td height="37">10</td>
          <td class="left">外廓尺寸、轴数</td>
          <td  id="item10">&nbsp;</td>
          <td colspan="4" rowspan="2" class="left"> 查验员： <br>
            <br>
            <p align="right"> 年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp; </p></td>
        </tr>
        <tr>
          <td height="37">11</td>
          <td class="left">轮胎规格</td>
          <td  id="item11">&nbsp;</td>
        </tr>
        <tr>
          <td height="37">12</td>
          <td class="left">侧后部防护装置</td>
          <td  id="item12">&nbsp;</td>
          <td rowspan="2">复检合格</td>
          <td colspan="3" rowspan="2" class="left">查验员：<br>
            <br>
            <br>
            <p align="right"> 年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp; </p></td>
        </tr>
        <tr>
          <td height="37">13</td>
          <td class="left">车身反光标志和车辆<br>
            &nbsp;尾部标志板、喷涂</td>
          <td  id="item13">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="5"  height="200" name="leftanteriorphoto">机动车照片<br>
            (注册登记、转移登记、需要制作照片的变更登记 、转入、<br>
            监销) </td>
          <td colspan="3" class="left">备注：<br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br></td>
        </tr>
      
        <td colspan="8" height="70" style="font-size:12px" name="vinphoto">车辆识别代号(车架号)拓印膜<br>
          <br>
          （注册登记、转移登记、转出、转入、更换车身或者车架、更换整车、审领登记证书、重新打刻 VIN	 ） </td>
      </tr>
    </table>
   <p style="text-indent:2em;"> 说明：1、填表时应在对应的业务类型上划“√”</p> </div>
</div>
</body>
</html>
<script>
$(function(){
	
	
   $.ajax({
	 	   type: "post",
	 	   async: true,
	 	   url: '<%=request.getContextPath()%>/module/spotcheck/details',
	 	   data:{
	 	     "srln":'1001201703272140',
	 	    },
	 	   dataType:"text",
	 	   success: function (data) {
	 	    //将json字符串转为json对象
	 	    var jsondata=data;
	 	   
	 	    var list = $("[name='ywlx']");
			$("[name='carno']").html(jsondata.data[0].carNo);
			$("[name='cardkind']").html(jsondata.data[0].cardkind);
			
			  
			for(var i=0; i<list.length;i++){
				if(list[i].value==jsondata.data[0].serviceType){
					 $("[name='ywlx']:eq("+i+")").prop("checked",true); 
					}
				}
			for(var j=1;j<19;j++){
				if(jsondata.data[0]["item"+j]==1){
					$("#item"+j).html("√");
					}else{
						$("#item"+j).html("X");
						}
				}
			$("[name='leftanteriorphoto']").html(jsondata.data[0].leftanteriorphoto);	
				vinphoto
				$("[name='vinphoto']").html(jsondata.data[0].vinphoto);	
					
	 	   }
		  
	 });
	 
 
 
});
</script>