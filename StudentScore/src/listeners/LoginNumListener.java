package listeners;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Seven on 2016/12/20.
 */
public class LoginNumListener implements HttpSessionListener,HttpSessionAttributeListener{

    @Override
    public void sessionCreated(HttpSessionEvent arg0){
        ServletContext context=arg0.getSession().getServletContext();
        Integer onlineNum=(Integer) context.getAttribute("onlineNum");
        Integer touristNum=(Integer)context.getAttribute("touristNum");
        context.setAttribute("onlineNum",onlineNum+1);
        context.setAttribute("touristNum",touristNum+1);
        System.out.println("在线人数"+onlineNum+" "+arg0.getSession().getId());
        System.out.println("游客人数"+touristNum);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0){
        ServletContext context=arg0.getSession().getServletContext();
        if(arg0.getSession().getAttribute("id")==null){
            Integer onlineNum=(Integer) context.getAttribute("onlineNum");
            Integer touristNum=(Integer) context.getAttribute("touristNum");
            context.setAttribute("onlineNum",onlineNum--);
            context.setAttribute("touristNum",touristNum--);
        }else{
            Integer onlineNum=(Integer) context.getAttribute("onlineNum");
            Integer loginNum=(Integer) context.getAttribute("loginNum");
            context.setAttribute("onlineNum",onlineNum--);
            context.setAttribute("loginNum",loginNum--);
        }
        System.out.println(arg0.getSession().getId()+"下线");

    }
}
