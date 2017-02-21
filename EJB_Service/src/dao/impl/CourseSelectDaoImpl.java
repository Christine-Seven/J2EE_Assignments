package dao.impl;

import dao.CourseSelectDao;
import model.Course;
import model.CourseSelect;
import model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Seven on 2017/2/20.
 */
@Stateless
public class CourseSelectDaoImpl implements CourseSelectDao {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public void save(CourseSelect courseSelect) {
        em.persist(courseSelect);
    }

    @Override
    public void update(CourseSelect courseSelect) {
        em.refresh(courseSelect);
    }


    @Override
    public List<Course> getSelectedCourseById(String student_id) {
        Query query=em.createQuery(" from CourseSelect cs where cs.student_id=student_id");
        List list=query.getResultList();
        em.clear();
        return list;
    }

    @Override
    public List<Student> getStudentById(String course_id) {
        Query query=em.createQuery(" from CourseSelect cs where cs.course_id=course_id");
        List list=query.getResultList();
        em.clear();
        return list;
    }

    @Override
    public int getScore(String student_id, String course_id) {
        Query query=em.createQuery(" from CourseSelect cs where cs.course_id=course_id and cs.student_id=student_id");
        int score=(int)query.getSingleResult();
        em.clear();
        return score;
    }
}
