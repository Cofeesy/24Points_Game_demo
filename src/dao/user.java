package dao;

import controller.controller;
import service.rankings;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class user {
    public static String nowuser = "";//当前用户名
    public static int score = 0;//当前分数
    public static int guanka=1;
    static Connection connection;

    //jdbc连接数据库
    static public void initConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/24_Games", "root", "18148059112Aa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //关闭数据库
    static public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //判断用户的用户名和密码是否正确
    public static boolean isuser(String password, String name) {
        try {
            nowuser = name;

            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery("select * from users where password = '" + password + "' and name = '" + name + "'");
            if (res.next()) {
                score=res.getInt("grade");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //是否存在该用户
    public static boolean isname(String name){
        try {
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery("select * from users where name = '" + name + "'");
            if (res.next()) {
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    //向数据库添加新用户信息
    public static void add(String name, String password, int grade) {
        try {
            nowuser = name;//设置当前用户

            String sql = "insert into users values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setInt(3, grade);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //排行榜
    public static void rankusers(controller frame) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery("select name,grade from users order by grade desc;");

            rankings rankings = new rankings(frame);
            Container container = rankings.getContentPane();
            JTextArea jTextArea = new JTextArea();
            container.add(jTextArea);
            jTextArea.append("排行榜：");

            int rank = 0;
            while (res.next()&&rank<=9) {
                String name = res.getString("name");
                int grade = res.getInt("grade");
                rank++;
                jTextArea.append("\n" + rank + "." + name + ": " + grade);
            }
            rankings.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //向数据库中当前用户加分
    public static void addgrade(){
        score+=guanka*5;
        try {
            PreparedStatement ps = connection.prepareStatement("update users set grade =? where name ='"+nowuser+"'");
            ps.setInt(1,score);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
