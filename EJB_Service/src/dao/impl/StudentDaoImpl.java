package dao.impl;

import dao.StudentDao;
import model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Seven on 2017/2/20.
 */
@Stateless
public class StudentDaoImpl implements StudentDao {
    @PersistenceContext
    protected EntityManager em;
    @Override
    public void save(Student student) {
        em.persist(student);
    }

    @Override
    public void update(Student student) {
        em.refresh(student);
    }

    @Override
    public Student getStudentById(String student_id,String password) {
        Query query=em.createQuery("from Student s where s.student_id=student_id and s.password=password");
        Student student=(Student) query.getSingleResult();
        return student;
    }
}
