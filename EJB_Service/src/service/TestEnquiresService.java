package service;

import model.Course;
import model.Test;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Seven on 2017/2/20.
 */
public interface TestEnquiresService {

    public HashMap<Course,List<Test>> getScorePO(String student_id);
}
