package com.prodigy.api.exercises.utils;

import com.opencsv.CSVReader;
import com.prodigy.api.exercises.Exercise;
import com.prodigy.api.questions.Question;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by guym on 21/05/2017.
 */
public class CSVExerciseReader implements ExerciseReader {

    private final File exerciseFile;
    private final File questionFile;

    public CSVExerciseReader(File exerciseFile, File questionFile) {
        this.exerciseFile = exerciseFile;
        this.questionFile = questionFile;
    }

    public List<Exercise> readAll() throws IOException {

        List<ExerciseRow> exerciseRows = new ArrayList<>();
        List<QuestionRow> questionRows = new ArrayList<>();

        try (CSVReader exerciseReader = new CSVReader(Files.newBufferedReader(exerciseFile.toPath()));
             CSVReader questionReader = new CSVReader(Files.newBufferedReader(questionFile.toPath()))) {

            // skip head rows
            exerciseReader.readNext();
            questionReader.readNext();

            for (String[] values : exerciseReader) {
                exerciseRows.add(new ExerciseRow(values));
            }
            for (String[] values : questionReader) {
                questionRows.add(new QuestionRow(values));
            }
        }

        Map<String, Exercise.Builder> builders = new HashMap<>();
        for (ExerciseRow row : exerciseRows) {
            Exercise.Builder builder = Exercise.builder().title(row.title);
            builders.put(row.id, builder);
        }
        for (QuestionRow row : questionRows) {
            Question question = Question.builder().body(row.title).answerKey(row.answer).build();
            Exercise.Builder builder = builders.get(row.exerciseId);
            if (builder == null) {
                throw new RuntimeException("Exercise with id " + row.exerciseId + " does not exist");
            }
            builder.addQuestion(question);
        }

        return builders.values()
                .stream()
                .map(builder -> builder.build())
                .collect(Collectors.toList());
    }

    class ExerciseRow {

        private final String id;
        private final String title;
        private final String subject;

        ExerciseRow(String[] values) {
            this.id = values[0];
            this.title = values[1];
            this.subject = values[2];
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getSubject() {
            return subject;
        }
    }

    class QuestionRow {

        private final String title;
        private final String answer;
        private final String exerciseId;

        QuestionRow(String[] values) {
            this.title = values[0];
            this.answer = values[1];
            this.exerciseId = values[2];
        }

        public String getTitle() {
            return title;
        }

        public String getAnswer() {
            return answer;
        }

        public String getExerciseId() {
            return exerciseId;
        }
    }
}