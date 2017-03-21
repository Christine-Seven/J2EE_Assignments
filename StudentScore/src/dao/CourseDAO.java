package dao;

import model.Course;

/**
 * Created by Seven on 19/03/2017.
 */

public interface CourseDao {


    public void save(Course course);

    public Course find(String column, String value);

    public void updateByCourseId(Course course);
}
