package dao;

import model.Course;
import model.CourseSelect;
import model.Student;

import java.util.List;

/**
 * Created by Seven on 2017/2/20.
 */
public interface CourseSelectDao {

    public void save(CourseSelect courseSelect);

    public void update(CourseSelect courseSelect);

    public List<Course> getSelectedCourseById(String student_id);

    public List<Student> getStudentById(String course_id);

    public int getScore(String student_id,String course_id);
}
