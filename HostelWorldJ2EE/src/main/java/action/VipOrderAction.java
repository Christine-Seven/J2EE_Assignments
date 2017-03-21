package action;

import model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.HostelService;
import service.OrdersService;
import util.OrderVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seven on 16/03/2017.
 */

@Controller
public class VipOrderAction extends BaseAction{
    @Autowired
    OrdersService ordersService;
    @Autowired
    HostelService hostelService;

    public String getVipOrders(){
        String vipNum=String.valueOf(request.getSession().getAttribute("id"));
        List<Orders> ordersList=ordersService.queryByVip(vipNum);
        List<OrderVO> orderVOs=this.getOrderVOs(ordersList);
        request.setAttribute("orders",orderVOs);
        return "orders";
    }

    public String cancelOrder(){
        String orderNum=request.getParameter("orderNum");
        if(ordersService.cancel(orderNum)){
            String vipNum=String.valueOf(request.getSession().getAttribute("id"));
            List<Orders> ordersList=ordersService.queryByVip(vipNum);
            List<OrderVO> orderVOs=this.getOrderVOs(ordersList);
            request.setAttribute("orders",orderVOs);
            return "cancel";
        }
        return "fail";
    }

    private List<OrderVO> getOrderVOs(List<Orders> ordersList){
        List<OrderVO> orderVOs=new ArrayList<>();
        for(Orders orders:ordersList){
            OrderVO orderVO=new OrderVO();
            String hostelName=hostelService.queryHostelByNum(orders.getHostelNum()).getHostelName();
            orderVO.setOrderNum(orders.getOrderNum());
            orderVO.setHostelName(hostelName);
            orderVO.setCheckinDate(orders.getCheckinDate());
            orderVO.setCheckoutDate(orders.getCheckoutDate());
            orderVO.setPayMethod(orders.getPayMethod());
            orderVO.setOrderCondition(orders.getOrderCondition());
            orderVO.setRequiredMoney(orders.getRequiredMoney());
            System.out.println("requiredMoney"+orders.getRequiredMoney());
            orderVOs.add(orderVO);
        }
        return orderVOs;
    }
}
