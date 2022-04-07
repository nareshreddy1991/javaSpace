package com.rao.excel.validation;

import com.rao.excel.LookUpVO;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UniqueValidation extends SpecialValidation {
    public static List<String> custRefNoList = new ArrayList<>();

    public Boolean template1Validation(Row row, String colName, String value, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template) {
        if ("CUSTOMERREFNO".equalsIgnoreCase(colName)) {
            if (!custRefNoList.contains(value)) {
                custRefNoList.add(value);
            } else {
                rowErrorMsg.append(colName + " - Duplicate Value, ");
                return false;
            }
        }
        return true;
    }

    public Boolean template2Validation(Row row, String colName, String value, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template) {
        return null;
    }

    public void reset() {
        custRefNoList.clear();
    }
}
