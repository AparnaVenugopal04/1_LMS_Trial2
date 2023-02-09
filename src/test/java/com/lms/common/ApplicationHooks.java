package com.lms.common;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private DriverProperties driverProp;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	

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
	 public void quitBrowser()
	 {
		 driverProp.getDriver().quit();
	 }

	}






