package dao;

import model.Manager;

import java.util.List;

/**
 * Created by Seven on 2017/2/24.
 */
public interface ManagerDao {

    /**
     * 验证经理的存在
     * @param managerNum
     * @return
     */
    public boolean checkManager(String managerNum);

    /**
     * 验证密码正确性
     * @param managerNum
     * @param password
     * @return
     */
    public boolean checkPassword(String managerNum,String password);

    /**
     * 新增一位经理
     * @param manager
     */
    public void save(Manager manager);

    /**
     * 更新经理信息
     * @param manager
     */
    public void update(Manager manager);

    /**
     * 获得所有经理
     * @return
     */
    public List<Manager> getAllList();

    /**
     * 根据经理编号查找经理
     * @param managerNum
     * @return
     */
    public Manager queryByNum(String managerNum);

    /**
     * 生成可用经理编号
     * @return
     */
    public String getManagerNum();

}
