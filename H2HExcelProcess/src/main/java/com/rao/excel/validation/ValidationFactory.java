package com.rao.excel.validation;

public class ValidationFactory {

    public static SpecialValidation getValidator(String template, String colName) {
        /*
        Node: For below columns no need to define any rules in countryConfig.dat file, even if you config it will not be considered
         */
        if ("T1".equals(template)) {
            if ("BENEBANKCODE".equalsIgnoreCase(colName)) {
                return new BenBankCodeValidation();
            } else if ("CRCCY".equalsIgnoreCase(colName)) {
                return new CRCCYValidation();
            } else if ("PAYMENTAMOUNT".equalsIgnoreCase(colName)) {
                return new PaymentAmountValidation();
            } else if ("PURPOSECODE".equalsIgnoreCase(colName)) {
                return new PurposeCodeValidation();
            } else if ("SUBPYMTTYPE".equalsIgnoreCase(colName)) {
                return new SubPaymentTypeValidation();
            }else if("CUSTOMERREFNO".equalsIgnoreCase(colName)){
                return new UniqueValidation();
            }
        } else if ("T1".equals(template)) {
            if ("BENEBANKCODE".equalsIgnoreCase(colName)) {
                return new BenBankCodeValidation();
            } else if ("CRCCY".equalsIgnoreCase(colName)) {
                return new CRCCYValidation();
            } else if ("PAYMENTAMOUNT".equalsIgnoreCase(colName)) {
                return new PaymentAmountValidation();
            } else if ("PURPOSECODE".equalsIgnoreCase(colName)) {
                return new PurposeCodeValidation();
            } else if ("SUBPYMTTYPE".equalsIgnoreCase(colName)) {
                return new SubPaymentTypeValidation();
            }else if("CUSTOMERREFNO".equalsIgnoreCase(colName)){
                return new UniqueValidation();
            }
        }
        return null;
    }

}
