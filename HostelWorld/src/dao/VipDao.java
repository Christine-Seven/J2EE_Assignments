package dao;

import model.Vip;

import java.util.List;

/**
 * Created by Seven on 2017/2/15.
 */
public interface VipDao {

    /**
     * 新增会员
     * @param vip
     */
    public void save(Vip vip);

    /**
     * 修改会员信息
     * @param vip
     */
    public void update(Vip vip);

    /**
     * 根据会员编号查找会员
     * @param vipNum
     * @return
     */
    public Vip find(String vipNum);

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
