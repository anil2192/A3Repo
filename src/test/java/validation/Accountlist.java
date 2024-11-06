package validation;

import java.util.*;
import java.io.*;

public class Accountlist {

    // Method to get account-linked cards
    public static Map<String, List<String>> getAccountLinkedCards(String datasheet) {
        Map<String, List<String>> accountLinkedCards = new HashMap<>();

        // Validate the datasheet path
        if (datasheet == null || datasheet.isEmpty()) {
            throw new RuntimeException("Datasheet path is not configured.");
        }

        // Read the CSV file into List of Maps
        List<Map<String, String>> dataRows = readCSVIntoListOfMaps(datasheet);

        // Fetch account-linked cards
        for (Map<String, String> dataRow : dataRows) {
            if (dataRow.get("Field").equalsIgnoreCase("Account1linkedcards")) {
                accountLinkedCards.put("Account1linkedcards", Arrays.asList(dataRow.get("TestCase1").split(";")));
            }
            if (dataRow.get("Field").equalsIgnoreCase("Account2linkedcards")) {
                accountLinkedCards.put("Account2linkedcards", Arrays.asList(dataRow.get("TestCase1").split(";")));
            }
            if (dataRow.get("Field").equalsIgnoreCase("Account3linkedcards")) {
                accountLinkedCards.put("Account3linkedcards", Arrays.asList(dataRow.get("TestCase1").split(";")));
            }
        }

        return accountLinkedCards;
    }

    // Utility method to read CSV into List of Maps (same as previous)
    private static List<Map<String, String>> readCSVIntoListOfMaps(String datasheet) {
        List<Map<String, String>> dataRows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(datasheet))) {
            String line;
            String[] headers = br.readLine().split(","); // Read the header row
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> dataRow = new HashMap<>();
                for (int i = 0; i < values.length; i++) {
                    dataRow.put(headers[i], values[i]);
                }
                dataRows.add(dataRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataRows;
    }


    // Utility method to read CSV into List of Maps (same as previous)

    public static void main(String[] args) {
        // Define the CSV file path
        String datasheet = "src/main/java/resources/testData.csv";

        // Fetch account-linked cards data
        Map<String, List<String>> accountCards = getAccountLinkedCards(datasheet);

        // Output account linked cards
        for (Map.Entry<String, List<String>> entry : accountCards.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}


