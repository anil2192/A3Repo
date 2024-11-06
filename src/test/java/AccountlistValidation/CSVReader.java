package AccountlistValidation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVReader {

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

    public static String getUsername(Map<String, Map<String, String>> csvData, String testCase) {
        return csvData.get("Username").get(testCase);
    }

    public static String getPassword(Map<String, Map<String, String>> csvData, String testCase) {
        return csvData.get("Password").get(testCase);
    }

    public static Map<String, String> getAccountDataForTestCase(Map<String, Map<String, String>> csvData, String testCase) {
        Map<String, String> accountData = new HashMap<>();
        for (String field : csvData.keySet()) {
            if (!field.equals("Username") && !field.equals("Password")) {
                accountData.put(field, csvData.get(field).get(testCase));
            }
        }
        return accountData;
    }
}
