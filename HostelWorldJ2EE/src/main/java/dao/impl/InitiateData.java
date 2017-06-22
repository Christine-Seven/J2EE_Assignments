package dao.impl;

import util.ApprovalStateEnum;
import util.OrderConditionEnum;
import util.PayMethod;
import util.VipStateEnum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Seven on 21/06/2017.
 */
public class InitiateData {
    private static Connection conn;

    public static Connection getConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String databaseName = "hostelworld";// 已经在MySQL数据库中创建好的数据库。
            String userName = "root";// MySQL默认的root账户名
            String password = "123456";// 默认的root账户密码为空
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);
            return connection;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void initiateOrders(){
         String orderNum;
         String hostelNum;
         String vipNum;
         int roomTypeId;
         int roomNum;
         double requiredMoney;
         double paidMoney;
         String orderCondition;
         String checkinDate;
         String checkoutDate;
         String payMethod;

        Random random=new Random();
//        String[] vipNums={"V000001","V000002","V000003","V000004","V000005","V000006"};
        //188,288,388
        int[] prices={307,439,532};
        for(int i=17;i<=200;i++){

            DecimalFormat df=new DecimalFormat("000000");
            orderNum="H000015"+df.format(i);
            hostelNum="H000015";
            vipNum="V"+df.format(random.nextInt(99)+1);
            roomTypeId=random.nextInt(3)+1;
            switch (roomTypeId){
                case 1:requiredMoney=prices[0];paidMoney=prices[0];break;
                case 2:requiredMoney=prices[1];paidMoney=prices[1];break;
                case 3:requiredMoney=prices[2];paidMoney=prices[2];break;
                default:requiredMoney=prices[1];paidMoney=prices[1];break;
            }
            roomNum=1;
            orderCondition= OrderConditionEnum.SETTLE.toString();
            int month=random.nextInt(6)+1;
            int day;
            if(month==2){
                day=random.nextInt(28)+1;
                checkinDate = "2017-" + month + "-" + day;
                if(day<28) {
                    checkoutDate="2017-"+month+"-"+(day+1);
                }else{
                    checkoutDate="2017-"+(month+1)+"-01";
                }
            } else{
                day=random.nextInt(30)+1;
                checkinDate = "2017-" + month + "-" + day;
                if(day<30) {
                    checkoutDate="2017-"+month+"-"+(day+1);
                }else{
                    checkoutDate="2017-"+(month+1)+"-01";
                }
            }
            payMethod=PayMethod.CARD.toString();
            String sql="INSERT INTO orders VALUES (\'" +orderNum+
                    "\',\'" +hostelNum+
                    "\',\'" +vipNum+
                    "\'," +roomTypeId+
                    "," +roomNum+
                    "," +requiredMoney+
                    "," +paidMoney+
                    ",\'" +orderCondition+
                    "\',\'" +checkinDate+
                    "\',\'" +checkoutDate+
                    "\',\'" +payMethod+
                    "\');";

            try {
                Statement statement=conn.createStatement();
                int re=statement.executeUpdate(sql);
                if(re!=1){
                    System.out.println(sql);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private static void initiateRoomPlan(){
         String hostelNum;
         int roomTypeId;
         int roomNum;
         String startDate;
         String endDate;
         double roomPrice;
         String date;

         Random random=new Random(10);
        for(int i=5;i<=109;i++){
            DecimalFormat df=new DecimalFormat("000000");
            hostelNum="H"+df.format(i);
            for(int type=1;type<=3;type++){
                roomTypeId=type;
                roomNum=random.nextInt(20);
                int day=(random.nextInt(31)+1);
                startDate="2016-10-"+day;
                endDate="2017-5-"+day;
                roomPrice=random.nextInt(800);
                date=startDate;

                String sql="INSERT INTO roomPlan(hostelNum,roomTypeId,roomNum,startDate,endDate,roomPrice,date) VALUES(\'" +hostelNum+
                        "\'," +roomTypeId+
                        "," +roomNum+
                        ",\'" +startDate+
                        "\',\'" +endDate+
                        "\'," +roomPrice+
                        ",\'" +date+
                        "\');";

                try {
                    Statement statement=conn.createStatement();
                    int result=statement.executeUpdate(sql);
                    if(result!=1){
                        System.out.println(sql);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    private static void initiateHostel(){

         String hostelNum;
         String hostelName;
         String hostelPassword;
         double profit;
         int level;
         String province;
         String city;
         String address;
         String hostelInfo;
         String approvalState;
         String applyDate;
        Random random=new Random(10);
//         String[] cities={"南京","无锡","淮安","苏州","常州","镇江","泰州"};
         for(int i=5;i<=150;i++){
             DecimalFormat df=new DecimalFormat("000000");
             hostelNum="H"+df.format(i);
             hostelName="hostel"+i;
             hostelPassword="123";

             profit=random.nextInt(100)*100;
             level=random.nextInt(5)+1;
             province="";
             city="";
//             System.out.println(city);
             address=i+"";
             hostelInfo=hostelName;
             approvalState= ApprovalStateEnum.APPROVE.toString();
             int day=(random.nextInt(31)+1);
             applyDate="2015-10-"+day;

             String sql="INSERT INTO hostel VALUES (\'" +hostelNum+
                     "\',\'" +hostelPassword+
                     "\'," +profit+
                     ",\'" +province+
                     "\',\'" +city+
                     "\',\'" +address+
                     "\',\'" +hostelInfo+
                     "\',\'" +approvalState+"\',"+level+",\'"+hostelName+
                     "\',\'" +applyDate+
                     "\');";
             try {
                 Statement statement=conn.createStatement();
                 int result=statement.executeUpdate(sql);
                 if(result!=1){
                     System.out.println(sql);
                 }
             } catch (SQLException e) {
                 e.printStackTrace();
             }

         }

    }

    private static void initiateVips(){
         String vipNum;

         String vipName;
         String vipPassword;
         String bankCardId;
         double vipPoint;
         double money;
         String state;
         String activateDate;
         String validDate;
         Integer vipLevel;
        //     * 1-500 level 1
        //     * 500-1000 level 2
        //     * 1000-2000 3
        //     * 2000-5000 4
        //     * >5000 5
         for(int i=4;i<100;i++){
             DecimalFormat df=new DecimalFormat("000000");
             vipNum="V"+df.format(i);
             vipName="vip"+i;
             vipPassword="123";
             bankCardId=i+"";
             vipPoint=i*100;
             money=1000;
             state= VipStateEnum.ACTIVATE.toString();
             activateDate="2016-10-12";
             validDate="2017-10-12";
             vipLevel=getVipLevel(vipPoint);

             String sql="INSERT INTO vip VALUES (\'" +vipNum+"\',\'"+vipName+"\',\'"+vipPassword+"\',\'"+bankCardId+"\',"
                     +vipPoint+","+money+",\'"+state+"\',\'"+activateDate+"\',"+vipLevel+",\'"+validDate+
                     "\');";
             Statement stmt;
             try {
                 stmt = conn.createStatement();
                 int result = stmt.executeUpdate(sql);
                 if(result==-1){
                     System.out.println(sql);
                     System.out.println("WRONG!!!");
                 }
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
    }

    private static int getVipLevel(double point) {
        int[] scale={0,500,1000,2000,5000};
        int[] levels={0,1,2,3,4,5};
        int level=0;
        while(point>scale[level]){
            level++;
            if(level>=5){
                break;
            }
        }
        return levels[level];
    }

    public static void main(String[] arg0){
        conn=getConn();
//        initiateVips();
//         initiateHostel();
//        initiateRoomPlan();
        initiateOrders();
    }
}
