package mhl.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

// 与数据库的工具类
public class JDBCUtilsByDruid {
    private static DataSource ds;
    // 实现事务
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static Connection connection;
    public static DataSource getDs() {
        return ds;
    }

    static {
        Properties properties = new Properties();
        try {

            properties.load(new FileInputStream("src/druid-config/druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //开启事务
    public static void startTransaction(){
        try {
            connection = getConnection();
            if (connection != null){
                connection.setAutoCommit(false);
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    //回滚
    public static void rollback(Savepoint savepoint){
        try {
            connection = getConnection();
            if (connection != null){
                connection.rollback(savepoint);
                connection.close();
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    //提交并复原
    public static void commitAndRelease(){
        try {
            connection = getConnection();
            if (connection != null){
                connection.commit();
                connection.setAutoCommit(true);

                threadLocal.remove();

            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() throws SQLException {
        connection = threadLocal.get();
        if(connection == null){
            connection = ds.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection)  {
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }catch (Exception e){
            throw new RuntimeException();
        }


    }

}
