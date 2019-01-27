package org.myproject;

import java.sql.*;

public class LoginUtils {
    public static boolean login(User user){
        Connection conn=null;
        Statement ps=null;
        ResultSet rs=null;
        try {
            String sql="select QQ_EMAIL FROM consumer WHERE QQ_EMAIL='"+user.getQQ_EMAIL()+"';";
             conn=JdbcUtils_DBCP.getConnection();
             ps=conn.createStatement();
             rs=ps.executeQuery(sql);
            if(rs.next()){
                //可以登录了
                return true;
            }else {
                //没有找到用户，不能登陆
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //不能登陆
            return false;
        }finally {
            JdbcUtils_DBCP.release(conn,ps);
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
