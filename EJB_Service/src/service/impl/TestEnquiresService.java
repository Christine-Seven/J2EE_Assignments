package service.impl;

import dao.CourseSelectDao;
import dao.TestDao;
import factory.EJBFactory;
import model.Course;
import model.Test;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Seven on 2017/2/20.
 */
@Stateless
public class TestEnquiresService implements service.TestEnquiresService {

    CourseSelectDao courseSelectDao=(CourseSelectDao) EJBFactory.getEJB("ejb:/EJB_Service/CourseSelectDaoImpl!dao.CourseSelectDao");
    TestDao testDao=(TestDao)EJBFactory.getEJB("ejb:/EJB_Service/TestDaoImpl!dao.TestDao");

    @Override
    public HashMap<Course, List<Test>> getScorePO(String student_id) {
        List<Course> courses=courseSelectDao.getSelectedCourseById(student_id);
        HashMap<Course,List<Test>> scores=new HashMap<>();
        for(Course course:courses){
            String course_id=course.getCourse_id();
            List<Test> tests=testDao.getTestByCourseAndStudentId(student_id,course_id);
            scores.put(course,tests);
        }
        return scores;
    }
}
