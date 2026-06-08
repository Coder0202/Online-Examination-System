package org.OnlineExamSystem.repositiory;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.OnlineExamSystem.model.AdminModel;

public class subjectRepositioryimpl extends DBconfig implements subjectRepositiory {

    @Override
    public boolean addSubject(AdminModel m1) {

        try {

            stmt = conn.prepareStatement(
                "insert into subject(subject_name) values(?)"
            );

            stmt.setString(1, m1.getSubject_name());

            int value = stmt.executeUpdate();

            return value > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Optional<List<AdminModel>> viewSubject() {

        List<AdminModel> list = new ArrayList<>();

        try {

            stmt = conn.prepareStatement("select * from subject");

            rs = stmt.executeQuery();

            while (rs.next()) {

                AdminModel model = new AdminModel();

                model.setSubject_id(rs.getInt("subject_id"));
                model.setSubject_name(rs.getString("subject_name"));

                list.add(model);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list.size() > 0 ? Optional.of(list) : Optional.empty();
    }

    @Override
    public boolean deleteSubject(int id) {

        try {

            stmt = conn.prepareStatement(
                "delete from subject where subject_id=?"
            );

            stmt.setInt(1, id);

            int value = stmt.executeUpdate();

            return value > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateSubject(AdminModel model) {

        try {

            stmt = conn.prepareStatement(
                "update subject set subject_name=? where subject_id=?"
            );

            stmt.setString(1, model.getSubject_name());
            stmt.setInt(2, model.getSubject_id());

            int value = stmt.executeUpdate();

            return value > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}