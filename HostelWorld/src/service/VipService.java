package service;

import model.Vip;

import java.util.List;

/**
 * Created by Seven on 2017/2/15.
 */
public interface VipService {

    /**
     * 新注册会员
     * @param vip
     */
    public void registerVip(Vip vip);

    /**
     * 缴纳会费大于1000即可激活会员卡
     * @param money
     * @return
     */
    public boolean activiateVip(double money);

    

    /**
     * 修改会员信息
     * @param vip
     */
    public void updateVip(Vip vip);

    /**
     * 根据编号查找会员
     * @param vipNum
     * @return
     */
    public Vip findVipById(String vipNum);

    /**
     * 获得所有会员列表
     * @return
     */
    public List<Vip> getAllVipList();

}
