package model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Seven on 2016/12/19.
 */
@Entity
@Table(name="score")
public class Score {

    Student student;
    HashMap<Course,List<Test>> courseScore;

    public Score(Student student, HashMap<Course,List<Test>> courseScore){
        this.student = student;
        this.courseScore=courseScore;
    }

    public Student getStudent(){
        return student;
    }

    public HashMap<Course,List<Test>> getCourseScore(){
        return courseScore;
    }
}
