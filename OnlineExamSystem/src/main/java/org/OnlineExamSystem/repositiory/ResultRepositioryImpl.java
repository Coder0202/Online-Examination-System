package org.OnlineExamSystem.repositiory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.resultModel;

public class ResultRepositioryImpl extends DBconfig implements ResultRepositiory {

   
    @Override
    public Optional<List<resultModel>> getAllResults() {

        List<resultModel> list = new ArrayList<>();

        try {

            String query =
                    "SELECT " +
                    "r.result_id, " +
                    "s.student_name, " +
                    "e.exam_name, " +
                    "r.total_marks, " +
                    "r.obtained_marks, " +
                    "r.percentage, " +
                    "r.status " +
                    "FROM result r " +
                    "INNER JOIN students s " +
                    "ON r.student_id = s.student_id " +
                    "INNER JOIN exam e " +
                    "ON r.exam_id = e.exam_id";

            stmt = conn.prepareStatement(query);

            rs = stmt.executeQuery();

            while (rs.next()) {

                resultModel model = new resultModel();

                model.setResult_id(
                        rs.getInt("result_id"));

                model.setStudent_name(
                        rs.getString("student_name"));

                model.setExam_name(
                        rs.getString("exam_name"));

                model.setTotal_marks(
                        rs.getInt("total_marks"));

                model.setObtained_marks(
                        rs.getInt("obtained_marks"));

                model.setPercentage(
                        rs.getDouble("percentage"));

                model.setStatus(
                        rs.getString("status"));

                list.add(model);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list.isEmpty()
                ? Optional.empty()
                : Optional.of(list);
    }
}