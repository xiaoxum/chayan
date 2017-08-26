//使用监控程序读取条码，定时读取看是否有条码信息
		 function readQrtext() {
		 	// alert(33);
		     var barcodetemp = "";
		 // var aa='黄/白/棕';
		 // var b=aa.split("/");
		 // $("#_csys1").val(getcsyshz(b[0]));
		 // $("#_csys2").val(getcsyshz(b[1]));
		 // $("#_csys3").val(getcsyshz(b[2]));
		    if(typeof(vehPrinter) != "undefined"){
		 	    var strbarcode = vehPrinter.GetQrText();
		 	    
		 	    if (strbarcode == "") {
		 	        return 0;
		 	    }

		 	    if (strbarcode == "-1") {
		 	        alert("条码信息有误!");
		 	        return 0;
		 	    }
		 	    // alert(strbarcode);
		 	    parsebarcode(strbarcode);
		    }
		    
		 }

		 function parsebarcode(strbarcode) {
		     // alert(strbarcode);
		     clzt = "A";
		     var barArray = strbarcode.split("|");
		     var i;
		     var indx_ = 0;
		     var qx_jh_dp = "";
		     var ywlx_B = "0";
		     var dykjbbh = "";
		     strBarCodeType = barArray[0];

		     var strBarCodeTypeArray = strBarCodeType.split("_");
		     if (strBarCodeTypeArray.length > 1) {
		         dykjbbh = strBarCodeTypeArray[1];
		     }


		     if (strBarCodeType == "ZCCCHGZ") {
		         if (dykjbbh != "") {
		             dykjbbh = dykjbbh.substring(1, 2);
		             // tmri.dykjbbh.value = dykjbbh;
		         }

		         if (dykjbbh != "3") {
		             alert("旧版合格证不予办理车辆注册登记，请联系生产厂家进行更换！");
		             formclear();
		             return;
		         }
		     }
		     strBarCodeType = strBarCodeTypeArray[0];
		     // 该条码组成为：
		     // HGFOZ_<版本> 条码头 0
		     // Hgfo_Hg 海关 1
		     // Hgfo_Zmsbh 证明书编号 2
		     // Hgfo_Cpxh 厂牌型号 3
		     // Hgfo_Csys 车身颜色 4
		     // Hgfo_Fdjh 发动机号 5
		     // Hgfo_Clsbdh 车辆识别代号 6
		     // Hgfo_Ccrq 出厂日期 7
		     // Hgfo_Cd 产地 制造国/地区 8
		     // Hgfo_Wsbh 裁定没收法律文书编号 9
		     // Hgfo_Qfbmdh 签发部门电话 10
		     // Hgfo_Qfrq

		     if (strBarCodeType == "HGFOZ") {
		         $("#_clxh").val(barArray[3]);
		         $("#_fdjh").val(barArray[5]);
		         // $("#_clsbdh").val(barArray[6]);
		         $("#_hphm").val($("#clsbdh").val().substr(10, 7));
		         $("#_ccrq").val(barArray[7]);
		     return 1;
		     }


		     if (strBarCodeType == "ZCCCHGZ") {
		         qx_jh_dp = barArray[1];
		         // ------------------------------------------------------------------//
		         // 读取的是全项合格证
		         if (qx_jh_dp == "QX") {
		             if ($("#clsbdh").val() != "") {
		                 alert("请先清空前一辆车的信息!");
		                 return 0;
		             }


		             if ($("#xh").textbox("getValue") == "") {
		                 $("#xh").textbox("setValue",barArray[2]);
		             }
		             if ($("#fzTime").textbox("getValue") == "") {
		                 $("#fzTime").textbox("setValue",barArray[3]);
		             }
		             if ($("#zzcmc").textbox("getValue") == "") {
		                 $("#zzcmc").textbox("setValue",barArray[4]);
		             }
		            /*  if ($("#_ccrq").val() == "") {
//		                 $("#_ccrq").val(barArray[61]);
		             	$("#_ccrq").val(barArray[49]);
		             } */
		             if ($("#dpType").textbox("getValue") == "") {
		                 $("#dpType").textbox("setValue",barArray[10]);
		             }
		            
		             // $("#cllx").val()=barArray[6];

		           /*   if ($("#clpp1").textbox("getValue") == "" && barArray[7].indexOf("/") > 0) {
		                 var vehClpp = barArray[7].split("/");
		                 // $("#tempclpp1").val() = vehClpp[0];
		                 $("#clpp1").textbox("setValue",vehClpp[0]);
		                 $("#clpp2").textbox("setValue",vehClpp[1]);
		             } */

		             if ($("#clpp1").textbox("getValue") == "") {
		                 // $("#tempclpp1").val() = barArray[7];
		                 $("#clpp1").textbox("setValue",barArray[7]);
		             }
		            
		             if ($("#copyClxh").textbox("getValue") == "") {
		                 // $("#tempclxh").val() = barArray[8];
		                 $("#copyClxh").textbox("setValue",barArray[8]);
		             }
		             if ($("#csys").textbox("getValue") == "") {
		            	 
		            	 $("#csys").textbox("setValue",barArray[9]);
		             }
		             
		             if ($("#csys1").combobox("getValue") == "") {
		             	// barArray[9]='白';
		                 var csys = barArray[9].split("/");
		                 
		                  //alert(csys[0]+','+csys[1]+','+csys[3]);
		                 if(csys[0]!=null){
		                 	// alert(getcsyshz(csys[0]));
		                 	$("#csys1").combobox("setValue",getcsyshz(csys[0]));
		                 }
		                 if(csys[1]!=null){
		                 	$("#csys2").combobox("setValue",getcsyshz(csys[1]));
		                 }
		                 if(csys[2]!=null){
		                 	
		                 	$("#csys3").combobox("setValue",getcsyshz(csys[2]));
		                 }
		                 // alert($("#_csys1").val());
		             }
		             
		             // $("#csys").val()=barArray[9];
		             if ($("#clsbdh").textbox("getValue") == "") {
		            	 $("#clsbdh").textbox("setValue",barArray[13] + barArray[14]);
		                 $("#copyClsbdh").textbox("setValue",barArray[13] + barArray[14]);
		                // alert(123+$("#clsbdh").textbox("getValue").substr(10, 7));
		                 /* $("#_hphm").val($("#_clsbdh").val().substr(10, 7)); */
		                 
		             }
		             
		             if ($("#fdjh").textbox("getValue") == "") {
		                 $("#fdjh").textbox("setValue",barArray[15]);
		                 /*
		 				 * indx_=$("#fdjh").val().lastIndexOf(" "); if (indx_>0){
		 				 * $("#fdjh").val()= $("#fdjh").val().substr(indx_+1); }
		 				 */
		             }
		           /*    if ($("#_hgzfzrq").val() == "") {
		                 $("#_hgzfzrq").val(barArray[3]);
		                 /*
		 				 * indx_=$("#fdjh").val().lastIndexOf(" "); if (indx_>0){
		 				 * $("#fdjh").val()= $("#fdjh").val().substr(indx_+1); }
		 				 */
		            // }  */
		             
		             if ($("#fdxh").textbox("getValue") == "") {
		                 // reSetSelectOption($("#fdjxh,barArray[16]);
		                 $("#fdxh").textbox("setValue",barArray[16]);
		             }
		             // alert(barArray[17]);
		             if ( barArray[17].length > 0) {
		                 // alert('ryzl:'+barArray[17].substring(0, 1));
		                 // initComboxAndValue("_rlzl","燃料种类",barArray[17].substring(0,
		 				// 1));
		                 $("#rlzl").textbox("setValue",getrlzl(barArray[17].substring(0, 1)));
		                 // $("#_rlzl").combobox('select', barArray[17].substring(0, 1));
		                 // $("#_rlzl").val(barArray[17].substring(0, 1));
		                
		             }
		             /*
		 			 * 这个信息暂时不要取 if ($("#_pfbz").val() == "") {
		 			 * $("#_pfbz").val(barArray[18]); } if ($("#zxxs").val() == "") {
		 			 * //alert("zzxs:" + barArray[21]); $("#zxxs").val(barArray[21]); }
		 			 * if ($("#_zzl").val() == "") { $("#_zzl").val(barArray[36]); } if
		 			 * ($("#hdzzl").val() == "") { $("#hdzzl").val(barArray[37]); } if
		 			 * ($("#zbzl").val() == "") { $("#zbzl").val(barArray[38]); } if
		 			 * ($("#zqyzzl").val() == "") { $("#zqyzzl").val(barArray[40]); }
		 			 * 
		 			 * 
		 			 * if ($("#hdzk").val() == "") { if (barArray[43] != null &&
		 			 * barArray[43].indexOf("+") > 0) { var jsszk =
		 			 * barArray[43].split("+"); $("#hdzk").val(jsszk[0] + jsszk[1]); }
		 			 * else { $("#hdzk").val(barArray[43]); } }
		 			 */
		             // alert(barArray[41]);
		             if ($("#hdzk").textbox("getValue") == "") {
		                 if (barArray[41] != null && barArray[41].indexOf("/") > 0) {
		                     $("#hdzk").textbox("setValue",barArray[41].substring(0, barArray[41].indexOf("/")));
		                     $("#hdzk1").textbox("setValue",barArray[41].substring(0, barArray[41].indexOf("/")));
		                 } else {
		                     $("#hdzk").textbox("setValue",barArray[41]);
		                     $("#hdzk1").textbox("setValue",barArray[41]);
		                 }
		             }
		             if ($("#pl").textbox("getValue") == "") {
		                 $("#pl").textbox("setValue",barArray[19]);
		             }
		             if ($("#gl20").textbox("getValue") == "") {
		                 if (barArray[20] != null && barArray[20].indexOf("/") > 0) {
		                     $("#gl20").textbox("setValue",barArray[20].substring(0, barArray[20].indexOf("/")));
		                 } else {
		                     $("#gl20").textbox("setValue",barArray[20]);
		                 }
		             }
		             
		             
		             //
		             if ($("#lts").textbox("getValue") == "") {
		                 $("#lts").textbox("setValue",barArray[24]);
		             }
		             if ($("#ltgg").textbox("getValue") == "") {
		                 $("#ltgg").textbox("setValue",barArray[25]);
		                 if ($("#ltgg").textbox("getValue").length > 20) {
		                     $("#ltgg").textbox("setValue",$("#ltgg").textbox("getValue").substring(0, 20));
		                 }
		             }
		             if ($("#gbthps").textbox("getValue") == "") {
		            	 $("#gbthps").textbox("setValue",barArray[26]);
		                /*  var zxzs = 1;
		                 if (barArray.length > 55) {
		                     zxzs = parseInt(barArray[55]);
		                 }
		                 if (barArray[6] == "挂车") {
		                     $("#gbthps").textbox("setValue",getGbthps("-/" + barArray[26], 1));
		                 } else {
		                     $("#gbthps").textbox("setValue",getGbthps(barArray[26], zxzs));
		                 } */
		             }
		             if ($("#zj").textbox("getValue") == "") {
		                 $("#zj").textbox("setValue",barArray[27]);
		             }
		             if ($("#zh").textbox("getValue")  == "") {
		                 $("#zh").textbox("setValue",barArray[28]);
		             }
		             if ($("#zs").textbox("getValue")  == "") {
		                 $("#zs").textbox("setValue",barArray[29]);
		             }
		             //轮距
		             if ($("#hl").textbox("getValue") == "") {
			            	
		                 if (barArray[22] != null && barArray[22].indexOf("/") > 0) {
		                     $("#hl").textbox("setValue",barArray[22].substring(0, barArray[22].indexOf("/"))+"/"+barArray[23].substring(0, barArray[22].indexOf("/")));
		                 } else {
		                     $("#hl").textbox("setValue",barArray[22]+"/"+barArray[23]);
		                 }
		             }
	                    
		            /*  if ($("#hlj").val() == "") {
		                 $("#hlj").val(getHlj(barArray[23], $("#_zs").val()));
		             } */

		             if ($("#wkcc").textbox("getValue") == "") {
		            	 if(barArray[30]==""){
		            		 $("#wkcc").textbox("setValue","");
		            	 }else{
		                 // alert(barArray[30]);
		                 $("#wkcc").textbox("setValue",barArray[30]+"*"+barArray[31]+"*"+barArray[32]);
		            	 }	
		             }
		             /* if ($("#_wkk").val() == "") {
		                 $("#_wkk").val(barArray[31]);
		             }
		             if ($("#_wkg").val() == "") {
		                 $("#_wkg").val(barArray[32]);
		             } */
		             if ($("#hxnbc").textbox("getValue") == "") {
		            	 if(barArray[33]==""){
		            		 $("#hxnbc").textbox("setValue","");
		            	 }else{
		                 $("#hxnbc").textbox("setValue",barArray[33]+"*"+barArray[34]+"*"+barArray[35]);
		            	 }
		             }
		            /*  if ($("#_hxnbcck").val() == "") {
		                 $("#_hxnbcck").val(barArray[34]);
		             }
		             if ($("#_hxnbccg").val() == "") {
		                 $("#_hxnbccg").val(barArray[35]);
		             } */
		             if($("#zbzl").textbox("getValue") == ""){
		             	$("#zbzl").textbox("setValue",barArray[38]);
		             }
		             if($("#zzl").textbox("getValue") == ""){
		             	$("#zzl").textbox("setValue",barArray[36]);
		             }
		             if ($("#zxxs").textbox("getValue") == "") {
		                 //alert("zzxs:" + barArray[21]);
		                 $("#zxxs").textbox("setValue",barArray[21]);
		             }
		             if ($("#bfbz").textbox("getValue") == "") {
		                 //alert("zzxs:" + barArray[21]);
		                 $("#bfbz").textbox("setValue",barArray[18]);
		             }
		             if($("#hdzzl").textbox("getValue") == "") 
		             { 
		             	$("#hdzzl").textbox("setValue",barArray[37]);
		             } 
		             if($("#zqyzl").textbox("getValue") == "") 
		             { 
		             	$("#zqyzl").textbox("setValue",barArray[40]);
		             }
		             
		             if($("#yh").textbox("getValue") == "") 
		             { 
		             	$("#yh").textbox("setValue",barArray[54]);
		             }
		             if($("#zgscs").textbox("getValue") == "") 
		             { 
		             	$("#zgscs").textbox("setValue",barArray[48]);
		             }
		             if($("#zzrq").textbox("getValue") == "") 
		             { 
		             	$("#zzrq").textbox("setValue",barArray[49]);
		             }
		 			if ($("#qpk").textbox("getValue") == "") 
		 			{ 
		 				if (barArray[43] != null &&barArray[43].indexOf("+") > 0)
		 				{ 
		 				  var jsszk = barArray[43].split("+");
		 				  $("#qpk").textbox("setValue",jsszk[0]+"/"+jsszk[1]);
		 		         /*  $("#_hpzk").val()=jsszk[1]; */
		 				}
		 				else
		 				{
		 				   $("#qpk").textbox("setValue",barArray[43]);
		 				}
		 			}
		             // $("#barcode").val() = "";

		         }
		      	 
		   
		     
	     
	     // ---------------------------------------------------------//
		      // 读取的是简化合格证
				    if(qx_jh_dp=="JH"){
				        if($("#clsbdh").val()!=""){
				            alert("请先清空前一辆车的信息!");
				            return 0;
				        }else{
				            $("#hidhgzzt").val("2");
				        }
				      // $("#hgzbh").val()=barArray[2];
				      $("#zzcmc").textbox("setValue",barArray[4]);
				      $("#fzTime").textbox("setValue",barArray[3]);
				      // $("#cllx").val()=barArray[6];
				     
				      if(barArray[7].indexOf("/")>0){
				        var vehClpp = barArray[7].split("/");
				        // $("#tempclpp1").val()=vehClpp[0];
				        $("#clpp1").textbox("setValue",vehClpp[0]);
				       /*  $("#clpp2").val(vehClpp[1]); */
				      }else{
				        // $("#tempclpp1").val()=barArray[7];
				      $("#clpp1").textbox("setValue",barArray[7]);
				      }
				      
				      // $("#tempclxh").val()=barArray[8];
				      $("#copyClxh").textbox("setValue",barArray[8]);

				      $("#clsbdh").textbox("setValue",barArray[13] + barArray[14]);
				     /*  $("#hphm").val($("#clsbdh").val().substr(10, 7)); */
				     if(barArray[30]==""){
				    	 $("#wkcc").textbox("setValue",""); 
				     }else{
				    	 $("#wkcc").textbox("setValue",barArray[30]+"*"+barArray[31]+"*"+barArray[32]);
				     }
				     
				     /*  $("#cwkk").val(barArray[31]);
				      $("#cwkg").val(barArray[32]); */
				      if(barArray[33]==""){
				    	  $("#hxnbc").textbox("setValue",""); 
				      }else{
				    	  $("#hxnbc").textbox("setValue",barArray[33]+"*"+barArray[34]+"*"+barArray[35]);  
				      }
				     /* 
				      $("#hxnbkd").val(barArray[34]);
				      $("#hxnbgd").val(barArray[35]); */
				      $("#zzl").textbox("setValue",barArray[36]);
				      $("#hdzzl").textbox("setValue",barArray[37]);
				      $("#zbzl").textbox("setValue",barArray[38]);
				     /*  $("#zqyzzl").textbox("setValue",barArray[40]); */
				      // $("#hdzk").val()=barArray[41];
				      if (barArray[41]!=null && barArray[41].indexOf("/")>0){
				          $("#hdzk").textbox("setValue",barArray[41].substring(0, barArray[41].indexOf("/")));
				          $("#hdzk1").textbox("setValue",barArray[41].substring(0, barArray[41].indexOf("/")));
				      }else{
				      $("#hdzk").textbox("setValue",barArray[41]);
				      $("#hdzk1").textbox("setValue",barArray[41]);
				      }
				      if(barArray[43]!=null && barArray[43].indexOf("+")>0){
				          var jsszk = barArray[43].split("+");
				          $("#hdzk").textbox("setValue",jsszk[0] + jsszk[1]);
				          $("#hdzk1").textbox("setValue",jsszk[0] + jsszk[1]);
				        // $("#qpzk").val()=jsszk[0];
				        // $("#hpzk").val()=jsszk[1];
				      } else {
				        $("#hdzk").textbox("setValue",barArray[43]);
				        $("#hdzk1").textbox("setValue",barArray[43]);
				        // $("#qpzk").val()=barArray[43];
				      }
				      alert("请继续读取底盘合格证信息！");
				      // $("#barcode").val()="";
				      // $("#barcode.parentNode.focus();
				  }
	 // -------------------------------------------------------------------//
		    // 读取的是底盘合格证
		    if(qx_jh_dp=="DP"){
		        if($("#hidhgzzt").val()!="2"){
		          alert("请先读取简易合格证信息!");
		          return 0;
		        }else{
		        $("#hidhgzzt").val("3");
		        } 
				
				
		      $("#fdjh").textbox("setValue",barArray[12]);
		      // reSetSelectOption($("#fdjxh,barArray[13]);
		      $("#fdxh").textbox("setValue",barArray[13]);
		      $("#rlzl").textbox("setValue",getrlzl(barArray[14].substring(0, 1)));
		      // if( barArray[14].length>1){
		        // $("#rlzl2").val()=barArray[14].substring(1,2);
		      // }
		     /*  $("#pfbz").val(barArray[15]); */
		      
		      $("#zxxs").textbox("setValue",barArray[18]);
		      // $("#gl").val()=barArray[17];
		      $("#pl").textbox("setValue",barArray[16]);
		      if (barArray[17]!=null && barArray[17].indexOf("/")>0){
		          $("#pl").textbox("setValue",barArray[16]+"/"+barArray[17].substring(0, barArray[17].indexOf("/")));
		      }else{
		    	  $("#pl").textbox("setValue",barArray[16]+"/"+barArray[17]);
		      }
		     

		      $("#lts").textbox("setValue",barArray[21]);
		      $("#ltgg").textbox("setValue",barArray[22]);
		      if ($("#ltgg").textbox("getValue").length>20){
		          $("#ltgg").textbox("setValue",$("#ltgg").textbox("getValue").substring(0, 20));
		      }
		      if(barArray[6]=="挂车"){
		          $("#gbthps").textbox("setValue",getGbthps("-/" + barArray[23], 1));
		      }else{
		      $("#gbthps").textbox("setValue",getGbthps(barArray[23], 1));
		      }
		      // $("#gbthps").val()=getGbthps(barArray[23]);
		      $("#zj").textbox("setValue",getZj(barArray[24]));
		      $("#zs").textbox("setValue",barArray[26]);
		      // $("#qlj").val()=barArray[19];
		      if (barArray[19]!=null && barArray[19].indexOf("/")>0){
		          $("#hl").textbox("setValue",barArray[19].substring(0, barArray[19].indexOf("/"))+"/"+getHlj(barArray[20], $("#zs").textbox("getValue")));
		      }else{
		      $("#hl").textbox("setValue",barArray[19]+"/"+getHlj(barArray[20], $("#zs").textbox("getValue")));
		      }
		     /*  $("#hlj").val(getHlj(barArray[20], $("#zs").val())); */
		      // if($("#zqyzl").val()==""){$("#zqyzl").val()=barArray[32];}
		      if(barArray[6]=="二类底盘" && $("#hdzk").textbox("getValue")==""){
		        if(barArray[34]!=null && barArray[34].indexOf("+")>0){
		            var jsszk = barArray[34].split("+");
		            $("#hdzk").textbox("setValue",jsszk[0] + jsszk[1]);
		            $("#hdzk1").textbox("setValue",jsszk[0] + jsszk[1]);
		          // $("#qpzk").val()=jsszk[0];
		          // $("#hpzk").val()=jsszk[1];
		      } else {
		          $("#hdzk").textbox("setValue",barArray[34]);
		          $("#hdzk1").textbox("setValue",barArray[34]);
		          // $("#qpzk").val()=barArray[34];
		        }
		      }
		      // $("#barcode").val()="";
		      // paramForm.isSetClsbdh").val()="0";
		    }


		    // 排量大于四位，则不读取
		    var float_pl = parseFloat($("#pl").textbox("getValue"));
		    $("#pl").textbox("setValue",Math.round(float_pl));
		    if (isNaN($("#pl").textbox("getValue"))){
				 $("#pl").textbox("setValue","");
			}
		    // if (tmri.pl.value.length>4) tmri.pl.value="";
		    //
		    /*
			 * if (barArray[6] == "两轮摩托车和两轮轻便摩托车" || barArray[6] == "三轮摩托车和三轮轻便摩托车" ||
			 * barArray[6] == "低速货车" || barArray[6] == "三轮汽车") { tmri.ltgg.readOnly =
			 * false; tmri.lts.readOnly = false; tmri.zs.readOnly = false;
			 * tmri.zzl.readOnly = false; tmri.hdzk.readOnly = false;
			 * tmri.hdzzl.readOnly = false; tmri.qpzk.readOnly = false;
			 * tmri.hpzk.readOnly = false; }
			 */

		    $("#fzTime").textbox("setValue",$("#fzTime").textbox("getValue").replace("年", "-"));
		    $("#fzTime").textbox("setValue",$("#fzTime").textbox("getValue").replace("月", "-"));
		    $("#fzTime").textbox("setValue",$("#fzTime").textbox("getValue").replace("日", ""));
		    /* $("#fdjh").textbox("setValue",$("#fdjh").textbox("getValue").replace($("#fdjxh").textbox("getValue") + " ", "")); */
		  
		}
		return 1;
		}

	function getGbthps(m_SourceValue, m_zxzs) {

	    // 是否为数字
	    var newgbthps;
	    var i_gbthps = 0;
	    if (m_SourceValue.length == 0) {
	        return "";
	    }
	    // m_SourceValue="-/"+m_SourceValue;
	    var paraArray = m_SourceValue.split(","); // 根据","分割成字符串数组
	    var i;
	    var j;
	    var k;
	    for (i = 0; i < paraArray.length; i++) {
	        if (isNum_Para(paraArray[i])) {
	            // 如为数字，直接判断
	            i_gbthps = parseInt(paraArray[i]);
	        }
	        else {
	            i_gbthps = 0;
	            if (paraArray[i].indexOf("/") > -1) {

	                for (k = 0; k < m_zxzs; k++) {
	                    paraArray[i] = paraArray[i].substr(paraArray[i].indexOf("/") + 1);
	                }
	                paraArray[i] += "/";

	                while (paraArray[i].indexOf("/") > -1) {
	                    newgbthps = paraArray[i].substring(0, paraArray[i].indexOf("/"));
	                    if (isNum_Para(newgbthps)) {
	                        i_gbthps += parseInt(newgbthps);
	                    } else {
	                        if (newgbthps.indexOf("+") > 0) {
	                            var valueSplit = newgbthps.split("+");
	                            for (j = 0; j < valueSplit.length; j++) {
	                                // 为了应对钢板弹簧片数形式为‘-/8+-’的情况，所以增加valueSplit[j]是否为数字的判断
	                                if (isNum_Para(valueSplit[j])) {
	                                    i_gbthps += parseInt(valueSplit[j]);
	                                }
	                            }
	                            // i_gbthps = i_gbthps+(parseInt(valueSplit[0]) +
								// parseInt(valueSplit[1]));
	                        }
	                    }
	                    paraArray[i] = paraArray[i].substr(paraArray[i].indexOf("/") + 1);
	                }
	            } else {
	                if (paraArray[i].indexOf("+") > 0) {
	                    var valueSplit = paraArray[i].split("+");
	                    for (j = 0; j < valueSplit.length; j++) {
	                        i_gbthps += parseInt(valueSplit[j]);
	                    }
	                    // i_gbthps=parseInt(valueSplit[0]) +
						// parseInt(valueSplit[1]);
	                }
	            }
	        }
	    }
	    if (i_gbthps == 0) {
	        return "";
	    }
	    else {
	        return i_gbthps;
	    }
	}

	function getZj(m_SourceValue) {

	    // 是否为数字
	    var newgbthps;
	    var i_zj = 0;
	    if (m_SourceValue.length == 0) {
	        i_zj = 0;
	    }
	    var paraArray = m_SourceValue.split("+"); // 根据","分割成字符串数组
	    var i;
	    for (i = 0; i < paraArray.length; i++) {
	        if (isNum_Para(paraArray[i])) {
	            // 如为数字，直接判断
	            i_zj += parseInt(paraArray[i]);
	        }
	    }
	    return i_zj;
	}

	// 后轮距
	function getHlj(m_SourceValue, m_zs) {
	    // 是否为数字
	    var newgbthps;
	    var i_lj = 0;

	    if (m_SourceValue.length == 0) {
	        return "";
	    }
	    var i_zs = parseInt(m_zs);
	    if (m_SourceValue.indexOf("/") > 0) {
	        var paraArray = m_SourceValue.split("/"); // 根据","分割成字符串数组
	        if (i_zs == 3) {
	            i_lj = parseInt(paraArray[0]);
	        } else {
	            i_lj = parseInt(paraArray[paraArray.length - 2]);
	        }
	    } else {
	        if (m_SourceValue.indexOf("+") > 0) {
	            var paraArray = m_SourceValue.split("+"); // 根据","分割成字符串数组
	            var i;
	            for (i = 0; i < paraArray.length; i++) {
	                if (isNum_Para(paraArray[i])) {
	                    // 如为数字，直接判断
	                    i_lj += parseInt(paraArray[i]);
	                }
	            }
	        } else {
	            i_lj = parseInt(m_SourceValue);
	        }
	    }
	    return i_lj;
	}

	// -------------------------------
	// 函数名：isNum(i_field,i_value)
	// 功能介绍：检查输入字符串是否为数字
	// 参数说明：数据项，输入的对应值
	// 返回值 ：1-是数字,0-非数字
	// -------------------------------
	function isNum_Para(i_value) {
	    if (!notNull_Para(i_value)) {
	        return false;
	    }

	    re = new RegExp("[^0-9.]");
	    var s;
	    if (s = i_value.match(re)) {
	        // alert("'"+i_field+"' 中含有非法字符 '"+s+"' ！");
	        return false;
	    }
	    return true;
	}

	// -------------------------------
	// 函数名：notNull(i_value)
	// 功能介绍：检查输入是否为非空
	// 参数说明：数据项，输入的对应值
	// 返回值 ：1-非空,0-为空
	// -------------------------------
	function notNull_Para(i_value) {
	    if (i_value == "" || i_value == null) {
	        // alert("'"+i_field+"' 不可为空！");
	        return false;
	    }
	    return true;
	}
		 function getcsyshz(valuestr){
				if(valuestr.indexOf('白')>-1){
					return '白'
				}
				if(valuestr.indexOf('灰')>-1){
					return '灰'
				}
				if(valuestr.indexOf('黄')>-1){
					return '黄'
				}
				if(valuestr.indexOf('粉')>-1){
					return '粉'
				}
				if(valuestr.indexOf('红')>-1){
					return '红'
				}
				if(valuestr.indexOf('紫')>-1){
					return '紫'
				}
				if(valuestr.indexOf('绿')>-1){
					return '绿'
				}
				if(valuestr.indexOf('蓝')>-1){
					return '蓝'
				}
				
				if(valuestr.indexOf('棕')>-1){
					return '棕'
				}
				if(valuestr.indexOf('黑')>-1){
					return '黑'
				}
				if(valuestr.indexOf('银')>-1){
					return '灰'
				}
				if(valuestr.indexOf('其他')>-1){
					return '其他'
				}
				
			}
		
		 function getrlzl(nlzl){
				
				if(nlzl=="A"){
					return '汽油'
				}
				if(nlzl=="B"){
					return '柴油'
				}
				if(nlzl=="C"){
					return '电'
				}
				if(nlzl=="D"){
					return '混合油	'
				}
				if(nlzl=="E"){
					return '天然气'
				}
				if(nlzl=="F"){
					return '液化石油气'
				}
				if(nlzl=="L"){
					return '甲醇'
				}
				if(valuestr=="M"){
					return '乙醇'
				}
				if(nlzl=="N"){
					return '太阳能'
				}
				if(nlzl=="Y"){
					return '无'
				}
				if(nlzl=="Z"){
					return '其他'
				}
				
			}
		
		 
		

	