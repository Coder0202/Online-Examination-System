package org.OnlineExamSystem.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminModel {
	 private int exam_id;         
	 private String  exam_name;  
	 private int  total_questions;
	 private int  total_marks   ;
	 private int  exam_duration;
	 private int subject_id;
	 private int student_id;
	 private double passPercentage;
	 private String subject_name;
	 private String admin_name;

}
