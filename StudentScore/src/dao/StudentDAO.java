package dao;

import model.Student;

/**
 * Created by Seven on 19/03/2017.
 */
public interface StudentDao {

    public void save(Student student);

    public void update(Student student);

    public Student find(String column, String value);
}
