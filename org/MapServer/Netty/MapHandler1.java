package org.MapServer.Netty;


import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
import org.almShortest.Creature;
import org.almShortest.DeSearch;
import org.almShortest.Node;
import org.almShortest.Search;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.*;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static org.almShortest.Creature.creature;

public class MapHandler1 extends ChannelInboundHandlerAdapter {//游戏地图


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpPostRequestDecoder decoder1 = null;
        DefaultFullHttpResponse response1 = null;
        FullHttpRequest fullHR = (FullHttpRequest) msg;
        Charset charset = Charset.forName("utf-8");
        String url = fullHR.uri();

        if (url.equals("/game.html")) {//游戏地图页面
//            try {
            System.out.println(url + "sssssssssssssssss");
            FileInputStream fin = new FileInputStream("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/game.html");
            FileChannel fc = fin.getChannel();

            ByteBuffer bf = ByteBuffer.allocate(7000);
            String mess = "";
            while ((fc.read(bf)) != -1) {
                bf.flip();
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer cb = decoder.decode(bf);
                bf.clear();
                mess += String.valueOf(cb);
            }
//            System.out.println("===========================================" + mess);
            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            ctx.writeAndFlush(response);
            System.out.println("....................");
//            }catch (Exception e){
//                System.out.println("eeeeeeeeeeeeee");
//            }

        }
        if (url.equals("/create.html")){//创建地图的页面
            System.out.println(url + "sssssssssssssssss");
            FileInputStream fin = new FileInputStream("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/create.html");
            FileChannel fc = fin.getChannel();

            ByteBuffer bf = ByteBuffer.allocate(7000);
            String mess = "";
            while ((fc.read(bf)) != -1) {
                bf.flip();
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer cb = decoder.decode(bf);
                bf.clear();
                mess += String.valueOf(cb);
            }
//            System.out.println("===========================================" + mess);
            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            ctx.writeAndFlush(response);

        }
        if (url.contains("/js")) {//接收到路径包含JS的作相应处理
            System.out.println(url + "aaaaaaaaaaaaaaaaaaaaa");
            FileInputStream fin = new FileInputStream(url);
            FileChannel fc = fin.getChannel();

            ByteBuffer bf = ByteBuffer.allocate(10000);
            String mess = "";
            while ((fc.read(bf)) != -1) {
                bf.flip();
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer cb = decoder.decode(bf);
                bf.clear();
                mess += String.valueOf(cb);
            }
//            System.out.println("===========================================" + mess);
            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            ctx.writeAndFlush(response);
        }

        if (url.contains("png") || url.contains("jpg")) {//接收到png,jpg作相应处理
            System.out.println();
            String mess = "";
            byte[] imgbyte;
            FileInputStream fin = new FileInputStream("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/" + url);
            int img_size = fin.available();
//                    System.out.println(img_size);
            imgbyte = new byte[img_size];
            fin.read(imgbyte);
            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(imgbyte));
            response.headers().set(CONTENT_TYPE, "image/*");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            ctx.writeAndFlush(response);
        }
        if (url.equals("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/assets/worldmap.json")) {//请求的其中一张地图，可根据路径找到
            System.out.println(url + "qqqqqqqqqqqqqqqqqq");
            FileInputStream fin = new FileInputStream(url);
            FileChannel fc = fin.getChannel();

            ByteBuffer bf = ByteBuffer.allocate(3000);
            String mess = "";
            while ((fc.read(bf)) != -1) {
                bf.flip();
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer cb = decoder.decode(bf);
                bf.clear();
                mess += String.valueOf(cb);
            }
//            System.out.println("===========================================" + mess);
            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            ctx.writeAndFlush(response);
        }

        role.Monster[] monsters;
        if (url.equals("/value")) {
            System.out.println("接收怪物属性");
            monsters = new role.Monster[5];
            List<InterfaceHttpData> parmlist = new ArrayList();
            decoder1 = new HttpPostRequestDecoder(fullHR);
            InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
            parmlist = httpReq.getBodyHttpDatas();
            System.out.println(parmlist);
            double[] monstervalue = new double[5];
            for (int i = 0; i < 5; i++) {
                String a[] = parmlist.get(i).toString().split("=");
                double values = Double.parseDouble(a[1]);
                System.out.println(values);
                monstervalue[i] = values;
            }
            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            ctx.writeAndFlush(response);

            monsters[0] = new role.Monster(10, 11, monstervalue[0], monstervalue[1], monstervalue[2], monstervalue[3], monstervalue[4]);
            monsters[1] = new role.Monster(20, 9, monstervalue[0], monstervalue[1], monstervalue[2], monstervalue[3], monstervalue[4]);
            monsters[2] = new role.Monster(22, 7, monstervalue[0], monstervalue[1], monstervalue[2], monstervalue[3], monstervalue[4]);
            monsters[3] = new role.Monster(15, 6, monstervalue[0], monstervalue[1], monstervalue[2], monstervalue[3], monstervalue[4]);
            monsters[4] = new role.Monster(18, 22, monstervalue[0], monstervalue[1], monstervalue[2], monstervalue[3], monstervalue[4]);
            Creature creature = Creature.getCreature();
            creature.setMonsters(monsters);

        }
        role.Man man1;
        if (url.equals("/value1")) {
            System.out.println("接收角色属性");
            List<InterfaceHttpData> parmlist = new ArrayList();
            decoder1 = new HttpPostRequestDecoder(fullHR);
            InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
            parmlist = httpReq.getBodyHttpDatas();
            System.out.println(parmlist);
            double[] manvalue = new double[5];
            for (int i = 0; i < 5; i++) {
                String a[] = parmlist.get(i).toString().split("=");
                double values = Double.parseDouble(a[1]);
                System.out.println(values);
                manvalue[i] = values;
            }
            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            ctx.writeAndFlush(response);

            man1 = new role.Man(manvalue[0], manvalue[1], manvalue[2], manvalue[3], manvalue[4]);
            creature.setMan(man1);
        }

        Node startNode;


        Node finishNode;



        //-----------------------------------------------------------------------------------------
        if (url.equals("/A")){
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            List<InterfaceHttpData> parmlist = new ArrayList();
            decoder1 = new HttpPostRequestDecoder(fullHR);
            InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
            parmlist = httpReq.getBodyHttpDatas();
            System.out.println(parmlist);
            String[] a = parmlist.get(0).toString().split("=");
            int x1 = Integer.parseInt(a[1]);

            String[] b = parmlist.get(1).toString().split("=");
            int y1 = Integer.parseInt(b[1]);

            String[] c = parmlist.get(2).toString().split("=");
            int sx1 = Integer.parseInt(c[1]);

            String[] d = parmlist.get(3).toString().split("=");
            int sy1 = Integer.parseInt(d[1]);
            System.out.println("================================================================================================================");
            System.out.println(y1+","+x1);
            System.out.println(sy1+","+sx1);
            System.out.println("================================================================================================================");

            startNode = new Node(sy1,sx1);
            finishNode = new Node(y1, x1);
            int[][] mapinfo = { //存放地图信息
                    {4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 1, 1, 2, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 3, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4,},
                    {1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4,},
                    {1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 4, 4,},
                    {1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4,},
                    {1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
                    {1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
                    {1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
                    {1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1,},
                    {1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 4,},
                    {4, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 4, 4,},
                    {4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4,},
                    {4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},

            };

            for (int i = 0; i < mapinfo.length; i++) {
                for (int j = 0; j < mapinfo[0].length; j++) {
                    if (mapinfo[i][j] != 1) mapinfo[i][j] = 0;
                }
            }

            Creature creature = Creature.getCreature();
            for (int i = 0; i < creature.getMonsters().length; i++) {
//                System.out.println(creature.getMonsters()[i].getX() + " " + creature.getMonsters()[i].getY());
                mapinfo[creature.getMonsters()[i].getY()][creature.getMonsters()[i].getX()] = 0;
            }


            DeSearch desearch = new DeSearch(mapinfo, startNode, finishNode);
            ArrayList<int[]> map1=desearch.aStar();

            List<Integer> sx=new LinkedList<>();
            List<Integer> sy=new LinkedList<>();


            for (int i = map1.size()-1; i>=0; i--){
                sx.add(map1.get(i)[0]);
                sy.add(map1.get(i)[1]);
            }



            System.out.println(sx);
            System.out.println(sy);

            String jsonX = new JSONObject().toJSONString(sx);
            String jsonY = new JSONObject().toJSONString(sy);

            String listjson = "{\"" + "x\"" + ":" + jsonX + "," + "\"y\"" + ":" + jsonY  +"}";
//                String Sx1=sx.toString();
//                System.out.println(Sx1);
//                System.out.println();



//                System.out.println(route.size());
//            String listjson= JSON.toJSONString(route);
//                System.out.println(listjson);
//                System.out.println(man.getHealth());
//  route = search.aStar(man);//得到坐标集
//                int len=route.size();
////                int[] sx=new int[len];
////                int[] sy=new int[len];


            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(listjson.getBytes()));
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            response.headers().set(ACCESS_CONTROL_ALLOW_METHODS, "POST");
            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with,content-type");
            ctx.writeAndFlush(response);
        }

        if (url.equals("/B")){
            System.out.println("**************************************************************************************************");

            List<InterfaceHttpData> parmlist = new ArrayList();
            decoder1 = new HttpPostRequestDecoder(fullHR);
            InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
            parmlist = httpReq.getBodyHttpDatas();
            System.out.println(parmlist);
            String[] a = parmlist.get(0).toString().split("=");
            int x1 = Integer.parseInt(a[1]);

            String[] b = parmlist.get(1).toString().split("=");
            int y1 = Integer.parseInt(b[1]);

            String[] c = parmlist.get(2).toString().split("=");
            int sx1 = Integer.parseInt(c[1]);

            String[] d = parmlist.get(3).toString().split("=");
            int sy1 = Integer.parseInt(d[1]);
            System.out.println("================================================================================================================");
            System.out.println(y1+","+x1);
            System.out.println(sy1+","+sx1);
            System.out.println("================================================================================================================");

            startNode = new Node(sy1,sx1);
            finishNode = new Node(y1, x1);
            int[][] mapinfo = { //存放地图信息
                    {4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 1, 1, 2, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 3, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4,},
                    {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4,},
                    {1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4,},
                    {1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 4, 4,},
                    {1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4,},
                    {1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
                    {1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
                    {1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
                    {1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1,},
                    {1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 4,},
                    {4, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 4, 4,},
                    {4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4,},
                    {4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},

            };

            for (int i = 0; i < mapinfo.length; i++) {
                for (int j = 0; j < mapinfo[0].length; j++) {
                    if (mapinfo[i][j] != 1) mapinfo[i][j] = 0;
                }
            }

            Creature creature = Creature.getCreature();
            for (int i = 0; i < creature.getMonsters().length; i++) {
//                System.out.println(creature.getMonsters()[i].getX() + " " + creature.getMonsters()[i].getY());
                mapinfo[creature.getMonsters()[i].getY()][creature.getMonsters()[i].getX()] = creature.getMonsters()[i].getId();
            }


            Search search = new Search(mapinfo, startNode, finishNode, creature.getMonsters(), creature.getMan());
            ArrayList<int[]> map1=search.aStar();

            List<Integer> sx=new LinkedList<>();
            List<Integer> sy=new LinkedList<>();
            List<Double> hea=new LinkedList<>();

            for (int i = map1.size()-1; i>=0; i--){
                sx.add(map1.get(i)[0]);
                sy.add(map1.get(i)[1]);
            }


            double health = search.getMan().getHealth();
            hea.add(health);

            System.out.println(sx);
            System.out.println(sy);
            System.out.println(hea);

            String jsonX = new JSONObject().toJSONString(sx);
            String jsonY = new JSONObject().toJSONString(sy);
            String jsonHea = new JSONObject().toJSONString(hea);

            String listjson = "{\"" + "x\"" + ":" + jsonX + "," + "\"y\"" + ":" + jsonY + "," + "\"health\"" + ":" + jsonHea +"}";
//                String Sx1=sx.toString();
//                System.out.println(Sx1);
//                System.out.println();



//                System.out.println(route.size());
//            String listjson= JSON.toJSONString(route);
//                System.out.println(listjson);
//                System.out.println(man.getHealth());
//  route = search.aStar(man);//得到坐标集
//                int len=route.size();
////                int[] sx=new int[len];
////                int[] sy=new int[len];


            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(listjson.getBytes()));
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            response.headers().set(ACCESS_CONTROL_ALLOW_METHODS, "POST");
            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with,content-type");
            ctx.writeAndFlush(response);
        }
//        if (url.equals("/road")) {
////            System.out.println("接收及时坐标点");
//            List<InterfaceHttpData> parmlist = new ArrayList();
//            decoder1 = new HttpPostRequestDecoder(fullHR);
//            InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
//            parmlist = httpReq.getBodyHttpDatas();
//            System.out.println(parmlist);
//            String[] a = parmlist.get(0).toString().split("=");
//            int x1 = Integer.parseInt(a[1]);
//
//            String[] b = parmlist.get(1).toString().split("=");
//            int y1 = Integer.parseInt(b[1]);
//
//            String[] c = parmlist.get(2).toString().split("=");
//            int sx1 = Integer.parseInt(c[1]);
//
//            String[] d = parmlist.get(3).toString().split("=");
//            int sy1 = Integer.parseInt(d[1]);
//            System.out.println("================================================================================================================");
//            System.out.println(y1+","+x1);
//            System.out.println(sy1+","+sx1);
//            System.out.println("================================================================================================================");
//
//            startNode = new Node(sy1,sx1);
//            finishNode = new Node(y1, x1);
//            int[][] mapinfo = { //存放地图信息
//                    {4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {4, 4, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {4, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 1, 1, 2, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 3, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4,},
//                    {1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4,},
//                    {1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4,},
//                    {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4,},
//                    {1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4,},
//                    {1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 4, 4,},
//                    {1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4,},
//                    {1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
//                    {1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
//                    {1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
//                    {1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1,},
//                    {1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
//                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 4,},
//                    {4, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 4, 4,},
//                    {4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4,},
//                    {4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4,},
//                    {4, 4, 4, 4, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4,},
//                    {4, 4, 4, 4, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4,},
//                    {4, 4, 4, 4, 4, 4, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4,},
//                    {4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,},
//
//            };
//
//            for (int i = 0; i < mapinfo.length; i++) {
//                for (int j = 0; j < mapinfo[0].length; j++) {
//                    if (mapinfo[i][j] != 1) mapinfo[i][j] = 0;
//                }
//            }
//
//            Creature creature = Creature.getCreature();
//            for (int i = 0; i < creature.getMonsters().length; i++) {
////                System.out.println(creature.getMonsters()[i].getX() + " " + creature.getMonsters()[i].getY());
//                mapinfo[creature.getMonsters()[i].getY()][creature.getMonsters()[i].getX()] = creature.getMonsters()[i].getId();
//            }
//
//
//            BuildingsSearch search = new BuildingsSearch(mapinfo, startNode, finishNode, creature.getMonsters(), creature.getMan());
//            ArrayList<int[]> map1=search.aStar();
//
//            List<Integer> sx=new LinkedList<>();
//            List<Integer> sy=new LinkedList<>();
//            List<Double> hea=new LinkedList<>();
//
//            for (int i = map1.size()-1; i>=0; i--){
//                sx.add(map1.get(i)[0]);
//                sy.add(map1.get(i)[1]);
//            }
//
//
//            double health = search.getMan().getHealth();
//            hea.add(health);
//
//            System.out.println(sx);
//            System.out.println(sy);
//            System.out.println(hea);
//
//            String jsonX = new JSONObject().toJSONString(sx);
//            String jsonY = new JSONObject().toJSONString(sy);
//            String jsonHea = new JSONObject().toJSONString(hea);
//
//            String listjson = "{\"" + "x\"" + ":" + jsonX + "," + "\"y\"" + ":" + jsonY + "," + "\"health\"" + ":" + jsonHea +"}";
//
//
//            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(listjson.getBytes()));
//            response.headers().set(CONTENT_TYPE, "text/html");
//            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
//            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//            response.headers().set(ACCESS_CONTROL_ALLOW_METHODS, "POST");
//            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with,content-type");
//            ctx.writeAndFlush(response);
//
//
////                System.out.println(x1+","+y1);
//        }


          else {
            ctx.fireChannelRead(msg);
        }
    }

}
