package action;

import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import service.VipService;

import javax.servlet.http.HttpSession;

/**
 * Created by Seven on 2017/2/21.
 */
public class LoginAction extends BaseAction{

    @Autowired
    private VipService vipService;

    public VipService getVipService() {
        return vipService;
    }

    public void setVipService(VipService vipService) {
        this.vipService = vipService;
    }

    @Override
    public String execute() throws Exception{
        String vipNum=request.getParameter("vipNum");
        String password=request.getParameter("password");
        if(vipNum.length()==0||password.length()==0){
            return "relogin";
        }else{
            //以编号开头的字母区分会员与客栈
            Vip vip=vipService.findVipById(vipNum);
            if(vip==null){
                System.out.println("cannot find the vip");
                return "relogin";
            }else if(!password.equals(vip.getVipPassword())){
                System.out.println("wrong password");
                return "relogin";
            }else{
                //成功登录
                HttpSession session=request.getSession(true);
                session.setAttribute("type",vip);
                session.setAttribute("id",vipNum);
                return "success";
            }
        }
    }
}
