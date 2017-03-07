package service;

import model.Vip;

import java.util.List;

/**
 * Created by Seven on 2017/2/15.
 */
public interface VipService {

    /**
     * 验证该会员编号是否存在
     * @param vipNum
     * @return
     */
    public boolean isExist(String vipNum);

    /**
     * 验证会员密码是否正确
     * @param vipNum
     * @param password
     * @return
     */
    public boolean checkPassword(String vipNum,String password);

    /**
     * 新注册会员
     * @param vip
     */
    public void registerVip(Vip vip);

    /**
     * 获得可用会员编号
     * @return
     */
    public String getVipNum();

    /**
     * 会员主动取消资格，删除该会员卡
     * @param vipNum
     * @param password
     * @return
     */
    public boolean cancelVip(String vipNum,String password);

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
     * 根据会员名查找会员
     * @param vipName
     * @return
     */
    public List<Vip> queryByName(String vipName);

    /**
     * 获得所有会员列表
     * @return
     */
    public List<Vip> getAllVipList();


}
