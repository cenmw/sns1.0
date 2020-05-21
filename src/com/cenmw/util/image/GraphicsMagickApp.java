package com.cenmw.util.image;

import java.io.IOException;

import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import com.cenmw.util.ImageUtil;

/**
 * 需要GraphicsMagick工具
 * 
 * @author LiangJiChao
 * 
 */
public class GraphicsMagickApp {
	private final static String GraphicsMagickAppPath = "D:\\Program Files\\GraphicsMagick-1.3.12-Q16";

	/**
	 * 先缩放，后居中切割图片
	 * 
	 * @param srcPath
	 *            源图路径
	 * @param desPath
	 *            目标图保存路径
	 * @param rectw
	 *            待切割在宽度
	 * @param recth
	 *            待切割在高度
	 * @throws IM4JavaException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void cropImageCenter(String srcPath, String desPath,
			int rectw, int recth) {
		IMOperation op = new IMOperation();
		op.addImage(srcPath);
		op.resize(rectw, recth, '^').gravity("center").extent(rectw, recth);
		op.addImage(desPath);
		ConvertCmd convert = new ConvertCmd(true);
		/*
		 * linux下不要设置此值，不然会报错
		 */
		convert.setSearchPath(GraphicsMagickAppPath);
		try {
			convert.run(op);
		} catch (IOException e) {
		} catch (InterruptedException e) {
		} catch (IM4JavaException e) {
		}
	}

	/**
	 * 图片裁剪,等比压缩
	 * 
	 * @param srcPath
	 *            源图片
	 * @param desPath
	 *            处理后图片
	 * @param sw
	 *            源图片宽
	 * @param sh
	 *            源图片高
	 * @param dw
	 *            处理后图片宽
	 * @param dh
	 *            处理后图片高
	 * @throws Exception
	 */
	public static void cropImage(String srcPath, String desPath, int sw,
			int sh, int dw, int dh) {
		if (sw <= 0 || sh <= 0 || dw <= 0 || dh <= 0) {
			return;
		}
		IMOperation op = new IMOperation();
		op.addImage();
		// 如果源图宽度和高度都小于目标宽高，则仅仅压缩图片
		if ((sw <= dw) && (sh <= dh)) {
			op.resize(sw, sh);
		}

		// 如果源图宽度小于目标宽度，并且源图高度大于目标高度
		if ((sw <= dw) && (sh > dh)) {
			op.resize(sw, sh); // 压缩图片
			op.append().crop(sw, dh, 0, (sh - dh) / 2); // 切割图片
		}

		// 如果源宽度大于目标宽度，并且源高度小于目标高度
		if ((sw > dw) && (sh <= dh)) {
			op.resize(sw, sh);
			op.append().crop(dw, sh, (sw - dw) / 2, 0);
		}

		// 如果源图宽、高都大于目标宽高
		if (sw > dw && sh > dh) {
			float ratiow = (float) dw / sw; // 宽度压缩比
			float ratioh = (float) dh / sh; // 高度压缩比

			// 宽度压缩比小（等）于高度压缩比（是宽小于高的图片）
			if (ratiow >= ratioh) {
				int ch = (int) (ratiow * sh); // 压缩后的图片高度
				op.resize(dw, ch); // 按目标宽度压缩图片
				op.append().crop(dw, dh, 0, (ch > dh) ? ((ch - dh) / 2) : 0); // 根据高度居中切割压缩后的图片
			} else { // （宽大于高的图片）
				int cw = (int) (ratioh * sw); // 压缩后的图片宽度
				op.resize(cw, dh); // 按计算的宽度进行压缩
				op.append().crop(dw, dh, (cw > dw) ? ((cw - dw) / 2) : 0, 0); // 根据宽度居中切割压缩后的图片
			}
		}

		op.addImage();
		ConvertCmd convert = new ConvertCmd(true);
		/*
		 * linux下不要设置此值，不然会报错
		 */
		convert.setSearchPath(GraphicsMagickAppPath);
		try {
			convert.run(op, srcPath, desPath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IM4JavaException e) {
			e.printStackTrace();
		}// BufferedImage or String
	}

	/**
	 * 
	 * @param sourceFilePath
	 * @param watermarkPath
	 * @param position
	 *            NorthWest, North, NorthEast, West, Center,East, SouthWest,
	 *            South, or SouthEast
	 * @param alpha
	 */
	public static void watermarkImage(String sourceFilePath,
			String watermarkPath, String position, int alpha) {
		IMOperation op = new IMOperation();
		op.addImage(watermarkPath);
		String pos = "";
		if (position.equals(ImageUtil.WatermarkPosition_LEFT)) {
			pos = "west";
		} else if (position.equals(ImageUtil.WatermarkPosition_RIGHT)) {
			pos = "east";
		} else if (position.equals(ImageUtil.WatermarkPosition_UP)) {
			pos = "north";
		} else if (position.equals(ImageUtil.WatermarkPosition_DOWN)) {
			pos = "south";
		} else if (position.equals(ImageUtil.WatermarkPosition_LEFTUP)) {
			pos = "northwest";
		} else if (position.equals(ImageUtil.WatermarkPosition_RIGHTUP)) {
			pos = "northeast";
		} else if (position.equals(ImageUtil.WatermarkPosition_LEFTDOWN)) {
			pos = "southwest";
		} else if (position.equals(ImageUtil.WatermarkPosition_RIGHTDOWN)) {
			pos = "southeast";
		} else if (position.equals(ImageUtil.WatermarkPosition_CENTER)) {
			pos = "center";
		}

		op.gravity(pos);// 位置
		op.dissolve(alpha);// 透明度

		op.addImage(sourceFilePath);
		op.addImage(sourceFilePath);

		CompositeCmd cmd = new CompositeCmd(true);
		/*
		 * linux下不要设置此值，不然会报错
		 */
		cmd.setSearchPath(GraphicsMagickAppPath);
		try {
			cmd.run(op);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IM4JavaException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 缩放gif图片转换成jpg
	 * 
	 * @param srcPath
	 * @param desPath
	 * @param w
	 * @param h
	 */
	public static void imageGif2JPG(String srcPath, String desPath, int w, int h) {
		IMOperation op = new IMOperation();
		op.addImage(srcPath + "[0]");
		op.resize(w, h, '^');
		op.addImage(desPath);
		ConvertCmd convert = new ConvertCmd(true);
		/*
		 * linux下不要设置此值，不然会报错
		 */
		convert.setSearchPath(GraphicsMagickAppPath);
		try {
			convert.run(op);
		} catch (IOException e) {
		} catch (InterruptedException e) {
		} catch (IM4JavaException e) {
		}
	}
}
