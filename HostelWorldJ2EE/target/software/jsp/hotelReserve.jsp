<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 2017/3/8
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hotel Reserve</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

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
                <li><a href="main.jsp">首页</a></li>
                <li class="active"><a href="hotelSearch.jsp">预订客栈</a></li>
                <li><a href="#">联系我们</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <!--<li id="notLogin" data-toggle="modal" data-target="#login"><a href="#">登录/注册</a></li>-->
                <li><a href="#">我要开店</a></li>
            </ul>

            <div id="memberDiv"
                 style="position: absolute;top: 15px;left: 950px;width: 150px;height: 30px;color: black">
                <label>会员名:</label><p id="name" style="position:absolute;top:0px;left:50px;width:70px;height:20px">${id}</p>
            </div>

        </div><!--/.nav-collapse -->
    </div>
</nav>
</div>

<img src="../img/pic03.jpg" style="width: 100%;height: 300px;z-index: -1">
<div style="position: relative;margin-left: 50px">
    <h3>南京市鼓楼区青年旅社</h3>
    <p style="height: 100px;width: 100%"></p>
</div>

<div>
    <div style="margin-left: 200px">
        <label style="padding-left: 3%">入住日期:</label>
        <input type="date" id="checkinDate" style="width: 150px;height: 30px">
        <label style="padding-left: 3%">离店日期:</label>
        <input type="date" id="checkoutDate" style="width: 150px;height: 30px">
        <button class="btn-primary" type="button" style="margin-left: 30px">查询</button>
    </div>
    <table style="position:relative;left:10%;top:50px;" border="1">
        <tr>
            <td style="padding: 30px">
                <img src="../img/pic02.jpg" style="height: 100px;width: 150px">
            </td>
            <td style="padding: 30px;">
                <h3>豪华单人间</h3>
                <p style="width: 500px;height: 30px">jskfjalssfaafs</p>
            </td>
            <td style="padding:30px;">
                <h3>¥300</h3>
                <button type="button" class="btn-primary" data-toggle="modal" data-target="#reserve">预约</button>
            </td>
        </tr>

    </table>
</div>


<!--提示框-->
<div class="modal fade" tabindex="-1" role="dialog" id="reserve" style="margin-top: 10%">
    <div class="modal-dialog" role="document" >
        <div class="modal-content" style="width: 700px;height: auto;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="align-items: center;padding-left: 3%">客栈预约</h4>
            </div>
            <div class="modal-body">
                <div>
                    <label style="padding: 5px">入住日期：</label>
                    <input type="date">
                    <label style="padding: 5px">离店日期：</label>
                    <input type="date">
                </div>
                <div>
                    <table>
                        <tr style="padding:20px;">
                            <td style="padding: 5px">
                                <label style="padding: 5px">入住人姓名:</label>
                                <input id="name1">
                            </td>
                            <td style="padding: 5px">
                                <label style="padding: 5px">身份证号:</label>
                                <input id="idNum1">
                            </td>
                            <td style="padding: 5px">
                                <label style="padding: 5px">性别:</label>
                                <select id="gender1">
                                    <option value="1">男</option>
                                    <option value="2">女</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <div class="row">
                        <div class="col-xs-3" style="float: right">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">预约</button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../js/bootstrap.min.js"></script>
</body>
</html>
