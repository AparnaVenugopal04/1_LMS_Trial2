package com.lms.pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lms.common.DriverProperties;
import com.lms.common.ExcelReportGenerator;
import com.lms.stepdefinition.LMSHomePageSteps;

public class LMSHolidayListPage {

	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LMSHolidayListPage.class);
	private ExcelReportGenerator report = new ExcelReportGenerator();

	
	
	// Constructor of the page class
	public LMSHolidayListPage(WebDriver driver) {
		this.driver = driver;
	}

	private By windowTitle = By.xpath("//div/h4[contains(text(),\"Office Holiday list\")]");

	// Method to get the title of the LMS Holiday List window
	public String getWindowTitle() throws InterruptedException {

		// Title of the window
		// WebDriverWait wait = new WebDriverWait(this.driver,Duration.ofSeconds(30));
		Thread.sleep(5000);
		return driver.findElement(windowTitle).getText();

	}

	// Method to count the Public Holidays
	public void countOfPublicHolidays() {

		// WebDriverWait wait = new WebDriverWait(this.driver,Duration.ofSeconds(30));
		// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\\\"display-holidaylist-form\\\"]/table/tbody/tr")));
		logger.info("Validate whether the count of public holidays is less than 10");
		// Get all the row elements
		List<WebElement> rowElements = driver
				.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr"));

		int rowSize = rowElements.size();
		logger.info("No of rows in the table is: " + rowSize);

		// Get all the column elements
		List<WebElement> colElements = driver
				.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[1]/td"));

		int colSize = colElements.size();
		logger.info("No of columns in the table is: " + colSize);

		int counter = 0;
		for (int i = 1; i <= rowSize; i++) {
			for (int j = 1; j <= colSize; j++) {

				String cellContent = driver
						.findElement(By
								.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[" + i + "]/td[" + j + "]"))
						.getText() + "  ";

				// logger.info(cellContent);

				if (cellContent.contains("Public Holiday")) {
					// Increment the counter
					counter++;
				}

			}
		}
		// Check if the count of public holidays is greater than or equal to 10
		if (counter >= 10) {
			logger.info("Number of public holidays:" + counter);
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

	
		
	// Method to count the Public Holidays
	public void generateHolidayReport() throws IOException 
	{
		report.generateHoldiayExcelReport(getTableHeader(), getTableRows(), getTableColumns());

	}
}
