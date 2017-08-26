<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>角色管理</title>
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
</head>
<body>
	<div id="p" class="easyui-panel" title="查询"
		style="width: 100%; margin-bottom: 5px;">
		<table width="100%" height="100%" align="center" cellspacing="0"
			cellpadding="0">
			<tr>
				<TD class="bgc"><span class="star">*</span>所属查验区</TD><TD >
            <select class="easyui-combobox" panelmaxheight="auto"  data-options="editable:false,url:'<%=request.getContextPath()%>/module/nvrsetting/loadChkpt',valueField:'organCode',textField:'organName'" id="organ" style="width:60%; height:30px;">
             </select></TD>
			</tr>
		</table>
		<div
			style="width: 100%; overflow: hidden; background: #eff6fe; padding: 2px; text-align: right;"
			class="easyui-panel">
			<button type="button" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'" onclick="search();">查询</button>
			<button type="button" class="easyui-linkbutton"
				data-options="iconCls:'icon-reload'" onclick="reset();">重置</button>
		</div>
	</div>

	<div
		style="width: 100%; overflow: hidden; background: #eff6fe; padding: 2px;"
		class="easyui-panel">
		<button type="button" class="easyui-linkbutton"
			data-options="iconCls:'icon-add'"
			onclick="window.location='<%=request.getContextPath()%>/module/nvrsetting/toaddchkptnvrconfig'">新增视频配置</button>
		<button type="button" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit'" onclick="findRoleById();">修改</button>
		<button type="button" class="easyui-linkbutton"
			data-options="iconCls:'icon-no'" onclick="deleteRole();">删除</button>
	</div>

	<div id="p" class="easyui-panel" title="角色管理一览表" style="width: 100%;">
		<table width="100%" height="100%" align="center" cellspacing="0"
			cellpadding="0" id="tab">
			<thead height="30">
				<th>选择</th>
				<th>查验区</th>
				<th>查验线名称</th>
				<th>ip地址</th>
				<th>http端口</th>
				<th>用户名</th>
				<th>用户密码</th>
				<th>设备通道号</th>
				<th>操作</th>
			</thead>
			<tbody id="tbody">
				
			</tbody>
		</table>
	</div>
<!-- 	<div style="width: 100%; background: #eff6fe;" class="easyui-panel">
		<div id="pp" class="easyui-pagination"
			data-options="
					total:100, 
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'第',
                    afterPageText:'页 共 {pages} 页',
                    onSelectPage:function(pageNumber){page(10,pageNumber)}"></div>
	</div> -->

</body>
<script type="text/javascript">
var pageSize = 10; // 页面大小
var pageNumber = 1; // 当前页码
$(function() {
    page(pageSize, pageNumber);
    
    
    
    
});

// 搜索
function search() {
    page();

}

// 分页
function page() {
	var organCode = removeAllSpace($("#organ").textbox("getValue"));
    $("#tbody").empty();
    $.ajax({
        url: "<%=request.getContextPath()%>/module/nvrsetting/findchkptnvrInfos",
        type: "post",
        data: {
        	organCode:organCode
        },
        success: function(result) {
        	if(result=="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
            var list = result;
            if (list != null && list.length > 0) {
                $.each(list,
                function(index, nvrConfigDto) {
                    if (index % 2 == 1) {
                        var tr = "<tr class='rowbgcolor'>"
                        		+ "<td class='texcenter'style='width:10%;'><input  type='radio' name='radio' value=" + nvrConfigDto.organCode+"-"+nvrConfigDto.checkLineId+" /></td>"
                        		+ "<td >" + nvrConfigDto.chkptName + "</td>"
                        		+ "<td >" + nvrConfigDto.checkLineName + "</td>"
                        		+ "<td >" + nvrConfigDto.nvrIp + "</td>"
                        		+ "<td >" + nvrConfigDto.nvrPort + "</td>"
                        		+ "<td >" + nvrConfigDto.nvrUser + "</td>"
                        		+ "<td >" + nvrConfigDto.nvrPwd + "</td>"
                        		+ "<td >" + nvrConfigDto.nvrChannel + "</td>"
                        		+ "<td ><button onclick='loadVideo(\""+ nvrConfigDto.organCode+"\","+nvrConfigDto.checkLineId+")'>视频预览</button></td>"
                        		+ "</tr>";
                    } else {
                        var tr = "<tr >"
		                        + "<td class='texcenter' style='width:10%;'><input  type='radio' name='radio' value="  + nvrConfigDto.organCode+"-"+nvrConfigDto.checkLineId+ " /></td>"
		                        + "<td >" + nvrConfigDto.chkptName + "</td>"
		                        + "<td >" + nvrConfigDto.checkLineName + "</td>"
                        		+ "<td >" + nvrConfigDto.nvrIp + "</td>"
                        		+ "<td >" + nvrConfigDto.nvrPort + "</td>"
                        		+ "<td >" + nvrConfigDto.nvrUser + "</td>"
                        		+ "<td >" + nvrConfigDto.nvrPwd + "</td>"
                        		+ "<td >" + nvrConfigDto.nvrChannel + "</td>"
                        		+ "<td ><button onclick='loadVideo(\""+ nvrConfigDto.organCode+"\","+nvrConfigDto.checkLineId+")'>视频预览</button></td>"
		                		+ "</tr>";
                    }
                    $("#tbody").append(tr);
                });
            } else {
                var tr = "<tr><td colspan='7' align='center'>数据库没用相应的数据!</td></tr>";
                $("#tbody").append(tr);
            }

        }

    });

}

// 删除
function deleteRole() {
    id = $("[name=radio]:checked").val();
    if (validateEmpty(id, "请选择需要删除的视频配置！")) {
        return;
    }
    var dd= id.split("-");
    parent.$.messager.confirm('询问', '您是否删除此视频配置？',
    function(b) {
        if (b) {
            $("#tbody").empty();
            $.ajax({
                url: "<%=request.getContextPath()%>/module/nvrsetting/deletechkptnvrconfig",
                type: "post",
                data: {
                	organCode: dd[0],
                	checkLineId: dd[1]
                },
                success: function(result) {
                	if(result=="no-login"){
    					top.location="<%=request.getContextPath() %>/login.jsp"
    	        	}
                    if(result=="success"){
                    	page();
                    }else{
                    	alert("系统异常");
                    }
                }
            });
        }
    });
}

// 修改
function findRoleById() {
    id = $("[name=radio]:checked").val();
    if (validateEmpty(id, "请选择需要修改的角色！")) {
        return;
    }
    var dd= id.split("-");
    window.location.href = "<%=request.getContextPath()%>/module/nvrsetting/toaddchkptnvrconfig?organCode=" + dd[0]+"&checkLineId="+dd[1];
}

// 重置
function reset() {
    $("#roleName").textbox("setValue", "");
}

function loadVideo(organCode,checkLineId){
	parent.loadVideo(organCode,checkLineId);
}





</script>
</html>