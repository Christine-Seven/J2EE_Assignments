package model;

/**
 * Created by Seven on 2016/12/19.
 */
@Entity
@Table(name="test")
public class Test {

    String course_id;
    String test_id;
    String test_name;
    String date;
    int score;

    public Test(String course_id, String test_id, String test_name, String date, int score){
        this.course_id=course_id;
        this.test_id=test_id;
        this.test_name=test_name;
        this.date=date;
        this.score=score;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getTest_id() {
        return test_id;
    }

    public String getTest_name() {
        return test_name;
    }

    public String getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }
}
