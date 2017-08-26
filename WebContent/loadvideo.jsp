<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>

</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/jquery-1.4.1.min.js"></script>
   <script src="<%=request.getContextPath()%>/static/js/Play.js"></script>
    <style type="text/css">
        body
        {
            padding: 0px;
            margin: 0px;
            background-color: #071730;
            overflow: hidden;
            text-align: center;
        }


        #divright
        {
            float: left;
            background-color: #071730;
            overflow: hidden;
            padding: 0px;
            margin: 0px auto;
            height: 100%;
            
        }

        #divyj
        {
            background-color: #cbcbcb;
            height: 59px;
            margin: 10px;
        }
       
        #video
        {
            margin: 10px;
        }
       
       
       
       
    </style>
</head>
<body >
        <div  id="divright"  >
	            <div id="video" style="text-align: center;width:950px;margin-left:30px;" >
	                <object classid="clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921" id="vlcplayer" events="True"
	                    style="width:950px;height:540px; ">
	                    <param name="MRL" value="" />
	                    <param name="ShowDisplay" value="True" />
	                    <param name="AutoLoop" value="False" />	
	                    <param name="AutoPlay" value="False" />
	                    <param name="Volume" value="50" />
	                    <param name="toolbar" value="true" />
	                    <param name="StartTime" value="0" />
	                </object>
	            </div>
            	<div id="divyj" >
                    <div  style="text-align: center; width:950px;margin-left:30px;">
                        <input type="button" value="快进"  style="font-size: 20px; font-weight:bolder; color:Blue"  onclick="doPlayFaster()" />
                        <input type="button" value="||"  style="font-size: 20px; width:60px; font-weight:bolder; color:Blue"  onclick="doPlayOrPause()"/>
                        <input type="button" value="慢放"  style="font-size: 20px; font-weight:bolder; color:Blue" onclick="doPlaySlower()"/>
                        <label id="speed" style="font-size: 20px;font-weight:bolder;" ></label>
                        <label id="Label1" style="font-size: 20px;font-weight:bolder; color:Blue" >倍</label>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/download/vlc_2.2.4.0.exe" >播放插件下载</a>
                    </div>
                </div>
         </div>
</body>
<script type="text/javascript">
function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]); return null; 
	} 
 $(function(){
	 
	 PlayVideo(getQueryString("url"),"1");
	 
 }); 
 
 

</script>
</html>