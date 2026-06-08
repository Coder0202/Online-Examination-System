package org.OnlineExamSystem.repositiory;

import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.AdminModel;
import org.OnlineExamSystem.model.studentModel;

public interface studentRepositiory {
	

	public Optional<List<studentModel>> getAllStudent();

	

}
