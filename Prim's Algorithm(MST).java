import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

class TestClass {
    static ArrayList<Node> adj[];
    static int n,m;
    
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] name = br.readLine().split(" ");
        n = Integer.parseInt(name[0]);
        m = Integer.parseInt(name[1]);
        
        adj = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            adj[i] = new ArrayList<Node>();
        }
        
        int u,v,w;
        for(int i=0;i<m;i++){
            name = br.readLine().split(" ");
            
            u = Integer.parseInt(name[0]);
            v = Integer.parseInt(name[1]);
            w = Integer.parseInt(name[2]);
            adj[u].add(new Node(v,w));
            adj[v].add(new Node(u,w));
        }
        
        
        System.out.println(Prim());
    }
    
    static int Prim(){
        boolean[] vis = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for(Node e:adj[1]){
            pq.add(e);
        }
        int ans =0;
        
        vis[1]=true;
        
        Node node;
        while(pq.size()!=0){
            node = pq.remove();
            if(!vis[node.to]){
                vis[node.to] = true;
                ans += node.w;
                for(Node e:adj[node.to]){
                    if(!vis[e.to]) pq.add(e);
                }
            }
        }
        
        return ans;
    }
    
    static class Node implements Comparable<Node>{
        int to,w;
        
        Node(int to,int w){
            this.to = to;
            this.w = w;
        }
        
        public int compareTo(Node o){
            return this.w-o.w;
        }
    }
}
