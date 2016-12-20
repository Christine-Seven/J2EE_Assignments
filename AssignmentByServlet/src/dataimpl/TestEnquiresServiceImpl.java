package dataimpl;

import po.CoursePO;
import po.ScorePO;
import po.StudentPO;
import po.TestPO;
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
public class TestEnquiresServiceImpl {

    public ScorePO getScorePO(String student_id) throws SQLException {
        Connection connection = Constant.connection;
        HashMap<CoursePO, List<TestPO>> courseScore = new HashMap<>();

        Statement statement = connection.createStatement();
        //get student info
        String getstudent_sql="SELECT student_name,gender,age,grade FROM student WHERE student_id="+student_id+";";
        ResultSet student_rs=statement.executeQuery(getstudent_sql);
        String student_name=student_rs.getString("student_name");
        String grade=student_rs.getString("grade");
        String gender=student_rs.getString("gender");
        int age=student_rs.getInt("age");
        StudentPO studentPO=new StudentPO(student_id,student_name,grade,gender,age);
        //get selected course
        String getselection_sql = "SELECT course_id FROM selection " +
                "WHERE student_id=" + student_id + ";";
        ResultSet courseid_rs = statement.executeQuery(getselection_sql);
        while (courseid_rs.next()) {
            //get course
            String course_id = courseid_rs.getString("course_id");
            String getcourse_sql = "SELECT course_name FROM course " +
                    "WHERE course_id=" + course_id + ";";
            ResultSet course_rs = statement.executeQuery(getcourse_sql);
            CoursePO coursePO;
            if (course_rs.next()) {
                String course_name = course_rs.getString("course_name");
                coursePO = new CoursePO(course_id, course_name);
                //get test
                String gettest_sql = "SELECT test_id,test_name,`date` FROM test WHERE course_id=" + course_id + ";";
                ResultSet testid_rs = statement.executeQuery(gettest_sql);
                List<TestPO> testPOList = new ArrayList<>();
                while (testid_rs.next()) {
                    String test_id = testid_rs.getString("test_id");
                    String test_name = testid_rs.getString("test_name");
                    String date = String.valueOf(testid_rs.getDate("date"));
                    String getscore_sql = "SELECT score FROM score " +
                            "WHERE student_id=" + student_id + " AND test_id=" + test_id + ";";
                    ResultSet score_rs = statement.executeQuery(getscore_sql);
                    if (score_rs.next()) {
                        int score = score_rs.getInt("score");
                        TestPO testPO = new TestPO(course_id, test_id, test_name, date, score);
                        testPOList.add(testPO);
                    } else {
                        TestPO testPO = null;
                        testPOList.add(testPO);
                    }
                }
                courseScore.put(coursePO,testPOList);
            }
        }
        ScorePO scorePO=new ScorePO(studentPO,courseScore);
        return scorePO;
    }
}