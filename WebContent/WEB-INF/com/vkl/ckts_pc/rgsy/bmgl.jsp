<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>部门管理</title>
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
	<div id="p" class="easyui-panel" title="部门信息查询"
		style="width: 100%; margin-bottom: 5px;">
		<table width="100%" height="100%" align="center" cellspacing="0"
			cellpadding="0">
			<tr>
				<td class="bgc">部门类型</td>
				<td><select class="easyui-combobox" id="deptType"
					data-options="editable:false" style="width: 80%; height: 30px;">
						<option value="">--请选择--</option>
						<option value="1">市车管所</option>
						<option value="2">县/区车管所</option>
						<option value="3">查验点</option>
				</select></td>
				<td class="bgc">部门名称</td>
				<td><input id="deptName" class="easyui-textbox"
					style="width: 90%;" /></td>
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
			onclick="window.location='<%=request.getContextPath()%>/module/dept/bmadd'">新增部门</button>
		<button type="button" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit'" onclick="findDeptById();">修改</button>
		<button type="button" class="easyui-linkbutton"
			data-options="iconCls:'icon-no'" onclick="deleteDept();">删除</button>
	</div>

	<div id="p" class="easyui-panel" title="部门一览表" style="width: 100%;">
		<table width="100%" height="100%" align="center" cellspacing="0"
			cellpadding="0" id="tab">
			<thead>
				<th>选择</th>
				<th>部门类型</th>
				<th>部门名称</th>
				<th>上级部门</th>
				<th>行政区划</th>
				<th>联系地址</th>
				<th>联系电话</th>
			</thead>

			<tbody id="tbody">
			</tbody>
		</table>
	</div>
	<div style="width: 100%; background: #eff6fe;" class="easyui-panel">
		<div id="pp" class="easyui-pagination"
			data-options="
					total:100, 
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'第',
                    afterPageText:'页 共 {pages} 页',
                    onSelectPage:function(pageNumber){page(10,pageNumber);}"></div>
	</div>

</body>
<script type="text/javascript">
var pageSize = 10; // 页面大小
var pageNumber = 1; // 当前页码
$(function() {
    page(pageSize, pageNumber);
});

// 搜索
function search() {
    page(pageSize, pageNumber);

}

// 分页
function page(pageSize, pageNumber) {
    var deptType = removeAllSpace($("#deptType").combobox("getValue"));
    var deptName = removeAllSpace($("#deptName").textbox("getValue"));
    $.ajax({
        url: "<%=request.getContextPath()%>/module/dept/pageFindDept",
        type: "post",
        data: {
            pageSize: pageSize,
            pageNumber: pageNumber,
            deptType: deptType,
            deptName: deptName
        },
        success: function(result) {
        	if(result =="no-login"){
				top.location="<%=request.getContextPath() %>/login.jsp"
        	}
        	$("#tbody").empty();
            var list = result.list;
            $("#pp").pagination({
                total: result.total,
            });
            if (list != null && list.length > 0) {
                $.each(list,
                function(index, deptEntity) {
                    if (index % 2 == 1) {
                        var tr = "<tr class='rowbgcolor'>" + "<td class='texcenter'><input  type='radio' name='radio' value=" + deptEntity.id + " /></td>" + "<td>" + deptEntity.deptType + "</td>" + "<td>" + deptEntity.deptName + "</td>" + "<td>" + deptEntity.parentId + "</td>" + "<td>" + deptEntity.asts + "</td>" + "<td>" + deptEntity.deptAddr + "</td>" + "<td>" + deptEntity.deptPhone + "</td>" + "</tr>";
                    } else {
                        var tr = "<tr >" + "<td class='texcenter'><input  type='radio' name='radio' value=" + deptEntity.id + " /></td>" + "<td>" + deptEntity.deptType + "</td>" + "<td>" + deptEntity.deptName + "</td>" + "<td>" + deptEntity.parentId + "</td>" + "<td>" + deptEntity.asts + "</td>" + "<td>" + deptEntity.deptAddr + "</td>" + "<td>" + deptEntity.deptPhone + "</td>" + "</tr>";
                    }
                    $("#tbody").append(tr);
                });
            } else {
                var tr = "<tr><td colspan='7' align='center'>没有相应的部门信息!</td></tr>";
                $("#tbody").append(tr);
            }

        }

    });

}

// 删除
function deleteDept() {
    id = $("[name=radio]:checked").val();
    if (validateEmpty(id, "请选择需要删除的部门！")) {
        return;
    }
    parent.$.messager.confirm('确认', '您是否删除此部门？',
    function(b) {
        if (b) {
            pageNumber = $("#pp").pagination('options').pageNumber;
            var deptType = removeAllSpace($("#deptType").combobox("getValue"));
            var deptName = removeAllSpace($("#deptName").textbox("getValue"));
            $.ajax({
                url: "<%=request.getContextPath()%>/module/dept/deleteDept",
                type: "post",
                data: {
                    pageSize: pageSize,
                    pageNumber: pageNumber,
                    deptType: deptType,
                    deptName: deptName,
                    id: id
                },
                success: function(result) {
                	if(result =="no-login"){
    					top.location="<%=request.getContextPath() %>/login.jsp"
    	        	}
                	$("#tbody").empty();
                    var list = result.list;
                    $("#pp").pagination({
                        total: result.total,
                        pageNumber: pageNumber
                    });
                    if (list != null && list.length > 0) {
                        $.each(list,
                        function(index, deptEntity) {
                            if (index % 2 == 1) {
                                var tr = "<tr class='rowbgcolor'>" + "<td class='texcenter'><input  type='radio' name='radio' value=" + deptEntity.id + " /></td>" + "<td>" + deptEntity.deptType + "</td>" + "<td>" + deptEntity.deptName + "</td>" + "<td>" + deptEntity.parentId + "</td>" + "<td>" + deptEntity.asts + "</td>" + "<td>" + deptEntity.deptAddr + "</td>" + "<td>" + deptEntity.deptPhone + "</td>" + "</tr>";
                            } else {
                                var tr = "<tr >" + "<td class='texcenter'><input  type='radio' name='radio' value=" + deptEntity.id + " /></td>" + "<td>" + deptEntity.deptType + "</td>" + "<td>" + deptEntity.deptName + "</td>" + "<td>" + deptEntity.parentId + "</td>" + "<td>" + deptEntity.asts + "</td>" + "<td>" + deptEntity.deptAddr + "</td>" + "<td>" + deptEntity.deptPhone + "</td>" + "</tr>";
                            }
                            $("#tbody").append(tr);
                        });
                    } else {
                        var tr = "<tr><td colspan='7' align='center'>没有相应的部门信息!</td></tr>";
                        $("#tbody").append(tr);
                    }

                }

            });
        }
    });
}

// 修改
function findDeptById() {
    id = $("[name=radio]:checked").val();
    if (validateEmpty(id, "请选择需要修改的部门！")) {
        return;
    }
    window.location.href = "<%=request.getContextPath()%>/module/dept/findDeptById?id=" + id;
}

// 重置
function reset() {
    $("#deptType").combobox("setValue", "");
    $("#deptName").textbox("setValue", "");
}
</script>
</html>