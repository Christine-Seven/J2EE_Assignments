package serviceImpl;

import dao.CourseDao;
import dao.StudentDao;
import model.Course;
import model.Score;
import model.Student;
import model.Test;
import utility.Constant;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Seven on 2016/12/18.
 */
@Service
public class TestEnquiresServiceImpl {
    @Autowired
    CourseDao courseDao;
    @Autowired
    StudentDao studentDao;

    public Course getScorePO(String student_id) throws SQLException {
        Connection connection = Constant.connection;
        HashMap<Course, List<Test>> courseScore = new HashMap<>();
        String course_id="";
        Student student =studentDao.find("1",student_id);
        Course course=courseDao.find("1",course_id);

        return course;
    }


}