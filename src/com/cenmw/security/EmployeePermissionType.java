package com.cenmw.security;

public enum EmployeePermissionType {
	ADDCONTENT, //添加基础信息
	DeleteCONTENT, //删除基础信息 
	UPDATECONTENT, //修改基础信息
	VIEWCONTENT, //访问基础信息
	ADDLEARN, //添加学习中心
	DeleteLEARN, //删除学习中心 
	UPDATELEARN, //修改学习中心
	VIEWLEARN, //访问学习中心
	ADDLEARNCLASS, //添加学习分类
	DeleteLEARNCLASS, //删除学习分类 
	UPDATELEARNCLASS, //修改学习分类
	VIEWLEARNCLASS, //访问学习分类
	ADDVEDIOCLASS, //添加视频分类
	DeleteVEDIOCLASS, //删除视频分类 
	UPDATEVEDIOCLASS, //修改视频分类
	VIEWVEDIOCLASS, //访问视频分类
	ADDVEDIO, //添加视频
	DeleteVEDIO, //删除视频 
	UPDATEVEDIO, //修改视频
	VIEWVEDIO, //访问视频
	ADDCONSULTCLASS, //添加咨询分类
	DeleteCONSULTCLASS, //删除咨询分类 
	UPDATECONSULTCLASS, //修改咨询分类
	VIEWCONSULTCLASS, //访问咨询分类
	ADDCONSULT, //添加咨询
	DeleteCONSULT, //删除咨询 
	UPDATECONSULT, //修改咨询
	VIEWCONSULT, //访问咨询
	ADDCONSULTREPLY, //添加咨询认可
	ADDLABORCLASS, //添加任务分类
	DeleteLABORCLASS, //删除任务分类 
	UPDATELABORCLASS, //修改任务分类
	VIEWLABORCLASS, //访问任务分类
	ADDLABOR, //添加任务
	DeleteLABOR, //删除任务 
	UPDATELABOR, //修改任务
	VIEWLABOR, //访问任务
	ADDLABORREPLY, //添加任务认可
	ADDINTEGRAL, //添加积分
	DeleteINTEGRAL, //删除积分 
	UPDATEINTEGRAL, //修改积分
	VIEWINTEGRAL, //访问积分
	ADDCOMMENT, //添加评论
	DeleteCOMMENT, //删除评论 
	UPDATECOMMENT, //修改评论
	VIEWCOMMENT, //访问评论
	ADDCOMMENTIP, //添加评论屏蔽ip
	DeleteCOMMENTIP, //删除评论屏蔽ip 
	UPDATECOMMENTIP, //修改评论屏蔽ip
	VIEWCOMMENTIP, //访问评论屏蔽ip
	ADDCOMMENTWORD, //添加评论屏蔽字
	DeleteCOMMENTWORD, //删除评论屏蔽字 
	UPDATECOMMENTWORD, //修改评论屏蔽字
	VIEWCOMMENTWORD, //访问评论屏蔽字
	ADDMEMBER, //添加会员
	DeleteMEMBER, //删除会员 
	UPDATEMEMBER, //修改会员
	VIEWMEMBER, //访问会员
	ADDMEMBERMOOD, //添加会员心情
	DeleteMEMBERMOOD, //删除会员心情 
	UPDATEMEMBERMOOD, //修改会员心情
	VIEWMEMBERMOOD, //访问会员心情
	ADDMEMBERBLOG, //添加会员日志
	DeleteMEMBERBLOG, //删除会员日志 
	UPDATEMEMBERBLOG, //修改会员日志
	VIEWMEMBERBLOG, //访问会员日志
	ADDMEMBERCONTENT, //添加会员文章
	DeleteMEMBERCONTENT, //删除会员文章 
	UPDATEMEMBERCONTENT, //修改会员文章
	VIEWMEMBERCONTENT, //访问会员文章
	ADDMEMBERPHOTO, //添加会员相片
	DeleteMEMBERPHOTO, //删除会员相片 
	UPDATEMEMBERPHOTO, //修改会员相片
	VIEWMEMBERPHOTO, //访问会员相片
	ADDMEMBERVEDIO, //添加会员视频
	DeleteMEMBERVEDIO, //删除会员视频 
	UPDATEMEMBERVEDIO, //修改会员视频
	VIEWMEMBERVEDIO, //访问会员视频
	ADDMEMBERLABOR, //添加会员任务
	DeleteMEMBERLABOR, //删除会员任务 
	UPDATEMEMBERLABOR, //修改会员任务
	VIEWMEMBERLABOR, //访问会员任务
	ADDMEMBERCOLLECTION, //添加会员收藏
	DeleteMEMBERCOLLECTION, //删除会员收藏 
	UPDATEMEMBERCOLLECTION, //修改会员收藏
	VIEWMEMBERCOLLECTION, //访问会员收藏
	ADDMEMBERMESSAGE, //添加会员私信
	DeleteMEMBERMESSAGE, //删除会员私信 
	UPDATEMEMBERMESSAGE, //修改会员私信
	VIEWMEMBERMESSAGE, //访问会员私信
	ADDMEMBERREPORT, //添加会员举报
	DeleteMEMBERREPORT, //删除会员举报 
	UPDATEMEMBERREPORT, //修改会员举报
	VIEWMEMBERREPORT, //访问会员举报
	ADDMEMBERPOLICE, //添加会员警察局
	DeleteMEMBERPOLICE, //删除会员警察局 
	UPDATEMEMBERPOLICE, //修改会员警察局
	VIEWMEMBERPOLICE, //访问会员警察局
	ADDMEMBERPOLICESF, //添加会员释放
	DeleteMEMBERPOLICESF, //删除会员释放 
	UPDATEMEMBERPOLICESF, //修改会员释放
	VIEWMEMBERPOLICESF, //访问会员释放
	ADDMEMBERLOG, //添加会员访问日志
	DeleteMEMBERLOG, //删除会员访问日志 
	UPDATEMEMBERLOG, //修改会员访问日志
	VIEWMEMBERLOG, //访问会员访问日志
	ADDMEMBERSTATUS, //添加会员动态
	DeleteMEMBERSTATUS, //删除会员动态 
	UPDATEMEMBERSTATUS, //修改会员动态
	VIEWMEMBERSTATUS, //访问会员动态
	ADDMEMBERPRAISE, //添加会员赞
	DeleteMEMBERPRAISE, //删除会员赞 
	UPDATEMEMBERPRAISE, //修改会员赞
	VIEWMEMBERPRAISE, //访问会员赞
	ADDMEMBERFRIEND, //添加会员好友
	DeleteMEMBERFRIEND, //删除会员好友 
	UPDATEMEMBERFRIEND, //修改会员好友
	VIEWMEMBERFRIEND, //访问会员好友
	ADDMEMBERBLOGCLASS, //添加会员日志分类
	DeleteMEMBERBLOGCLASS, //删除会员日志分类 
	UPDATEMEMBERBLOGCLASS, //修改会员日志分类
	VIEWMEMBERBLOGCLASS, //访问会员日志分类
	ADDMEMBERCONTENTCLASS, //添加会员文章分类
	DeleteMEMBERCONTENTCLASS, //删除会员文章 分类
	UPDATEMEMBERCONTENTCLASS, //修改会员文章分类
	VIEWMEMBERCONTENTCLASS, //访问会员文章分类
	ADDMEMBERPHOTOGROUP, //添加会员相片分组
	DeleteMEMBERPHOTOGROUP, //删除会员相片分组 
	UPDATEMEMBERPHOTOGROUP, //修改会员相片分组
	VIEWMEMBERPHOTOGROUP, //访问会员相片分组
	ADDMEMBERFRIENDCLASS, //添加会员好友分组
	DeleteMEMBERFRIENDCLASS, //删除会员好友分组 
	UPDATEMEMBERFRIENDCLASS, //修改会员好友分组
	VIEWMEMBERFRIENDCLASS, //访问会员好友分组
	ADDMEMBERVEDIOCLASS, //添加会员视频分组
	DeleteMEMBERVEDIOCLASS, //删除会员视频分组
	UPDATEMEMBERVEDIOCLASS, //修改会员视频分组
	VIEWMEMBERVEDIOCLASS, //访问会员视频分组
	ADDINTEGRALCONFIG, //添加积分设置
	UPDATEINTEGRALCONFIG, //修改积分设置
	ADDTOPIC, //添加测试中心
	DeleteTOPIC, //删除测试中心 
	UPDATETOPIC, //修改测试中心
	VIEWTOPIC, //访问测试中心
	ADDTOPICCLASS, //添加测试分类
	DeleteTOPICCLASS, //删除测试分类 
	UPDATETOPICCLASS, //修改测试分类
	VIEWTOPICCLASS, //访问测试分类
}