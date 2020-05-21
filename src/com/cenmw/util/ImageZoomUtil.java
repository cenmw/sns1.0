package com.cenmw.util;

import com.cenmw.util.image.GraphicsMagickApp;



public class ImageZoomUtil {
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
		System.out.println("===========================111");
		GraphicsMagickApp.cropImageCenter(sourcePath, targetPath, imageWidth,
				imageHeight);
	}
}
