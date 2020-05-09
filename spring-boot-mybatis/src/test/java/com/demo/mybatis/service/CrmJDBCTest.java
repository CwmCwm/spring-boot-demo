package com.demo.mybatis.service;


import com.demo.mybatis.MybatisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;

@SpringBootTest(classes = MybatisApplication.class)
@RunWith(value = SpringRunner.class)
public class CrmJDBCTest {

    //JDBC连接数据库时的参数，其实就是 application.properties 中的值
    public final static String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://192.168.137.200:3306/crm?useUnicode=true&characterEncoding=utf-8";
    public final static String DB_USERNAME = "root";
    public final static String DB_PASSWORD = "root";

    //这里就是创建数据库连接，返回这个创建的数据库连接，这就是最原始的写法，这里没有数据库连接池
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName(DB_DRIVER_CLASS);
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return connection;
    }

    /**
     演示JDBC执行sql语句

     mysql命令行
     insert into crm1(crm1Id) values (100);
     * */
    @Test
    public void crm1Insert () throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        //preparedStatement 预解析sql语句，你看有 ? 问号吧
        PreparedStatement preparedStatement = connection.prepareStatement("insert into crm1(crm1Id) values (?)");
        //替代 preparedStatement 中的 ? 问号值
        preparedStatement.setLong(1, 100L);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }


    /**
     演示JDBC启用数据库事务，事务提交，事务回滚

     mysql命令行
     begin;
     insert into crm1(crm1Id) values (100);
     insert into crm2(crm2Id) values (200);
     commit;
     rollback;
     * */
    @Test
    public void crmTransaction () throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = connection.prepareStatement("insert into crm1(crm1Id) values (?)");
            preparedStatement1.setLong(1, 100L);
            preparedStatement1.executeUpdate();
            preparedStatement1.close();

            PreparedStatement preparedStatement2 = connection.prepareStatement("insert into crm2(crm2Id) values (?)");
            preparedStatement2.setLong(1, 200L);
            preparedStatement2.executeUpdate();
            preparedStatement2.close();

//            if (1==1) {
//               throw new RuntimeException("抛出异常看看会不会回滚");
//            }

            connection.commit();
        } catch (Exception exception) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    /**
     演示JDBC启用数据库事务，数据库保存点，我回忆了一下，基本没有过数据库保存点

     mysql命令行
     begin;
     insert into crm1(crm1Id) values (100);
     savepoint crm1Savepoint;
     insert into crm2(crm2Id) values (200);
     savepoint crm2Savepoint;
     rollback to crm1Savepoint;
     rollback to crm2Savepoint;
     commit;

     注意：使用数据库保存点，当回滚到对应的保存点后，要再执行一下 commit;  你试验一下
     * */
    @Test
    public void crmSavePoint () throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Savepoint crm1Savepoint = null;
        Savepoint crm2Savepoint = null;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement1 = connection.prepareStatement("insert into crm1(crm1Id) values (?)");
            preparedStatement1.setLong(1, 100L);
            preparedStatement1.executeUpdate();
            preparedStatement1.close();
            crm1Savepoint = connection.setSavepoint("crm1Savepoint");

            PreparedStatement preparedStatement2 = connection.prepareStatement("insert into crm2(crm2Id) values (?)");
            preparedStatement2.setLong(1, 200L);
            preparedStatement2.executeUpdate();
            preparedStatement2.close();
            crm2Savepoint = connection.setSavepoint("crm2Savepoint");

            if (1==1) {
               throw new RuntimeException("抛出异常看看会不会回滚");
            }

            connection.commit();
        } catch (Exception exception) {
            connection.rollback(crm1Savepoint);
            connection.commit();
        } finally {
            connection.close();
        }
    }

}
