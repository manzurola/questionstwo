package com.prodigy.domain.questions;

import com.prodigy.domain.diff.Diff;
import com.prodigy.domain.diff.ListDiffCheck;
import com.prodigy.domain.nlp.Sentence;
import com.prodigy.domain.nlp.Word;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DiffBasedSentenceTransformer implements SentenceTransformer {

    private final ListDiffCheck diffCheck;

    public DiffBasedSentenceTransformer(ListDiffCheck diffCheck) {
        this.diffCheck = diffCheck;
    }

    @Override
    public SentenceTransformation transform(Sentence source, Sentence target) {
        // diff source and target
        // create fifo queue from diff
        // while queue is not empty
        //  pop diff
        //      if eq then continue
        //      if insert then create insertTransformation
        //      if delete then create delete transformation

        LinkedList<Diff<Word>> diffQueue = new LinkedList<>(diffCheck.checkDiff(
                source.words(),
                target.words(),
                word -> word.value().hashCode()
        ));

        List<TransformationOperation> operations = new ArrayList<>();

        Sentence after = source;
        int pointer = 0;
        while (!diffQueue.isEmpty()) {
            Diff<Word> diff = diffQueue.pop();
            switch (diff.operation()) {
                case DELETE:
                    TransformationOperation delete = new DeleteWord(after, pointer);
                    after = delete.after();
                    operations.add(delete);
                    break;
                case INSERT:
                    TransformationOperation insert = new InsertWord(after, pointer, diff.item());
                    after = insert.after();
                    operations.add(insert);
                    ++pointer;
                    break;
                case EQUAL:
                    ++pointer;
                    break;
                default:
                    break;
            }
        }

        return new SentenceTransformation(operations);
    }
}
