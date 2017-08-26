<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>查验区备案</title>
<!-- 引入easyui框架样式表 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/easyui.css">
<!-- 引入图标样式表 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/icon.css">
<!-- 引入自定义样式表 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/css.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/css1/demo.css">
<!-- 引入jQuery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<!-- 扩展Jquery -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/extJquery.js" charset="utf-8"></script>
<!-- 引入EasyUI -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.easyui.min.js"></script>
<!-- 引入EasyUI中文脚本 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/easyui-lang-zh_CN.js"></script>
<!-- 自定义数据验证js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/custom-validate.js"></script>
</head>

<body>
   <div id="p" class="easyui-panel" title="查验区查询" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
     <td class="bgc">查验区序号</td>
     <td><input class="easyui-textbox" id="organCode" style="width:80%;"/></td>
      <td class="bgc">查验区名称</td><td><input id="organName" class="easyui-textbox" style="width:80%;"/></td>
     </tr>
     </table>
     <div style=" width:100%; background:#eff6fe; text-align:right;  padding:2px;"  class="easyui-panel">
              <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-redo'"  onclick="reset();">重置</button></div>
      </div>
      
    <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='<%=request.getContextPath()%>/module/chkpt/cyqbasq'">备案申请</button>
     <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-edit'"  onclick="findChkptFileById();">修改</button>
     <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="deleteChkptFile();">删除</button>
     </div>
  
  <div id="p" class="easyui-panel" title="查验区备案一览表" style="width:100%;"> 
  <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0" id="tab">
     <thead >
         <th>选择</th>
        <!--  <th>查验区</br>序号</th> -->
         <th>查验区名称</th>
         <th>查验区类别</th>
         <th>联系电话</th>
        <!--  <th>可查验</br>使用性质</th> -->
         <th>是否启用</br>电子围栏</th>
         <!-- <th>电子围栏</br>中心位置</th> -->
         <th>电子围栏</br>半径（米）</th>
         <th>查验区</br>所属部门</th>
         <th>查验区</br>负责人</th>
         <th>备案状态</th>
         <th>本地使用状态</th>
     </thead>
     
     <tbody id="tbody">
     </tbody>
  </table>
</div>
<div style=" width:100%; background:#eff6fe; "  class="easyui-panel">
      <div id="pp"   class="easyui-pagination" data-options="
					total:100, 
					layout:['first','prev','links','next','last','manual'],
                    beforePageText:'第',
                    afterPageText:'页 共 {pages} 页',
                    onSelectPage:function(pageNumber){page(10,pageNumber);}"></div>
    </div>
<script type="text/javascript">
var pageSize = 10; 	// 页面大小
var pageNumber = 1;	// 当前页码

$(function(){
	page(pageSize,pageNumber);
});

// 搜索
function search(){
	page(pageSize,pageNumber);
}

// 分页
function page(pageSize,pageNumber){
	var organCode=removeAllSpace($("#organCode").textbox("getValue"));
	var organName=removeAllSpace($("#organName").textbox("getValue"));
	$.ajax({
		url:"<%=request.getContextPath()%>/module/chkpt/pageFindChkptFile",
		type:"post",
		data:{
			pageSize:pageSize,
			pageNumber:pageNumber,
			organCode:organCode,
			organName:organName
		},
		success:function(result){
			if(result == "no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
			$("#tbody").empty();
			var list=result.list;
			$("#pp").pagination({
				  total:result.total
			});
			if(list!=null && list.length>0){
				$.each(list,function(index,chkptFileEntity){
					if(index % 2 == 1){
						var tr="<tr class='rowbgcolor'>"
				         	+"<td class='texcenter' style='width:4%;'><input  type='radio' name='radio' value="+chkptFileEntity.organCode+" /></td>"
				         	/* +"<td  style='width:15%;'>"+chkptFileEntity.organCode+"</td>" */
				         	+"<td   style='width:15%;'>"+chkptFileEntity.organName+"</td>"
				         	+"<td  style='width:10%;'>"+chkptFileEntity.organType+"</td>"
				         	+"<td style='width:8%;'>"+chkptFileEntity.pripPhone+"</td>"
				         /* 	+"<td>"+chkptFileEntity.syxzs+"</td>" */
				         	+"<td style='width:6%;'>"+chkptFileEntity.userDz+"</td>"
				         	/* +"<td>"+chkptFileEntity.ckCentLg+"&nbsp;/&nbsp;"+chkptFileEntity.ckCentLt+"</td>" */
				         	+"<td class='texright' style='width:6%;'>"+chkptFileEntity.ckCentR+"</td>"
				         	+"<td style='width:15%;'>"+chkptFileEntity.parentDeptName+"</td>"
				         	+"<td  style='width:6%;'>"+chkptFileEntity.pripName+"</td>"
				         	+"<td  style='width:6%;'>"+chkptFileEntity.fileStatu+"</td>"
				         	+"<td  style='width:6%;'>"+chkptFileEntity.localStatu+"</td>"
				         	+"</tr>";
					}else{
						var tr="<tr >"
				         	+"<td class='texcenter'><input  type='radio' name='radio' value="+chkptFileEntity.organCode+" /></td>"
				         /* 	+"<td >"+chkptFileEntity.organCode+"</td>" */
				         	+"<td>"+chkptFileEntity.organName+"</td>"
				         	+"<td>"+chkptFileEntity.organType+"</td>"
				         	+"<td>"+chkptFileEntity.pripPhone+"</td>"
				         /* 	+"<td>"+chkptFileEntity.syxzs+"</td>" */
				         	+"<td>"+chkptFileEntity.userDz+"</td>"
				         /* 	+"<td>"+chkptFileEntity.ckCentLg+"&nbsp;/&nbsp;"+chkptFileEntity.ckCentLt+"</td>" */
				         	+"<td class='texright'>"+chkptFileEntity.ckCentR+"</td>"
				         	+"<td>"+chkptFileEntity.parentDeptName+"</td>"
				         	+"<td>"+chkptFileEntity.pripName+"</td>"
				         	+"<td>"+chkptFileEntity.fileStatu+"</td>"
				         	+"<td>"+chkptFileEntity.localStatu+"</td>"
				         	+"</tr>";
					}
					

					$("#tbody").append(tr);
				});
			}else{
				var tr="<tr><td colspan='13' align='center'>数据库没用相应的数据!</td></tr>";
				$("#tbody").append(tr);
			}
			
		}
		
	})
	
}

// 删除
function deleteChkptFile(){
	var organCode=$("input[name='radio']:checked").val();
	if(validateEmpty(organCode, "请选择需要删除的备案记录！")){
		return ;
	}
	parent.$.messager.confirm('询问', '您是否删除此备案？',
	function(b) {
		if (b) {
	pageNumber=$("#pp").pagination('options').pageNumber;
	var searchOrganCode=removeAllSpace($("#organCode").textbox("getValue"));
	var organName=removeAllSpace($("#organName").textbox("getValue"));
	$.ajax({
		url:"<%=request.getContextPath()%>/module/chkpt/deleteChkptFile",
		type:"post",
		data:{
			pageSize: pageSize,
			pageNumber: pageNumber,
			organCode: organCode,
			organName: organName,
			searchOrganCode: searchOrganCode
		},
		success:function(result){
			if(result =="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
			$("#tbody").empty();
			var list=result.list;
			$("#pp").pagination({
				  total:result.total,
				  pageNumber:pageNumber
			});
			if(list!=null && list.length>0){
				$.each(list,function(index,chkptFileEntity){
					if(index % 2 == 1){
					/* 	var tr="<tr class='rowbgcolor'>"
				         	+"<td class='texcenter'><input  type='radio' name='radio' value="+chkptFileEntity.organCode+"  /></td>" */
				         	/* +"<td >"+chkptFileEntity.organCode+"</td>" */
				         /* 	+"<td>"+chkptFileEntity.organName+"</td>"
				         	+"<td>"+chkptFileEntity.organType+"</td>"
				         	+"<td>"+chkptFileEntity.pripPhone+"</td>" */
				         	/* +"<td>"+chkptFileEntity.syxzs+"</td>" */
				         	/* +"<td>"+chkptFileEntity.userDz+"</td>" */
				         	/* +"<td>"+chkptFileEntity.ckCentLg+"&nbsp;/&nbsp;"+chkptFileEntity.ckCentLt+"</td>" */
				         	/* +"<td class='texright'>"+chkptFileEntity.ckCentR+"</td>"
				         	+"<td>"+chkptFileEntity.parentDeptName+"</td>"
				         	+"<td>"+chkptFileEntity.pripName+"</td>"
				         	+"<td>"+chkptFileEntity.fileStatu+"</td>"
				         	+"<td>"+chkptFileEntity.localStatu+"</td>"
				         	+"</tr>"; */
				         	
						var tr="<tr class='rowbgcolor'>"
				         	+"<td class='texcenter' style='width:4%;'><input  type='radio' name='radio' value="+chkptFileEntity.organCode+" /></td>"
				         	/* +"<td  style='width:15%;'>"+chkptFileEntity.organCode+"</td>" */
				         	+"<td   style='width:15%;'>"+chkptFileEntity.organName+"</td>"
				         	+"<td  style='width:10%;'>"+chkptFileEntity.organType+"</td>"
				         	+"<td style='width:8%;'>"+chkptFileEntity.pripPhone+"</td>"
				         /* 	+"<td>"+chkptFileEntity.syxzs+"</td>" */
				         	+"<td style='width:6%;'>"+chkptFileEntity.userDz+"</td>"
				         	/* +"<td>"+chkptFileEntity.ckCentLg+"&nbsp;/&nbsp;"+chkptFileEntity.ckCentLt+"</td>" */
				         	+"<td class='texright' style='width:6%;'>"+chkptFileEntity.ckCentR+"</td>"
				         	+"<td style='width:15%;'>"+chkptFileEntity.parentDeptName+"</td>"
				         	+"<td  style='width:6%;'>"+chkptFileEntity.pripName+"</td>"
				         	+"<td  style='width:6%;'>"+chkptFileEntity.fileStatu+"</td>"
				         	+"<td  style='width:6%;'>"+chkptFileEntity.localStatu+"</td>"
				         	+"</tr>";
				         	
				         	
				         	
					}else{
						var tr="<tr >"
				         	+"<td class='texcenter'><input  type='radio' name='radio' value="+chkptFileEntity.organCode+" /></td>"
				         	/* +"<td >"+chkptFileEntity.organCode+"</td>" */
				         	+"<td>"+chkptFileEntity.organName+"</td>"
				         	+"<td>"+chkptFileEntity.organType+"</td>"
				         	+"<td>"+chkptFileEntity.pripPhone+"</td>"
				         	/* +"<td>"+chkptFileEntity.syxzs+"</td>" */
				         	+"<td>"+chkptFileEntity.userDz+"</td>"
				         	/* +"<td>"+chkptFileEntity.ckCentLg+"&nbsp;/&nbsp;"+chkptFileEntity.ckCentLt+"</td>" */
				         	+"<td class='texright'>"+chkptFileEntity.ckCentR+"</td>"
				         	+"<td>"+chkptFileEntity.parentDeptName+"</td>"
				         	+"<td>"+chkptFileEntity.pripName+"</td>"
				         	+"<td>"+chkptFileEntity.fileStatu+"</td>"
				         	+"<td>"+chkptFileEntity.localStatu+"</td>"
				         	+"</tr>";
					}
					$("#tbody").append(tr);
				});
			}else{
				var tr="<tr><td colspan='13' align='center'>数据库没用相应的数据!</td></tr>";
				$("#tbody").append(tr);
			}
			
		}
		
	});
		}})
}

// 修改
function findChkptFileById(){
	var organCode=$("[name=radio]:checked").val();
	if(validateEmpty(organCode, "请选择需要删除的备案记录！")){
		return ;
	}
	window.location.href = "<%=request.getContextPath()%>/module/chkpt/findChkptFileById?organCode="+organCode;
}


// 重置
function reset(){
	$("#organCode").textbox("setValue","");
	$("#organName").textbox("setValue","");
}
</script>

</body>


</html>
