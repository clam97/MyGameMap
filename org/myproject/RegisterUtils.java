package org.myproject;


import org.apache.log4j.Logger;

import java.sql.*;

public class RegisterUtils {
    //日志对象
   // private static Logger log=Logger.getLogger(RegisterUtils.class);
   //计算当前数据库有多少条数据
    public static int getIDCount(){
        int res=0;
        Connection conn=null;
        Statement statement=null;
        ResultSet rs=null;
        try {
             conn=JdbcUtils_DBCP.getConnection();
             statement=conn.createStatement();
            String sql="select count(ID)  from consumer;";
            rs=statement.executeQuery(sql);
            rs.next();
            res=rs.getInt(1);
            System.out.println("当前数据库有"+res+"条数据！");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils_DBCP.release(conn,statement);
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
return res;

    }
public static int register(User user){
      // log.info("注册者信息："+user.toString());
       Connection conn=null;
       PreparedStatement ps=null;
    try {
        conn=JdbcUtils_DBCP.getConnection();
        System.out.println("*******************************************************************************************************************");
        String sql="insert into consumer(ID,QQ_EMAIL,PASSWORD,VIP) values (?,?,?,?);";
        ps=conn.prepareStatement(sql);
        ps.setInt(1,getIDCount()+1);
        ps.setString(2,user.getQQ_EMAIL());
        ps.setString(3,user.getPASSWORD());
        ps.setInt(4,user.getVIP());
        ps.executeUpdate();
        return 1;     //注册成功
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;    //注册失败
    }finally {
        //释放资源
        JdbcUtils_DBCP.release(conn,ps);
    }

}





}
