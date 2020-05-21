package com.cenmw.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cenmw.util.image.GraphicsMagickApp;

public class ImageUtil {
	private static final String WatermarkPath = RootdirectoryPopUtil
			.getPicLocalPath("/common/images/watermark.png");

	private static final int WatermarkAlpha = 50;
	public static final String WatermarkPosition_LEFT = "left";
	public static final String WatermarkPosition_RIGHT = "right";
	public static final String WatermarkPosition_UP = "up";
	public static final String WatermarkPosition_DOWN = "down";
	public static final String WatermarkPosition_LEFTUP = "leftup";
	public static final String WatermarkPosition_RIGHTUP = "rightup";
	public static final String WatermarkPosition_LEFTDOWN = "leftdown";
	public static final String WatermarkPosition_RIGHTDOWN = "rightdown";
	public static final String WatermarkPosition_CENTER = "center";

	/**
	 * 居中缩略截图
	 * 
	 * @param sourcePath
	 *            --源图片地址
	 * @param targetPath
	 *            --目标图片地址
	 * @param imageWidth
	 *            --缩放图片宽度
	 * @param imageHeight
	 *            --缩放图片高度
	 */
	public static void zoomCenterImage(String sourcePath, String targetPath,
			int imageWidth, int imageHeight) {
		GraphicsMagickApp.cropImageCenter(sourcePath, targetPath, imageWidth,
				imageHeight);
	}

	public static void zoomCenterImage(File srcFile, String targetPath,
			int imageWidth, int imageHeight) {
		int sw = 0;
		int sh = 0;
		BufferedImage image = null;
		try {
			image = ImageIO.read(srcFile);
			sw = image.getWidth();// 图片宽度
			sh = image.getHeight();// 图片高度
			GraphicsMagickApp.cropImage(srcFile.getAbsolutePath(), targetPath, sw,
					sh, imageWidth, imageHeight);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public static void watermarkImage(String sourceFilePath, String position) {
		GraphicsMagickApp.watermarkImage(sourceFilePath, WatermarkPath,
				position, WatermarkAlpha);
	}

	/**
	 * 等比压缩 判断是否GIF图片处理为jpg图片
	 */
	public static void cropImage(String sourceFilePath, String targetPath,
			int w, int h) {
		String ext = FileUtil.getExt(sourceFilePath);
		if (ext.equalsIgnoreCase("gif")) {
			GraphicsMagickApp.imageGif2JPG(sourceFilePath, targetPath, w, h);
		} else {

			GraphicsMagickApp.cropImage(sourceFilePath, targetPath, w, h, w, h);
		}
	}

	public static void main(String args[]) {
		String sourceFilePath = "H:\\workspace\\100cenmw\\WebRoot\\uploadfiles\\2012-10-12\\image\\1350012610282.gif";
		String targetPath = "H:\\workspace\\100cenmw\\WebRoot\\uploadfiles\\2012-10-12\\image\\1350012610282_50_50.jpg";
		ImageUtil.cropImage(sourceFilePath, targetPath, 50, 50);
	}

	/**
	 * 自动缩放图 -- 递归缩放 如果源图是gif 生成图片最好是jpg
	 * 
	 * @param srcFile
	 * @param targetPath
	 * @param maxWidth
	 *            --缩放图最大限制宽度
	 * @param maxHeight
	 *            --缩放图最大限制高度
	 * @param minWidth
	 *            --缩放图最小宽度
	 * @param minHeight
	 *            --缩放图最小高度
	 */
	public static int[] zoomCropImageAuto(File srcFile, double maxWidth,
			double maxHeight, double minWidth, double minHeight) {
		double sourceWidth = 0;
		double sourceHeight = 0;
		BufferedImage image = null;
		try {
			image = ImageIO.read(srcFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sourceWidth = image.getWidth();// 图片宽度
		sourceHeight = image.getHeight();// 图片高度
		double ow = sourceWidth;
		double oh = sourceHeight;
		double w = 0;// 最终缩放的宽度
		double h = 0;// 最终缩放的高度
		double scale = 0.8;// 递归缩放比例
		if (sourceWidth > sourceHeight) {// 当源图宽度大于高度时,采用宽度进行等比缩放
			if (sourceWidth > minWidth) {
				if (sourceWidth > maxWidth) {
					sourceWidth = recursion(sourceWidth, maxWidth, 3, 0, scale);
				} else {
					sourceWidth = recursion(sourceWidth, maxWidth / 2, 2, 0,
							scale);
				}
				w = sourceWidth;
				h = w * sourceHeight / ow;
			} else {
				w = sourceWidth;
				h = sourceHeight;
			}
		} else if (sourceWidth < sourceHeight) {
			if (sourceHeight > minHeight) {
				if (sourceHeight > maxHeight) {
					sourceHeight = recursion(sourceHeight, maxHeight, 3, 0,
							scale);
				} else {
					sourceHeight = recursion(sourceHeight, maxHeight / 2, 2, 0,
							scale);
				}
				h = sourceHeight;
				w = sourceWidth * h / oh;
			} else {
				w = sourceWidth;
				h = sourceHeight;
			}
		} else {
			if (sourceWidth > minWidth) {
				if (sourceWidth > maxWidth) {
					sourceWidth = recursion(sourceWidth, maxWidth, 3, 0, scale);
				} else {
					sourceWidth = recursion(sourceWidth, maxWidth / 2, 2, 0,
							scale);
				}
				w = sourceWidth;
				h = w * sourceHeight / ow;
			} else {
				w = sourceWidth;
				h = sourceHeight;
			}
		}
		int _w = (int) w;
		int _h = (int) h;
		String sourceFilePath = srcFile.getAbsolutePath();
		String ext = FileUtil.getExt(sourceFilePath);
		String targetPath = getGiFHandleRenamePic(sourceFilePath, _w, _h);
		File targetFile = new File(targetPath);
		if (!targetFile.exists()) {
			if (ext.equalsIgnoreCase("gif")) {
				GraphicsMagickApp.imageGif2JPG(sourceFilePath, targetPath, _w,
						_h);
			} else {

				GraphicsMagickApp.cropImage(sourceFilePath, targetPath, _w, _h,
						_w, _h);
			}
		}
		return new int[] { _w, _h };
	}

	private static String getGiFHandleRenamePic(String pic, int w, int h) {
		String ext = FileUtil.getExt(pic);
		if (ext.equalsIgnoreCase("gif")) {
			pic = StringUtil.rename(pic, "_" + w + "_" + h + "");
			pic = pic.substring(0, pic.lastIndexOf(".")) + ".jpg";
		} else {
			pic = StringUtil.rename(pic, "_" + w + "_" + h + "");
		}
		return pic;
	}

	/**
	 * 自动缩放图 -- 递归缩放
	 * 
	 * @param sourceWidth
	 *            --源图宽
	 * @param sourceHeight
	 *            --源图高
	 * @param maxWidth
	 *            --缩放图最大限制宽度
	 * @param maxHeight
	 *            --缩放图最大限制高度
	 * @param minWidth
	 *            --缩放图最小宽度
	 * @param minHeight
	 *            --缩放图最小高度
	 */
	public static void zoomCropImageAuto(double sourceWidth,
			double sourceHeight, double maxWidth, double maxHeight,
			double minWidth, double minHeight) {
		double ow = sourceWidth;
		double oh = sourceHeight;
		double w = 0;// 最终缩放的宽度
		double h = 0;// 最终缩放的高度
		double scale = 0.8;// 递归缩放比例
		if (sourceWidth > sourceHeight) {// 当源图宽度大于高度时,采用宽度进行等比缩放
			if (sourceWidth > minWidth) {
				if (sourceWidth > maxWidth) {
					sourceWidth = recursion(sourceWidth, maxWidth, 3, 0, scale);
				} else {
					sourceWidth = recursion(sourceWidth, maxWidth / 2, 2, 0,
							scale);
				}
				w = sourceWidth;
				h = w * sourceHeight / ow;
			} else {
				w = sourceWidth;
				h = sourceHeight;
			}
		} else if (sourceWidth < sourceHeight) {
			if (sourceHeight > minHeight) {
				if (sourceHeight > maxHeight) {
					sourceHeight = recursion(sourceHeight, maxHeight, 3, 0,
							scale);
				} else {
					sourceHeight = recursion(sourceHeight, maxHeight / 2, 2, 0,
							scale);
				}
				h = sourceHeight;
				w = sourceWidth * h / oh;
			} else {
				w = sourceWidth;
				h = sourceHeight;
			}
		} else {
			if (sourceWidth > minWidth) {
				if (sourceWidth > maxWidth) {
					sourceWidth = recursion(sourceWidth, maxWidth, 3, 0, scale);
				} else {
					sourceWidth = recursion(sourceWidth, maxWidth / 2, 2, 0,
							scale);
				}
				w = sourceWidth;
				h = w * sourceHeight / ow;
			} else {
				w = sourceWidth;
				h = sourceHeight;
			}
		}
	}

	/**
	 * 按次数递归缩放
	 * 
	 * @param sourceWidth
	 * @param limitWidth
	 * @param size
	 *            --递归总数
	 * @param seed
	 *            --递归次数
	 * @param scale
	 *            --缩放比例
	 * @return
	 */
	private static double recursion(double sourceWidth, double limitWidth,
			int size, int seed, double scale) {
		if (seed == size) {// 已递归次数等于递归总数
			return sourceWidth;
		} else {
			sourceWidth = scaleAuto(sourceWidth, limitWidth, scale);
			seed++;
			sourceWidth = recursion(sourceWidth, limitWidth / 2, size, seed,
					scale);
		}
		return sourceWidth;
	}

	/**
	 * 递归缩放
	 * 
	 * @param sv
	 * @param tv
	 * @param scale
	 *            --缩放比例
	 * @return
	 */
	private static double scaleAuto(double sv, double tv, double scale) {
		sv = sv * scale;
		if (sv > tv) {
			sv = scaleAuto(sv, tv, scale);
		}
		return sv;
	}

	/**
	 * 获取缩放宽高
	 * 
	 * @param sw
	 *            --源图宽
	 * @param sh
	 *            --源图高
	 * @param lw
	 *            --限制宽
	 * @param lh
	 *            --限制高
	 * @return
	 */
	public static int[] getResizeImageWH(int sw, int sh, int lw, int lh) {
		int index = 0;
		int _w = sw;
		int _h = sh;
		if (sw > lw)
			index += 1;
		if (sh > lh)
			index += 2;
		switch (index) {
		case 1:
			_w = lw;
			_w = sh * lw / sw;
		case 2:
			_h = lh;
			_w = sw * lh / sh;
		case 3:
			_h = (sh / lh > sw / lw) ? lh : sh * lw / sw;
			_w = (sh / lh > sw / lw) ? sw * lh / sh : lw;
		}
		return new int[] { _w, _h };
	}

}
