package org.OnlineExamSystem.service;

import java.util.List;

import org.OnlineExamSystem.model.ExamModel;
import org.OnlineExamSystem.repositiory.ExamRepositioryImpl;
import org.OnlineExamSystem.repositiory.ExamRepository;

public class ExamServiceImpl implements ExamService {

    ExamRepository repo =
            new ExamRepositioryImpl();

    // ==========================
    // ADD EXAM
    // ==========================

    @Override
    public boolean isAddExam(ExamModel model) {

        return repo.isAddExam(model);
    }

    // ==========================
    // GET ALL EXAMS
    // ==========================

    @Override
    public List<ExamModel> getAllExams() {

        return repo.getAllExams();
    }

    // ==========================
    // DELETE EXAM
    // ==========================

    @Override
    public boolean isDeleteExam(int id) {

        return repo.isDeleteExam(id);
    }

    // ==========================
    // GET EXAM BY ID
    // ==========================

    @Override
    public ExamModel getExamById(int id) {

        return repo.getExamById(id);
    }

    // ==========================
    // UPDATE EXAM
    // ==========================

    @Override
    public boolean isUpdateExam(ExamModel model) {

        return repo.isUpdateExam(model);
    }
}