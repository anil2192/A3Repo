package ViaExcelAccValidation;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtils {

    public static Map<String, Map<String, List<String>>> readExcel(String filePath) {
        Map<String, Map<String, List<String>>> testData = new LinkedHashMap<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            Iterator<Row> rowIterator = sheet.rowIterator();

            // Skip header row
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                String testCaseName = currentRow.getCell(0).getStringCellValue();
                Map<String, List<String>> dataMap = new LinkedHashMap<>();

                for (int i = 1; i < currentRow.getPhysicalNumberOfCells(); i++) {
                    String header = headerRow.getCell(i).getStringCellValue();
                    String cellValue = currentRow.getCell(i).getStringCellValue();

                    if (!dataMap.containsKey(header)) {
                        dataMap.put(header, new ArrayList<>());
                    }

                    dataMap.get(header).add(cellValue);
                }

                testData.put(testCaseName, dataMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return testData;
    }
}
