package dao;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Seven on 2016/12/19.
 */
public class ScoreDAO {

    StudentDAO studentDAO;
    HashMap<CourseDAO,List<TestDAO>> courseScore;

    public ScoreDAO(StudentDAO studentDAO, HashMap<CourseDAO,List<TestDAO>> courseScore){
        this.studentDAO = studentDAO;
        this.courseScore=courseScore;
    }

    public StudentDAO getStudentDAO(){
        return studentDAO;
    }

    public HashMap<CourseDAO,List<TestDAO>> getCourseScore(){
        return courseScore;
    }
}
