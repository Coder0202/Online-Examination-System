package org.OnlineExamSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor

@AllArgsConstructor

public class QuestionModel {

    private int question_id;

    private int subject_id;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String correct_answer;
}