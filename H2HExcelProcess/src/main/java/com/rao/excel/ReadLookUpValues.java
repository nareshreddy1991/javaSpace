package com.rao.excel;

import org.apache.log4j.Logger;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadLookUpValues {
    static Logger LOG = Logger.getLogger(ReadLookUpValues.class.getName());

    public static Map<String, LookUpVO> loadLookUpMap() {
        List<LookUpVO> lookupList = new ArrayList<>();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("lookup.dat");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try (ICsvBeanReader beanReader = new CsvBeanReader(new BufferedReader(streamReader), CsvPreference.STANDARD_PREFERENCE)) {
            final String[] headers = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();
            LookUpVO countryRule;
            while ((countryRule = beanReader.read(LookUpVO.class, headers, processors)) != null) {
                lookupList.add(countryRule);
            }
            LOG.info("Look up records:" + lookupList.size());
            return lookupList.stream()
                    .collect(Collectors.toMap(e -> String.join("-", e.getCountry(),e.getTemplate(), e.getHeader()), e -> e, (a, b) -> a));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[]{
                new Optional(),
                new NotNull(),
                new NotNull(),
                new NotNull()
        };
        return processors;
    }
}
