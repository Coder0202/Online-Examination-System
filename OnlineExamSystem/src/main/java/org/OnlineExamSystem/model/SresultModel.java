package org.OnlineExamSystem.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SresultModel {
	private int resultId;
	private int studentId;
	private int examId;
	private int totalMarks;
	private int obtainedMarks;
	private double percentage;
	private String status;

}
