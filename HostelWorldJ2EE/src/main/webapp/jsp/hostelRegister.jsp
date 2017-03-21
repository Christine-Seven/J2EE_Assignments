<%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 17/03/2017
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hostel Register</title>
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
                <li><a href="main.action">预订客栈</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="main.action">登录/注册</a></li>
                <li><a href="hostelRegister_hostelRegister.action">我要开店</a></li>
            </ul>

        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="col-md-offset-2 col-md-8" style="margin-top: 50px">
    <div>
        <fieldset style="margin: 50px">
            <legend>注册新客栈</legend>
            <form action="hostelRegister_hostelInfo" method="post" onsubmit="beforelogin()">
                <label style="margin: 20px">客栈名称:</label>
                <input name="hostelName" placeholder="不超过20个字符" class="input-sm" style="width: 300px">
                <br>
                <label style="margin: 20px">密码:</label>
                <input id="password" name="password" type="password" placeholder="不超过20个字符" class="input-sm" style="width: 300px">
                <br>
                <label style="margin: 20px">确认密码:</label>
                <input id="confirm_password" name="confirmpassword" type="password" placeholder="不超过20个字符" class="input-sm" style="width: 300px">
                <br>
                <label style="margin: 20px">客栈等级:</label>
                <input name="level" placeholder="请输入整数" class="input-sm" style="width: 150px">
                <br>
                <div class="row" style="margin-top: 20px;margin-left: 5px">
                    <div class="col-xs-2">
                        <label >所在地址:</label>
                    </div>
                    <div class="col-xs-2" style="height: 20px">
                        <select name="province">
                            <option value="江苏省">江苏省</option>
                            <option value="上海">上海</option>
                        </select>
                    </div>
                    <div class="col-xs-2" style="height: 20px">
                        <select name="city">
                            <option value="南京">南京市</option>
                            <option value="苏州">苏州市</option>
                            <option value="常州">常州市</option>
                        </select>
                    </div>
                    <div class="col-xs-6" style="height: 20px">
                        <input name="address" class="input-sm" placeholder="不超过20个字符" style="width: 150px">
                    </div>
                </div>
                <div style="margin-left: 20px;margin-top: 30px">
                    <label>客栈简介:</label>
                    <textarea name="hostelInfo" class="form-control" rows="4" style="margin: 10px" placeholder="介绍一下你的客栈叭～"></textarea>
                </div>
                <button class="btn btn-default btn-primary col-md-offset-11" type="submit" style="margin-top: 20px">申请开店</button>
            </form>
        </fieldset>
    </div>
</div>
<script>
    function beforelogin(){
        var password=document.getElementById("password").value;
        var confirmpwd=document.getElementById("confirm_password").value;
        if(password==confirmpwd){
            return true;
        }
        return false;
    }
</script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
