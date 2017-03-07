package dao.impl;

import dao.CourseDao;
import model.Course;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Seven on 2017/2/20.
 */
@Stateless
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void save(Course course) {
        em.persist(course);
    }

    @Override
    public void update(Course course) {
        em.refresh(course);
    }

    @Override
    public Course getCourseById(String course_id) {
        Course course=em.find(Course.class,course_id);
        return course;
    }
}
