package model;

import javax.persistence.*;

/**
 * Created by Seven on 2017/2/21.
 */
@Entity
@Table(name="roomType")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String roomType;
//    private String roomNum;
//    private String hostelNum;
//    private String roomState;
//
//    public String getRoomState() {
//        return roomState;
//    }
//
//    public void setRoomState(String roomState) {
//        this.roomState = roomState;
//    }
//
//    public String getHostelNum() {
//        return hostelNum;
//    }
//
//    public void setHostelNum(String hostelNum) {
//        this.hostelNum = hostelNum;
//    }
//
//    public String getRoomNum() {
//        return roomNum;
//    }
//
//    public void setRoomNum(String roomNum) {
//        this.roomNum = roomNum;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
