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
