package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePage;

//This class contains Test Cases for the Field availability validation page
public class FieldAvailabilityValidationTests extends BaseTest {
//	Declaring object for Home page
	HomePage homePage;


//	Positive Test Case
	@Test(priority = 1, testName = "Validating \"Check-in\" field availability")
	public void validating_Check_in_field_availability() throws Exception {
//		Creating object for HomePage to access methods from its class
		homePage = new HomePage(driver);
//		Validating whether the "Check-in" tab/field is available in the Application
		homePage.checkForFields("Check-in");
	}

//	Positive Test Case
	@Test(priority = 2, testName = "Validating \"Flight Status\" field availability")
	public void validating_Flight_Status_field_availability() throws Exception {
//		Creating object for HomePage to access methods from its class
		homePage = new HomePage(driver);
//		Validating whether the "Flight Status" tab/field is available in the Application
		homePage.checkForFields("Flight Status");
	}
	
//	Positive Test Case
	@Test(priority = 3, testName = "Validating \"Manage Booking\" field availability")
	public void validating_Manage_Booking_field_availability() throws Exception {
//		Creating object for HomePage to access methods from its class
		homePage = new HomePage(driver);
//		Validating whether the "Manage Booking" tab/field is available in the Application
		homePage.checkForFields("Manage Booking");
	}

}
