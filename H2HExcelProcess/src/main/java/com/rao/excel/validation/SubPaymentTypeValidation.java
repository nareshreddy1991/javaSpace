package com.rao.excel.validation;

import com.rao.excel.LookUpVO;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.Map;

public class SubPaymentTypeValidation extends SpecialValidation {

    public Boolean template1Validation(Row row, String colName, String subPaymentType, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template) {
        String paymentType = getValue(row, headerList, "PAYMENTTYPE");
        String countryCode = getValue(row, headerList, "DEBITCTRYCODE");
        if ("HK".equalsIgnoreCase(countryCode) && "IBFC".equalsIgnoreCase(paymentType)) {
            if (isBlank(subPaymentType)) {
                rowErrorMsg.append(colName + "- is blank, ");
                return false;
            } else if (!subPaymentType.equalsIgnoreCase("PTA") &&
                    !subPaymentType.equalsIgnoreCase("PTM") &&
                    !subPaymentType.equalsIgnoreCase("PTF")) {
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
