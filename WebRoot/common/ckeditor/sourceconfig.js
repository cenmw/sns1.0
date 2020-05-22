/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language = 'zh-cn';
	config.scayt_autoStartup = false;
	config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;
	config.uiColor = '#E4E8EF';
	config.toolbar =
[
	['Font','FontSize','-','TextColor','BGColor','-'],
	['Bold','Italic','Underline','-'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-'],
	['Undo','Redo','-','Link','Unlink','-','Image','Smiley','-','Source']
];
	config.uploadTab = false;
	config.resize_enabled = false;
	config.image_previewText = " ";
	/**上传 begin**/
	
//	alert( CKEDITOR.instances.introduce.element.getAttribute('class')); 
	//alert( CKEDITOR.document.getBody().getName() );
	var obj=CKEDITOR.document.getBody();
	var id=obj['$'].getElementsByTagName("input")[0].value;
	
	config.filebrowserImageBrowseUrl = "/ckeditor/product/showProductImages?id="+id;
	config.filebrowserImageUploadUrl = "/ckeditor/product/imageUpload?id="+id;
	
//	config.filebrowserBrowseUrl = "common/ckeditor/uploader/browse.jsp";
//	config.filebrowserUploadUrl =  "common/ckeditor/uploader/upload.jsp ";
//	
//	config.filebrowserFlashBrowseUrl = "common/ckeditor/uploader/browse.jsp?type=Flashs";
//	config.filebrowserFlashUploadUrl = "common/ckeditor/uploader/upload.jsp?type=Flashs";
	
	
	config.filebrowserWindowWidth = "640";
	config.filebrowserWindowHeight = "480";
	
	/**上传 end**/
	
	//工具栏默认是否展开
//    config.toolbarStartupExpanded = true;
//	config.skin='office2003';
};
