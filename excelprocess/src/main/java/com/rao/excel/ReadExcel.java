package com.rao.excel;

import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReadExcel {
    public static void main(String[] args) {
        try {
            List<CountryRulesVO> countryRulesList = ReadCountryRules.loadCountryRules();
            Map<String, CountryRulesVO> countryMap = countryRulesList.stream()
                    .collect(Collectors.toMap(e -> String.join("-", e.getCountry(), e.getColumnName()).toUpperCase(),
                            Function.identity(), (a, b) -> a));

            Workbook workbook = WorkbookFactory.getWorkbook();
            Sheet sheet = workbook.getSheetAt(0);

            //output excel
            Workbook newWorkbook = WorkbookFactory.createWorkbook(workbook);
            Sheet newSheet = newWorkbook.createSheet(sheet.getSheetName());

            Iterator<Row> rowIterator = sheet.iterator();
            Row headerRow = rowIterator.next();
            List<String> headerList = getHeaders(headerRow);
            int statusColPosition = headerList.size() + 1;
            Cell sourceCell = headerRow.cellIterator().next();
            WorkbookFactory.writeHeaders(headerList, newSheet, sourceCell);
            String country = "India";
            int rowNum = 1;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Row newRow = newSheet.createRow(rowNum++);
                int colNum = 0;
                Boolean rowStatus = null;
                StringBuffer rowErrorMsg = new StringBuffer();
                while (cellIterator.hasNext()) {
                    String header = headerList.get(colNum);
                    String key = String.join("-", country, header);
                    CountryRulesVO countryRulesVO = countryMap.get(key.toUpperCase());
                    Cell cell = cellIterator.next();
                    Cell newCell = newRow.createCell(colNum);
                    Boolean cellResult = null;
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            System.out.print(headerList.get(colNum) + ":N:" + cell.getNumericCellValue() + "\t");
                            cellResult = CellRulesProcessor.validateCell(cell.getNumericCellValue(), countryRulesVO, rowErrorMsg, header);
                            newCell.setCellValue(cell.getNumericCellValue());
                            break;
                        case STRING:
                            System.out.print(headerList.get(colNum) + ":S:" + cell.getStringCellValue() + "\t");
                            cellResult = CellRulesProcessor.validateCell(cell.getStringCellValue(), countryRulesVO, rowErrorMsg, header);
                            newCell.setCellValue(cell.getStringCellValue());
                            break;
//                        case CELL_TYPE_BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue() + "t");
//                            newCell.setCellValue(cell.getBooleanCellValue());
//                            break;
                    }
                    WorkbookFactory.cloneStyle(cell, newCell);
                    if (cellResult != null && rowStatus == null /*|| rowStatus*/) {
                        rowStatus = cellResult == true;
                    }
                    colNum++;
                }
                System.out.println("new line");
                Cell statusCell = newRow.createCell(statusColPosition);
                WorkbookFactory.formatCell(statusCell, null);
                if (rowStatus != null) {
                    statusCell.setCellValue(rowStatus ? "SUCCESS" : "FAILED");
                } else {
                    statusCell.setCellValue("NA");//if no rules are defined for any columns
                }
                Cell commentCell = newRow.createCell(statusColPosition + 1);
                commentCell.setCellValue(rowErrorMsg.toString());
            }

            WorkbookFactory.writeWorkbook(newWorkbook);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> getHeaders(Row headerRow) {
        List<String> headerList = new ArrayList<>();
        Iterator<Cell> cellIterator = headerRow.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            headerList.add(cell.getStringCellValue());
        }
        return headerList;
    }


}
