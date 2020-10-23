import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    static int[] dist;
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int t =in.nextInt();
        
        
        
            
        
        for(int k=0 ;k<t ;k++){
            //System.err.println("ADJ LIST SIZE = " + adj[1].size());
            n =in.nextInt();
            m =in.nextInt();
            adj = new LinkedList[n+1];
            
            dist = new int[n+1];
            for(int i=1;i<=n ;i++) {
                dist[i] = Integer.MAX_VALUE;
            }
            
            for(int i=0;i<=n ;i++) adj[i] = new LinkedList();
            
            for(int i=0;i<m;i++) {
                addEdge(in.nextInt(),in.nextInt(),in.nextInt());
            }

            l = in.nextInt();
            int[] friends = new int[l];
            
            for(int i=0;i<l;i++) friends[i]=in.nextInt();
            d =in.nextInt();
            
            System.out.println(ShortPath(d,friends,dist.clone()));
            

            /*System.out.println("Displaying Graph : ");
            for(int i = 1; i <= n; ++i) {
                System.out.print(i + ":");
                for(Edge e : adj[i]){
                    System.out.print(" " + e);
                } System.out.println();
            }*/
        }
    }

    static LinkedList<Edge> adj[];
    static int n,m,l,d;

        
        static void addEdge(int u,int v,int unites){
            adj[u].add(new Edge(v,unites));
            adj[v].add(new Edge(u,unites));

        }
        
        
        
        static int ShortPath(int s,int[] friends,int[] dist){
            PriorityQueue<Edge> q = new PriorityQueue<Edge>();
            q.add(new Edge(s,0));


            dist[s]=0;
            while(q.size()>0){
		Edge edge = q.remove();
		int element = edge.to;
		if (edge.weight > dist[edge.to]) continue;

                for(Edge e:adj[element]){

                        if(dist[element]+e.weight<dist[e.to]){
                            dist[e.to] = dist[element]+e.weight;
                            q.add(new Edge(e.to, dist[e.to]));
                        }

                }
            }
            return mind(dist,friends);
        }
        
        static int mind(int[] dist,int[] friends){
            int min = Integer.MAX_VALUE;
                for(int i=0; i<friends.length ;i++){
                    if(dist[friends[i]]<min) min = dist[friends[i]];
                }

            return min;
        }
        
        private static class Edge implements Comparable<Edge>{
            int to;
            int weight;
    
                public Edge(int to, int weight) {
                    this.to = to;
                    this.weight = weight;
                }
    
                @Override
                public int compareTo(Edge e) {
                    // compare Edges with respect to their weights increasing
                    return this.weight - e.weight;
                }
    
                @Override
                public String toString() {
                    return "("+this.to+", "+this.weight+")";
                }
        }
}
