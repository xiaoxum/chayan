<%@ page language="java" contentType="text/html; charset=utf-8" import="com.vkl.ckts.common.entity.UserEntity,com.vkl.ckts.common.constr.Constrant"
    pageEncoding="utf-8"%>
   <%
	UserEntity user = (UserEntity)session.getAttribute(Constrant.S_USER_SESSION);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<title>用户管理</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css1/css.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDrag.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/zDialog.js"></script>
</head>

<body>
   <div id="p" class="easyui-panel" title="用户信息查询" style="width:100%; margin-bottom:5px;">
   <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0">
     <tr>
      <td class="bgc">用户角色</td><td><select class="easyui-combobox"  data-options="editable:false,url:'<%=request.getContextPath()%>/module/role/findRoleList',valueField:'id',textField:'roleName'" id="serial" style="width:80%; height:30px;">
             </select></TD>
      <td class="bgc">用户名称</td><td><input class="easyui-textbox" style="width:90%;" id="userName" value=""/></td>
     </tr>
     </table>
     <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px; text-align:right;"  class="easyui-panel">
              <button  type="button" class="easyui-linkbutton"   data-options="iconCls:'icon-search'"  onclick="search();">查询</button>
              <button  type="button" class="easyui-linkbutton"  data-options="iconCls:'icon-reload'"  onclick="reset();">重置</button>
       </div>
      </div>
      
    <div style=" width:100%; overflow:hidden; background:#eff6fe; padding:2px;"  class="easyui-panel">
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="window.location='<%=request.getContextPath()%>/userAdmin/uAddIndex'">新增用户</button>
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-edit'"  onclick="upd();">修改</button>
     <button  type="button"  class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="del();">删除</button>
     </div>
  
  <div id="p" class="easyui-panel" title="用户一览表" style="width:100%;"> 
  <table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0" id="tab">
     <thead height="30">
          <th style='width:3%;'>选择</th>
          <th style='width:7%;'>用户账号</th>
          <th style='width:10%;'>用户名称</th>
          <th style='width:4%;'>性别</th>
          <th style='width:6%;'>警员编号</th>
          <th style='width:8%;'>用户角色</th>
          <th style='width:15%;'>部  门</th>
          <th style='width:5%;'>用户状态</th>
          <th style='width:5%;'>备案人员</th>
       </thead>
     <tbody id="tbody">
     
     </tbody>
  </table>
</div>
<div style=" width:100%; background:#eff6fe;"  class="easyui-panel">
      <div id="pp"   class="easyui-pagination" data-options="
					total:0, 
					pageSize:15,
					 layout:['first','prev','links','next','last','manual'],
                    beforePageText:'Page',
                    onSelectPage:function(pageNumber){}"></div>
    </div>

</body>
<script type="text/javascript">


	// 条件查询
	function search(){
		page(1);
	}
	// 重置插叙条件
	function reset(){
		 $("#serial").combobox("setValues","");
			$("#userName").textbox("setValue","");
	}
	$(function(){
		page(1);
		if('${flag11}' != null){
			if('${flag11}' == 'del'){
				$.messager.alert("提示","用户删除成功！");
			}else if('${flag11}' == 'upd'){
				$.messager.alert("提示","用户修改成功！");
			}
				<%request.getSession().setAttribute("flag11", null);%>			
		}
	});
	// 分页
	function page(num){
		var role = $("#serial").combobox("getValues");
		var currentUser="<%=user.getId()%>";
		
		var userName = $("#userName").textbox("getValue");
		if(userName != ''){
			if (!checkRegExp(userName, "isChineseName", "","用户名格式不正确！")) {
		        return;
		    }
		}
		$.ajax({
			url : '<%=request.getContextPath()%>/userAdmin/page',
			type : 'post',
			data : {uRole : role.join(","), userName : userName, pageNo : num, pageSize : 15},
			success : function(data){
				if(data=="no-login"){
					top.location="<%=request.getContextPath() %>/login.jsp"
	        	}
				var total = data[0];
				data = data[1];
				var info;
				$("#tbody").empty();
				$.each(data,function(index,dt){
					if (currentUser == dt.id) {
						return true;
					}
					var isfilter=(dt.isFiler=='1')?"是":"否";
					if(index % 2 == 1){
						info = "<tr class='rowbgcolor'>"
								+"<td class='texcenter'><input type='radio' name='sel' value='"+dt.id+"'></td>"
								+"<td>"+dt.loginName+"</td><td style='width:20%;'>"+dt.userName+"</td>"
								+"<td>"+dt.userSex+"</td>"
								+"<td>"+dt.fuzz+"</td>"
								+"<td>"+dt.uRole+"</td>"
								+"<td>"+dt.deptName+"</td>"
								+"<td>"+dt.userStatu+"</td>"
								+"<td>"+isfilter+"</td>"
								+"</tr>";
					}else{
						info = "<tr>"
							+"<td class='texcenter'><input type='radio' name='sel' value='"+dt.id+"'></td>"
							+"<td>"+dt.loginName+"</td><td style='width:20%;'>"+dt.userName+"</td>"
							+"<td>"+dt.userSex+"</td>"
							+"<td>"+dt.fuzz+"</td>"
							+"<td>"+dt.uRole+"</td>"
							+"<td>"+dt.deptName+"</td>"
							+"<td>"+dt.userStatu+"</td>"
							+"<td>"+isfilter+"</td>"
							+"</tr>";
					}
					$("#tbody").append(info);
				})
				$("#pp").pagination({
					total : total-1,
				})
			}
		});
	}
		// 修改
		function upd(){
			var id = $("input[name='sel']:checked").val();
			if(id == null || id == ''){
				$.messager.alert("提示","请选择修改项！");
			}else{
				window.location.href="<%=request.getContextPath()%>/userAdmin/toUserUpd?id="+id;
			}
		}
		// 删除
		function del(){
			var id = $("input[name='sel']:checked").val();
			if(id == null || id == ''){
				$.messager.alert("提示","请选择删除项！");
			}else{
				parent.$.messager.confirm("询问","你确定要删除该用户",function(b){
					if(b){
						if("<%=user.getId()%>" == id){
							$.messager.alert("提示","无法删除自身用户！");
						}else{
							window.location.href="<%=request.getContextPath()%>/userAdmin/del?id="+id;					
						}
					}
				})
			}
		}
</script>
</html>
