package org.OnlineExamSystem.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminLoginModel {
	
	       private int admin_id; 
	       private String admin_name;
	       private String email ;
	       private String password;
}
