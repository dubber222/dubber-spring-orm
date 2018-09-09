package com.dubber.vip;

import com.alibaba.druid.util.StringUtils;
import com.dubber.vip.model.ShopUser;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dubber on 2018/5/10.
 * <p>
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
        // List list = SearchList(ShopUser.class);

        ShopUser shopUser = new ShopUser();
        shopUser.setUsername("zoro");
        List list = SearchListByObject(shopUser);

        System.out.println(Arrays.toString(list.toArray()));
    }

    private static List<?> SearchListByObject(Object condition) {
        Class<?> clazz = condition.getClass();

        Connection conn = null;
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pbqshop?characterEncoding=UTF-8&rewriteBatchedStatements=true", "root", "chen2shuai");
            Table table = clazz.getAnnotation(Table.class);
            String sql = "select * from " + table.name();
            StringBuffer sqlbuf = new StringBuffer(sql)
                    .append(" where 1=1 ");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(condition);
                if (null != value && !StringUtils.equals(value.toString(),"0")) {
                    Class<?> type = field.getType();
                    if(String.class == type){
                        sqlbuf.append(" and ").append(field.getName()).append(" ='").append(value).append("'");
                    }else {
                        sqlbuf.append(" and ").append(field.getName()).append(" = ").append(value);
                    }
                }
            }
            System.out.println("sqlbuf ==> " + sqlbuf);
            PreparedStatement ps = conn.prepareStatement(sqlbuf.toString());
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取查询列数量
            int columnCount = rsmd.getColumnCount();
            selectCommon(clazz, list, rs, rsmd, columnCount);

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    private static void selectCommon(Class<?> clazz, List<Object> list, ResultSet rs, ResultSetMetaData rsmd, int columnCount) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        while (rs.next()) {
            // ---------------ORM开始----------
            Object instance = clazz.newInstance();

            for (int i = 0; i < columnCount; i++) {
                String columnName = rsmd.getColumnName(i + 1);
                //获得类中私有属性
                Field field = clazz.getDeclaredField(columnName);
                field.setAccessible(true);

                Object fieldType = field.getType();
                packageInstance(rs, instance, columnName, field, fieldType);
            }

            // ---------------ORM结束----------
            list.add(instance);
        }
    }

    private static List<?> SearchList(Class<?> clazz) {
        Connection conn = null;
        List<Object> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pbqshop?characterEncoding=UTF-8&rewriteBatchedStatements=true", "root", "chen2shuai");
            Table table = clazz.getAnnotation(Table.class);

            PreparedStatement ps = conn.prepareStatement("select * from " + table.name());
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取查询列数量
            int columnCount = rsmd.getColumnCount();
            selectCommon(clazz, list, rs, rsmd, columnCount);

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
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
        /*if(fieldType == int.class){
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
        }*/
        //TODO 还有很多类型需要转化

        //各自的厂商实现自己的链接
        //MySQL为例,以下类型Java语言中是不存在的
        //bigint ，由开发厂商自动就映射好了
        //varchar
        //int
        field.set(instance, rs.getObject(columnName));
    }
}
