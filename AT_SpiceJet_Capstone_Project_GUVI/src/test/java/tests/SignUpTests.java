package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePage;
import pageObjects.SignUpPage;

//This class contains Test Cases for the Sign Up page
public class SignUpTests extends BaseTest {
//	Declaring objects for Sign Up Home page
	SignUpPage signUp;
	HomePage homePage;

//	Negative Test Case
	@Test(priority = 1, testName = "Without entering User Details")
	public void without_entering_User_Details() throws Exception {
//		Clicks on sign up button from home page
		landingPage.openSignUpPage();

//		Creating object for SignUpPage to access methods from its class
		signUp = new SignUpPage(driver);

//		Signing up the application without entering the User Details 
		String[] data = { "MR", "", "", "", "", "", "", "" };
		signUp.enterUserDetails(data);

//		Validating the error with expected text
		signUp.validateErrorText("MandatoryError", "Please fill all mandatory fields marked with an '*' to proceed");
	}

//	Negative Test Case
	@Test(priority = 2, testName = "Entering blank Value in each field", enabled = false)
	public void entering_blank_value_in_each_Field() throws Exception {

//		Clicks on sign up button from home page
		landingPage.openSignUpPage();

//		Creating object for SignUpPage to access methods from this class
		signUp = new SignUpPage(driver);

//		Getting the User Details
		String title = signUp.getTitle();
		String firstName = signUp.getFirstName();
		String lastName = signUp.getLastName();
		String DOB = signUp.getDOB();
		String mobileNumber = signUp.getMobileNumber();
		String emailAddress = signUp.getEmail();
		String password = signUp.getPassword();
		String confirmPassword = password;

//		Declaring the data, Error Types and Error Texts to perform this test case
		String[] errorType = { "MandatoryError" };
		String[] data = { title, firstName, lastName, DOB, mobileNumber, emailAddress, password, confirmPassword };
		String[] temp = new String[data.length];
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		String[] errorTexts = { "Please fill all mandatory fields marked with an '*' to proceed" };
		int counter = 1;
//		specifying the blank value for each and every field
		while (counter < data.length) {
			data[counter] = "";
			signUp.enterUserDetails(data);
			signUp.validateErrorText(errorType[0], errorTexts[0]);
			signUp.clearData();
			data[counter] = temp[counter];
			counter++;
		}
	}

	@Test(priority = 3, testName = "Entering Numerical Value in each field")
	public void entering_numerical_value_in_each_Field() throws Exception {

//		Clicks on sign up button from home page
		landingPage.openSignUpPage();

//		Creating object for SignUpPage to access methods from this class
		signUp = new SignUpPage(driver);

//		Getting the User Details
		String title = signUp.getTitle();
		String firstName = signUp.getFirstName();
		String lastName = signUp.getLastName();
		String DOB = signUp.getDOB();
		String mobileNumber = signUp.getMobileNumber();
		String emailAddress = signUp.getEmail();
		String password = signUp.getPassword();
		String confirmPassword = password;

//		Declaring the data, Error Types and Error Texts to perform this test case
		String[] errorType = { "title", "firstName", "lastName", "DOB", "mobile", "email", "password",
				"confirmPassword" };
		String[] data = { title, firstName, lastName, DOB, mobileNumber, emailAddress, password, confirmPassword };
		String[] temp = new String[data.length];
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		String[] errorTexts = { "", "Please enter valid first name", "Please enter valid last name", "", "",
				"Please enter valid Email ID",
				"Password length should be min 8 & max 16 with at least 1 uppercase, 1 lowercase, a numeric value & a special character.",
				"Password length should be min 8 & max 16 with at least 1 uppercase, 1 lowercase, a numeric value & a special character." };
		int counter = 1;

		while (counter < data.length) {
			if (errorType[counter].equalsIgnoreCase("Title") || errorType[counter].equalsIgnoreCase("DOB")
					|| errorType[counter].equalsIgnoreCase("mobile")) {
				counter++;
				continue;
			}
//			specifying the Numeric value for each and every field
			data[counter] = "1234567890";
			signUp.enterUserDetails(data);
			signUp.validateErrorText(errorType[counter], errorTexts[counter]);
			signUp.clearData();
			data[counter] = temp[counter];
			counter++;
		}
	}

	@Test(priority = 4, testName = "Entering AlphaNumeric Value in each field")
	public void entering_AlphaNumeric_value_in_each_Field() throws Exception {

//		Clicks on sign up button from home page
		landingPage.openSignUpPage();

//		Creating object for SignUpPage to access methods from this class
		signUp = new SignUpPage(driver);

//		Getting the User Details
		String title = signUp.getTitle();
		String firstName = signUp.getFirstName();
		String lastName = signUp.getLastName();
		String DOB = signUp.getDOB();
		String mobileNumber = signUp.getMobileNumber();
		String emailAddress = signUp.getEmail();
		String password = signUp.getPassword();
		String confirmPassword = password;

//		Declaring the data, Error Types and Error Texts to perform this test case
		String[] errorType = { "title", "firstName", "lastName", "DOB", "mobile", "email", "password",
				"confirmPassword" };
		String[] data = { title, firstName, lastName, DOB, mobileNumber, emailAddress, password, confirmPassword };
		String[] temp = new String[data.length];
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		String[] errorTexts = { "", "Please enter valid first name", "Please enter valid last name", "", "",
				"Please enter valid Email ID",
				"Password length should be min 8 & max 16 with at least 1 uppercase, 1 lowercase, a numeric value & a special character.",
				"Password length should be min 8 & max 16 with at least 1 uppercase, 1 lowercase, a numeric value & a special character." };
		int counter = 1;

		while (counter < data.length) {
			if (errorType[counter].equalsIgnoreCase("Title") || errorType[counter].equalsIgnoreCase("DOB")
					|| errorType[counter].equalsIgnoreCase("mobile")) {
				counter++;
				continue;
			}
//			specifying the AlphaNumeric value for each and every field
			data[counter] = "abcde12345";
			signUp.enterUserDetails(data);
			signUp.validateErrorText(errorType[counter], errorTexts[counter]);
			signUp.clearData();
			data[counter] = temp[counter];
			counter++;
		}
	}

	@Test(priority = 5, testName = "Entering Symbolic Value in each field")
	public void entering_Symbolic_value_in_each_Field() throws Exception {

//		Clicks on sign up button from home page
		landingPage.openSignUpPage();

//		Creating object for SignUpPage to access methods from this class
		signUp = new SignUpPage(driver);

//		Getting the User Details
		String title = signUp.getTitle();
		String firstName = signUp.getFirstName();
		String lastName = signUp.getLastName();
		String DOB = signUp.getDOB();
		String mobileNumber = signUp.getMobileNumber();
		String emailAddress = signUp.getEmail();
		String password = signUp.getPassword();
		String confirmPassword = password;

//		Declaring the data, Error Types and Error Texts to perform this test case
		String[] errorType = { "title", "firstName", "lastName", "DOB", "mobile", "email", "password",
				"confirmPassword" };
		String[] data = { title, firstName, lastName, DOB, mobileNumber, emailAddress, password, confirmPassword };
		String[] temp = new String[data.length];
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		String[] errorTexts = { "", "Please enter valid first name", "Please enter valid last name", "", "",
				"Please enter valid Email ID",
				"Password length should be min 8 & max 16 with at least 1 uppercase, 1 lowercase, a numeric value & a special character.",
				"Password length should be min 8 & max 16 with at least 1 uppercase, 1 lowercase, a numeric value & a special character." };
		int counter = 1;

		while (counter < data.length) {
			if (errorType[counter].equalsIgnoreCase("Title") || errorType[counter].equalsIgnoreCase("DOB")
					|| errorType[counter].equalsIgnoreCase("mobile")) {
				counter++;
				continue;
			}
//			specifying the Symbolic value for each and every field
			data[counter] = "!@#$%^&*()";
			signUp.enterUserDetails(data);
			signUp.validateErrorText(errorType[counter], errorTexts[counter]);
			signUp.clearData();
			data[counter] = temp[counter];
			counter++;
		}
	}
	
//	Negative Test Case
	@Test(priority = 6, testName = "Password Checklist Validation")
	public void password_Checklist_Validation() throws Exception {
//		Clicks on sign up button from home page
		landingPage.openSignUpPage();

//		Creating object for SignUpPage to access methods from its class
		signUp = new SignUpPage(driver);

//		Getting the User Details
		String password = signUp.getPassword();

//		Declaring the data to perform this test case
		String[] data = { password.toUpperCase(), password.toLowerCase(), "Tusr@1", "@1234567", "Testuser@", "Testuser123456", password };
		signUp.validatePasswordChecklist(data);
	}
	
	
//	Negative Test Case
	@Test(priority = 7, testName = "OTP Validation")
	public void validating_the_OTP_Details() throws Exception {
//		Clicks on sign up button from home page
		landingPage.openSignUpPage();

//		Creating object for SignUpPage to access methods from its class
		signUp = new SignUpPage(driver);

//		Getting the User Details
		String title = signUp.getTitle();
		String firstName = signUp.getFirstName();
		String lastName = signUp.getLastName();
		String DOB = signUp.getDOB();
		String mobileNumber = signUp.getMobileNumber();
		String emailAddress = signUp.getEmail();
		String password = signUp.getPassword();
		String confirmPassword = password;

//		Declaring the data, actions and Error Texts to perform this test case
		String[] actions = { "Provide Incorrect OTP", "Wait till the Timer stops", "Resend the OTP" };
		String[] data = { title, firstName, lastName, DOB, mobileNumber, emailAddress, password, confirmPassword };
		String[] errorTexts = { "Please enter valid OTP", "OTP has expired", "OTP sent successfully!" };
		int counter = 0;

		signUp.enterUserDetails(data);
		while (counter < actions.length) {
			signUp.fillOTP();
			signUp.validateOTP(actions[counter], errorTexts[counter]);
			counter++;
		}
	}

//	Positive Test Case
	@Test(priority = 8, testName = "Entering Valid User Details")
	public void entering_Valid_User_Details() throws Exception {
//		Clicks on sign up button from home page
		landingPage.openSignUpPage();

//		Creating object for SignUpPage to access methods from its class
		signUp = new SignUpPage(driver);

//		Getting the User Details
		String title = signUp.getTitle();
		String firstName = signUp.getFirstName();
		String lastName = signUp.getLastName();
		String DOB = signUp.getDOB();
		String mobileNumber = signUp.getMobileNumber();
		String emailAddress = signUp.getEmail();
		String password = signUp.getPassword();
		String confirmPassword = password;
//		Declaring the data to perform this test case
		String[] data = { title, firstName, lastName, DOB, mobileNumber, emailAddress, password, confirmPassword };
		signUp.enterUserDetails(data);
		signUp.fillOTP();
		homePage = new HomePage(driver);
		homePage.validateProfileName("Sign up is successfull");
	}


}
