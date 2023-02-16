package com.lms.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lms.stepdefinition.LMSHolidayListSteps;

public class LoggerHelper {

	private static final Logger logger = LogManager.getLogger(LMSHolidayListSteps.class);

	
	
	public void UpdateLog(String message)
    {
        logger.info(message);
    }
	
	public void UpdateLog(String message,AssertionError e)
    {
        logger.error(message,e);
    }
	
	public void UpdateLog(String message,Exception exception)
    {
        logger.error(message,exception);
    }
}
