/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	
	config.language = 'zh-cn';
	config.uiColor = '#E4E8EF';
	config.toolbar =
[
	['Font','FontSize','-','TextColor','BGColor','-'],
	['Bold','Italic','Underline','-'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-'],
	['Undo','Redo','-','Link','Unlink','-','Image','Flash','Smiley','-','Source']
];
	config.uploadTab = false;
	config.resize_enabled = false;
	config.image_previewText = "";
	
	/**上传 begin**/
	
//	alert( CKEDITOR.instances.introduce.element.getAttribute('class')); 
	//alert( CKEDITOR.document.getBody().getName() );
	var obj=CKEDITOR.document.getBody();
	var id=0;
	if(obj['$'].getElementsByTagName("input")!=null&&obj['$'].getElementsByTagName("input").length>0){
		id=obj['$'].getElementsByTagName("input")[0].value;
	}
	var foldername="regionnote";
	config.filebrowserImageBrowseUrl = "/ckeditor/showImages?id="+id+"&foldername="+foldername;
	config.filebrowserImageUploadUrl = "/ckeditor/uploadImage?id="+id+"&foldername="+foldername;
	
	config.filebrowserFlashBrowseUrl = "/ckeditor/showFlashs?id="+id+"&foldername="+foldername;
	config.filebrowserFlashUploadUrl = "/ckeditor/uploadFlash?id="+id+"&foldername="+foldername;
	
//	config.filebrowserBrowseUrl = "common/ckeditor/uploader/browse.jsp";
//	config.filebrowserUploadUrl =  "common/ckeditor/uploader/upload.jsp ";
//	
	
	
	
	config.filebrowserWindowWidth = "640";
	config.filebrowserWindowHeight = "480";
	
	/**上传 end**/
	
	//工具栏默认是否展开
//    config.toolbarStartupExpanded = true;
//	config.skin='office2003';
	
};
