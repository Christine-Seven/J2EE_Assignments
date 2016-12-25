package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Seven on 2016/12/20.
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event){
        ServletContext context=event.getServletContext();
        context.setAttribute("onlineNum",0);
        context.setAttribute("touristNum",0);
        context.setAttribute("loginNum",0);
        System.out.print("监听到应用被启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event){
        ServletContext context=event.getServletContext();
        context.removeAttribute("onlineNum");
        context.removeAttribute("touristNum");
        context.removeAttribute("loginNum");
        System.out.print("监听到应用被关闭");
    }
}
