package action;

import model.Vip;
import service.VipService;
import service.impl.VipServiceImpl;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * Created by Seven on 2017/2/15.
 */
public class VipRegisterAction {

    private Vip vip;
    private VipService vipService;

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public VipService getVipService() {
        return vipService;
    }

    public void setVipService(VipService vipService) {
        this.vipService = vipService;
    }

    public String execute(){
        try{
            this.setVipService(new VipServiceImpl());
            vipService.registerVip(vip);
            return SUCCESS;
        }catch (Exception e){
            return ERROR;
        }
    }
}
