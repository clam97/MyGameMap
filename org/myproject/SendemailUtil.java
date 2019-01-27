package org.myproject;

import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.Year;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

public class SendemailUtil {
    //生成验证码方法
    public static int genCode(){
//        Calendar c=Calendar.getInstance();
//        int year=c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int date = c.get(Calendar.DATE);
//        int hour = c.get(Calendar.HOUR_OF_DAY);
//        int minute = c.get(Calendar.MINUTE);
//        int second = c.get(Calendar.SECOND);
//        int i=(int)(Math.random()*10+5663+year+month+date+hour+minute+second);
//        System.out.println(i);
        Random random=new Random();
        int r=random.nextInt(10000);
//        System.out.println(r);
        return r;
    }
    public static int doSend(User user) {
        try {
            Properties properties = new Properties();
            //连接协议
            properties.put("mail.transport.protocol", "smtp");
            //主机名
            properties.put("mail.smtp.host", "smtp.qq.com");
            //端口号
            properties.put("mail.smtp.port", 465);
            properties.put("mail.smtp.auth", "true");
            //设置是否使用ssl安全连接，一般都用
            properties.put("mail.smtp.ssl.enable", "true");
            //设置是否显示debug信息，true会在控制台显示相关信息
            properties.put("mail.debug", "true");
            //得到会话对象
            Session session = Session.getInstance(properties);
            //得到邮件对象
            Message message = new MimeMessage(session);
            //设置发件人邮箱地址
            message.setFrom(new InternetAddress("2418173870@qq.com"));
            //设置收件人地址
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(user.getQQ_EMAIL())});
            //设置邮件标题
            message.setSubject("这是一封验证邮件！");
            //设置邮件内容
            int i = genCode();
            String code = i + "";
            message.setText(code);
            //同时把QQ_EMAIL和验证码在redis里存一份，有失效时间
            RedisUtils.save(user,i);
            //得到邮递员对象
            Transport transport = session.getTransport();
            //连接自己的邮箱账户
            transport.connect("2418173870@qq.com", "gbshycftfjuqecei");
            //发送邮件
            transport.sendMessage(message, message.getAllRecipients());
        }catch (MessagingException e){
            e.printStackTrace();
            System.out.println("发送失败！");
            return 0;      //失败
        }
        System.out.println("发送成功！");

        return 1;      //成功

    }


    }








