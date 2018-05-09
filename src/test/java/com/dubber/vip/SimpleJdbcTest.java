package com.dubber.vip;

import com.dubber.vip.model.ShopUser;

import java.sql.*;

/**
 * Created by dubber on 2018/5/9.
 *
 * 一、简单的JDBC =====> 演变为框架
 * 使用反射机制
 */
public class SimpleJdbcTest {


    public static void main(String[] args) {
        /**
         * 数据库连接6步
         * 1、加载驱动
         * 2、创建连接connection
         * 3、创建预声明语句，开始事务
         * 4、执行sql语句
         * 5、获取结果集
         * 6、关闭结果集，语句集和连接
         */
        //search();
    }

    private static void search() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pbqshop?characterEncoding=UTF-8&rewriteBatchedStatements=true","root","chen2shuai");
            PreparedStatement statement =  conn.prepareStatement("select userid,username from shop_user");
            ResultSet resultSet =  statement.executeQuery();
            System.out.println(resultSet.toString());
            while(resultSet.next()){
                System.out.println("userid:" + resultSet.getLong("userid") + ",username:"+
                resultSet.getString("username"));
            }

            resultSet.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
