package org.OnlineExamSystem.repositiory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.AdminModel;
import org.OnlineExamSystem.model.studentModel;

public class studentRepostioryImpl extends DBconfig implements studentRepositiory {

	@Override
	public Optional<List<studentModel>> getAllStudent() {

	    List<studentModel> list = new ArrayList<>();

	    try {

	        stmt = conn.prepareStatement("select * from students");

	        rs = stmt.executeQuery();

	        while (rs.next()) {

	            studentModel model = new studentModel();

	            model.setStudent_id(rs.getInt("student_id"));
	            model.setStudent_name(rs.getString("student_name"));
	            model.setEmail(rs.getString("email"));
	            model.setPassword(rs.getString("password"));
	            model.setCourse(rs.getString("course"));
	            model.setMobile(rs.getString("mobile"));

	            list.add(model);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list.size() > 0 ? Optional.of(list) : Optional.empty();
	}

}
