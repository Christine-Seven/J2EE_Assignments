package action;

import model.Hostel;
import model.Manager;
import model.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.HostelService;
import service.ManagerService;
import service.VipService;

import javax.servlet.http.HttpSession;

/**
 * Created by Seven on 2017/2/21.
 */
@Controller
public class LoginAction extends BaseAction{

    @Autowired
    private VipService vipService;
    @Autowired
    private HostelService hostelService;
    @Autowired
    private ManagerService managerService;

    @Override
    public String execute() throws Exception{
            String name=request.getParameter("name");
            String password=request.getParameter("password");
            //以编号开头的字母区分会员与客栈
            char first= name.charAt(0);
            switch (first){
                case 'V':
                    //会员
                    Vip vip=vipService.findVipById(name);
                    if(vip==null){
                        System.out.println("Cannot find the vip");
                        return "relogin";
                    }else{
                        //成功登录
                        if(vipService.checkPassword(name,password)) {
                            System.out.println("Find the vip");
                            HttpSession session = request.getSession(true);
                            session.setAttribute("type", vip);
                            session.setAttribute("id", name);
                            return "vip";
                        }else{
                            return "relogin";
                        }
                    }
                case 'H':
                    //客栈
                    Hostel hostel=hostelService.queryHostelByNum(name);
                    if(hostel==null){
                        System.out.println("Cannot find the hostel");
                        return "relogin";
                    }else{
                        System.out.println("Find the hostel");
                        HttpSession session=request.getSession(true);
                        session.setAttribute("type",hostel);
                        session.setAttribute("id", name);
                        return "hostel";
                    }
                case 'M':
                    //经理
                    Manager manager=managerService.queryByNum(name);
                    if(manager==null){
                        System.out.println("Cannot find the manager");
                        return "relogin";
                    }else{
                        HttpSession session=request.getSession(true);
                        session.setAttribute("type",manager);
                        session.setAttribute("id", name);
                        return "manager";
                    }
                default:
                    //不存在该编号
                    return "relogin";
            }

    }
}
