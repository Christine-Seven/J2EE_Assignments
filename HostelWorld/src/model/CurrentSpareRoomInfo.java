package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Seven on 2017/2/21.
 */
@Entity
@Table(name="currentSpareRoomInfo")
public class CurrentSpareRoomInfo {

    @Id
    private int id;
    private String hostelNum;
    private int roomTypeId;
    private int spareNum;


    public String getHostelNum() {
        return hostelNum;
    }

    public void setHostelNum(String hostelNum) {
        this.hostelNum = hostelNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getSpareNum() {
        return spareNum;
    }

    public void setSpareNum(int spareNum) {
        this.spareNum = spareNum;
    }
}
