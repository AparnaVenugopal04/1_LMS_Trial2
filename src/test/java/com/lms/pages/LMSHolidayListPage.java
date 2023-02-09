package com.lms.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LMSHolidayListPage {

	private WebDriver driver;

	// Constructor of the page class
	public LMSHolidayListPage(WebDriver driver) {
		this.driver = driver;
	}

	private By windowTitle = By.xpath("//div/h4[contains(text(),\"Office Holiday list\")]");
    

	// Method to get the title of the LMS Holiday List window
	public String getWindowTitle() throws InterruptedException {

		// Title of the window
		//WebDriverWait wait = new WebDriverWait(this.driver,Duration.ofSeconds(30));
		Thread.sleep(5000);
		return driver.findElement(windowTitle).getText();

	}

	// Method to count the Public Holidays
	public void countOfPublicHolidays() 
	{
		
		//WebDriverWait wait = new WebDriverWait(this.driver,Duration.ofSeconds(30));
        //wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\\\"display-holidaylist-form\\\"]/table/tbody/tr")));
        
        //Get all the row elements
		List<WebElement> rowElements = driver
				.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr"));
		
		int rowSize = rowElements.size();
		System.out.println("No of rows in the table is: " +rowSize);
		
		 //Get all the row elements
		List<WebElement> colElements = driver
				.findElements(By.xpath("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr[1]/td"));
		
		int colSize = colElements.size();
		System.out.println("No of columns in the table is: " +colSize);
		
		for (int i=1;i<=rowSize;i++)
		{
			for(int j=1;j<=colSize;j++)
			{
				
				String cellContent = 
				driver.findElement(By.xpath
						("//*[@id=\"display-holidaylist-form\"]/table/tbody/tr["+ i +"]/td["+ j +"]"))
						.getText()+ "  ";
				System.out.println(cellContent);
			}
			System.out.println();
		}
		
	}

}
