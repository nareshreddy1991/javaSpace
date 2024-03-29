package com.rao.excel;

import com.rao.excel.validation.UniqueValidation;
import com.rao.excel.validation.SpecialValidation;
import com.rao.excel.validation.ValidationFactory;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReadExcel {
    static Logger LOG = Logger.getLogger(ReadExcel.class.getName());

    public static void main(String[] args) {
        try {
            Map<String, CountryRulesVO> countryMap = ReadCountryRules.loadCountryRules();
            Map<String, LookUpVO> lookUpMap = ReadLookUpValues.loadLookUpMap();

            File[] files = listFiles();
            LOG.info("files found in inbound:" + files.length);
            for (File file : files) {
                try {
                    LOG.info("Processing file:" + file.getName());
                    Workbook workbook = WorkbookFactory.getWorkbook(file);
                    //output excel
                    Workbook newWorkbook = WorkbookFactory.createWorkbook(workbook);
                    Iterator<Sheet> sheetIterator = workbook.sheetIterator();
                    while (sheetIterator.hasNext()) {
                        Sheet sheet = sheetIterator.next();
                        LOG.info("Processing Sheet:" + sheet.getSheetName());
                        Sheet newSheet = newWorkbook.createSheet(sheet.getSheetName());

                        Iterator<Row> rowIterator = sheet.iterator();
                        Row headerRow = rowIterator.next();//Headers should be at the first row
                        List<String> headerList = getHeaders(headerRow);
                        LOG.info("Total headers:" + headerList.size());
                        int statusColPosition = 0;
                        Cell sourceCell = headerRow.cellIterator().next();
                        WorkbookFactory.writeHeaders(headerList, newSheet, sourceCell);
                        int rowNum = 1;
                        while (rowIterator.hasNext()) {
                            Row row = rowIterator.next();
                            Row newRow = newSheet.createRow(rowNum++);
                            Boolean rowStatus = null;
                            StringBuffer rowErrorMsg = new StringBuffer();
                            for (int colNum = 2; colNum < headerList.size(); colNum++) {
                                String header = headerList.get(colNum);
                                CountryRulesVO countryRulesVO = getCountryRules(headerList, header, countryMap, row);
                                Cell cell = row.getCell(colNum - 2);
                                if (cell == null) {
                                    continue;
                                }
                                Cell newCell = newRow.createCell(colNum);
                                Boolean cellResult = null;
                                switch (cell.getCellType()) {
                                    case NUMERIC:
                                        LOG.error("Found a number value, skipping validations for header:" + header);
                                        break;
                                    case STRING:
                                    default:
                                        String template = getTemplate(headerList);
                                        SpecialValidation validator = ValidationFactory.getValidator(template, header);
                                        if (validator != null) {
                                            cellResult = validator.validate(row, header, cell.getStringCellValue(), headerList, rowErrorMsg, lookUpMap, template);
                                        }
                                        if (cellResult == null || cellResult) {//check for other validation
                                            cellResult = CellRulesProcessor.validateCell(cell.getStringCellValue(), countryRulesVO, rowErrorMsg, header, lookUpMap, template);
                                        }
                                        newCell.setCellValue(cell.getStringCellValue());
                                        break;
                                }
                                WorkbookFactory.cloneStyle(cell, newCell, cellResult);
                                if (cellResult != null && (rowStatus == null || rowStatus)) {
                                    rowStatus = cellResult;
                                }
                            }
                            Cell statusCell = newRow.createCell(statusColPosition);
//                            WorkbookFactory.formatCell(statusCell, null);
                            if (rowStatus != null) {
                                statusCell.setCellValue(rowStatus ? "Success" : "Failed");
                                if (rowStatus)
                                    rowErrorMsg.append("1. Payment looks okay for processing <br/> 2. Please ensure..");//TODO fill complete msg
                            } else {
                                statusCell.setCellValue("NA");//if no rules are defined for any columns
                            }
                            Cell commentCell = newRow.createCell(statusColPosition + 1);
                            commentCell.setCellValue(rowErrorMsg.toString());
                        }
                        new UniqueValidation().reset();
                    }
                    WorkbookFactory.writeWorkbook(newWorkbook, file);
//                    file.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                    LOG.error("Something went wrong", e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Something went wrong", e);
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
        int position = -1;
        String template;
        if (headerList.indexOf("PAYCTRYCODE") >= 0) {
            template = "T1";
            position = headerList.indexOf("PAYCTRYCODE");
        } else if (headerList.indexOf("DEBIT COUNTRY CODE") >= 0) {
            template = "T2";
            position = headerList.indexOf("DEBIT COUNTRY CODE");
        } else {
            template = "T3";
        }
        CountryRulesVO countryRulesVO = null;
        if (position > 0) {//T1 T2 logic
            Cell country = row.getCell(position - 2);
            if (country != null) {
                String key = String.join("-", country.getStringCellValue() != null ? country.getStringCellValue() : null, header, template);
                countryRulesVO = countryMap.get(key.toUpperCase());
            }
        } else {//T3
            String key = String.join("-", "", header, template);
            countryRulesVO = countryMap.get(key.toUpperCase());
        }
        return countryRulesVO;
    }

    private static String getTemplate(List<String> headerList) {
        String template;
        if (headerList.indexOf("PAYCTRYCODE") >= 0) {
            template = "T1";
        } else if (headerList.indexOf("DEBIT COUNTRY CODE") >= 0) {
            template = "T2";
        } else {
            template = "T3";
        }
        return template;
    }

    private static File[] listFiles() {
        String userHome = System.getProperty("user.home");
        System.out.println(userHome);
        File inbound = new File(userHome + File.separator + Constants.INBOUND);
        File[] files = inbound.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".xls") || name.toLowerCase().endsWith(".xlsx");
            }
        });
        return files != null ? files : new File[]{};
    }


}
