package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Seven on 2017/2/21.
 */
@Entity
@Table(name="lodgerInfo")
public class LodgerInfo {

    @Id
    private String lodgerNum;
    private String lodgerName;
    private String idNum;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getLodgerName() {
        return lodgerName;
    }

    public void setLodgerName(String lodgerName) {
        this.lodgerName = lodgerName;
    }

    public String getLodgerNum() {
        return lodgerNum;
    }

    public void setLodgerNum(String lodgerNum) {
        this.lodgerNum = lodgerNum;
    }
}
