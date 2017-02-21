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
     * 获得所有会员列表
     * @return
     */
    public List<Vip> getAllVipList();
}
