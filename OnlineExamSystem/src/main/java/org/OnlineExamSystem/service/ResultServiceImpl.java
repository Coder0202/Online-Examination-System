package org.OnlineExamSystem.service;

import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.resultModel;
import org.OnlineExamSystem.repositiory.ResultRepositiory;
import org.OnlineExamSystem.repositiory.ResultRepositioryImpl;

public  class ResultServiceImpl implements ResultService {

    ResultRepositiory repo = new ResultRepositioryImpl();

   
    public Optional<List<resultModel>> getAllResults() {

        return repo.getAllResults();
    }
}