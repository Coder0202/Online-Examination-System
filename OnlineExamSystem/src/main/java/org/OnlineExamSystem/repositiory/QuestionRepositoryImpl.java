package org.OnlineExamSystem.repositiory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.QuestionModel;

public class QuestionRepositoryImpl
        extends DBconfig
        implements QuestionRepository {

    @Override
    public boolean addQuestion(
            QuestionModel model) {

        try {

            stmt = conn.prepareStatement(

            "insert into questions(subject_id,question,option1,option2,option3,option4,correct_answer) values(?,?,?,?,?,?,?)"

            );

            stmt.setInt(
                    1,
                    model.getSubject_id()
            );

            stmt.setString(
                    2,
                    model.getQuestion()
            );

            stmt.setString(
                    3,
                    model.getOption1()
            );

            stmt.setString(
                    4,
                    model.getOption2()
            );

            stmt.setString(
                    5,
                    model.getOption3()
            );

            stmt.setString(
                    6,
                    model.getOption4()
            );

            stmt.setString(
                    7,
                    model.getCorrect_answer()
            );

            int value =
                    stmt.executeUpdate();

            return value > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Optional<List<QuestionModel>>
    getAllQuestions() {

        List<QuestionModel> list =
                new ArrayList<>();

        try {

            stmt = conn.prepareStatement(
                    "select * from questions"
            );

            rs = stmt.executeQuery();

            while(rs.next()) {

                QuestionModel model =
                        new QuestionModel();

                model.setQuestion_id(
                        rs.getInt("question_id")
                );

                model.setSubject_id(
                        rs.getInt("subject_id")
                );

                model.setQuestion(
                        rs.getString("question")
                );

                model.setOption1(
                        rs.getString("option1")
                );

                model.setOption2(
                        rs.getString("option2")
                );

                model.setOption3(
                        rs.getString("option3")
                );

                model.setOption4(
                        rs.getString("option4")
                );

                model.setCorrect_answer(
                        rs.getString("correct_answer")
                );

                list.add(model);
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return list.isEmpty()
                ? Optional.empty()
                : Optional.of(list);
    }

    @Override
    public boolean deleteQuestion(
            int id) {

        try {

            stmt = conn.prepareStatement(
                    "delete from questions where question_id=?"
            );

            stmt.setInt(1, id);

            int value =
                    stmt.executeUpdate();

            return value > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateQuestion(
            QuestionModel model) {

        try {

            stmt = conn.prepareStatement(

            "update questions set subject_id=?,question=?,option1=?,option2=?,option3=?,option4=?,correct_answer=? where question_id=?"

            );

            stmt.setInt(
                    1,
                    model.getSubject_id()
            );

            stmt.setString(
                    2,
                    model.getQuestion()
            );

            stmt.setString(
                    3,
                    model.getOption1()
            );

            stmt.setString(
                    4,
                    model.getOption2()
            );

            stmt.setString(
                    5,
                    model.getOption3()
            );

            stmt.setString(
                    6,
                    model.getOption4()
            );

            stmt.setString(
                    7,
                    model.getCorrect_answer()
            );

            stmt.setInt(
                    8,
                    model.getQuestion_id()
            );

            int value =
                    stmt.executeUpdate();

            return value > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}