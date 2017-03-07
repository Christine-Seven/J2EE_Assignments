package service;

import model.Orders;

import java.util.List;

/**
 * Created by Seven on 2017/2/23.
 */
public interface OrdersService {

    /**
     * 新增订单
     * @param orders
     * @return
     */
    public void saveOrders(Orders orders);

    /**
     * 获得可用订单编号
     * @param hostelNum
     * @return
     */
    public String getOrderNum(String hostelNum);

    /**
     * 更新订单信息
     * @param orders
     * @return
     */
    public void updateOrders(Orders orders);

    /**
     * 预支付订金
     * @param orderNum
     * @param money
     * @return
     */
    public boolean pay(String orderNum, double money);

    public boolean cancel(String orderNum);

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


}
