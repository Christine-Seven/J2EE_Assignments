package dao;

import model.Student;

/**
 * Created by Seven on 2017/2/20.
 */
public interface StudentDao {

    public void save(Student student);

    public void update(Student student);

    public Student getStudentById(String student_id,String password);
}
