//package validation;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//public class validUserData {
//
//    // Define the fields you want to fetch for TestCase1
//    List<String> fields = Arrays.asList("Username", "Password", "Account1linkedcards", "Account2linkedcards", "Account3linkedcards");
//
//    // Fetch the test data as a map of field name to list of values
//    Map<String, List<String>> testData = DataReader.getTestDataList(fields);
//
//// Output the test data for each field
//for (Map.Entry<String, List<String>> entry : testData.entrySet()) {
//        System.out.println(entry.getKey() + ": " + entry.getValue());
//    }
//
//}
