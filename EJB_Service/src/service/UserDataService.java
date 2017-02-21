package service;

import model.Student;

/**
 * Created by Seven on 2017/2/20.
 */
public interface UserDataService {
    public Student getStudentById(String id,String password);
}
