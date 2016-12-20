package filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Seven on 2016/12/20.
 */
public class CharacterEncodingFilter implements Filter {

    class MyRequest extends HttpServletRequestWrapper {
        ServletRequest request;
        private HttpServletRequest req;

        MyRequest(HttpServletRequest req){
            super(req);
            this.req=req;
        }

        @Override
        public String getParameter(String name){
            name=super.getParameter(name);
            try {
                return new String(name.getBytes("iso8859-1"), "utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;

        if(request.getMethod().equals("post")){
            request.setCharacterEncoding("utf-8");
        }else{
            request.setCharacterEncoding("utf-8");
            request=new MyRequest(request);
        }
        servletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(request,servletResponse);
    }
}
