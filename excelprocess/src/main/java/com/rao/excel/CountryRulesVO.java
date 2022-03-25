package com.rao.excel;

public class CountryRulesVO {
    private String country;
    private String columnName;
    private String mandatory;
    private String lengthCheck;
    private Integer length;
    private String specialCharsCheck;
    private String template;

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
        return country != null ? country.trim() : null;
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
}
