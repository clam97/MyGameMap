package org.myproject;


import org.apache.log4j.Logger;

public class Login {
 //private static Logger log = Logger.getLogger(Login.class);

 public static boolean doLogin(User user) {
  //log.info("this is User" + user.toString());
           boolean bl=LoginUtils.login(user);
          if (bl){
           System.out.println("可以登录");
           return true;
          }else{
           System.out.println("不能登录");
           return false;
          }
 }
}








