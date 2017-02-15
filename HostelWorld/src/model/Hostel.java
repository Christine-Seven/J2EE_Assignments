package model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Seven on 2017/2/14.
 */
@Entity
public class Hostel {

    private int id;
    private String hostelNum;
    private String hostelPassword;
    private int totalRoomNum;
    private int spareRoomNum;
    private double profit;
    private String province;
    private String city;
    private String address;

    public String getHostelNum() {
        return hostelNum;
    }

    public void setHostelNum(String hostelNum) {
        this.hostelNum = hostelNum;
    }

    public String getHostelPassword() {
        return hostelPassword;
    }

    public void setHostelPassword(String hostelPassword) {
        this.hostelPassword = hostelPassword;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getSpareRoomNum() {
        return spareRoomNum;
    }

    public void setSpareRoomNum(int spareRoomNum) {
        this.spareRoomNum = spareRoomNum;
    }

    public int getTotalRoomNum() {
        return totalRoomNum;
    }

    public void setTotalRoomNum(int totalRoomNum) {
        this.totalRoomNum = totalRoomNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
