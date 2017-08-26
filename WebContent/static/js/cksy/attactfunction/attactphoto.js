CreateReport("Report");
var reTime=6;
var zddy;
var xmmj=$('#xmlj').val();
$(function(){
	Report.LoadFromURL(xmmj+"/report/photo.grf");
	
	$('#startTime').datebox('setValue', formatterDate(new Date()));
	$('#endTime').datebox('setValue', formatterDate(new Date()));
	search(1);
	$("#tab tr:gt(0)").hover(function() {
		$(this).addClass('blue');
	}, function () {
	  $(this).removeClass('blue');
	});
	$('#ser').accordion({
		 animate:false
	})  
	//initPrinterdoc();
	//reInTime=setInterval("refreshPage()",1000);
	$("#clsbdh").textbox({onChange:function(){
		 $(this).textbox("getValue");
		var clsbdh = $(this).textbox("getValue").toUpperCase();
		$(this).textbox("setValue",clsbdh);
		
	 }});

	
})










function search(pageNo){
	var clsbdh = $("#clsbdh").textbox("getValue");
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	$.ajax({
        cache: true,
        type: "POST",
        url:xmmj+'/module/attact/loadattactphotos',
        data:{
        	    clsbdh:clsbdh,
	        	startTime:startTime,
	        	endTime:endTime,
	        	pageNo:pageNo
        	},
        async: false,
        error: function(request) {
            alert("系统操作繁忙请稍后！");
        },
        success: function(data) {
        	
        	if(data=="no-login"){
				top.location=xmmj+"/login.jsp"
        	}
        	
        	// 清空table内容
        	$("#myTbody").empty();
        	for(var i=0;i<data.list.length;i++){
        		// 将时间戳转成时间
        		if(i % 2 == 1){
					var tr="<tr ondblclick='jump("+data.list[i].srln+","+data.list[i].rckCount+")'class='rowbgcolor '>"
			         	+"<td >"+data.list[i].clsbdh+"</td>"
			         	+"<td>"+data.list[i].photoName+"</td>"
			         	+"<td>"+formatterDate(new Date(data.list[i].tackeDate))+"</td>"
			         
			        	+"<td ><button  type='button' onClick='dyzp(\""
						+ data.list[i].photoUrl
						+ "\")'>打印</button><button  type='button' onClick='viewPic(\""
						+ data.list[i].photoUrl
						+ "\")'>查看</button></td>"
			         	+"</tr>";
				}else{
					var tr="<tr ondblclick='jump("+data.list[i].srln+","+data.list[i].rckCount+")'  >"
					  
					     +"<td >"+data.list[i].clsbdh+"</td>"
		         	     +"<td>"+data.list[i].photoName+"</td>"
		         	     +"<td>"+formatterDate(new Date(data.list[i].tackeDate))+"</td>"
			         	
			         	+"<td ><button  type='button' onClick='dyzp(\""
						+ data.list[i].photoUrl
						+ "\")'>打印</button><button  type='button' onClick='viewPic(\""
						+ data.list[i].photoUrl
						+ "\")'>查看</button></td>"
			         	+"</tr>";
				}
        		// 添加tr
        		$("#myTbody").append(tr);
        	}
        	// 显示分页栏
        	$("#pp").show();
        	// 设置总条数
        	
        	$("#pp").pagination({
       		  total:data.totalCount
       		});
        	//reTime=5;
        	// reInTime=setInterval("refreshPage()",1000);
        }
    });
	
	$("#tab tr:gt(0)").hover(function() {
		$(this).addClass('blue');
	}, function () {
	  $(this).removeClass('blue');
	});
	

}
  function findInfo(){
	  var pageNo = $('#pp').pagination('options').pageNumber;
	  search(pageNo);
  }


function dyzp(picUrl){
	var printNum = $("#printNum").textbox("getValue");
	if(printNum==null || printNum==0){
		alert("请输入打印数量 ");
	}
	if (printNum==1) {
		Report.LoadDataFromURL(xmmj + "/module/login/getattactphotosj?picUrl=" + picUrl
				+ "&printNum=1&time=" +(new Date()));
		Report.Print(false);
	}else{
		var flag=(printNum%2==0);
		var num = flag?printNum/2:(printNum-1)/2;
		for(var j=0;j<num;j++){
			Report.LoadDataFromURL(xmmj + "/module/login/getattactphotosj?picUrl=" + picUrl
				+ "&printNum=2&time=" +(new Date()));
			Report.Print(false);
		}
		if(!flag){
			Report.LoadDataFromURL(xmmj + "/module/login/getattactphotosj?picUrl=" + picUrl
				+ "&printNum=1&time=" +(new Date()));
			Report.Print(false);
		}
	}
	
	
}
