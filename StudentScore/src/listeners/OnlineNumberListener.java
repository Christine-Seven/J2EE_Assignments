package listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Seven on 2016/12/20.
 */
public class OnlineNumberListener implements HttpSessionListener{

    //统计在线人数
    private int userCount=0;

    @Override
    public void sessionCreated(HttpSessionEvent arg0){

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0){

    }
}
