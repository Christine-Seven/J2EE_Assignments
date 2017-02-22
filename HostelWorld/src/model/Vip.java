package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Seven on 2017/2/15.
 */
@Entity
@Table(name="vip")
public class Vip implements Serializable {

    @Id
    private String vipNum;
    private String vipName;
    private String vipPassword;
    private String bankCardId;
    private double vipPoint;
    private double money;
    private String conditionChangeDate;
    private String condition;
    private String activateDate;

    public String getBankCard() {
        return bankCardId;
    }

    public void setBankCard(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getConditionChangeDate() {
        return conditionChangeDate;
    }

    public void setConditionChangeDate(String conditionChangeDate) {
        this.conditionChangeDate = conditionChangeDate;
    }

    public String getVipNum() {
        return vipNum;
    }

    public void setVipNum(String vipNum) {
        this.vipNum = vipNum;
    }

    public String getVipPassword() {
        return vipPassword;
    }

    public void setVipPassword(String vipPassword) {
        this.vipPassword = vipPassword;
    }

    public double getVipPoint() {
        return vipPoint;
    }

    public void setVipPoint(double vipPoint) {
        this.vipPoint = vipPoint;
    }

    public String getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(String activateDate) {
        this.activateDate = activateDate;
    }
}
