package org.OnlineExamSystem.service;

import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.AdminModel;

public interface subjectService {
	public boolean addSubject(AdminModel m1);
	 public Optional<List<AdminModel>> viewSubject();
	 public boolean deleteSubject(int id);
	 public boolean updateSubject(AdminModel model);
	 }
