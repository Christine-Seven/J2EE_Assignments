package action;

import model.Approval;
import model.BalanceSettle;
import model.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.ApprovalService;
import service.BalanceSettleService;
import service.HostelService;
import service.ManagerService;
import util.ApprovalStateEnum;
import util.ApprovalVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Seven on 2017/3/2.
 */
@Controller
public class ManagerAction extends BaseAction {

    @Autowired
    HostelService hostelService;
    @Autowired
    ManagerService managerService;
    @Autowired
    BalanceSettleService balanceSettleService;
    @Autowired
    ApprovalService approvalService;

    public String getApply(){
        List<Approval> approvals=approvalService.queryByState(ApprovalStateEnum.WAIT.toString());
        //将其转化为审批信息列表
        List<ApprovalVO> approvalVOs=new ArrayList<>();
        for(Approval approval:approvals){
            Hostel hostel=hostelService.queryHostelByNum(approval.getHostelNum());
            ApprovalVO approvalVO=new ApprovalVO();
            approvalVO.setApprovalNum(approval.getApprovalNum());
            approvalVO.setApplyType(approval.getApprovalType().toString());
            approvalVO.setApplyDate(approval.getApplyDate());
            approvalVO.setHostelNum(hostel.getHostelNum());
            approvalVO.setHostelName(hostel.getHostelName());
            approvalVO.setProvince(hostel.getProvince());
            approvalVO.setCity(hostel.getCity());
            approvalVO.setAddress(hostel.getAddress());
            approvalVO.setHostelInfo(hostel.getHostelInfo());
            approvalVO.setLevel(hostel.getLevel());
            approvalVOs.add(approvalVO);
        }
        request.setAttribute("applyList",approvalVOs);
        return "apply";
    }

    public String managerApproval(){
        String hostelNum=request.getParameter("hostelNum");
        String approve=request.getParameter("approve");
        int approvalNum=Integer.valueOf(request.getParameter("approvalNum"));

        Approval approval=approvalService.find(approvalNum);
        Hostel hostel=hostelService.queryHostelByNum(hostelNum);
        if(approve.equals("-1")){
            hostel.setApprovalState(ApprovalStateEnum.DISAPPROVE.toString());
            hostelService.updateHostel(hostel);
            approval.setApproveState(ApprovalStateEnum.DISAPPROVE.toString());
            approvalService.update(approval);
        }else{
            hostel.setApprovalState(ApprovalStateEnum.APPROVE.toString());
            hostelService.updateHostel(hostel);
            approval.setApproveState(ApprovalStateEnum.APPROVE.toString());
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            approval.setApproveDate(sdf.format(date));
            approvalService.update(approval);
        }

        return this.getApply();
    }

    public String getSettle(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<BalanceSettle> balanceSettles;
        try {
            balanceSettles = managerService.getWaitSettle(sdf.format(date));
            System.out.println(balanceSettles.size());
            request.setAttribute("balanceSettle",balanceSettles);
            return "settle";
        } catch (ParseException e) {
            return "fail";
        }
    }

    public String managerSettle(){
        String managerNum=String.valueOf(request.getSession().getAttribute("id"));
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            managerService.getWaitSettle(sdf.format(date));
            List<BalanceSettle> balanceSettles=managerService.settleBalance(sdf.format(date),managerNum);
            request.setAttribute("balanceSettle",balanceSettles);
            return "managerSettle";
        } catch (ParseException e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String getStatistic(){
        return "statistic";
    }

}
