<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>PDA备案</title>
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
   <div id="p" class="easyui-panel" title="查询" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
     <td class="bgc">备案编号</td>
     <td><input id="fileId" class="easyui-textbox" style="width:80%;"/></td>
     <td class="bgc">查验区</td>
     <td> <select class="easyui-combobox" panelmaxheight="auto"  data-options="editable:false,url:'<%=request.getContextPath()%>/module/pda/loadChkpt',valueField:'organCode',textField:'organName'" id="serial" style="width:70%; height:30px;">
             </select></td>
     </tr>
     </table>
      <div style=" width:100%; overflow:hidden; text-align:right; background:#eff6fe; padding:2px;"  class="easyui-panel">
            <button  type="button" class="easyui-linkbutton"   data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
            <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-reload'"  onclick="reset();">重置</button>
     </div>
      </div>
      
    <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='<%=request.getContextPath()%>/module/pda/pdabasq'">备案申请</button>
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'"  onclick="findPdaFileById();">修改</button>
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="deletePdaFile();">删除</button>
     </div>
  
  <div id="p" class="easyui-panel" title="PDA备案一览表" style="width:100%;"> 
  <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0" id="tab" >
     <thead >
         <th>选择</th>
         <th>备案编号</th>
         <th>系统名称</th>
         <th>系统版本</th>
         <th>系统说明</th>
         <th>终端品牌型号</th>
         <th>操作系统</th>
         <th>验收时间</th>
         <th>查验区序号范围</th>
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
$('#serial').combobox({
    // 渲染下拉列表添加复选框
    formatter: function(row) {
        return '<label  >'+ row.organCode +': ' + row.organName + '</label>';
    }
   
});
// 分页
function page(pageSize,pageNumber){
	var fileId=removeAllSpace($("#fileId").textbox("getValue"));
	var serial=removeAllSpace($("#serial").combobox("getValue"));
	$.ajax({
		url:"<%=request.getContextPath()%>/module/pda/pageFindPdaFile",
		type:"post",
		data:{
			pageSize: pageSize,
			pageNumber:pageNumber,
			fileId: fileId,
			serial: serial
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
				$.each(list,function(index,pdaDeviceFifleEntity){
					var caTime = getTime(pdaDeviceFifleEntity.caTime.toString().substring(0, 10));
					if(index % 2 == 1){
						var tr="<tr class='rowbgcolor'>"
				         	+"<td class='texcenter'><input  type='radio' name='radio' value="+pdaDeviceFifleEntity.id+" /></td>"
				         	+"<td>"+pdaDeviceFifleEntity.fileId+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.sysName+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.verNo+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.sysExp+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.deviceUt+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.operSys+"</td>"
				         	+"<td>"+caTime+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.organName+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.fileStatu+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.localStatu+"</td>"
				         	+"</tr>";
					}else{
						var tr="<tr >"
							+"<td class='texcenter'><input  type='radio' name='radio' value="+pdaDeviceFifleEntity.id+" /></td>"
				         	+"<td>"+pdaDeviceFifleEntity.fileId+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.sysName+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.verNo+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.sysExp+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.deviceUt+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.operSys+"</td>"
				         	+"<td>"+caTime+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.organName+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.fileStatu+"</td>"
				         	+"<td>"+pdaDeviceFifleEntity.localStatu+"</td>"
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
function deletePdaFile(){
	id=$("[name=radio]:checked").val();
	if(validateEmpty(id, "请选择需要删除的备案记录！")){
		return ;
	}
	parent.$.messager.confirm('询问', '您是否删除此备案？',
	function(b) {
		if (b) {
		pageNumber=$("#pp").pagination('options').pageNumber;
		var fileId=removeAllSpace($("#fileId").textbox("getValue"));
		var serial=removeAllSpace($("#serial").combobox("getValue"));
		$.ajax({
			url:"<%=request.getContextPath()%>/module/pda/deletePdaFile",
			type:"post",
			data:{
				pageSize: pageSize,
				pageNumber: pageNumber,
				fileId: fileId,
				serial: serial,
				id: id
		
		
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
					$.each(list,function(index,pdaDeviceFifleEntity){
						var caTime = getTime(pdaDeviceFifleEntity.caTime.toString().substring(0, 10));
						if(index % 2 == 1){
							var tr="<tr class='rowbgcolor'>"
					         	+"<td class='texcenter'><input  type='radio' name='radio' value="+pdaDeviceFifleEntity.id+" /></td>"
					         	+"<td>"+pdaDeviceFifleEntity.fileId+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.sysName+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.verNo+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.sysExp+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.deviceUt+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.operSys+"</td>"
					         	+"<td>"+caTime+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.serial+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.fileStatu+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.localStatu+"</td>"
					         	+"</tr>";
						}else{
							var tr="<tr >"
								+"<td class='texcenter'><input  type='radio' name='radio' value="+pdaDeviceFifleEntity.id+" /></td>"
					         	+"<td>"+pdaDeviceFifleEntity.fileId+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.sysName+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.verNo+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.sysExp+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.deviceUt+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.operSys+"</td>"
					         	+"<td>"+caTime+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.serial+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.fileStatu+"</td>"
					         	+"<td>"+pdaDeviceFifleEntity.localStatu+"</td>"
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
function findPdaFileById(){
	id=$("[name=radio]:checked").val();
	if(validateEmpty(id, "请选择需要修改的备案记录！")){
		return ;
	}
	window.location.href = "<%=request.getContextPath()%>/module/pda/findPdaFileById?id="+id;
}


// 重置
function reset(){
	$("#fileId").textbox("setValue","");
	$("#serial").combobox("setValue","");
}


</script>

</html>