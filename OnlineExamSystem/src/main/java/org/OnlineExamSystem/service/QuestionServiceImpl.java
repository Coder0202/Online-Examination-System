package org.OnlineExamSystem.service;

import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.QuestionModel;
import org.OnlineExamSystem.repositiory.QuestionRepository;
import org.OnlineExamSystem.repositiory.QuestionRepositoryImpl;


public class QuestionServiceImpl
        implements QuestionService {

    QuestionRepository repo=new QuestionRepositoryImpl();

    @Override
    public boolean addQuestion(QuestionModel model) {

        return repo.addQuestion(model);
    }

    @Override
    public Optional<List<QuestionModel>> getAllQuestions() {

        return repo.getAllQuestions();
    }

    @Override
    public boolean deleteQuestion(int id) {

        return repo.deleteQuestion(id);
    }

    @Override
    public boolean updateQuestion(QuestionModel model) {

        return repo.updateQuestion(model);
    }
}