package com.cenmw.util.image;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ThumbnailatorApp {
	/**
	 * 缩放图片 --源图比例缩放
	 * 
	 * @param sourceFilePath
	 *            --源图片绝对地址
	 * @param targetFilePath
	 *            --生成图片绝对地址
	 * @param imageWidth
	 *            --生成图片的宽
	 * @param imageHeight
	 *            --生成图片的高
	 */
	public static void zoomImage(String sourceFilePath, String targetFilePath,
			int imageWidth, int imageHeight) {
		try {
			Thumbnails.of(sourceFilePath).size(imageWidth, imageHeight)
					.toFile(targetFilePath);// 源图比例缩放
		} catch (IOException e) {
		}
	}

	/**
	 * 居中比例缩放
	 * 
	 * @param sourceFilePath
	 * @param targetFilePath
	 * @param imageWidth
	 * @param imageHeight
	 */
	public static void zoomCenterImage(String sourceFilePath,
			String targetFilePath, int imageWidth, int imageHeight) {
		try {
			Thumbnails.of(sourceFilePath).size(imageWidth, imageHeight)
					.sourceRegion(Positions.CENTER, imageWidth, imageHeight)
					.toFile(targetFilePath);// 居中比例缩放
		} catch (IOException e) {
		}
	}

	/**
	 * 缩放图片 --拉伸缩放
	 * 
	 * @param sourceFilePath
	 *            --源图片绝对地址
	 * @param targetFilePath
	 *            --生成图片绝对地址
	 * @param imageWidth
	 *            --生成图片的宽
	 * @param imageHeight
	 *            --生成图片的高
	 */
	public static void zoomForceImage(String sourceFilePath,
			String targetFilePath, int imageWidth, int imageHeight) {
		try {

			Thumbnails.of(sourceFilePath).forceSize(imageWidth, imageHeight)
					.toFile(targetFilePath);// 拉伸缩放
		} catch (IOException e) {
		}
	}

	public static void main(String args[]) {
		int imageWidth = 338;
		int imageHeight = 500;
		String sourceFilePath = "D:\\images\\001.jpg";
		String targetFilePath = "D:\\images\\001_" + imageWidth + "_"
				+ imageHeight + ".jpg";
		zoomImage(sourceFilePath, targetFilePath, imageWidth, imageHeight);
	}
}
