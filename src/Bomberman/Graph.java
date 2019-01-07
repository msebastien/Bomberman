package Bomberman;

public class Graph {
    int size;
    int[][] adjacencyMatrix;

    Graph(int size){
        this.size = size;
        adjacencyMatrix = new int[size][size];
    }

    // Add an edge between a src and destination node
    void addEdge(int src, int dest){
        adjacencyMatrix[src-1][dest-1] = 1;
    }

    void removeEdge(int src, int dest){
        adjacencyMatrix[src-1][dest-1] = 0;
    }

    void print(){
        for(int i = 0; i < size; i++){ // Columns
            for(int j=0; j < size; j++){ // Rows
                System.out.print(adjacencyMatrix[i][j] + " "); // Print one row
                System.out.println();
            }
        }
    }


}
