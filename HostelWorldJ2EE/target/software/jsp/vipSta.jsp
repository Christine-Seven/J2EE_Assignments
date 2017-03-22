<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.alibaba.fastjson.JSONPObject" %><%--
  Created by IntelliJ IDEA.
  User: Seven
  Date: 19/03/2017
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vip Sta</title>
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
                <li><a href="vipInfo.action">会员名: ${id}</a></li>
                <li><a href="hostelRegister_hostelRegister.action">我要开店</a></li>
            </ul>

        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="navbar navbar-default navbar-fixed" style="width: 150px;height: 620px;top: 50px">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li role="presentation"><a href="vipInfo.action"><h5 style="padding-left: 15px">我的资料</h5></a></li>
        <li role="presentation"><a href="vipOrder.action"><h5 style="padding-left: 15px">我的订单</h5></a></li>
        <li role="presentation" class="active"><a href="vipSta.action"><h5 style="padding-left: 15px">统计信息</h5></a></li>
    </ul>
</div>
<%
    int total = (int) request.getAttribute("total");
    double totalMoney = (double) request.getAttribute("totalMoney");
    HashMap<String, Double> hostel = (HashMap<String, Double>) request.getAttribute("hostels");
    String jsonObject = JSONObject.toJSONString(hostel);

%>
<div style="position: absolute;top:80px;left:160px;width: 900px;height: 600px;">
    <div class="row" style="top: 100px;">
        <div class="col-md-4 col-md-offset-2">
            <div class=""
                 style="background: #337AB7;margin-right: 0.8em;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);transition: 0.5s all;border-radius: 20px">
                <div class="col-md-8" style="padding: 10px;">
                    <h3 style="color: #fff;font-size: 2.5em;font-family: 'Carrois Gothic', sans-serif;"><%=total%>张</h3>
                    <h4 style="	font-size: 1.2em;color: #fff;margin: 0.3em 0em;font-family: 'Carrois Gothic', sans-serif;">
                        累计订单</h4>
                    <p style=" color: #fff;font-size: 0.8em;line-height: 1.8em;">心之所向</p>
                </div>
                <div class="col-md-4 market-update-right"
                     style="top:25px;font-size: 3em;color:#337AB7;width: 80px;height: 80px;background: #fff;text-align: center;border-radius: 49px;-o-border-radius:49px;line-height: 1.7em;">
                    <span class="glyphicon glyphicon-list-alt" aria-hidden="true" style="top:10px"></span>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="col-md-4">
            <div class=""
                 style="background: #337AB7;margin-right: 0.8em;box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);transition: 0.5s all;border-radius: 20px">
                <div class="col-md-8" style="padding: 10px;">
                    <h3 style="color: #fff;font-size: 2.5em;font-family: 'Carrois Gothic', sans-serif;">¥<%=totalMoney%>
                    </h3>
                    <h4 style="	font-size: 1.2em;color: #fff;margin: 0.3em 0em;font-family: 'Carrois Gothic', sans-serif;">
                        累计消费</h4>
                    <p style=" color: #fff;font-size: 0.8em;line-height: 1.8em;">心之所向</p>
                </div>
                <div class="col-md-4 market-update-right"
                     style="top:25px;font-size: 3em;color:#337AB7;width: 80px;height: 80px;background: #fff;text-align: center;border-radius: 49px;-o-border-radius:49px;line-height: 1.7em;">
                    <span class="glyphicon glyphicon-list-alt" aria-hidden="true" style="top:10px"></span>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <div class="col-md-6 col-md-offset-2"  style="top:100px;">
        <fieldset>
            <legend>消费情况统计</legend>
            <div id="main" style="width: 600px;height:400px;"></div>
        </fieldset>

    </div>

</div>


<script src="js/echarts.simple.min.js"></script>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    var hostels =<%=jsonObject%>;
    var hostel = [];
    var money = [];
    var res = [];
    for (var item in hostels) {
        res.push(
                {
                    name: item,
                    value: hostels[item]
                }
        );
        hostel.push(item);
        money.push(hostels[item]);
    }

    //    for(var i=0;){
    //        money.push(hostels["string"][item]);
    //    }
    // 指定图表的配置项和数据
    option = {
        backgroundColor: '#ffffff',

        title: {
            text: '消费情况',
            left: 'center',
            top: 20,
            textStyle: {
                color: '#000000'
            }
        },

        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
            show: false,
            min: 80,
            max: 600,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        series: [
            {
                name: '客栈名称',
                type: 'pie',
                radius: '55%',
                center: ['50%', '50%'],
                data: res.sort(function (a, b) {
                    return a.value - b.value
                }),
                roseType: 'angle',
                label: {
                    normal: {
                        textStyle: {
                            color: 'rgba(0,0,0, 0.3)'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: 'rgba(0, 0, 0, 0.3)'
                        },
                        smooth: 0.2,
                        length: 10,
                        length2: 20
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#c23531',
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },

                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
            }
        ]

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</body>
</html>
