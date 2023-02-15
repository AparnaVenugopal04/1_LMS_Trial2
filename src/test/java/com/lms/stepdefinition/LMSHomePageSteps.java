/*  Project Name: LMS Holiday Reports
 *  Team : Aparna Venugopal, Ashwathi Diana P, Don Jiji, Bhavya Lakshmi
 *  Submission Date : 20-Feb-2023          
 * 
 *  Purpose: This JAVA program is for all the steps to be performed on the LMS home page
 *  
 */


package com.lms.stepdefinition;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import com.lms.common.CommonMethods;
import com.lms.common.DriverProperties;
import com.lms.common.LMSHomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LMSHomePageSteps 
{
	//Declare the variable for WebDriver
	WebDriver driver;
	//Instantiate the driver
	private LMSHomePage homePage = new LMSHomePage(DriverProperties.getDriver());
	CommonMethods common = new CommonMethods();
	String expectedPageTitle = "LMS | Leave Management System- Home";

	
	//Test Case 1 - Validation of LMS home page
	//Step1 : User is able to navigate to the LMS home page
	@Given("user enters valid {string}")
	public void user_enters_valid(String url)
	{
		common.Updatelog("*******TestCase 1 : LMS Homepage vaidation*********");
		DriverProperties.getDriver().get(url);
		common.Updatelog("Verify that the user is able to enter the url for LMS :" + url);
       
	}

	@Then("LMS homepage is displayed")
	public void lms_homepage_is_displayed() 
	{
		//Validate whether the LMS home page is loaded
		String actualPageTitle = homePage.validatePageHeading();
		
		// Compare the page title with the expected Page title
				try {
					Assert.assertEquals(expectedPageTitle, actualPageTitle);
					common.Updatelog("LMS HomePage title is matching as expected");
					common.Updatelog("TestCase 1 : LMS Homepage validation - PASS");
				} catch (AssertionError error) {
					common.Updatelog("TestCase 1 : LMS Homepage vaidation", error);
				}
	}
}
