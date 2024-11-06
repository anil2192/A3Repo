package AccountlistValidation;

import java.io.IOException;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainClass {

    public static void main(String[] args) {
        try {
            // Provide the path to your CSV file
            String csvFilePath = "src/main/java/resources/testData.csv";

            // Read the CSV file using CSVReader
            Map<String, Map<String, String>> testData = CSVReader.readCsv(csvFilePath);

            // Choose the test case to validate
            String selectedTestCase = "TestCase3";  // or "TestCase2"

            // Get the username and password for the selected test case
            String username = CSVReader.getUsername(testData, selectedTestCase);
            String password = CSVReader.getPassword(testData, selectedTestCase);
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            // Initialize WebDriver (you need to initialize the correct driver for your browser)
            WebDriver driver = new ChromeDriver(); // Replace with appropriate driver initialization

            // Instantiate CardValidator class with WebDriver
            CardValidator cardValidator = new CardValidator(driver);

            // Get the account data for the selected test case
            Map<String, String> accountData = CSVReader.getAccountDataForTestCase(testData, selectedTestCase);

            // Validate all accounts or print card endings if there's only one account
            cardValidator.validateAllAccounts(accountData);

            // Close the driver after validation
            if (driver != null) {
                driver.quit();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
