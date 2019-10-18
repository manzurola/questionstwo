package com.prodigy.diff;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HashingListDiffTest {

    @Test
    public void sourceIsMissingItem() {
        List<String> source = Arrays.asList("Hello", "World");
        List<String> target = Arrays.asList("Hello", "Dear", "World");
        List<Diff<String>> actual = newStringDiffChecker().diffSourceAndTarget(source, target);
        List<Diff<String>> expected = Arrays.asList(
                new Diff<>(Operation.EQUAL, "Hello"),
                new Diff<>(Operation.INSERT, "Dear"),
                new Diff<>(Operation.EQUAL, "World")
        );
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sourceHasRedundantItem() {
        List<String> source = Arrays.asList("Hello", "Dear", "World");
        List<String> target = Arrays.asList("Hello", "World");
        List<Diff<String>> actual = newStringDiffChecker().diffSourceAndTarget(source, target);
        List<Diff<String>> expected = Arrays.asList(
                new Diff<>(Operation.EQUAL, "Hello"),
                new Diff<>(Operation.DELETE, "Dear"),
                new Diff<>(Operation.EQUAL, "World")
        );
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void complexDiff() {
        List<String> source = Arrays.asList("Dear", "World", ".");
        List<String> target = Arrays.asList("Hello", "World", "This", "Is", "Different");
        List<Diff<String>> actual = newStringDiffChecker().diffSourceAndTarget(source, target);
        List<Diff<String>> expected = Arrays.asList(
                new Diff<>(Operation.DELETE, "Dear"),
                new Diff<>(Operation.INSERT, "Hello"),
                new Diff<>(Operation.EQUAL, "World"),
                new Diff<>(Operation.DELETE, "."),
                new Diff<>(Operation.INSERT, "This"),
                new Diff<>(Operation.INSERT, "Is"),
                new Diff<>(Operation.INSERT, "Different")
        );
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void emptySource() {
        List<String> source = Collections.emptyList();
        List<String> target = Arrays.asList("Hello", "World");
        List<Diff<String>> actual = newStringDiffChecker().diffSourceAndTarget(source, target);
        List<Diff<String>> expected = Arrays.asList(
                new Diff<>(Operation.INSERT, "Hello"),
                new Diff<>(Operation.INSERT, "World")
        );
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void emptyTarget() {
        List<String> source = Arrays.asList("Hello", "World");
        List<String> target = Collections.emptyList();
        List<Diff<String>> actual = newStringDiffChecker().diffSourceAndTarget(source, target);
        List<Diff<String>> expected = Arrays.asList(
                new Diff<>(Operation.DELETE, "Hello"),
                new Diff<>(Operation.DELETE, "World")
        );
        Assert.assertEquals(expected, actual);
    }

    private ListDiffCheck<String> newStringDiffChecker() {
        return new ListDiffCheck<>();
    }
}
