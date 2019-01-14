package Bomberman.Pathfinding;

import java.awt.Point;

public class Node {
    private Point coord;
    private int heuristicValue; // Distance between start and end nodes
    private int cost; // Distance between start and this node
    private Node parent;

    public Node(Point p){
        coord = p;
    }


    public int getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Point getCoord() {
        return coord;
    }

    public void setParentNode(Node node){
        this.parent = node;
    }

    public Node getParentNode(){
        return this.parent;
    }
}
