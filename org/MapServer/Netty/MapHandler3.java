//package org.MapServer.Netty;
//
//import com.alibaba.fastjson.JSONObject;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.handler.codec.http.DefaultFullHttpResponse;
//import io.netty.handler.codec.http.FullHttpRequest;
//import io.netty.handler.codec.http.HttpResponse;
//import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
//import io.netty.handler.codec.http.multipart.InterfaceHttpData;
//import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
//import org.Man;
//import org.Monster;
//import org.almShortest.Creature;
//import org.almShortest.Node;
//import org.almShortest.BuildingsSearch;
//
//import java.nio.charset.Charset;
//import java.util.*;
//
//import static io.netty.handler.codec.http.HttpHeaderNames.*;
//import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS;
//import static io.netty.handler.codec.http.HttpResponseStatus.OK;
//import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
//
//public class MapHandler3 extends ChannelInboundHandlerAdapter {//导航地图
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("MapHandler3");
//        HttpPostRequestDecoder decoder1;
//        DefaultFullHttpResponse response1=null;
//        FullHttpRequest fullHR = (FullHttpRequest) msg;
//        Charset charset = Charset.forName("utf-8");
//        String url = fullHR.uri();
//        if (url.equals("/MapHandler3")){
//
//        }
//        if (url.equals("/inter1")){
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
//            };
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
////
//
//            String jsonX = new JSONObject().toJSONString(sx1);
//            String jsonY = new JSONObject().toJSONString(sy1);
//
//            String listjson="{\""+"x\""+":"+jsonX+","+"\"y\""+":"+jsonY+"}";
//            System.out.println(listjson);
//
//
//            System.out.println(man.getHealth());
//
//
//            HttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(listjson.getBytes()));
//            response.headers().set(CONTENT_TYPE, "text/html");
//            response.headers().setInt(CONTENT_LENGTH, ((DefaultFullHttpResponse) response).content().readableBytes());
//            response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN,"*");
//            response.headers().set(ACCESS_CONTROL_ALLOW_METHODS,"POST");
//            response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS,"x-requested-with,content-type");
//            ctx.writeAndFlush(response);
//        }
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
//    }
//}
