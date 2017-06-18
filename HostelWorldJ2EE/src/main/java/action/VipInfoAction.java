package action;

import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.HostelService;
import service.OrdersService;
import service.VipBalanceService;
import service.VipService;

import java.util.Map;

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

        // 每月订单总额
        Map<String, Double> priceByMonth =vipService.getPriceByMonth(vipNum);
        // 每月出行次数
        Map<String, Integer> timeByMonth = vipService.getTimeByMonth(vipNum);
        // 按城市分布的出行次数
        Map<String, Integer> timeByCity = vipService.getTimeByCity(vipNum);
        // 按城市查看价格分布
        Map<String, Map<String,Integer>> priceByCity = vipService.getPriceByCity(vipNum);

        for(String month:priceByMonth.keySet()){
            System.out.println("month is "+month);
        }

        request.setAttribute("priceByMonth", priceByMonth);
        request.setAttribute("timeByMonth", timeByMonth);
        request.setAttribute("timeByCity", timeByCity);
        request.setAttribute("priceByCity", priceByCity);
        return "vipSta";
    }

}
