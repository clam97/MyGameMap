package org.almShortest;

import handler.Handler;
import role.Man;
import role.Monster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class DeSearch {//模拟寻路算法

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


    public char[][] getTrace() {
        return trace;
    }

    public HashMap<Integer, Integer> getPos() {
        return pos;
    }

    public DeSearch(int[][] mapInfo, Node startPoint, Node finishPoint) {
        this.man = man;
        this.mapInfo = mapInfo;
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        this.trace = new char[mapInfo.length][mapInfo[0].length];

        //可注释----------------------------------------------------------------
        trace[startPoint.getX()][startPoint.getY()] = '*';
        trace[finishPoint.getX()][finishPoint.getY()] = '*';
        //可注释----------------------------------------------------------------

    }

    public int manHattanDist(int x, int y) {
        return abs(x - finishPoint.getX()) + abs(y - finishPoint.getY());
    }


    public ArrayList<int[]> aStar() {
        TreeMap<Double, Node> nodes = new TreeMap<>();
        ArrayList<int[]> arrayList=new ArrayList<>();
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
            }

            if (flag == 1) break;

            int nextX = nodes.get(nodes.firstKey()).getX();
            int nextY = nodes.get(nodes.firstKey()).getY();
            System.out.println(nextX + " " + nextY);

            //可注释----------------------------------------------------------------
            trace[nextX][nextY] = '#';
            //可注释----------------------------------------------------------------


            mapInfo[nextX][nextY] = 0;
            startPoint.setX(nextX);
            startPoint.setY(nextY);

            int[] cor = {nextX,nextY};
            arrayList.add(cor);

            nodes.clear();
        }
        int nextX1 = finishPoint.getX();
        int nextY1 = finishPoint.getY();
        int[] cors={nextX1,nextY1};
        arrayList.add(cors);
        mapInfo = mapCopy;
        return arrayList;
    }

    public boolean fight(Man man, Monster monster) {
        if (Handler.isFight(man, monster)) {
//            Handler.reduce(man, monster);
            return true;
        } else return false;
    }
}

