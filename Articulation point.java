import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        int u,v;
        
        visited = new boolean[n+1];
        desc    = new int[n+1];
        low    = new int[n+1];
        parent = new int[n+1];
        adj = new LinkedList[n+1];
        for(int i=1 ;i<=n ;i++) {
            adj[i] = new LinkedList();
            low[i] = Integer.MAX_VALUE;
        }
        
        
        int[] edges = new int[n+1];
        for(int i=0 ;i<m ;i++) {
            u = in.nextInt();
            v = in.nextInt();
            adj[u].add(v);
            adj[v].add(u);
            edges[u]++;
            edges[v]++;
        }
        
        ans = new boolean[n+1];
        solve();
        

        for(int i=1 ;i<=n ;i++) {
            System.out.println((ans[i] ? "Satisfied "+edges[i] : "Not Satisfied"));
        }
        
        
    }
    
    static LinkedList<Integer> adj[];
    static boolean[] visited,ans;
    static int[] desc,low,parent;
    static int n,m,element;
    
    static void solve(){
        int time=0;
        int childs=0;
        
        for(int i=1 ;i<=n ;i++){
                if(!visited[i]) {
                    time=0;
                    childs=0;
                    DFS(i,time+1,childs);
                }
        }
    }
    
    static void DFS(int s,int time,int childs){
        visited[s] = true;
        desc[s] = time;
        low[s]  = time;
        
        for(int e:adj[s]){
            if(!visited[e]){
                childs++;
                parent[e] = s;
                DFS(e,time+1,0);

                low[s] = Math.min(low[s],low[e]);

                if(parent[s]==0 && childs>1) ans[s] = true;
                if(parent[s]!=0 && low[e]>=desc[s]) ans[s] = true;
            }else if(parent[s] != e) low[s] = Math.min(low[s],desc[e]);
            
        }
    }
}
