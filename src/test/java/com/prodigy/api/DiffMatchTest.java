package com.prodigy.api;

import name.fraser.neil.plaintext.diff_match_patch;
import org.junit.Test;

import java.util.LinkedList;

public class DiffMatchTest {

    @Test
    public void name() {
        diff_match_patch dmp = new diff_match_patch();
        String a = "Dogs are cute.";
        String b = "Dogs is cute.";
        LinkedList<diff_match_patch.Diff> diffs = dmp.diff_main(b, a);
        System.out.println(diffs);
        dmp.diff_cleanupSemantic(diffs);
        System.out.println(diffs);
        dmp.diff_cleanupEfficiency(diffs);
        System.out.println(diffs);
    }
}
