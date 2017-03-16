package action;

import model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.HostelService;
import service.OrdersService;

import java.util.HashMap;
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
        HashMap<Orders,String> ordersMap=new HashMap<>();
        for(Orders orders:ordersList){
            String hostelName=hostelService.queryHostelByNum(orders.getHostelNum()).getHostelName();
            ordersMap.put(orders,hostelName);
        }
//        request.setAttribute("reserve",reserveResult);
        request.setAttribute("orders",ordersMap);
        return "orders";
    }
}
