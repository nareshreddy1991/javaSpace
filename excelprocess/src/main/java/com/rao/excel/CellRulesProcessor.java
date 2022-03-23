package com.rao.excel;

public class CellRulesProcessor {
    private static final String Regex = "[ -\\/:-@\\[-\\`{-~]";//TODO common regex

    public static Boolean validateCell(String value, CountryRulesVO rule, StringBuffer rowErrorMsg, String colName) {
        if (rule != null) {
            if (rule.getMandatory()) {
                if (value == null || value.trim().length() == 0) {
                    rowErrorMsg.append(colName + "-Mandatory");
                    return false;
                }
            }
            if (rule.getLengthCheck()) {
                if (value.trim().length() > rule.getLength()) {
                    rowErrorMsg.append(colName + "-Length is exceeds:" + rule.getLength());
                    return false;
                }
            }
            if (rule.getSpecialCharsCheck()) {
                if (value.trim().matches(Regex)) {
                    rowErrorMsg.append(colName + "-Special chars found");
                    return false;
                }

            }
            return true;
        }
        return null;
    }

    public static Boolean validateCell(Double value, CountryRulesVO rule, StringBuffer rowErrorMsg, String colName) {
        if (rule != null) {
            if (rule.getMandatory()) {
                if (value == null || value == 0.0) {//TODO 0.0??
                    rowErrorMsg.append(colName + "-Mandatory");
                    return false;
                }
            }
//            if (rule.getLengthCheck()) { //TODO NA
//                if (value.trim().length() > rule.getLength())
//                    return false;
//            }
//            if (rule.getSpecialCharsCheck()) {
//                if (value.trim().matches(""))
//                    return false;
//            }
            return true;
        }
        return null;
    }
}
