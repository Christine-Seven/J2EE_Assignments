package service.impl;

import dao.StudentDao;
import factory.EJBFactory;
import model.Student;

import javax.ejb.Stateless;


/**
 * Created by Seven on 2017/2/20.
 */
@Stateless
public class UserDataService implements service.UserDataService {
    StudentDao studentDao=(StudentDao) EJBFactory.getEJB("ejb:/EJB_Service/StudentDaoImpl!dao.StudentDao");
    @Override
    public Student getStudentById(String id, String password) {
        Student student=studentDao.getStudentById(id,password);
        return student;
    }
}
