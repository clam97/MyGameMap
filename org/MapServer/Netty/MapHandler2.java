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
import org.Man;
import org.Monster;
import org.almShortest.Creature;
import org.almShortest.DeSearch;
import org.almShortest.Node;
import org.almShortest.Search;
import org.unit.*;

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

public class MapHandler2 extends ChannelInboundHandlerAdapter {//百度地图
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpPostRequestDecoder decoder1;
        DefaultFullHttpResponse response1=null;
        FullHttpRequest fullHR = (FullHttpRequest) msg;
        Charset charset = Charset.forName("utf-8");
        String url = fullHR.uri();

        if (url.equals("/navigation1.html")){
            System.out.println(url+"sssaaaaaaaaaaa");
            FileInputStream fin = new FileInputStream("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/navigation1.html");
            FileChannel fc = fin.getChannel();

            ByteBuffer bf = ByteBuffer.allocate(8000);
            String mess = "";
            while ((fc.read(bf)) != -1) {
                bf.flip();
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer cb = decoder.decode(bf);
                bf.clear();
                mess += String.valueOf(cb);
            }
//           q
            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            ctx.writeAndFlush(response);
        }
        if (url.equals("/home/wwj/IdeaProjects/MyGameMap/web/Find_Road/assets/roadmap.json")){
            System.out.println(url+"111111111111111111");
            FileInputStream fin = new FileInputStream(url);
            FileChannel fc = fin.getChannel();

            ByteBuffer bf = ByteBuffer.allocate(5000);
            String mess = "";
            while ((fc.read(bf)) != -1) {
                bf.flip();
                CharsetDecoder decoder = charset.newDecoder();
                CharBuffer cb = decoder.decode(bf);
                bf.clear();
                mess += String.valueOf(cb);
            }
            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(mess.getBytes("utf-8")));
            response.headers().set(CONTENT_TYPE, "text/html");
            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
            ctx.writeAndFlush(response);
        }
        if (url.equals("/where")){
            Node finishNode=null;
            List<InterfaceHttpData> parmlist = new ArrayList();
            decoder1 = new HttpPostRequestDecoder(fullHR);
            InterfaceHttpPostRequestDecoder httpReq = decoder1.offer(fullHR);
            parmlist = httpReq.getBodyHttpDatas();
            System.out.println(parmlist);
            String[] e=parmlist.get(0).toString().split("=");
            String value=e[1];
            System.out.println(e[1]);

            Building fifCantBuilding = new Building();
            fifCantBuilding.setName("五餐");
            Floor cantFloor1 = new Floor();
            cantFloor1.setFloorNum(1);
            cantFloor1.setName("学生餐厅");
            cantFloor1.setRooms(null);
            Floor cantFloor2 = new Floor();
            cantFloor2.setFloorNum(2);
            cantFloor2.setName("教师餐厅");
            cantFloor2.setRooms(null);
            Floor[] floors = new Floor[2];
            floors[0] = cantFloor1;
            floors[1] = cantFloor2;
            fifCantBuilding.setFloors(floors);


            Building fourCantBuilding = new Building();
            fourCantBuilding.setName("四餐");
            Floor fourcantFloor1 = new Floor();
            fourcantFloor1.setFloorNum(1);
            fourcantFloor1.setName("传统餐厅");
            fourcantFloor1.setRooms(null);
            Floor fourcantFloor2 = new Floor();
            fourcantFloor2.setFloorNum(2);
            fourcantFloor2.setName("南国美味");
            fourcantFloor2.setRooms(null);
            Floor[] floors1 = new Floor[2];
            floors1[0] = fourcantFloor1;
            floors1[1] = fourcantFloor2;
            fourCantBuilding.setFloors(floors1);

            Building firCantBuilding = new Building();
            firCantBuilding.setName("四餐");
            Floor fircantFloor1 = new Floor();
            fircantFloor1.setFloorNum(1);
            fircantFloor1.setName("学生餐厅");
            fircantFloor1.setRooms(null);
            Floor fircantFloor2 = new Floor();
            fircantFloor2.setFloorNum(2);
            fircantFloor2.setName("教师餐厅");
            fircantFloor2.setRooms(null);
            Floor[] floors2 = new Floor[2];
            floors2[0] = fircantFloor1;
            floors2[1] = fircantFloor2;
            firCantBuilding.setFloors(floors2);

            Building aBuilding = new Building();
            aBuilding.setName("A楼");
            Floor aFloor1 = new Floor();
            Room[] rooms = new Room[1];
            rooms[0] = new Room();
            rooms[0].setRoomNum("1024");
            rooms[0].setFunction("信息学院院办");
            aFloor1.setRooms(rooms);
            aFloor1.setFloorNum(10);
            aFloor1.setName("十楼");
            Floor[] floors3 = new Floor[1];
            floors3[0] = aFloor1;
            aBuilding.setFloors(floors3);

            Building[] buildings = new Building[4];
            buildings[0] = fifCantBuilding;
            buildings[1] = fourCantBuilding;
            buildings[2] = firCantBuilding;
            buildings[3] = aBuilding;
            Location location = new Location();
            location.setName("天津职业技术师范大学");
            location.setBuildings(buildings);

            BuildingsSearch buildingsSearch = new BuildingsSearch();






            if (!value.equals("A楼") & !value.equals("B楼") & !value.equals("C楼") & !value.equals("老教") & !value.equals("阶级") & !value.equals("一餐") & !value.equals("四餐") & !value.equals("五餐") & !value.equals("逸夫楼") & !value.equals("机械楼") & !value.equals("图书馆")){
                value = buildingsSearch.funcSearch(value,location);
            }


            if (value.equals("A楼")){
                finishNode=new Node(8,22);
            }
            if (value.equals("B楼")){
                finishNode=new Node(5,20);
            }
            if (value.equals("C楼")){
                finishNode=new Node(5,24);
            }
            if (value.equals("老教")){
                finishNode=new Node(3,16);
            }
            if (value.equals("阶级")){
                finishNode=new Node(1,18);
            }
            if (value.equals("一餐")){
                finishNode=new Node(8,16);
            }
            if (value.equals("四餐")){
                finishNode=new Node(11,26);
            }
            if (value.equals("五餐")){
                finishNode=new Node(11,30);
            }
            if (value.equals("逸夫楼")){
                finishNode=new Node(5,4);
            }
            if (value.equals("机械楼")){
                finishNode=new Node(1,5);
            }
            if (value.equals("图书馆")){
                finishNode=new Node(11,6);
            }else{
                HashMap<String,Node> map = new HashMap<String,Node>();

            }
            Node startNode = new Node(0, 22);
            int [][] mapInfo={
                    {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,1,1,4,4,4,4,4,4,4,4,},
                    {2,2,2,2,1,1,1,2,2,3,3,3,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
                    {2,2,2,2,2,1,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,},
                    {2,2,2,2,2,1,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,1,1,1,1,1,2,2,2,2,2,2,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,},
                    {2,2,2,2,1,2,1,2,2,2,2,2,2,2,2,2,1,2,2,2,1,1,1,1,1,1,2,1,1,1,1,1,},
                    {2,2,2,1,1,1,1,1,2,2,2,2,2,2,2,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,},
                    {2,2,2,1,1,2,1,1,2,2,2,2,2,2,2,1,1,1,2,2,2,1,1,1,2,2,2,2,2,1,1,1,},
                    {2,2,2,1,1,1,1,1,2,2,2,2,2,2,2,1,1,1,2,2,2,2,1,1,4,1,1,1,1,1,1,1,},
                    {2,2,2,2,1,2,1,2,2,2,2,2,2,2,2,2,1,2,2,2,2,1,1,1,4,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,},
                    {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,1,1,1,1,2,2,2,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,3,3,3,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,3,3,3,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,3,3,3,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,4,1,1,1,1,1,1,1,3,3,2,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,2,2,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},

            };
            for (int i = 0; i < mapInfo.length; i++) {
                for (int j = 0; j < mapInfo[0].length; j++) {
                    if (mapInfo[i][j] != 1) mapInfo[i][j] = 0;
                }
            }

//            for (int i = 0; i < mapInfo.length; i++) {
//                for (int j = 0; j < mapInfo[0].length; j++) {
//                    System.out.print(mapInfo[i][j]+" ");
//                }
//                System.out.println();
//            }


            DeSearch deSearch=new DeSearch(mapInfo,startNode,finishNode);
            ArrayList<int[]> map2=deSearch.aStar();
            System.out.println(map2);

            List<Integer> sx=new LinkedList<>();
            List<Integer> sy=new LinkedList<>();
            for (int i = map2.size()-1; i>=0; i--){
                sx.add(map2.get(i)[0]);
                sy.add(map2.get(i)[1]);
            }
                System.out.println(sx);
                System.out.println(sy);

                String jsonX = new JSONObject().toJSONString(sx);
                String jsonY = new JSONObject().toJSONString(sy);

                String listjson = "{\"" + "x\"" + ":" + jsonX + "," + "\"y\"" + ":" + jsonY +"}";
                System.out.println(listjson);
                HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(listjson.getBytes()));
                response.headers().set(CONTENT_TYPE, "text/html");
                response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
                response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
                response.headers().set(ACCESS_CONTROL_ALLOW_METHODS, "POST");
                response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with,content-type");
                ctx.writeAndFlush(response);


                }
        else {
            ctx.fireChannelRead(msg);
        }
//        if (url.equals("/inter")){
//
//            List<InterfaceHttpData> parmlist=new ArrayList();
//            decoder1=new HttpPostRequestDecoder(fullHR);
//            InterfaceHttpPostRequestDecoder httpReq=decoder1.offer(fullHR);
//            parmlist=httpReq.getBodyHttpDatas();
////            System.out.println(parmlist);
//            String[] a=parmlist.get(0).toString().split("=");
//            int x1=Integer.parseInt(a[1]);
//
//            String[] b=parmlist.get(1).toString().split("=");
//            int y1=Integer.parseInt(b[1]);
//
//
//            HashMap<Integer, Integer> route = new HashMap<Integer, Integer>();
//
//            int [][]mapinfo={ //存放地图信息
//                };
//
//            for (int i = 0; i < mapinfo.length; i++){
//                for (int j = 0; j < mapinfo[0].length; j++) {
//                    if (mapinfo[i][j] != 1) mapinfo[i][j] = 0;
//                }
//            }
//
//            int x0=8;
//            int y0=5;
//
//            Node startnode = new Node(x0, y0);//存放起点
//            Node finshnode = new Node(x1, y1);//存放终点
//            x0=x1;
//            y0=y1;
//
//            Man man = new Man(200.0,150.0,500.0,200.0,100.0);//角色信息
//            Creature creature = Creature.getCreature();
//            creature.setMan(man);
//
//
//            Monster[] monsters = creature.getMonsters();
//
//            for (int i = 0; i<creature.getMonsters().length; i++){
//                System.out.println(monsters[i].getX()+" "+monsters[i].getY());
//                mapinfo[monsters[i].getX()][monsters[i].getY()] = monsters[i].getId();
//            }
//            BuildingsSearch search = new BuildingsSearch(mapinfo, startnode, finshnode, creature.getMonsters());
//            route = search.aStar(man);//得到坐标集
//            Stack<Integer> sx=new Stack<>();
//            Stack<Integer> sy=new Stack<>();
//
//            System.out.println(route);
//            System.out.println(route.size());
//            for (Integer i:route.keySet()) {
//                sx.push(i);
//                sy.push(route.get(i));
//            }
//            sx.push(x1);
//            sy.push(y1);
//
//            System.out.println(sx);
//            System.out.println("*****");
//            System.out.println(sy);
//
//            int lenx=sx.size();
//            List<Integer> sx1=new LinkedList<>();
//            List<Integer> sy1=new LinkedList<>();
//
//            for (int i = 0; i <lenx; i++) {
//
//                sx1.add(sx.pop());
//            }
//            for (int i = 0; i <lenx ; i++) {
//                sy1.add(sy.pop());
//            }
//
//            System.out.println(sx1);
//            System.out.println(sy1);
//
//            String jsonX = new JSONObject().toJSONString(sx1);
//            String jsonY = new JSONObject().toJSONString(sy1);
//
//            String listjson="{\""+"x\""+":"+jsonX+","+"\"y\""+":"+jsonY+"}";
//            System.out.println(listjson);
//
//            System.out.println(man.getHealth());
//
//
//
//
//            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(listjson.getBytes()));
//            response.headers().set(CONTENT_TYPE, "text/html");
//            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
//            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");
//            response.headers().set(ACCESS_CONTROL_ALLOW_METHODS,"POST");
//            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS,"x-requested-with,content-type");
//            ctx.writeAndFlush(response);
//
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
