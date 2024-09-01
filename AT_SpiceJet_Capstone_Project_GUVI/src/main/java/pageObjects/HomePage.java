package pageObjects;

import java.time.LocalDate;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BaseComponents;

//This class contains Page objects for the Home page
public class HomePage extends BaseComponents {
//	Declaring object for WebDriver
	WebDriver driver;

	HashMap<String, String> data;
	LocalDate oneWayLocalDate, roundLocalDate;
	String oneWayDay, oneWayMonth, oneWayYear, roundDay, roundMonth, roundYear, dateXPath, count;

//	Constructor for HomePage class to assign "driver" and PageFactory
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Storing Web Elements in Home page of the SpiceJet application using @FindBy annotation
	@FindBy(linkText = "Sign up")
	WebElement signUpPage;
	@FindBy(xpath = "//*[text()='Login']")
	WebElement logInPage;
	@FindBy(xpath = "//*[@data-automation='account-greeting']/span")
	WebElement profileName;

	@FindBy(xpath = "//*[@data-testid='one-way-radio-button']")
	WebElement oneWayRadioButton;
	@FindBy(xpath = "//*[@data-testid='round-trip-radio-button']")
	WebElement roundTripRadioButton;
	@FindBy(xpath = "//*[@data-testid='to-testID-origin']")
	WebElement originStation;
	@FindBy(xpath = "//*[@data-testid='to-testID-destination']")
	WebElement destinationStation;
	@FindBy(xpath = "//*[@data-testid='departure-date-dropdown-label-test-id']")
	WebElement departureDate;
	@FindBy(xpath = "//*[@data-testid='return-date-dropdown-label-test-id']")
	WebElement returnDate;
	@FindBy(xpath = "//*[@data-testid='home-page-travellers']")
	WebElement passengers;
	@FindBy(xpath = "//*[@data-testid='Adult-testID-minus-one-cta']")
	WebElement minusAdult;
	@FindBy(xpath = "//*[@data-testid='Adult-testID-plus-one-cta']")
	WebElement plusAdult;
	@FindBy(xpath = "//*[@data-testid='Children-testID-minus-one-cta']")
	WebElement minusChild;
	@FindBy(xpath = "//*[@data-testid='Children-testID-plus-one-cta']")
	WebElement plusChild;
	@FindBy(xpath = "//*[@data-testid='Infant-testID-minus-one-cta']")
	WebElement minusInfant;
	@FindBy(xpath = "//*[@data-testid='Infant-testID-plus-one-cta']")
	WebElement plusInfant;
	@FindBy(xpath = "//*[@data-testid='home-page-travellers-done-cta']")
	WebElement passengersSubmit;
	@FindBy(xpath = "//*[@data-testid='home-page-flight-cta']")
	WebElement flightSearchSubmit;
	@FindBy(id = "list-results-section-0")
	WebElement flightSearchResults_1;
	@FindBy(id = "list-results-section-1")
	WebElement flightSearchResults_2;
	@FindBy(xpath = "//*[text()='Modify Search']")
	WebElement modifySearch;
	@FindBy(xpath = "//*[@data-testid='continue-search-page-cta']")
	WebElement continueSearch;

//	Action method to launch the URL
	public void goToURL(String URL) {
		driver.get(URL);
	}

//	Action method to open the Sign up page
	public void openSignUpPage() {
		clickButton(signUpPage);
	}

//	Action method to open the Login page
	public void openLogInPage() {
		clickButton(logInPage);
	}

//	Action method to get Flight Search Details to enter in the search page using row number specified
	public void getFlightSearchDetails(int rowNum) throws Exception {
		data = getDataFromExcel("SpiceJet_Search_Details", rowNum);
	}

//	Action method to validate the User name under profile section, after successfully logged in
	public void validateProfileName(String expectedText) throws Exception {
		Thread.sleep(30000);
		takeScreenshot(driver);
		expectedText = "Hi, " + expectedText.substring(0, 1) + expectedText.substring(1).toLowerCase() + "!";
//		Explicitly waiting for the profileName element to appear
		try {
			waitForElementToAppear(profileName);
			Assert.assertEquals(getTextContent(profileName), expectedText);
		} catch (Exception e) {
			Assert.assertTrue(false, "User not Registered");
		}
	}

//	Action method to to perform Flight search process in the home page
	public void performFlightSearch(int rowNum) throws Exception {
		getFlightSearchDetails(rowNum);
//		origin station details
		clickButton(originStation);
		if (data.get("Origin Station") != null) {
			clickButton(
					originStation.findElement(By.xpath("//*[contains(text(),'" + data.get("Origin Station") + "')]")));
		}
//		destination station details 
		clickButton(destinationStation);
		if (data.get("Destination Station") != null) {
			clickButton(destinationStation
					.findElement(By.xpath("//*[contains(text(),'" + data.get("Destination Station") + "')]")));
		}
//		selecting departure date as 5 weeks from current date
		if (data.get("Departure Date") != null) {
			String[] deptDate = data.get("Departure Date").split("-");
			oneWayDay = deptDate[0];
			oneWayMonth = deptDate[1];
			oneWayYear = deptDate[2];
			dateXPath = "//*[@data-testid='undefined-calendar-picker']//*[@data-testid='undefined-month-" + oneWayMonth
					+ "-" + oneWayYear + "']//*[@data-testid='undefined-calendar-day-" + oneWayDay + "']";
			clickButton(driver.findElement(By.xpath(dateXPath)));
		}
//		selecting return date as 6 weeks from current date if trip type is Round trip
		if (data.get("Trip Type") != null && data.get("Trip Type").equalsIgnoreCase("Round")) {
			clickButton(returnDate);
			String[] returnDate = data.get("Return Date").split("-");
			roundDay = returnDate[0];
			roundMonth = returnDate[1];
			roundYear = returnDate[2];
			dateXPath = "//*[@data-testid='undefined-calendar-picker']//*[@data-testid='undefined-month-" + roundMonth
					+ "-" + roundYear + "']//*[@data-testid='undefined-calendar-day-" + roundDay + "']";
			clickButton(driver.findElement(By.xpath(dateXPath)));
		}

//		providing number of Adult passengers
		clickButton(passengers);

		if (data.get("No of Adults") != null && !data.get("No of Adults").isEmpty()) {
			waitForElementToAppear(minusAdult
					.findElement(By.xpath("following-sibling::div//*[contains(@style,'font-family: inherit;')]")));
			WebElement adultCount = minusAdult
					.findElement(By.xpath("following-sibling::div//*[contains(@style,'font-family: inherit;')]"));
			count = getTextContent(adultCount);
			while (!count.equalsIgnoreCase(data.get("No of Adults"))) {
				plusAdult.click();
				count = getTextContent(adultCount);
			}
		}
//		providing number of Childern passengers
		if (data.get("No of Children") != null && !data.get("No of Children").isEmpty()) {
			waitForElementToAppear(minusChild
					.findElement(By.xpath("following-sibling::div//*[contains(@style,'font-family: inherit;')]")));
			WebElement childCount = minusChild
					.findElement(By.xpath("following-sibling::div//*[contains(@style,'font-family: inherit;')]"));
			count = getTextContent(childCount);
			while (!count.equalsIgnoreCase(data.get("No of Children"))) {
				plusChild.click();
				count = getTextContent(childCount);
			}
		}
//		providing number of Infant passengers
		if (data.get("No of Infant") != null && !data.get("No of Infant").isEmpty()) {
			waitForElementToAppear(minusInfant
					.findElement(By.xpath("following-sibling::div//*[contains(@style,'font-family: inherit;')]")));
			WebElement infantCount = minusInfant
					.findElement(By.xpath("following-sibling::div//*[contains(@style,'font-family: inherit;')]"));
			count = getTextContent(infantCount);
			while (!count.equalsIgnoreCase(data.get("No of Infant"))) {
				plusInfant.click();
				count = getTextContent(infantCount);
			}
		}

		clickButton(passengersSubmit);
		clickButton(flightSearchSubmit);
//		search result is displayed based on the given search criteria
		waitForSeconds(15);
		waitForElementToBeClickable(flightSearchResults_1);
		takeScreenshot(driver);
		if (data.get("Trip Type") != null && data.get("Trip Type").equalsIgnoreCase("Round")) {
			waitForElementToBeClickable(flightSearchResults_2);
			takeScreenshot(driver, driver.findElement(By.xpath("//*[text()='About Us']")));
		}
	}

//	Action method to modify the search after the first search
	public void modifyFlightSearch(int rowNum) throws Exception {
		performFlightSearch(rowNum);

		clickButton(modifySearch);

//		Modifying the departure date by adding 3 more days from already given date
		departureDate.click();
		String[] deptDate = data.get("Departure Date").split("-");
		oneWayDay = deptDate[0];
		oneWayMonth = deptDate[1];
		oneWayYear = deptDate[2];
		oneWayDay = String.valueOf(Integer.parseInt(oneWayDay) + 3);
		dateXPath = "//*[@data-testid='undefined-calendar-picker']//*[@data-testid='undefined-month-" + oneWayMonth
				+ "-" + oneWayYear + "']//*[@data-testid='undefined-calendar-day-" + oneWayDay + "']";
		clickButton(driver.findElement(By.xpath(dateXPath)));
//		Modifying the return date by adding 3 more days from already given date
		if (data.get("Trip Type")!=null && data.get("Trip Type").equalsIgnoreCase("Round")) {
			String[] returnDate = data.get("Return Date").split("-");
			roundDay = returnDate[0];
			roundMonth = returnDate[1];
			roundYear = returnDate[2];
			roundDay = String.valueOf(Integer.parseInt(roundDay) + 3);
			dateXPath = "//*[@data-testid='undefined-calendar-picker']//*[@data-testid='undefined-month-" + roundMonth
					+ "-" + roundYear + "']//*[@data-testid='undefined-calendar-day-" + roundDay + "']";
			clickButton(driver.findElement(By.xpath(dateXPath)));
		}
//		Modifying Passengers count
		clickButton(passengers);
		if (data.get("No of Infant") != null) {
			clickButton(minusInfant);
		} else {
			waitForElementToAppear(minusAdult
					.findElement(By.xpath("following-sibling::div//*[contains(@style,'font-family: inherit;')]")));
			WebElement adultCount = minusAdult
					.findElement(By.xpath("following-sibling::div//*[contains(@style,'font-family: inherit;')]"));
			count = getTextContent(adultCount);
			while (!count.equalsIgnoreCase("1")) {
				minusAdult.click();
				count = getTextContent(adultCount);
			}
		}
		clickButton(passengersSubmit);
		clickButton(flightSearchSubmit);
		waitForSeconds(15);
//		Search results displayed after criteria is modified
		waitForElementToBeClickable(flightSearchResults_1);
		takeScreenshot(driver);
		if (data.get("Trip Type")!=null && data.get("Trip Type").equalsIgnoreCase("Round")) {
			waitForElementToBeClickable(flightSearchResults_2);
			takeScreenshot(driver, driver.findElement(By.xpath("//*[text()='About Us']")));
		}
	}

//	Action method to check whether tabs/fields were available in the application 
	public void checkForFields(String data) throws Exception {
		String xPath = "//*[contains(@data-testid,\"" + data.toLowerCase() + "-horizontal-nav-tabs\")]";
		takeScreenshot(driver);
		Assert.assertTrue(isElementPresent(xPath, "Click Field"), xPath + " field not found in the page!!!");
	}
}
