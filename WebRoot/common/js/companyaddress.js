
  /**companyaddressdata begin**/
var companyaddressdataone=new Array();
var companyaddressdatatwo=new Array();
var companyaddressdatathree=new Array();
companyaddressdataone.push("黑龙江省");
companyaddressdataone.push("北京市");
companyaddressdataone.push("浙江省");

companyaddressdatatwo.push("黑龙江省×鸡西市");
companyaddressdatatwo.push("黑龙江省×大兴安岭");
companyaddressdatatwo.push("黑龙江省×黑河市");
companyaddressdatatwo.push("北京市×崇文区");
companyaddressdatatwo.push("北京市×海滨区");
companyaddressdatatwo.push("浙江省×舟山市");
companyaddressdatatwo.push("浙江省×丽水市");

companyaddressdatathree.push("鸡西市×滴道区");
companyaddressdatathree.push("鸡西市×鸡冠区");
companyaddressdatathree.push("鸡西市×鸡东县");
companyaddressdatathree.push("鸡西市×麻山区");
companyaddressdatathree.push("鸡西市×城子河区");
companyaddressdatathree.push("大兴安岭×");
companyaddressdatathree.push("黑河市×嫩江县");
companyaddressdatathree.push("黑河市×孙吴县");
companyaddressdatathree.push("崇文区×");
companyaddressdatathree.push("海滨区×");
companyaddressdatathree.push("舟山市×岱山县");
companyaddressdatathree.push("舟山市×定海区");
companyaddressdatathree.push("丽水市×松阳县");
companyaddressdatathree.push("丽水市×青田县");
 /**companyaddressdata end**/
 /**companyaddressdata function begin**/
function getCompanyAddressTwo(companyaddressdata,onevalue){
	var two=new Array();
	if(companyaddressdata!=null&&companyaddressdata.length>0){
		for(var i=0;i<companyaddressdata.length;i++){
			var tempArr=companyaddressdata[i].split("×");
			if(tempArr[0]==onevalue){
				if(tempArr[1]!=""){
					two.push(tempArr[1]);
				}
			}
		}
	}
	return two;
}
function getCompanyAddressThree(companyaddressdata,twovalue){
	var three=new Array();
	if(companyaddressdata!=null&&companyaddressdata.length>0){
		for(var i=0;i<companyaddressdata.length;i++){
			var tempArr=companyaddressdata[i].split("×");
			if(tempArr[0]==twovalue){
				if(tempArr[1]!=""){
					three.push(tempArr[1]);
				}
			}
		}
	}
	return three;
}
function showCompanyAddressOne(id){
	var selectObj=document.getElementById(id);
	if(selectObj!=null){
		for(var i=0;i<companyaddressdataone.length;i++){
			selectObj.options.add(new Option(companyaddressdataone[i],companyaddressdataone[i]));
		}
	}
}
function showCompanyAddressTwo(obj,id,tid){
	if(obj!=null){
		var value=obj.options[obj.selectedIndex].value;
		var twoarr=getCompanyAddressTwo(companyaddressdatatwo,value);
		var selectObj=document.getElementById(id);
		var tobj=document.getElementById(tid);
		if(tobj!=null&&tobj.length>1){
			tobj.length=1;
		}
		if(selectObj!=null&&selectObj.length>1){
				selectObj.length=1;
		}
		if(selectObj!=null&&value!=""){
			for(var i=0;i<twoarr.length;i++){
				selectObj.options.add(new Option(twoarr[i],twoarr[i]));
			}
		}
	}
}
function showCompanyAddressThree(obj,id){
	if(obj!=null){
		var value=obj.options[obj.selectedIndex].value;
		var threearr=getCompanyAddressThree(companyaddressdatathree,value);
		var selectObj=document.getElementById(id);
		if(selectObj!=null&&selectObj.length>1){
			selectObj.length=1;
		}
		if(selectObj!=null&&value!=""){
			for(var i=0;i<threearr.length;i++){
				selectObj.options.add(new Option(threearr[i],threearr[i]));
			}	
		}
	}
}
function setCompanyAddressValue(oneid,twoid,threeid,value){
	var oneObj=document.getElementById(oneid);
	var twoObj=document.getElementById(twoid);
	var threeObj=document.getElementById(threeid);
	
	if(oneObj!=null&&twoObj!=null&&threeObj!=null){
		if(value!=""){
			var oneval=value.split(",")[0];
			var twoval=value.split(",")[1];
			var threeval=value.split(",")[2];
			if(oneObj!=null&&oneObj.length>1&&oneval!=""){
				oneObj.length=1;
			}
			if(oneval!=""){
				var one=document.getElementById(oneid);
				showCompanyAddressOne(oneid);
				if(one!=null&&one.length>1){
					
					for(var i=0;i<one.length;i++){
						if(one.options[i].value==oneval){
							one.options[i].selected=true;
						}
					}
				}
				
				var two=document.getElementById(twoid);
				showCompanyAddressTwo(one,twoid,threeid);
				if(two!=null&&two.length>1){
					
					for(var i=0;i<two.length;i++){
						if(two.options[i].value=twoval){
							two.options[i].selected=true;
						}
					}
				}
				var three=document.getElementById(threeid);
				showCompanyAddressThree(two,threeid);
				if(three!=null&&three.length>1){
					for(var i=0;i<three.length;i++){
						if(three.options[i].value=threeval){
							three.options[i].selected=true;
						}
					}
				}
			}
		}
	}
}
/**
 * <s:hidden name="memberInfo.companyaddress" id="memberInfo.companyaddress"></s:hidden>
   		<select name="province" id="province" onChange="showCompanyAddressTwo(this,'eparchy','city')">
          <option value="">请选择</option>
        </select>
        <script>
        showCompanyAddressOne("province");
        </script>
        <select name="eparchy" id="eparchy" onChange="showCompanyAddressThree(this,'city')">
          <option value="">请选择</option>
        </select>
        <select name="city" id="city">
          <option value="">请选择</option>
        </select>
        <script>
        setCompanyAddressValue("province","eparchy","city","<s:property value="memberInfo.companyaddress" escape="false"/>");
        </script>
 * **/
 /**companyaddressdata function end**/
