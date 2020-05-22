// JavaScript Document
　　function scalePhoto(imgId,width,height){
　　var img = document.getElementById(imgId);
　　if(img != null){
　　var imgWidth = img.width;
　　var imgHeight = img.height;
　　if(imgWidth>width&&imgHeight>height){
　　//both image's width and height are larger than required one
　　var widthRate = imgWidth/width;
　　var heightRate = imgHeight/height;
　　if(widthRate>heightRate){
　　//use width to retrieve the image
　　img.width=width;
　　img.height=imgHeight*(width/imgWidth);
　　}else{
　　img.height=height;
　　img.width=imgWidth*(height/imgHeight);
　　}
　　}
　　else if(imgWidth>width){
　　//the image width lg than the required width
　　img.width=width;
　　img.height=imgHeight*(width/imgWidth);
　　}
　　else if(imgHeight>height){
　　//the image height lg than the required
　　img.height=height;
　　img.width=imgWidth*(height/imgHeight);
　　}
　　}
　　} 