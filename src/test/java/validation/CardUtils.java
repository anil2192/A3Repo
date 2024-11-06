package validation;//package validation;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
//public class CardUtils {
//
//    // Method to get last 4 digits of each card for a specific field
//    public static List<String> getLast4Digits(String fullCardNumbers) {
//        // Split the cards based on semicolon
//        String[] cards = fullCardNumbers.split(";");
//        List<String> last4Digits = new ArrayList<>();
//
//        // Iterate through each card number and get the last 4 digits
//        for (String card : cards) {
//            if (card.length() >= 4) {
//                last4Digits.add(card.substring(card.length() - 4));
//            }
//        }
//        return last4Digits;
//    }
//
//    // Method to read CSV manually without any external libraries
//    public static Map<String, String> readCsv(String csvFilePath) throws IOException {
//        Map<String, String> dataMap = new HashMap<>();
//        BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
//        String line;
//
//        // Skip the header (Field, TestCase1)
//        br.readLine();
//
//        // Read each line and populate the map with field and value
//        while ((line = br.readLine()) != null) {
//            String[] values = line.split(",");
//            if (values.length == 2) {
//                String field = values[0].trim();
//                String testData = values[1].trim();
//                dataMap.put(field, testData);
//            }
//        }
//        br.close();
//        return dataMap;
//    }
//
//    public static void main(String[] args) {
//        try {
//            // Provide the path to your CSV file
//            String csvFilePath = "src/main/java/resources/testData.csv";
//            Map<String, String> testData = readCsv(csvFilePath);
//
//            // Get the username and password
//            String username = testData.get("Username");
//            String password = testData.get("Password");
//
//            // Get last 4 digits for cards linked to each account
//            List<String> account1Last4 = getLast4Digits(testData.get("Account1linkedcards"));
//            List<String> account2Last4 = getLast4Digits(testData.get("Account2linkedcards"));
//            List<String> account3Last4 = getLast4Digits(testData.get("Account3linkedcards"));
//
//            // Output the results
//            System.out.println("Username: " + username);
//            System.out.println("Password: " + password);
//            System.out.println("Account 1 Last 4 Digits: " + account1Last4);
//            System.out.println("Account 2 Last 4 Digits: " + account2Last4);
//            System.out.println("Account 3 Last 4 Digits: " + account3Last4);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CardUtils {

    // Method to get last 4 digits of each card for a specific field
    public static List<String> getLast4Digits(String fullCardNumbers) {
        // Split the cards based on semicolon
        String[] cards = fullCardNumbers.split(";");
        List<String> last4Digits = new ArrayList<>();

        // Iterate through each card number and get the last 4 digits
        for (String card : cards) {
            if (card.length() >= 4) {
                last4Digits.add(card.substring(card.length() - 4));
            }
        }
        return last4Digits;
    }

    // Method to read CSV manually without any external libraries
    public static Map<String, Map<String, String>> readCsv(String csvFilePath) throws IOException {
        Map<String, Map<String, String>> testDataMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
        String line;

        // Read the first line (header)
        String[] headers = br.readLine().split(",");

        // Read each line and populate the map with field and test case values
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String field = values[0].trim();

            Map<String, String> testCasesData = new HashMap<>();
            for (int i = 1; i < values.length; i++) {
                testCasesData.put(headers[i].trim(), values[i].trim());
            }
            testDataMap.put(field, testCasesData);
        }
        br.close();
        return testDataMap;
    }

    public static void main(String[] args) {
        try {
            // Provide the path to your CSV file
            String csvFilePath = "src/main/java/resources/testData.csv";
            Map<String, Map<String, String>> testData = readCsv(csvFilePath);

            // Choose TestCase1 or TestCase2
            String selectedTestCase = "TestCase2";  // or "TestCase2"

            // Get the username and password for the selected test case
            String username = testData.get("Username").get(selectedTestCase);
            String password = testData.get("Password").get(selectedTestCase);

            // Get last 4 digits for cards linked to each account
            List<String> account1Last4 = getLast4Digits(testData.get("Account1linkedcards").get(selectedTestCase));
            List<String> account2Last4 = getLast4Digits(testData.get("Account2linkedcards").get(selectedTestCase));
            List<String> account3Last4 = getLast4Digits(testData.get("Account3linkedcards").get(selectedTestCase));

            // Output the results
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Account 1 Last 4 Digits: " + account1Last4);
            System.out.println("Account 2 Last 4 Digits: " + account2Last4);
            System.out.println("Account 3 Last 4 Digits: " + account3Last4);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

