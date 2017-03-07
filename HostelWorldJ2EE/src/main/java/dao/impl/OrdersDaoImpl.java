package dao.impl;

import dao.BaseDao;
import dao.OrdersDao;
import model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.OrderConditionEnum;

import java.text.DecimalFormat;
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
        List<Orders> orderses=baseDao.querySQL(sql);
        return orderses;
    }

    @Override
    public List<Orders> queryByVip(String vipNum) {
        String sql="select * from hostelworld.orders as o where o.vipNum=\""+vipNum+"\";";
        List<Orders> orderses=baseDao.querySQL(sql);
        return orderses;
    }

    @Override
    public List<Orders> queryByHostelAndCheckin(String hostelNum, String checkinDate) {
        String sql="select * from hostelworld.orders as o where o.hostelNum=\""+hostelNum+"\" and o.checkinDate=\""+checkinDate+"\";";
        List<Orders> orderses=baseDao.querySQL(sql);
        return orderses;
    }

    @Override
    public List<Orders> queryByHostelAndCheckout(String hostelNum, String checkoutDate) {
        String sql="select * from hostelworld.orders as o where o.hostelNum=\""+hostelNum+"\" and o.checkoutDate=\""+checkoutDate+"\" and o.orderCondition=\"" +
                OrderConditionEnum.checkout+"\";";
        List<Orders> orderses=baseDao.querySQL(sql);
        return orderses;
    }

    @Override
    public List<Orders> queryByCondition(String orderCondition) {
        String sql="select * from hostelworld.orders as o where o.orderCondition=\"" +
                orderCondition+"\";";
        List<Orders> orderses=baseDao.querySQL(sql);
        return orderses;
    }
}
