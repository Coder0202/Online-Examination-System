package org.OnlineExamSystem.repositiory;

import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.QuestionModel;

public interface QuestionRepository {

	public boolean addQuestion(QuestionModel model);

    public Optional<List<QuestionModel>> getAllQuestions();

    public boolean deleteQuestion(int id);

    public boolean updateQuestion(QuestionModel model);
}