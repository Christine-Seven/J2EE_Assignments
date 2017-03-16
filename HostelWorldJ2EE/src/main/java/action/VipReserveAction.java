package action;

import model.CurrentSpareRoomInfo;
import model.Hostel;
import model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.CurrentSpareRoomService;
import service.HostelService;
import service.OrdersService;
import service.RoomTypeService;
import util.OrderConditionEnum;
import util.PayMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 2017/3/2.
 */
@Controller
public class VipReserveAction extends BaseAction {

    @Autowired
    HostelService hostelService;
    @Autowired
    CurrentSpareRoomService currentSpareRoomService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    OrdersService ordersService;

    public String hostelSearch() throws Exception {
        String checkinDate = request.getParameter("checkinDate");
        String checkoutDate = request.getParameter("checkoutDate");
        String city = request.getParameter("city");
        List<Hostel> hostelList;
        if (city != null) {
            hostelList = hostelService.queryHostelByCity(city);
        } else {
            hostelList = hostelService.queryAll();
        }
        if (hostelList != null) {
            request.setAttribute("hostels", hostelList);
            request.setAttribute("city", city);
            request.setAttribute("checkinDate", checkinDate);
            request.setAttribute("checkoutDate", checkoutDate);
            System.out.println(hostelList.size());
            return "hostels";
        } else {
            return "notFound";
        }

    }

    public String roomSearch() throws Exception {
        String hostelNum = request.getParameter("hostelNum");
        List<CurrentSpareRoomInfo> currentSpareRoomInfos = currentSpareRoomService.getCurrentSpareRoom(hostelNum);
        if (currentSpareRoomInfos != null) {
            Map<String, Integer> roomMap = new HashMap<>();
            for (CurrentSpareRoomInfo currentSpareRoomInfo : currentSpareRoomInfos) {
                int typeId = currentSpareRoomInfo.getRoomTypeId();

                String type = roomTypeService.find(typeId).getRoomType();
                System.out.println(typeId+"  "+type);
                if (!roomMap.containsKey(type)) {
                    roomMap.put(type, currentSpareRoomInfo.getSpareNum());
                }
            }
            request.setAttribute("rooms", roomMap);
            Hostel hostel = hostelService.queryHostelByNum(hostelNum);
            request.setAttribute("hostelName", hostel.getHostelName());
            request.setAttribute("hostelInfo", hostel.getHostelInfo());
            request.setAttribute("hostelNum", hostelNum);
            return "rooms";
        } else {
            return "notFound";
        }
    }

    public String hostelReserve() throws Exception{
        String hostelNum=request.getParameter("hostelNum");
        String vipNum=request.getParameter("vipNum");
        String roomNum=request.getParameter("roomNum");
        String roomType=request.getParameter("roomType");
        String requiredMoney=request.getParameter("requiredMoney");
        String checkinDate=request.getParameter("checkinDate");
        String checkoutDate=request.getParameter("checkoutDate");
        String payMethod=request.getParameter("payMethod");
        Orders orders=new Orders();
        String orderNum=ordersService.getOrderNum(hostelNum);
        orders.setOrderNum(orderNum);
        orders.setRoomTypeId(roomTypeService.queryByType(roomType).getId());
        orders.setHostelNum(hostelNum);
        orders.setVipNum(vipNum);
        orders.setRoomNum(Integer.valueOf(roomNum));
        orders.setRequiredMoney(Double.valueOf(requiredMoney));
        orders.setOrderCondition(OrderConditionEnum.BOOK.toString());
        orders.setCheckinDate(checkinDate);
        orders.setCheckoutDate(checkoutDate);
        orders.setPayMethod(payMethod);
        ordersService.saveOrders(orders);

        if(payMethod.equals(PayMethod.CARD.toString())){
            if(ordersService.pay(orderNum,Double.valueOf(requiredMoney))){
                return "orders";
            }else{
                return "notEnoughMoney";
            }
        }
        return "orders";
    }
}
