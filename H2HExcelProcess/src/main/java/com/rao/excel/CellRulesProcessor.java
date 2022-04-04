package com.rao.excel;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellRulesProcessor {

    public static Boolean validateCell(String value, CountryRulesVO rule, StringBuffer rowErrorMsg, String colName
            , Map<String, LookUpVO> lookUpMap, String template) {
        if (rule != null) {
            if (rule.getMandatory()) {
                if (value == null || value.trim().length() == 0) {
                    rowErrorMsg.append(colName + "-Mandatory, ");
                    return false;
                }
            }
            if (rule.getLengthCheck()) {
                if (value.trim().length() > rule.getLength()) {
                    rowErrorMsg.append(colName + "-Length exceeds:" + rule.getLength() + ", ");
                    return false;
                }
            }
            if (rule.getSpecialCharsCheck()) {
                Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                Matcher matcher = pattern.matcher(value.trim());
                if (matcher.find()) {//test it
                    rowErrorMsg.append(colName + "-Special chars found, ");
                    return false;
                }

            }
            if ("Y".equals(rule.getIsAmount())) {
                Double amount = new Double(value);
                if (rule.getMinAmount() != null && amount.compareTo(rule.getMinAmount()) < 0) {
                    rowErrorMsg.append(colName + "-amount is less than " + rule.getMinAmount() + ", ");
                    return false;
                }
                if (rule.getMaxAmount() != null && amount.compareTo(rule.getMaxAmount()) > 0) {
                    rowErrorMsg.append(colName + "-amount is greater than " + rule.getMaxAmount() + ", ");
                    return false;
                }
            }

            if ("Y".equals(rule.getLookup())) {
                LookUpVO lookUpVO = lookUpMap.get(String.join("-", rule.getCountry(), template, colName));
                if (lookUpVO != null && !lookUpVO.getValuesAsList().contains(value)) {
                    rowErrorMsg.append(colName + "-value doesn't exists, ");
                    return false;
                }
            }
            return true;
        }
        return null;
    }
}
