<%@ page import="po.StudentPO" %>
<%@ page import="po.ScorePO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="po.CoursePO" %>
<%@ page import="po.TestPO" %>
<%@ page import="java.util.List" %><%--
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
        ScorePO scorePO=(ScorePO) request.getAttribute("scorePO");
        StudentPO studentPO = scorePO.getStudentPO();
        //student info
        String student_id=studentPO.getStudent_id();
        String student_name=studentPO.getStudent_name();
        String grade=studentPO.getGrade();
    %>
    <div>
        <h3>学号:<%=student_id%></h3>
        <h3>姓名:<%=student_name%></h3>
        <h3>年级:<%=grade%></h3>
    </div>

    <%
        //course info
        HashMap<CoursePO,List<TestPO>> courseScore=scorePO.getCourseScore();
        for(CoursePO coursePO:courseScore.keySet()){
            String course_id=coursePO.getCourse_id();
            String course_name=coursePO.getCourse_name();
    %>
        <h4>科目ID:<%=course_id%></h4>
        <h4>科目名称:<%=course_name%></h4>
    <%
            List<TestPO> testPOList=courseScore.get(coursePO);
            for(TestPO testPO:testPOList){
                String test_id=testPO.getTest_id();
                String test_name=testPO.getTest_name();
                String date=testPO.getDate();
                int score=testPO.getScore();
    %>
        <p>测验ID:<%=test_id%></p>
        <p>测验名称<%=test_name%></p>
        <p>分数<%=score%></p>
        <p>日期<%=date%></p>
    <%
            }

        }

    %>
</body>
</html>
