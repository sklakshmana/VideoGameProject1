package com.testautomation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static void main(String[] args) {
		
		Map<String,String> testData=ExcelUtility.getMapData();
		
		for( Entry<String,String> map:testData.entrySet())
		{
			System.out.println("Key = "+map.getKey()+", Value = "+map.getValue());
		}

	}
	
	public static Map<String,String> getMapData() {
		
		Map<String,String> testData=new HashMap<String,String>();
		
		try {
			FileInputStream fins=new FileInputStream("./files/demo2.xlsx");
			
			Workbook workbook=new XSSFWorkbook(fins);
			
			Sheet sheet= workbook.getSheetAt(0);
			
			int lastRowNumber=sheet.getLastRowNum();
			
			
			
			for(int i=0;i<=lastRowNumber;i++)
			{
				Row row=sheet.getRow(i);
				
				Cell keyCell=row.getCell(0);
				String key=keyCell.getStringCellValue().trim();
				
				Cell valueCell=row.getCell(1);
				String value=valueCell.getStringCellValue().trim();
				testData.put(key, value);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return testData;
	}

}
