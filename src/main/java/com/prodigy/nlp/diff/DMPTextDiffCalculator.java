package com.prodigy.nlp.diff;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DMPTextDiffCalculator implements TextDiffCalculator {

    private final DiffMatchPatch dmp;
    private boolean ignoreCase;

    public DMPTextDiffCalculator() {
        this.dmp = new DiffMatchPatch();
    }

    public DMPTextDiffCalculator(DiffMatchPatch dmp) {
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
        List<DiffMatchPatch.Diff> diffs = doDiff(actualChars.toString(), expectedChars.toString());

        // expand diffs of more than one char to multiple single char diffs
        LinkedList<DiffMatchPatch.Diff> expanded = new LinkedList<>();
        for (DiffMatchPatch.Diff diff : diffs) {
            if (diff.text.length() > 1) {
                char[] chars = diff.text.toCharArray();
                for (char aChar : chars) {
                    expanded.add(new DiffMatchPatch.Diff(diff.operation, String.valueOf(aChar)));
                }
            } else {
                expanded.add(diff);
            }
        }

        // revert diffs from chars to words
        for (DiffMatchPatch.Diff diff : expanded) {
            diff.text = charToWord.get(diff.text);
        }

        return toTextDiffs(expanded);
    }

    @Override
    public double distance(List<TextDiff> diffs) {
        return dmp.diffLevenshtein(toDiffs(diffs));
    }

    private List<TextDiff> toTextDiffs(LinkedList<DiffMatchPatch.Diff> diffs) {
        return diffs
                .stream()
                .map(d -> new TextDiff(d.text, getOperation(d.operation)))
                .collect(Collectors.toList());
    }

    private LinkedList<DiffMatchPatch.Diff> toDiffs(List<TextDiff> diffs) {
        LinkedList<DiffMatchPatch.Diff> linked = new LinkedList<>();
        diffs.forEach(d -> linked.add(new DiffMatchPatch.Diff(getOperation(d.getOperation()), d.getText())));
        return linked;
    }

    private TextDiff.Operation getOperation(DiffMatchPatch.Operation source) {
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

    private DiffMatchPatch.Operation getOperation(TextDiff.Operation source) {
        switch (source) {
            case EQUAL:
                return DiffMatchPatch.Operation.EQUAL;
            case DELETE:
                return DiffMatchPatch.Operation.DELETE;
            case INSERT:
                return DiffMatchPatch.Operation.INSERT;
            default:
                throw new RuntimeException("Invalid operation mapping " + source);
        }
    }

    private LinkedList<DiffMatchPatch.Diff> doDiff(String actual, String expected) {
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(actual, expected);
        dmp.diffCleanupMerge(diffs);
        return diffs;
    }
}
