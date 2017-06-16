package action;

import model.Hostel;
import model.Orders;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.HostelService;
import service.OrdersService;
import service.VipBalanceService;
import service.VipService;
import util.Str2Calendar;

import java.util.*;

/**
 * Created by Seven on 2017/3/2.
 */
@Controller
public class VipInfoAction extends BaseAction {

    @Autowired
    VipService vipService;
    @Autowired
    VipBalanceService vipBalanceService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    HostelService hostelService;

    public String jump2vipInfo() {
        String vipNum = String.valueOf(request.getSession().getAttribute("id"));
        Vip vip = vipService.findVipById(vipNum);
        request.setAttribute("vip", vip);
        return SUCCESS;
    }

    public String activateVip() {
        String vipNum = String.valueOf(request.getSession().getAttribute("id"));
        double money = Double.valueOf(request.getParameter("money"));
        if (vipBalanceService.activateVip(vipNum, money)) {
            Vip vip = vipService.findVipById(vipNum);
            request.setAttribute("vip", vip);
            return "activate";
        } else {
            return "fail";
        }
    }

    public String cancelVip() {
        String vipNum = String.valueOf(request.getSession().getAttribute("id"));
        if (vipService.cancelVip(vipNum)) {
            Vip vip = vipService.findVipById(vipNum);
            request.setAttribute("vip", vip);
            return "cancel";
        } else {
            return "fail";
        }
    }

    public String convertCredit() {
        String vipNum = String.valueOf(request.getSession().getAttribute("id"));
        double point = Double.valueOf(request.getParameter("point"));
        if (vipBalanceService.convertCreditToMoney(vipNum, point) > 0) {
            Vip vip = vipService.findVipById(vipNum);
            request.setAttribute("vip", vip);
            return "convert";
        }
        return "fail";
    }

    public String topUp() {
        String vipNum = String.valueOf(request.getSession().getAttribute("id"));
        double money = Double.valueOf(request.getParameter("money"));
        if (vipBalanceService.topUp(vipNum, money)) {
            Vip vip = vipService.findVipById(vipNum);
            request.setAttribute("vip", vip);
            return "topup";
        }
        return "fail";
    }

    public String modifyVip() {
        String vipName = request.getParameter("vipName");
        String bankCardId = request.getParameter("bankCardId");
        String vipNum = String.valueOf(request.getSession().getAttribute("id"));
        Vip vip = vipService.findVipById(vipNum);
        vip.setVipName(vipName);
        vip.setBankCardId(bankCardId);
        vipService.updateVip(vip);
        request.setAttribute("vip", vip);

        return "modify";
    }

    //会员统计信息
    public String vipSta() {
        String vipNum = String.valueOf(request.getSession().getAttribute("id"));
        List<Orders> ordersList = ordersService.queryByVip(vipNum);

        //统计量如下，均为近一年的数据
        // 每月订单总额
        Map<Integer, Double> priceByMonth = new HashMap<>();
        // 每月出行次数
        Map<Integer, Integer> timeByMonth = new HashMap<>();
        // 按城市分布的出行次数
        Map<String, Integer> timeByCity = new HashMap<>();
        // 按城市查看价格分布
        Map<String, List<Double>> priceByCity = new HashMap<>();

        for (Orders orders : ordersList) {

            //获得订单所在月份
            Calendar c = Str2Calendar.str2Calendar(orders.getCheckinDate());
            if (c==null){
                break;
            }
            int month = c.get(Calendar.MONTH);
            System.out.println("month=" + month);
            if (c.get(Calendar.YEAR) == 2017) {
                // 每月订单总额
                if (!priceByMonth.containsKey(month)) {
                    priceByMonth.put(month, orders.getPaidMoney());
                } else {
                    double money = priceByMonth.get(month);
                    priceByMonth.put(month, money + orders.getPaidMoney());
                }
                //每月出行次数
                if (!timeByMonth.containsKey(month)) {
                    timeByMonth.put(month, 1);
                } else {
                    int time = timeByMonth.get(month);
                    timeByMonth.put(month, time++);
                }
            }
            //按城市查看出行次数
            Hostel hostel = hostelService.queryHostelByNum(orders.getHostelNum());
            String city = hostel.getCity();
            if (!timeByCity.containsKey(city)) {
                timeByCity.put(city, 1);
            } else {
                int time = timeByCity.get(city);
                timeByCity.put(city, time++);
            }
            if (!priceByCity.containsKey(city)) {
                List<Double> prices = new ArrayList<>();
                prices.add(orders.getPaidMoney());
                priceByCity.put(city, prices);
            } else {
                List<Double> prices = priceByCity.get(city);
                prices.add(orders.getPaidMoney());
                priceByCity.put(city, prices);
            }
        }
        request.setAttribute("priceByMonth", priceByMonth);
        request.setAttribute("timeByMonth", timeByMonth);
        request.setAttribute("priceByCity", priceByCity);
        request.setAttribute("timeByCity", timeByCity);
        return "vipSta";
    }
}
