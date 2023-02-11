package com.lms.steprunner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
		(
		features = {"src/test/resources/Features/"},				   
		glue= {"com.lms.stepdefinition","com.lms.common"},
		plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true
				)

public class LMSTestRunner {

}
