package com.lms.pages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lms.common.CommonMethods;

public class LMSHolidayListPage {

	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LMSHolidayListPage.class);
	CommonMethods common = new CommonMethods();

	int rowCount, columnCount;
	String holidayType;
	FileOutputStream outputStream = null;
	String reportPath = "C:\\Users\\Aparna.Venugopal\\eclipse\\1_LMS_Trial2\\HolidayReport.xlsx";
	Workbook workbook = new XSSFWorkbook();
	Sheet sheetPublicHoliday;
	Sheet sheetOptionalHoliday;
	Row headerPublic;
	Row headerOptional;
	Font headerfont = workbook.createFont();
	CellStyle headerStyle = workbook.createCellStyle();

	// Constructor of the page class
	public LMSHolidayListPage(WebDriver driver) {
		this.driver = driver;
	}

	 private By windowTitle = By.xpath("//div/h4[contains(text(),\"Office Holiday list\")]");

	// Method to get the title of the LMS Holiday List window
	public String getWindowTitle() throws InterruptedException {

		// Title of the window
		Thread.sleep(5000);
		String holidayListTitle = driver.findElement(windowTitle).getText();
		common.Updatelog("Page title is displayed as :" + holidayListTitle);

		return holidayListTitle;
	}

	// Method to count the Public Holidays
	public void countOfPublicHolidays() throws IOException {

		common.Updatelog("Validate whether the count of public holidays is greater than 10");
		// Get the row count of the Holiday List table
		int rowSize = getTableRows().size();
		common.Updatelog("Number of rows in the Holiday list table is: " + rowSize);

		// Get the column count of the Holiday List table
		int colSize = getTableColumns().size();
		common.Updatelog("Number of columns in the Holiday list table is: " + colSize);

		// Get the count of Public Holidays from the Holiday List table
		int publicHolidaycounter = 0;
		for (int i = 1; i <= rowSize; i++) {
			for (int j = 1; j <= colSize; j++) {

				// Get the content and check if the holiday type is Public Holiday
				String cellContent = driver
						.findElement(By
								.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[" + i + "]/td[" + j + "]"))
						.getText() + "  ";

				if (cellContent.contains("Public Holiday")) {
					// Increment the counter
					publicHolidaycounter++;
				}

			}
		}
		// Check if the count of public holidays is greater than or equal to 10
		if (publicHolidaycounter >= 10) {
			common.Updatelog("Number of public holidays is " + publicHolidaycounter);
			common.Updatelog("The count of public holidays is greater than or equal to 10");
		} else {
			common.Updatelog("Number of public holidays:" + publicHolidaycounter);
			common.Updatelog("The count of public holidays is not greater than or equal to 10");

		}

	}

	// Method to create the excel report
	public void generateHoldiayExcelReport() throws IOException {
		try {
		common.Updatelog("Verify that the user is able to generate a report based on the Holiday type");

		// Get the table headers and data
		List<WebElement> headerData = getTableHeader();
		List<WebElement> rowData = getTableRows();
		List<WebElement> colData = getTableColumns();

		// Create sheets for Public Holidays and Optional Holidays
		sheetPublicHoliday = workbook.createSheet("Public Holidays");
		sheetOptionalHoliday = workbook.createSheet("Optional Holidays");
		common.Updatelog("Sheets are created successully in the excel workbook");

		headerPublic = sheetPublicHoliday.createRow(0);
		headerOptional = sheetOptionalHoliday.createRow(0);
		common.Updatelog("Rows are created successully in the excel workbook");

		// Set a font for the headings
		headerfont.setBold(true);
		headerfont.setFontHeightInPoints((short) 12);
		headerfont.setColor(IndexedColors.BLACK.index);

		// Create cell style for headers
		headerStyle.setFont(headerfont);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

		// Set the headers in the sheet
		for (int header = 1; header <= headerData.size(); header++) {
			String headerText = driver
					.findElement(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/thead/tr[1]/th[" + header + "]"))
					.getText();

			Cell cell1 = headerPublic.createCell(header - 1);
			cell1.setCellValue(headerText);
			cell1.setCellStyle(headerStyle);

			Cell cell2 = headerOptional.createCell(header - 1);
			cell2.setCellValue(headerText);
			cell2.setCellStyle(headerStyle);
		}

		// Set the values for holiday type in both the sheets
		String holidayType = null;
		int publicHolidaycounter = 1;
		for (int row = 1; row <= rowData.size(); row++) {
			holidayType = driver
					.findElement(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[" + row + "]/td[4]"))
					.getText();
			Row rowvalue = sheetPublicHoliday.createRow(publicHolidaycounter);

			if (holidayType.equalsIgnoreCase("Public Holiday")) {
				for (int col = 1; col <= colData.size(); col++) {
					List<WebElement> cellcontent = driver.findElements(By.xpath(
							"//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[" + row + "]/td[" + col + "]"));

					for (WebElement loop : cellcontent) {
						String detail = loop.getText();
						rowvalue.createCell(col - 1).setCellValue(detail);
					}

				}
				publicHolidaycounter++;
			}
		}
		int optionalHolidaycounter = 1;
		for (int row = 1; row <= rowData.size(); row++) {
			holidayType = driver
					.findElement(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[" + row + "]/td[4]"))
					.getText();
			Row rowvalue = sheetOptionalHoliday.createRow(optionalHolidaycounter);
			if (holidayType.equalsIgnoreCase("Optional Holiday")) {
				for (int col = 1; col <= colData.size(); col++) {
					List<WebElement> cellcontent = driver.findElements(By.xpath(
							"//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[" + row + "]/td[" + col + "]"));

					for (WebElement loop : cellcontent) {
						String detail = loop.getText();
						rowvalue.createCell(col - 1).setCellValue(detail);
					}

				}
				optionalHolidaycounter++;
			}
		}

		outputStream = new FileOutputStream(reportPath);
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
		common.Updatelog("Excel report generated successfully based on holiday type - PASS");
		}
		catch (Exception exception)
		{
	common.Updatelog("Excel report not generated",exception);

		}
	}

	// Method to get the table headers
	public List<WebElement> getTableHeader() {

		logger.info("Get the values of the table header");
		// Get the headers
		List<WebElement> tableHeaders = driver
				.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/thead/tr[1]/th"));

		return tableHeaders;
	}

	// Method to get the table rows
	public List<WebElement> getTableRows() {

		// Get all the row elements
		List<WebElement> rowElements = driver
				.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr"));
		return rowElements;
	}

	// Method to get the table columns
	public List<WebElement> getTableColumns() {

		// Get all the row elements
		List<WebElement> colElements = driver
				.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[1]/td"));

		return colElements;
	}
}