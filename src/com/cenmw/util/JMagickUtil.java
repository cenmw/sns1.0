package com.cenmw.util;

import magick.CompressionType;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

public class JMagickUtil {
	/**
	 * <p>
	 * 描述：压缩图片质量不改变图片大小
	 * </P>
	 * 
	 * @param imagePath
	 *            图片的路径
	 * @param quality
	 *            图片的压缩比例
	 * @param newImagePath
	 *            新图片的路径
	 * @throws MagickException
	 */
	public static void compression(String imagePath, int quality,
			String newImagePath) throws MagickException {
		// 创建imageInfo对象
		ImageInfo imageInfo = new ImageInfo(imagePath);
		// 设置压缩比例
		imageInfo.setQuality(quality);
		// 读取imageInfo
		MagickImage image = new MagickImage(imageInfo);
		// 设置新图片的路径
		image.setFileName(newImagePath);
		// 执行
		image.writeImage(imageInfo);
		// 销毁
		image.destroyImages();
	}

	/**
	 * <p>
	 * 描述：将图片转换为tiff格式
	 * </P>
	 * 
	 * @param imagePath
	 *            图片的路径
	 * @param newImagePath
	 *            新图片的路径
	 * @throws MagickException
	 */
	public static void converToTIFF(String imagePath, String newImagePath)
			throws MagickException {
		// 创建imageInfo对象
		ImageInfo imageInfo = new ImageInfo(imagePath);
		imageInfo.setMagick("tiff");
		imageInfo.setCompression(CompressionType.ZipCompression); // 设置压缩

		// 读取imageInfo
		MagickImage image = new MagickImage(imageInfo);

		// 设置新图片的路径
		image.setFileName(newImagePath);
		// 执行
		image.writeImage(imageInfo);
		// 销毁
		image.destroyImages();
	}

	public void test() {
		try {
			// MagickImageUtil.compression("c:\\1.jpg", 50, "c:\\2.jpg");
			converToTIFF("c:\\shutterstock_15467920.jpg",
					"c:\\shutterstock_15467920.tif");
		} catch (MagickException e) {
			e.printStackTrace();
		}
	}

	private static boolean genearateSumImg(String orgImg) {
		boolean result = false;
		// 取得原文件
		try {

			MagickImage image = new MagickImage(new ImageInfo(orgImg));
			int scalex = 80;
			int scaley = 80;
			MagickImage small = image.scaleImage(scalex, scaley);
			small.setFileName(orgImg);
			small.writeImage(new ImageInfo());

			result = true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public static void main(String args[]) {
		System.setProperty("jmagick.systemclassloader", "no");
		genearateSumImg("C:\\Users\\Administrator\\Pictures\\wallpaper_04.jpg");
	}

}
