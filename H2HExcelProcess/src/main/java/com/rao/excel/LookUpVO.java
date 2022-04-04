package com.rao.excel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LookUpVO {
    private String country;
    private String template;
    private String header;
    private String values;
    private List<String> valuesAsList;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        String[] split = Optional.ofNullable(values).orElse("")
                .split("[|]");
        valuesAsList = Stream.of(split).collect(Collectors.toList());
        this.values = values;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getValuesAsList() {
        return valuesAsList;
    }
}
