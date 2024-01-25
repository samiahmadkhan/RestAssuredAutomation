package com.api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class readXlSheetUtility {
	
	
	public static Object[][] getData(String filePath,String sheetName) throws Exception {
		FileInputStream file=new FileInputStream(filePath);
		 Workbook Book = WorkbookFactory.create(file);
		 Sheet sheet = Book.getSheet(sheetName);
		 Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
					}
			}

			return data;
	}

}
