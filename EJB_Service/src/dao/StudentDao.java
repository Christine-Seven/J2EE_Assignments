package dao;

import model.Student;

import javax.ejb.Remote;

/**
 * Created by Seven on 2017/2/20.
 */
@Remote
public interface StudentDao {

    public void save(Student student);

    public void update(Student student);

    public Student getStudentById(String student_id,String password);
}
