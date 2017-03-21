package action;

import model.Hostel;
import model.Manager;
import model.Orders;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.*;
import util.OrderConditionEnum;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Seven on 2017/3/2.
 */
@Controller
public class WorldStaInfoAction extends BaseAction {

    @Autowired
    ManagerService managerService;
    @Autowired
    HostelService hostelService;
    @Autowired
    VipService vipService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    CheckInfoService checkInfoService;

    public String worldSta(){
        //world累计收益 客栈累计收益 会员总数
        //所有客栈的入住信息列表 checkInfosList 柱状图
        //所有会员的订单列表

        //HostelWorld
        String managerNum=String.valueOf(request.getSession().getAttribute("id"));
        Manager manager=managerService.queryByNum(managerNum);
        double totalProfit=manager.getProfit();

        //Hostels
        List<Hostel> hostels=hostelService.queryAll();
        double hostelProfit=0;
        HashMap<String,Integer> checkNums=new HashMap<>();
        for(Hostel hostel:hostels){
            hostelProfit=hostelProfit+hostel.getProfit();
            int checkNum=checkInfoService.getCheckInfoByHostel(hostel.getHostelNum()).size();
            checkNums.put(hostel.getHostelNum(),checkNum);
        }

        //Vip
        List<Vip> vips=vipService.getAllVipList();
        int vipCount=vips.size();

        //Orders
        List<Orders> ordersList=ordersService.queryByState(OrderConditionEnum.BOOK.toString());
        ordersList.addAll(ordersService.queryByState(OrderConditionEnum.CHECKIN.toString()));
        ordersList.addAll(ordersService.queryByState(OrderConditionEnum.CHECKOUT.toString()));
        ordersList.addAll(ordersService.queryByState(OrderConditionEnum.SETTLE.toString()));
        ordersList.addAll(ordersService.queryByState(OrderConditionEnum.VALID.toString()));

        request.setAttribute("totalProfit",totalProfit);
        request.setAttribute("hostelProfit",hostelProfit);
        request.setAttribute("vipCount",vipCount);
        request.setAttribute("checkNums",checkNums);
        request.setAttribute("ordersList",ordersList);
        return "worldSta";
    }
}
