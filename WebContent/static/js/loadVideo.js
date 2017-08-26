
$(function () {
	var wWidth = window.screen.width;
	var t = document.documentElement.clientWidth;
	// 检查插件是否已经安装过
	if (-1 == WebVideoCtrl.I_CheckPluginInstall()) {
		alert("您还未安装过插件，双击开发包目录里的WebComponents.exe安装！");
		return;
	}
	
	// 初始化插件参数及插入插件
	WebVideoCtrl.I_InitPlugin(1000, 570, {
        iWndowType: 2,
		cbSelWnd: function (xmlDoc) {
			g_iWndIndex = $(xmlDoc).find("SelectWnd").eq(0).text();
			var szInfo = "当前选择的窗口编号：" + g_iWndIndex;
			showCBInfo(szInfo);
		}
	});
	WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
	login();
	changeWndNum(1);
});

function login() {
	   var szIP = $("#nvrIp").val(),
		szPort = $("#nvrPort").val(),
		szUsername = $("#nvrUser").val(),
		szPassword = $("#nvrPwd").val();
 /*   alert(szIP);
    alert(nvrPort);
    alert(nvrUser);
    alert(nvrPwd);*/
	if ("" == szIP || "" == szPort) {
		return;
	}

	var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
		success: function (xmlDoc) {
			//alert("登录成功！");
			WebVideoCtrl.I_Stop();
			getChannel();
			window.setTimeout(function(){
				startPreview();
			}, 300);
			
			
		},
		error: function () {
			//showOPInfo(szIP + " 登录失败！");
		}
	});
	var te = iRet;

	if (-1 == iRet) {
		//showOPInfo(szIP + " 已登录过！");
	}
}

function getChannel(){
	var szIP =  $("#nvrIp").val();
	// 模拟通道
	WebVideoCtrl.I_GetAnalogChannelInfo(szIP, {
		async: false,
		success: function (xmlDoc) {
			//alert("成功！");
			
		},
		error: function () {
			
		}
	});
}
// 窗口分割数
function changeWndNum(iType) {
	
	iType = parseInt(iType, 10);
	WebVideoCtrl.I_ChangeWndNum(iType);
}
function startPreview(){
	var szIP =  $("#nvrIp").val();
	var iStreamType=1;
	var iChannelID = $("#nvrChannel").val();
	//var iChannelID=1;
	var bZeroChannel=false;	
	var iWndIndex=0;
	
	var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
		iWndIndex:iWndIndex,
		iStreamType: iStreamType,
		iChannelID: iChannelID,
		bZeroChannel: bZeroChannel
	});
	//alert(iRet);
}

