package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.FlightBookingPage;
import pageObjects.HomePage;
import pageObjects.PaymentPage;

//This class contains Test Cases for the Flight Booking page
public class FlightBookingTests extends BaseTest {
//	Declaring object for Home page, Flight Booking and Payment Pages
	HomePage homePage;
	FlightBookingPage flightBookingPage;
	PaymentPage paymentPage;


//	Positive Test Case
	@Test(priority = 1, testName = "Entering Valid Payment Details for One Way Trip Flight Booking")
	public void entering_Valid_Payment_Details_for_One_Way_Trip_Flight_Booking() throws Exception {
//		Creating object for HomePage, FlightBookingPage and PaymentPage to access methods from its class
		homePage = new HomePage(driver);
		flightBookingPage = new FlightBookingPage(driver);
		paymentPage = new PaymentPage(driver);
		
//		performing Flight Search process by passing row number of excel data sheet to extract the data
		homePage.performFlightSearch(7);
//		performing Booking process by passing row number of excel data sheet to extract the data
		flightBookingPage.bookFlight(7);
//		performing Payment process by passing row number of excel data sheet to extract the data
		paymentPage.proceedToPayment(7);
		
	}

//	Negative Test Case
	@Test(priority = 2, testName = "Entering Invalid Payment Details for One Way Trip Flight Booking")
	public void entering_Invalid_Payment_Details_for_One_Way_Trip_Flight_Booking() throws Exception {
//		Creating object for HomePage, FlightBookingPage and PaymentPage to access methods from its class
		homePage = new HomePage(driver);
		flightBookingPage = new FlightBookingPage(driver);
		paymentPage = new PaymentPage(driver);
		
//		performing Flight Search process by passing row number of excel data sheet to extract the data
		homePage.performFlightSearch(8);
//		performing Booking process by passing row number of excel data sheet to extract the data
		flightBookingPage.bookFlight(8);
//		performing Payment process by passing row number of excel data sheet to extract the data
		paymentPage.proceedToPayment(8);
		
	}
	
//	Positive Test Case
	@Test(priority = 3, testName = "Entering Valid Payment Details for Round Trip Flight Booking")
	public void entering_Valid_Payment_Details_for_Round_Trip_Flight_Booking() throws Exception {
//		Creating object for HomePage, FlightBookingPage and PaymentPage to access methods from its class
		homePage = new HomePage(driver);
		flightBookingPage = new FlightBookingPage(driver);
		paymentPage = new PaymentPage(driver);
		
//		performing Flight Search process by passing row number of excel data sheet to extract the data
		homePage.performFlightSearch(9);
//		performing Booking process by passing row number of excel data sheet to extract the data
		flightBookingPage.bookFlight(9);
//		performing Payment process by passing row number of excel data sheet to extract the data
		paymentPage.proceedToPayment(9);
		
	}

//	Negative Test Case
	@Test(priority = 4, testName = "Entering Invalid Payment Details for Round Trip Flight Booking")
	public void entering_Invalid_Payment_Details_for_Round_Trip_Flight_Booking() throws Exception {
//		Creating object for HomePage, FlightBookingPage and PaymentPage to access methods from its class
		homePage = new HomePage(driver);
		flightBookingPage = new FlightBookingPage(driver);
		paymentPage = new PaymentPage(driver);
		
//		performing Flight Search process by passing row number of excel data sheet to extract the data
		homePage.performFlightSearch(10);
//		performing Booking process by passing row number of excel data sheet to extract the data
		flightBookingPage.bookFlight(10);
//		performing Payment process by passing row number of excel data sheet to extract the data
		paymentPage.proceedToPayment(10);
		
	}

}
