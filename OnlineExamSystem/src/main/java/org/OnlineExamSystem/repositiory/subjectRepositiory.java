package org.OnlineExamSystem.repositiory;

import java.util.List;
import java.util.Optional;


import org.OnlineExamSystem.model.AdminModel;

public interface subjectRepositiory {

    public boolean addSubject(AdminModel m1);
    public Optional<List<AdminModel>> viewSubject();
    public boolean deleteSubject(int id);
    public boolean updateSubject(AdminModel model);

}