package action;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Seven on 22/03/2017.
 */
public class LogoutAction {
    public String logout() {
        ServletContext context=this.getServletContext();
        Integer touristNum=(Integer) context.getAttribute("touristNum");
        Integer loginNum=(Integer) context.getAttribute("loginNum");

        context.setAttribute("touristNum",touristNum++);
        context.setAttribute("loginNum",loginNum--);

        request.getRequestDispatcher("login.jsp").forward(request,response);
        return "logout";
    }
}
