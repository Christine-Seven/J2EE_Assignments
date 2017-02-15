package model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Seven on 2017/2/15.
 */
@Entity
public class Vip {
    private int id;
    private String vipNum;
    private String vipName;
    private String vipPassword;
    private String bankCard;
    private double vipPoint;
    private int vipLevel;
    private double money;
    private String conditionDate;
    private String condition;

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
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

    public String getConditionDate() {
        return conditionDate;
    }

    public void setConditionDate(String conditionDate) {
        this.conditionDate = conditionDate;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
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
}
