package org.OnlineExamSystem.service;

import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.resultModel;

public interface ResultService {

    public Optional<List<resultModel>> getAllResults();

	
}