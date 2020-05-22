
function refreshProgress() {
	$("#uploadbox").hide();
	$("#progressBar").show();
	$.get("/front/member/getProgressBar", function(msg) {
		if(msg!=""){
			var j = eval('(' + msg + ')');
			var fileIndex = j.fileIndex;
			var progressPercent = Math.ceil((j.bytesRead / j.totalSize) * 100);
			if(j.status=="copy"){
				$('#progressBarText').html('传输: ' + progressPercent + '%');
			}else{
				$('#progressBarText').html('已上传: ' + progressPercent + '%');
			}
			
			$("#progressBarBoxContent").css("width",
					parseInt(progressPercent * 3.5) + "px");
			
			if (j.isInProgress == 0) {
				window.setTimeout("refreshProgress()", 1000);
			}
			if(j.status=="copy"){
				$("#progressBarBoxContent").css("background",
					"red");
				window.setTimeout("refreshProgress()", 1000);
			}
		}else{
			window.setTimeout("refreshProgress()", 1000);
		}
	});
}


function startProgress() {
	
	if($("#uploadfile").val()==""){
		alert("请选择文件(支持：bmp,jpg,png)");
		return false;
	}
	var file=$.trim($("#uploadfile").val());
	var validateFormat = new Array("bmp","jpg","png");
	var fileFormat = file.substring(file.lastIndexOf(".")+1).toLowerCase();
	var validate = false;
	for (var i = 0; i < validateFormat.length ; i++){
		if(fileFormat == validateFormat[i]){
			validate = true; break;
		}
	}
	if (validate == false){
		alert("请上传正确的文件格式(支持：bmp,jpg,jpeg,png)"); 
		return true;
	}
	
	$("#uploadbox").hide();
	$("#progressBarText").html('上传中...');
	window.setTimeout("refreshProgress()", 1);
	return true;
}
