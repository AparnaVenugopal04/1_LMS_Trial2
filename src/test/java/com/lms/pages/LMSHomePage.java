package com.lms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LMSHomePage {
	
	private WebDriver driver;


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
			}

}