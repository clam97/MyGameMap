package org.MapServer;
//
//import org.apache.commons.httpclient.HttpConnectionManager;
//import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
//
//public class HttpPoolHandler {//建立连接池
//    private static String       DEFAULT_CHARSET     = "GBK";//设置编码支持格式
//
//    private int  defaultConnectionTimeout  =8000; //设置连接超时时间为8秒
//
//    private int defaulSoTime=8000; //设置回应超时时间
//
//    private int defaultMaxTotalConn = 500;//累积连接的数量
//
//
//    private HttpConnectionManager connectionManager; //建立HTTP连接管理器
//
//
//
//    private HttpPoolHandler(){
//        connectionManager=new MultiThreadedHttpConnectionManager();
//
//        connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxTotalConn);
//    }
//
//
//}
