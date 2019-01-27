package org.almShortest;

public class Node{
    private int x;
    private int y;
    private double Dist = 0;
    private Node leftNode;
    private Node rightNode;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDist() {
        return Dist;
    }

    public void setDist(double dist) {
        Dist = dist;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public int getY() {
        return y;
    }


}
