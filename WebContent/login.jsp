<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查验监管系统</title>
<% 

     String loginName="";
     String loginPwd="";
     Cookie[] cookies=request.getCookies();
      if(cookies!=null){
    	  for(Cookie cookie:cookies){
  	    	String cookieKey=cookie.getName();
  	    	if("loginName".equals(cookieKey)){
  	    		String loginName1=cookie.getValue();
  	    		if(loginName1!=null){
  	    			loginName=loginName1;
  	    		}
  	    	}
            if("loginPwd".equals(cookieKey)){
            	String loginPwd1=cookie.getValue();
  	    		if(loginPwd1!=null){
  	    			loginPwd=loginPwd1;
  	    		}
  	    	}
  	      } 
      }
	   


%>
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/demo.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/logincss.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.placeholder.min.js"></script>
</head>
<body style="background: #287dc5; padding: 0;">




	<div class="loginbg">
		<div class="bgbox">
			<div class="loginlogo">
				<img src="<%=request.getContextPath() %>/static/images/new/loginlogo.jpg" />
			</div>
			<div class="loginbox">
				<table>
					<tr>
						<td><div class="text">
								<p style="text-indent: 2em;">
									基于查验智能终端的查验监管系统，实现查验员身份核对、机动车公告信息比对、查验过程图片随机抓拍、查验全程视频和执法记录仪音视频核查及抽查回放，实现对机动车查验过程信息、查验结果、查验项目照片以及查验记录表等资料照片信息实时采集、上传和远程复核，实现查验项目评判结果自动核对，最终实现对机动车查验过程、多方位实时监控，切实解决机动车查验不严格、不规范问题。</p>
							</div></td>
						<td><div class="line">
								<img src="<%=request.getContextPath() %>/static/images/new/loginline.png" />
							</div></td>
						<td>
							<div class="box">
								<div id="login" class="animate form">
										<p>
											<label for="loginName" class="uname"  data-icon="u">
												用户名 </label> 
												<span  id="errorMsg" style="color:red; display: none;">
												</span>
												<input id="loginName" class="username" name="loginName"
												required type="text" placeholder="请输入用户名称"  value='<%=loginName%>'/><span id="loginNameMessage" style="color:red;"></span>
										</p>
										<p>
											<label for="loginPwd" class="youpasswd" data-icon="p">
												密码 </label> <input id="loginPwd" class="password" name="loginPwd"
												required type="password" placeholder="请输入密码"  value="<%=loginPwd%>"/><span id="loginPwdMessage" style="color:red;" ></span>
										</p>
										<p class="login button">
											<input type="button" id="login" onclick="log()" value="登录" />
										</p>
                                           <div style="margin:20px auto;width:200px;"> 版本号:NM20170817 </div>
									</form>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="loginbottom">
				<img src="<%=request.getContextPath() %>/static/images/new/loginbottom.jpg" />
			</div>
		</div>
	</div>
</body>
<script>
$(function() {
	$('input[placeholder]').placeholder();
    //是否触发回车键
    document.onkeydown = function(e) {
        var ev = document.all ? window.event: e;
        if (ev.keyCode == 13) {
            log();
        }
    }
});
function log() {
	
    if ($("#loginName").val() == null || $("#loginName").val() == "") {
        $("#loginNameMessage").html("请输入用户名");
        return false;
    } else {
        $("#loginNameMessage").html("");
    }
    
    if ($("#loginPwd").val() == null || $("#loginPwd").val() == "" || $("#loginPwd").val() == "请输入密码") {
        $("#loginPwdMessage").html("请输入密码");
        return false;
    } else {
        $("#loginPwdMessage").html("");
    }
    // 设置按钮不可点击
    $("#login").attr("disabled",true);
    
    $.ajax({
        url: "<%=request.getContextPath() %>/module/login/login",
        type: "post",
        dataType: "json",
        context: document.body,
        data: {
            loginName: $("#loginName").val(),
            loginPwd: $("#loginPwd").val()
        },
        success: function(data) {
            // 设置按钮可点击
            $("#login").removeAttr("disabled");
            if (data.errorMsg == "登录成功") {
                window.location.href = "<%=request.getContextPath() %>/module/base/home"
            }else if (data.errorMsg == "在线") {
                $.messager.confirm("确认", '该用户以在线是否强制登录？',
                function(r) {
                    if (r) {
                        window.location.href = "<%=request.getContextPath() %>/module/base/home"
                    }
                });
            }else{
            	$("#errorMsg").html(data.errorMsg);
            	$("#errorMsg").show();
            }
        }
    });
}
</script>
</html>