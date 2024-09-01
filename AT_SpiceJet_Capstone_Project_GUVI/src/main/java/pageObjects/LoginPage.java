package pageObjects;

import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseComponents;

//This class contains Page objects for the Login page
public class LoginPage extends BaseComponents {
//	Declaring object for WebDriver
	WebDriver driver;
	ArrayList<String> windows;
	HomePage homePage;

//	Constructor for LoginPage class to assign "driver" and PageFactory
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		windows = super.windows;
	}

//	Storing Web Elements in Login page of the SpiceJet application using @FindBy annotation
	@FindBy(xpath = "//*[text()='Mobile Number']")
	WebElement mobileNumberRadioButton;
	@FindBy(xpath = "//*[text()='Email']")
	WebElement emailRadioButton;
	@FindBy(xpath = "//*[contains(@data-testid,'user-mobileno-input-box')]")
	WebElement userIDInput;
	@FindBy(xpath = "//*[contains(@data-testid,'password-input-box')]")
	WebElement passwordInput;
	@FindBy(xpath = "//*[@id=\"main-container\"]/div/div[3]//div[5]/div[3]/div[1]")
	WebElement loginButton;
	@FindBy(linkText = "Forgot Password ?")
	WebElement forgotPassword;
	@FindBy(linkText = "Travel Agent")
	WebElement travelAgentPage;
	@FindBy(xpath = "//input[contains(@id,'UserID')]")
	WebElement agentsPageUserID;
	@FindBy(xpath = "//input[contains(@id,'AgentUserId')]")
	WebElement subAgentsPageUserID;
	@FindBy(xpath = "//input[contains(@id,'Password')]")
	WebElement agentsPagePassword;
	@FindBy(xpath = "//input[contains(@id,'LogIn')]")
	WebElement agentsPageLogin;
	@FindBy(linkText = "Sub Agent")
	WebElement subAgentPage;
	@FindBy(linkText = "Corporate Accounts")
	WebElement corporateAccountsPage;
	@FindBy(linkText = "SME Travellers")
	WebElement SMETravellersPage;
	@FindBy(id = "UserName")
	WebElement SMEPageUserID;
	@FindBy(id = "LoginPassword")
	WebElement SMEPagePassword;
	@FindBy(className = "loginButton")
	WebElement SMEPageLoginButton;
	@FindBy(xpath = "//*[@id='errorDiv']//p")
	WebElement errorText;
	@FindBy(xpath = "//*[@id=\"main-container\"]/div//div[4]/div[3]")
	WebElement userIDErrorText;
	@FindBy(xpath = "//*[@id=\"main-container\"]//div[5]/div[1]/div[3]")
	WebElement passwordErrorText;

//	Action method for getting User name from the Excel file
	public String getMobileNumber() throws Exception {
		return getDataFromExcel("SpiceJet_User_Details", "Mobile Number");
	}

//	Action method for getting Password from the Excel file
	public String getEmailID() throws Exception {
		return getDataFromExcel("SpiceJet_User_Details", "Email Address");
	}

//	Action method for getting User name from the Excel file
	public String getPassword() throws Exception {
		return getDataFromExcel("SpiceJet_User_Details", "Password");
	}

//	Action method to enter user details in the login page
	public void enterUserDetails(String loginType, String[] inputs) {
		
//		Based on the login type respective "if" block will be executed 
		if (loginType.equalsIgnoreCase("Mobile Number")) {
			clickButton(mobileNumberRadioButton);
			enterText(userIDInput, inputs[0]);
			enterText(passwordInput, inputs[1]);
			clickButton(loginButton);
		}

		if (loginType.equalsIgnoreCase("Email ID")) {
			clickButton(emailRadioButton);
			enterText(userIDInput, inputs[0]);
			enterText(passwordInput, inputs[1]);
			clickButton(loginButton);
		}

		if (loginType.equalsIgnoreCase("Mobile Login Password")) {
			clickButton(mobileNumberRadioButton);
			enterText(userIDInput, inputs[0]);
			enterText(passwordInput, inputs[1]);
			clickButton(loginButton);
		}

		if (loginType.equalsIgnoreCase("Email Login Password")) {
			clickButton(emailRadioButton);
			enterText(userIDInput, inputs[0]);
			enterText(passwordInput, inputs[1]);
			clickButton(loginButton);
		}

		if (loginType.equalsIgnoreCase("Travel Agent")) {
			clickButton(travelAgentPage);
			switchToChildWindow(driver);
			enterText(agentsPageUserID, inputs[0]);
			enterText(agentsPagePassword, inputs[1]);
			clickButton(agentsPageLogin);
		}

		if (loginType.equalsIgnoreCase("Corporate Accounts")) {
			clickButton(corporateAccountsPage);
			switchToChildWindow(driver);
			enterText(agentsPageUserID, inputs[0]);
			enterText(agentsPagePassword, inputs[1]);
			clickButton(agentsPageLogin);
		}

		if (loginType.equalsIgnoreCase("SME Travellers")) {
			clickButton(SMETravellersPage);
			switchToChildWindow(driver);
			enterText(SMEPageUserID, inputs[0]);
			enterText(SMEPagePassword, inputs[1]);
			clickButton(SMEPageLoginButton);
		}

		if (loginType.equalsIgnoreCase("Sub Agent")) {
			clickButton(subAgentPage);
			switchToChildWindow(driver);
			enterText(agentsPageUserID, inputs[0]);
			enterText(subAgentsPageUserID, inputs[0]);
			enterText(agentsPagePassword, inputs[1]);
			clickButton(agentsPageLogin);
		}
	}

//	Action method to validate Error text	
	public void validateErrorText(String Name, String expectedText) throws Exception {
		if (Name.equalsIgnoreCase("SME Travellers")) {
			Assert.assertEquals(getAlertText(), expectedText);
			switchToParentWindow();
		} else if (Name.equalsIgnoreCase("Mobile Number") || Name.equalsIgnoreCase("Email ID")) {
			waitForElementToAppear(userIDErrorText);
			Assert.assertEquals(getTextContent(userIDErrorText), expectedText);
			takeScreenshot(driver, userIDErrorText);
		} else if (Name.equalsIgnoreCase("Mobile Login Password") || Name.equalsIgnoreCase("Email Login Password")) {
			Assert.assertEquals(getTextContent(passwordErrorText), expectedText);
			takeScreenshot(driver, passwordErrorText);
		} else {
			Assert.assertEquals(getTextContent(errorText), expectedText);
			takeScreenshot(driver, errorText);
			switchToParentWindow();
		}
	}

//	Action method to validate User ID after entering the credentials based on the login type
	public void performUserIDValidation(String[] userIDData, String[] loginTypes, String[] errorTexts)
			throws Exception {

		for (int i = 0; i < userIDData.length; i++) {
			String[] data = { userIDData[i], getPassword() };
			for (int j = 0; j < loginTypes.length; j++) {
				if (((!loginTypes[j].equalsIgnoreCase("Mobile Number")) && userIDData[i].isEmpty())
						&& ((!loginTypes[j].equalsIgnoreCase("Email ID")) && userIDData[i].isEmpty())) {
					continue;
				} else {
					enterUserDetails(loginTypes[j], data);
					if (i == userIDData.length - 1 && userIDData[i].equalsIgnoreCase("abcdefghij")
							&& loginTypes[j].equalsIgnoreCase("Travel Agent")) {
						validateErrorText(loginTypes[j], errorTexts[errorTexts.length - 1]);
					} else
						validateErrorText(loginTypes[j], errorTexts[j]);
					if (loginTypes[j].equalsIgnoreCase("Mobile Number") || loginTypes[j].equalsIgnoreCase("Email")) {
						clearData(userIDInput);
						clearData(passwordInput);
					}
				}

			}

		}
	}

//	Action method to validate Password after entering the credentials based on the login type
	public void performPasswordValidation(String[] passwordData, String[] errorTypes, String[] errorTexts)
			throws Exception {

		for (int i = 0; i < passwordData.length; i++) {
			String[] data = { getMobileNumber(), passwordData[i] };
			for (int j = 0; j < errorTypes.length; j++) {
				if (((!errorTypes[j].equalsIgnoreCase("Mobile Login Password")) && passwordData[i].isEmpty())
						&& ((!errorTypes[j].equalsIgnoreCase("Email Login Password")) && passwordData[i].isEmpty())) {
					continue;
				} else {
					if (errorTypes[j].equalsIgnoreCase("Email Login Password"))
						data[0] = getEmailID();
					enterUserDetails(errorTypes[j], data);
					if (passwordData[i].length() > 16 && errorTypes[j].equalsIgnoreCase("Travel Agent")) {
						validateErrorText(errorTypes[j], errorTexts[errorTexts.length - 1]);
					} else if ((errorTypes[j].equalsIgnoreCase("Mobile Login Password") && !passwordData[i].isEmpty())
							|| (errorTypes[j].equalsIgnoreCase("Email Login Password") && !passwordData[i].isEmpty())) {
						takeScreenshot(driver);
					} else
						validateErrorText(errorTypes[j], errorTexts[j]);
					if (errorTypes[j].equalsIgnoreCase("Mobile Login Password")
							|| errorTypes[j].equalsIgnoreCase("Email Login Password")) {
						clearData(userIDInput);
						clearData(passwordInput);
					}
				}

			}

		}
	}

//	Action method to validate whether login is successful after entering the credentials based on the login type
	public void performLoginValidation(String[] loginTypes) throws Exception {

		String mobile = getMobileNumber();
		String email = getEmailID();
		String password = getPassword();
		for (int i = 0; i < loginTypes.length; i++) {

			if (loginTypes[i].equalsIgnoreCase("Mobile Number")) {
				String[] data = { mobile, password };
				enterUserDetails(loginTypes[i], data);
				homePage = new HomePage(driver);
				homePage.validateProfileName(mobile);
			} else {
				String[] data = { email, password };
				enterUserDetails(loginTypes[i], data);
				homePage = new HomePage(driver);
				homePage.validateProfileName(email);
			}
			if (loginTypes[i].equalsIgnoreCase("Mobile Number") || loginTypes[i].equalsIgnoreCase("Email")) {
				clearData(userIDInput);
				clearData(passwordInput);
			}
		}
	}

//	Action method to clear the data from the specified element
	public void clearData(WebElement element) {
		waitForElementToAppear(element);
		clickButton(element);
		enterText(element, Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
	}
}
