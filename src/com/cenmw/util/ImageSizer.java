package com.cenmw.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图像压缩工具
 * 
 * @author
 * 
 */
public class ImageSizer {
	/**
	 * 压缩图片方法
	 * 
	 * @param oldFile
	 *            将要压缩的图片
	 * @param width
	 *            压缩宽
	 * @param height
	 *            压缩高
	 * @param quality
	 *            压缩清晰度 建议为1.0
	 * @param smallIcon
	 *            压缩图片后,添加的扩展名（在图片后缀名前添加）
	 * @param percentage
	 *            是否等比压缩 若true宽高比率将将自动调整
	 * @author 梁吉超
	 * @return 如果处理正确返回压缩后的文件名 null则参数可能有误
	 */

	public static String doCompress(String oldFile, int width, int height,
			float quality, String smallIcon, boolean percentage) {
		if (oldFile != null && width > 0 && height > 0) {
			String newImage = null;
			try {
				File file = new File(oldFile);
				// 文件不存在
				if (!file.exists()) {
					System.out.println(file.exists());
					return null;
				}
				/* 读取图片信息 */
				Image srcFile = ImageIO.read(file);
				int new_w = width;
				int new_h = height;
				if (percentage) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) srcFile.getWidth(null))
							/ (double) width + 0.1;
					double rate2 = ((double) srcFile.getHeight(null))
							/ (double) height + 0.1;
					double rate = rate1 > rate2 ? rate1 : rate2;
					new_w = (int) ((srcFile.getWidth(null)) / rate);
					new_h = (int) ((srcFile.getHeight(null)) / rate);
				}
				/* 宽高设定 */
				BufferedImage tag = new BufferedImage(new_w, new_h,
						BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);

				/* 压缩后的文件名 */
				String filePrex = oldFile
						.substring(0, oldFile.lastIndexOf('.'));
				newImage = filePrex + smallIcon
						+ oldFile.substring(filePrex.length());

				/* 压缩之后临时存放位置 */
				FileOutputStream out = new FileOutputStream(newImage);

				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);

				/* 压缩质量 */
				jep.setQuality(quality, true);
				encoder.encode(tag, jep);

				out.close();
				srcFile.flush();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return newImage;
		} else {
			return null;
		}
	}

	// 测试
	public static void main(String str[]) {
		System.out.println(ImageSizer.doCompress(
				"C:\\Users\\Administrator\\Pictures\\wallpaper_04.jpg", 393,
				244, 1.0f, "_small", false));
		System.out.print("ok…");
	}

}