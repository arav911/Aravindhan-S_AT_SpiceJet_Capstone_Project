package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseComponents;

//This class contains Page objects for the Landing page
public class LandingPage extends BaseComponents {
//	Declaring object for WebDriver
	WebDriver driver;

//	Constructor for LandingPage class to assign "driver" and PageFactory
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Storing Web Elements in Landing page of the SpiceJet application using @FindBy annotation
	@FindBy(linkText = "Signup")
	WebElement signUpPage;
	@FindBy(xpath = "//*[text()='Login']")
	WebElement logInPage;

//	Action method to launch the URL
	public void goToURL(String URL) {
		driver.get(URL);
	}

//	Action method to open the Sign up page
	public void openSignUpPage() {
		waitForElementToAppear(signUpPage);
		clickButton(signUpPage);
	}

//	Action method to open the Login page
	public void openLoginPage() {
		waitForElementToAppear(logInPage);
		clickButton(logInPage);
	}
}
