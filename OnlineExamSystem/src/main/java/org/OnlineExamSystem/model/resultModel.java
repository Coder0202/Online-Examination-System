package org.OnlineExamSystem.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class resultModel extends studentModel {

    private int result_id;

    private String exam_name;

    private int total_marks;

    private int obtained_marks;

    private double percentage;

    private String status;
    
}