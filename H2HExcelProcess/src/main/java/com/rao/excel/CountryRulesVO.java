package com.rao.excel;

public class CountryRulesVO {
    private String country;//field name should be same as column name in the countryConfig.dat file
    private String columnName;
    private String mandatory;
    private String lengthCheck;
    private Integer length;
    private String specialCharsCheck;
    private String template;
    private String isAmount;
    private Double minAmount;
    private Double maxAmount;
    private String lookup;
    public CountryRulesVO(){}

    public CountryRulesVO(String country, String columnName, String mandatory, String lengthCheck, Integer length, String specialCharsCheck, String template, String isAmount, Double minAmount, Double maxAmount, String lookup) {
        this.country = country;
        this.columnName = columnName;
        this.mandatory = mandatory;
        this.lengthCheck = lengthCheck;
        this.length = length;
        this.specialCharsCheck = specialCharsCheck;
        this.template = template;
        this.isAmount = isAmount;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.lookup = lookup;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    public void setLengthCheck(String lengthCheck) {
        this.lengthCheck = lengthCheck;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setSpecialCharsCheck(String specialCharsCheck) {
        this.specialCharsCheck = specialCharsCheck;
    }

    public String getCountry() {
        return country != null ? country.trim() : "";
    }

    public String getColumnName() {
        return columnName != null ? columnName.trim() : null;
    }

    public Integer getLength() {
        return length;
    }

    public Boolean getMandatory() {
        return mandatory != null ? mandatory.equalsIgnoreCase("Y") : false;
    }

    public String getMandatoryVal(){
        return mandatory;
    }
    public String getLengthCheckVal(){
        return lengthCheck;
    }
    public String getSpecialCharsCheckVal(){
        return specialCharsCheck;
    }

    public Boolean getLengthCheck() {
        return lengthCheck != null ? lengthCheck.equalsIgnoreCase("Y") : false;
    }

    public Boolean getSpecialCharsCheck() {
        return specialCharsCheck != null ? specialCharsCheck.equalsIgnoreCase("Y") : false;
    }

    public String getTemplate() {
        return template != null ? template.trim() : null;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getIsAmount() {
        return isAmount;
    }

    public void setIsAmount(String isAmount) {
        this.isAmount = isAmount;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public String getLookup() {
        return lookup;
    }

    public void setLookup(String lookup) {
        this.lookup = lookup;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }
}
