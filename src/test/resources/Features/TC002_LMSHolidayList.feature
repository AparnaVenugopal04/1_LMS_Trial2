# Project : ShiftGear Training - Capstone Project - LMS
# Team :  <>

@LMSHolidayList
Feature: Navigate to Holiday List on LMS and validate the count of Public Holidays

   Background:
   Given user is already on the LMS homepage

      |url|
			|https://lms.ey.net/|
      
   Scenario: User is able to validate the count of Public Holidays on LMS
    Given user clicks on Holidays link on the page
   	Then office holiday list window is displayed
		
