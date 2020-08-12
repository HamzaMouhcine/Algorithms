import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        int u,v;
        odd=0;
        even=0;
        
        visited = new boolean[n+1];
        desc    = new int[n+1];
        low    = new int[n+1];
        adj = new LinkedList[n+1];
        for(int i=1 ;i<=n ;i++) {
            adj[i] = new LinkedList();
            low[i] = Integer.MAX_VALUE;
        }

        
        

        for(int i=0 ;i<m ;i++) adj[in.nextInt()].add(in.nextInt());


        solve();
        System.out.println(odd-even);

        
        
    }
    
    static LinkedList<Integer> adj[];
    static boolean[] visited;
    static int[] desc,low;
    static int n,m,element;
    static Stack<Integer> st;
    static int even,odd;
    
    static void addEdge(int u,int v){
        adj[u].add(v);
    }
    
    static void solve(){


        st = new Stack<>();
        
        for(int i=1 ;i<=n ;i++){
                if(desc[i]==0) {
                    time=1;
                    DFS(i);
                }
        }
    }
    
    static int time;
    
    static void DFS(int s){
        visited[s] = true;
        desc[s] = time;
        low[s]  = time;
        st.push(s);
        //System.out.println(st);
        
        for(int e:adj[s]){
            if(desc[e]==0){
                time++;
                DFS(e);

                low[s] = Math.min(low[s],low[e]);

            }else if(visited[e]) low[s] = Math.min(low[s],desc[e]);
            
        }
        int w = 0,cnt=1;  // To store stack extracted vertices
        if (low[s] == desc[s])
        {
            while (st.peek() != s)
            {
                w = st.pop();
                cnt++;
                //System.out.print(w+" ");
                visited[w] = false;
            }
            w = st.pop();
            //System.out.println(w);
            visited[w] = false;
            //System.out.println(cnt+" source: "+s);
            if(cnt%2==0) even+=cnt;
            else odd+=cnt;
        }
    }
}
