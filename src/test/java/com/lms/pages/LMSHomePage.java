/*  Project Name: LMS Holiday Reports
 *  Team : Aparna Venugopal, Ashwathi Diana P, Don Jiji, Bhavya Lakshmi
 *  Submission Date : 20-Feb-2023          
 * 
 *  Purpose: This JAVA program handles all the objects and methods for LMS home page
 *  
 */

package com.lms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lms.common.LoggerHelper;

public class LMSHomePage {

	private WebDriver driver;
	LoggerHelper logger = new LoggerHelper();
	String expectedPageTitle = "LMS | Leave Management System- Home";

	// Constructor of the page class
	public LMSHomePage(WebDriver driver) {
		this.driver = driver;
	}

	

	// Method to validate the title of the LMS home page
	public String validatePageHeading() {
		// Get the title of the page
		String actualPageTitle = driver.getTitle();
		logger.UpdateLog("Page title is displayed as :" + actualPageTitle);

		return actualPageTitle;

	}

	// Method to click on the Holiday List link
	public void openHolidayList() {
		// Click on the Holiday List link
		try {
			WebElement holidayList = driver.findElement(By.xpath("//a[@href='/Home/HolidayList']"));
			holidayList.click();
			logger.UpdateLog("User is able to click on the Holidays link");
		} catch (Exception exception) {
			logger.UpdateLog("User is not able to click on the Holidays link - FAIL", exception);
		}

	}

}