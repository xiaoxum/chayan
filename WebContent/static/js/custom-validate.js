/**
 * 自定义数据验证js<br>
 * 
 * 
 * validateEmpty(obj,msg) 判断是否为空,为空弹出提示信息. obj: 需要验证的数据; msg: 为空的提示信息; <br>
 * checkRegExp(data, type, length, msg) 正则表达式验证(data: 要验证的数据，type:验证类型（idCard:身份证，邮箱，长度），长度大小,msg: 验证失败的信息)<br>
 * 
 * 例子： <br>
 * validateEmpty(userName,"用户名不可为空");
 * 
 * @author hwf
 */


 // 判断是否为空,为空弹出提示信息
function validateEmpty(obj,msg) {
    if (obj == undefined || obj.length <= 0 || obj == "") {
    	$.messager.alert("提示！", msg);
		return true;
    }else{
		return false;
	}
}

// 正则表达式验证(要验证的数据，验证类型（身份证，邮箱，长度），长度大小,验证失败的信息)
function checkRegExp(data, type, length, msg) {
    // 身份证验证
    if (type == "idCard") {
        var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
        if (regIdCard.exec(data)) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
    // 最大长度验证
    if (type == "maxLength") {
        if (data.length <= length) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
    // 最大长度验证
    if (type == "minLength") {
        if (data.length >= length) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
    // 长度匹配验证
    if (type == "length") {
        if (data.length == length) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
    // 中文姓名验证
    if (type == "isChineseName") {
        var re = /((^[\u4e00-\u9fa5]{2,}$))/;
        if (re.exec(data)) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
    // 是否含有中文验证()
    if (type == "isChinese") {
        var re = /[\u4e00-\u9fa5]/;
        if (re.exec(data)) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
    // 联系电话验证
    if(type == "phoneNumber") {
    	var re = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
    	if (re.exec(data)) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
    // 经度验证
    if(type == "longitude") {
    	var re = /^[\-\+]?(0?\d{1,2}\.\d{1,5}|1[0-7]?\d{1}\.\d{1,5}|180\.0{1,5})$/;
    	if (re.exec(data)) {
			return true;
		}else{
			$.messager.alert("提示！", msg+"(整数部分为正负0～180，必须输入1到5位小数)");
			return false;
		}
    }
    
    // 纬度验证
    if(type == "latitude") {
    	var re = /^[\-\+]?([0-8]?\d{1}\.\d{1,5}|90\.0{1,5})$/;
    	if (re.exec(data)) {
			return true;
		}else{
			$.messager.alert("提示！", msg+"(整数部分为正负0～90，必须输入1到5位小数)");
			return false;
		}
    }
    
    // 验证非零的正整数
    if(type == "positiveInteger") {
    	var re = /^\+?[1-9][0-9]*$/;
    	if (re.exec(data)) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
    // 验证正数 精确2位小数
    if(type == "twoDecimal") {
    	var re = /^(([1-9]+)|([0-9]+\.{0,1}[0-9]{1,2}))$/;
    	if (re.exec(data)) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
    // 验证邮箱
    if(type == "email") {
    	var re = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    	if (re.exec(data)) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
    // 验证url
    if(type == "url") {
    	var re = /^((ht|f)tps?):\/\/[\w\-]+(\.[\w\-]+)+([\w\-\.,@?^=%&:\/~\+#]*[\w\-\@?^=%&\/~\+#])?$/;
    	if (re.exec(data)) {
			return true;
		}else{
			$.messager.alert("提示！", msg);
			return false;
		}
    }
    
}


// 身份证验证
var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
	    21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",
	    34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",
	    43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川"
	    ,52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",
	    64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
	function isCardID(sId){
	    var iSum=0 ;
	    var info="" ;
	    if(!/^\d{17}(\d|x)$/i.test(sId)){
	    	$.messager.alert("提示！", "你输入的身份证长度或格式错误");
	    	return false;
	    } 
	    sId=sId.replace(/x$/i,"a");
	    if(aCity[parseInt(sId.substr(0,2))]==null){
	    	$.messager.alert("提示！", "你的身份证地区非法");
	    	return false;
	    } 
	    sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
	    var d=new Date(sBirthday.replace(/-/g,"/")) ;
	    if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
	    	$.messager.alert("提示！", "身份证上的出生日期非法");
	    	return false;
	    }
	    for(var i = 17;i>=0;i --){
	    	iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
	    }
	    if(iSum%11!=1){
	    	$.messager.alert("提示！", "你输入的身份证号非法");
	    	return false
	    }
	    return true;
	}


// 格式化时间
function getTime(/** timestamp=0 **/) {
    var ts = arguments[0] || 0;
    var t, y, m, d, h, i, s;
    t = ts ? new Date(ts * 1000) : new Date();
    y = t.getFullYear();
    m = t.getMonth() + 1;
    d = t.getDate();
    h = t.getHours();
    i = t.getMinutes();
    s = t.getSeconds();
    // 可根据需要在这里定义时间格式  
    return y + '-' + (m < 10 ? '0' + m: m) + '-' + (d < 10 ? '0' + d: d);
}

// 去除所有空格
function removeAllSpace(str){
	
	return str.replace(/\s+/g,"");
}
