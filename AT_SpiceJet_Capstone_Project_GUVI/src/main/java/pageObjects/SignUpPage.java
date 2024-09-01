package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseComponents;

//	This class contains Page objects for the Sign Up page
public class SignUpPage extends BaseComponents {
//	Declaring object for WebDriver and Random
	WebDriver driver;
	Random random;

//	Constructor for SignUpPage class to assign "driver" and PageFactory
	public SignUpPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		random = new Random();
	}

//	Storing Web Elements in sign up page of the SpiceJet application using @FindBy annotation
	@FindBy(xpath = "//*[contains(text(),'Title')]//following-sibling::select[contains(@class, 'form-control')]")
	WebElement title;
	@FindBy(id = "first_name")
	WebElement firstName;
	@FindBy(id = "last_name")
	WebElement lastName;
	@FindBy(id = "dobDate")
	WebElement dob;
	@FindBy(css = ".react-datepicker__day--selected")
	WebElement datePicker;
	@FindBy(xpath = "//*[contains(text(),'Phone')]//following-sibling::input[contains(@class, 'form-control')]")
	WebElement mobileNumber;
	@FindBy(id = "email_id")
	WebElement email;
	@FindBy(id = "new-password")
	WebElement password;
	@FindBy(id = "c-password")
	WebElement confirmPassword;
	@FindBy(id = "defaultCheck1")
	WebElement termsAndConditions;
	@FindBy(xpath = "//button[text()='Submit']")
	WebElement signUpButton;
	@FindBy(xpath = "//div[@id='otp']/input")
	List<WebElement> OTPField;
	@FindBy(xpath = "//div[contains(@class,'OTP-modal')]//button")
	WebElement verifyOTP;
	@FindBy(xpath = "//div[@class='right']")
	WebElement OTPTimer;
	@FindBy(linkText = "Resend OTP")
	WebElement OTPResend;

	@FindBy(xpath = "//*[contains(text(),'Title')]/parent::div/preceding-sibling::div/div[contains(@class, 'inlineErrors')]")
	WebElement topErrorText;
	@FindBy(xpath = "//*[@id='first_name']/following-sibling::div//div[contains(@class, 'inlineErrors')]")
	WebElement firstNameErrorText;
	@FindBy(xpath = "//*[@id='last_name']/following-sibling::div//div[contains(@class, 'inlineErrors')]")
	WebElement lastNameErrorText;
	@FindBy(xpath = "//div[@class='modal-body']//p")
	WebElement mobileNumberErrorText;
	@FindBy(xpath = "//div[@class='enterotpclose']/button")
	WebElement mobileNumberErrorTextClose;
	@FindBy(xpath = "//*[@id='email_id']/following-sibling::div//div[contains(@class, 'inlineErrors')]")
	WebElement emailErrorText;
	@FindBy(xpath = "//*[@id='new-password']/parent::div/following-sibling::div//div[contains(@class, 'inlineErrors')]")
	WebElement passwordErrorText;
	@FindBy(xpath = "//*[@id='c-password']/parent::div/following-sibling::div//div[contains(@class, 'inlineErrors')]")
	WebElement confirmPasswordErrorText;
	@FindBy(xpath = "//div[@id='otp']//following-sibling::small")
	WebElement OTPErrorText;

	@FindBy(xpath = "//*[@id='new-password']/parent::div/div//*[@alt='eye-icon']")
	WebElement showPassword;
	@FindBy(xpath = "//*[@id='c-password']/parent::div/div//*[@alt='eye-icon']")
	WebElement showConfirmPassword;

//	Action method for getting Title from the Excel file
	public String getTitle() throws Exception {
		return getDataFromExcel("SpiceJet_User_Details", "Title");
	}

//	Action method for getting First name from the Excel file
	public String getFirstName() throws Exception {
		return getDataFromExcel("SpiceJet_User_Details", "First Name");
	}

//	Action method for getting Last name from the Excel file
	public String getLastName() throws Exception {
		return getDataFromExcel("SpiceJet_User_Details", "Last Name");
	}

//	Action method for getting Email Address from the Excel file
	public String getDOB() throws Exception {
		return getDataFromExcel("SpiceJet_User_Details", "DOB");
	}

//	Action method for getting Email Address from the Excel file
	public String getMobileNumber() throws Exception {
		return getDataFromExcel("SpiceJet_User_Details", "Mobile Number");
	}

//	Action method for getting Email Address from the Excel file
	public String getEmail() throws Exception {
		return getDataFromExcel("SpiceJet_User_Details", "Email Address");
	}

//	Action method for getting Password from the Excel file
	public String getPassword() throws Exception {
		return getDataFromExcel("SpiceJet_User_Details", "Password");
	}

//	Action method to enter user details in the sign up page
	public void enterUserDetails(String[] inputs) throws Exception {
		switchToChildWindow(driver);
		chooseDropdownValue(title, inputs[0]);
		enterText(firstName, inputs[1]);
		enterText(lastName, inputs[2]);
		if (dob.getAttribute("value").isEmpty()) {
			enterText(dob, inputs[3]);
			if (!inputs[3].isEmpty())
				clickButton(datePicker);
		}
		if (mobileNumber.getAttribute("value").isEmpty()
				|| mobileNumber.getAttribute("value").equalsIgnoreCase("+91")) {
			enterText(mobileNumber, inputs[4]);
			keyBoardActions("tab");
		}
		while (email.getAttribute("value").isEmpty()) {
			enterText(email, inputs[5]);
			keyBoardActions("tab");
			if(inputs[5].isEmpty())
				break;
		}
		while (password.getAttribute("value").isEmpty()) {
			enterText(password, inputs[6]);
			if(inputs[6].isEmpty())
				break;
		}
		enterText(confirmPassword, inputs[7]);
		if (!termsAndConditions.isSelected())
			clickButton(termsAndConditions);
		clickButton(signUpButton);
	}

//	Action method to validate password checklist in the sign up page
	public void validatePasswordChecklist(String[] data) throws Exception {
		switchToChildWindow(driver);
		clickButton(showPassword);
		clickButton(showConfirmPassword);

		for (int i = 0; i < data.length; i++) {
			enterText(password, data[i]);
			enterText(confirmPassword, data[i]);
			keyBoardActions("tab");
			takeScreenshot(driver);
			clearData(password);
			clearData(confirmPassword);
		}
	}

//	Action method to to fill the OTP in the sign up page
	public void fillOTP() throws Exception {
		for (WebElement otp : OTPField) {
			int value = random.nextInt(9);
			while (value == 0) {
				value = random.nextInt(9);
			}
			enterText(otp, String.valueOf(value));
		}
	}

//	Action method to validate the OTP actions in the sign up page
	public void validateOTP(String action, String expectedText) throws Exception {
		waitForAllElementsToAppear(OTPField);
		waitForElementToAppear(verifyOTP);
		waitForElementToAppear(OTPResend);
		switchToChildWindow(driver);
//		performs till the Timer gets stopped
		if (action.contains("Timer")) {
			try {
				waitForElementToAppear(OTPTimer);
				while (!getTextContent(OTPTimer).isEmpty()) {
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				waitForElementToAppear(OTPErrorText);
				Assert.assertEquals(getTextContent(OTPErrorText), expectedText);
			}
		} 
//		performs when an incorrect OTP is entered
		else if (action.contains("Incorrect")) {
			for (WebElement otp : OTPField) {
				int value = random.nextInt(9);
				while (value == 0) {
					value = random.nextInt(9);
				}
				enterText(otp, String.valueOf(value));
			}
			clickButton(verifyOTP);
			waitForElementToAppear(OTPErrorText);
			Assert.assertEquals(getTextContent(OTPErrorText), expectedText);
		} 
//		performs when OTP is resent
		else if (action.contains("Resend")) {
			clickButton(OTPResend);
			waitForElementToAppear(OTPErrorText);
			Assert.assertEquals(getTextContent(OTPErrorText), expectedText);
		} 
//		performs when valid OTP has been entered
		else {
			clickButton(verifyOTP);
			Assert.assertEquals(getTextContent(OTPErrorText), expectedText);
		}
		takeScreenshot(driver);
		for (WebElement otp : OTPField)
			clearData(otp);
		
	}

//	Action method to validate Error text found after entering the credentials	
	public void validateErrorText(String fieldName, String expectedText) throws Exception {
		if (fieldName.equalsIgnoreCase("firstName"))
			Assert.assertEquals(getTextContent(firstNameErrorText), expectedText);
		else if (fieldName.equalsIgnoreCase("lastName"))
			Assert.assertEquals(getTextContent(lastNameErrorText), expectedText);
		else if (fieldName.equalsIgnoreCase("email"))
			Assert.assertEquals(getTextContent(emailErrorText), expectedText);
		else if (fieldName.equalsIgnoreCase("password"))
			Assert.assertEquals(getTextContent(passwordErrorText), expectedText);
		else if (fieldName.equalsIgnoreCase("MandatoryError")) {
			Assert.assertEquals(getTextContent(topErrorText), expectedText);
			takeScreenshot(driver, topErrorText);
		}
		takeScreenshot(driver);
	}

//	Action method to clear user details in the sign up page
	public void clearData() {
		ArrayList<WebElement> elements = new ArrayList<WebElement>();
		elements.add(firstName);
		elements.add(lastName);
		elements.add(email);
		elements.add(password);
		elements.add(confirmPassword);
		for (WebElement element : elements) {
			clearData(element);
		}
	}

//	Action method to clear user details in the sign up page
	public void clearData(WebElement element) {
		try {
			waitForElementToAppear(element);
			clickButton(element);
			enterText(element, Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
