package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Seven on 2017/2/21.
 */
public class BaseAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware,ServletContextAware{

    public HttpServletRequest request;
    public HttpServletResponse response;
    public ServletContext context;
    public Map sessionMap;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response=httpServletResponse;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap=map;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context=servletContext;
    }
}
