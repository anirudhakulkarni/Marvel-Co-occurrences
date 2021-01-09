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
class Graph<String> {
    private Map<String, LinkedList<String[]> > map = new HashMap<>();
    public void addVertex(String s) 
    { 
        map.put(s, new LinkedList<String[]>()); 
    } 
    // This function adds the edge 
    // between source to destination 
    public void addEdge(String source, 
                        String destination, 
                        String weight) 
    { 

        if (!map.containsKey(source)) 
            addVertex(source); 

        if (!map.containsKey(destination)) 
            addVertex(destination); 
        String[] edge= {destination,weight};
        map.get(source).add(edge); 
        edge=new String[] {source,weight};
        map.get(destination).add(edge);
    } 

    // This function gives the count of vertices 
    public void getVertexCount() 
    { 
        System.out.println("The graph has "
                        + map.keySet().size() 
                        + " vertex"); 
    } 

    // This function gives the count of edges 
    public void getEdgesCount(boolean bidirection) 
    { 
        int count = 0; 
        for (String v : map.keySet()) { 
            count += map.get(v).size(); 
        } 
        if (bidirection == true) { 
            count = count / 2; 
        } 
        System.out.println("The graph has "
                        + count 
                        + " edges."); 
    } 

    // This function gives whether 
    // a vertex is present or not. 
    public void hasVertex(String s) 
    { 
        if (map.containsKey(s)) { 
            System.out.println("The graph contains "
                            + s + " as a vertex."); 
        } 
        else { 
            System.out.println("The graph does not contain "
                            + s + " as a vertex."); 
        } 
    } 

    // This function gives whether an edge is present or not. 
    public void hasEdge(String s, String d) 
    { 
        if (map.get(s).contains(d)) { 
            System.out.println("The graph has an edge between "
                            + s + " and " + d + "."); 
        } 
        else { 
            System.out.println("The graph has no edge between "
                            + s + " and " + d + "."); 
        } 
    }
    public String ToString() 
    { 
        StringBuilder builder = new StringBuilder(); 
  
        for (String v : map.keySet()) { 
            builder.append(v.toString() + ": "); 
            for (String w : map.get(v)) { 
                builder.append(w.toString() + " "); 
            } 
            builder.append("\n"); 
        } 
  
        return builder.toString(); 
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
////////////////////////////////////////////////////////debug
        String line="";

        //input taking
        PrintStream o = new PrintStream(new File("A.txt")); 
        System.setOut(o);
        try {
            /*There are two columns, Id and label in nodes.csv but they have the same values. For the purpose of the assignment, you should use the label column as the node id and disregard the id column.*/
            //id is first value and label is second
            Graph<String> graph=new Graph<String>();
            BufferedReader br=new BufferedReader(new FileReader(nodes_csv_loc));
            while ((line=br.readLine())!=null) {
                String[] values=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                graph.addVertex(values[1]);
                // for(String index : values) {
                //     System.out.printf("%-50s", index);
                //    }
                //    System.out.println();
                //System.out.println(values[0].equals(values[1]));
                //System.out.println("id: "+values[0]+" -----------label: "+values[1]);
            }
            br=new BufferedReader(new FileReader(edges_csv_loc));
            while ((line=br.readLine())!=null) {
                String[] values=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                graph.addEdge(values[0], values[1], values[2]);
                // for(String index : values) {
                //     System.out.printf("%-50s", index);
                //    }
                //    System.out.println();
                //System.out.println(values[0].equals(values[1]));
                //System.out.println("id: "+values[0]+" -----------label: "+values[1]);
            }
            System.out.println("Graph:\n"
                           + graph.ToString()); 
        } catch (FileNotFoundException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        
        //input taking method 2
        try {
            FileWriter fileWriter = new FileWriter("Ab.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            BufferedReader br=new BufferedReader(new FileReader(nodes_csv_loc));
            while ((line=br.readLine())!=null) {
                String[] values=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                for(String index : values) {
                    printWriter.printf("%-50s", index);
                   }
                   printWriter.println();
                //System.out.println(values[0].equals(values[1]));
                //System.out.println("id: "+values[0]+" -----------label: "+values[1]);
            }
            br=new BufferedReader(new FileReader(edges_csv_loc));
            while ((line=br.readLine())!=null) {
                String[] values=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                for(String index : values) {
                    printWriter.printf("%-50s", index);
                   }
                   printWriter.println();
                //System.out.println(values[0].equals(values[1]));
                //System.out.println("id: "+values[0]+" -----------label: "+values[1]);
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
///////////////////////////////////////////////////////////////////////////////////////input taken

        // if(function.equals("average")){
        //     // System.out.println("555555"+nodes_csv+" "+edges_csv+" "+function);
        //     File toread=new File(args[0]);
        // }
        // System.out.println(function);
    }
    
}