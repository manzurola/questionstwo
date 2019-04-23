package com.prodigy.nlp;

import com.opencsv.CSVReader;

import java.io.Reader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ContractionResolverImpl implements ContractionResolver {

    private final ConcurrentHashMap<Word, List<Word>> contractions = new ConcurrentHashMap<>();

    public ContractionResolverImpl(Reader dataFile) {
        CSVReader reader = new CSVReader(dataFile);
        for (String[] next : reader) {
            Word contraction = new Word(next[0].trim());
            List<Word> variations = Arrays.stream(next[1].split("/"))
                    .map(String::trim)
                    .map(Word::new)
                    .collect(Collectors.toList());
            contractions.put(contraction, variations);
        }
    }

    @Override
    public List<Word> resolve(Word contracted) {
        if (contractions.containsKey(contracted)) {
            return contractions.get(contracted);
        }
        return new ArrayList<>();
    }
}
