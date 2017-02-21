package dao;

import model.Test;

import java.util.List;

/**
 * Created by Seven on 2017/2/20.
 */
public interface TestDao {
    public void save(Test test);

    public void update(Test test);

    public Test getTestByTestId(String test_id);

    public  List<Test> getTestByCourseAndStudentId(String course_id,String student_id);

}
