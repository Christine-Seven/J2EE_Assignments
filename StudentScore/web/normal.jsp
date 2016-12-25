<%@ page import="dao.StudentDAO" %>
<%@ page import="dao.ScoreDAO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="dao.CourseDAO" %>
<%@ page import="dao.TestDAO" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 2016/12/19
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Score</title>
</head>
<body>
    <%
        ScoreDAO scoreDAO =(ScoreDAO) request.getAttribute("scoreDAO");
        StudentDAO studentDAO = scoreDAO.getStudentDAO();
        //student info
        String student_id= studentDAO.getStudent_id();
        String student_name= studentDAO.getStudent_name();
        String grade= studentDAO.getGrade();
    %>
    <div>
        <h3>学号:<%=student_id%></h3>
        <h3>姓名:<%=student_name%></h3>
        <h3>年级:<%=grade%></h3>
    </div>

    <%
        //course info
        HashMap<CourseDAO,List<TestDAO>> courseScore= scoreDAO.getCourseScore();
        for(CourseDAO courseDAO :courseScore.keySet()){
            String course_id= courseDAO.getCourse_id();
            String course_name= courseDAO.getCourse_name();
    %>
        <h4>科目ID:<%=course_id%></h4>
        <h4>科目名称:<%=course_name%></h4>
    <%
            List<TestDAO> testDAOList =courseScore.get(courseDAO);
            for(TestDAO testDAO : testDAOList){
                String test_id= testDAO.getTest_id();
                String test_name= testDAO.getTest_name();
                String date= testDAO.getDate();
                int score= testDAO.getScore();
    %>
        <p>测验ID:<%=test_id%></p>
        <p>测验名称<%=test_name%></p>
        <p>分数<%=score%></p>
        <p>日期<%=date%></p>
    <%
            }

        }

    %>
    <%
        Integer onlineNum=(Integer)request.getServletContext().getAttribute("onlineNum");
        Integer touristNum=(Integer) request.getServletContext().getAttribute("touristNum");
        Integer loginNum=(Integer) request.getServletContext().getAttribute("loginNum");
    %>
    <p>在线人数：<%=onlineNum%></p>
    <p>游客人数：<%=touristNum%></p>
    <p>登录人数：<%=loginNum%></p>

    <form action="/logout" method="post">
        <input type="submit" name="logout" placeholder="退出">
    </form>
</body>
</html>
