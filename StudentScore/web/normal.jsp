<%@ page import="model.Student" %>
<%@ page import="model.Score" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="model.Course" %>
<%@ page import="model.Test" %>
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
        Score scoreDAO =(Score) request.getAttribute("scoreDAO");
        Student student = scoreDAO.getStudent();
        //student info
        String student_id= student.getStudent_id();
        String student_name= student.getStudent_name();
        String grade= student.getGrade();
    %>
    <div>
        <h3>学号:<%=student_id%></h3>
        <h3>姓名:<%=student_name%></h3>
        <h3>年级:<%=grade%></h3>
    </div>

    <%
        //course info
        HashMap<Course,List<Test>> courseScore= scoreDAO.getCourseScore();
        for(Course course :courseScore.keySet()){
            String course_id= course.getCourse_id();
            String course_name= course.getCourse_name();
    %>
        <h4>科目ID:<%=course_id%></h4>
        <h4>科目名称:<%=course_name%></h4>
    <%
            List<Test> testList =courseScore.get(course);
            for(Test test : testList){
                String test_id= test.getTest_id();
                String test_name= test.getTest_name();
                String date= test.getDate();
                int score= test.getScore();
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
