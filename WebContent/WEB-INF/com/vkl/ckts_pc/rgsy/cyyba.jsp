<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>查验员备案</title>
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
  <div id="p" class="easyui-panel" title="查验员查询" style="width: 100%; margin-bottom: 5px;"> 
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0"> 
     <tr> 
      <td class="bgc">查验员姓名</td> 
      <td><input class="easyui-textbox" id="viewName" style="width: 80%;" /></td> 
      <td class="bgc">查验资格证编号</td> 
      <td><input class="easyui-textbox" id="validId" style="width: 80%;" /></td> 
      <td class="bgc">所属查验区</td> 
      <td><select class="easyui-combobox" panelmaxheight="auto"  data-options="editable:false,url:'<%=request.getContextPath()%>/module/pda/loadChkpt',valueField:'organCode',textField:'organName'" id="organ" style="width:80%;">
             </select></td> 
      </table> 
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
              <button type="button" data-options="iconCls:'icon-search'" onclick="search();">查询</button> <button type="button" data-options="iconCls:'icon-search'" onclick="reset();">重置</button>
       </div>
  </div> 
  <div style="width: 100%; overflow: hidden; background: #eff6fe; padding: 2px;" class="easyui-panel"> 
   <button type="button" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="window.location='<%=request.getContextPath()%>/module/record/cyybasq'">备案申请</button> 
   <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-edit'"  onclick="updateViewerFile();">修改</button>
   <button  type="button" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="deleteViewerFile();">删除</button>
  </div> 
<div id="p" class="easyui-panel" title="查验员备案一览表" style="width:100%;"> 
  <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0" id="tab">
     <thead >
         <th>选择</th>
         <th>资格证编号</th>
         <th>查验员姓名</th>
         <th>身份证号码</th>
         <th>资格证发放单位</th>
         <th>权限标识</th>
         <th>查验员等级</th>
         <th>所属查验区</th>
         <th>是否民警</th>
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
</body>
<script type = "text/javascript">
var pageSize = 10; 	// 页面大小
var pageNumber = 1;	// 当前页码
var viewName;	// 查验员姓名
var validId; 	// 资格证编号
var organ; 		// 所属查验区
$(function(){
	page(pageSize,pageNumber);
});

// 搜索
function search(){
	page(pageSize,pageNumber);
}
$('#organ').combobox({
    // 渲染下拉列表添加复选框
    formatter: function(row) {
        return '<label  >'+ row.organCode +': ' + row.organName + '</label>';
    }
   
});
// 分页
function page(pageSize,pageNumber){
    // 分别获取查验员姓名、资格证编号、所属查验区进行搜索
    viewName = removeAllSpace($("#viewName").textbox("getValue"));
    validId = removeAllSpace($("#validId").textbox("getValue"));
    organ = removeAllSpace($("#organ").textbox("getValue"));
	$.ajax({
		url:"<%=request.getContextPath()%>/module/record/pageFindViewerFile",
		type:"post",
		data:{
			 pageNumber: pageNumber,
	         pageSize: pageSize,
	         viewName: viewName,
	         validId: validId,
	         organ: organ
		},
		success:function(result){
			if(result=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
			$("#tbody").empty();
			var list=result.list;
			$("#pp").pagination({
				  total:result.total,
			});
			if(list!=null && list.length>0){
				$.each(list,function(index,viewerFileEntity){
					var validEndTime = getTime(viewerFileEntity.validEndTime.toString().substring(0, 10));
					var permissionFlag=viewerFileEntity.permissionFlag;
					var permissName=(permissionFlag=='1')?"公告比对":(permissionFlag=='2')?"拍照":(permissionFlag=='3')?"全项":"";
					if(index % 2 == 1){
						var tr="<tr class='rowbgcolor'>"
				         	+"<td class='texcenter'><input  type='radio' name='radio' value="+viewerFileEntity.id+" /></td>"
				         	+"<td >"+viewerFileEntity.validId+"</td>"
				         	+"<td>"+viewerFileEntity.viewName+"</td>"
				         	+"<td>"+viewerFileEntity.identity+"</td>"
				         	+"<td>"+viewerFileEntity.validIntrName+"</td>"
				         	+"<td>"+permissName+"</td>"
				         	+"<td>"+viewerFileEntity.viewerRank+"</td>"
				         	+"<td>"+viewerFileEntity.organ+"</td>"
				         	+"<td>"+viewerFileEntity.isPolice+"</td>"
				         	+"<td>"+viewerFileEntity.fileStatu+"</td>"
				         	+"<td>"+viewerFileEntity.localStatu+"</td>"
				         	+"</tr>";
					}else{
						var tr="<tr>"
				         	+"<td class='texcenter'><input  type='radio' name='radio' value="+viewerFileEntity.id+" /></td>"
				         	+"<td >"+viewerFileEntity.validId+"</td>"
				         	+"<td>"+viewerFileEntity.viewName+"</td>"
				         	+"<td>"+viewerFileEntity.identity+"</td>"
				         	+"<td>"+viewerFileEntity.validIntrName+"</td>"
				         	+"<td>"+permissName+"</td>"
				         	+"<td>"+viewerFileEntity.viewerRank+"</td>"
				         	+"<td>"+viewerFileEntity.organ+"</td>"
				         	+"<td>"+viewerFileEntity.isPolice+"</td>"
				         	+"<td>"+viewerFileEntity.fileStatu+"</td>"
				         	+"<td>"+viewerFileEntity.localStatu+"</td>"
				         	+"</tr>";
					}
					$("#tbody").append(tr);
				});
			}else{
				var tr="<tr><td colspan='11' align='center'>数据库没用相应的数据!</td></tr>";
				$("#tbody").append(tr);
			}
		}
	});
}

// 删除
function deleteViewerFile(){
	id=$("[name=radio]:checked").val();
	if(validateEmpty(id, "请选择需要删除的备案记录！")){
		return ;
	}
	parent.$.messager.confirm('询问', '您是否删除此备案？',
	function(b) {
		if (b) {
		pageNumber=$("#pp").pagination('options').pageNumber;
	    // 分别获取查验员姓名、资格证编号、所属查验区进行搜索
    	viewName = removeAllSpace($("#viewName").textbox("getValue"));
    	validId = removeAllSpace($("#validId").textbox("getValue"));
    	organ = removeAllSpace($("#organ").textbox("getValue"));
		$.ajax({
			url:"<%=request.getContextPath()%>/module/record/deleteViewerFile",
			type:"post",
			data:{
		           id: id,
	               pageNumber: pageNumber,
	               pageSize: pageSize,
	               viewName: viewName,
	               validId: validId,
	               organ: organ
			},
			success:function(result){
				if(result=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				$("#tbody").empty();
				var list=result.list;
				$("#pp").pagination({
					  total:result.total,
					  pageNumber:pageNumber
				});
				if(list!=null && list.length>0){
					$.each(list,function(index,viewerFileEntity){
						var permissionFlag=viewerFileEntity.permissionFlag;
						var permissName=(permissionFlag=='1')?"公告比对":(permissionFlag=='2')?"拍照":(permissionFlag=='3')?"全项":"";
						var validEndTime = getTime(viewerFileEntity.validEndTime.toString().substring(0, 10));
						if(index % 2 == 1){
							var tr="<tr class='rowbgcolor'>"
					         	+"<td class='texcenter'><input  type='radio' name='radio' value="+viewerFileEntity.id+" /></td>"
					         	+"<td >"+viewerFileEntity.validId+"</td>"
					         	+"<td>"+viewerFileEntity.viewName+"</td>"
					         	+"<td>"+viewerFileEntity.identity+"</td>"
					         	+"<td>"+viewerFileEntity.validIntrName+"</td>"
					         	+"<td>"+permissName+"</td>"
					         	+"<td>"+viewerFileEntity.viewerRank+"</td>"
					         	+"<td>"+viewerFileEntity.organ+"</td>"
					         	+"<td>"+viewerFileEntity.isPolice+"</td>"
					         	+"<td>"+viewerFileEntity.fileStatu+"</td>"
					         	+"<td>"+viewerFileEntity.localStatu+"</td>"
					         	+"</tr>";
						}else{
							var tr="<tr>"
					         	+"<td class='texcenter'><input  type='radio' name='radio' value="+viewerFileEntity.id+" /></td>"
					         	+"<td >"+viewerFileEntity.validId+"</td>"
					         	+"<td>"+viewerFileEntity.viewName+"</td>"
					         	+"<td>"+viewerFileEntity.identity+"</td>"
					         	+"<td>"+viewerFileEntity.validIntrName+"</td>"
					         	+"<td>"+permissName+"</td>"
					         	+"<td>"+viewerFileEntity.viewerRank+"</td>"
					         	+"<td>"+viewerFileEntity.organ+"</td>"
					         	+"<td>"+viewerFileEntity.isPolice+"</td>"
					         	+"<td>"+viewerFileEntity.fileStatu+"</td>"
					         	+"<td>"+viewerFileEntity.localStatu+"</td>"
					         	+"</tr>";
						}
						$("#tbody").append(tr);
					});
				}else{
					var tr="<tr><td colspan='11' align='center'>数据库没用相应的数据!</td></tr>";
					$("#tbody").append(tr);
				}
				
			}
			
		});
        }
    });
}

// 修改
function updateViewerFile(){
	id=$("[name=radio]:checked").val();
	if(validateEmpty(id, "请选择需要修改的备案记录！")){
		return ;
	}
	window.location.href = "<%=request.getContextPath()%>/module/record/findViewerFileById?id=" + id;
}


// 重置
function reset() {
    $("#viewName").textbox("setValue", "");
    $("#validId").textbox("setValue", "");
    $("#organ").combobox("setValue", "");
}

</script>
</html>
