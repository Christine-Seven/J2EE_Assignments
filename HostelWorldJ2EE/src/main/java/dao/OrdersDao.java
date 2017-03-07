package dao;

import model.Orders;

import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
public interface OrdersDao {

    /**
     * 新增订单
     * @param orders
     */
    public void save(Orders orders);

    /**
     * 获得下一个订单号
     * @return
     */
    public String getOrderNum(String hostelNum);

    /**
     * 修改订单
     * @param orders
     */
    public void update(Orders orders);

    /**
     * 根据单号查找订单
     * @param orderNum
     * @return
     */
    public Orders find(String orderNum);

    /**
     * 根据客栈查找订单
     * @param hostelNum
     * @return
     */
    public List<Orders> queryByHostel(String hostelNum);

    /**
     * 根据会员编号查找订单
     * @param vipNum
     * @return
     */
    public List<Orders> queryByVip(String vipNum);

    /**
     * 根据客栈和入住日期查找订单
     * @param hostelNum
     * @param checkinDate
     * @return
     */
    public List<Orders> queryByHostelAndCheckin(String hostelNum, String checkinDate);

    /**
     * 根据客栈和离店日期查找订单
     * @param hostelNum
     * @param checkoutDate
     * @return
     */
    public List<Orders> queryByHostelAndCheckout(String hostelNum, String checkoutDate);

    /**
     * 根据订单状态查找
     * @param orderCondition
     * @return
     */
    public List<Orders> queryByCondition(String orderCondition);
}
