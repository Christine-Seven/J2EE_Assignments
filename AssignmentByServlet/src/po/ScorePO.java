package po;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Seven on 2016/12/19.
 */
public class ScorePO {

    StudentPO studentPO;
    HashMap<CoursePO,List<TestPO>> courseScore;

    public ScorePO(StudentPO studentPO,HashMap<CoursePO ,List<TestPO>> courseScore){
        this.studentPO=studentPO;
        this.courseScore=courseScore;
    }

    public StudentPO getStudentPO(){
        return studentPO;
    }

    public HashMap<CoursePO,List<TestPO>> getCourseScore(){
        return courseScore;
    }
}
