package dao.impl;

import dao.BaseDao;
import dao.OrdersDao;
import model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        String sql="select * from hostelworld.orders as o where o.hostelNum=\""+hostelNum+"\" and checkinDate=\""+checkinDate+"\";";
        List<Orders> orderses=baseDao.querySQL(sql);
        return orderses;
    }

    @Override
    public List<Orders> queryByHostelAndCheckout(String hostelNum, String checkoutDate) {
        String sql="select * from hostelworld.orders as o where o.hostelNum=\""+hostelNum+"\" and checkoutDate=\""+checkoutDate+"\";";
        List<Orders> orderses=baseDao.querySQL(sql);
        return orderses;
    }
}
