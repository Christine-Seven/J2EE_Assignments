package model;

import javax.persistence.*;

/**
 * Created by Seven on 2017/2/21.
 */
@Entity
@Table(name="checkInfo")
public class CheckInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int checkNum;
    private String hostelNum;
    private String lodgerNum;
    private double paidMoney;
    private String checkCondition;
    private String checkinDate;
    private String checkoutDate;
    private int roomTypeId;

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getCheckCondition() {
        return checkCondition;
    }

    public void setCheckCondition(String checkCondition) {
        this.checkCondition = checkCondition;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getHostelNum() {
        return hostelNum;
    }

    public void setHostelNum(String hostelNum) {
        this.hostelNum = hostelNum;
    }

    public String getLodgerNum() {
        return lodgerNum;
    }

    public void setLodgerNum(String lodgerNum) {
        this.lodgerNum = lodgerNum;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(double paidMoney) {
        this.paidMoney = paidMoney;
    }
}
