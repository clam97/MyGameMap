package org.myproject;

public class Register {
    public static int doRegister(User user,int code){
          int flag=RedisUtils.compare_and_register(user,code);
          if(flag==1){
              System.out.println("注册成功！");
              return 1;
          }else {
              System.out.println("注册失败！");
              return 0;
          }
    }
}
