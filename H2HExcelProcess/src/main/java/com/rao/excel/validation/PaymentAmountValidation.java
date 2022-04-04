package com.rao.excel.validation;

import com.rao.excel.LookUpVO;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.Map;

public class PaymentAmountValidation extends SpecialValidation {
    public Boolean template1Validation(Row row, String colName, String paymentAmount, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template) {
        String paymentType = getValue(row, headerList, "PAYMENTTYPE");
        String countryCode = getValue(row, headerList, "DEBITCTRYCODE");
        if ("HK".equalsIgnoreCase(countryCode) &&
                ("LBT".equalsIgnoreCase(paymentType) || "LBT".equalsIgnoreCase(paymentType))) {
            Double amount = new Double(paymentAmount);
            if (amount.compareTo(100000000d) < 0) {
                rowErrorMsg.append(colName + "- is should be less than 100000000, ");
                return false;
            } else if (!paymentAmount.equalsIgnoreCase("PTA") &&
                    !paymentAmount.equalsIgnoreCase("PTM") &&
                    !paymentAmount.equalsIgnoreCase("PTF")) {
                rowErrorMsg.append(colName + "- not a valid value, ");
                return false;
            }
        }
        return true;
    }

    public Boolean template2Validation(Row row, String colName, String paymentAmount, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template) {
        return null;
    }
}
