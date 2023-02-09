package com.lms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LMSHolidayListPage {
	
	
	private WebDriver driver;

	 //Constructor of the page class
	 public LMSHolidayListPage(WebDriver driver)
	 {
		 this.driver = driver;
	 }
	    
	 private By windowTitle = By.xpath("//div/h4[contains(text(),\"Office Holiday list\")]");

	   //Method to get the title of the LMS Holiday List window
		public String getWindowTitle() throws InterruptedException
		{
			//Switch to the window
			 //String mainWindow = driver.getWindowHandle();
			 //driver.switchTo().window(mainWindow);
			 
			//Title of the window
			Thread.sleep(5000);
					return driver.findElement(windowTitle).getText();
			
	}
}
