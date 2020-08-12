import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
        public static void main (String[] args) throws java.lang.Exception{
            Scanner in = new Scanner(System.in);
            
    		int n = in.nextInt();
    		int m = in.nextInt();
    		int[] lvl = new int[n+1];
    		adj = new PriorityQueue[n+1];
    		for(int i=1 ;i<=n ;i++) adj[i] = new PriorityQueue();
    		
    		int u,v;
    		for(int i=0 ;i<m ;i++) {
    		    u = in.nextInt();
    		    v = in.nextInt();
    		    adj[u].add(v);
    		    lvl[v]++;
    		}
    		
    		Queue<Integer> q = new LinkedList<Integer>();
    		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    		for(int i=n ;i>=1 ;i--) 
    		    if(lvl[i]==0)
    		        pq.add(i);
    		      
    		int cnt =0,e,element;
    		while(pq.size()>0){
    		    element = pq.poll();
    		    q.add(element);
    		    
    		    while(adj[element].size()>0){
    		        e = adj[element].poll();
    		        lvl[e]--;
    		        if(lvl[e]==0) pq.add(e);
    		    }
    		    
    		    cnt++;
    		}
    		
    		if(cnt!=n) System.out.print("Sandro fails.");
    		else {
    		    System.out.print(q.remove());
    		    while(q.size()>0) System.out.print(" "+q.remove());
    		}
    		System.out.println();
		
	}
	
	static PriorityQueue<Integer> adj[];
	
}