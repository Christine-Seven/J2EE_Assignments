package service.impl;

import dao.BankCardDao;
import dao.VipDao;
import model.BankCard;
import model.Hostel;
import model.Orders;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HostelService;
import service.OrdersService;
import service.VipService;
import util.PriceRangeEnum;
import util.Str2Calendar;
import util.VipStateEnum;

import java.util.*;

/**
 * Created by Seven on 2017/2/15.
 */

@Service
public class VipServiceImpl implements VipService {

    @Autowired
    private VipDao vipDao;
    @Autowired
    private BankCardDao bankCardDao;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private HostelService hostelService;

    @Override
    public boolean isExist(String vipNum) {
        return vipDao.checkVip(vipNum);
    }

    @Override
    public boolean checkPassword(String vipNum, String password) {
        return vipDao.checkPassword(vipNum, password);
    }

    @Override
    public void registerVip(Vip vip) {
        vipDao.save(vip);
        String bankCardId = vip.getBankCardId();

        BankCard bankCard = new BankCard();
        bankCard.setBankCardId(bankCardId);
        bankCard.setBalance(10000);
        bankCardDao.save(bankCard);
    }

    @Override
    public String getVipNum() {
        return vipDao.getVipNum();
    }

    @Override
    public boolean cancelVip(String vipNum) {
        Vip vip = vipDao.find(vipNum);
        vip.setState(VipStateEnum.CANCEL.toString());
        vipDao.update(vip);
        return true;
    }

    @Override
    public void updateVip(Vip vip) {
        String bankCardId = vip.getBankCardId();
        if (bankCardDao.find(bankCardId) == null) {
            BankCard bankCard = new BankCard();
            bankCard.setBankCardId(bankCardId);
            bankCard.setBalance(10000);
            bankCardDao.save(bankCard);
        }
        vipDao.update(vip);
    }

    @Override
    public Vip findVipById(String vipNum) {
        return vipDao.find(vipNum);
    }

    @Override
    public List<Vip> queryByName(String vipName) {
        return vipDao.queryByName(vipName);
    }

    @Override
    public List<Vip> getAllVipList() {
        return vipDao.getAllVipList();
    }

    @Override
    public Map<Integer, Double> getPriceByMonth(String vipNum) {
        List<Orders> ordersList = ordersService.queryByVip(vipNum);
        // 每月订单总额
        Map<Integer, Double> priceByMonth = new HashMap<>();

        for (Orders orders : ordersList) {
            //获得订单所在月份
            int month = getMonthByOrder(orders);
            System.out.println("month=" + month);

            if (!priceByMonth.containsKey(month)) {
                priceByMonth.put(month, orders.getPaidMoney());
            } else {
                double money = priceByMonth.get(month);
                priceByMonth.put(month, money + orders.getPaidMoney());
            }

        }
        return priceByMonth;
    }

    @Override
    public Map<Integer, Integer> getTimeByMonth(String vipNum) {
        List<Orders> ordersList = ordersService.queryByVip(vipNum);
        // 每月出行次数
        Map<Integer, Integer> timeByMonth = new HashMap<>();

        for (Orders orders : ordersList) {
            //获得订单所在月份
            int month = getMonthByOrder(orders);

            if (!timeByMonth.containsKey(month)) {
                timeByMonth.put(month, 1);
            } else {
                int time = timeByMonth.get(month);
                timeByMonth.put(month, time++);
            }
        }
        return timeByMonth;
    }

    @Override
    public Map<String, Integer> getTimeByCity(String vipNum) {
        List<Orders> ordersList = ordersService.queryByVip(vipNum);
        // 按城市分布的出行次数
        Map<String, Integer> timeByCity = new HashMap<>();

        for (Orders orders : ordersList) {
            Hostel hostel = hostelService.queryHostelByNum(orders.getHostelNum());
            String city = hostel.getCity();
            if (!timeByCity.containsKey(city)) {
                timeByCity.put(city, 1);
            } else {
                int time = timeByCity.get(city);
                time = time+1;
                timeByCity.put(city, time);
            }
        }
        return timeByCity;
    }

    @Override
    public Map<String, Map<String, Integer>> getPriceByCity(String vipNum) {
        List<Orders> ordersList = ordersService.queryByVip(vipNum);
        // 按城市查看价格分布
        Map<String, Map<String,Integer>> priceByCity = new HashMap<>();

        for(Orders orders:ordersList){
            Hostel hostel = hostelService.queryHostelByNum(orders.getHostelNum());
            String city = hostel.getCity();
            if (!priceByCity.containsKey(city)) {
                Map<String,Integer> priceByRange=new HashMap<>();
                PriceRangeEnum range=getPriceRange(orders.getPaidMoney());
                priceByRange.put(range.toString(),1);
                priceByCity.put(city, priceByRange);
            } else {
                Map<String,Integer> priceByRange=priceByCity.get(city);
                String range=getPriceRange(orders.getPaidMoney()).toString();
                if(!priceByRange.containsKey(range)){
                    priceByRange.put(range,1);

                }else{
                    int num=priceByRange.get(range);
                    priceByRange.put(range,num++);
                }
                priceByCity.put(city, priceByRange);
            }
        }
        return priceByCity;
    }

    //获得价格所属区间
    private PriceRangeEnum getPriceRange(double price){
        int[] ranges={100,200,300,500};
        PriceRangeEnum[] priceRangeEnums={PriceRangeEnum.LT100,PriceRangeEnum.LT200,PriceRangeEnum.LT300,PriceRangeEnum.LT500,PriceRangeEnum.MT500};
        int i=0;
        for(;i<ranges.length;i++){
            if(price<ranges[i]) {
                return priceRangeEnums[i];
            }
        }
        return priceRangeEnums[i];
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
}
