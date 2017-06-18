package service.impl;

import dao.OrdersDao;
import dao.VipDao;
import model.Orders;
import model.RoomPlan;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.OrdersService;
import service.RoomPlanService;
import service.VipBalanceService;
import util.OrderConditionEnum;
import util.Str2Calendar;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private VipDao vipDao;
    @Autowired
    VipBalanceService vipBalanceService;
    @Autowired
    RoomPlanService roomPlanService;

    @Override
    public void saveOrders(Orders orders) {
        ordersDao.save(orders);
    }

    @Override
    public String getOrderNum(String hostelNum) {
        return ordersDao.getOrderNum(hostelNum);
    }

    @Override
    public void updateOrders(Orders orders) {
        ordersDao.update(orders);
    }

    @Override
    public boolean pay(String orderNum, double money) {
        Orders orders=ordersDao.find(orderNum);
        if(null!=orders){
            double requiredMoney=orders.getRequiredMoney();
            if(money==requiredMoney){
                Vip vip=vipDao.find(orders.getVipNum());
                double curmoney=vip.getMoney();
                if(vipBalanceService.withdraw(vip.getVipNum(),money)>=0){
                    orders.setPaidMoney(money);
                    orders.setOrderCondition(OrderConditionEnum.VALID.toString());
                    ordersDao.update(orders);
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean cancel(String orderNum) {
        Orders orders=ordersDao.find(orderNum);
        if(null!=orders&&(orders.getOrderCondition().equals(OrderConditionEnum.VALID.toString())||(orders.getOrderCondition().equals(OrderConditionEnum.BOOK.toString())))){
            double requiredMoney=orders.getRequiredMoney();
            double paidMoney=orders.getPaidMoney();
            if(paidMoney==requiredMoney){
                //收取30%的手续费，其他费用退还
                orders.setPaidMoney(paidMoney-requiredMoney*0.7);
                orders.setOrderCondition(OrderConditionEnum.CANCEL.toString());
                ordersDao.update(orders);
                Vip vip=vipDao.find(orders.getVipNum());
                if(null!=vip){
                    double currentMoney=vip.getMoney();
                    vip.setMoney(currentMoney+requiredMoney*0.7);
                    vipDao.update(vip);
                    return true;
                }
            }else{
                orders.setOrderCondition(OrderConditionEnum.CANCEL.toString());
                ordersDao.update(orders);
            }
            return true;
        }
        return false;
    }

    @Override
    public Orders find(String orderNum) {
        return ordersDao.find(orderNum);
    }

    @Override
    public List<Orders> queryByHostel(String hostelNum) {
        return ordersDao.queryByHostel(hostelNum);
    }

    @Override
    public List<Orders> queryByVip(String vipNum) {
        return ordersDao.queryByVip(vipNum);
    }

    @Override
    public List<Orders> queryByHostelAndCheckin(String hostelNum, String checkinDate) {
        return ordersDao.queryByHostelAndCheckin(hostelNum,checkinDate);
    }

    @Override
    public List<Orders> queryByHostelAndCheckout(String hostelNum, String checkoutDate) {
        return ordersDao.queryByHostelAndCheckout(hostelNum, checkoutDate);
    }

    @Override
    public List<Orders> queryByState(String state) {
        return ordersDao.queryByCondition(state);
    }

    @Override
    public List<Orders> queryAll() {
        return ordersDao.queryAll();
    }

    @Override
    public Map<Integer, Double[]> getAdrByMonth(List<Orders> ordersList) {
        Map<Integer, Double[]> adrByMonth = new HashMap<>();
        for (Orders orders : ordersList) {
            Calendar c = Str2Calendar.str2Calendar(orders.getCheckinDate());
            int month = c.get(Calendar.MONTH);
            double price = orders.getPaidMoney();

            if (!adrByMonth.containsKey(month)) {
                Double[] numAndPrice = new Double[2];
                numAndPrice[0] = 1.0;
                numAndPrice[1] = price;
                adrByMonth.put(month, numAndPrice);
            } else {
                Double[] numAndPrice = adrByMonth.get(month);
                numAndPrice[0]++;
                numAndPrice[1] = numAndPrice[1] + price;
                adrByMonth.put(month, numAndPrice);
            }
        }
        adrByMonth=getPropotion(adrByMonth);
        return adrByMonth;
    }

    @Override
    public Map<Integer, Double[]> getOccByMonth(List<Orders> ordersList) {
        Map<Integer, Double[]> occByMonth = new HashMap<>();

        for (Orders orders : ordersList) {
            Calendar c = Str2Calendar.str2Calendar(orders.getCheckinDate());
            int month = c.get(Calendar.MONTH);
            if (!occByMonth.containsKey(month)) {
                //循环遍历这个月的每一天
                //访问其当天的房价计划，获得可住房间数目总和
                //而后统计该月订单数目，将二者相除
                Double[] numAndTotal = new Double[2];
                List<RoomPlan> roomPlans = roomPlanService.queryNewestRoomPlan(orders.getHostelNum());
                int roomNum = 0;
                for (RoomPlan roomPlan : roomPlans) {
                    roomNum = roomNum + roomPlan.getRoomNum();
                }
                int totalRoom = roomNum * getDaysInMonth(month);
                numAndTotal[0] = totalRoom * 1.0;
                numAndTotal[1] = 1.0;
                occByMonth.put(month, numAndTotal);
            } else {
                Double[] numAndTotal = occByMonth.get(month);
                numAndTotal[1] = numAndTotal[1] + 1;
                occByMonth.put(month, numAndTotal);
            }
        }
        occByMonth=getPropotion(occByMonth);
        return occByMonth;
    }

    @Override
    public Map<Integer, Double[]> getRevparByMonth(List<Orders> ordersList) {
        Map<Integer, Double[]> revparByMonth = new HashMap<>();
        for (Orders orders : ordersList) {
            Calendar c = Str2Calendar.str2Calendar(orders.getCheckinDate());
            int month = c.get(Calendar.MONTH);
            double price = orders.getPaidMoney();
            if (!revparByMonth.containsKey(month)) {
                List<RoomPlan> roomPlans = roomPlanService.queryNewestRoomPlan(orders.getHostelNum());
                int roomNum = 0;
                for (RoomPlan roomPlan : roomPlans) {
                    roomNum = roomNum + roomPlan.getRoomNum();
                }
                int totalRoom = roomNum * getDaysInMonth(month);
                Double[] priceAndTotal = new Double[2];
                priceAndTotal[0] = totalRoom * 1.0;
                priceAndTotal[1] = price;
                revparByMonth.put(month, priceAndTotal);
            } else {
                Double[] priceAndTotal = revparByMonth.get(month);
                priceAndTotal[1] = priceAndTotal[1] + price;
                revparByMonth.put(month, priceAndTotal);
            }
        }
        revparByMonth = getPropotion(revparByMonth);
        return revparByMonth;
    }



    //获得最终的比例指标
    private Map<Integer,Double[]> getPropotion(Map<Integer,Double[]> quota){
        for (Integer months : quota.keySet()) {
            Double[] numAndTotal = quota.get(months);
            if (numAndTotal[0] != 0) {
                numAndTotal[0] = numAndTotal[1] / numAndTotal[0];
            } else {
                numAndTotal[0] = 0.0;
            }
            quota.put(months, numAndTotal);
        }
        return quota;
    }

    private int getDaysInMonth(int month) {
        switch (month) {
            case 1:
                return 31;
            case 2:
                return 28;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;
            default:
                return 0;
        }
    }
}
