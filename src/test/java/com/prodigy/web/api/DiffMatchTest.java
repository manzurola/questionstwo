package com.prodigy.web.api;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.junit.Test;

import java.util.LinkedList;

public class DiffMatchTest {

    @Test
    public void name() {
        DiffMatchPatch dmp = new DiffMatchPatch();
        String a = "Dogs are cute.";
        String b = "Dogs is cute.";
        LinkedList<DiffMatchPatch.Diff> diffs = dmp.diffMain(b, a);
        System.out.println(diffs);
        dmp.diffCleanupMerge(diffs);
        System.out.println(diffs);
        dmp.diffCleanupEfficiency(diffs);
        System.out.println(diffs);
    }
}
