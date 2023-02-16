package com.lms.common;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private DriverProperties driverProp;
	private ConfigReader configReader;
	Properties prop;
	LoggerHelper logger = new LoggerHelper();

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
		logger.UpdateLog("Closing the browser after test execution");
		logger.UpdateLog("*************************************************");
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) throws IOException {
		// take screenshot:
		String screenshotName = scenario.getName().replaceAll(" ", "_");

		byte[] sourcePath = ((TakesScreenshot) driverProp.getDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.attach(sourcePath, "image/png", screenshotName);
		logger.UpdateLog("Screenshot captured for the test case");

		
		
	}
}
