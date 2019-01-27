package org.Redis;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class Myredis {
//
//        private static void save (Jedis jedis,int[][] map,int flag,int[][] mapresult,HashMap end){
//            String stringmap= JSON.toJSONString(map);              //把地图二维数组转成string
//            String stringmapresult=JSON.toJSONString(mapresult);//把中间路程二维数组转成string
//            String stringend=JSON.toJSONString(end);
//            String stringflag=String.valueOf(flag);                //把flag转成string（对应地图）
//            String stringflag1=String.valueOf("R"+flag);          //把Rflag转成string（对应算法生成二维数组）
//            String stringflag2=String.valueOf("E"+flag);         //把Eflag转成string（对应终点坐标）
//            jedis.set(stringflag,stringmap);                     //把地图存进redis
//            jedis.set(stringflag1,stringmapresult);              //把地图中间坐标存进redis
//            jedis.set(stringflag2,stringend);                    //把地图终点存进redis
//            jedis.hset("key",stringmap,stringend);          //把地图和终点坐标关联
//            jedis.hset("key1",stringend,stringmap);         //
//            flag++;
//        }
private static Jedis testRedis() {                                           //创建jedis对象，测试redis服务器是否启动
    Jedis jedis = new Jedis("localhost");
    System.out.println("redis已启动！");
    System.out.println("服务器正在运行" + jedis.ping());
               return jedis;                                                 //返回jedis对象
}

    private static void save(int[][] result,Jedis jedis,int[][] map,HashMap end){      //每次存取调用的函数
        HashMap<String,String> hashMap=new HashMap<>();                                //生成一个hashmap
        String stringmap= JSON.toJSONString(map);                                      //把地图转成json格式的字符串
        String stringmapresult=JSON.toJSONString(result);                              //把生成的路径转成json格式的字符串
        String stringend=JSON.toJSONString(end);                                       //把终点坐标转成json字符串
        hashMap.put(stringmap,stringend);                                              //把地图和终点坐标对应
        hashMap.put(stringend,stringmapresult);                                        //把终点坐标和生成路径对应
        jedis.hmset("hashset",hashMap);                                            //用hash表存这个hashmap
    }



    private static String handler(Jedis jedis,int[][] map,HashMap end){             //前端传给服务器地图和终点坐标，处理函数
        String stringmap= JSON.toJSONString(map);                                   //把地图转成json格式的字符串
        String stringend=JSON.toJSONString(end);                                     //把终点坐标转成json字符串
        if(jedis.scard("mapcollection")==0){                                   //先判断mapcollection集合里有没有地图
            System.out.println("集合没有地图，先保存");                                //没有，保存
            jedis.sadd("mapcollection",stringmap);
            return "0";                                                        //返回字符串0，服务器去算，算完了调用上面的save函数存到redis
        }
        else{                                                                  //有地图
            System.out.println("集合有地图");
            if(jedis.sismember("mapcollection",stringmap)){               //判断集合里面有没有这张地图
                System.out.println("集合有这张地图");
                if(jedis.hget("hashset",stringmap)==stringend){          //有，再判断这张地图有没有对应的终点坐标
                    String result=jedis.hget("hashset",stringend);         //有，把生成的中间路径拿出来给服务器
                    System.out.println("走过，返回给服务器"+result);
                     return jedis.hget("hashset",stringend);             //返回给服务器一个终点
                }else{
                    System.out.println("没走过，请回去算");
                    return "0";                                              //没走过，返回字符串0，回去算
                }
            }else {
                System.out.println("集合没有这张地图，请回去算");
                return "0";                                                   //没这张地图，返回字符串0，回去算
            }
        }
    }
}
