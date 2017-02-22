package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Seven on 2017/2/14.
 */
@Entity
@Table(name="hostel")
public class Hostel {

    @Id
    private String hostelNum;
    private String hostelName;
    private String hostelPassword;
    private double profit;
    private int level;
    private String province;
    private String city;
    private String address;
    private String hostelInfo;
    private String approvalState;

    public String getHostelNum() {
        return hostelNum;
    }

    public void setHostelNum(String hostelNum) {
        this.hostelNum = hostelNum;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getHostelPassword() {
        return hostelPassword;
    }

    public void setHostelPassword(String hostelPassword) {
        this.hostelPassword = hostelPassword;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
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

    public String getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(String approvalState) {
        this.approvalState = approvalState;
    }

    public String getHostelInfo() {
        return hostelInfo;
    }

    public void setHostelInfo(String hostelInfo) {
        this.hostelInfo = hostelInfo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
