package org.OnlineExamSystem.service;
import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.*;

public interface AdminService {
	
	public boolean isLoginAdmin(AdminLoginModel model);
	public Optional<List<AdminModel>> getAllExam();
	public int isTotalStudent(AdminModel m1);
	public int isTotalSubject(AdminModel m1);
	public double getPassPercentage(AdminModel m1);
	public String getAdminName(AdminModel m1);
}
