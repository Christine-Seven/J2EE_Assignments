package model;

import javax.persistence.*;

/**
 * Created by Seven on 2017/2/24.
 */
@Entity
@Table(name="approval")
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int approvalNum;
    private String hostelNum;
    private String approvalType;
    private String approveState;
    private String approveDate;
    private String applyDate;


    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public int getApprovalNum() {
        return approvalNum;
    }

    public void setApprovalNum(int approvalNum) {
        this.approvalNum = approvalNum;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public String getApproveState() {
        return approveState;
    }

    public void setApproveState(String approveState) {
        this.approveState = approveState;
    }

    public String getHostelNum() {
        return hostelNum;
    }

    public void setHostelNum(String hostelNum) {
        this.hostelNum = hostelNum;
    }
}
