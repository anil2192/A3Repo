package AccountlistValidation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CardValidator {

    private WebDriver driver;

    public CardValidator(WebDriver driver) {
        this.driver = driver;
    }

    public String formatLast4DigitsForXPath(String fullCardNumber) {
        // Extract the last 4 digits
        String last4Digits = fullCardNumber.substring(fullCardNumber.length() - 4);

        // Convert into the format: "3, 4, 5, 6"
        char[] digits = last4Digits.toCharArray();
        String formattedDigits = String.join(", ", String.valueOf(digits[0]), String.valueOf(digits[1]),
                String.valueOf(digits[2]), String.valueOf(digits[3]));
        return formattedDigits;
    }

    public String generateDynamicXPath(String formattedCardEnding) {
        return "//*[@content-desc='NAB Edge Credit Card MasterCard Card ending " + formattedCardEnding + "']" +
                "/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[2]";
    }

    public void validateCardEnding(String fullCardNumber) {
        String formattedCardEnding = formatLast4DigitsForXPath(fullCardNumber);
        String xpath = generateDynamicXPath(formattedCardEnding);

        try {
            WebElement cardElement = driver.findElement(By.xpath(xpath));
            if (cardElement != null) {
                String displayedCardEnding = cardElement.getText(); // Adjust based on UI structure
                if (displayedCardEnding.contains(formattedCardEnding)) {
                    System.out.println("Validation Passed: Card ending " + formattedCardEnding + " is displayed.");
                } else {
                    System.out.println("Validation Failed: Expected card ending " + formattedCardEnding + ", but found " + displayedCardEnding);
                }
            }
        } catch (Exception e) {
            System.out.println("Validation Failed: Could not find card ending " + formattedCardEnding + " on the screen.");
        }
    }

    public void validateAllAccounts(Map<String, String> accountData) {
        // Check if there is more than one account with cards
        boolean multipleAccounts = accountData.keySet().stream()
                .anyMatch(key -> key.startsWith("Account") && key.contains("linkedcards") && accountData.get(key).contains(";"));

        if (multipleAccounts) {
            // If there are multiple accounts or multiple cards, perform validation
            for (String accountKey : accountData.keySet()) {
                if (accountKey.startsWith("Account") && accountKey.contains("linkedcards")) {
                    // Get the full card numbers for this account
                    String accountCards = accountData.get(accountKey);

                    // Split by semicolon to get individual cards
                    List<String> cardsList = Arrays.asList(accountCards.split(";"));

                    // Validate each card's ending
                    for (String card : cardsList) {
                        validateCardEnding(card);
                    }
                }
            }
        } else {
            // If only one account and one or more cards
            accountData.forEach((key, value) -> {
                if (key.startsWith("Account") && key.contains("linkedcards")) {
                    String[] cards = value.split(";");
                    for (String card : cards) {
                        // Print the card ending
                        String formattedCardEnding = formatLast4DigitsForXPath(card);
                        System.out.println("Card ending for account: " + formattedCardEnding);
                    }
                }
            });
            System.out.println("Skipping account validation as there is only one account with one or more cards.");
        }
    }
}
