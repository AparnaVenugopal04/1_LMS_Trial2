package com.lms.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lms.pages.LMSHolidayListPage;

public class ExcelReportGenerator 
{
	

	private static WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LMSHolidayListPage.class);
	
	// Method to create the excel report
	public static void generateHoldiayExcelReport
	(List<WebElement> headers, List<WebElement> rows,List<WebElement> columns) 
			throws IOException {

	   logger.info("Generate report based on the holiday type");

		// Create a workbook in xlsx format
		Workbook workbook = new XSSFWorkbook();

		// Create sheet
		Sheet sheet1 = workbook.createSheet("Public Holiday");
		Sheet sheet2 = workbook.createSheet("Optional Holiday");

		for(int row=0;row<rows.size();row++)
		{
			for (int col=0;col<columns.size();col++)
			{
				String cellContent = driver
						.findElement(By
								.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[" + row + "]/td[" + col + "]"))
						.getText() + "  ";

				if (cellContent.contains("Public Holiday")) 
				{
					Row rowvalue = sheet1.createRow(row);
					rowvalue.createCell(col).setCellValue(cellContent);
				}
				else if (cellContent.contains("Optional Holiday"))
				{
					Row rowvalue = sheet2.createRow(row);
					rowvalue.createCell(col).setCellValue(cellContent);
				}
			}
		}
	
	
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Aparna.Venugopal\\eclipse\\1_LMS_Trial2\\HolidayReport.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		
		logger.info("Report generated successfully");
	}

}
