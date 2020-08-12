import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FastReader in = new FastReader();

            n = in.nextInt();
            e=0;
            

            
            subset subsets[] = new subset[n+1];
            for(int i=1; i<=n; i++){
                subsets[i]=new subset();
                subsets[i].parent = i;
                subsets[i].rank = 0;
            }
     
            
            int m = in.nextInt();
            
            ArrayList<Edge> edges = new ArrayList<Edge>();
            for(int i=0 ;i<m ;i++) {
                    edges.add(new Edge(in.nextInt(),in.nextInt(),in.nextInt()));
            }
            Collections.sort(edges);
            Kruksal(edges,subsets);

    // main
    }
    
    
    static int e,n;
        
        static class subset{
            int parent,rank;
        }
        
        static int find(subset subsets[],int i){
            if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
 
            return subsets[i].parent;
        }
        
        static void union(subset subsets[],int x,int y){
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);
     
            
            if (subsets[xroot].rank < subsets[yroot].rank)
                subsets[xroot].parent = yroot;
            else if (subsets[xroot].rank > subsets[yroot].rank)
                subsets[yroot].parent = xroot;
            else
            {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }
        
        
        static void Kruksal(ArrayList<Edge> edges,subset[] subsets){
            ArrayList<Edge> ans = new ArrayList<Edge>();
            int i=0,cnt=0;

            while(e<n-1 && i<edges.size()){
                Edge next_edge = new Edge(edges.get(i).src,edges.get(i).dest,edges.get(i).weight);
                i++;
 
                int x = find(subsets, next_edge.src);
                int y = find(subsets, next_edge.dest);
     
                // If including this edge does't cause cycle,
                // include it in result and increment the index 
                // of result for next edge
                if (x != y){
                    ans.add(next_edge);
                    union(subsets, x, y);
                    e++;
                }
            }

            for (i = 0; i < ans.size(); i++)
                cnt += ans.get(i).weight;

            System.out.println(cnt);
        }
        

        
        private static class Edge implements Comparable<Edge>{
            int src;
            int dest;
            int weight;
    
                public Edge(int src, int dest, int weight) {
                    this.src = src;
                    this.dest = dest;
                    this.weight = weight;
                }
    
                @Override
                public int compareTo(Edge e) {
                    // compare Edges with respect to their weights increasing
                    return this.weight - e.weight;
                }
    
                @Override
                public String toString() {
                    return "("+this.dest+", "+this.weight+")";
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
