package com.cenmw.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DecimalFormatUtil {
	public static double getSimpleFormat(double value) {
		return getFormatByPattern("#.##", value);
	}

	public static double getFormatByPattern(String pattern, double value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return Double.valueOf(output);
	}

	public static String getFormat(double value) {
		NumberFormat formatter = new DecimalFormat("0.00");
		return formatter.format(value);
	}

	public static String getPrec(String na, String nb) {
		double a = Double.valueOf(na);
		double b = Double.valueOf(nb);
		String perc = "0";
		if (a > 0 && b > 0) {
			Double yu = new Double(a / b);
			yu = yu * 100;
			perc = getFormat(yu);
		}
		return perc;
	}

	public static double getChuFaVal(String na, String nb) {
		double a = Double.valueOf(na);
		double b = Double.valueOf(nb);
		if (a > 0 && b > 0) {
			Double yu = new Double(a / b);
			return yu;
		}
		return 0;
	}

	/**
	 * 四舍五入
	 * 
	 * @param num
	 * @return
	 */
	public static String getSSWR(String num) {
		return new BigDecimal(num).setScale(0, BigDecimal.ROUND_HALF_UP)
				.toString();
	}

	public static void main(String args[]) {
		// double a=Double.valueOf("1");
		// double b=Double.valueOf("11");
		// Double yu=new Double(a/b);
		// yu=yu*100;
		// String perc=DecimalFormatUtil.getFormat(yu);
		// System.out.println(perc);
		System.out.println(getSSWR("25.55"));
	}
}
