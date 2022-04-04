package com.rao.excel.validation;

import com.rao.excel.LookUpVO;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.Map;

public class PurposeCodeValidation extends SpecialValidation {

    public Boolean template1Validation(Row row, String colName, String purposeCode, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template) {
        String paymentType = getValue(row, headerList, "PAYMENTTYPE");
        String countryCode = getValue(row, headerList, "DEBITCTRYCODE");
        String crccy = getValue(row, headerList, "CRCCY");
        String banebankcode = getValue(row, headerList, "BENEBANKCODE");
        if ("HK".equalsIgnoreCase(countryCode) && "TT".equalsIgnoreCase(paymentType) && "CNT".equalsIgnoreCase(crccy)) {
            String code = banebankcode.substring(5, 7);
            if (isBlank(purposeCode) && code.equalsIgnoreCase("CN")) {
                rowErrorMsg.append(colName + "- is blank, ");
                return false;
            } else if (!isBlank(purposeCode) && banebankcode.substring(5, 2).equalsIgnoreCase("CN")) {
                LookUpVO lookUpVO = lookUpMap.get(String.join("-", countryCode, template, colName));
                if (lookUpVO != null && !lookUpVO.getValuesAsList().contains(purposeCode)) {
                    rowErrorMsg.append(colName + "-value doesn't exists, ");
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean template2Validation(Row row, String colName, String purposeCode, List<String> headerList, StringBuffer rowErrorMsg, Map<String, LookUpVO> lookUpMap, String template) {
        return null;
    }
}
