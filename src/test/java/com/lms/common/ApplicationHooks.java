package com.lms.common;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.lms.pages.LMSHolidayListPage;
import com.vimalselvam.cucumber.listener.Reporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private DriverProperties driverProp;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	private static final Logger logger = LogManager.getLogger(LMSHolidayListPage.class);

	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}

	@Before(order = 1)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		driverProp = new DriverProperties();
		driverProp.init_driver(browserName);

	}

	@After(order = 0)
	public void quitBrowser() {
		driverProp.getDriver().quit();
		logger.info("Closing the browser after test execution");
		logger.info("*************************************************");
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			
			byte[] sourcePath = ((TakesScreenshot) driverProp.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
			logger.info("Screenshot captured for failed test case");
			logger.info("*************************************************");
		}
	}
}
