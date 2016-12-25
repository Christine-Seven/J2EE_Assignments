package dao;

/**
 * Created by Seven on 2016/12/19.
 */
public class StudentDAO {
    String student_id;
    String student_name;
    String grade;
    String gender;
    int age;

    public StudentDAO(String student_id, String student_name, String grade, String gender, int age){
        this.student_id=student_id;
        this.student_name=student_name;
        this.grade=grade;
        this.gender=gender;
        this.age=age;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getGrade() {
        return grade;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}
