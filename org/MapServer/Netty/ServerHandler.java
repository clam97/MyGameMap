package org.MapServer.Netty;


import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
import org.myproject.*;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;


//实现对网络数据的读写
public class
ServerHandler extends ChannelInboundHandlerAdapter { //回调类

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {//读数据时会调用该方法
        HttpPostRequestDecoder decoder1 = null;
        DefaultFullHttpResponse response1 = null;
        FullHttpRequest fullHR = (FullHttpRequest) msg;
//        System.out.println(fullHR);
//        System.out.println(fullHR);
//        ByteBuf sb = (ByteBuf) fullHR.content();

        Charset charset = Charset.forName("utf-8");
        String url = fullHR.uri();
        System.out.println(url + ":=============================================");

            if (url.equals("/")) {//首次访问返回首页
                try {
                    FileInputStream fin = new FileInputStream("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/login.html");
                    FileChannel fc = fin.getChannel();
                    ByteBuffer bf = ByteBuffer.allocate(2048);
                    String mess = "";
                    while ((fc.read(bf)) != -1) {
                        bf.flip();
                        CharsetDecoder decoder = charset.newDecoder();
                        CharBuffer cb = decoder.decode(bf);
//                System.out.println(cb);
                        bf.clear();
                        mess += String.valueOf(cb);
                    }
//                    System.out.println("===========================================" + mess);
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);
                    System.out.println(response.toString());
                } catch (IOException e) {
                    System.out.println("null");
                }

            }
        if (url.equals("/login.html")) {//处理登录页面
            try {
                FileInputStream fin = new FileInputStream("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/login.html");
                FileChannel fc = fin.getChannel();
                ByteBuffer bf = ByteBuffer.allocate(2048);
                String mess = "";
                while ((fc.read(bf)) != -1) {
                    bf.flip();
                    CharsetDecoder decoder = charset.newDecoder();
                    CharBuffer cb = decoder.decode(bf);
//                System.out.println(cb);
                    bf.clear();
                    mess += String.valueOf(cb);
                }
//                    System.out.println("===========================================" + mess);
                HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
                response.headers().set(CONTENT_TYPE, "text/html");
                response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                ctx.writeAndFlush(response);
                System.out.println(response.toString());
            } catch (IOException e) {
                System.out.println("null");
            }

        }

            if (url.equals("/register.html")){//处理注册页面
                try {
                    FileInputStream fin = new FileInputStream("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/register.html");
                    FileChannel fc = fin.getChannel();
                    ByteBuffer bf = ByteBuffer.allocate(4080);
                    String mess = "";
                    while ((fc.read(bf)) != -1) {
                        bf.flip();
                        CharsetDecoder decoder = charset.newDecoder();
                        CharBuffer cb = decoder.decode(bf);
//                System.out.println(cb);
                        bf.clear();
                        mess += String.valueOf(cb);
                    }
//                    System.out.println("===========================================" + mess);
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);
                    System.out.println(response.toString());
                } catch (IOException e) {
                    System.out.println("null");
                }
            }

            if (url.equals("/search.html")){//搜索页面


                System.out.println("//////////////////////////sssssssssssss");
                try {
                    FileInputStream fin = new FileInputStream("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/search.html");
                    FileChannel fc = fin.getChannel();
                    ByteBuffer bf = ByteBuffer.allocate(2048);
                    String mess = "";
                    while ((fc.read(bf)) != -1) {
                        bf.flip();
                        CharsetDecoder decoder = charset.newDecoder();
                        CharBuffer cb = decoder.decode(bf);
//                System.out.println(cb);
                        bf.clear();
                        mess += String.valueOf(cb);
                    }
//                    System.out.println("===========================================" + mess);
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);
                    System.out.println(response.toString());
                } catch (IOException e) {
                    System.out.println("null");
                }
            }

            if (url.equals("/choose.html")){//选择模式页面
                try {
                    FileInputStream fin = new FileInputStream("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/choose.html");
                    FileChannel fc = fin.getChannel();
                    ByteBuffer bf = ByteBuffer.allocate(2048);
                    String mess = "";
                    while ((fc.read(bf)) != -1) {
                        bf.flip();
                        CharsetDecoder decoder = charset.newDecoder();
                        CharBuffer cb = decoder.decode(bf);
//                System.out.println(cb);
                        bf.clear();
                        mess += String.valueOf(cb);
                    }
//                    System.out.println("===========================================" + mess);
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);
                    System.out.println(response.toString());
                } catch (IOException e) {
                    System.out.println("null");
                }

            }
            if (url.equals("/nvip")){//普通用户登录
                int pvip=0;
                String pname;
                String passwd;
                List<InterfaceHttpData> parmlist = new ArrayList();
                decoder1 = new HttpPostRequestDecoder(fullHR);
                InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
                parmlist = httpReq.getBodyHttpDatas();
                System.out.println(parmlist);

                String[] a=parmlist.get(0).toString().split("=");
                pname=a[1];

                String[] b=parmlist.get(1).toString().split("=");
                passwd=b[1];

                System.out.println(pname+" "+passwd);
                User user=new User();
                user.setQQ_EMAIL(pname);
                user.setPASSWORD(passwd);
                user.setVIP(pvip);

                System.out.println("----------------------------------------------------------------");
                System.out.println("----------------------------------------------------------------");
                boolean isRegis = Login.doLogin(user);
                System.out.println("----------------------------------------------------------------");
                System.out.println("----------------------------------------------------------------");




                if(isRegis){
                    String revive="0K";
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(revive.getBytes()));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);
                }else{
                    String revive="NO";
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, NOT_FOUND, Unpooled.wrappedBuffer(revive.getBytes()));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);
                }


            }
                if (url.equals("/vip")){//VIP用户登录
                    int vvip=1;
                    String vname;
                    String vpasswd;
                    List<InterfaceHttpData> parmlist = new ArrayList();
                    decoder1 = new HttpPostRequestDecoder(fullHR);
                    InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
                    parmlist = httpReq.getBodyHttpDatas();
                    System.out.println(parmlist);

                    String[] a=parmlist.get(0).toString().split("=");
                    vname=a[1];

                    String[] b=parmlist.get(1).toString().split("=");
                    vpasswd=b[1];

                    System.out.println(vname+" "+vpasswd);
                    User user=new User();
                    user.setQQ_EMAIL(vname);
                    user.setPASSWORD(vpasswd);
                    user.setVIP(vvip);
                    Login.doLogin(user);




                    String revive1="OK";
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(revive1.getBytes()));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);

                }
                if (url.equals("/code")){
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    List<InterfaceHttpData> parmlist = new ArrayList();
                    decoder1 = new HttpPostRequestDecoder(fullHR);
                    InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
                    parmlist = httpReq.getBodyHttpDatas();
                    System.out.println(parmlist);
                    String[] a=parmlist.get(0).toString().split("=");

                    System.out.println(a[1]);
                    System.out.println("**********************************************************************************");


                    //传一个User进来，给他发验证码
                    User user = new User();
                    user.setQQ_EMAIL(a[1]);


                    SendemailUtil.doSend(user);

                }


                if (url.equals("/pregister")){//普通用户注册
                    String pname=null;
                    String passwd=null;
                    String pcode=null;

                    List<InterfaceHttpData> parmlist = new ArrayList();
                    decoder1 = new HttpPostRequestDecoder(fullHR);
                    InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
                    parmlist = httpReq.getBodyHttpDatas();
                    System.out.println(parmlist);

                    String[] a=parmlist.get(0).toString().split("=");
                    pname=a[1];

                    String[] b=parmlist.get(1).toString().split("=");
                    passwd=b[1];

                    String[] c=parmlist.get(2).toString().split("=");
                    pcode=c[1];

                    int pcode1=Integer.parseInt(pcode);

                    System.out.println(pname+" "+passwd+" "+pcode);
                    User user=new User();
                    user.setQQ_EMAIL(pname);
                    user.setPASSWORD(passwd);
                    user.setVIP(0);

                    Register.doRegister(user,pcode1);








                    String revive="0K";
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(revive.getBytes()));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);
                }

                if (url.equals("/vregister")){//VIP用户注册
                    String vname=null;
                    String vasswd=null;
                    String vcode=null;
                    int vcode1=Integer.parseInt(vcode);
                    List<InterfaceHttpData> parmlist = new ArrayList();
                    decoder1 = new HttpPostRequestDecoder(fullHR);
                    InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
                    parmlist = httpReq.getBodyHttpDatas();
                    System.out.println(parmlist);

                    String[] a=parmlist.get(0).toString().split("=");
                    vname=a[1];

                    String[] b=parmlist.get(1).toString().split("=");
                    vasswd=b[1];

                    String[] c=parmlist.get(2).toString().split("=");
                    vcode=c[1];

                    System.out.println(vname+" "+vasswd+" "+vcode);
                    User user=new User();
                    user.setQQ_EMAIL(vname);
                    user.setPASSWORD(vasswd);
                    user.setVIP(1);
                    Register.doRegister(user,vcode1);



                    String revive="0K";
                    HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(revive.getBytes()));
                    response.headers().set(CONTENT_TYPE, "text/html");
                    response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                    ctx.writeAndFlush(response);

                }

            else if (url.equals("/assets/bg1.jpg")) {//当请求assets下的图片
                System.out.println();
                String mess = "";
                byte[] imgbyte;

                FileInputStream fin = new FileInputStream("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/assets/bg1.jpg");
                int img_size = fin.available();
//                    System.out.println(img_size);
                imgbyte = new byte[img_size];
                fin.read(imgbyte);
                HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(imgbyte));
                response.headers().set(CONTENT_TYPE, "image/*");
                response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                ctx.writeAndFlush(response);

            }

           else {
                ctx.fireChannelRead(msg);
        }
    }


//        @Override
//        public void exceptionCaught (ChannelHandlerContext ctx, Throwable cause) throws Exception {
//            super.exceptionCaught(ctx, cause);
//            Channel channel = ctx.channel();
//            //……
//            if (channel.isActive()) ctx.close();
//        }
    }

