<%@ page import="model.Hostel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 2017/3/6
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hotel Search</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<script>

</script>
<div>
    <nav class="navbar navbar-default navbar-fixed-top" style="color: #d0e9c6">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" style="margin-left: -50px">Hostel World</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="main.action">首页</a></li>
                    <li class="active"><a href="searchHostel.action">预订客栈</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="vipInfo.action">会员名: ${id}</a></li>
                    <li><a href="hostelRegister_hostelRegister.action">我要开店</a></li>
                </ul>

            </div><!--/.nav-collapse -->
        </div>
    </nav>
</div>
<%
    String checkinDate = String.valueOf(request.getAttribute("checkinDate"));
    String checkoutDate = String.valueOf(request.getAttribute("checkoutDate"));
%>
<img src="img/pic01.jpg" style="width: 100%;height: 300px;z-index: -1">
<div style="position: absolute;top: 220px;height:80px;width:100%;background-color: rgba(0,0,0,0.2);"></div>
<div id="filterDiv" style="position: absolute;top: 33%;left: 20%;width:100%;height:30px;margin-top: 3%;color: white">
    <form action="searchHostel" method="post">
        <label>城市:</label>
        <select name="city" style="width: 150px;height: 30px">
            <option value="上海">上海</option>
            <option value="南京">南京</option>
        </select>
        <label style="padding-left: 3%">入住日期:</label>
        <input type="date" name="checkinDate" style="width: 150px;height: 30px" value=<%=checkinDate%>>
        <label style="padding-left: 3%">离店日期:</label>
        <input type="date" name="checkoutDate" style="width: 150px;height: 30px" value=<%=checkoutDate%>>
        <button class="btn-primary" type="submit" style="margin-left: 30px">查询</button>
    </form>

</div>

<table style="position:relative;left:10%;top:50px;">
    <%
        List<Hostel> hostelList = (ArrayList<Hostel>) request.getAttribute("hostels");
        for (int i = 0; i < hostelList.size(); i++) {
            Hostel hostel = hostelList.get(i);
            int j = i + 1;
            String url = "img/room" + j + ".png";
    %>
    <tr>
        <td style="padding: 50px">
            <img src="<%=url%>" style="height: 150px;width: 200px">
        </td>
        <td style="padding-bottom: 50px">
            <h3><a href=""></a><%=hostel.getHostelName()%>
            </h3>
            <p style="width: 500px;height: 50px"><%=hostel.getHostelInfo()%>
            </p>
        </td>
        <td style="padding:50px;">
            <form action="searchRoom" method="post">
                <input type="hidden" name="checkinDate" value="<%=checkinDate%>">
                <input type="hidden" name="checkoutDate" value="<%=checkoutDate%>">
                <input type="hidden" name="hostelNum" value=<%=hostel.getHostelNum()%>>
                <button type="submit" class="btn btn-default btn-primary">戳戳店铺</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-3.1.1.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>
