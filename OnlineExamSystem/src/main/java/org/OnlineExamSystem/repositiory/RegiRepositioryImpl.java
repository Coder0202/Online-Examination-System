package org.OnlineExamSystem.repositiory;

import org.OnlineExamSystem.model.RegisterModel;

public class RegiRepositioryImpl extends DBconfig implements RegiRepositiory {

    @Override
    public boolean isRegisterUser(RegisterModel model) {

        try {

            stmt = conn.prepareStatement(
                "insert into students(student_name,email,password,course,mobile) values(?,?,?,?,?)"
            );

            stmt.setString(1, model.getStudent_name());
            stmt.setString(2, model.getEmail());
            stmt.setString(3, model.getPassword());
            stmt.setString(4, model.getCourse());
            stmt.setString(5, model.getMobile());

            int value = stmt.executeUpdate();

            return value > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}