<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 2017/3/4
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Hostel World</title>

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
<body style="background-image: url(img/bg2.jpg) ;background-size: cover">
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
                <a class="navbar-brand">Hostel World</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="main.action">首页</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li data-toggle="modal" data-target="#login"><a href="#">登录/注册</a></li>
                    <li><a href="#">我要开店</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>
</div>

<!--提示框-->
<div class="modal fade" tabindex="-1" role="dialog" id="vip-register" style="margin-top: 10%">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="align-items: center;padding-left: 3%">注册新会员</h4>
            </div>
            <div class="modal-body">
                <form action="/hostelworld/vipRegister" method="post" onsubmit="return toRegister()">
                    <div class="form-group">
                        <input type="text" name="vipName" id="vipName" class="form-control" placeholder="请输入会员名">
                    </div>
                    <div class="form-group">
                        <input type="password" name="passwd" id="pwd" class="form-control" placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <input type="password" name="confirmpwd" id="cpwd" class="form-control" placeholder="请再次输入密码">
                    </div>
                    <div class="form-group">
                        <input type="number" name="bandCardId" id="bankCardId" class="form-control"
                               placeholder="请输入银行卡号">
                    </div>
                    <div class="row">
                        <div class="col-xs-8">
                            <a data-toggle="modal" data-target="#login" data-dismiss="modal"
                               class="text-center">已经是会员了</a>
                        </div>
                        <!-- /.col -->
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">注册</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="col-md-4 row" style="margin-top:20%;margin-left:7%;background-color: rgba(163, 166, 157, 0.67);">
    <h4 class="modal-title" style="align-items: center;padding-left: 3%;padding-top: 3%">欢迎登录</h4>
    <div class="modal-body">
            <form action="/hostelworld/login" method="post" onsubmit="return toLogin()">
                <div class="form-group">
                    <input type="name" id="name" name="name" class="form-control" placeholder="请输入会员名">
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码">
                </div>
                <div class="row">
                    <div class="col-xs-8">
                        <a data-toggle="modal" data-target="#vip-register" data-dismiss="modal" class="text-center">注册成为新会员</a>
                    </div>
                    <!-- /.col -->
                    <div class="col-xs-4">
                        <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                    </div>
                </div>
            </form>
        </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/loginandregister.js"></script>

</body>
</html>
