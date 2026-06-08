package org.OnlineExamSystem.repositiory;

import java.util.List;
import java.util.Optional;


import org.OnlineExamSystem.model.AdminLoginModel;
import org.OnlineExamSystem.model.AdminModel;
import org.OnlineExamSystem.model.RegisterModel;

public interface AdminRepoitiory {
	public boolean isLoginAdmin(AdminLoginModel model);
	public Optional<List<AdminModel>> getAllExam();
	public int isTotalStudent(AdminModel m1);
	public int isTotalSubject(AdminModel m1);
	public double getPassPercentage(AdminModel m1);
	public String getAdminName(AdminModel m1);

}
