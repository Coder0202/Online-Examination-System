package org.OnlineExamSystem.repositiory;

import java.util.List;
import java.util.Optional;

import org.OnlineExamSystem.model.resultModel;

public interface ResultRepositiory {

    public Optional<List<resultModel>> getAllResults();
}