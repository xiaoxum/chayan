CreateReport("Report");
var reTime=6;
var zddy;
var xmmj=$('#xmlj').val();
$(function(){
	Report.LoadFromURL(xmmj+"/report/photo.grf");
	setInterval("refreshPage()",1000);
	$("#menuId").combotree({
	       onCheck:function(node,checked){  
	    	   var tree = $('#menuId').combotree('tree');
	    	   if(checked){
		    	   //当点击 checkbox 时触发
		    	   var  node1=$(tree).tree('getParent',node.target); 
		    	   if(node1!=null){
		    		   $(tree).tree('check', node1.target);     
		    	   }
		       /*  var nodes=$(tree).tree("getChildren",node.target);
		    		  //选中父节点
		    	  $.each(nodes,function(index,node2){
		    		 $(tree).tree('check', node2.target);
		    	  })  */
	    	   }else{
	    		   var pnode=$(tree).tree('getParent',node.target);
	    		    if(pnode!=null){
	    			  var che=false;
		    		  var nodes=$(tree).tree("getChildren",pnode.target)
		    		   $.each(nodes,function(index,node1){
		    			 if(node1.checked){
		    				 che=true;
		    			 }
		    		   })
		    		   if(!che){
		    			   $(tree).tree('uncheck', pnode.target);
		    		   } 
	    		     }
	    		    //获取子节点
	    		    var nodes=$(tree).tree("getChildren",node.target);
		    		  //选中父节点
		    	    $.each(nodes,function(index,node2){
		    	    	 if(node2.checked){
		    	    		 $(tree).tree('check', node.target);
		    	    		 return;
		    			 }
		    	      }) 
	    	   }
	      }
	})
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
	$("#hphm").textbox({onChange:function(){
		 $(this).textbox("getValue");
		var hphm = $(this).textbox("getValue").toUpperCase();
		$(this).textbox("setValue",hphm);
		
	 }});
	
	
})


function refreshPage(){
	reTime=reTime-1;
	var zddy=$('input:radio[name="zddy"]:checked').val();
	var pageNo = $('#pp').pagination('options').pageNumber;
	$("#tss").html(reTime+"秒后自动刷新");
	if(reTime==0){
		if(zddy==1){
			zddyerr();
		}
		search(pageNo);
		reTime=6;
	}
	
}


//查询
function zddyerr(){
	
	var clsbdh = $("#clsbdh").textbox("getValue");
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var parentId = $("#mySelect").combobox('getValue');
	var hphm = $("#hphm").textbox("getValue");
	var pageNo = $('#pp').pagination('options').pageNumber;
	var pageSize= $('#pp').pagination('options').pageSize;
	var photoId = $("#menuId").combotree("getValues");
	var printNum = $("#printNum").textbox("getValue");
	if(printNum==null || printNum==0){
		alert("请输入打印数量 ");
	}
	if (validateEmpty(photoId,"请选择照片，用于自动打印")) {
       return;
    }
	$.ajax({
    cache: true,
    type: "POST",
    url:xmmj+'/module/photoinfo/findzddys',
    data:{
	        	pageNo:pageNo,
	        	parentId:parentId,
	        	startTime:startTime,
	        	clsbdh:clsbdh,
	        	hphm:hphm,
	        	endTime:endTime,
	        	picId:photoId.toString()
    	},
    async: false,
    error: function(request) {
        alert("系统操作繁忙请稍后！");
    },
    success: function(data) {
    	if(data=="no-login"){
			top.location=xmmj+"/login.jsp"
    	}
    	if (data!=null) {
    		for(var i=0;i<data.length;i++){
    			if (printNum==1) {
    				Report.LoadDataFromURL(xmmj + "/module/login/getphotosj?picUrl=" + data[i].picUrl
							+ "&printNum=1&srln="+data[i].srln+"&rckCount="+ data[i].rckCount+"&picId="+data[i].picId);
					Report.Print(false);
				}else{
					var flag=(printNum%2==0);
					var num = flag?printNum/2:(printNum-1)/2;
					for(var j=0;j<num;j++){
						Report.LoadDataFromURL(xmmj + "/module/login/getphotosj?picUrl=" + data[i].picUrl
								+ "&printNum=2&srln="+data[i].srln+"&rckCount="+ data[i].rckCount+"&picId="+data[i].picId);
						Report.Print(false);
					}
					if(!flag){
						Report.LoadDataFromURL(xmmj + "/module/login/getphotosj?picUrl=" + data[i].picUrl
								+ "&printNum=1&srln="+data[i].srln+"&rckCount="+ data[i].rckCount+"&picId="+data[i].picId);
						Report.Print(false);
					}
				}
    		}
		}
    }
});
}





function checkNode(node){
	  var  node1=$(tree).tree('getParent',node.target);          //得到父节点
	    $("#menuId").tree('check', node1.target);                                      //选中父节点
}
function search(pageNo){
	var clsbdh = $("#clsbdh").textbox("getValue");
	var startTime = $("#startTime").datebox("getValue");
	var endTime = $("#endTime").datebox("getValue");
	var parentId = $("#mySelect").combobox('getValue');
	var hphm = $("#hphm").textbox("getValue");
	var sfdy = $("#sfdy").combobox('getValue');
	$.ajax({
        cache: true,
        type: "POST",
        url:xmmj+'/module/photoinfo/findphotodtos',
        data:{
        	    clsbdh:clsbdh,
	        	startTime:startTime,
	        	endTime:endTime,
	        	pageNo:pageNo,
	        	operType:parentId,
	        	hphm:hphm,
	        	sfdy:sfdy
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
        		var sji=data.list[i].sfdy==0?"未打印":"已打印";
        		if(i % 2 == 1){
					var tr="<tr ondblclick='jump("+data.list[i].srln+","+data.list[i].rckCount+")'class='rowbgcolor '>"
			         	+"<td >"+data.list[i].clsbdh+"</td>"
			         	+"<td>"+data.list[i].hphm+"</td>"
			         	+"<td>"+data.list[i].cllxName+"</td>"
			         	+"<td>"+data.list[i].typeName+"</td>"
			         	/*+"<td>"+data.list[i].picUrl+"</td>"*/
			         	+"<td>"+data.list[i].picName+"</td>"
			         	+"<td>"+sji+"</td>"
			         	+"<td>"+data.list[i].picTimeS+"</td>"
			        	+"<td ><button  type='button' onClick='dyzp("+ data.list[i].srln
						+ ","
						+ data.list[i].picId
						+ ","
						+ data.list[i].rckCount
						+ ",\""
						+ data.list[i].picUrl
						+ "\")'>打印</button><button  type='button' onClick='viewPic(\""
						+ data.list[i].picUrl
						+ "\")'>查看</button></td>"
			         	+"</tr>";
				}else{
					var tr="<tr ondblclick='jump("+data.list[i].srln+","+data.list[i].rckCount+")'  >"
					  
						+"<td >"+data.list[i].clsbdh+"</td>"
			         	+"<td>"+data.list[i].hphm+"</td>"
			         	+"<td>"+data.list[i].cllxName+"</td>"
			         	+"<td>"+data.list[i].typeName+"</td>"
			         	/*+"<td>"+data.list[i].picUrl+"</td>"*/
			         	+"<td>"+data.list[i].picName+"</td>"
			         	+"<td>"+sji+"</td>"
			         	+"<td>"+data.list[i].picTimeS+"</td>"
			         	+"<td ><button  type='button' onClick='dyzp("+ data.list[i].srln
						+ ","
						+ data.list[i].picId
						+ ","
						+ data.list[i].rckCount
						+ ",\""
						+ data.list[i].picUrl
						+ "\")'>打印</button><button  type='button' onClick='viewPic(\""
						+ data.list[i].picUrl
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


function dyzp(srln,picId,rckCount,picUrl){
	var printNum = $("#printNum").textbox("getValue");
	if(printNum==null || printNum==0){
		alert("请输入打印数量 ");
	}
	if (printNum==1) {
		Report.LoadDataFromURL(xmmj + "/module/login/getphotosj?picUrl=" + picUrl
				+ "&printNum=1&srln="+srln+"&rckCount="+ rckCount+"&picId="+picId+ "&time=" +(new Date()));
		Report.Print(false);
	}else{
		var flag=(printNum%2==0);
		var num = flag?printNum/2:(printNum-1)/2;
		for(var j=0;j<num;j++){
			Report.LoadDataFromURL(xmmj + "/module/login/getphotosj?picUrl=" + picUrl
				+ "&printNum=2&srln="+srln+"&rckCount="+ rckCount+"&picId="+picId+ "&time=" +(new Date()));
			Report.Print(false);
		}
		if(!flag){
			Report.LoadDataFromURL(xmmj + "/module/login/getphotosj?picUrl=" + picUrl
				+ "&printNum=1&srln="+srln+"&rckCount="+ rckCount+"&picId="+picId+ "&time=" +(new Date()));
			Report.Print(false);
		}
	}
	
	
}
