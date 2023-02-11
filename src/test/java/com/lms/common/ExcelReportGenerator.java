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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lms.pages.LMSHolidayListPage;

public class ExcelReportGenerator {
	//WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LMSHolidayListPage.class);


	// Method to create the excel report
	public static void generateHoldiayExcelReport(List<WebElement> headers, List<WebElement> rows,
			List<WebElement> columns) throws IOException {

		List<String> columnHeaders = new ArrayList<String>();
		List<String> rowRrecords = new ArrayList<String>();
		List<String> colRrecords = new ArrayList<String>();

		// Set the column headers
		for (WebElement headerRec : headers) {
			String txtHeader = headerRec.getText();
			columnHeaders.add(txtHeader);
		}

		// Set the rows
		for (WebElement rowRec : rows) {
			String txtRow = rowRec.getText();
			rowRrecords.add(txtRow);
		}

		// Set the columns
		for (WebElement colRec : rows) {
			String txtCol = colRec.getText();
			colRrecords.add(txtCol);
		}

		logger.info("Generate report based on the holiday type");

		// Create a workbook in xlsx format
		Workbook workbook = new XSSFWorkbook();

		// Create sheet
		Sheet sheet1 = workbook.createSheet("Public Holiday");
		Sheet sheet2 = workbook.createSheet("Optional Holiday");

		// Set a font for the headings
		Font headerfont = workbook.createFont();
		headerfont.setBold(true);
		headerfont.setFontHeightInPoints((short) 12);
		headerfont.setColor(IndexedColors.BLACK.index);

		// Create cell style
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFont(headerfont);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

		// Create header row
		Row headerRow = sheet1.createRow(0);
		// Iterate over the column headers to set the values
		/*for (int i = 0; i <= columnHeaders.size(); i++) {
			Cell cell = headerRow.createCell(i);
			for (String value1 : columnHeaders) {
				cell.setCellValue(value1);
				cell.setCellStyle(headerStyle);
			}
		}*/
		
		

		// Fill the data with the rows and columns from Office Holiday List

		CreationHelper cHelper = workbook.getCreationHelper();
		CellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(cHelper.createDataFormat().getFormat("DD-MM-YYYY"));

		/*
		List<List<String>> fullList = new ArrayList<List<String>>();
		fullList.add(rowRrecords);
		fullList.add(colRrecords);
        Iterator <List<String>>i = fullList.iterator();
        int rownum=0;
        int cellnum = 0;
        while (i.hasNext()) {
            List<String> templist = (List<String>) i.next();
            Iterator<String> tempIterator= templist.iterator();
            Row row = sheet1.createRow(rownum++);
            cellnum = 0;
            while (tempIterator.hasNext()) {
                String temp = (String) tempIterator.next();
                    Cell cell = row.createCell(cellnum++);
                            cell.setCellValue(temp);

                }

            }*/
		
	
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Aparna.Venugopal\\eclipse\\1_LMS_Trial2\\HolidayReport.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		
		logger.info("Report generated successfully");
	}

}
