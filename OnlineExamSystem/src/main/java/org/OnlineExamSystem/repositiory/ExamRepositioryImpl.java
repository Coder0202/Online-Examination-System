package org.OnlineExamSystem.repositiory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.OnlineExamSystem.model.ExamModel;

public class ExamRepositioryImpl extends DBconfig
        implements ExamRepository {

    // ADD EXAM

    @Override
    public boolean isAddExam(ExamModel model) {

        try {

            stmt = conn.prepareStatement(
                    "insert into exam(exam_name,subject_id,total_questions,total_marks,exam_duration) values(?,?,?,?,?)");

            stmt.setString(1, model.getExamName());
            stmt.setInt(2, model.getSubjectId());
            stmt.setInt(3, model.getTotalQuestions());
            stmt.setInt(4, model.getTotalMarks());
            stmt.setInt(5, model.getExamDuration());

            int value = stmt.executeUpdate();

            return value > 0;

        } catch (Exception e) {

            System.out.println(e);

            return false;
        }
    }

    // GET ALL EXAMS

    @Override
    public List<ExamModel> getAllExams() {

        List<ExamModel> list =
                new ArrayList<>();

        try {

            stmt = conn.prepareStatement(
                    "select * from exam");

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                ExamModel model =
                        new ExamModel();

                model.setExamId(
                        rs.getInt("exam_id"));

                model.setExamName(
                        rs.getString("exam_name"));

                model.setSubjectId(
                        rs.getInt("subject_id"));

                model.setTotalQuestions(
                        rs.getInt("total_questions"));

                model.setTotalMarks(
                        rs.getInt("total_marks"));

                model.setExamDuration(
                        rs.getInt("exam_duration"));

                list.add(model);
            }

        } catch (Exception e) {

            System.out.println(e);
        }

        return list;
    }

    // DELETE EXAM

    @Override
    public boolean isDeleteExam(int id) {

        try {

            stmt = conn.prepareStatement(
                    "delete from exam where exam_id=?");

            stmt.setInt(1, id);

            int value =
                    stmt.executeUpdate();

            return value > 0;

        } catch (Exception e) {

            System.out.println(e);

            return false;
        }
    }

    // UPDATE EXAM

    @Override
    public boolean isUpdateExam(ExamModel model) {

        try {

            stmt = conn.prepareStatement(
                    "update exam set exam_name=?,subject_id=?,total_questions=?,total_marks=?,exam_duration=? where exam_id=?");

            stmt.setString(1,
                    model.getExamName());

            stmt.setInt(2,
                    model.getSubjectId());

            stmt.setInt(3,
                    model.getTotalQuestions());

            stmt.setInt(4,
                    model.getTotalMarks());

            stmt.setInt(5,
                    model.getExamDuration());

            stmt.setInt(6,
                    model.getExamId());

            int value =
                    stmt.executeUpdate();

            return value > 0;

        } catch (Exception e) {

            System.out.println(e);

            return false;
        }
    }

    // GET EXAM BY ID

    @Override
    public ExamModel getExamById(int id) {

        ExamModel model =
                new ExamModel();

        try {

            stmt = conn.prepareStatement(
                    "select * from exam where exam_id=?");

            stmt.setInt(1, id);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                model.setExamId(
                        rs.getInt("exam_id"));

                model.setExamName(
                        rs.getString("exam_name"));

                model.setSubjectId(
                        rs.getInt("subject_id"));

                model.setTotalQuestions(
                        rs.getInt("total_questions"));

                model.setTotalMarks(
                        rs.getInt("total_marks"));

                model.setExamDuration(
                        rs.getInt("exam_duration"));
            }

        } catch (Exception e) {

            System.out.println(e);
        }

        return model;
    }
}