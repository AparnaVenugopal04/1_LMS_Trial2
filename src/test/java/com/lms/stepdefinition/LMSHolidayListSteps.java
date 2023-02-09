package com.lms.stepdefinition;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.lms.common.DriverProperties;
import com.lms.pages.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LMSHolidayListSteps {

	WebDriver driver;
	private LMSHomePage homePage = new LMSHomePage(DriverProperties.getDriver());
	private LMSHolidayListPage holidayList = new LMSHolidayListPage(DriverProperties.getDriver());

	@Given("user is already on the LMS homepage")
	public void user_is_already_on_the_lms_homepage(DataTable dataTable) {

		List<Map<String, String>> credList = dataTable.asMaps();
		String url = credList.get(0).get("url");
		
		DriverProperties.getDriver().get(url);

	}

	@Given("user clicks on Holidays link on the page")
	public void user_clicks_on_holidays_link_on_the_page() 
	{
		homePage.openHolidayList();
	}

	@Then("office holiday list window is displayed")
	public void office_holiday_list_window_is_displayed() throws InterruptedException
	{
		String actualWinTitle = holidayList.getWindowTitle();
		System.out.println("actualWinTitle");
		// Compare the window title with the expected title
		String expectedWinTitle = "Office Holiday list";
		Assert.assertEquals(expectedWinTitle, actualWinTitle);
		System.out.println("HolidayList is displayed- Test Success");
	}

}
