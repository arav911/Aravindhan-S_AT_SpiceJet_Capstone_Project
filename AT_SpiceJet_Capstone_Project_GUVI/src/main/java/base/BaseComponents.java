package base;

//import java.io.File;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

//This class contains Action methods and Common utility methods
public class BaseComponents {
//	Declaring object for WebDriver, WebDriverWait, Alert, Actions, ArrayList, HashMap and String[]
	WebDriver driver;
	WebDriverWait wait;
	Alert alert;
	Actions action;
	protected ArrayList<String> windows;
	private HashMap<String, String> data;
	String[] keys, values;

//	Declaring static variable for screenshot counter
//	public static int screenShotCounter = 1;

//	Constructor for BaseComponents class to assign "driver" and PageFactory
	public BaseComponents(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
		windows = new ArrayList<String>();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

//	Action method to perform sendKeys operation in a Text box WebElement
	protected void enterText(WebElement element, String text) {
		waitForElementToAppear(element);
		element.sendKeys(text);
	}

//	Action method to perform click operation in a button WebElement
	protected void clickButton(WebElement element) {
		try {
			waitForSeconds(5);
			waitForElementToBeClickable(element);
			moveToElement(element);
			element.click();
		} catch (Exception e) {
			action.moveToElement(element).click().build().perform();
		}
	}

//	Action method to select dropdown value
	protected void chooseDropdownValue(WebElement element, String text) {
		waitForElementToAppear(element);
		Select dropdown = new Select(element);
		dropdown.selectByValue("MR");
	}

//	Action method to switch to Child Window from the browser
	protected ArrayList<String> switchToChildWindow(WebDriver driver) {
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		while (iterator.hasNext()) {
			windows.add(iterator.next());
		}
		driver.switchTo().window(windows.get(windows.size() - 1));
		return windows;
	}

//	Action method to switch from Child Window to the Parent Window
	protected void switchToParentWindow() {
		driver.close();
		driver.switchTo().window(windows.get(0));
	}

//	Action method to switch to Frame
	protected void switchToFrame(WebElement element) {
		waitForSeconds(5);
		driver.switchTo().frame(element);
	}

//	Action method to switch from Frame to the Default Window
	protected void switchToDefaultWindow() {
		driver.switchTo().defaultContent();
	}

//	Action method to move to particular WebElement
	protected void moveToElement(WebElement element) {
		try {
			waitForSeconds(5);
			waitForElementToAppear(element);
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	Action method to move to particular WebElement
	protected void keyBoardActions(String key) {
		if (key.equalsIgnoreCase("Tab"))
			action.sendKeys(Keys.TAB).build().perform();
	}

//	Action method to get text from a WebElement
	protected String getTextContent(WebElement element) {
		try {
			waitForSeconds(5);
			waitForElementToAppear(element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element.getText();
	}

//	Action method to Handle the Alert
	protected String getAlertText() {
		String alertText = "";
		try {
			waitForSeconds(6);
			alert = driver.switchTo().alert();
			alertText = alert.getText();
			alert.accept();
			String reportInfo = "The Alert Text is \"" + alertText + "\"";

//		Setting the attribute for alert text
			ITestResult itr = Reporter.getCurrentTestResult();
			itr.setAttribute("reportInfo", reportInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alertText;
	}

//	Action method to take screenshot on the web page	
	protected void takeScreenshot(WebDriver driver) {
//		Get the stack trace
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

//      The stack trace element at index 3 represents the caller of this method
		StackTraceElement caller = stackTrace[3];

//      Extract the method name
		String testName = caller.getMethodName();
		String clsFileName = caller.getFileName();

//      Taking Screenshot in the web page
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destPath = "./Test_Results/Screenshots/" + clsFileName + "/" + testName + "/"
				+ BaseTest.screenShotCounter + "_" + testName + ".png";
		File finalDestination = new File(destPath);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseTest.screenShotCounter++;
		System.out.println("Screenshot captured successfully and placed in below path:\n" + destPath);
	}

//	Action method to take screenshot on the web page	
	protected void takeScreenshot(WebDriver driver, WebElement element) {
		try {
			waitForSeconds(5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForElementToAppear(element);
		moveToElement(element);
//		Get the stack trace
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

//      The stack trace element at index 3 represents the caller of this method
		StackTraceElement caller = stackTrace[3];

//      Extract the method name
		String testName = caller.getMethodName();
		String clsFileName = caller.getFileName();

//      Taking Screenshot in the web page
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destPath = "./Test_Results/Screenshots/" + clsFileName + "/" + testName + "/"
				+ BaseTest.screenShotCounter + "_" + testName + ".png";
		File finalDestination = new File(destPath);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseTest.screenShotCounter++;
		System.out.println("Screenshot captured successfully and placed in below path:\n" + destPath);
	}

//	Action method to perform clearing of data in a Text box WebElement
	protected void clearData(WebElement element) {
		waitForElementToAppear(element);
		element.clear();
	}

	protected void waitForSeconds(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	Action method to explicitly wait for a WebElement to appear
	protected void waitForElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

//	Action method to explicitly wait for a WebElement to appear
	protected void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

//	Action method to explicitly wait for a WebElement to disappear
	protected void waitForElementToDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

//	Action method to explicitly wait for a WebElement to appear
	protected void waitForAllElementsToAppear(List<WebElement> elements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

//	Action method to explicitly wait for a WebElement to disappear
	protected void waitForAllElementsToDisappear(List<WebElement> elements) {
		wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

//	Action method to check whether an element is available in the application
	protected boolean isElementPresent(String xpath, String action) throws Exception {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			if (!action.isEmpty()) {
				if (action.equalsIgnoreCase("Click Field"))
					clickButton(element);
			}
			if (element.isDisplayed())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

//	Action method to check whether an element is available in the application
	protected boolean isElementPresent(WebElement element, String xpath) {
		try {
			element.findElement(By.xpath(xpath));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

//	Action method to extract data from the Excel resource sheet and storing it as HashMap
	protected HashMap<String, String> getDataFromExcel(String fileName, int rowNumber) throws Exception {

//		Specify the location of file
		String filePath = "./src/main/resources/" + fileName + ".xlsx";
		File file = new File(filePath);

//		Load file
		FileInputStream fis = new FileInputStream(file);

//		Load workbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);

//		Load work sheet
		XSSFSheet sheet = wb.getSheet("Sheet1");

//		get total number of rows
//		int rows = sheet.getPhysicalNumberOfRows();

//		get total number of columns
		int columns = sheet.getRow(0).getPhysicalNumberOfCells();

//		print all the values from the excel
//		for(int i=0; i<1; i++) {
		data = new HashMap<String, String>();
		keys = new String[columns];
		values = new String[columns];
		for (int j = 0; j < columns; j++) {
			try {
				if (sheet.getRow(0).getCell(j).getCellType().equals(CellType.BLANK))
					keys[j] = null;
				if (sheet.getRow(0).getCell(j).getCellType().equals(CellType.STRING))
					keys[j] = sheet.getRow(0).getCell(j).getStringCellValue();
				if (sheet.getRow(0).getCell(j).getCellType().equals(CellType.NUMERIC))
					keys[j] = String.valueOf((int) sheet.getRow(0).getCell(j).getNumericCellValue());
				if (sheet.getRow(rowNumber).getCell(j).getCellType().equals(CellType.BLANK))
					values[j] = null;
				if (sheet.getRow(rowNumber).getCell(j).getCellType().equals(CellType.STRING))
					values[j] = sheet.getRow(rowNumber).getCell(j).getStringCellValue();
				if (sheet.getRow(rowNumber).getCell(j).getCellType().equals(CellType.NUMERIC))
					values[j] = String.valueOf((int) sheet.getRow(rowNumber).getCell(j).getNumericCellValue());
				data.put(keys[j], values[j]);
			} catch (NullPointerException e) {
				continue;
			}
		}

		for (int i = 0; i < columns; i++) {
			if (data.get(keys[i]) != null && data.get(keys[i]).contains("<>")) {
				String[] splittedValue = data.get(keys[i]).split("<>");
				data.put(keys[i], getDataFromExcel(splittedValue[0], splittedValue[1]));
			}
		}

//		Close workbook
		wb.close();

//		returning the HashMap data
		return data;

	}

//	Action method to get data from the Excel file by specifying file name and column name	
	protected String getDataFromExcel(String fileName, String columnName) throws Exception {

//		Specify the location of file
		String filePath = "./src/main/resources/" + fileName + ".xlsx";
		String readData = "";
		File file = new File(filePath);

//		Load file
		FileInputStream fis = new FileInputStream(file);

//		Load workbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);

//		Load work sheet
		XSSFSheet sheet = wb.getSheet("Sheet1");

		int columns = sheet.getRow(0).getPhysicalNumberOfCells();

//		Getting value from the respective cell
		for (int i = 0; i < columns; i++) {
			if (sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(columnName))
				readData = sheet.getRow(1).getCell(i).getStringCellValue();
		}

		readData = (readData != null) ? readData : "";

//		Close workbook
		wb.close();

		return readData;
	}
}