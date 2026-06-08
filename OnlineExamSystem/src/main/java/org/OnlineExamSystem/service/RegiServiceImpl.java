package org.OnlineExamSystem.service;

import org.OnlineExamSystem.model.RegisterModel;
import org.OnlineExamSystem.repositiory.DBconfig;
import org.OnlineExamSystem.repositiory.RegiRepositiory;
import org.OnlineExamSystem.repositiory.RegiRepositioryImpl;

public class RegiServiceImpl extends DBconfig implements RegiService {
	
	RegiRepositiory repo=new RegiRepositioryImpl();

	@Override
	public boolean isRegisterUser(RegisterModel model) {
		
		return repo.isRegisterUser(model);
	}

}
