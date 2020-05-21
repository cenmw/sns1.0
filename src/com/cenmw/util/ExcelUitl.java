package com.cenmw.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelUitl {
	public static List getAllValue(String filePath, int cell) {
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(new File(filePath));
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheet(0);
		int rows = sheet.getRows();
		int r = 0;
		int c = cell;
		List list = new ArrayList();
		for (int i = 0; i < rows; i++) {
			r = i;
			if (i > 0) {
				Cell ce = sheet.getCell(c, r);
				String content = ce.getContents();
				if (!content.trim().equals("")) {
					list.add(content.trim());
				}
			}
		}
		List result = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			String s = (String) list.get(i);
			String[] arr = s.split(",");
			if (arr.length > 0) {
				for (int j = 0; j < arr.length; j++) {
					String val = arr[j];
					result.add(val);
				}
			}
		}
		return result;
	}
	

	public static void main(String args[]) throws BiffException, IOException {
		String filePath = "K:\\Develop\\100t1Work\\workspace\\100cenmw\\WebRoot\\uploadfiles\\47.xls";
		int cell = 0;
		List list = getAllValue(filePath, 0);
		Iterator it=list.iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
	}
}
