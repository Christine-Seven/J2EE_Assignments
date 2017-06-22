package service.impl;

import model.Orders;
import model.RoomPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HostelService;
import service.OrdersService;
import service.RoomPlanService;
import service.WorldStaService;
import util.Str2Calendar;

import java.util.*;

/**
 * Created by Seven on 16/06/2017.
 */
@Service
public class WorldStaServiceImpl implements WorldStaService {
    @Autowired
    OrdersService ordersService;
    @Autowired
    HostelService hostelService;
    @Autowired
    RoomPlanService roomPlanService;

    @Override
    public Map<Integer, Double[]> getAdrByMonth() {
        List<Orders> ordersList = ordersService.queryAll();
        return ordersService.getAdrByMonth(ordersList);
    }

    @Override
    public Map<String, Double[]> getAdrByCity() {
        Map<String, Double[]> adrByCity = new HashMap<>();
        List<Orders> ordersList = ordersService.queryAll();
        for (Orders orders : ordersList) {
            String hostelNum = orders.getHostelNum();
            String city = hostelService.queryHostelByNum(hostelNum).getCity();
            double price = orders.getPaidMoney();
            if (!adrByCity.containsKey(city)) {
                Double[] numAndPrice = new Double[2];
                numAndPrice[0] = 1.0;
                numAndPrice[1] = price;
                adrByCity.put(city, numAndPrice);
            } else {
                Double[] numAndPrice = adrByCity.get(city);
                numAndPrice[0] = numAndPrice[0] + 1;
                numAndPrice[1] = numAndPrice[1] + price;
                adrByCity.put(city, numAndPrice);
            }
        }
        adrByCity = getPropotion(adrByCity);
        return adrByCity;
    }

    @Override
    public Map<Integer, Double[]> getOccByMonth() {
        return ordersService.getOccByMonth(ordersService.queryAll());
    }

    @Override
    public Map<String, Double[]> getOccByCity() {
        Map<String, Double[]> occByCity = new HashMap<>();
        List<Orders> ordersList = ordersService.queryAll();

        for (Orders orders : ordersList) {
            String hostelNum = orders.getHostelNum();
            String city = hostelService.queryHostelByNum(hostelNum).getCity();
            if (!occByCity.containsKey(city)) {
                Double[] numAndTotal = new Double[2];
                List<RoomPlan> roomPlans = roomPlanService.queryNewestRoomPlan(orders.getHostelNum());
                int roomNum = 0;
                for (RoomPlan roomPlan : roomPlans) {
                    roomNum = roomNum + roomPlan.getRoomNum();
                }
                int totalRoom = roomNum;
                numAndTotal[0] = totalRoom * 1.0;
                numAndTotal[1] = 1.0;
                occByCity.put(city, numAndTotal);
            } else {
                Double[] numAndTotal = occByCity.get(city);
                List<RoomPlan> roomPlans = roomPlanService.queryNewestRoomPlan(orders.getHostelNum());
                int roomNum = 0;
                for (RoomPlan roomPlan : roomPlans) {
                    roomNum = roomNum + roomPlan.getRoomNum();
                }
                int totalRoom = roomNum;
                numAndTotal[0] = numAndTotal[0] + totalRoom;
                numAndTotal[1] = numAndTotal[1] + 1;
                occByCity.put(city, numAndTotal);
            }
        }
        occByCity = getPropotion(occByCity);
        return occByCity;
    }

    @Override
    public Map<Integer, Double[]> getRevparByMonth() {
        return ordersService.getRevparByMonth(ordersService.queryAll());
    }

    @Override
    public Map<String, Double[]> getRevparByCity() {
        Map<String, Double[]> revparByCity = new HashMap<>();
        List<Orders> ordersList = ordersService.queryAll();
        for (Orders orders : ordersList) {
            String hostelNum = orders.getHostelNum();
            String city = hostelService.queryHostelByNum(hostelNum).getCity();
            if (!revparByCity.containsKey(city)) {
                List<RoomPlan> roomPlans = roomPlanService.queryNewestRoomPlan(orders.getHostelNum());
                int roomNum = 0;
                for (RoomPlan roomPlan : roomPlans) {
                    roomNum = roomNum + roomPlan.getRoomNum();
                }
                int totalRoom = roomNum;
                Double[] priceAndTotal = new Double[2];
                priceAndTotal[0] = totalRoom * 1.0;
                priceAndTotal[1] = orders.getPaidMoney();
                revparByCity.put(city, priceAndTotal);
            } else {
                List<RoomPlan> roomPlans = roomPlanService.queryNewestRoomPlan(orders.getHostelNum());
                int roomNum = 0;
                for (RoomPlan roomPlan : roomPlans) {
                    roomNum = roomNum + roomPlan.getRoomNum();
                }
                int totalRoom = roomNum;
                Double[] priceAndTotal = revparByCity.get(city);
                priceAndTotal[0] = priceAndTotal[0] + totalRoom;
                priceAndTotal[1] = priceAndTotal[1] + orders.getPaidMoney();
                revparByCity.put(city, priceAndTotal);
            }
        }
        revparByCity = getPropotion(revparByCity);
        return revparByCity;
    }

    @Override
    public Map<Integer, Double> getMoneyByTime() {
        List<Orders> ordersList = ordersService.queryAll();
        Map<Integer, Double> moneyByTime = new HashMap<>();

        for (Orders orders : ordersList) {
            String hostelNum = orders.getHostelNum();
            String date = hostelService.queryHostelByNum(hostelNum).getApplyDate();
            int time = getTime(date);
            double price = orders.getPaidMoney();
            if (!moneyByTime.containsKey(time)) {
                moneyByTime.put(time, price);
            } else {
                double money = moneyByTime.get(time);
                money = money + price;
                moneyByTime.put(time, money);
            }
        }
        return moneyByTime;
    }

    @Override
    public Map<String, Double> getMoneyByCity() {
        List<Orders> ordersList = ordersService.queryAll();
        Map<String,Double> moneyByCity = new HashMap<>();

        for(Orders orders:ordersList){
            String hostelNum=orders.getHostelNum();
            String city=hostelService.queryHostelByNum(hostelNum).getCity();
            double price = orders.getPaidMoney();
            if(!moneyByCity.containsKey(city)){
                moneyByCity.put(city,price);
            }else{
                double money=moneyByCity.get(city);
                money=money+price;
                moneyByCity.put(city,money);
            }
        }

        return moneyByCity;
    }

    @Override
    public Map<Integer, Double> getMoneyByLevel() {
        List<Orders> ordersList = ordersService.queryAll();
        Map<Integer,Double> moneyByLevel=new HashMap<>();

        for(Orders orders:ordersList){
            String hostelNum=orders.getHostelNum();
            Integer level=hostelService.queryHostelByNum(hostelNum).getLevel();

            double price = orders.getPaidMoney();
            if(!moneyByLevel.containsKey(level)){
                moneyByLevel.put(level,price);
            }else{
                double money=moneyByLevel.get(level);
                money=money+price;
                moneyByLevel.put(level,money);
            }
        }
        return moneyByLevel;
    }

    @Override
    public Map<Integer, Double> getMoneyByMonth() {
        List<Orders> ordersList = ordersService.queryAll();
        Map<Integer,Double> moneyByMonth=new HashMap<>();

        for(Orders orders:ordersList){
            int month=getMonthByOrder(orders);
            double price = orders.getPaidMoney();
            if(!moneyByMonth.containsKey(month)){
                moneyByMonth.put(month,price);
            }else{
                double money=moneyByMonth.get(month);
                money=money+price;
                moneyByMonth.put(month,money);
            }
        }

        return moneyByMonth;
    }

    @Override
    public Map<Integer, Double> getMoneyBySeason() {
        List<Orders> ordersList = ordersService.queryAll();
        Map<Integer,Double> moneyBySeason=new HashMap<>();

        for(Orders orders:ordersList){
            int month=getSeasonByOrder(orders);
            double price = orders.getPaidMoney();
            if(!moneyBySeason.containsKey(month)){
                moneyBySeason.put(month,price);
            }else{
                double money=moneyBySeason.get(month);
                money=money+price;
                moneyBySeason.put(month,money);
            }
        }
        return moneyBySeason;
    }

    @Override
    public Map<Integer, Set<String>> getActiveByMonth() {
        List<Orders> ordersList = ordersService.queryAll();
        Map<Integer,Set<String>> activeByMonth = new HashMap<>();

        for(Orders orders:ordersList){
            int month=getMonthByOrder(orders);
            String vipNum=orders.getVipNum();
            if(!activeByMonth.containsKey(month)){
                Set<String> vips=new HashSet<>();
                vips.add(vipNum);
                activeByMonth.put(month,vips);
            }else{
                Set<String> vips=activeByMonth.get(month);
                if(!vips.contains(vipNum)){
                    vips.add(vipNum);
                }
                activeByMonth.put(month,vips);
            }
        }
        return activeByMonth;
    }

    @Override
    public Map<String, Set<String>> getActiveByCity() {
        List<Orders> ordersList = ordersService.queryAll();
        Map<String,Set<String>> activeByCity = new HashMap<>();

        for(Orders orders:ordersList){
            String hostelNum=orders.getHostelNum();
            String city = hostelService.queryHostelByNum(hostelNum).getCity();
            if(!activeByCity.containsKey(city)){
                Set<String> vips=new HashSet<>();
                if(isActive(orders)){
                    vips.add(orders.getVipNum());
                }
                activeByCity.put(city,vips);
            }else{
                Set<String> vips=activeByCity.get(city);
                if(isActive(orders)){
                    vips.add(orders.getVipNum());
                }
                activeByCity.put(city,vips);
            }
        }
        return activeByCity;
    }

    @Override
    public Map<String, Integer> getCityByTime() {
        List<Orders> ordersList = ordersService.queryAll();
        Map<String,Integer> cityByTime=new HashMap<>();

        for(Orders orders:ordersList){
            String hostelNum=orders.getHostelNum();
            String city = hostelService.queryHostelByNum(hostelNum).getCity();
            if(!cityByTime.containsKey(city)){
                if(isActive(orders)){
                    cityByTime.put(city,1);
                }else{
                    cityByTime.put(city,0);
                }
            }else{
                int number=cityByTime.get(city);
                if(isActive(orders)){
                    cityByTime.put(city,number+1);
                }
            }
        }

        return cityByTime;
    }

    //判断是否为活跃期限内的订单,即为近一个季度的订单
    private boolean isActive(Orders orders){
        Calendar c=Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        if(Math.abs(month-getMonthByOrder(orders))<=3){
            return true;
        }
        return false;
    }

    //获得最终的比例指标
    private Map<String, Double[]> getPropotion(Map<String, Double[]> quota) {
        for (String city : quota.keySet()) {
            Double[] numAndTotal = quota.get(city);
            if (numAndTotal[0] != 0) {
                numAndTotal[0] = numAndTotal[1] / numAndTotal[0];
            } else {
                numAndTotal[0] = 0.0;
            }
            quota.put(city, numAndTotal);
        }
        return quota;
    }

    //获得加盟时间,以月为单位
    private int getTime(String date) {
        Calendar calendar=Calendar.getInstance();
        Calendar dateC=Str2Calendar.str2Calendar(date);
        int month=Math.abs(calendar.get(Calendar.MONTH)-dateC.get(Calendar.MONTH));
        return month;
    }

    //获得订单所在月份
    private int getMonthByOrder(Orders orders){
        Calendar c = Str2Calendar.str2Calendar(orders.getCheckinDate());
        if (c==null){
            return -1;
        }
        int month = c.get(Calendar.MONTH)+1;
        return month;
    }

    //获得订单所属季度
    private int getSeasonByOrder(Orders orders){
       int month=getMonthByOrder(orders);
       switch (month){
           case 1:return 1;
           case 2:return 1;
           case 3:return 1;
           case 4:return 2;
           case 5:return 2;
           case 6:return 2;
           case 7:return 3;
           case 8:return 3;
           case 9:return 3;
           case 10:return 4;
           case 11:return 4;
           case 12:return 4;
           default:return 1;
       }
    }
}
