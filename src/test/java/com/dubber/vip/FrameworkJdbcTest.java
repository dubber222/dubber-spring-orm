package com.dubber.vip;

import com.dubber.vip.model.ShopUser;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dubber on 2018/5/10.
 *
 * 框架式的JDBC
 */
public class FrameworkJdbcTest {

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
        List list = SearchList(ShopUser.class);


        System.out.println(Arrays.toString(list.toArray()));


    }

    private static List<?> SearchList(Class<?> clazz) {
        Connection conn = null;
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pbqshop?characterEncoding=UTF-8&rewriteBatchedStatements=true","root","chen2shuai");
            Table table = clazz.getAnnotation(Table.class);

            PreparedStatement ps =  conn.prepareStatement("select * from " + table.name());
            ResultSet rs =  ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取查询列数量
            int columnCount = rsmd.getColumnCount();
            while(rs.next()){
                // ---------------ORM开始----------
                Object instance = clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i+1);
                    //获得类中私有属性
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);

                    Object fieldType = field.getType();
                    packageInstance(rs, instance, columnName, field, fieldType);
                }

                // ---------------ORM结束----------

                list.add(instance);
            }

            rs.close();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    private static void packageInstance(ResultSet rs, Object instance, String columnName, Field field, Object fieldType) throws IllegalAccessException, SQLException {
        if(fieldType == int.class){
            field.set(instance,rs.getInt(columnName));
        }
        if(fieldType == double.class){
            field.set(instance,rs.getDouble(columnName));
        }

        if(fieldType == float.class){
            field.set(instance,rs.getFloat(columnName));
        }

        if(fieldType == boolean.class){
            field.set(instance,rs.getBoolean(columnName));
        }

        if(fieldType == String.class){
            field.set(instance,rs.getString(columnName));
        }

        if(fieldType == long.class){
            field.set(instance,rs.getLong(columnName));
        }
        //TODO 还有很多类型需要转化
    }
}
