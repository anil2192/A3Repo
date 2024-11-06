package ViaExcelAccValidation;

public class AccountPageActions {

    public static String getDisplayedCardEnding(int accountIndex) {
        // Simulate fetching card ending from account list UI for the given account
        // In reality, this would be extracted using the provided XPath
        System.out.println("Fetching displayed card ending for Account: " + accountIndex);
        return "7, 3, 4, 8";  // Example of fetched card ending from the UI
    }
}
