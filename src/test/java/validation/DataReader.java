//package validation;
//
//import java.util.*;
//
//
//import java.io.*;
//
//public class DataReader {
//
//    // Method to get a list of values for TestCase1
//    public static Map<String, List<String>> getTestDataList(String datasheet, List<String> fields) {
//        Map<String, List<String>> resultMap = new HashMap<>();
//
//        // Validate the datasheet path
//        if (datasheet == null || datasheet.isEmpty()) {
//            throw new RuntimeException("Datasheet path is not configured.");
//        }
//
//        // Read the CSV file into List of Maps
//        List<Map<String, String>> dataRows = readCSVIntoListOfMaps(datasheet);
//
//        // Loop through the fields to retrieve their values
//        for (String field : fields) {
//            boolean fieldFound = false;
//            for (Map<String, String> dataRow : dataRows) {
//                if (dataRow.get("Field").equalsIgnoreCase(field)) {
//                    String value = dataRow.get("TestCase1");
//                    if (value != null) {
//                        // Split values by semicolon to handle multiple cards
//                        List<String> values = Arrays.asList(value.split(";"));
//                        resultMap.put(field, values);
//                        fieldFound = true;
//                        break;
//                    }
//                }
//            }
//            if (!fieldFound) {
//                throw new RuntimeException(String.format("Test data for field [%s] not found in input file.", field));
//            }
//        }
//
//        return resultMap;
//    }
//
//    // Utility method to read CSV into List of Maps
//    private static List<Map<String, String>> readCSVIntoListOfMaps(String datasheet) {
//        List<Map<String, String>> dataRows = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(datasheet))) {
//            String line;
//            String[] headers = br.readLine().split(","); // Read the header row
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                Map<String, String> dataRow = new HashMap<>();
//                for (int i = 0; i < values.length; i++) {
//                    dataRow.put(headers[i], values[i]);
//                }
//                dataRows.add(dataRow);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dataRows;
//    }
//
//    public static void main(String[] args) {
//        // Define the CSV file path
//        String datasheet = "src/main/java/resources/testData.csv";
//
//        // Define the fields you want to fetch for TestCase1
//        List<String> fields = Arrays.asList("Username", "Password", "Account1linkedcards", "Account2linkedcards", "Account3linkedcards");
//
//        // Fetch the test data as a map of field name to list of values
//        Map<String, List<String>> testData = getTestDataList(datasheet, fields);
//
//        // Output the test data for each field
//        for (Map.Entry<String, List<String>> entry : testData.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//    }
//}
