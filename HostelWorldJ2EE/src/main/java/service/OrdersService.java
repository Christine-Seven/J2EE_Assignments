package service;

import model.Orders;

import java.util.List;
import java.util.Map;

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

    /**
     * 取消订单
     * @param orderNum
     * @return
     */
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

    /**
     * 根据订单状态查询订单
     * @param state
     * @return
     */
    public List<Orders> queryByState(String state);

    /**
     * 获得所有订单
     * @return
     */
    public List<Orders> queryAll();

    /**
     * 获得订单集合每月平均房价
     * Integer-->月份，Double[0]-->adr，Double[1]-->实际客房收入
     * @param ordersList
     * @return
     */
    public Map<Integer,Double[]> getAdrByMonth(List<Orders> ordersList);

    /**
     *  获得订单集合每月入住率
     *  Integer-->月份，Double[0]-->occ，Double[1]-->实际入住订单数
     * @param ordersList
     * @return
     */
    public Map<Integer,Double[]> getOccByMonth(List<Orders> ordersList);

    /**
     * 获得订单集合每月平均每间可售房收入
     * Integer-->月份，Double[0]-->revpar，Double[1]-->可售房数量
     * @param ordersList
     * @return
     */
    public Map<Integer,Double[]> getRevparByMonth(List<Orders> ordersList);


}
