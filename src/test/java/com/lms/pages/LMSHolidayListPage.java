package com.lms.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lms.stepdefinition.LMSHomePageSteps;

public class LMSHolidayListPage {

	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LMSHolidayListPage.class);

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

		// Get all the row elements
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

				logger.info(cellContent);
				
				if (cellContent.contains("Public Holiday")) {
					// Increment the counter
					counter++;
					logger.info("Counter" + counter);
				}
				
				}
			}
		// Check if the count of public holidays is greater than or equal to 10
		if (counter >= 10) {
			logger.info("Number of public holidays:" + counter);
		}

}

}
