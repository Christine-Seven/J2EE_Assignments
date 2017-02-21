package dao.impl;

import dao.TestDao;
import model.Course;
import model.Test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Seven on 2017/2/20.
 */
@Stateless
public class TestDaoImpl implements TestDao {
    @PersistenceContext
    protected EntityManager em;

    @Override
    public void save(Test test) {
        em.persist(test);
    }

    @Override
    public void update(Test test) {
        em.refresh(test);
    }

    @Override
    public Test getTestByTestId(String test_id) {
        Test test=em.find(Test.class,test_id);
        return test;
    }

    @Override
    public List<Test> getTestByCourseAndStudentId(String course_id,String student_id) {
        Query query=em.createQuery("from Test t where t.course_id=course_id and t.student_id=student_id");
        List list=query.getResultList();
        return list;
    }
}
