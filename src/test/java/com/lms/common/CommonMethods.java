package com.lms.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lms.stepdefinition.LMSHolidayListSteps;

public class CommonMethods {

	private static final Logger logger = LogManager.getLogger(LMSHolidayListSteps.class);

	
	
	public void Updatelog(String message)
    {
        logger.info(message);
    }
	
	public void Updatelog(String message,AssertionError e)
    {
        logger.error(message,e);
    }
	
	public void Updatelog(String message,Exception exception)
    {
        logger.error(message,exception);
    }
}
