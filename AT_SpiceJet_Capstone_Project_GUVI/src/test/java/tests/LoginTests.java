package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.LoginPage;

//This class contains Test Cases for the Login page
public class LoginTests extends BaseTest {
//	Declaring object for Login page
	LoginPage login;


//	Negative Test Case
	@Test(priority = 1, testName = "Entering Invalid User Name")
	public void entering_Invalid_User_Name() throws Exception {
//		Clicks on sign up button from home page
		landingPage.openLoginPage();

//		Creating object for LoginPage to access methods from its class
		login = new LoginPage(driver);
//		Declaring the User ID data, Error Types and Error Texts to perform this test case
		String[] errorTypes = { "Mobile Number", "Email ID", "Travel Agent", "Sub Agent", "Corporate Accounts",
				"SME Travellers" };
		String[] userIDData = { "", "76543", "258966586484635428", "!@#$%^&*()", "abcdefghij" };
		String[] errorTexts = { "Please enter a valid mobile number", "Please enter a valid email address",
				"The User Id or Password entered is not valid. Please verify the User ID and Password and try again.",
				"The agent ID has been set to inactive. Please contact the airline or customer service to activate your account.",
				"This Login can be used by Corporate agents Only.",
				"No Account found. Please contact Airline/SME Administrator.", "Invalid user credentials" };
//		Validating the User ID with Invalid data like blank, numeric, alphanumeric, symbols and alphabets
		login.performUserIDValidation(userIDData, errorTypes, errorTexts);
	}

//	Negative Test Case
	@Test(priority = 2, testName = "Entering Invalid Password")
	public void entering_Invalid_Password() throws Exception {
//		Clicks on sign up button from home page
		landingPage.openLoginPage();

//		Creating object for LoginPage to access methods from its class
		login = new LoginPage(driver);

//		Declaring the password data, Error Types and Error Texts to perform this test case
		String[] errorTypes = { "Mobile Login Password", "Email Login Password", "Travel Agent", "Sub Agent", "Corporate Accounts",
				"SME Travellers" };
		String[] passwordData = { "", "76543", "258966586484635428", "!@#$%^&*()", "abcdefghij" };
		String[] errorTexts = { "Please enter a valid password", "Please enter a valid password",
				"The User Id or Password entered is not valid. Please verify the User ID and Password and try again.",
				"The agent ID has been set to inactive. Please contact the airline or customer service to activate your account.",
				"This Login can be used by Corporate agents Only.",
				"No Account found. Please contact Airline/SME Administrator."
				, "LogonRequest.Password is invalid. The value's length must be at least 0 characters and no longer than 16 characters."};
//		Validating the Password with Invalid data like blank, numeric, alphanumeric, symbols and alphabets
		login.performPasswordValidation(passwordData, errorTypes, errorTexts);
	}
	
//	Positive Test Case
	@Test(priority = 3, testName = "Entering Valid User Details")
	public void entering_Valid_User_Details() throws Exception {
//		Clicks on sign up button from home page
		landingPage.openLoginPage();

//		Creating object for LoginPage to access methods from its class
		login = new LoginPage(driver);

//		Declaring the login types to perform this test case		
		String[] loginTypes = { "Mobile Number", "Email ID", "Travel Agent", "Sub Agent", "Corporate Accounts",
				"SME Travellers" };
//		Validating the Login process to check whether it is successful or not
		login.performLoginValidation(loginTypes);
	}

}
