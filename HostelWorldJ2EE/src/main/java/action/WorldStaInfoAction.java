package action;

import model.Hostel;
import model.Manager;
import model.Orders;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.*;

import java.util.*;

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
    @Autowired
    WorldStaService worldStaService;

    public String getIndex(){
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
        Map<String,Integer> checkNums=new HashMap<>();
        for(Hostel hostel:hostels){
            hostelProfit=hostelProfit+hostel.getProfit();
            int checkNum=checkInfoService.getCheckInfoByHostel(hostel.getHostelNum()).size();
            checkNums.put(hostel.getHostelName(),checkNum);
        }
        checkNums=sortByValue(checkNums);
        //Vip
        List<Vip> vips=vipService.getAllVipList();
        int vipCount=vips.size();

        //Orders
        List<Orders> ordersList=ordersService.queryAll();

        request.setAttribute("totalProfit",totalProfit);
        request.setAttribute("hostelProfit",hostelProfit);
        request.setAttribute("vipCount",vipCount);
        request.setAttribute("ordersList",ordersList);

        //新增统计信息
        Map<Integer,Double[]> adrByMonth = worldStaService.getAdrByMonth();
        Map<String,Double[]> adrByCity=worldStaService.getAdrByCity();
        Map<Integer,Double[]> occByMonth=worldStaService.getOccByMonth();
        Map<String,Double[]> occByCity=worldStaService.getOccByCity();
        Map<Integer,Double[]> revparByMonth=worldStaService.getRevparByMonth();
        Map<String,Double[]> revparByCity=worldStaService.getRevparByCity();

        request.setAttribute("adrByMonth",adrByMonth);
        request.setAttribute("adrByCity",adrByCity);
        request.setAttribute("occByMonth",occByMonth);
        request.setAttribute("occByCity",occByCity);
        request.setAttribute("revparByMonth",revparByMonth);
        request.setAttribute("revparByCity",revparByCity);

        return "index";
    }

    public String getMoney(){

        //营业情况统计
        Map<Integer,Double> moneyByTime=worldStaService.getMoneyByTime();
        Map<String,Double> moneyByCity=worldStaService.getMoneyByCity();
        Map<Integer,Double> moneyByLevel=worldStaService.getMoneyByLevel();
        Map<Integer,Double> moneyByMonth=worldStaService.getMoneyByMonth();
        Map<Integer,Double> moneyBySeason=worldStaService.getMoneyBySeason();
        System.out.println(moneyBySeason.keySet().size());

        request.setAttribute("moneyByTime",moneyByTime);
        request.setAttribute("moneyByCity",moneyByCity);
        request.setAttribute("moneyByLevel",moneyByLevel);
        request.setAttribute("moneyByMonth",moneyByMonth);
        request.setAttribute("moneyBySeason",moneyBySeason);

        return "money";
    }

    public String getPeople(){

        //会员分布统计
        Map<Integer,Set<String>> activeByMonth=worldStaService.getActiveByMonth();
        Map<String,Set<String>> activeByCity=worldStaService.getActiveByCity();

        request.setAttribute("activeByMonth",activeByMonth);
        request.setAttribute("activeByCity",activeByCity);

        //热门城市
        Map<String,Integer> cityByTime=sortByValue(worldStaService.getCityByTime());
        request.setAttribute("cityByTime",cityByTime);

        //热门客栈
        //Hostels
        List<Hostel> hostels=hostelService.queryAll();
        Map<String,Integer> checkNums=new HashMap<>();
        for(Hostel hostel:hostels){
            int checkNum=checkInfoService.getCheckInfoByHostel(hostel.getHostelNum()).size();
            checkNums.put(hostel.getHostelName(),checkNum);
        }
        checkNums=sortByValue(checkNums);

        request.setAttribute("checkNums",checkNums);
        return "people";
    }

    private Map<String,Integer> sortByValue(Map<String,Integer> map){
        List<Map.Entry<String,Integer>> list =
                new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<String,Integer>>() {
            public int compare( Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2 ) {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        Map<String,Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String,Integer> entry : list) {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}
