package com.prodigy.domain.questions;

import com.prodigy.domain.diff.Diff;
import com.prodigy.domain.diff.ListDiffCheck;
import com.prodigy.domain.nlp.Sentence;
import com.prodigy.domain.nlp.Word;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DiffBasedSentenceTransformation implements SentenceTransformation {

    private final ListDiffCheck diffCheck;

    public DiffBasedSentenceTransformation(ListDiffCheck diffCheck) {
        this.diffCheck = diffCheck;
    }

    @Override
    public SentenceTransform transform(Sentence source, Sentence target) {
        List<TransformationOperation> operations = new ArrayList<>();
        LinkedList<Diff<Word>> wordDiff = diffWords(source, target);
        Sentence before = source;
        int sourceIndex = 0;

        while (!wordDiff.isEmpty()) {
            Diff<Word> diff = wordDiff.pop();
            switch (diff.operation()) {
                case DELETE:
                    TransformationOperation delete = new DeleteWord(before, sourceIndex);
                    operations.add(delete);
                    before = delete.after();
                    break;
                case INSERT:
                    TransformationOperation insert = new InsertWord(before, sourceIndex, diff.item());
                    operations.add(insert);
                    before = insert.after();
                    ++sourceIndex;
                    break;
                case EQUAL:
                    ++sourceIndex;
                    break;
                default:
                    break;
            }
        }

        return new SentenceTransform(operations);
    }

    private LinkedList<Diff<Word>> diffWords(Sentence source, Sentence target) {
        return new LinkedList<>(diffCheck.checkDiff(
                source.words(),
                target.words(),
                word -> word.value().hashCode()
        ));
    }
}
