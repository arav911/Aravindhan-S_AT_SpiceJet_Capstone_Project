package pageObjects;

import java.time.LocalDate;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseComponents;

//This class contains Page objects for the Flight Booking page
public class FlightBookingPage extends BaseComponents {
//	Declaring object for WebDriver
	WebDriver driver;

//	Declaring object for HashMap, LocalDate, String
	HashMap<String, String> data;
	LocalDate oneWayLocalDate, roundLocalDate;
	String oneWayDay, oneWayMonth, oneWayYear, roundDay, roundMonth, roundYear, dateXPath, count;

//	Constructor for Flight Booking Page class to assign "driver" and PageFactory
	public FlightBookingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Storing Web Elements in Flight Booking page of the SpiceJet application using @FindBy annotation
	@FindBy(xpath = "//*[@data-testid='continue-search-page-cta']")
	WebElement continueSearch;
	
	//WebElements for Contact Details
	@FindBy(xpath = "//*[@data-testid='title-contact-detail-card']")
	WebElement contactDetailsTitle;
	@FindBy(xpath = "//*[@data-testid='first-inputbox-contact-details']")
	WebElement contactDetailsFirstName;
	@FindBy(xpath = "//*[@data-testid='last-inputbox-contact-details']")
	WebElement contactDetailsLastName;
	@FindBy(xpath = "//*[@data-testid='contact-number-input-box']")
	WebElement contactDetailsMobileNumber;
	@FindBy(xpath = "//*[@data-testid='emailAddress-inputbox-contact-details']")
	WebElement contactDetailsEmailID;
	
	//WebElements for Passenger 1 Details
	@FindBy(xpath = "//*[contains(text(),'primary passenger')]")
	WebElement primaryPassenger;
	@FindBy(xpath = "//*[@data-testid='traveller-0-title-field']")
	WebElement passengersInfoTitle1;
	@FindBy(xpath = "//*[@data-testid='traveller-0-first-traveller-info-input-box']")
	WebElement passengersInfoFirstName1;
	@FindBy(xpath = "//*[@data-testid='traveller-0-last-traveller-info-input-box']")
	WebElement passengersInfoLastName1;
	@FindBy(xpath = "//*[@data-testid='traveller-0-travel-info-cta']")
	WebElement passengersInfoNextButton1;
	
	//WebElements for Passenger 2 Details
	@FindBy(xpath = "//*[@data-testid='traveller-1-title-field']")
	WebElement passengersInfoTitle2;
	@FindBy(xpath = "//*[@data-testid='traveller-1-first-traveller-info-input-box']")
	WebElement passengersInfoFirstName2;
	@FindBy(xpath = "//*[@data-testid='traveller-1-last-traveller-info-input-box']")
	WebElement passengersInfoLastName2;
	@FindBy(xpath = "//*[@data-testid='traveller-1-travel-info-cta']")
	WebElement passengersInfoNextButton2;
	
	//WebElements for Passenger 3 Details
	@FindBy(xpath = "//*[@data-testid='traveller-2-title-field']")
	WebElement passengersInfoTitle3;
	@FindBy(xpath = "//*[@data-testid='traveller-2-first-traveller-info-input-box']")
	WebElement passengersInfoFirstName3;
	@FindBy(xpath = "//*[@data-testid='traveller-2-last-traveller-info-input-box']")
	WebElement passengersInfoLastName3;
	@FindBy(xpath = "//*[@data-testid='traveller-2-travel-info-cta']")
	WebElement passengersInfoNextButton3;
	
	//WebElements for Passenger 4 Details
	@FindBy(xpath = "//*[@data-testid='traveller-3-title-field']")
	WebElement passengersInfoTitle4;
	@FindBy(xpath = "//*[@data-testid='traveller-3-first-traveller-info-input-box']")
	WebElement passengersInfoFirstName4;
	@FindBy(xpath = "//*[@data-testid='traveller-3-last-traveller-info-input-box']")
	WebElement passengersInfoLastName4;
	@FindBy(xpath = "//*[@data-testid='traveller-3-travel-info-cta']")
	WebElement passengersInfoNextButton4;
	
	//WebElements for Passenger 5 Details
	@FindBy(xpath = "//*[@data-testid='traveller-4-title-field']")
	WebElement passengersInfoTitle5;
	@FindBy(xpath = "//*[@data-testid='traveller-4-first-traveller-info-input-box']")
	WebElement passengersInfoFirstName5;
	@FindBy(xpath = "//*[@data-testid='traveller-4-last-traveller-info-input-box']")
	WebElement passengersInfoLastName5;
	@FindBy(xpath = "//*[@data-testid='traveller-4-travel-info-cta']")
	WebElement passengersInfoNextButton5;
	
	//WebElements for Passenger 6 Details
	@FindBy(xpath = "//*[@data-testid='traveller-5-title-field']")
	WebElement passengersInfoTitle6;
	@FindBy(xpath = "//*[@data-testid='traveller-5-first-traveller-info-input-box']")
	WebElement passengersInfoFirstName6;
	@FindBy(xpath = "//*[@data-testid='traveller-5-last-traveller-info-input-box']")
	WebElement passengersInfoLastName6;
	@FindBy(xpath = "//*[@data-testid='traveller-5-travel-info-cta']")
	WebElement passengersInfoNextButton6;
	
	//WebElements for Infant 1 Details
	@FindBy(xpath = "//*[@data-testid='title-traveller-0-infant-information']")
	WebElement infantInfoTitle1;
	@FindBy(xpath = "//*[@data-testid='first-traveller-0-infant-information']")
	WebElement infantInfoFirstName1;
	@FindBy(xpath = "//*[@data-testid='last-traveller-0-infant-information']")
	WebElement infantInfoLastName1;
	@FindBy(xpath = "//*[@data-testid='date-of-birth-traveller-0-infant-information']")
	WebElement infantInfoDOB1;
	@FindBy(xpath = "(//*[@data-testid='title-traveller-0-infant-information'])[2]")
	WebElement infantInfoTravellingWith1;
	@FindBy(xpath = "//*[@data-testid='traveller-0-travel-info-view']")
	WebElement infantInfoNextButton1;
	
	//WebElements for Infant 2 Details
	@FindBy(xpath = "//*[@data-testid='title-traveller-1-infant-information']")
	WebElement infantInfoTitle2;
	@FindBy(xpath = "//*[@data-testid='first-traveller-1-infant-information']")
	WebElement infantInfoFirstName2;
	@FindBy(xpath = "//*[@data-testid='last-traveller-1-infant-information']")
	WebElement infantInfoLastName2;
	@FindBy(xpath = "//*[@data-testid='date-of-birth-traveller-1-infant-information']")
	WebElement infantInfoDOB2;
	@FindBy(xpath = "(//*[@data-testid='title-traveller-1-infant-information'])[2]")
	WebElement infantInfoTravellingWith2;
	@FindBy(xpath = "//*[@data-testid='traveller-1-travel-info-view']")
	WebElement infantInfoNextButton2;
	
	@FindBy(xpath = "//*[@data-testid='traveller-info-continue-cta']")
	WebElement continueBookingButton;
	@FindBy(id = "at_addon_close_icon")
	WebElement addOnCloseIcon;
	@FindBy(xpath = "//*[@data-testid='add-ons-continue-footer-button']")
	WebElement addOnContinueButton;

//	Action method to get booking details to enter in the Booking Page of the application 
	public void getBookingDetails(int rowNum) throws Exception {
		data = getDataFromExcel("SpiceJet_Search_Details", rowNum);
	}

//	Action method to book a flight in the booking page after the Flight Search is successfully done 
	public void bookFlight(int rowNum) throws Exception {
		getBookingDetails(rowNum);
		waitForSeconds(7);
		clickButton(continueSearch);
//		Filling the contact details
		clickButton(contactDetailsTitle);
		clickButton(contactDetailsTitle.findElement(
				By.xpath("//following-sibling::div//*[text()='" + data.get("Contact Details Title") + "']")));
		enterText(contactDetailsFirstName, data.get("Contact Details First Name"));
		enterText(contactDetailsLastName, data.get("Contact Details Last Name"));
		enterText(contactDetailsMobileNumber, data.get("Contact Details Mobile Number"));
		enterText(contactDetailsEmailID, data.get("Contact Details Email ID"));

//		Filling the Passenger 1 details
		clickButton(primaryPassenger);
		clickButton(passengersInfoTitle1);
		clickButton(passengersInfoTitle1
				.findElement(By.xpath("//following-sibling::div//*[text()='" + data.get("Passenger 1 Title") + "']")));
		enterText(passengersInfoFirstName1, data.get("Passenger 1 First Name"));
		enterText(passengersInfoLastName1, data.get("Passenger 1 Last Name"));
		if (isElementPresent("//*[@data-testid='traveller-0-travel-info-cta']", ""))
			clickButton(passengersInfoNextButton1);

//		Filling the Passenger 2 details
		if (data.get("Passenger 2 Title") != null) {
			clickButton(passengersInfoTitle2);
			clickButton(passengersInfoTitle2.findElement(
					By.xpath("//following-sibling::div//*[text()='" + data.get("Passenger 2 Title") + "']")));
			enterText(passengersInfoFirstName2, data.get("Passenger 2 First Name"));
			enterText(passengersInfoLastName2, data.get("Passenger 2 Last Name"));
			if (isElementPresent("//*[@data-testid='traveller-1-travel-info-cta']", ""))
				clickButton(passengersInfoNextButton2);
		}

//		Filling the Passenger 3 details
		if (data.get("Passenger 3 Title") != null) {
			clickButton(passengersInfoTitle3);
			clickButton(passengersInfoTitle3.findElement(
					By.xpath("//following-sibling::div//*[text()='" + data.get("Passenger 3 Title") + "']")));
			enterText(passengersInfoFirstName3, data.get("Passenger 3 First Name"));
			enterText(passengersInfoLastName3, data.get("Passenger 3 Last Name"));
			if (isElementPresent("//*[@data-testid='traveller-2-travel-info-cta']", ""))
				clickButton(passengersInfoNextButton3);
		}

//		Filling the Passenger 4 details
		if (data.get("Passenger 4 Title") != null) {
			clickButton(passengersInfoTitle4);
			clickButton(passengersInfoTitle4.findElement(
					By.xpath("//following-sibling::div//*[text()='" + data.get("Passenger 4 Title") + "']")));
			enterText(passengersInfoFirstName4, data.get("Passenger 4 First Name"));
			enterText(passengersInfoLastName4, data.get("Passenger 4 Last Name"));
			if (isElementPresent("//*[@data-testid='traveller-3-travel-info-cta']", ""))
				clickButton(passengersInfoNextButton4);
		}

//		Filling the Passenger 5 details
		if (data.get("Passenger 5 Title") != null) {
			clickButton(passengersInfoTitle5);
			clickButton(passengersInfoTitle5.findElement(
					By.xpath("//following-sibling::div//*[text()='" + data.get("Passenger 5 Title") + "']")));
			enterText(passengersInfoFirstName5, data.get("Passenger 5 First Name"));
			enterText(passengersInfoLastName5, data.get("Passenger 5 Last Name"));
			if (isElementPresent("//*[@data-testid='traveller-4-travel-info-cta']", ""))
				clickButton(passengersInfoNextButton5);
		}
//		Filling the Passenger 6 details
		if (data.get("Passenger 6 Title") != null) {
			clickButton(passengersInfoTitle6);
			clickButton(passengersInfoTitle6.findElement(
					By.xpath("//following-sibling::div//*[text()='" + data.get("Passenger 6 Title") + "']")));
			enterText(passengersInfoFirstName6, data.get("Passenger 5 First Name"));
			enterText(passengersInfoLastName6, data.get("Passenger 5 Last Name"));
			if (isElementPresent("//*[@data-testid='traveller-5-travel-info-cta']", ""))
				clickButton(passengersInfoNextButton6);

		}
//		Filling the Infant 1 details
		if (data.get("Infant 1 Title") != null) {
			clickButton(infantInfoTitle1);
			clickButton(infantInfoTitle1
					.findElement(By.xpath("//following-sibling::div//*[text()='" + data.get("Infant 1 Title") + "']")));
			enterText(infantInfoFirstName1, data.get("Infant 1 First Name"));
			enterText(infantInfoLastName1, data.get("Infant 1 Last Name"));
			clickButton(infantInfoDOB1);
			clickButton(driver.findElement(By.xpath("//*[text()='2024']")));
			clickButton(driver.findElement(By.xpath("//*[text()='Jan']")));
			clickButton(driver.findElement(By.xpath("//*[text()='1']")));
			clickButton(infantInfoTravellingWith1);
			clickButton(driver.findElement(By.xpath(
					"((//*[@data-testid='title-traveller-0-infant-information'])[2]//following-sibling::div//div[@dir='auto'])[2]")));
			if (isElementPresent("//*[@data-testid='traveller-0-travel-info-view']", ""))
				clickButton(infantInfoNextButton1);
		}
//		Filling the Infant 2 details
		if (data.get("Infant 2 Title") != null) {
			clickButton(infantInfoTitle2);
			clickButton(infantInfoTitle2
					.findElement(By.xpath("//following-sibling::div//*[text()='" + data.get("Infant 1 Title") + "']")));
			enterText(infantInfoFirstName2, data.get("Infant 1 First Name"));
			enterText(infantInfoLastName2, data.get("Infant 1 Last Name"));
			clickButton(infantInfoDOB2);
			clickButton(driver.findElement(By.xpath("//*[text()='2024']")));
			clickButton(driver.findElement(By.xpath("//*[text()='Jan']")));
			clickButton(driver.findElement(By.xpath("//*[text()='1']")));
			clickButton(infantInfoTravellingWith2);
			clickButton(driver.findElement(By.xpath(
					"((//*[@data-testid='title-traveller-1-infant-information'])[2]//following-sibling::div//div[@dir='auto'])[2]")));
			if (isElementPresent("//*[@data-testid='traveller-1-travel-info-view']", ""))
				clickButton(infantInfoNextButton2);
		}
//		Proceeding to Payment process
		clickButton(continueBookingButton);
		waitForSeconds(10);
		takeScreenshot(driver);
	}

}
