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
import java.util.Map.Entry;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
class Graph {
    Map<String, Integer > map = new HashMap<>();
    LinkedList<Edge>[] adj;
    int size;
    public Graph (int size) {
        this.adj=new LinkedList[size];
        this.size=size;
    }
    void addEdge(String source, String destination, int weight){
        this.adj[map.get(source)].add(new Edge(map.get(destination), weight));
        this.adj[map.get(source)].set(0, new Edge(adj[map.get(source)].get(0).vertexId+1,adj[map.get(source)].get(0).weight+weight));
        this.adj[map.get(destination)].add(new Edge(map.get(source), weight));
        this.adj[map.get(destination)].set(0, new Edge(adj[map.get(destination)].get(0).vertexId+1,adj[map.get(destination)].get(0).weight+weight));
    }
    void addVertex(String id,int i){
        this.map.put(id,i);
        if (adj[i] == null) {
            adj[i] = new LinkedList<Edge>();
         }
        this.adj[i].add(new Edge(0,0));//
    }
    void printer(){
        int ans=0;
        int ans2=0;
        System.out.println(adj.length);
        for (int ii = 0; ii < adj.length; ii++) {
            ans+=adj[ii].get(0).vertexId;
            ans2+=adj[ii].get(0).weight;
        }
        System.out.println(ans+" ");
        System.out.println(ans2+" ");
    }
    void average(){
        float ans=0;
        for (int ii = 0; ii < adj.length; ii++) {
            ans+=adj[ii].get(0).vertexId;
        }
        ans/=this.size;
        System.out.printf("%.2f", ans);
        System.out.println();
    }
    void rank(){
        vertex[] occur=new vertex[this.size];
        int it=0;
        for (String entry : map.keySet()) {
            occur[it]=new vertex(entry, map.get(entry), adj[map.get(entry)].get(0).vertexId, adj[map.get(entry)].get(0).weight);
            it++;
        }
        MergeSort.sort(occur, 0, this.size-1);
        for (int ii = 0; ii < occur.length-1; ii++) {
            System.out.print(occur[occur.length-1-ii].label+",");
        }
        // for (int ii = 0; ii < occur.length-1; ii++) {
        //     System.out.println(occur[occur.length-1-ii].label+" "+occur[occur.length-1-ii].weight);
        // }
        
        System.out.println(occur[0].label);
    }
    void independant_storylines_dfs(){
        boolean visited[]=new boolean[this.size];

        ArrayList<vertex[] > CompList = new ArrayList<vertex[] >(this.size); 
        vertex[] occur=new vertex[this.size];
        int it=0;
        for (String entry : map.keySet()) {
            occur[it]=new vertex(entry, map.get(entry), adj[map.get(entry)].get(0).vertexId, adj[map.get(entry)].get(0).weight);
            it++;
        }
        for (int ii = 0; ii < occur.length; ii++) {
            if(!visited[ii]){
                //System.out.println(ii);
                ArrayList<vertex> component=new ArrayList<>();
                this.dfs(component,this,ii,visited,occur);
                vertex[] acomponent=new vertex[component.size()];
                acomponent=component.toArray(acomponent);
                MergeSort.sort(acomponent, 0, acomponent.length-1);
                CompList.add(acomponent);
            }
        }
        
        //
        // for (int i = 0; i < visited.length; i++) {
        //     System.out.println(visited[i]);
        // }
        // //
        //System.out.println(CompList.size());
        for (int ii = 0; ii < CompList.size(); ii++) { 
            for (int j = 0; j < CompList.get(ii).length-1 ; j++) { 
                System.out.print(CompList.get(ii)[j].label+"," ); 
            }
            System.out.print(CompList.get(ii)[CompList.get(ii).length-1].label );
            System.out.println(); 
        } 

    }
    void dfs(ArrayList component,Graph graph,int ii,boolean visited[],vertex occur[]){
        visited[ii]=true;
        component.add(occur[ii]);
        Iterator<Edge> i = adj[ii].listIterator();
        while (i.hasNext()) 
        {
            Edge n = i.next();
            if (!visited[n.vertexId])
                dfs(component, graph, n.vertexId, visited,occur);;
        }
    }
}
class vertex{
    public int typen;
    public int totaln;
    public int index;
    public String label;
    public vertex(String label,int index, int typen, int totaln) {
        this.typen = typen;
        this.totaln = totaln;
        this.index=index;
        this.label=label;
    }
}
class Edge
{
    public int vertexId;
    public int weight;
    public Edge(int vertexId, int weight) {
        this.vertexId = vertexId;
        this.weight = weight;
    }
}

public class assignment4 {
    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();
        String nodes_csv_loc = args[0];
        String edges_csv_loc = args[1];
        //String function = args[2];

        String line="";
        //input taking
        FileReader nodesF;
        BufferedReader br,br2;
        int num_lines=0;
        try {
            nodesF=new FileReader(nodes_csv_loc);
            LineNumberReader count = new LineNumberReader(new FileReader(nodes_csv_loc));
            count.skip(Long.MAX_VALUE);
            num_lines=count.getLineNumber()-1;
            
            br=new BufferedReader(nodesF);
            br2=new BufferedReader(new FileReader(edges_csv_loc));
            int i=0;
            Graph graph=new Graph(num_lines);
            while ((line=br.readLine())!=null) {
                String[] values=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                //System.out.println(values[0]);
                values[0]=values[0].replaceAll("\"", "");
                if(values[0].equals("Id"))continue;
                graph.addVertex(values[0], i);
                i++;
                //System.out.println(values[0]);
            }
            while ((line=br2.readLine())!=null) {
                String[] values=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                values[0]=values[0].replaceAll("\"", "");
                values[1]=values[1].replaceAll("\"", "");
                if(values[2].equals("Weight"))continue;
                graph.addEdge(values[0], values[1], Integer.parseInt(values[2]));
            }
            //graph.printer();
            if(args[2].equals("average")){
                graph.average();
            }        
            else if(args[2].equals("rank")){
                FileOutputStream f = new FileOutputStream("file.txt");
                System.setOut(new PrintStream(f));
                graph.rank();
            }   
            else{
                FileOutputStream f = new FileOutputStream("file.txt");
                System.setOut(new PrintStream(f));
                graph.independant_storylines_dfs();
            } 
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long stopTime = System.nanoTime();
        //System.out.println((stopTime - startTime)/1000000000.0);  
    }
    
}
class MergeSort 
{
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    static void merge(vertex arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        vertex L[] = new vertex[n1];
        vertex R[] = new vertex[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].totaln < R[j].totaln) {
                arr[k] = L[i];
                i++;
            }
            else if(L[i].totaln>R[j].totaln) {
                arr[k] = R[j];
                j++;
            }
            else{
                if (L[i].label.compareToIgnoreCase(R[j].label)<0) {
                    arr[k] = L[i];
                    i++;
                }
                else{
                    arr[k] = R[j];
                    j++;
                }
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    static void sort(vertex arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;
 
            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
 
    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
