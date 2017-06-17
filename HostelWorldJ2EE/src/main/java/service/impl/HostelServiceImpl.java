package service.impl;

import dao.HostelDao;
import model.Hostel;
import model.Orders;
import model.RoomPlan;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HostelService;
import service.OrdersService;
import service.RoomPlanService;
import service.VipService;
import util.PriceRangeEnum;
import util.Str2Calendar;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class HostelServiceImpl implements HostelService {
    @Autowired
    private HostelDao hostelDao;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private RoomPlanService roomPlanService;
    @Autowired
    private VipService vipService;

    @Override
    public boolean checkHostel(String hostelNum) {
        return hostelDao.checkHostel(hostelNum);
    }

    @Override
    public boolean checkApprove(String hostelNum) {
        return hostelDao.checkApprove(hostelNum);
    }

    @Override
    public boolean checkPassword(String hostelNum, String hostelPassword) {
        return hostelDao.checkPassword(hostelNum, hostelPassword);
    }

    @Override
    public void registerHostel(Hostel hostel) {
        hostelDao.save(hostel);
    }

    @Override
    public String getHostelNum() {
        return hostelDao.getHostelNum();
    }

    @Override
    public void updateHostel(Hostel hostel) {
        hostelDao.updateHostel(hostel);
    }

    @Override
    public void deleteHostel(String hostelNum) {
        hostelDao.deleteHostel(hostelNum);
    }

    @Override
    public List<Hostel> queryHostelByProvince(String province) {
        return hostelDao.queryHostelByProvince(province);
    }

    @Override
    public List<Hostel> queryHostelByCity(String city) {
        return hostelDao.queryHostelByCity(city);
    }

    @Override
    public List<Hostel> queryHostelByName(String hostelName) {
        return hostelDao.queryHostelByName(hostelName);
    }

    @Override
    public Hostel queryHostelByNum(String hostelNum) {
        return hostelDao.queryHostelByNum(hostelNum);
    }

    @Override
    public List<Hostel> queryHostelByLevel(int level) {
        return hostelDao.queryHostelByLevel(level);
    }

    @Override
    public List<Hostel> queryAll() {
        return hostelDao.queryAll();
    }

    @Override
    public List<Hostel> queryByApprove(String approveState) {
        return hostelDao.queryByApprove(approveState);
    }

    @Override
    public Map<Integer, Double[]> getAdrByHostel(String hostelNum) {
        Map<Integer, Double[]> adrByMonth = new HashMap<>();
        List<Orders> ordersList = ordersService.queryByHostel(hostelNum);

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
    public Map<Integer, Double[]> getOccByHostel(String hostelNum) {
        List<Orders> ordersList = ordersService.queryByHostel(hostelNum);
        Map<Integer, Double[]> occByMonth = new HashMap<>();

        for (Orders orders : ordersList) {
            Calendar c = Str2Calendar.str2Calendar(orders.getCheckinDate());
            int month = c.get(Calendar.MONTH);
            if (!occByMonth.containsKey(month)) {
                //循环遍历这个月的每一天
                //访问其当天的房价计划，获得可住房间数目总和
                //而后统计该月订单数目，将二者相除
                Double[] numAndTotal = new Double[2];
                List<RoomPlan> roomPlans = roomPlanService.queryNewestRoomPlan(hostelNum);
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
    public Map<Integer, Double[]> getRevparByHostel(String hostelNum) {
        List<Orders> ordersList = ordersService.queryByHostel(hostelNum);
        Map<Integer, Double[]> revparByMonth = new HashMap<>();
        for (Orders orders : ordersList) {
            Calendar c = Str2Calendar.str2Calendar(orders.getCheckinDate());
            int month = c.get(Calendar.MONTH);
            double price = orders.getPaidMoney();
            if (!revparByMonth.containsKey(month)) {
                List<RoomPlan> roomPlans = roomPlanService.queryNewestRoomPlan(hostelNum);
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

    @Override
    public Map<Integer, Integer> getLevelByHostel(String hostelNum) {
        List<Orders> ordersList = ordersService.queryByHostel(hostelNum);
        Map<Integer,Integer> vipByLevel=new HashMap<>();
        for (Orders orders:ordersList){
            Vip vip=vipService.findVipById(orders.getVipNum());
            int level=vip.getVipLevel();
            if(!vipByLevel.containsKey(level)){
                vipByLevel.put(level,1);
            }else{
                int num=vipByLevel.get(level);
                num=num+1;
                vipByLevel.put(level,num);
            }
        }
        return vipByLevel;
    }

    @Override
    public Map<Integer, Map<String, Integer>> getPriceByMonth(String hostelNum, List<Integer> months) {
        List<Orders> ordersList = ordersService.queryByHostel(hostelNum);
        Map<Integer, Map<String, Integer>> priceByMonth = new HashMap<>();
        for (Orders orders : ordersList) {
            int month = this.getMonthByOrder(orders);
            if (month != -1) {
                if (months.contains(month)) {
                    double price = orders.getPaidMoney();
                    PriceRangeEnum range = this.getPriceRange(price);
                    if (!priceByMonth.containsKey(month)) {
                        Map<String, Integer> priceByRange = new HashMap<>();
                        priceByRange.put(range.toString(), 1);
                        priceByMonth.put(month, priceByRange);
                    } else {
                        Map<String, Integer> priceByRange = priceByMonth.get(month);
                        if (!priceByRange.containsKey(range.toString())) {
                            priceByRange.put(range.toString(), 1);
                        } else {
                            int num = priceByRange.get(range.toString());
                            num = num + 1;
                            priceByRange.put(range.toString(), num);
                        }
                        priceByMonth.put(month, priceByRange);
                    }
                }
            }
        }
        return priceByMonth;
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
        return priceRangeEnums[i+1];
    }

    //获得订单所在月份
    private int getMonthByOrder(Orders orders){
        Calendar c = Str2Calendar.str2Calendar(orders.getCheckinDate());
        if (c==null){
            return -1;
        }
        int month = c.get(Calendar.MONTH);
        return month;
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
