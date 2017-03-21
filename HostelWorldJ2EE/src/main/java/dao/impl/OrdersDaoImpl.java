package dao.impl;

import dao.BaseDao;
import dao.OrdersDao;
import model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.OrderConditionEnum;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seven on 2017/2/21.
 */
@Repository
public class OrdersDaoImpl implements OrdersDao {
    @Autowired
    private BaseDao baseDao;

//    public void setBaseDao(BaseDao baseDao) {
//        this.baseDao = baseDao;
//    }
//
//    public BaseDao getBaseDao() {
//        return baseDao;
//    }


    @Override
    public void save(Orders orders) {
        baseDao.save(orders);
    }

    @Override
    public String getOrderNum(String hostelNum) {
        int currentNum=this.queryByHostel(hostelNum).size();
        DecimalFormat df=new DecimalFormat("000000");
        String newNum=hostelNum+df.format(currentNum+1);
        return newNum;
    }

    @Override
    public void update(Orders orders) {
        baseDao.update(orders);
    }

    @Override
    public Orders find(String orderNum) {
        Orders orders=(Orders) baseDao.load(Orders.class,orderNum);
        return orders;
    }

    @Override
    public List<Orders> queryByHostel(String hostelNum) {
        String sql="select * from hostelworld.orders as o where o.hostelNum=\""+hostelNum+"\";";
        List<Object[]> orderses=baseDao.querySQL(sql);
        return this.getOrders(orderses);
    }

    @Override
    public List<Orders> queryByVip(String vipNum) {
        String sql="select * from hostelworld.orders as o where o.vipNum=\""+vipNum+"\";";
        List<Object[]> orderses=baseDao.querySQL(sql);
        return this.getOrders(orderses);
    }

    @Override
    public List<Orders> queryByHostelAndCheckin(String hostelNum, String checkinDate) {
        String sql="select * from hostelworld.orders as o where o.hostelNum=\""+hostelNum+"\" and o.checkinDate=\""+checkinDate+"\";";
        List<Object[]> orderses=baseDao.querySQL(sql);
        return this.getOrders(orderses);
    }

    @Override
    public List<Orders> queryByHostelAndCheckout(String hostelNum, String checkoutDate) {
        String sql="select * from hostelworld.orders as o where o.hostelNum=\""+hostelNum+"\" and o.checkoutDate=\""+checkoutDate+"\" and o.orderCondition=\"" +
                OrderConditionEnum.CHECKOUT+"\";";
        List<Object[]> orderses=baseDao.querySQL(sql);
        return this.getOrders(orderses);
    }

    @Override
    public List<Orders> queryByCondition(String orderCondition) {
        String sql="select * from hostelworld.orders as o where o.orderCondition=\"" +
                orderCondition+"\";";
        List<Object[]> orderses=baseDao.querySQL(sql);
        return this.getOrders(orderses);
    }

    private List<Orders> getOrders(List<Object[]> objects){
        List<Orders> ordersList=new ArrayList<>();
        for(Object[] object:objects){
            Orders orders=new Orders();
            orders.setOrderNum((String.valueOf(object[0])));
            orders.setHostelNum(String.valueOf(object[1]));
            orders.setVipNum(String.valueOf(object[2]));
            orders.setRoomTypeId((int) object[3]);
            orders.setRoomNum((int)object[4]);
            orders.setRequiredMoney((double)object[5]);
            orders.setPaidMoney((double)object[6]);
            orders.setOrderCondition(String.valueOf(object[7]));
            orders.setCheckinDate(String.valueOf(object[8]));
            orders.setCheckoutDate(String.valueOf(object[9]));
            orders.setPayMethod(String.valueOf(object[10]));
            ordersList.add(orders);
        }
        return ordersList;
    }
}
