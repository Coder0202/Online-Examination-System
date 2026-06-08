package org.OnlineExamSystem.service;

import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.AdminModel;
import org.OnlineExamSystem.model.studentModel;

public interface studentService {
	public Optional<List<studentModel>> getAllStudent();

}
