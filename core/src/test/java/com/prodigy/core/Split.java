package com.prodigy.core;

import com.prodigy.core.diff.DMPDiffCalculator;
import com.prodigy.core.diff.Diff;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Split {

    @Test
    public void testDiff() {


        List<Elem> target = Arrays.asList(new Elem("hello"), new Elem("my"), new Elem("name"), new Elem("is"), new Elem("guy"));
        List<Elem> source = Arrays.asList(new Elem("hello"), new Elem("name"), new Elem("is"), new Elem("guy"));

        List<Diff<Elem>> expected = Arrays.asList(
                new Diff<>(Diff.Operation.EQUAL, new Elem("hello")),
                new Diff<>(Diff.Operation.INSERT, new Elem("my")),
                new Diff<>(Diff.Operation.EQUAL, new Elem("name")),
                new Diff<>(Diff.Operation.EQUAL, new Elem("is")),
                new Diff<>(Diff.Operation.EQUAL, new Elem("guy"))
        );

        DMPDiffCalculator diffCalculator = new DMPDiffCalculator();
        List<Diff<Elem>> actual = diffCalculator.getDiff(source, target);

        Assert.assertEquals(expected, actual);
    }

    public static class Elem {
        private final String text;

        public Elem(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Elem)) return false;
            Elem elem = (Elem) o;
            return Objects.equals(text, elem.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text);
        }

        @Override
        public String toString() {
            return "Elem{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }
}

