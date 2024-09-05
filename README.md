# Aravindhan-S_AT_SpiceJet_Capstone_Project_GUVI

This project involves End to End Automation Testing of Sign Up, Login functionalities and Booking Flights SpiceJet Application, 
developed using tools such as Selenium WebDriver, TestNG, ExtentReport, Maven

Please find the below points to get an overview of the framework designed.

1. Identified the web pages on https://www.spicejet.com/ and created separate Java classes for each page by adding test suites and test classes to it.
2. Base Package and Classes consist of methods to perform actions on each web element, such as clicking a button or entering text into a text field, waiting for a particular element,
   extracting the alert text or text from a particular element, configuring the report setup and WebDrivers.

    ***Location: src/main/java/base***

    ***Files: BaseComponents.java, BaseTests.java, Listeners.java***

3. Data were imported in the form of an Excel sheet and properties file to configure the Browser and URL, which can be viewed under the Main Resources folder.

    ***Location: src/main/resources***

    ***Files: GlobalData.properties, SpiceJet_Search_Details.xlsx, SpiceJet_User_Details.xlsx***

4. Page Objects creation defined the web elements using the @FindBy annotation from the Selenium WebDriver library, which also used the PageFactory class to initialize the web elements.

    ***Location: src/main/java/pageObjects***

    ***Files: SignUpPage.java, LoginPage.java, HomePage.java, LandingPage.java, FlightBookingPage.java, PaymentPage.java***

5. Written both the Positive and Negative Test Cases, and used assertions to verify that the expected results are achieved. Also performed the below steps in this project:
      - Validating the Sign-Up Page by filling out Mandatory and Non-Mandatory Fields.
      - Login with dummy email ID and Password on Popup fields after clicking Login button on top of the page.
      - Searching for flights by entering dummy origin and destination information for both the One-Way and Round Trips
      - Selecting a flight and proceeding to the booking page.
      - Filling out the booking form with dummy passenger and payment information.
      - Verifying that the booking was successful by checking the resulting web page for the booking confirmation message.
      - Validating Check-in, Flight status and Manage Booking fields available on the booking page
      - Closing the web browser when testing is complete.
      - Writing code to run the test suite automatically regularly.
    
    ***Location: src/test/java/tests***

    ***Files: SignUpTests.java, LoginTests.java, FlightSearchTest.java, FlightBookingTests.java, FieldAvailabilityTests.java***

6. Extent Reports and Screenshots were generated under the Test_Results folder.

    ***Report Location: Test_Results/Reports***

    ***Screenshots Location: Test_Results/Screenshots***
