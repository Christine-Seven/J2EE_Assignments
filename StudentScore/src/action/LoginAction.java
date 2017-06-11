package action;

import model.Course;
import model.Score;
import model.Test;
import serviceImpl.TestEnquiresServiceImpl;
import serviceImpl.UserDataServiceImpl;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Seven on 22/03/2017.
 */
@Controller
public class LoginAction extends BaseAction {
    @Autowired
    UserDataService userDataService;

    public String login() {
        //get the input info: id&password
        String student_id = request.getParameter("id");
        String password = request.getParameter("password");

        try {
            if (userDataService.getUserByID(student_id, password)) {
                request.getSession().setAttribute("id", student_id);
                ServletContext context = request.getSession().getServletContext();
                Integer loginNum = (Integer) context.getAttribute("loginNum");
                Integer touristNum = (Integer) context.getAttribute("touristNum");
                context.setAttribute("loginNum", loginNum + 1);
                context.setAttribute("touristNum", touristNum - 1);
                //valid user
                TestEnquiresServiceImpl testEnquiresService = new TestEnquiresServiceImpl();
                Score scoreDAO = testEnquiresService.getScorePO(student_id);
                HashMap<Course, List<Test>> courseScore = scoreDAO.getCourseScore();
                boolean allTestTaken = true;
                for (Course course : courseScore.keySet()) {
                    List<Test> testList = courseScore.get(course);

                    for (Test test : testList) {
                        if (test == null) {
                            allTestTaken = false;
                        }
                    }
                }
                if (allTestTaken) {
                    //the student has took all the tests
                    request.setAttribute("scoreDAO", scoreDAO);
//                        request.setAttribute("loginNum",loginNum);
//                        request.setAttribute("touristNum",touristNum);
//                        request.setAttribute("onlineNum",context.getAttribute("onlineNum"));
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/normal.jsp");
                    if (dispatcher != null) {
                        dispatcher.forward(request, response);
                    }
                } else {
                    //warning page
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/warning.jsp");
                    if (dispatcher != null) {
                        dispatcher.forward(request, response);
                    }
                }

            } else {
                //invalid user
                request.setAttribute("id", student_id);
                request.setAttribute("password", password);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                if (dispatcher != null) {
                    dispatcher.forward(request, response);
                }
            }
        } catch (NamingException e) {
            System.out.println("Naming exception");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL exception");
            e.printStackTrace();
        }

        return "login";
    }

}
