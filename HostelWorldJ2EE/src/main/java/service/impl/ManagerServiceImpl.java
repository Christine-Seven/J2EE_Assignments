package service.impl;

import dao.BalanceSettleDao;
import dao.HostelDao;
import dao.ManagerDao;
import dao.OrdersDao;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ApprovalService;
import service.ManagerService;
import util.OrderConditionEnum;
import util.SettleConditionEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Seven on 2017/2/25.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private BalanceSettleDao balanceSettleDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private HostelDao hostelDao;

    @Override
    public boolean checkManager(String managerNum) {
        return managerDao.checkManager(managerNum);
    }

    @Override
    public boolean checkPassword(String managerNum, String password) {
        return managerDao.checkPassword(managerNum,password);
    }

    @Override
    public void save(Manager manager) {
        managerDao.save(manager);
    }

    @Override
    public void update(Manager manager) {
        managerDao.update(manager);
    }

    @Override
    public List<Manager> getAllList() {
        return managerDao.getAllList();
    }

    @Override
    public Manager queryByNum(String managerNum) {
        return managerDao.queryByNum(managerNum);
    }

    @Override
    public String getManagerNum() {
        return managerDao.getManagerNum();
    }

    @Override
    public void approve(List<Approval> approvals) {
        for (Approval approval:approvals){
            approvalService.update(approval);
        }
    }

    @Override
    public List<BalanceSettle> settleBalance(String date) throws ParseException {
        List<Orders> orderses=ordersDao.queryByCondition(OrderConditionEnum.CHECKOUT.toString());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date d=sdf.parse(date);
        for(Orders orders:orderses){
            Date checkoutDate=sdf.parse(orders.getCheckoutDate());
            if(checkoutDate.before(d)){
                String hostelNum=orders.getHostelNum();
                double paidMoney=orders.getPaidMoney();
                //若数据库中已存在该hostelNum并且结算状态为wait，则将paidMoney加到balance上
                //若不存在wait状态的hostelNum，则新建一条结算记录，并将其状态置为wait
                List<BalanceSettle> balanceSettles=balanceSettleDao.getBalanceSettleByHostel(hostelNum);
                boolean isExist=false;
                for(BalanceSettle balanceSettle:balanceSettles){
                    if(balanceSettle.getSettleCondition().equals(SettleConditionEnum.wait.toString())){
                        isExist=true;
                        double currentBalance=balanceSettle.getBalance();
                        balanceSettle.setBalance(currentBalance+paidMoney);
                        balanceSettleDao.update(balanceSettle);
                        break;
                    }
                }
                if(!isExist){
                    BalanceSettle balanceSettle=new BalanceSettle();
                    balanceSettle.setHosetlNum(hostelNum);
                    balanceSettle.setSettleCondition(SettleConditionEnum.wait.toString());
                    balanceSettle.setBalance(paidMoney);
                    balanceSettleDao.save(balanceSettle);
                }
            }
        }
        //获得所有wait状态的结算记录，将其balance添加到对应的hostel的profit中
        //并将其状态置为settled
        List<BalanceSettle> balanceSettles=balanceSettleDao.getBalanceSettleByCondition(SettleConditionEnum.wait.toString());
        for(BalanceSettle balanceSettle:balanceSettles){
            Hostel hostel=hostelDao.queryHostelByNum(balanceSettle.getHosetlNum());
            double currentBalance=balanceSettle.getBalance();
            double currentProfit=hostel.getProfit();
            hostel.setProfit(currentProfit+currentBalance);
            hostelDao.updateHostel(hostel);
            balanceSettle.setSettleCondition(SettleConditionEnum.settled.toString());
            balanceSettleDao.update(balanceSettle);
        }
        return balanceSettles;
    }
}
