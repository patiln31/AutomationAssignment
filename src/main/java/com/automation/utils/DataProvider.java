package com.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProvider {
    
    public static Object[][] getTestData(String filePath, String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row dataRow = sheet.getRow(i);
                Map<String, String> data = new HashMap<>();
                
                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    Cell cell = dataRow.getCell(j);
                    String value = cell != null ? cell.toString() : "";
                    data.put(key, value);
                }
                testData.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Object[][] result = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            result[i][0] = testData.get(i);
        }
        
        return result;
    }
}