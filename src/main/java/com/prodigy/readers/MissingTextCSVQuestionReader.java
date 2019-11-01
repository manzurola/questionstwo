package com.prodigy.questions.readers;

import com.prodigy.service.AddQuestionRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MissingTextAddQuestionRequestCSVReader extends AddQuestionRequestCSVReader {

    private static final String BLANK_TOKEN = "___";
    private static final Pattern CONTENT_PATTERN = Pattern.compile("(\\{(.*?)\\})");
    private static final int VALUE_COUNT = 5;


    public MissingTextAddQuestionRequestCSVReader(File file) throws IOException {
        super(file);
        System.out.println("MissingTextAddQuestionRequestCSVReader: " + file);
    }

    @Override
    protected AddQuestionRequest parseValues(String[] values) {
        if (values.length != VALUE_COUNT) {
            throw new IllegalArgumentException(String.format("expecting [%d] values, found only [%d] in entry [%s]", VALUE_COUNT, values.length, Arrays.toString(values)));
        }

        String body = values[0].trim();
        String subject = values[1].trim();
        String instructions = values[2].trim();
        String source = values[3].trim();
        int difficultyLevel = Integer.valueOf(values[4].trim());

        StringBuffer bodyBuf = new StringBuffer();
        StringBuffer answerBuf = new StringBuffer();
        List<String> answerKey = new ArrayList<>();
        int blankIndex = 0;

        Matcher matcher = CONTENT_PATTERN.matcher(body);
        while (matcher.find()) {
            matcher.appendReplacement(bodyBuf, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), Matcher.quoteReplacement(BLANK_TOKEN)));
            String answer = matcher.group(2);
            String[] words = answer.split("/");
            for (String word : words) {
                boolean correct = false;
                word = word.trim();
                if (word.startsWith("[") && word.endsWith("]")) {   // dummy
                    word = word.substring(1, word.length() - 1).trim();
                } else {
                    answerBuf.append(word).append(", ");
                    answerKey.add(word);
                    correct = true;
                }
            }
            blankIndex++;
        }
        matcher.appendTail(bodyBuf);
        answerBuf.delete(answerBuf.length() - 2, answerBuf.length());

        return AddQuestionRequest.builder()
                .answerKey(answerBuf.toString())
                .body(bodyBuf.toString())
                .instructions(instructions)
                .subject(subject)
                .build();
    }
}
