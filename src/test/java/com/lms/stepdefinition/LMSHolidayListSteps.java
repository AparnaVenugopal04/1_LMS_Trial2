package com.lms.stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static final Logger logger = LogManager.getLogger(LMSHolidayListSteps.class);

	@Given("user is already on the LMS homepage")
	public void user_is_already_on_the_lms_homepage(DataTable dataTable) {

		logger.info("TestCase 2 : Execution started");
		List<Map<String, String>> credList = dataTable.asMaps();
		String url = credList.get(0).get("url");

		DriverProperties.getDriver().get(url);
		logger.info("Entered the url for LMS :" + url);
	}

	@Given("user clicks on Holidays link on the page")
	public void user_clicks_on_holidays_link_on_the_page() {
		homePage.openHolidayList();
	}

	@Then("office holiday list window is displayed")
	public void office_holiday_list_window_is_displayed() throws InterruptedException {
		String actualWinTitle = holidayList.getWindowTitle();
		System.out.println("actualWinTitle");
		// Compare the window title with the expected title
		String expectedWinTitle = "Office Holiday list"; // Test Case - Pass
		// String expectedWinTitle = "Office Holiday"; //Test Case - Fail
		Assert.assertEquals(expectedWinTitle, actualWinTitle);
		logger.info("LMS Holiday List is displayed");
		logger.info("LMS Holiday List page title is :" + actualWinTitle);

	}

	@Then("user validates count of Public Holidays is greater than or equal to ten")
	public void user_validates_count_of_public_holidays_is_less_than_or_equal_to_ten() throws IOException {
		holidayList.countOfPublicHolidays();
		logger.info("Count of Public Holdiays is greater than or equal to ten- Test Case Passed");

	}

	@Then("user can generate a report based on the holiday type")
	public void user_can_generate_a_report_based_on_the_holiday_type() throws IOException{
	try {
		{
			holidayList.generateHoldiayExcelReport();
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
