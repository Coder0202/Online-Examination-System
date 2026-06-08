package org.OnlineExamSystem.service;

import java.util.List;

import java.util.Optional;

import org.OnlineExamSystem.model.AdminLoginModel;
import org.OnlineExamSystem.model.AdminModel;
import org.OnlineExamSystem.model.RegisterModel;
import org.OnlineExamSystem.repositiory.AdminRepoitiory;
import org.OnlineExamSystem.repositiory.AdminRepositioryImpl;

public  class AdminServiceImpl implements AdminService {

    AdminRepoitiory repo = new AdminRepositioryImpl();

	@Override
	public boolean isLoginAdmin(AdminLoginModel model) {
		
		return repo.isLoginAdmin(model);
	}

	@Override
	public Optional<List<AdminModel>> getAllExam() {
		
		return repo.getAllExam();
	}

	@Override
	public int isTotalStudent(AdminModel m1) {
		
		return repo.isTotalStudent(m1);
	}

	@Override
	public int isTotalSubject(AdminModel m1) {
		
		return repo.isTotalSubject(m1);
	}

	@Override
	public double getPassPercentage(AdminModel m1) {
		
		return repo.getPassPercentage(m1);
	}

	@Override
	public String getAdminName(AdminModel m1) {
		
		return repo.getAdminName(m1);
	}

	

   

       
    }
