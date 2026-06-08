package org.OnlineExamSystem.service;

import java.util.List;

import java.util.Optional;
import org.OnlineExamSystem.model.studentModel;
import org.OnlineExamSystem.repositiory.studentRepositiory;
import org.OnlineExamSystem.repositiory.studentRepostioryImpl;

public class studentServiceImpl implements studentService {

	studentRepositiory repo=new studentRepostioryImpl();
	@Override
	public Optional<List<studentModel>> getAllStudent() {
		
		return repo.getAllStudent();
	}
	
	

}
