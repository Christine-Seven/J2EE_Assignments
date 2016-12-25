package servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Seven on 2016/12/20.
 */
public class LogoutServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context=this.getServletContext();
        Integer touristNum=(Integer) context.getAttribute("touristNum");
        Integer loginNum=(Integer) context.getAttribute("loginNum");

        context.setAttribute("touristNum",touristNum++);
        context.setAttribute("loginNum",loginNum--);

        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
}
