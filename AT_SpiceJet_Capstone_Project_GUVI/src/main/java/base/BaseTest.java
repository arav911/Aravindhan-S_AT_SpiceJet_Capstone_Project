package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

//This class is the base to this Framework and initializes the driver instance
public class BaseTest {

//	Declaring variable for URL
	String URL;
	public static int screenShotCounter = 1;
	
//	Declaring objects for WebDriver and LandingPage 
	public WebDriver driver;
	public LandingPage landingPage;

//	Declaring String Literals to generate credentials
	String fName = "", lName = "", email = "", pwd = "", dob = "", mobNo = "", deptDate = "", returnDate = "";
	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	String numbers = "0123456789";

//	Initializing the WebDriver
	public WebDriver initializeDriver() throws IOException {
//		Loading the properties file to get "Browser" and "URL" parameter values
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("./src/main/resources/GlobalData.properties");
		properties.load(file);
		String browser = properties.getProperty("browser");
		URL = properties.getProperty("url");
		
//		driver assigned as "ChromeDriver", if browser value is "chrome"
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {  // else "FirefoxDriver" would be assigned
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(browser.equalsIgnoreCase("edge")) { // else "EdgeDriver" would be assigned
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
//		Adding implicit wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
//		Maximizing the window
		driver.manage().window().maximize();
		return driver;
	}

//	This method will be executed before every @Test method
	@BeforeMethod
	public void launchApplication() throws IOException {
//		getting the driver
		driver = initializeDriver();
		
//		creating object for HomePage to access the methods in it
		landingPage = new LandingPage(driver);
		
//		Opening the URL from HomePage
		landingPage.goToURL(URL);
	}

//	This method will be executed after every @Test method
	@AfterMethod
	public void quitBrowser() {
//		Quits the driver instance and the browser tabs
		driver.quit();
	}

	void generateUserDetails() throws Exception {
		Random random = new Random();
//	    Generate random First name
		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			fName += randomChar;
		}
		
//	    Generate random Last name
		for (int i = 0; i < 5; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			lName += randomChar;
		}
		
//	    Generate Email Address
		email = fName.toLowerCase() + lName.toLowerCase() + "@gmail.com";

//		Generate Random Password
		for (int i = 0; i < 7; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			pwd += randomChar;
		}
		int num = random.nextInt(99999);
		pwd = pwd.substring(0, 1) + pwd.substring(1).toLowerCase();
		pwd = pwd + "@" + String.valueOf(num);
		
//		Generate Date of Birth
		LocalDate localDate = LocalDate.now();
		dob = localDate.minusDays(15000).toString();
		
//		Generate Departure Date
		localDate = LocalDate.now().plusWeeks(5);
		deptDate = String.valueOf(localDate.getDayOfMonth());
		String temp = localDate.getMonth().toString();
		deptDate = deptDate + "-" + temp.substring(0, 1) + temp.substring(1).toLowerCase();
		deptDate = deptDate + "-" + String.valueOf(localDate.getYear());
		
//		Generate Return Date
		localDate = LocalDate.now().plusWeeks(6);
		returnDate = String.valueOf(localDate.getDayOfMonth());
		temp = localDate.getMonth().toString();
		returnDate = returnDate + "-" + temp.substring(0, 1) + temp.substring(1).toLowerCase();
		returnDate = returnDate + "-" + String.valueOf(localDate.getYear());
		
//		Generate Mobile Number
		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(numbers.length());
			char randomChar = numbers.charAt(index);
			mobNo += randomChar;				
		}
		if (mobNo.charAt(0) != '9' || mobNo.charAt(0) != '8' || mobNo.charAt(0) != '7')
			mobNo = mobNo.substring(0, 1).replace(mobNo.charAt(0), '9') + mobNo.substring(1);

		System.out.println("First name: " + fName + "\nLast name: " + lName + "\nEmail Address: " + email + "\nPassword: " + pwd 
				+ "\nDate of Birth: " + dob + "\nMobile Number: " + mobNo + "\nDeparture Date: " + deptDate + "\nReturn Date: " + returnDate);
//		Storing the User Details in the excel file by passing File name, user name and password as arguments
		storeUserDetails("SpiceJet_User_Details", fName, lName, email, pwd, dob, mobNo, deptDate, returnDate);
	}

	void storeUserDetails(String fileName, String fName, String lName, String email, String pwd, String dob, String mobNo, String deptDt, String rtrnDt) throws Exception {
//		creating object of workbook
		XSSFWorkbook wb = new XSSFWorkbook();

//		Giving file path where the Excel file will create
		String filePath = "./src/main/resources/" + fileName + ".xlsx";
//		creating object of FileOutputStream
		FileOutputStream fos = new FileOutputStream(filePath);

//		creating object of work sheet
		XSSFSheet sheet = wb.createSheet("Sheet1");

//		Creating ArrayList
		ArrayList<Object[]> inputData = new ArrayList<Object[]>();
		inputData.add(new Object[] { "First Name", "Last Name", "DOB", "Mobile Number", "Email Address", "Password", "Departure Date", "Return Date" });
		inputData.add(new Object[] { fName, lName, dob, mobNo, email, pwd, deptDt, rtrnDt });

		int rowNum = 0;
//		Outer loop for rows
		for (Object[] input : inputData) {
			XSSFRow row = sheet.createRow(rowNum++);
			int cellNum = 0;
//			Inner loop for columns
			for (Object inp : input) {
				XSSFCell cell = row.createCell(cellNum++);
				if (inp instanceof String)
					cell.setCellValue((String) inp);
			}
		}

//		Writing data to excel
		wb.write(fos);
		
//		Closing FileOutputStream
		fos.close();
		
//		Closing workbook
		wb.close();
		System.out.println("User Details successfully stored in "+fileName+".xlsx!!!");
	}
}
