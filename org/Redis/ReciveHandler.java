package org.Redis;

import org.Monster;
import org.almShortest.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class ReciveHandler {
    private int [][] mapinfo;
    private Node startnode;
    private Node finshnode;
    private Monster[] monsters;
    private ArrayList<String> route;

    public ReciveHandler(int[][] mapinfo, Node startnode, Node finshnode, Monster[] monsters, HashMap<Integer, Integer> route){

    }
    public ReciveHandler(int [][] mapinfo, Node startnode, Node finshnode, Monster[] monsters, ArrayList<String> route){
                 this.mapinfo=mapinfo;
                 this.startnode=startnode;
                 this.finshnode=finshnode;
                 this.monsters=monsters;
                 this.route=route;
    }
}
