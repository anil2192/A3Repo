package ViaExcelAccValidation;

import java.util.List;

public class CardValidator {

    public static boolean validateCardEnding(List<String> linkedCards, String displayedCardEnding) {
        // Remove commas and spaces from displayedCardEnding
        String formattedDisplayedCardEnding = displayedCardEnding.replaceAll(",\\s*", "");

        // Check if any card from linkedCards matches the formatted card ending
        for (String card : linkedCards) {
            String lastFourDigits = card.substring(card.length() - 4);  // Get last 4 digits of the card
            if (lastFourDigits.equals(formattedDisplayedCardEnding)) {
                return true;
            }
        }

        return false;
    }
}
