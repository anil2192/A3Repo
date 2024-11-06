package ViaExcelAccValidation;

import ViaExcelAccValidation.ExcelUtils;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class AccountListValidation {

    @Test
    public void AccountListValidationInMIB() {
        // 1. Read input from Excel
        // Assuming readExcel returns Map<String, Map<String, List<String>>>
        Map<String, Map<String, List<String>>> testData = ExcelUtils.readExcel("src/test/java/ViaExcelAccValidation/AccountlistInput.xlsx");

        // 2. Define the test case name to run (hardcode it or pass it dynamically)
        String testCaseToRun = "TC001";  // Example: Run TC001. You can change it to TC002, TC003, etc.

        // 3. Iterate over each test case (testcasename)
        for (Map.Entry<String, Map<String, List<String>>> entry : testData.entrySet()) {
            String testCaseName = entry.getKey();  // Get test case name
            Map<String, List<String>> dataMap = entry.getValue();  // Get associated data

            // Only run the test case that matches the specified testCaseToRun
            if (!testCaseToRun.equals(testCaseName)) {
                continue;  // Skip this test case if it doesn't match
            }

            System.out.println("Executing test case: " + testCaseName);

            // 4. Get username, password, and MobileNo for login
            String username = dataMap.get("username").get(0);
            System.out.println("username");
            String password = dataMap.get("password").get(0);
            System.out.println("password");
            String mobileNo = dataMap.get("MobileNo").get(0);
            System.out.println("mobileNo");

            // Log in to the mobile application with the extracted username and password
            MobileAppActions.login(username, password);

//            // 5. Check how many accounts are present based on the number of AccountXlinkedcards
//            int numberOfAccounts = (int) dataMap.keySet().stream().filter(k -> k.startsWith("Account")).count();
//
//            // If the user has more than one account, we need to validate the account list
//            boolean shouldValidateAccountList = numberOfAccounts > 1;
//
//            if (shouldValidateAccountList) {
//                System.out.println("Multiple accounts detected for " + testCaseName + ", validating Account list.");
//
//                boolean accountListValidated = false;
//
//                // Loop through each account to validate card endings
//                for (int i = 1; i <= numberOfAccounts; i++) {
//                    List<String> linkedCards = dataMap.get("Account" + i + "linkedcards");
//
//                    if (linkedCards != null && !linkedCards.isEmpty()) {
//                        String displayedCardEnding = AccountPageActions.getDisplayedCardEnding(i);
//
//                        // Validate if any of the linked cards matches the displayed card ending
//                        boolean isValid = CardValidator.validateCardEnding(linkedCards, displayedCardEnding);
//                        if (isValid) {
//                            System.out.println("Account " + i + " (Test case: " + testCaseName + "): Valid card ending displayed: " + displayedCardEnding);
//                            accountListValidated = true;
//                        } else {
//                            System.out.println("Account " + i + " (Test case: " + testCaseName + "): No matching card ending found.");
//                        }
//                    }
//                }
//
//                if (!accountListValidated) {
//                    System.out.println("No valid account list found for " + testCaseName + ".");
//                }
//            } else {
//                // If the customer has only one account, skip validation
//                System.out.println("Single account detected for " + testCaseName + ", skipping Account list validation.");
//            }
//
//            System.out.println("Finished executing test case: " + testCaseName + "\n");
        }
    }
}
