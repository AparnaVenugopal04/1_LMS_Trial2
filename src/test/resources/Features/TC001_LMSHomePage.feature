# Project : ShiftGear Training - Capstone Project - LMS
# Team :  <>

@LMSHomePage
Feature: Navigate to the Leave Management System(LMS) home page

  Scenario: Leave Management System(LMS) home page is displayed
    Given user enters valid "<url>" 
   	Then LMS homepage is displayed
    

    Examples: 
      | url  | 
      | https://lms.ey.net/ |