import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FastReader in = new FastReader();
        n = in.nextInt();
        int m = in.nextInt();
        int u,v;
        
        boolean[][] arr = new boolean[n][n];
        ans = 0;
        
        Graph g = new Graph(n);
        for(int i=0 ;i<m ;i++) {
            u = in.nextInt();
            v = in.nextInt();
            arr[u][v] = true; 
            arr[v][u] = true;
        }
        
        if(g.Hamiltonian_Paths(arr)) System.out.println("YES");
        else System.out.println("NO");
        
        //System.out.println(ans);

    // main
    }
    
    static int n,ans;
    
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
        
        boolean Hamiltonian_Paths(boolean adj[][]){
            boolean[][] dp = new boolean[this.V][1<<this.V];
            for(int i=0; i<V; i++)
                dp[i][1<<i]=true;
            for(int i=0; i<(1<<V); i++){
                for(int j=0; j<V; j++)
                    if((i&(1<<j))>0){
                        for(int k=0; k<V; k++)
                            if((i&(1<<k))>0 && adj[k][j] && k!=j && dp[k][i^(1<<j)]){
                                dp[j][i]=true;
                                break;
                            }
                    }
            }
            for(int i=0; i<n; i++)
                if(dp[i][(1<<n)-1])
                    return true;
            return false;
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
