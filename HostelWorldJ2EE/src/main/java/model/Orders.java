package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Seven on 2017/2/21.
 */
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    private String orderNum;
    private String hostelNum;
    private String vipNum;
    private int roomTypeId;
    private double requiredMoney;
    private double paidMoney;
    private String orderCondition;
    private String checkinDate;
    private String checkoutDate;

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
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

    public String getOrderCondition() {
        return orderCondition;
    }

    public void setOrderCondition(String orderCondition) {
        this.orderCondition = orderCondition;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(double paidMoney) {
        this.paidMoney = paidMoney;
    }

    public double getRequiredMoney() {
        return requiredMoney;
    }

    public void setRequiredMoney(double requiredMoney) {
        this.requiredMoney = requiredMoney;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getVipNum() {
        return vipNum;
    }

    public void setVipNum(String vipNum) {
        this.vipNum = vipNum;
    }
}
