package po;

/**
 * Created by Seven on 2016/12/19.
 */
public class CoursePO {

    String course_id;
    String course_name;

    public CoursePO(String course_id,String course_name){
        this.course_id=course_id;
        this.course_name=course_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }
}
