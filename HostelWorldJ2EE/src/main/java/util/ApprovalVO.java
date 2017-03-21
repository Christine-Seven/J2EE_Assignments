package util;

/**
 * Created by Seven on 19/03/2017.
 */
public class ApprovalVO {

    private int approvalNum;
    private String applyType;
    private String hostelNum;
    private String hostelName;
    private String province;
    private String city;
    private String address;
    private String hostelInfo;
    private int level;
    private String applyDate;

    public int getApprovalNum() {
        return approvalNum;
    }

    public void setApprovalNum(int approvalNum) {
        this.approvalNum = approvalNum;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getHostelNum() {
        return hostelNum;
    }

    public void setHostelNum(String hostelNum) {
        this.hostelNum = hostelNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHostelInfo() {
        return hostelInfo;
    }

    public void setHostelInfo(String hostelInfo) {
        this.hostelInfo = hostelInfo;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
