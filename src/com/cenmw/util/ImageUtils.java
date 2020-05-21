package com.cenmw.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {

	private static final String TEXT_TITLE = "百里挑一网";

	public static final String IMG_TOP_LEFT = "IMG_UPPER_LEFT";
	public static final String IMG_BOTTOM_LEFT = "IMG_BOTTOM_LEFT";
	public static final String IMG_TOP_RIGHT = "IMG_TOP_RIGHT";
	public static final String IMG_BOTTOM_RIGHT = "IMG_BOTTOM_RIGHT";
	public static final String IMG_CENTER = "IMG_CENTER";
	public static final String IMG_TOP_CENTER = "IMG_TOP_CENTER";
	public static final String IMG_BOTTOM_CENTER = "IMG_BOTTOM_CENTER";
	public static final String IMG_LEFT_CENTER = "IMG_LEFT_CENTER";
	public static final String IMG_RIGHT_CENTER = "IMG_RIGHT_CENTER";

	/**
	 * 添加文字水印
	 * 
	 * @param targetFile
	 *            --目标图片
	 * @param fontName
	 *            --水印文字
	 * @param fontStyle
	 *            --字体样式 Font.BLOD
	 * @param color
	 *            --字体颜色
	 * @param fontSize
	 *            --字体大小
	 * @param x
	 *            --X轴偏移量
	 * @param y
	 *            --Y轴偏移量
	 * @throws Exception
	 */
	public static void waterMarkText(File targetFile, String fontName,
			int fontStyle, Color color, int fontSize, int x, int y)
			throws Exception {
		try {
			// 创建一个Image对象并以源图片数据流填充
			Image src = ImageIO.read(targetFile);

			// 得到源图宽
			int width = src.getWidth(null);
			// 得到源图高
			int height = src.getHeight(null);

			// 创建一个BufferedImage来作为图像操作容器
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			// 创建一个绘图环境来进行绘制图像
			Graphics2D g = image.createGraphics();

			// 将源图像数据流载入这个BufferedImage
			g.drawImage(src, 0, 0, width, height, null);

			// 设定文本字体
			g.setFont(new Font("宋体", fontStyle, fontSize));

			String rand = TEXT_TITLE;
			if (fontName != null && fontName.trim().length() > 0) {
				rand = fontName;
			}
			// 设定文本颜色
			g.setColor(color);

			// 设置透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					0.5f));

			// 向BufferedImage写入文本字符，水印在图片上的坐标
			g.drawString(rand, x, y);

			// 使更改生效
			g.dispose();
			// 创建输出文件流
			FileOutputStream outi = new FileOutputStream(targetFile);

			// 创建JPEG编码对象
			JPEGImageEncoder encodera = JPEGCodec.createJPEGEncoder(outi);

			// 对这个BufferedImage(image)进行JPEG编码
			encodera.encode(image);
			// 关闭输出文件流
			outi.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	/**
	 * 添加图片水印
	 * 
	 * @param imgFile
	 *            --图片源文件
	 * @param waterFile
	 *            --水印图片
	 * @param alpha
	 *            --水印图片透明度
	 * @throws Exception
	 */
	public static void waterMarkImage(File imgFile, File waterFile,
			String position, float alpha) throws Exception {
		try {
			// 目标文件
			Image src = ImageIO.read(imgFile);
			int width = src.getWidth(null);
			int height = src.getHeight(null);

			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			// 水印文件路径
			Image waterImg = ImageIO.read(waterFile);
			int w_width = waterImg.getWidth(null);
			int w_height = waterImg.getHeight(null);

			// 设置水印图片的透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));

			// 偏移量
			int x = 0;
			int y = 0;
			if (position.equals("IMG_TOP_LEFT")) {
				x = 0;
				y = 0;
			} else if (position.equals("IMG_TOP_RIGHT")) {
				x = width - w_width;
				y = 0;
			} else if (position.equals("IMG_BOTTOM_LEFT")) {
				x = 0;
				y = height - w_height;
			} else if (position.equals("IMG_BOTTOM_RIGHT")) {
				x = width - w_width;
				y = height - w_height;
			} else if (position.equals("IMG_CENTER")) {
				x = width / 2 - w_width / 2;
				y = height / 2 - w_height / 2;
			} else if (position.equals("IMG_TOP_CENTER")) {
				x = width / 2 - w_width / 2;
				y = 0;
			} else if (position.equals("IMG_LEFT_CENTER")) {
				x = 0;
				y = height / 2 - w_height / 2;
			} else if (position.equals("IMG_RIGHT_CENTER")) {
				x = width - w_width;
				y = height / 2 - w_height / 2;
			} else if (position.equals("IMG_BOTTOM_CENTER")) {
				x = width / 2 - w_width / 2;
				y = height - w_height;
			}

			g.drawImage(waterImg, x, y, w_width, w_height, null);

			// 水印文件结束

			g.dispose();
			FileOutputStream out = new FileOutputStream(imgFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		File file = new File("D:/demo.jpg");
		File waterFile = new File("D:/shuiyin.png");
		// waterMarkText(file, "水印,大花", Font.BOLD, Color.blue, 28, 0, 28);
		waterMarkImage(file, waterFile, ImageUtils.IMG_BOTTOM_CENTER, 0.5f);
	}

}
