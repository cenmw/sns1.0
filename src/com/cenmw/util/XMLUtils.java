package com.cenmw.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * xml文件操作类 主要演示为主,方便快速应用到项目开发中 需要借助dom4j.jar包
 * 
 * @author LiangJiChao 下午02:39:33
 */
public class XMLUtils {
	public Document createDocument() {
		Document doc = DocumentHelper.createDocument();// 创建一个空白的xml文档
		Element root = doc.addElement("root");// 创建根节点 root
		root.addAttribute("imageWidth", "680");
		root.addAttribute("imageHeight", "345");
		Element peoper = root.addElement("peoper");//根节点root 下添加peoper节点
		peoper.addAttribute("id", "1");//peoper节点添加id=1属性
		peoper.addElement("name").setText("小明");//peoper节点添加元素 name
		peoper.addElement("age").setText("12");
		peoper.addElement("sex").setText("小男孩");
		peoper.addElement("info").add(DocumentHelper.createCDATA("<script></script>"));//添加解析器忽略形式的文本信息
		Element peoper1 = root.addElement("peoper");
		peoper1.addAttribute("id", "2");
		peoper1.addElement("name").setText("小红");
		peoper1.addElement("age").setText("11");
		peoper1.addElement("sex").setText("小女孩");
		peoper1.addElement("info").add(DocumentHelper.createCDATA("<%%>"));
		return doc;
	}

	public void writeDocument(Document doc) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();// 美化格式
		// OutputFormat format = OutputFormat.createCompactFormat();//缩减格式
		XMLWriter writer = new XMLWriter(System.out, format);// 屏幕输入xml内容
		writer.write(doc);
	}

	public static void writeStoreDocument(Document doc,String outfile) throws IOException {
		OutputFormat format = OutputFormat.createPrettyPrint();// 美化格式
		// OutputFormat format = OutputFormat.createCompactFormat();//缩减格式
		XMLWriter writer = new XMLWriter(
				new FileWriter(new File(outfile)), format);// 写入到文件
		doc.setXMLEncoding("GBK");
		writer.write(doc);
		writer.close();
	} 

//	public static void main(String args[]) {
//		XMLUtils x = new XMLUtils();
//		try {
//			x.writeStoreDocument(x.createDocument(),"d:\\bbs.xml");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	public static void main(String args[]){
		String outpath = "I:\\webapp\\workspace\\100cenmw\\WebRoot\\bmall\\xml\\bbs.xml";
		Document doc = DocumentHelper.createDocument();// 创建一个空白的xml文档
		Element root = doc.addElement("root");// 创建根节点 root
		root.addAttribute("imageWidth", "680");
		root.addAttribute("imageHeight", "345");
		Element menu = root.addElement("menu");//根节点root 下添加menu节点
		menu.addAttribute("url", "百度");
		menu.addAttribute("titleStr", "===蓝天");
		try {
			XMLUtils.writeStoreDocument(doc,outpath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
