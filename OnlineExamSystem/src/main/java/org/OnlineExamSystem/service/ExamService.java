package org.OnlineExamSystem.service;

import java.util.List;

import org.OnlineExamSystem.model.ExamModel;

public interface ExamService {

    // ADD EXAM
    public boolean isAddExam(ExamModel model);

    // GET ALL EXAMS
    public List<ExamModel> getAllExams();

    // DELETE EXAM
    public boolean isDeleteExam(int id);

    // GET EXAM BY ID
    public ExamModel getExamById(int id);

    // UPDATE EXAM
    public boolean isUpdateExam(ExamModel model);
}