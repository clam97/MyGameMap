package org.myproject;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils_DBCP {
    /**
          * 在java中，编写数据库连接池需实现java.sql.DataSource接口，每一种数据库连接池都是DataSource接口的实现
           * DBCP连接池就是java.sql.DataSource接口的一个具体实现
           */
    private static DataSource ds=null;
        //在静态代码块中创建数据库连接池
    static {
        try{
            //加载dbcpconfig.properties
            InputStream in=JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties prop=new Properties();
            prop.load(in);
             //创建数据源
            ds = BasicDataSourceFactory.createDataSource(prop);
            System.out.println("数据库连接池创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("数据库连接池创建失败！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接池创建失败！");
        }
    }
    //从数据源中获得数据库连接
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    //释放资源，包括connection数据库连接对象，负责执行sql命令的statement对象
    public static void release(Connection conn, Statement st){
if(st!=null){
            try {
                //关闭负责执行SQL命令的Statement对象
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        st=null;
}
if(conn!=null){
        try{
            //将Connection连接对象还给数据库连接池
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn=null;
}
    }
}
