package model;

/**
 * Created by Seven on 2017/2/20.
 */
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="course")
public class Course  implements Serializable{
    @Id
    int id;
    @Column
    String course_id;
    @Column
    String course_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}
