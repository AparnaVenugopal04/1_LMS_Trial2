package com.lms.stepdefinition;

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
	
	
	@Given("user enters valid {string}")
	public void user_enters_valid(String url)
	{
		
		DriverProperties.getDriver().get(url);
        
	}

	@Then("LMS homepage is displayed")
	public void lms_homepage_is_displayed() 
	{
	    //To validate if the title of the page is as expected
		String actualPageTitle = homePage.getPageTitle();
		
		//Compare the page title with the expected Page title
		String expectedPageTitle = "LMS | Leave Management System- Home";
		Assert.assertEquals(expectedPageTitle,actualPageTitle);	
		System.out.println("LMS HomePage is displayed- Test Success");
		
	}
}
