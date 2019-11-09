package com.prodigy.domain.diff.impl;

import com.prodigy.domain.diff.Diff;
import com.prodigy.domain.diff.Operation;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

final class DMPWrapper {

    final List<Diff<String>> checkDiff(String source, String target) {
        DiffMatchPatch dmp = new DiffMatchPatch();
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(source, target, false);
        dmp.diffCleanupMerge(diffs);
        return transformFromDMP(diffs);
    }

    private List<Diff<String>> transformFromDMP(List<DiffMatchPatch.Diff> dmpDiffs) {
        List<Diff<String>> translated = new ArrayList<>();
        for (DiffMatchPatch.Diff dmpDiff : dmpDiffs) {
            Diff<String> diff = new Diff<>(Operation.valueOf(dmpDiff.operation.name()), dmpDiff.text);
            translated.add(diff);
        }
        return translated;
    }
}
