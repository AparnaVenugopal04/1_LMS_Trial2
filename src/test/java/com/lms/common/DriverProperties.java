package com.lms.common;

import static org.mockito.Mockito.RETURNS_SMART_NULLS;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lms.stepdefinition.LMSHolidayListSteps;
import org.apache.logging.log4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;

public class DriverProperties {
	
	private static final Logger logger = LogManager.getLogger(DriverProperties.class);
	public WebDriver driver;
	
	//public static DriverProperties seleniumDriver = new DriverProperties();
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();


	public WebDriver init_driver(String browser) {

		System.out.println("browser value is: " + browser);

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
			logger.info("Initiated Chrome driver");
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
			logger.info("Initiated Firefox driver");
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
			logger.info("Initiated Edge driver");
		} else {
			System.out.println("Please pass the correct browser value: " + browser);
			logger.info("Invalid browser type");
		}

		getDriver().manage().window().maximize();
		return getDriver();

	}
	
 public static WebDriver getDriver()
 {
	 return tlDriver.get();
 }
}
