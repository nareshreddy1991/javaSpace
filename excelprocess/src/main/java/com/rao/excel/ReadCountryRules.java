package com.rao.excel;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadCountryRules {

    public static List<CountryRulesVO> loadCountryRules() {

        List<CountryRulesVO> countryRulesList = new ArrayList<>();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("countryConfig.dat");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try (ICsvBeanReader beanReader = new CsvBeanReader(new BufferedReader(streamReader), CsvPreference.STANDARD_PREFERENCE)) {
            final String[] headers = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();
            CountryRulesVO countryRule;
            while ((countryRule = beanReader.read(CountryRulesVO.class, headers, processors)) != null) {
                countryRulesList.add(countryRule);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return countryRulesList;
    }

    private static CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[]{
                new NotNull(),
                new NotNull(),
                new NotNull(),
                new Optional(),
                new Optional(),
                new Optional(new ParseInt()),
                new Optional()
        };
        return processors;
    }
}
