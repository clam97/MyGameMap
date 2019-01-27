package org.almShortest;

import handler.Handler;
import role.Man;
import role.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Search {//游戏寻路算法

    HashMap<Integer, Integer> map = new HashMap<>();
    private HashMap<Integer, Integer> pos;
    private int[][] mapInfo;
    private int[][] mapCopy;
    private char[][] trace;
    //    private ArrayList<Node> nodes;
    private Node startPoint;
    private Node finishPoint;
    private HashMap<Integer, Monster> monsterHashMap = new HashMap<Integer, Monster>();
    private Man man;

//    public BuildingsSearch(int[][] map, Node startPoint, Node finishPoint) {
//    }


    public HashMap<Integer, Integer> getPos() {
        return pos;
    }

    public Man getMan() {
        return man;
    }

    public void setPos(HashMap<Integer, Integer> pos) {
        this.pos = pos;
    }

    public int[][] getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(int[][] mapInfo) {
        this.mapInfo = mapInfo;
    }

    public char[][] getTrace() {
        return trace;
    }

    public void setTrace(char[][] trace) {
        this.trace = trace;
    }

    public Node getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Node startPoint) {
        this.startPoint = startPoint;
    }

    public Node getFinishPoint() {
        return finishPoint;
    }

    public void setFinishPoint(Node finishPoint) {
        this.finishPoint = finishPoint;
    }


    public Search(int[][] mapInfo, Node startPoint, Node finishPoint, Monster[] monsters, Man man) {
        this.man = man;
        this.mapInfo = mapInfo;
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        this.trace = new char[mapInfo.length][mapInfo[0].length];

        for (int i = 0; i < monsters.length; i++) {
            System.out.println(monsters[i].getX()+" "+monsters[i].getY()+"--------------------------");
            monsters[i].setId(i + 2);
            monsterHashMap.put(i + 2, monsters[i]);
            mapInfo[monsters[i].getY()][monsters[i].getX()] = i + 2;
        }
        mapCopy = mapInfo;


        //可注释----------------------------------------------------------------
        trace[startPoint.getX()][startPoint.getY()] = '*';
        trace[finishPoint.getX()][finishPoint.getY()] = '*';
        //可注释----------------------------------------------------------------

    }

    public int manHattanDist(int x, int y) {
        return abs(x - finishPoint.getX()) + abs(y - finishPoint.getY());
    }


    public ArrayList<int[]> aStar() {

//        System.out.println("startpoint: "+startPoint.getX()+"   "+startPoint.getY());
//        System.out.println("endpoint: "+finishPoint.getX()+"   "+finishPoint.getY());

        TreeMap<Double, Node> nodes = new TreeMap<>();
        ArrayList<int[]> map = new ArrayList<>();
        int flag = 0;
        //可注释----------------------------------------------------------------

        for (int i = 0; i < trace.length; i++) {
            for (int j = 0; j < trace[0].length; j++) {
                trace[i][j] = '!';
            }
        }
        //可注释----------------------------------------------------------------

        if (mapInfo[finishPoint.getX()][finishPoint.getY()] == 0)return null;
        while (true) {
            if (startPoint.getY() > 0) {
                if (mapInfo[startPoint.getX()][startPoint.getY() - 1] != 0) {
                    Node node = new Node(startPoint.getX(), startPoint.getY() - 1);
                    node.setDist(manHattanDist(startPoint.getX(), startPoint.getY() - 1) + 1);
                    nodes.put(node.getDist(), node);
                }
            }

            if (startPoint.getY() < mapInfo[0].length - 1) {
                if (mapInfo[startPoint.getX()][startPoint.getY() + 1] != 0) {
                    Node node = new Node(startPoint.getX(), startPoint.getY() + 1);
                    node.setDist(manHattanDist(startPoint.getX(), startPoint.getY() + 1) + 1);
                    nodes.put(node.getDist(), node);

                }
            }

            if (startPoint.getX() > 0) {
                if (mapInfo[startPoint.getX() - 1][startPoint.getY()] != 0) {
                    Node node = new Node(startPoint.getX() - 1, startPoint.getY());
                    node.setDist(manHattanDist(startPoint.getX() - 1, startPoint.getY()) + 1);
                    nodes.put(node.getDist(), node);
                }
            }

            if (startPoint.getX() < mapInfo.length - 1) {
                if (mapInfo[startPoint.getX() + 1][startPoint.getY()] != 0) {
                    Node node = new Node(startPoint.getX() + 1, startPoint.getY());
                    node.setDist(manHattanDist(startPoint.getX() + 1, startPoint.getY()) + 1);
                    nodes.put(node.getDist(), node);
                }
            }


            if (startPoint.getX() > 0 && startPoint.getY() > 0) {
                if (mapInfo[startPoint.getX() - 1][startPoint.getY() - 1] != 0) {
                    Node node = new Node(startPoint.getX() - 1, startPoint.getY() - 1);
                    node.setDist(manHattanDist(startPoint.getX() - 1, startPoint.getY() - 1) + sqrt(2));
                    nodes.put(node.getDist(), node);
                }
            }

            if (startPoint.getX() < mapInfo.length - 1 && startPoint.getY() < mapInfo[0].length - 1) {
                if (mapInfo[startPoint.getX() + 1][startPoint.getY() + 1] != 0) {
                    Node node = new Node(startPoint.getX() + 1, startPoint.getY() + 1);
                    node.setDist(manHattanDist(startPoint.getX() + 1, startPoint.getY() + 1) + sqrt(2));
                    nodes.put(node.getDist(), node);
                }
            }

            if (startPoint.getX() > 0 && startPoint.getY() < mapInfo[0].length - 1) {
                if (mapInfo[startPoint.getX() - 1][startPoint.getY() + 1] != 0) {
                    Node node = new Node(startPoint.getX() - 1, startPoint.getY() + 1);
                    node.setDist(manHattanDist(startPoint.getX() - 1, startPoint.getY() + 1) + sqrt(2));
                    nodes.put(node.getDist(), node);

                }
            }

            if (startPoint.getX() < mapInfo.length - 1 && startPoint.getY() > 0) {
                if (mapInfo[startPoint.getX() + 1][startPoint.getY() - 1] != 0) {
                    Node node = new Node(startPoint.getX() + 1, startPoint.getY() - 1);
                    node.setDist(manHattanDist(startPoint.getX() + 1, startPoint.getY() - 1) + sqrt(2));
                    nodes.put(node.getDist(), node);
                }
            }


            for (Double key : nodes.keySet()) {
                int corX = nodes.get(key).getX();
                int corY = nodes.get(key).getY();
                if (corX == finishPoint.getX() & corY == finishPoint.getY()) {
                    flag = 1;
                    break;
                }
                if (mapInfo[corX][corY] > 1) {
                    if (!Handler.isFight(man, monsterHashMap.get(mapInfo[corX][corY]))) {
                        nodes.put(key,null);
                    }
                }
            }

            for (int i = 0; i<nodes.size(); i++){
                if (nodes.get(nodes.firstKey()) == null) nodes.remove(nodes.firstKey());
            }




            if (flag == 1) {
                int[] finalMap = {finishPoint.getX(),finishPoint.getY()};
                map.add(finalMap);
                break;
            }

            int nextX = nodes.get(nodes.firstKey()).getX();
            int nextY = nodes.get(nodes.firstKey()).getY();
            System.out.println(nextX + " " + nextY);

            if (mapInfo[nextX][nextY] > 1){
                Handler.reduce(man,monsterHashMap.get(mapInfo[nextX][nextY]));
                System.out.println(man.getHealth()+"==================================");
            }


            //可注释----------------------------------------------------------------
            trace[nextX][nextY] = '#';
            //可注释----------------------------------------------------------------

            mapInfo[nextX][nextY] = 0;
            startPoint.setX(nextX);
            startPoint.setY(nextY);

            int[] cor = {nextX,nextY};
            map.add(cor);

            nodes.clear();
        }
        mapInfo = mapCopy;
//        System.out.println("startpointtttt: "+startPoint.getX()+"   "+startPoint.getY());
//        System.out.println("endpointtttttt: "+finishPoint.getX()+"   "+finishPoint.getY());

        for (int[] k:map){
            System.out.println(k[0]+"---"+k[1]);
        }


        return map;
    }

    public boolean fight(Man man, Monster monster) {
        if (Handler.isFight(man, monster)) {
//            Handler.reduce(man, monster);
            return true;
        } else return false;
    }
}

