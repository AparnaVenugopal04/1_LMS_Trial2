package com.lms.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.lms.stepdefinition.LMSHomePageSteps;


public class LMSHomePage {
	
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(LMSHomePage.class);


	 //Constructor of the page class
	 public LMSHomePage(WebDriver driver)
	 {
		 this.driver = driver;
	 }
	    
	 private By holidayList = By.xpath("//a[@href='/Home/HolidayList']");
	 
	   //Method to get the title of the LMS home page
		public String getPageTitle()
		{
			//Title of the page
			String homepageTitle = driver.getTitle();
			return homepageTitle;
		}
		
		
		//Method to click on the Holiday List link
			public void openHolidayList()
			{
				//Click on the Holiday List link
				driver.findElement(holidayList).click();
				logger.info("User clicks on the Holiday link");

			}

}