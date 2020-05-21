package com.cenmw.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.Date;

import magick.CompositeOperator;
import magick.CompressionType;
import magick.DrawInfo;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;
import magick.PreviewType;

public class ImageHandle {

	static {
		System.setProperty("jmagick.systemclassloader", "no");
	}

	/**
	 * 压缩图片(先压后切)
	 * 
	 * @param filePath
	 *            源文件路径
	 * @param toPath
	 *            缩略图路径
	 * @param toPath
	 *            压缩后的宽度
	 * @param toPath
	 *            压缩后的高度
	 */
	public static void createThumbnail(String filePath, String toPath, int w,
			int h) {
		ImageInfo info = null;
		MagickImage image = null;
		Dimension imageDim = null;
		MagickImage scaled = null;
		int wideth = 0;
		int height = 0;
		try {
			info = new ImageInfo(filePath);
			image = new MagickImage(info);
			imageDim = image.getDimension();
			wideth = imageDim.width;
			height = imageDim.height;

			// 得到压缩后的图片宽度和高度
			int[] w_h = getW_H(wideth, height, w, h);
			wideth = w_h[0];
			height = w_h[1];

			scaled = image.scaleImage(wideth, height);// 小图片文件的大小.

			scaled.setFileName(toPath);
			scaled.writeImage(info);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scaled != null) {
				scaled.destroyImages();
			}
		}
		// 切图

		// 切图的x、y坐标

		int x = (wideth - w) / 2;
		int y = (height - h) / 2;
		cutImg(toPath, toPath, w, h, x, y);

	}

	/**
	 * 切图
	 * 
	 * @param imgPath
	 *            源图路径
	 * @param toPath
	 *            修改图路径
	 * @param w
	 *            宽度
	 * @param h
	 *            高度
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 */
	public static void cutImg(String imgPath, String toPath, int w, int h,
			int x, int y) {
		ImageInfo infoS = null;
		MagickImage image = null;
		MagickImage cropped = null;
		Rectangle rect = null;
		try {
			infoS = new ImageInfo(imgPath);
			image = new MagickImage(infoS);
			rect = new Rectangle(x, y, w, h);
			cropped = image.cropImage(rect);
			cropped.setFileName(toPath);
			cropped.writeImage(infoS);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
	}

	/**
	 * 水印(图片logo)
	 * 
	 * @param filePath
	 *            源文件路径
	 * @param toImg
	 *            修改图路径
	 * @param logoPath
	 *            logo图路径
	 * @throws MagickException
	 */
	public static void initLogoImg(String filePath, String toImg,
			String logoPath) throws MagickException {
		ImageInfo info = new ImageInfo();
		MagickImage fImage = null;
		MagickImage sImage = null;
		MagickImage fLogo = null;
		MagickImage sLogo = null;
		Dimension imageDim = null;
		Dimension logoDim = null;
		try {
			fImage = new MagickImage(new ImageInfo(filePath));
			imageDim = fImage.getDimension();
			int width = imageDim.width;
			int height = imageDim.height;
			if (width > 660) {
				height = 660 * height / width;
				width = 660;
			}
			sImage = fImage.scaleImage(width, height);

			fLogo = new MagickImage(new ImageInfo(logoPath));
			logoDim = fLogo.getDimension();
			int lw = width / 8;
			int lh = logoDim.height * lw / logoDim.width;
			sLogo = fLogo.scaleImage(lw, lh);

			sImage.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,
					width - (lw + lh / 10), height - (lh + lh / 10));
			sImage.setFileName(toImg);
			sImage.writeImage(info);
		} finally {
			if (sImage != null) {
				sImage.destroyImages();
			}
		}
	}

	/**
	 * 图片缩放
	 * 
	 * @param imgPath
	 *            原图片路径
	 * @param toPath
	 *            缩放后的图片路径
	 * @param w
	 *            缩放后图片宽度
	 * @param h
	 *            缩放后图片高度
	 */

	public static void zoomImg(String imgPath, String toPath, int w, int h)
			throws Exception {
		ImageInfo info = new ImageInfo(imgPath);
		MagickImage image = new MagickImage(info);
		MagickImage scaleImg = image.scaleImage(w, h);
		scaleImg.setFileName(toPath);
		scaleImg.writeImage(info);
	}

	/**
	 * 水印(文字)
	 * 
	 * @param filePath
	 *            源文件路径
	 * @param toImg
	 *            修改图路径
	 * @param text
	 *            名字(文字内容自己随意)
	 * @throws MagickException
	 */
	public static void initTextToImg(String filePath, String toImg, String text)
			throws MagickException {
		ImageInfo info = new ImageInfo(filePath);
		if (filePath.toUpperCase().endsWith("JPG")
				|| filePath.toUpperCase().endsWith("JPEG")) {
			info.setCompression(CompressionType.JPEGCompression); // 压缩类别为JPEG格式
			info.setPreviewType(PreviewType.JPEGPreview); // 预览格式为JPEG格式
			info.setQuality(95);
		}
		MagickImage aImage = new MagickImage(info);
		Dimension imageDim = aImage.getDimension();
		int wideth = imageDim.width;
		int height = imageDim.height;
		if (wideth > 660) {
			height = 660 * height / wideth;
			wideth = 660;
		}
		int a = 0;
		int b = 0;
		String[] as = text.split("");
		for (String string : as) {
			if (string.matches("[\u4E00-\u9FA5]")) {
				a++;
			}
			if (string.matches("[a-zA-Z0-9]")) {
				b++;
			}
		}
		int tl = a * 12 + b * 6 + 300;
		MagickImage scaled = aImage.scaleImage(wideth, height);
		if (wideth > tl && height > 5) {
			DrawInfo aInfo = new DrawInfo(info);
			aInfo.setFill(PixelPacket.queryColorDatabase("white"));
			aInfo.setUnderColor(new PixelPacket(0, 0, 0, 100));
			aInfo.setPointsize(12);
			// 解决中文乱码问题,自己可以去随意定义个自己喜欢字体，我在这用的楷体
			String fontPath = "C:/WINDOWS/Fonts/SIMKAI.TTF";
			// String fontPath = "/usr/maindata/MSYH.TTF";
			aInfo.setFont(fontPath);
			aInfo.setTextAntialias(true);
			aInfo.setOpacity(0);
			aInfo.setText("　" + text + "于　"
					+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())
					+ " 上传至www.228.com.cn");
			aInfo.setGeometry("+" + (wideth - tl) + "+" + (height - 5));
			scaled.annotateImage(aInfo);
		}
		scaled.setFileName(toImg);
		scaled.writeImage(info);
		scaled.destroyImages();
	}

	public static void initImg(String filePath, String toImg)
			throws MagickException {

		ImageInfo info = new ImageInfo(filePath);

		if (filePath.toUpperCase().endsWith("JPG")
				|| filePath.toUpperCase().endsWith("JPEG")) {
			info.setCompression(CompressionType.JPEGCompression); // 压缩类别为JPEG格式
			info.setPreviewType(PreviewType.JPEGPreview); // 预览格式为JPEG格式
			info.setQuality(95);
		}
		MagickImage aImage = new MagickImage(info);
		Dimension imageDim = aImage.getDimension();
		int wideth = imageDim.width;
		int height = imageDim.height;
		if (wideth > 660) {
			height = 660 * height / wideth;
			wideth = 660;
		}

		MagickImage scaled = aImage.scaleImage(wideth, height);

		scaled.setFileName(toImg);
		scaled.writeImage(info);
		scaled.destroyImages();
	}

	public static void newCutImg(String filePath, String toImg)
			throws MagickException {
		ImageInfo info = new ImageInfo(filePath);
		if (filePath.toUpperCase().endsWith("JPG")
				|| filePath.toUpperCase().endsWith("JPEG")) {
			info.setCompression(CompressionType.JPEGCompression); // 压缩类别为JPEG格式
			info.setPreviewType(PreviewType.JPEGPreview); // 预览格式为JPEG格式
			info.setQuality(95);
		}
		MagickImage aImage = new MagickImage(info);
		Dimension imageDim = aImage.getDimension();
		int wideth = imageDim.width;
		int height = imageDim.height;
		if (wideth > 330) {
			height = 330 * height / wideth;
			wideth = 330;
		}

		MagickImage scaled = aImage.scaleImage(wideth, height);

		scaled.setFileName(toImg);
		scaled.writeImage(info);
		scaled.destroyImages();
	}

	/**
	 * 等比压缩的宽、高 计算方法
	 * 
	 * @param wideth
	 *            原图宽度
	 * @param height
	 *            原图高度
	 * @param w
	 *            缩略图宽度
	 * @param h
	 *            缩略图高度
	 * @return int数组 {等比压缩的宽度，等比压缩高度}
	 */
	@SuppressWarnings("unused")
	private static int[] getW_H(int wideth, int height, int w, int h) {

		double w_ = Double.valueOf(w);
		double h_ = Double.valueOf(h);
		double wideth_ = Double.valueOf(wideth);
		double height_ = Double.valueOf(height);

		double b1 = w_ / h_;
		double b2 = wideth_ / height_;

		// System.out.println(b1 + "--" + b2);
		if (b1 < b2) {

			wideth_ = h_ * wideth_ / height_;
			height_ = h_;

		} else if (b1 > b2) {

			height_ = w_ * height_ / wideth_;
			wideth_ = w_;

		} else {

			wideth_ = w_;
			height_ = h_;

		}

		// System.out.println("wideth:" + wideth_);
		// System.out.println("height:" + height_);
		return new int[] { (int) wideth_, (int) height_ };

	}

	public static void main(String[] args) throws Exception {
		String imgPath = "D:\\wallpaper_04.jpg";
		/*
		 * String toPath = "C:\\images\\bg1.jpg"; ImageHandle.cutImg(imgPath,
		 * toPath, 200, 200, 50, 200); toPath = "C:\\images\\bg2.jpg";
		 * ImageHandle.zoomImg(imgPath, toPath, 400, 300);
		 */

		// String toPath = "c:/水印图.jpg";
		// String text = ("dot");
		// ImageHandle.initTextToImg(imgPath, toPath, text);

		/**
		 * 压缩图片
		 * 
		 * @param filePath
		 *            源文件路径
		 * @param toPath
		 *            缩略图路径
		 */
		String toPath1 = "D:\\wallpaper_04_new.jpg";
		createThumbnail(imgPath, toPath1, 112, 112);
	}

}
