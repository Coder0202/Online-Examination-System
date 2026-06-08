package org.OnlineExamSystem.repositiory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.AdminLoginModel;
import org.OnlineExamSystem.model.AdminModel;

public class AdminRepositioryImpl extends DBconfig implements AdminRepoitiory {

   

    @Override
    public boolean isLoginAdmin(AdminLoginModel model) {

        try {

            stmt = conn.prepareStatement("select * from admin where email=? and password=?");

            stmt.setString(1, model.getEmail());
            stmt.setString(2, model.getPassword());

            rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Optional<List<AdminModel>> getAllExam() {

        List<AdminModel> list = new ArrayList<>();

        try {

            

            stmt = conn.prepareStatement(
                "SELECT e.exam_id, e.exam_name," +
                "e.total_questions, e.total_marks, e.exam_duration " +
                "FROM exam e " +
                "INNER JOIN subject s ON e.subject_id = s.subject_id"
            );

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                AdminModel model = new AdminModel();

                model.setExam_id(rs.getInt("exam_id"));
                model.setExam_name(rs.getString("exam_name"));
                
                model.setTotal_questions(rs.getInt("total_questions"));
                model.setTotal_marks(rs.getInt("total_marks"));
                model.setExam_duration(rs.getInt("exam_duration"));

                list.add(model);
            }

            return Optional.of(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public int isTotalStudent(AdminModel m1) {

        int count = 0;

        try {
           

             stmt =
                    conn.prepareStatement("SELECT COUNT(*) FROM students");

             rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int isTotalSubject(AdminModel m1) {

        int count = 0;

        try {
          

           stmt =
                    conn.prepareStatement("SELECT COUNT(*) FROM subject");

             rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public double getPassPercentage(AdminModel m1) {

        double percentage = 0.0;

        try {
            

            stmt = conn.prepareStatement(
                "SELECT IFNULL((SUM(CASE WHEN status='Pass' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)),0) AS per FROM result"
            );

             rs = stmt.executeQuery();

            if (rs.next()) {
                percentage = rs.getDouble("per");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return percentage;
    }

    @Override
    public String getAdminName(AdminModel m1) {

        String adminName = null;

        try {

            stmt = conn.prepareStatement("SELECT admin_name FROM admin");

            rs = stmt.executeQuery();

            if (rs.next()) {
                adminName = rs.getString("admin_name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminName;
    }	
}