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
                    .collect(Collectors.toMap(e -> String.join("-", e.getCountry(), e.getColumnName(), e.getTemplate()).toUpperCase(),
                            Function.identity(), (a, b) -> a));

            Workbook workbook = WorkbookFactory.getWorkbook();
            Sheet sheet = workbook.getSheetAt(0);

            //output excel
            Workbook newWorkbook = WorkbookFactory.createWorkbook(workbook);
            Sheet newSheet = newWorkbook.createSheet(sheet.getSheetName());

            Iterator<Row> rowIterator = sheet.iterator();
            Row headerRow = rowIterator.next();
            List<String> headerList = getHeaders(headerRow);
            int statusColPosition = 0;
            Cell sourceCell = headerRow.cellIterator().next();
            WorkbookFactory.writeHeaders(headerList, newSheet, sourceCell);
            int rowNum = 1;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Row newRow = newSheet.createRow(rowNum++);
                Boolean rowStatus = null;
                StringBuffer rowErrorMsg = new StringBuffer();//10 0-9 ,12 212
                for (int colNum = 2; colNum < headerList.size(); colNum++) {
                    String header = headerList.get(colNum);
                    CountryRulesVO countryRulesVO = getCountryRules(headerList, header, countryMap, row);
                    Cell cell = row.getCell(colNum - 2);
                    if (cell == null) {
                        return;
                    }
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
                    }
                    WorkbookFactory.cloneStyle(cell, newCell);
                    if (cellResult != null && rowStatus == null /*|| rowStatus*/) {
                        rowStatus = cellResult == true;
                    }
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

    private static CountryRulesVO getCountryRules(List<String> headerList, String header, Map<String, CountryRulesVO> countryMap, Row row) {
        int position = headerList.indexOf("PAYCTRYCODE");
        String template = "T1";
        if (position < 0) {
            position = headerList.indexOf("DEBIT COUNTRY CODE");
            template = "T2";
        }
        CountryRulesVO countryRulesVO = null;
        if (position > 0) {
            Cell country = row.getCell(position - 2);
            if (country != null) {
                String key = String.join("-", country.getStringCellValue() != null ? country.getStringCellValue() : null, header, template);
                countryRulesVO = countryMap.get(key.toUpperCase());
            }
        } else {
            //log
        }
        return countryRulesVO;
    }


}
