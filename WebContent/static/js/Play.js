function handleMediaPlayerMediaChanged() {
	//document.getElementById("info").innerHTML = "Media Changed";
}

function handle_MediaPlayerNothingSpecial() {
	//document.getElementById("state").innerHTML = "Idle...";
}

function handle_MediaPlayerOpening() {
	//onOpen();
}

function handle_MediaPlayerBuffering(val) {
	//document.getElementById("info").innerHTML = val + "%";
}

function handle_MediaPlayerPlaying() {
	//onPlay();
}

function handle_MediaPlayerPaused() {
	//onPause();
}

function handle_MediaPlayerStopped() {
	//onStop();
}

function handle_MediaPlayerForward() {
	//document.getElementById("state").innerHTML = "Forward...";
}

function handle_MediaPlayerBackward() {
	//document.getElementById("state").innerHTML = "Backward...";
}

function handle_MediaPlayerEndReached() {
	//onEnd();
}

function handle_MediaPlayerEncounteredError() {
	//onError();
}

function handle_MediaPlayerTimeChanged(time) {
	//    var vlc = getVLC("vlc");
	//    var info = document.getElementById("info");
	//    if (vlc) {
	//        var mediaLen = vlc.input.length;
	//        if (mediaLen > 0) {
	//            // seekable media
	//            info.innerHTML = formatTime(time) + "/" + formatTime(mediaLen);
	//        }
	//    }
}

function handle_MediaPlayerPositionChanged(val) {
	// set javascript slider to correct position
}

function handle_MediaPlayerSeekableChanged(val) {
	//setSeekable(val);
}

function handle_MediaPlayerPausableChanged(val) {
	//setPauseable(val);
}

function handle_MediaPlayerTitleChanged(val) {
	//setTitle(val);
	//document.getElementById("info").innerHTML = "Title: " + val;
}

function handle_MediaPlayerLengthChanged(val) {
	//setMediaLength(val);
}

function autoHeightWidth() {
	$("#divright").width($(window).width() - 260);
	$(".layout1").height($(window).height());
	$("#video").height($(window).height() - 100);
	$("#video").width($(window).width() - 280);
	alert("D")
	$("#vlcplayer").height($(document.body).height() - 100);
	$("#vlcplayer").width($(document.body).width() - 280); //
}

// 单独播放使用
function PlayVideo(url, index) {
	
/*	
	$("#vlcplayer").height($(window).height() - 100);
	$("#vlcplayer").width($(window).width() - 280); //
*/
	getVLC("vlcplayer").style.display = "block";
	
	var vlc =  getVLC("vlcplayer"); 
	vlc.playlist.items.clear();
	//var options2 = new Array(":rtsp-tcp", ":effect-height=500px");

	var options =new Array("rtsp-tcp"); //[ ":rtsp-tcp" ];
	var itemId = vlc.playlist.add(url, "fancy name", options);
	//options = [];
	
	if (itemId != -1) {
		// play MRL
		vlc.playlist.playItem(itemId);
	} else {
		alert("播放失败");
	}
}

function getVLC(name) {
	if (window.document[name] != null) {
		return window.document[name];
	}
	if (navigator.appName.indexOf("Microsoft Internet") == -1) {
		if (document.embeds && document.embeds[name])
			return document.embeds[name];
	} else // if (navigator.appName.indexOf("Microsoft Internet")!=-1)
	{
		return document.getElementById(name);
	}
}

function init() {

	if (navigator.appName.indexOf("Microsoft Internet") == -1) {
		onVLCPluginReady()
	} else if (document.readyState == 'complete') {
		onVLCPluginReady();
	} else {
		/* Explorer loads plugins asynchronously */
		document.onreadystatechange = function() {
			if (document.readyState == 'complete') {
				onVLCPluginReady();
			}
		}
	}

}

function onVLCPluginReady() {
	registerVLCEvent("MediaPlayerMediaChanged", handleMediaPlayerMediaChanged);
	registerVLCEvent("MediaPlayerNothingSpecial",
			handle_MediaPlayerNothingSpecial);
	registerVLCEvent("MediaPlayerOpening", handle_MediaPlayerOpening);
	registerVLCEvent("MediaPlayerBuffering", handle_MediaPlayerBuffering);
	registerVLCEvent("MediaPlayerPlaying", handle_MediaPlayerPlaying);
	registerVLCEvent("MediaPlayerPaused", handle_MediaPlayerPaused);
	registerVLCEvent("MediaPlayerStopped", handle_MediaPlayerStopped);
	registerVLCEvent("MediaPlayerForward", handle_MediaPlayerForward);
	registerVLCEvent("MediaPlayerBackward", handle_MediaPlayerBackward);
	registerVLCEvent("MediaPlayerEndReached", handle_MediaPlayerEndReached);
	registerVLCEvent("MediaPlayerEncounteredError",
			handle_MediaPlayerEncounteredError);
	registerVLCEvent("MediaPlayerTimeChanged", handle_MediaPlayerTimeChanged);
	registerVLCEvent("MediaPlayerPositionChanged",
			handle_MediaPlayerPositionChanged);
	registerVLCEvent("MediaPlayerSeekableChanged",
			handle_MediaPlayerSeekableChanged);
	registerVLCEvent("MediaPlayerPausableChanged",
			handle_MediaPlayerPausableChanged);
	registerVLCEvent("MediaPlayerTitleChanged", handle_MediaPlayerTitleChanged);
	registerVLCEvent("MediaPlayerLengthChanged",
			handle_MediaPlayerLengthChanged);

}

function closePlayer() {
	unregisterVLCEvent("MediaPlayerMediaChanged", handleMediaPlayerMediaChanged);
	unregisterVLCEvent("MediaPlayerNothingSpecial",
			handle_MediaPlayerNothingSpecial);
	unregisterVLCEvent("MediaPlayerOpening", handle_MediaPlayerOpening);
	unregisterVLCEvent("MediaPlayerBuffering", handle_MediaPlayerBuffering);
	unregisterVLCEvent("MediaPlayerPlaying", handle_MediaPlayerPlaying);
	unregisterVLCEvent("MediaPlayerPaused", handle_MediaPlayerPaused);
	unregisterVLCEvent("MediaPlayerStopped", handle_MediaPlayerStopped);
	unregisterVLCEvent("MediaPlayerForward", handle_MediaPlayerForward);
	unregisterVLCEvent("MediaPlayerBackward", handle_MediaPlayerBackward);
	unregisterVLCEvent("MediaPlayerEndReached", handle_MediaPlayerEndReached);
	unregisterVLCEvent("MediaPlayerEncounteredError",
			handle_MediaPlayerEncounteredError);
	unregisterVLCEvent("MediaPlayerTimeChanged", handle_MediaPlayerTimeChanged);
	unregisterVLCEvent("MediaPlayerPositionChanged",
			handle_MediaPlayerPositionChanged);
	unregisterVLCEvent("MediaPlayerSeekableChanged",
			handle_MediaPlayerSeekableChanged);
	unregisterVLCEvent("MediaPlayerPausableChanged",
			handle_MediaPlayerPausableChanged);
	unregisterVLCEvent("MediaPlayerTitleChanged",
			handle_MediaPlayerTitleChanged);
	unregisterVLCEvent("MediaPlayerLengthChanged",
			handle_MediaPlayerLengthChanged);
}

function registerVLCEvent(event, handler) {
	var vlc = getVLC("vlcplayer");

	if (vlc) {
		if (vlc.attachEvent) {
			// Microsoft
			vlc.attachEvent(event, handler);
		} else if (vlc.addEventListener) {
			// Mozilla: DOM level 2
			vlc.addEventListener(event, handler, true);
		} else {
			// DOM level 0
			eval("vlc.on" + event + " = handler");
		}
	}
}

function unregisterVLCEvent(event, handler) {
	var vlc = getVLC("vlcplayer");
	if (vlc) {
		if (vlc.detachEvent) {
			// Microsoft
			vlc.detachEvent(event, handler);
		} else if (vlc.removeEventListener) {
			// Mozilla: DOM level 2
			vlc.removeEventListener(event, handler, true);
		} else {
			// DOM level 0
			eval("vlc.on" + event + " = null");
		}
	}
}

function cutImage() {

	var vlc = getVLC("vlcplayer");
	if (vlc) {
		vlc.video.takeSnapshot();
	}

};

/*function dd() {
	// var div=$("#vedio");

	//  var vlc =  getVLC("vlcplayer")
	var w = vlc.video.width;//视频原有尺寸  
	var h = vlc.video.height;//视频原有尺寸 
	alert(w);
	alert(h);
	var $canvas = $('canvas');
	alert($canvas)
	var ctx = $canvas[0].getContext('2d');
	alert(ctx)
	$canvas.attr({
		width : w,
		height : h
	});
	alert("d")
	ctx.drawImage(div, 0, 0, w, h);
	alert("d1")

	if (vlc) {
		vlc.video.takeSnapshot();

	}*/

//}

function doPlaySlower() {
	var vlc = getVLC("vlcplayer");
	if (vlc)
		vlc.input.rate = vlc.input.rate / 2;
	d
}

function doPlayFaster() {
	var vlc = getVLC("vlcplayer");
	if (vlc)
		vlc.input.rate = vlc.input.rate * 2;
}

var rate = 0;
var speed = 1;
var prevState = 0;
var monitorTimerId = 0;
var inputTracker;
var inputTrackerScrolling = false;
var inputTrackerIgnoreChange = false;
var telxState = false;
var canPause = true;
var canSeek = true;

function setPauseable(val) {
	canPause = val;
}
function doPlaySlower() {
	var vlc = getVLC("vlcplayer");
	if (vlc) {
		if (speed <= 0.125) {
			return;
		}

		vlc.input.rate = vlc.input.rate / 2;
		speed = speed / 2;
		$("#speed").html(speed+"X");
	}
}

function doPlayFaster() {
	var vlc = getVLC("vlcplayer");
	if (vlc) {
		if (speed >= 4) {
			return;
		}
		vlc.input.rate = vlc.input.rate * 2;
		speed = speed * 2;
		$("#speed").html(speed+"X");
	}
}

function doPlayOrPause() {
	var vlc = getVLC("vlcplayer");
	if (vlc) {
		vlc.input.rate = 1;
		speed = 1;
		$("#speed").html("");
		if (vlc.playlist.isPlaying && canPause) {
			vlc.playlist.togglePause();
			//monitor();
		} else if (vlc.playlist.items.count > 0) {
			vlc.playlist.play();
			//monitor();
		} else {
			alert('nothing to play !');
		}
	}
	

}


function doPlay()   
{    
    var vlc = getVLC("vlcplayer");
        vlc.playlist.play();   
           
        document.getElementById("btn_stop").disabled = false;   
        document.getElementById("btn_play").disabled = true;   
}   
function doStop()   
{  
        var vlc = getVLC("vlcplayer");
		getVLC("vlcplayer").playlist.stop();   
        document.getElementById("btn_stop").disabled = true;   
        document.getElementById("btn_play").disabled = false;   
}   
function fullScreen(){
	   var vlc = getVLC("vlcplayer");
		vlc.video.toggleFullscreen();   
	
	
}