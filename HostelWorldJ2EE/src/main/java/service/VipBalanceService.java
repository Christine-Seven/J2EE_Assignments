package service;

/**
 * Created by Seven on 2017/2/23.
 */
public interface VipBalanceService {


    /**
     * 缴纳会费大于1000即可激活会员卡
     * @param vipNum
     * @param money
     * @return
     */
    public boolean activateVip(String vipNum,double money);

    /**
     * 根据消费金额获取积分并升级
     * 按照1：1比例
     * @param money
     * @return
     */
    public int addCredit(String vipNum,double money);

    /**
     * 将积分兑换为卡余额
     * 10:1
     * @param point
     * @return
     */
    public double convertCreditToMoney(String vipNum,double point);


//    /**
//     * 根据积分获得该会员卡对应的级别
//     * 1-500 level 1
//     * 500-1000 level 2
//     * 1000-2000 3
//     * 2000-5000 4
//     * >5000 5
//     * @param point
//     * @return
//     */
//    public int getVipLevel(double point);

    /**
     * 充值会员卡
     * 若此时会员卡处于暂停状态，则验证充值后余额是否大于1000
     * 若大于，则恢复该会员卡为激活状态，并返回成功
     * 否则返回错误
     * @param vipNum
     * @param money
     * @return
     */
    public boolean topUp(String vipNum, double money);

    /**
     * 用会员卡消费
     * 返回余额
     * @param vipNum
     * @param money
     * @return
     */
    public double withdraw(String vipNum,double money);
}
