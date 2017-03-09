package action;

import com.alibaba.fastjson.JSONObject;
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

    private String name;
    private String password;
    private String jsonString;

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception{

        if(name.length()==0||password.length()==0){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("result","notFindVip");
            jsonString="not find";
            return SUCCESS;
        }else{
            //以编号开头的字母区分会员与客栈
            char first= name.charAt(0);
            switch (first){
                case 'V':
                    //会员
                    if(!vipService.isExist(name)){
                        System.out.println("Cannot find the vip");
                        JSONObject jsonObject=new JSONObject();
                        jsonObject.put("result","notFindVip");
                        jsonString=jsonObject.toString();
                        return SUCCESS;
                    }else if(!vipService.checkPassword(name,password)){
                        System.out.println("Wrong password!");
                        JSONObject jsonObject=new JSONObject();
                        jsonObject.put("result","wrongPassword");
                        jsonString="Wrong Password";
                        return SUCCESS;
                    }else{
                        //成功登录
                        Vip vip=vipService.findVipById(name);
                        System.out.println("Find the vip");
                        HttpSession session=request.getSession(true);
                        session.setAttribute("type",vip);
                        session.setAttribute("id", name);
//                        JSONObject jsonObject=new JSONObject();
//                        jsonObject.put("result","success");
//                        jsonString=jsonObject.toString();
                        jsonString="success";
                        return SUCCESS;
                    }
                case 'H':
                    //客栈
                    if(!hostelService.checkHostel(name)){
                        System.out.println("Cannot find the hostel");
                        JSONObject jsonObject=new JSONObject();

                        return "relogin";
                    }else if(!hostelService.checkPassword(name,password)){
                        System.out.println("Wrong password!");
                        return "relogin";
                    }else{
                        Hostel hostel=hostelService.queryHostelByNum(name);
                        HttpSession session=request.getSession(true);
                        session.setAttribute("type",hostel);
                        session.setAttribute("id", name);
                        return "hostelLogin";
                    }
                case 'M':
                    //经理
                    if(!managerService.checkManager(name)){
                        System.out.println("Cannot find the manager");
                        return "relogin";
                    }else if(!managerService.checkPassword(name,password)){
                        System.out.println("Wrong password!");
                        return "relogin";
                    }else{
                        Manager manager=managerService.queryByNum(name);
                        HttpSession session=request.getSession(true);
                        session.setAttribute("type",manager);
                        session.setAttribute("id", name);
                        return "managerLogin";
                    }
                default:
                    //不存在该编号
                    return ERROR;
            }

        }
    }
}
