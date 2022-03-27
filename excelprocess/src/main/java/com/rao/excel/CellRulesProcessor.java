package com.rao.excel;

import java.util.Map;

public class CellRulesProcessor {
    private static final String Regex = "[ -\\/:-@\\[-\\`{-~]";

    public static Boolean validateCell(String value, CountryRulesVO rule, StringBuffer rowErrorMsg, String colName, Boolean isSpecial
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
                    rowErrorMsg.append(colName + "-Length exceeds:" + rule.getLength()+", ");
                    return false;
                }
            }
            if (rule.getSpecialCharsCheck()) {
                if (value.trim().matches(Regex)) {//TODO
                    rowErrorMsg.append(colName + "-Special chars found, ");
                    return false;
                }

            }
            if ("Y".equals(rule.getIsAmount())) {
                Double amount = new Double(value);
                if (amount.compareTo(rule.getMinAmount()) < 0) {
                    rowErrorMsg.append(colName + "-amount is less than "+rule.getMinAmount()+", ");
                    return false;

                }
                if (isSpecial) {
                    if (amount.compareTo(new Double(50000)) > 0) {
                        rowErrorMsg.append(colName + "-amount is greater than 50000, ");
                        return false;

                    }
                }
            }
            if ("Y".equals(rule.getLookup())) {
                LookUpVO lookUpVO = lookUpMap.get(String.join("-", template, colName));
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
