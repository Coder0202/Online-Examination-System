package org.OnlineExamSystem.service;

import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.AdminModel;
import org.OnlineExamSystem.repositiory.subjectRepositiory;
import org.OnlineExamSystem.repositiory.subjectRepositioryimpl;

public class subjectServiceimpl implements subjectService {
	subjectRepositiory repo=new subjectRepositioryimpl();

	@Override
	public boolean addSubject(AdminModel m1) {
		
		return repo.addSubject(m1);
	}

	@Override
	public Optional<List<AdminModel>> viewSubject() {
		
		return repo.viewSubject();
	}
	@Override
	public boolean deleteSubject(int id) {

	    return repo.deleteSubject(id);
	}

	@Override
	public boolean updateSubject(AdminModel model) {

	    return repo.updateSubject(model);
	}

}
