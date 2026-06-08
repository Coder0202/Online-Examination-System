package org.OnlineExamSystem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamModel {

    // EXAM ID
    private int examId;

    // EXAM NAME
    private String examName;

    // SUBJECT ID
    private int subjectId;

    // TOTAL QUESTIONS
    private int totalQuestions;

    // TOTAL MARKS
    private int totalMarks;

    // EXAM DURATION
    private int examDuration;
}