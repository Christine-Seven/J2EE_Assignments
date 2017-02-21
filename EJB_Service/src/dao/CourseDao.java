package dao;

import model.Course;

/**
 * Created by Seven on 2017/2/20.
 */
public interface CourseDao {

    public void save(Course course);

    public void update(Course course);

    public Course getCourseById(String course_id);


}
