/* Demo Note:  This demo uses a FileProgress class that handles the UI for displaying the file name and percent complete.
The FileProgress class is not part of SWFUpload.
*/


/* **********************
   Event Handlers
   These are my custom event handlers to make my
   web application behave the way I went when SWFUpload
   completes different tasks.  These aren't part of the SWFUpload
   package.  They are part of my application.  Without these none
   of the actions SWFUpload makes will show up in my application.
   ********************** */
function fileQueued(file) {
	try {

	} catch (ex) {
		this.debug(ex);
	}

}

function fileQueueError(file, errorCode, message) {
	try {
//		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
//			alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
//			return;
//		}

//		switch (errorCode) {
//		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
//			break;
//		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
//			break;
//		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
//			break;
//		default:
//			if (file !== null) {
//			}
//			break;
//		}
	} catch (ex) {
        this.debug(ex);
    }
}

function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		
		/* I want auto start the upload and I can do that here */
		if(numFilesQueued==0){
			alert("最多只能上传4张");
		}
		if(numFilesSelected>0){
			this.startUpload();
			var target=this.customSettings.progressTarget;
			$("#"+target).show();
		}
		
	} catch (ex)  {
        this.debug(ex);
	}
}

function uploadStart(file) {
	try {
		/* I don't want to do any file validation or anything,  I'll just update the UI and
		return true to indicate that the upload should start.
		It's important to update the UI here because in Linux no uploadProgress events are called. The best
		we can do is say we are uploading.
		 */
		var target=this.customSettings.progressTarget;
		$("#"+target).append('<div class="progressWrapper"><div class="p_fname">'+file.name+'</div><div class="progressContainer"><div id="'+file.id+'" class="progressBarInProgress"></div></div></div>');
	}
	catch (ex) {}
	
	return true;
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
		if(percent==100){
			percent=99;
		}
		$("#"+file.id).css("width",percent+"%");
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadSuccess(file, serverData) {
	try {
		$("#"+file.id).css("width","100%");
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadError(file, errorCode, message) {
	try {

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			break;
		default:
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function uploadComplete(file) {
	
}

// This event comes from the Queue Plugin
function queueComplete(numFilesUploaded) {
	var target=this.customSettings.progressTarget;
	$("#"+target).hide();
}
