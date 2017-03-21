<%@ page import="util.RoomTypeEnum" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="util.RoomVO" %>
<%@ page import="java.util.*" %><%--
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
    <link href="css/bootstrap.min.css" rel="stylesheet">

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
                <li><a href="main.action">首页</a></li>
                <li class="active"><a href="searchHostel.action">预订客栈</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="vipInfo.action">会员名: ${id}</a>
                </li>
                <li><a href="hostelRegister_hostelRegister.action">我要开店</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
</div>

<img src="img/pic03.jpg" style="width: 100%;height: 300px;z-index: -1">
<%
    String hostelName = String.valueOf(request.getAttribute("hostelName"));
    String hostelNum = String.valueOf(request.getAttribute("hostelNum"));
    String checkinDate = String.valueOf(request.getAttribute("checkinDate"));
    String checkoutDate = String.valueOf(request.getAttribute("checkoutDate"));
%>
<div>
    <div class="col-md-8">
        <form action="searchRoom" method="post">
            <div style="margin: 50px">
                <label style="padding-left: 3%">入住日期:</label>
                <input type="date" name="checkinDate" style="width: 150px;height: 30px" value="<%=checkinDate%>">
                <label style="padding-left: 3%">离店日期:</label>
                <input type="date" name="checkoutDate" style="width: 150px;height: 30px" value="<%=checkoutDate%>">
                <label style="padding-left: 3%">房间数量:</label>
                <input type="text" name="roomNum" style="width: 50px;height: 30px" value="1">

                <input type="hidden" name="hostelNum" value=<%=hostelNum%>>
                <button class="btn btn-primary" type="submit" style="margin-left: 30px">查询</button>
            </div>
        </form>

        <table style="position:relative;left:30px">
            <%
                String hostelInfo = String.valueOf(request.getAttribute("hostelInfo"));
                List<RoomVO> rooms = (ArrayList<RoomVO>) request.getAttribute("rooms");
                String type="";
                int i=1;
                for (RoomVO roomVO : rooms) {
                    if (roomVO.getRoomType().equals(RoomTypeEnum.KingsizeRoom.toString())) {
                        type = "豪华大床房";
                    } else if (roomVO.getRoomType().equals(RoomTypeEnum.DoubleRoom.toString())) {
                        type = "温馨双人间";
                    } else {
                        type = "精致单人间";
                    }
                    String url="img/room"+i+".png";
            %>
            <tr>
                <td style="padding: 10px">
                    <img src="<%=url%>" style="height: 200px;width: 250px">
                </td>
                <td style="padding: 10px;">
                    <h3><%=type%>
                    </h3>
                    <p style="width: 400px;height: 30px">剩余房间数量:<span><%=roomVO.getNum()%></span></p>
                </td>
                <td style="padding:10px;">
                    <h3>¥<%=roomVO.getPrice()%></h3>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-dismiss="modal"
                            data-target="#reserve">预约
                    </button>
                </td>
            </tr>

            <!--提示框-->
            <div class="modal fade" tabindex="-1" role="dialog" id="reserve" style="margin-top: 10%">
                <div class="modal-dialog" role="document">
                    <div class="modal-content" style="width: 700px;height: auto;">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" style="align-items: center;padding-left: 3%">客栈预约</h4>
                        </div>
                        <div class="modal-body">
                            <form action="reserveHostel" method="post">
                                <fieldset>
                                    <legend style="font-size: 16px">预约信息</legend>
                                    <label style="padding: 5px">
                                        客栈名称：
                                        <span name="hostelName"> <%=hostelName%> </span>
                                    </label>
                                    <br>
                                    <label style="padding: 5px">
                                        房间类型：
                                        <input type="hidden" name="roomType" value=<%=roomVO.getRoomType()%>>
                                        <span> <%=roomVO.getRoomType()%> </span>
                                    </label>
                                    <br>
                                    <label style="padding:5px">入住日期：<%=checkinDate%></label>
                                    <input type="hidden" name="checkinDate" value=<%=checkinDate%>>
                                    <label style="padding-left: 20px">离店日期：<%=checkoutDate%></label>
                                    <input type="hidden" name="checkoutDate" value=<%=checkoutDate%>>
                                </fieldset>
                                <fieldset style="margin-top: 10px">
                                    <legend style="font-size: 16px;">付款</legend>
                                    <label style="padding: 10px;width: 200px;height: 30px">应付金额:
                                        <span>¥<%=roomVO.getRequiredMoney()%></span>
                                    </label>
                                    <label style="padding: 10px;width: 200px;height: 30px">会员价:
                                        <span>¥<%=roomVO.getRequiredMoney()*0.8%></span>
                                        <input name="requiredMoney" type="hidden" value="<%=roomVO.getRequiredMoney()*0.8%>">
                                    </label>
                                    <label class="form-inline" style="padding: 10px">付款方式：
                                        <select name="payMethod">
                                            <option value="CARD">会员卡</option>
                                            <option value="CASH">现金</option>
                                        </select>
                                    </label>
                                </fieldset>
                                <div class="row">
                                    <div class="col-xs-3" style="float: right">
                                        <input type="hidden" name="hostelNum" value=<%=hostelNum%>>
                                        <input type="hidden" value="${id}" name="vipNum">
                                        <button type="submit" class="btn btn-primary btn-block btn-flat">预约</button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

            <%
                    i++;
                }
            %>
        </table>
    </div>

    <div class="col-md-4">
        <div style="position: relative;margin: 100px">
            <h3><%=hostelName%>
            </h3>
            <p style="height: 100px;width: 100%"><%=hostelInfo%>
            </p>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
