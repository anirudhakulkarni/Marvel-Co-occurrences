/* function:average
=sum over all nodes of sum of all edges of a node*weights
=sum of all edges*weights/2 as each node is counted twice
*/
/* function:rank

*/
//imports
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
class node{
    private node prev;
    private node next;
    
    
    public node(){

    }
}
class Graph {
    private Map<String, Integer > map = new HashMap<>();
    
    void addEdge(long i){

    }
    
}

class Edge
{
    // Target vertex ID
    private int vertexId;
    private int weight;

    public Edge(int vertexId, int weight) {
        this.vertexId = vertexId;
        this.weight = weight;
    }

    public int getVertexId() {
        return vertexId;
    }

    public int getLength() {
        return weight;
    }
}
public class assignment4 {
    /**
     * Graph
     */
    


    public static void main(String[] args) throws Exception {
        String nodes_csv_loc = args[0];
        String edges_csv_loc = args[1];
        //String function = args[2];

        String line="";
        System.out.println("adsda");
        //input taking

        System.out.println("adsda");
        ///
        FileReader nodesF;
        BufferedReader br,br2;
        long num_lines=0;
        try {
            nodesF=new FileReader(nodes_csv_loc);
            LineNumberReader count = new LineNumberReader(new FileReader(nodes_csv_loc));
            count.skip(Long.MAX_VALUE);
            num_lines=1+count.getLineNumber();
            br=new BufferedReader(nodesF);
            br2=new BufferedReader(new FileReader(edges_csv_loc));
            
        
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Graph graph=new Graph();
        System.out.println(num_lines);
        int i=0;
        // while ((line=br.readLine())!=null) {
        //     String[] values=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        //     graph.addVertex(values[1],i);
        // }
        // while ((line=br2.readLine())!=null) {
        //     String[] values=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        //     graph.addEdge(values[0], values[1], values[2]);
        // }
        
    }
    
}