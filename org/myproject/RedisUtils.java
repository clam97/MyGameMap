package org.myproject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RedisUtils {
    //Redis服务器IP
    private static String ADDR = "127.0.0.1";
    //Redis的端口号
    private static int PORT = 6379;
    //访问密码  未设置密码则为null
    private static String AUTH = null;
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
    //如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;
    private static int TIMEOUT = 10000;

    //初始化Redis连接池
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
            System.out.println("Redis连接池创建成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取Jedis实例
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //释放一条redis连接
    public static void returnResource(Jedis jedis){
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
    }
    //将QQ_EMAIL和验证码存入redis，并且设置key失效
    public static void save(User user,int code){
            if(jedisPool!=null){
                Jedis jedis=null;
                try {
                    jedis = RedisUtils.getJedis();
                    jedis.set(user.getQQ_EMAIL(), code + "");
                    jedis.expire(user.getQQ_EMAIL(), 60);
                    System.out.println("请查看qq邮箱，在60秒内填入验证码！");
                }catch (Exception e){
                    System.out.println("存入失败1！");
                }finally {
                    returnResource(jedis);
                }

            }else {
                System.out.println("存入失败2");
            }
    }



    public static int compare_and_register(User user,int value){
        if(jedisPool!=null){
            Jedis jedis=null;
            try{
                //拿一个jedis实例
               jedis=jedisPool.getResource();
               //判断redis里面有没有这个user的QQ_EMAIL
                if(jedis.exists(user.getQQ_EMAIL())){
                    try{
                        //获得这个QQ_EMAIL对应的验证码
                        String code=jedis.get(user.getQQ_EMAIL());
                        //判断传进来的验证码和redis里面的是否一样
                         if(value==Integer.parseInt(code)){
                             int i=RegisterUtils.register(user);
                             if(i==1){
                                 System.out.println("注册成功！");
                                 return 1;
                             }else {
                                 System.out.println("注册失败！");
                                 return 0;
                             }
                         }else {
                             System.out.println("您输入的验证码有误！");
                             return 0;
                         }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return 0;
                    }
                }else {
                    System.out.println("验证码已失效！");
                    return 0;
                }
            }catch (Exception e){
               e.printStackTrace();
               return 0;
            }finally {
                RedisUtils.returnResource(jedis);
            }
        }else {
            System.out.println("无法注册！");
            return 0;
        }
    }
}

