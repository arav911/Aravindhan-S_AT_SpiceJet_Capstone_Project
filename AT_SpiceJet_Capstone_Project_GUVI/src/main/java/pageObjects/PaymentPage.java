package pageObjects;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseComponents;

//This class contains Page objects for the Payment page
public class PaymentPage extends BaseComponents {
//	Declaring object for WebDriver
	WebDriver driver;
	HashMap<String, String> data;

//	Constructor for PaymentPage class to assign "driver" and PageFactory
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Storing Web Elements in Payment page of the SpiceJet application using @FindBy annotation
	@FindBy(xpath = "//*[@data-testid='traveller-info-continue-cta']")
	WebElement continueBookingButton;
	@FindBy(id = "at_addon_close_icon")
	WebElement addOnCloseIcon;
	@FindBy(xpath = "//*[@data-testid='add-ons-continue-footer-button']")
	WebElement addOnContinueButton;
	@FindBy(id = "at_prepayment_close_icon")
	WebElement paymentCloseIcon;
	@FindBy(className = "card_number_iframe")
	WebElement cardNumberFrame;
	@FindBy(id = "card_number")
	WebElement cardNumber;
	@FindBy(id = "payment_form_card_number_error")
	WebElement cardNumberError;
	@FindBy(className = "name_on_card_iframe")
	WebElement cardHolderNameFrame;
	@FindBy(id = "name_on_card")
	WebElement cardHolderName;
	@FindBy(className = "card_exp_month_iframe")
	WebElement cardExpMonthFrame;
	@FindBy(id = "card_exp_month")
	WebElement cardExpiryMonth;
	@FindBy(className = "card_exp_year_iframe")
	WebElement cardExpYearFrame;
	@FindBy(id = "card_exp_year")
	WebElement cardExpiryYear;
	@FindBy(className = "security_code_iframe")
	WebElement cardCVVFrame;
	@FindBy(id = "security_code")
	WebElement CVV;
	@FindBy(xpath = "//*[@data-testid='paymentTnC']")
	WebElement paymentTnC;
	@FindBy(xpath = "//*[@data-testid = 'paymentTnC']//*[local-name() = 'svg']")
	WebElement paymentTnCCheckBox;
	@FindBy(xpath = "//*[@data-testid='common-proceed-to-pay']")
	WebElement proceedToPay;
	@FindBy(xpath = "//*[@id='prepayment-container']//div")
	WebElement confirmationMessage;

//	Action method to get the Payment details to enter in the Payment Page using the row number specified
	public void getPaymentDetails(int rowNum) throws Exception {
		data = getDataFromExcel("SpiceJet_Search_Details", rowNum);
	}

//	Action method to perform Payment process
	public void proceedToPayment(int rowNum) throws Exception {
		getPaymentDetails(rowNum);
		if (isElementPresent("//*[@id='at_addon_close_icon']", "")) {
			clickButton(addOnCloseIcon);
		}
		clickButton(addOnContinueButton);
		if (isElementPresent("//*[@id='at_prepayment_close_icon']", "")) {
			clickButton(paymentCloseIcon);
		}
		switchToFrame(cardNumberFrame);
		enterText(cardNumber, data.get("Card Number"));
		switchToDefaultWindow();
		switchToFrame(cardHolderNameFrame);
		enterText(cardHolderName, data.get("Card Holder Name"));
		switchToDefaultWindow();
		switchToFrame(cardExpMonthFrame);
		enterText(cardExpiryMonth, data.get("Card Expiry Month"));
		switchToDefaultWindow();
		switchToFrame(cardExpYearFrame);
		enterText(cardExpiryYear, data.get("Card Expiry Year"));
		switchToDefaultWindow();
		switchToFrame(cardCVVFrame);
		enterText(CVV, data.get("CVV"));
		switchToDefaultWindow();
		if (paymentTnCCheckBox.getAttribute("color") != null
				&& paymentTnCCheckBox.getAttribute("color").equalsIgnoreCase("#dddddd"))
			clickButton(paymentTnC);
		clickButton(proceedToPay);
		waitForSeconds(33);
		takeScreenshot(driver);
		if (data.get("Expected Message") != null)
			validateBooking(confirmationMessage, data.get("Expected Message"));
		if (data.get("Expected Error") != null)
			validateBooking(cardNumberError, data.get("Expected Error"));
	}

//	Action method to validate whether Booking is successful or not
	public void validateBooking(WebElement element, String expectedText) throws Exception {
		takeScreenshot(driver);
		Assert.assertEquals(getTextContent(element), expectedText);
		waitForSeconds(7);
	}

}
