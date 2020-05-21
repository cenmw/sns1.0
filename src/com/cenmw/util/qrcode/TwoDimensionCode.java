package com.cenmw.util.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;
import com.swetake.util.Qrcode;

public class TwoDimensionCode {

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 */
	public static String encoderQRCode(String content, String imgPath,
			String imgType, HttpServletRequest request, int itype) {
		return encoderQRCode(content, imgPath, imgType, "", "", request, itype);
	}

	public static String encoderQRCode(String content, String imgPath,
			String imgType, int itype) {
		return encoderQRCode(content, imgPath, imgType, "", "", null, itype);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 */
	public static String encoderQRCode(String content, String imgPath,
			String imgType, String logpath, HttpServletRequest request,
			int itype) {
		return encoderQRCode(content, imgPath, imgType, logpath, "", request,
				itype);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public static String encoderQRCode(String content, String imgPath,
			String imgType, String logpath, String color,
			HttpServletRequest request, int itype) {
		try {
			BufferedImage bufImg = qRCodeCommon(content, imgType, logpath,
					color, request, itype);
			String curimgPath = imgPath;
			if (request != null) {
				curimgPath = request.getSession().getServletContext()
						.getRealPath("")
						+ imgPath;
			}
			File imgFile = new File(curimgPath);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgPath;
	}

	/**
	 * 生成二维码(QRCode)图片的公共方法
	 * 
	 * @param content
	 *            存储内容
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 * @return
	 */
	public static BufferedImage qRCodeCommon(String content, String imgType,
			String logpath, String color, HttpServletRequest request, int itype) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('Q');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(0);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");
			// 图片尺寸
			boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
			int imagesize = 800;
			if (itype == 1) { // 大 中 小
				imagesize = 800;
			} else if (itype == 2) { // 大 中 小
				imagesize = 400;
			} else if (itype == 3) { // 大 中 小
				imagesize = 200;
			}
			bufImg = new BufferedImage(imagesize, imagesize,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// 设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imagesize, imagesize);
			// 设定图像颜色> BLACK
			if (color != null && color.length() > 0) {
				gs.setColor(Color.decode(color));
			} else {
				gs.setColor(Color.BLACK);
			}

			// 设置偏移量，不设置可能导致解析出错
			// int xpix=imagesize/(codeOut.length+pixoff);

			int xpix = imagesize / codeOut.length;
			int modpix = imagesize % codeOut.length;
			int pixoff = modpix / 2;
			for (int i = 0; i < codeOut.length; i++) {
				for (int j = 0; j < codeOut.length; j++) {
					if (codeOut[j][i]) {
						gs.fillRect(j * xpix + pixoff, i * xpix + pixoff, xpix,
								xpix);
					}
				}
			}
			// gs.setClip(pixoff, pixoff, imagesize - pixoff, imagesize -
			// pixoff);

			if (logpath != null && logpath.length() > 0) {
				if (request != null) {
					logpath = request.getSession().getServletContext()
							.getRealPath("")
							+ logpath;
				}
				BufferedImage bufimage = getimage(logpath);
				int swidth = bufimage.getWidth();
				int sheigth = bufimage.getHeight();
				int imagex = imagesize / 2 - swidth / 2;
				int imagey = imagesize / 2 - sheigth / 2;
				gs.drawImage(bufimage, imagex, imagey, swidth, sheigth, null);
				gs.dispose();
				bufImg.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param imgPath
	 *            图片路径
	 * @return
	 */
	public static String decoderQRCode(String imgPath) {
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param input
	 *            输入流
	 * @return
	 */
	public static String decoderQRCode(InputStream input) {
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(input);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	public static BufferedImage getimage(String logpath) {
		BufferedImage bi = null;
		File file = new File(logpath);
		try {
			// bi=ImageIO.read(TwoDimensionCode.class.getResourceAsStream("D:/logo.png"));
			bi = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}

	public static void main(String[] args) {
		System.out.println("=============================");
		String imgPath = "D:/app011.png";
		String logPath = "D:/图片3.png";
		/*
		 * StringBuffer encoderContent = new
		 * StringBuffer("Hello 大大、小小,welcome to QRCode!" +
		 * "\nMyblog [ http://sjsky.iteye.com ]" +
		 * "\nEMail [ sjsky007@gmail.com ]");
		 */
		StringBuffer encoderContent = new StringBuffer();
		// for(int i=0;i<33;i++){//一般不超过50个汉字扫描
//		for (int i = 0; i < 20; i++) {
//			encoderContent.append("测试");
//		}
		
		encoderContent.append("http://m.100cenmw.net/appdownload");
        //绿色 C9738  橙色 D86B00 
		TwoDimensionCode.encoderQRCode(encoderContent.toString(), imgPath,
				"png", logPath, "#000000", null, 1);

		// try {
		// OutputStream output = new FileOutputStream(imgPath);
		// handler.encoderQRCode(content, output);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// System.out.println("==============encoder success");

		/*
		 * String decoderContent = handler.decoderQRCode(imgPath);
		 * System.out.println("解析结果如下："); System.out.println(decoderContent);
		 * System.out.println("========decoder success!!!");
		 */

	}

}