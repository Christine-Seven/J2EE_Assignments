<%@ page import="model.Vip" %>
<%@ page import="util.VipStateEnum" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 12/03/2017
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vip Info</title>
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
<nav class="navbar navbar-default navbar-fixed-top">
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
                <li><a href="searchHostel.action">预订客栈</a></li>
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

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 620px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation" class="active"><a href="vipInfo.action"><h5 style="padding-left: 15px">我的资料</h5></a></li>
        <li role="presentation"><a href="vipOrder.action"><h5 style="padding-left: 15px">我的订单</h5></a></li>
        <li role="presentation"><a href="vipSta.action"><h5 style="padding-left: 15px">统计信息</h5></a></li>
    </ul>
</div>

<%
    Vip vip = (Vip) request.getAttribute("vip");
%>

<div style="position:absolute;top:50px;width: 500px;height: 620px;font-size: 16px">
    <div class="col-md-8" style="position: absolute;margin-left:180px; width:600px;height:500px">
        <fieldset style="margin-top: 20px;margin-left: 50px">
            <legend>基本信息</legend>
            <a href="#" onclick="EditInfo()"
               style="color: #1ab7ea; float: right; margin-right: 60px;"><b>修改资料</b></a>

            <form action="modifyVip" method="post">
                <label style="margin: 10px">会员编号:</label>
                <label><%=vip.getVipNum()%>
                </label>
                <br>
                <label style="margin: 20px">会员名:</label>
                <span id="vipName"><%=vip.getVipName()%></span>
                <input id="name_input" name="vipName" value="<%=vip.getVipName()%>"
                       style="position: absolute; left: 180px;margin-top: 20px; width: 200px; visibility: hidden;">
                <br>
                <label style="margin: 20px">银行卡号:</label>
                <span id="bankCardId"><%=vip.getBankCardId()%></span>
                <input id="bankCard_input" name="bankCardId" value="<%=vip.getBankCardId()%>"
                       style="position: absolute; left: 180px;margin-top: 20px; width: 200px; visibility: hidden;">
                <br>
                <button type="submit" id="submit_btn" style="visibility: hidden" class="btn btn-default btn-primary btn-sm">保存修改</button>
            </form>
       </fieldset>

        <fieldset style="margin-top: 30px;margin-left: 50px">
            <legend>会员卡信息</legend>
            <div class="row">
                <div class="col-xs-8">
                    <label style="margin: 10px">会员等级:</label>
                    <label><%=vip.getVipLevel()%>
                    </label>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <label style="margin: 10px">会员积分:</label><label><%=vip.getVipPoint()%>
                </label>
                </div>
                <div class="col-xs-3">
                    <button style="margin: 10px" class="btn btn-default btn-primary btn-xs" type="button" data-toggle="modal"
                            data-target="#convert">兑换
                    </button>
                </div>

            </div>

            <br>
            <div class="row">
                <div class="col-xs-8">
                    <label style="margin: 10px">会员卡余额:</label><label><%=vip.getMoney()%>
                </label>
                </div>
                <div class="col-xs-3">
                    <button style="margin: 10px" class="btn btn-default btn-primary btn-xs" data-toggle="modal" data-target="#topup">充值
                    </button>
                </div>
            </div>
            <br>
            <%
                if (vip.getState().equals(VipStateEnum.CANCEL.toString())) {
            %>
            <div class="row">
                <div class="col-xs-8">
                    <label style="margin: 10px">会员卡状态:</label><label><%=vip.getState()%>
                </label>
                </div>
            </div>
            <%
            } else if (vip.getState().equals(VipStateEnum.REGISTER.toString())) {
            %>
            <div class="row">
                <div class="col-xs-8">
                    <label style="margin: 10px">会员卡状态:</label><label><%=vip.getState()%>
                </label>
                </div>
                <div class="col-xs-3" >
                    <button style="margin: 10px" class="btn btn-default btn-primary btn-xs" data-toggle="modal" data-target="#activate">激活
                    </button>
                </div>
            </div>
            <%
            } else {
            %>
            <div class="row">
                <div class="col-xs-8">
                    <label style="margin: 10px">会员卡状态:</label><label><%=vip.getState()%>
                </label>
                </div>
                <form action="cancelVip" method="post">
                    <div class="col-xs-3">
                        <button  style="margin: 10px" type="submit" class="btn btn-default btn-primary btn-xs">停止</button>
                    </div>
                </form>
            </div>
            <%
                }
            %>
            <br>
        </fieldset>
    </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" id="activate" style="height: 500px">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">会员卡激活</h4>
            </div>
            <div class="modal-body">
                <form action="activateVip" method="post">
                    <label class="form-inline">
                        充值金额:
                        <input name="money" class="form-control" placeholder="请输入金额">
                    </label>
                    <p><b>ATTENTION!!!</b><br>输入金额大于<b>1000</b>才可以激活会员卡～</p>
                    <button type="submit" class="btn btn-primary btn-sm" style="float: right">激活</button>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" tabindex="-1" role="dialog" id="convert" style="height: 500px">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">兑换积分</h4>
            </div>

            <div class="modal-body">
                <form action="convertCredit" method="post">
                    <label>当前积分<%=vip.getVipPoint()%>
                    </label>
                    <br>
                    <label class="form-inline">
                        兑换积分:
                        <input name="point" class="form-control" placeholder="请输入想要兑换的积分数">
                    </label>
                    <p>10个积分可以兑换1元哟～</p>
                    <button type="submit" class="btn btn-primary btn-sm" style="float:right;">兑换</button>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" tabindex="-1" role="dialog" id="topup" style="height: 500px">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">会员卡充值</h4>
            </div>
            <div class="modal-body">
                <form action="topUp" method="post">
                    <label>当前金额<%=vip.getMoney()%>
                    </label>
                    <br>
                    <label>
                        充值金额:
                        <input name="money" placeholder="请输入金额">
                    </label>
                    <button type="submit" class="btn btn-primary btn-sm" style="float:right;">充值</button>
                </form>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script>
    function EditInfo() {
        document.getElementById("vipName").style.visibility = "hidden";
        document.getElementById("bankCardId").style.visibility = "hidden";
        document.getElementById("name_input").style.visibility = "";
        document.getElementById("bankCard_input").style.visibility = "";
        document.getElementById("submit_btn").style.visibility = "";
    }
</script>
</body>
</html>
