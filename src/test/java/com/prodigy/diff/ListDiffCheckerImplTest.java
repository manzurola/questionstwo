package com.prodigy.diff;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListDiffCheckerImplTest {

    @Test
    public void sourceIsMissingItem() {
        List<String> source = Arrays.asList("Hello", "World");
        List<String> target = Arrays.asList("Hello", "Dear", "World");
        ListDiffChecker diffCalculator = new ListDiffCheckerImpl();
        List<Diff<String>> actual = diffCalculator.checkDiff(source, target);
        List<Diff<String>> expected = Arrays.asList(
                new Diff<>(Diff.Operation.EQUAL, "Hello"),
                new Diff<>(Diff.Operation.INSERT, "Dear"),
                new Diff<>(Diff.Operation.EQUAL, "World")
        );
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sourceHasRedundantItem() {
        List<String> source = Arrays.asList("Hello", "Dear", "World");
        List<String> target = Arrays.asList("Hello", "World");
        ListDiffChecker diffCalculator = new ListDiffCheckerImpl();
        List<Diff<String>> actual = diffCalculator.checkDiff(source, target);
        List<Diff<String>> expected = Arrays.asList(
                new Diff<>(Diff.Operation.EQUAL, "Hello"),
                new Diff<>(Diff.Operation.DELETE, "Dear"),
                new Diff<>(Diff.Operation.EQUAL, "World")
        );
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void complexDiff() {
        List<String> source = Arrays.asList("Dear", "World", ".");
        List<String> target = Arrays.asList("Hello", "World", "This", "Is", "Different");
        ListDiffChecker diffCalculator = new ListDiffCheckerImpl();
        List<Diff<String>> actual = diffCalculator.checkDiff(source, target);
        List<Diff<String>> expected = Arrays.asList(
                new Diff<>(Diff.Operation.DELETE, "Dear"),
                new Diff<>(Diff.Operation.INSERT, "Hello"),
                new Diff<>(Diff.Operation.EQUAL, "World"),
                new Diff<>(Diff.Operation.DELETE, "."),
                new Diff<>(Diff.Operation.INSERT, "This"),
                new Diff<>(Diff.Operation.INSERT, "Is"),
                new Diff<>(Diff.Operation.INSERT, "Different")
        );
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void emptySource() {
        List<String> source = Collections.emptyList();
        List<String> target = Arrays.asList("Hello", "World");
        ListDiffChecker diffCalculator = new ListDiffCheckerImpl();
        List<Diff<String>> actual = diffCalculator.checkDiff(source, target);
        List<Diff<String>> expected = Arrays.asList(
                new Diff<>(Diff.Operation.INSERT, "Hello"),
                new Diff<>(Diff.Operation.INSERT, "World")
        );
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void emptyTarget() {
        List<String> source = Arrays.asList("Hello", "World");
        List<String> target = Collections.emptyList();
        ListDiffChecker diffCalculator = new ListDiffCheckerImpl();
        List<Diff<String>> actual = diffCalculator.checkDiff(source, target);
        List<Diff<String>> expected = Arrays.asList(
                new Diff<>(Diff.Operation.DELETE, "Hello"),
                new Diff<>(Diff.Operation.DELETE, "World")
        );
        Assert.assertEquals(expected, actual);
    }
}
