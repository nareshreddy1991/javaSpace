package com.rao.excel.validation;

import com.rao.excel.CountryRulesVO;
import com.rao.excel.LookUpVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.Map;

public abstract class SpecialValidation {

    public Boolean validate(Row row, String colName, String benBankCode, List<String> headerList, StringBuffer rowErrorMsg,
                            Map<String, LookUpVO> lookUpMap, String template) {
        if ("T1".equals(template)) {
            return template1Validation(row, colName, benBankCode, headerList, rowErrorMsg, lookUpMap, template);
        } else if ("T2".equals(template)) {
            return template2Validation(row, colName, benBankCode, headerList, rowErrorMsg, lookUpMap, template);
        }
        return true;
    }

    public abstract Boolean template1Validation(Row row, String colName, String value, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template);

    public abstract Boolean template2Validation(Row row, String colName, String value, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template);

    public static String getValue(Row row, List<String> headerList, String header) {
        int index = headerList.indexOf(header) - 2;
        Cell cell = row.getCell(index);
        String cellVal = cell.getStringCellValue();
        return cellVal;
    }

    public static boolean isBlank(String value) {
        if (value == null || value == "" || value == " ") {
            return true;
        }
        return false;
    }
}
