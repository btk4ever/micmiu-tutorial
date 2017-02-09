package com.micmiu.tutorial.java.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created
 * User: <a href="http://micmiu.com">micmiu</a>
 * Date: 5/25/2015
 * Time: 23:28
 */
public class ReadExcel2007PlusMain {

	public static void main(String[] args) throws Exception {
		String filename = "micmiu.xlsx";
		ReadExcel2007PlusMain reader = new ReadExcel2007PlusMain();
		reader.read(filename);
	}

	public void read(String filename) throws Exception {
		InputStream is = new FileInputStream(filename);
		Workbook wb = new XSSFWorkbook(is);


		for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
			Sheet sheet = wb.getSheetAt(numSheet);
			System.out.println(">>>> sheet Name:" + sheet.getSheetName());
			for (Row row : sheet) {
				System.out.println(">>>> Row  No: " + row.getRowNum());
				for (Cell cell : row) {
					CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
					System.out.print(cellRef.formatAsString());
					System.out.print(" - ");
					System.out.println(getCellValue(cell));
				}
			}
		}
		wb.close();
		is.close();
	}

	private String getCellValue(Cell cell){
		String result = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				result =  cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					result = cell.getDateCellValue()+"";
				} else {
					result = cell.getNumericCellValue()+"";
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				System.out.println(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				System.out.println(cell.getCellFormula());
				break;
			default:
				System.out.println();
		}
		return result;
	}


}


