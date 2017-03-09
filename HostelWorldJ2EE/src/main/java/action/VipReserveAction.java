package action;

import model.Vip;

/**
 * Created by Seven on 2017/3/2.
 */
public class VipReserveAction extends BaseAction {

    private Vip vip;
    private String vipNum;

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public String getVipNum() {
        return vipNum;
    }

    public void setVipNum(String vipNum) {
        this.vipNum = vipNum;
    }

    public String baseInfo() throws Exception{
        vip=(Vip) request.getSession().getAttribute("type");
        return SUCCESS;
    }
}
