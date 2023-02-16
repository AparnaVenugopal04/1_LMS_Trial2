# Project : ShiftGear Training - Capstone Project - LMS
# Team :  <>

@LMSHolidayList
Feature: Navigate to Holiday List on LMS, validate the count of Public Holidays and generate report

   Background:
   Given user is already on the LMS homepage

      |url|
			|https://lms.ey.net/|
      
   Scenario: User is able to validate the count of Public Holidays on LMS and generate report
    Given user clicks on Holidays link on the page
   	Then office holiday list window is displayed
		And user validates count of Public Holidays is greater than or equal to ten
    And user can generate a report based on the holiday type

		