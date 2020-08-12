import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FastReader in = new FastReader();
        int n = in.nextInt();
        
        Graph g = new Graph(n);
        for(int i=1 ;i<n ;i++) g.addEdge(in.nextInt(),in.nextInt());


    // main
    }
    
    
    static class Graph{
        int V;
        LinkedList<Integer> adj[] ;
        
        Graph(int v){
            this.V = v;
            adj= new LinkedList[v+1];
            for(int i=1 ;i<=v ;i++) adj[i] = new LinkedList<Integer>();
        }
        
        public void addEdge(int u,int v){
            adj[u].add(v);
            adj[v].add(u);
        }
        
        public void BFS(int s){
            boolean[] visited = new boolean[V+1];
            Queue<Integer> q = new LinkedList<Integer>();

            int ans =0 ;
            visited[s] = true;
            q.add(s);
            int element;
            while(q.size()!=0){
                element = q.remove();
                
                for(int e:adj[element]){
                    if(!visited[e]){
                        visited[e]=true;
                        q.add(e);
                    }
                }
            }

        }

        void DFSUtil(int v,boolean visited[]){
        
        visited[v] = true;
        System.out.print(v+" ");
 
        
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                    DFSUtil(n, visited);
            }
        }
 
    
        void DFS(int v)
        {
            // Mark all the vertices as not visited(set as
            // false by default in java)
            boolean visited[] = new boolean[V];
     
            // Call the recursive helper function to print DFS traversal
            DFSUtil(v, visited);
        }
    }
    
    
    
    //FR
    static class FastReader  {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
}
