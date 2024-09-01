package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.FlightBookingPage;
import pageObjects.HomePage;

//This class contains Test Cases for the Flight Search page
public class FlightSearchTests extends BaseTest {
//	Declaring object for Home page, Flight Booking page
	HomePage homePage;
	FlightBookingPage flightBookingPage;


//	Positive Test Case
	@Test(priority = 1, testName = "Entering Valid Flight Search Details for One Way Trip")
	public void entering_Valid_Flight_Search_Details_for_One_Way_Trip() throws Exception {
//		Creating object for HomePage to access methods from its class
		homePage = new HomePage(driver);
//		performing Flight Search process by passing row number of excel data sheet to extract the data 
		homePage.performFlightSearch(1);
		
	}

//	Positive Test Case
	@Test(priority = 2, testName = "Modifying Valid Flight Search Details for One Way Trip")
	public void modifying_Valid_Flight_Search_Details_for_One_Way_Trip() throws Exception {
//		Creating object for HomePage to access methods from its class
		homePage = new HomePage(driver);
//		performing Flight Search Modification process after first search by passing row number of excel data sheet to extract the data
		homePage.modifyFlightSearch(2);
	}
	
//	Negative Test Case
	@Test(priority = 3, testName = "Entering Invalid Flight Search Details for One Way Trip")
	public void entering_Invalid_Flight_Search_Details_for_One_Way_Trip() throws Exception {
//		Creating object for HomePage to access methods from its class
		homePage = new HomePage(driver);
//		performing Flight Search process by passing row number of excel data sheet to extract the data
		homePage.performFlightSearch(3);
	}
	
//	Positive Test Case
	@Test(priority = 4, testName = "Entering Valid Flight Search Details for Round Trip")
	public void entering_Valid_Flight_Search_Details_for_Round_Trip() throws Exception {
//		Creating object for HomePage to access methods from its class
		homePage = new HomePage(driver);
//		performing Flight Search process by passing row number of excel data sheet to extract the data
		homePage.performFlightSearch(4);
	}

//	Positive Test Case
	@Test(priority = 5, testName = "Modifying Valid Flight Search Details for Round Trip")
	public void modifying_Valid_Flight_Search_Details_for_Round_Trip() throws Exception {
//		Creating object for HomePage to access methods from its class
		homePage = new HomePage(driver);
//		performing Flight Search Modification process after first search by passing row number of excel data sheet to extract the data
		homePage.modifyFlightSearch(5);
	}
	
//	Negative Test Case
	@Test(priority = 6, testName = "Entering Invalid Flight Search Details for Round Trip")
	public void entering_Invalid_Flight_Search_Details_for_Round_Trip() throws Exception {
//		Creating object for HomePage to access methods from its class
		homePage = new HomePage(driver);
//		performing Flight Search process by passing row number of excel data sheet to extract the data
		homePage.performFlightSearch(6);
	}

}
