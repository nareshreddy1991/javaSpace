package com.rao.excel.validation;

import com.rao.excel.LookUpVO;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.Map;

public class CRCCYValidation extends SpecialValidation {


    public Boolean template1Validation(Row row, String colName, String crcyalue, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template) {
        String paymentType = getValue(row, headerList, "PAYMENTTYPE");
        String countryCode = getValue(row, headerList, "DEBITCTRYCODE");
        if ("HK".equalsIgnoreCase(countryCode))
            if (("ACH".equalsIgnoreCase(paymentType) || "LBT".equalsIgnoreCase(paymentType))) {
                if (!"HKD".equals(crcyalue.toUpperCase())) {
                    rowErrorMsg.append(colName + "- not a valid value, ");
                    return false;
                }
            } else if ("ACH".equalsIgnoreCase(paymentType)) {
                if (!"USD".equalsIgnoreCase(crcyalue) &&
                        !"HKD".equalsIgnoreCase(crcyalue) &&
                        !"CNY".equalsIgnoreCase(crcyalue)) {
                    rowErrorMsg.append(colName + "- not a valid value, ");
                    return false;
                }
            }
        return true;
    }

    public Boolean template2Validation(Row row, String colName, String subPaymentType, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template) {
        return null;
    }
}
