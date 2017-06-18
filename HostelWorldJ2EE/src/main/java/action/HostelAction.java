package action;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.*;
import util.CheckConditionEnum;
import util.OrderConditionEnum;
import util.RoomPlanVO;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Seven on 2017/3/2.
 */
@Controller
public class HostelAction extends BaseAction {
    @Autowired
    HostelService hostelService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    RoomPlanService roomPlanService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    CheckInfoService checkInfoService;
    @Autowired
    VipService vipService;
    @Autowired
    CurrentSpareRoomService currentSpareRoomService;

    public String getPlan() {
        String hostelNum = String.valueOf(request.getSession().getAttribute("id"));
        List<RoomPlan> roomPlans = roomPlanService.queryByHostel(hostelNum);
        request.setAttribute("roomPlan", this.getPlanVO(roomPlans));
        return "plan";
    }

    public String hostelPlan() {
        String hostelNum = String.valueOf(request.getSession().getAttribute("id"));
        String roomType = request.getParameter("roomType");
        double roomPrice = Double.valueOf(request.getParameter("roomPrice"));
        int roomNum = Integer.valueOf(request.getParameter("roomNum"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        RoomPlan roomPlan = new RoomPlan();
        roomPlan.setRoomTypeId(roomTypeService.queryByType(roomType).getId());
        roomPlan.setHostelNum(hostelNum);
        roomPlan.setRoomNum(roomNum);
        roomPlan.setRoomPrice(roomPrice);
        roomPlan.setStartDate(startDate);
        roomPlan.setEndDate(endDate);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        roomPlan.setDate(sdf.format(date));
        roomPlanService.saveRoomPlan(roomPlan);

        CurrentSpareRoomInfo currentSpareRoomInfo=new CurrentSpareRoomInfo();
        currentSpareRoomInfo.setHostelNum(hostelNum);
        currentSpareRoomInfo.setRoomTypeId(roomTypeService.queryByType(roomType).getId());
        currentSpareRoomInfo.setSpareNum(roomNum);
        currentSpareRoomService.save(currentSpareRoomInfo);

        List<RoomPlan> roomPlans = roomPlanService.queryByHostel(hostelNum);
        request.setAttribute("roomPlan", this.getPlanVO(roomPlans));
        return "hostelPlan";
    }

    public String getCheckInfo() {
        String hostelNum = String.valueOf(request.getSession().getAttribute("id"));
        List<CheckInfo> checkInfos = checkInfoService.getCheckInfoByHostel(hostelNum);
        List<CheckInfo> checkInfoList = new ArrayList<>();
        for (CheckInfo checkInfo : checkInfos) {
            if (checkInfo.getCheckCondition().equals(CheckConditionEnum.CHECKIN.toString())) {
                checkInfoList.add(checkInfo);
            }
        }

        request.setAttribute("check", checkInfoList);
        request.setAttribute("type", "all");
        return "check";
    }

    public String vipCheckin() {
        String hostelNum = String.valueOf(request.getSession().getAttribute("id"));
        String lodgerName = request.getParameter("lodgerName");
        String checkinDate = request.getParameter("checkinDate");
        String checkoutDate = request.getParameter("checkoutDate");
        double paidMoney = Double.valueOf(request.getParameter("paidMoney"));
        String roomNum = request.getParameter("roomNumber");
        int roomTypeId = Integer.valueOf(request.getParameter("roomTypeId"));
        String orderNum = request.getParameter("orderNum");

        CheckInfo checkInfo = new CheckInfo();
        checkInfo.setHostelNum(hostelNum);
        checkInfo.setLodgerName(lodgerName);
        checkInfo.setPaidMoney(paidMoney);
        checkInfo.setCheckinDate(checkinDate);
        checkInfo.setCheckoutDate(checkoutDate);
        checkInfo.setCheckCondition(CheckConditionEnum.CHECKIN.toString());
        checkInfo.setRoomNum(roomNum);
        checkInfo.setRoomTypeId(roomTypeId);
        checkInfo.setOrderNum(orderNum);
        checkInfoService.save(checkInfo);

        Orders orders = ordersService.find(orderNum);
        orders.setOrderCondition(OrderConditionEnum.CHECKIN.toString());
        ordersService.updateOrders(orders);

        List<CheckInfo> checkInfos = checkInfoService.getCheckInfoByHostel(hostelNum);
        List<CheckInfo> checkInfoList = new ArrayList<>();
        for (CheckInfo info : checkInfos) {
            if (info.getCheckCondition().equals(CheckConditionEnum.CHECKIN.toString())) {
                checkInfoList.add(info);
            }
        }

        request.setAttribute("check", checkInfos);
        request.setAttribute("type", "all");
        return "check";
    }

    public String getVipOrder() {
        String hostelNum = String.valueOf(request.getSession().getAttribute("id"));
        String vipNum = request.getParameter("vipNum");
        List<Orders> ordersList = ordersService.queryByVip(vipNum);
        List<Orders> orderses = new ArrayList<>();
        for (Orders orders : ordersList) {
            if (orders.getHostelNum().equals(hostelNum) && (orders.getOrderCondition().equals(OrderConditionEnum.VALID.toString()) || orders.getOrderCondition().equals(OrderConditionEnum.BOOK.toString()))) {
                orderses.add(orders);
            }
        }
        request.setAttribute("vipOrder", orderses);
        request.setAttribute("type", "vip");
        return "vipOrder";
    }

    public String checkInfo() {
        String hostelNum = String.valueOf(request.getSession().getAttribute("id"));
        String roomType = request.getParameter("roomType");
        String roomNum = request.getParameter("roomNum");
        String lodgerName = request.getParameter("lodgerName");
        String checkinDate = request.getParameter("checkinDate");
        String chekoutDate = request.getParameter("checkoutDate");
        double paidMoney = Double.valueOf(request.getParameter("paidMoney"));
        CheckInfo checkInfo = new CheckInfo();
        checkInfo.setHostelNum(hostelNum);
        checkInfo.setRoomTypeId(roomTypeService.queryByType(roomType).getId());
        checkInfo.setLodgerName(lodgerName);
        checkInfo.setPaidMoney(paidMoney);
        checkInfo.setCheckCondition(CheckConditionEnum.CHECKIN.toString());
        checkInfo.setCheckinDate(checkinDate);
        checkInfo.setCheckoutDate(chekoutDate);
        checkInfo.setRoomNum(roomNum);
        checkInfoService.save(checkInfo);

        List<CheckInfo> checkInfos = checkInfoService.getCheckInfoByHostel(hostelNum);
        List<CheckInfo> checkInfoList = new ArrayList<>();
        for (CheckInfo info : checkInfos) {
            if (info.getCheckCondition().equals(CheckConditionEnum.CHECKIN.toString())) {
                checkInfoList.add(info);
            }
        }
        request.setAttribute("check", checkInfoList);
        request.setAttribute("type", "all");
        return "checkInfo";
    }

    public String checkout() {
        String hostelNum = String.valueOf(request.getSession().getAttribute("id"));
        int checkNum = Integer.valueOf(request.getParameter("checkNum"));

        CheckInfo checkInfo = checkInfoService.find(checkNum);
        checkInfo.setCheckCondition(CheckConditionEnum.CHECKOUT.toString());
        checkInfoService.update(checkInfo);

        //判断是否为会员
        if (checkInfo.getOrderNum() != null) {
            Orders orders = ordersService.find(checkInfo.getOrderNum());
            orders.setOrderCondition(OrderConditionEnum.CHECKOUT.toString());
            ordersService.updateOrders(orders);
        }

        List<CheckInfo> checkInfos = checkInfoService.getCheckInfoByHostel(hostelNum);
        List<CheckInfo> checkInfoList = new ArrayList<>();
        for (CheckInfo info : checkInfos) {
            if (info.getCheckCondition().equals(CheckConditionEnum.CHECKIN.toString())) {
                checkInfoList.add(info);
            }
        }
        request.setAttribute("check", checkInfoList);
        request.setAttribute("type", "all");
        return "out";
    }

    private List<RoomPlanVO> getPlanVO(List<RoomPlan> roomPlans) {
        List<RoomPlanVO> roomPlanVOs = new ArrayList<>();
        for (RoomPlan roomPlan : roomPlans) {
            RoomPlanVO roomPlanVO = new RoomPlanVO();
            roomPlanVO.setHostelNum(roomPlan.getHostelNum());
            roomPlanVO.setDate(roomPlan.getDate());
            roomPlanVO.setStartDate(roomPlan.getStartDate());
            roomPlanVO.setEndDate(roomPlan.getEndDate());
            roomPlanVO.setRoomNum(roomPlan.getRoomNum());
            roomPlanVO.setRoomType(roomTypeService.find(roomPlan.getRoomTypeId()).getRoomType());
            roomPlanVOs.add(roomPlanVO);
        }
        return roomPlanVOs;
    }

    //客栈统计信息
    public String hostelSta() {
        String hostelNum=String.valueOf(request.getSession().getAttribute("id"));
        Map<Integer, Double[]> adrByMonth = hostelService.getAdrByHostel(hostelNum);
        Map<Integer, Double[]> occByMonth = hostelService.getOccByHostel(hostelNum);
        Map<Integer, Double[]> revparByMonth = hostelService.getRevparByHostel(hostelNum);

        //每月市场细分指数
        //TODO
        Map<Integer,List<Double>> indexBySeason=new HashMap<>();

        //会员等级分布
        Map<Integer,Integer> vipByLevel=hostelService.getLevelByHostel(hostelNum);
        //每月消费额区间分布
        //TODO
        //月份信息从界面传入，这里为了测试方便
        List<Integer> months=new ArrayList<Integer>();
        months.add(1);
        months.add(2);
        months.add(3);
        months.add(4);
        months.add(5);
        months.add(6);

        Map<Integer,Map<String,Integer>> priceByMonth = hostelService.getPriceByMonth(hostelNum,months);

        //会员等级与消费额的关系
        Map<Integer,List<Double>> vipByPrice=new HashMap<>();

        List<Orders> ordersList = ordersService.queryByHostel(hostelNum);
        for (Orders orders:ordersList){
            Vip vip=vipService.findVipById(orders.getVipNum());
            double price = orders.getPaidMoney();
            int level=vip.getVipLevel();
            if(!vipByLevel.containsKey(level)){
                vipByLevel.put(level,1);

                List<Double> prices=new ArrayList<>();
                prices.add(price);
                vipByPrice.put(level,prices);
            }else {
                int num=vipByLevel.get(level);
                num=num+1;
                vipByLevel.put(level,num);

                List<Double> prices=vipByPrice.get(level);
                prices.add(price);
                vipByPrice.put(level,prices);
            }
        }

        request.setAttribute("adrByMonth",adrByMonth);
        request.setAttribute("occByMonth",occByMonth);
        request.setAttribute("revparByMonth",revparByMonth);
        request.setAttribute("vipByLevel",vipByLevel);
        request.setAttribute("vipByPrice",vipByPrice);
        request.setAttribute("priceByMonth",priceByMonth);
        return "hostelSta";
    }




}
