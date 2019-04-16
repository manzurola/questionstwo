package com.prodigy.nlp.diff;

import name.fraser.neil.plaintext.diff_match_patch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DMPTextDiffCalculator implements TextDiffCalculator {

    private final diff_match_patch dmp;

    public DMPTextDiffCalculator() {
        this.dmp = new diff_match_patch();
    }

    public DMPTextDiffCalculator(diff_match_patch dmp) {
        this.dmp = dmp;
    }

    @Override
    public List<TextDiff> diff(String origin, String target) {
        return toTextDiffs(doDiff(origin, target));
    }

    @Override
    public List<TextDiff> diff(List<String> origin, List<String> target) {
        Map<String, String> wordToChar = new HashMap<>();
        Map<String, String> charToWord = new HashMap<>();

        StringBuilder expectedChars = new StringBuilder();
        StringBuilder actualChars = new StringBuilder();

        // convert words to chars for word diff

        char c = 'a';
        for (String word : target) {
            if (!wordToChar.containsKey(word)) {
                wordToChar.put(word, String.valueOf(c));
                charToWord.put(String.valueOf(c), word);
                c++;
            }
            String key = wordToChar.get(word);
            expectedChars.append(key);
        }

        for (String word : origin) {
            if (!wordToChar.containsKey(word)) {
                wordToChar.put(word, String.valueOf(c));
                charToWord.put(String.valueOf(c), word);
                c++;
            }
            String key = wordToChar.get(word);
            actualChars.append(key);
        }

        // do diff
        List<diff_match_patch.Diff> diffs = doDiff(actualChars.toString(), expectedChars.toString());

        // expand diffs of more than one char to multiple single char diffs
        LinkedList<diff_match_patch.Diff> expanded = new LinkedList<>();
        for (diff_match_patch.Diff diff : diffs) {
            if (diff.text.length() > 1) {
                char[] chars = diff.text.toCharArray();
                for (char aChar : chars) {
                    expanded.add(new diff_match_patch.Diff(diff.operation, String.valueOf(aChar)));
                }
            } else {
                expanded.add(diff);
            }
        }

        // revert diffs from chars to words
        for (diff_match_patch.Diff diff : expanded) {
            diff.text = charToWord.get(diff.text);
        }

        return toTextDiffs(expanded);
    }

    private List<TextDiff> toTextDiffs(List<diff_match_patch.Diff> diffs) {
        return diffs
                .stream()
                .map(d -> new TextDiff(d.text, getOperation(d.operation)))
                .collect(Collectors.toList());
    }

    private TextDiff.Operation getOperation(diff_match_patch.Operation source) {
        switch (source) {
            case EQUAL:
                return TextDiff.Operation.EQUAL;
            case DELETE:
                return TextDiff.Operation.DELETE;
            case INSERT:
                return TextDiff.Operation.INSERT;
            default:
                throw new RuntimeException("Invalid operation mapping " + source);
        }
    }

    private List<diff_match_patch.Diff> doDiff(String actual, String expected) {
        LinkedList<diff_match_patch.Diff> diffs = dmp.diff_main(actual, expected);
        dmp.diff_cleanupMerge(diffs);
        return diffs;
    }
}
