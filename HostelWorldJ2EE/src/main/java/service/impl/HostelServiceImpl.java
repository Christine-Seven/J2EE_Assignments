package service.impl;

import dao.HostelDao;
import model.Hostel;
import model.Orders;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.*;
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
    @Autowired
    private WorldStaService worldStaService;

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
        List<Orders> ordersList = ordersService.queryByHostel(hostelNum);
        return ordersService.getAdrByMonth(ordersList);
    }

    @Override
    public Map<Integer, Double[]> getOccByHostel(String hostelNum) {
        List<Orders> ordersList = ordersService.queryByHostel(hostelNum);
        return ordersService.getOccByMonth(ordersList);
    }

    @Override
    public Map<Integer, Double[]> getRevparByHostel(String hostelNum) {
        List<Orders> ordersList = ordersService.queryByHostel(hostelNum);
        return ordersService.getRevparByMonth(ordersList);
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

    @Override
    public Map<Integer, Double[]> getIndexByMonth(String hostelNum) {
        Map<Integer,Double[]> indexByMonth=new HashMap<>();
        Map<Integer,Double[]> worldAdrs=worldStaService.getAdrByMonth();
        Map<Integer,Double[]> worldOccs=worldStaService.getOccByMonth();
        Map<Integer,Double[]> worldRevpars=worldStaService.getRevparByMonth();

        for(int month:worldAdrs.keySet()){
            double adr=(this.getAdrByHostel(hostelNum).get(month)[0]/worldAdrs.get(month)[0]);
            double occ=(this.getOccByHostel(hostelNum).get(month)[0]/worldOccs.get(month)[0]);
            double revpar=(this.getRevparByHostel(hostelNum).get(month)[0]/worldRevpars.get(month)[0]);
            Double[] indexes={adr,occ,revpar};
            indexByMonth.put(month,indexes);
        }

        return indexByMonth;
    }

    //获得价格所属区间
    private PriceRangeEnum getPriceRange(double price){
        int[] ranges={100,200,300,500};
        PriceRangeEnum[] priceRangeEnums={PriceRangeEnum.少于一百,PriceRangeEnum.一百至两百,PriceRangeEnum.两百至三百,PriceRangeEnum.三百至五百,PriceRangeEnum.多于五百};
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
