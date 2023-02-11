package com.lms.stepdefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.lms.common.DriverProperties;
import com.lms.pages.LMSHomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LMSHomePageSteps 
{
	WebDriver driver;
	private LMSHomePage homePage = new LMSHomePage(DriverProperties.getDriver());
	private static final Logger logger = LogManager.getLogger(LMSHomePageSteps.class);
	
	@Given("user enters valid {string}")
	public void user_enters_valid(String url)
	{
		logger.info("*********************************");
		logger.info("TestCase 1 : Execution started");
		DriverProperties.getDriver().get(url);
		logger.info("Entered the url for LMS :" + url);
       
	}

	@Then("LMS homepage is displayed")
	public void lms_homepage_is_displayed() 
	{
	    //To validate if the title of the page is as expected
		String actualPageTitle = homePage.getPageTitle();
		
		//Compare the page title with the expected Page title
		String expectedPageTitle = "LMS | Leave Management System- Home";
		Assert.assertEquals(expectedPageTitle,actualPageTitle);
		logger.info("LMS HomePage title is :" +actualPageTitle);
		logger.info("LMS HomePage is displayed -Test Case Pass");
	}
}
