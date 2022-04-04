package com.rao.excel;

import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDouble;
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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReadCountryRules {
    static Logger LOG = Logger.getLogger(ReadCountryRules.class.getName());

    public static Map<String, CountryRulesVO> loadCountryRules() {

        List<CountryRulesVO> countryRulesList = new ArrayList<>();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("countryConfig.dat");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try (ICsvBeanReader beanReader = new CsvBeanReader(new BufferedReader(streamReader), CsvPreference.STANDARD_PREFERENCE)) {
            final String[] headers = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();
            CountryRulesVO countryRule;
            while ((countryRule = beanReader.read(CountryRulesVO.class, headers, processors)) != null) {
                countryRulesList.addAll(formatCountry(countryRule));
            }
            Map<String, CountryRulesVO> countryMap = countryRulesList.stream()
                    .collect(Collectors.toMap(e -> String.join("-", e.getCountry(), e.getColumnName(), e.getTemplate()).toUpperCase(),
                            Function.identity(), (a, b) -> a));
            LOG.info("Country rules size:" + countryRulesList.size());
            return countryMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static List<CountryRulesVO> formatCountry(CountryRulesVO c) {
        List<CountryRulesVO> list = new ArrayList<>();
        if (c.getCountry().contains("|")) {
            String[] countryArray = c.getCountry().split("[|]");
            for (String country : countryArray) {
                if (country != null && country != "") {
                    CountryRulesVO rule = new CountryRulesVO(country, c.getColumnName(), c.getMandatoryVal(), c.getLengthCheckVal(), c.getLength()
                            , c.getSpecialCharsCheckVal(), c.getTemplate(), c.getIsAmount(), c.getMinAmount(), c.getMaxAmount(), c.getLookup());
                    list.add(rule);
                }
            }
        } else {
            list.add(c);
        }
        return list;
    }

    private static CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[]{
                new Optional(),
                new NotNull(),
                new NotNull(),
                new Optional(),
                new Optional(),
                new Optional(new ParseInt()),
                new Optional(),
                new Optional(),
                new Optional(new ParseDouble()),
                new Optional(new ParseDouble()),
                new Optional()
        };
        return processors;
    }
}
