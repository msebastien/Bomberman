package Bomberman.Pathfinding;

import Bomberman.*;
import Bomberman.Map;

import java.awt.Point;
import java.util.*;

import static Bomberman.Animation.*;
//import java.util.Map;

public class Graph {
    private int mapSize;
    private int costBetweenNodes;
    private Node[][] tileGraph;
    private ArrayList<Node> pathList;
    private ArrayList<Node> evaluatedNodes;

    Graph(){
        pathList = new ArrayList<>();
        evaluatedNodes = new ArrayList<>();
        mapSize = Map.MAP_SIZE_X;
        tileGraph = new Node[mapSize][mapSize]; // Graph must be a square matrix (adjacency matrix)
        this.costBetweenNodes = 10;
    }

    public Node getNode(int x, int y) {
        return tileGraph[y][x];
    }

    public void reset(){
        tileGraph = new Node[mapSize][mapSize];
        pathList = new ArrayList<>();
        evaluatedNodes = new ArrayList<>();
    }

    public ArrayList<Node> getPath(){
        return pathList;
    }

    private void generateHeuristicValues(){

        for(int y = 0; y < mapSize; y++){
            for(int x = 0; x < mapSize; x++){
                // Create a node for each tile
                tileGraph[y][x] = new Node(new Point(y, x));

                // Checks if the tile is an obstacle
                if(Main.game.getMap().getTile(y, x).getTileType().getType() != OBSTACLE){
                    Point playerPos = Main.game.getMap().getPlayer().getPosInArrayMap();
                    tileGraph[y][x].setHeuristicValue( Math.abs(y - playerPos.y) + Math.abs(x - playerPos.x));
                }else{
                    tileGraph[y][x].setHeuristicValue(-1);
                }
            }
        }

    }

    public void generatePathToPlayer(Node startNode){
        generateHeuristicValues();

        // Reorder the nodes in the queue based on their fValue (Cost + Heuristic).
        // The head of the queue is the lowest fValue
        PriorityQueue<Node> discoveredNodes = new PriorityQueue<>(11, new Comparator<Object>() {
            @Override
            public int compare(Object n1, Object n2) {

                if( (((Node)n1).getHeuristicValue() + ((Node)n1).getCost()) < (((Node)n2).getHeuristicValue() + ((Node)n2).getCost()) ){
                    return 1;
                }else if( (((Node)n1).getHeuristicValue() + ((Node)n1).getCost()) == (((Node)n2).getHeuristicValue() + ((Node)n2).getCost()) ){
                    return 0;
                }else{
                    return -1;
                }
            }

        });

        // Insert the starting node in the queue of the discovered nodes (not yet evaluated)
        discoveredNodes.add(tileGraph[startNode.getCoord().y][startNode.getCoord().x]);

        while(!discoveredNodes.isEmpty()){
            // Retrieve and remove the first node of the queue
            Node n = discoveredNodes.poll();

            evaluatedNodes.add(n);

            Point playerPos = Main.game.getMap().getPlayer().getPosInArrayMap();
            // if the node matches with position of the player(destination), we can reconstruct the path
            if(n == tileGraph[playerPos.y][playerPos.x]){
                break;
            }

            // Check neighbour nodes (top, left, right, bottom)

            // Left node
            try {
                if (tileGraph[n.getCoord().x][n.getCoord().y - 1].getHeuristicValue() != -1
                        && !discoveredNodes.contains(tileGraph[n.getCoord().x][n.getCoord().y - 1])
                        && !evaluatedNodes.contains(tileGraph[n.getCoord().x][n.getCoord().y - 1])) {

                    // Compute Cost
                    int totalCost = n.getHeuristicValue() + n.getCost() + costBetweenNodes; // cost of previous node + cost of the move to the next node
                    tileGraph[n.getCoord().x][n.getCoord().y - 1].setCost(costBetweenNodes);
                    int cost = tileGraph[n.getCoord().x][n.getCoord().y - 1].getHeuristicValue() + totalCost;

                    int fValue = tileGraph[n.getCoord().x][n.getCoord().y - 1].getCost() + tileGraph[n.getCoord().x][n.getCoord().y - 1].getHeuristicValue();

                    if (fValue > cost || !discoveredNodes.contains(tileGraph[n.getCoord().x][n.getCoord().y - 1])) {
                        tileGraph[n.getCoord().x][n.getCoord().y - 1].setCost(tileGraph[n.getCoord().x][n.getCoord().y - 1].getCost());
                        tileGraph[n.getCoord().x][n.getCoord().y - 1].setHeuristicValue(tileGraph[n.getCoord().x][n.getCoord().y - 1].getHeuristicValue());

                        discoveredNodes.add(tileGraph[n.getCoord().x][n.getCoord().y - 1]);
                        tileGraph[n.getCoord().x][n.getCoord().y - 1].setParentNode(n);
                    }
                }
            } catch(IndexOutOfBoundsException e) {}
            // End left node

            // Right node
            try {
                if (tileGraph[n.getCoord().x][n.getCoord().y + 1].getHeuristicValue() != -1
                        && !discoveredNodes.contains(tileGraph[n.getCoord().x][n.getCoord().y + 1])
                        && !evaluatedNodes.contains(tileGraph[n.getCoord().x][n.getCoord().y + 1])) {

                    // Compute Cost
                    int totalCost = n.getHeuristicValue() + n.getCost() + costBetweenNodes; // Cost of previous node + Cost of the move to the next node
                    tileGraph[n.getCoord().x][n.getCoord().y + 1].setCost(costBetweenNodes);
                    int cost = tileGraph[n.getCoord().x][n.getCoord().y + 1].getHeuristicValue() + totalCost;

                    int fValue = tileGraph[n.getCoord().x][n.getCoord().y + 1].getCost() + tileGraph[n.getCoord().x][n.getCoord().y + 1].getHeuristicValue();
                    if (fValue > cost || !discoveredNodes.contains(tileGraph[n.getCoord().x][n.getCoord().y + 1])) {
                        tileGraph[n.getCoord().x][n.getCoord().y + 1].setCost(tileGraph[n.getCoord().x][n.getCoord().y + 1].getCost());
                        tileGraph[n.getCoord().x][n.getCoord().y + 1].setHeuristicValue(tileGraph[n.getCoord().x][n.getCoord().y + 1].getHeuristicValue());

                        discoveredNodes.add(tileGraph[n.getCoord().x][n.getCoord().y + 1]);
                        tileGraph[n.getCoord().x][n.getCoord().y + 1].setParentNode(n);
                    }
                }
            } catch(IndexOutOfBoundsException e) {}
            // End right node

            // Top node
            try {
                if (tileGraph[n.getCoord().x - 1][n.getCoord().y].getHeuristicValue() != -1
                        && !discoveredNodes.contains(tileGraph[n.getCoord().x - 1][n.getCoord().y])
                        && !evaluatedNodes.contains(tileGraph[n.getCoord().x - 1][n.getCoord().y])) {

                    // Compute Cost
                    int totalCost = n.getHeuristicValue() + n.getCost() + costBetweenNodes; // Cost of previous node + Cost of the move to the next node
                    tileGraph[n.getCoord().x - 1][n.getCoord().y].setCost(costBetweenNodes);
                    int cost = tileGraph[n.getCoord().x - 1][n.getCoord().y].getHeuristicValue() + totalCost;

                    int fValue = tileGraph[n.getCoord().x - 1][n.getCoord().y].getCost() + tileGraph[n.getCoord().x - 1][n.getCoord().y].getHeuristicValue();
                    if (fValue > cost || !discoveredNodes.contains(tileGraph[n.getCoord().x - 1][n.getCoord().y])) {
                        tileGraph[n.getCoord().x - 1][n.getCoord().y].setCost(tileGraph[n.getCoord().x - 1][n.getCoord().y].getCost());
                        tileGraph[n.getCoord().x - 1][n.getCoord().y].setHeuristicValue(tileGraph[n.getCoord().x - 1][n.getCoord().y].getHeuristicValue());

                        discoveredNodes.add(tileGraph[n.getCoord().x - 1][n.getCoord().y]);
                        tileGraph[n.getCoord().x - 1][n.getCoord().y].setParentNode(n);
                    }
                }
            } catch(IndexOutOfBoundsException e) {}
            // End top node

            // Bottom node
            try {
                if (tileGraph[n.getCoord().x + 1][n.getCoord().y].getHeuristicValue() != -1
                        && !discoveredNodes.contains(tileGraph[n.getCoord().x + 1][n.getCoord().y])
                        && !evaluatedNodes.contains(tileGraph[n.getCoord().x + 1][n.getCoord().y])) {

                    // Compute Cost
                    int totalCost = n.getHeuristicValue() + n.getCost() + costBetweenNodes; // Cost of previous node ( + Cost of the move to the next node (edge)
                    tileGraph[n.getCoord().x + 1][n.getCoord().y].setCost(costBetweenNodes);
                    int cost = tileGraph[n.getCoord().x + 1][n.getCoord().y].getHeuristicValue() + totalCost;

                    int fValue = tileGraph[n.getCoord().x + 1][n.getCoord().y].getCost() + tileGraph[n.getCoord().x + 1][n.getCoord().y].getHeuristicValue();
                    if (fValue > cost || !discoveredNodes.contains(tileGraph[n.getCoord().x + 1][n.getCoord().y])) {
                        tileGraph[n.getCoord().x + 1][n.getCoord().y].setCost(tileGraph[n.getCoord().x + 1][n.getCoord().y].getCost());
                        tileGraph[n.getCoord().x + 1][n.getCoord().y].setHeuristicValue(tileGraph[n.getCoord().x - 1][n.getCoord().y].getHeuristicValue());

                        discoveredNodes.add(tileGraph[n.getCoord().x + 1][n.getCoord().y]);
                        tileGraph[n.getCoord().x + 1][n.getCoord().y].setParentNode(n);
                    }
                }
            } catch(IndexOutOfBoundsException e) {}
            // End top node

        } // End while

        // Assigns the last evaluated node
        Node endNode = evaluatedNodes.get( evaluatedNodes.size() - 1 );

        // Check if endNode has a parent node. If it is not case, stops moving forward.
        // Stores each parent in the pathList
        while(endNode.getParentNode() != null){
            Node current = endNode;
            pathList.add(current);
            endNode = endNode.getParentNode();
        }

        pathList.add(startNode);

        // Clears the queue containing the discovered nodes, which are now useless as we have now the path
        discoveredNodes.clear();
    }


}
