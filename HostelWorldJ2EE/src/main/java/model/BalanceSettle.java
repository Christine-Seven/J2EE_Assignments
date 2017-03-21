package model;

import javax.persistence.*;

/**
 * Created by Seven on 2017/2/21.
 */
@Entity
@Table(name="balanceSettle")
public class BalanceSettle {

    @Id
    private int settleNum;
    private String settleDate;
    private double balance;
    private String hostelNum;
    private String settleCondition;

    public String getHostelNum() {
        return hostelNum;
    }

    public void setHostelNum(String hosetlNum) {
        this.hostelNum = hosetlNum;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getSettleCondition() {
        return settleCondition;
    }

    public void setSettleCondition(String settleCondition) {
        this.settleCondition = settleCondition;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public int getSettleNum() {
        return settleNum;
    }

    public void setSettleNum(int settleNum) {
        this.settleNum = settleNum;
    }
}
