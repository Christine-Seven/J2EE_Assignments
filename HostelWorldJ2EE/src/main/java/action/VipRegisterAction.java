package action;

import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.VipService;
import util.VipStateEnum;

/**
 * Created by Seven on 2017/2/15.
 */
@Controller
public class VipRegisterAction extends BaseAction{
    @Autowired
    private VipService vipService;

    public String execute(){
        try{
            String vipName=request.getParameter("vipName");
            String passwd=request.getParameter("passwd");
            String bankCardId=request.getParameter("bankCardId");
            Vip vip=new Vip();
            vip.setVipNum(vipService.getVipNum());
            vip.setVipName(vipName);
            vip.setVipPassword(passwd);
            vip.setBankCardId(bankCardId);
            vip.setState(VipStateEnum.register.toString());
            vip.setVipPoint(0);
            vip.setMoney(0);
            vipService.registerVip(vip);
            request.getSession().setAttribute("id",vip.getVipNum());
            request.getSession().setAttribute("type",vip);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
