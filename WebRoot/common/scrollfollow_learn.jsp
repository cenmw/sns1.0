<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script src="/common/scrollfollow/float.js" type="text/javascript"></script>
<link href="/common/scrollfollow/float.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

$(function(){
	$(".add-collect-to").float({
							   delay : 0,
							   position:"lm",
							   offset : {left : -307},
							   style:{width:334}
							  });
	$(".add-collect-to").hover(function(){
		$(this).float("clearOffset");
	},function(){
		$(this).float("addOffset");
	}) 
});

// 记录鼠标点击的输入框
function clearTitle(obj){
    var inputarr = document.getElementsByName("learntext");
	if(inputarr){
	    for(var i=0;i<inputarr.length;i++){
		   inputarr[i].title = '';
		}
	}
	obj.title='selecttitle';
}

// 将选中的值，填入到输入框中。
function scroll_setText(s_value){
    var inputarr = document.getElementsByName("learntext");
	if(inputarr){
	    for(var i=0;i<inputarr.length;i++){
		   if(inputarr[i].title == 'selecttitle'){		     
			  var o_v = inputarr[i].value;
			  inputarr[i].value = o_v + s_value ;			  
		   }
		}
	}
}

</script>

<div class="add-collect-to clearfix">
    <div class="title">特殊符号</div>
    <div class="float-box-content">
      <div class="msgborad">
          <table width="100%" >          
		     <tr height="18px">
			    <td style="cursor:pointer;" onclick="scroll_setText('+')">＋</td>
				<td style="cursor:pointer;" onclick="scroll_setText('－')">－</td>
				<td style="cursor:pointer;" onclick="scroll_setText('×')">×</td>
				<td style="cursor:pointer;" onclick="scroll_setText('÷')">÷</td>
			 </tr>
			 <tr height="18px">
			    <td style="cursor:pointer;" onclick="scroll_setText('／')">／</td>
				<td style="cursor:pointer;" onclick="scroll_setText('＝')">＝</td>
				<td style="cursor:pointer;" onclick="scroll_setText('≈')">≈</td>
				<td style="cursor:pointer;" onclick="scroll_setText('≠')">≠</td>
			 </tr>
			 <tr height="18px">
			    <td style="cursor:pointer;" onclick="scroll_setText('＜')">＜</td>
				<td style="cursor:pointer;" onclick="scroll_setText('＞')">＞</td>
				<td style="cursor:pointer;" onclick="scroll_setText('≤')">≤</td>
				<td style="cursor:pointer;" onclick="scroll_setText('≥')">≥</td>
			 </tr>			 
			 <tr height="18px">
			    <td style="cursor:pointer;" onclick="scroll_setText('△')">△</td>
				<td style="cursor:pointer;" onclick="scroll_setText('±')">±</td>
				<td style="cursor:pointer;" onclick="scroll_setText('∠')">∠</td>
				<td style="cursor:pointer;" onclick="scroll_setText('∥')">∥</td>
			 </tr>
			 <tr height="18px">
			    <td style="cursor:pointer;" onclick="scroll_setText('√')">√</td>
				<td style="cursor:pointer;" onclick="scroll_setText('α')">α</td>
				<td style="cursor:pointer;" onclick="scroll_setText('β')">β</td>
				<td style="cursor:pointer;" onclick="scroll_setText('π')">π</td>
			 </tr>
		  </table>
      </div>
    </div>
</div>