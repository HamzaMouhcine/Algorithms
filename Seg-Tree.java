import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FastReader in = new FastReader();
        
        int n = in.nextInt();
        int q = in.nextInt();
        
        arr = new int[n+1];
        tree = new int[10*n+2];
        for(int i=1 ;i<=n ;i++) arr[i] =  in.nextInt();
        
        build(1,1,n);
        for(int i=0 ;i<q ;i++){
            if(in.next().equals("q")){
                System.out.println(query(1,1,n,in.nextInt(),in.nextInt()));
            }else{
                update(1,1,n,in.nextInt(),in.nextInt());
            }
        }


    }
    
    static int[] arr , tree;
    
    static void build(int node, int start, int end){
        // Leaf node will have a single element
        if(start == end)   tree[node] = arr[start]; 
        else {
            int mid = (start + end) / 2;
            // Recurse on the left child
            build(2*node, start, mid);
            // Recurse on the right child
            build(2*node+1, mid+1, end);
            // Internal node will have the sum of both of its children
            tree[node] = Math.min( tree[2*node] , tree[2*node+1]);
        }
    }
    
    static void update(int node, int start, int end, int idx, int val){
        if(start == end){
            // Leaf node
            arr[idx] = val;
            tree[node] = val;
        }
        else{
            int mid = (start + end) / 2;
            if(start <= idx && idx <= mid){
                // If idx is in the left child, recurse on the left child
                update(2*node, start, mid, idx, val);
            }
            else{
                // if idx is in the right child, recurse on the right child
                update(2*node+1, mid+1, end, idx, val);
            }
            // Internal node will have the sum of both of its children
            tree[node] = Math.min(tree[2*node] , tree[2*node+1]);
        }
    }
    
    static int query(int node, int start, int end, int l, int r){
        if(r < start || end < l){
            // range represented by a node is completely outside the given range
            return Integer.MAX_VALUE;
        }
        if(l <= start && end <= r){
            // range represented by a node is completely inside the given range
            return tree[node];
        }
        // range represented by a node is partially inside and partially outside the given range
        int mid = (start + end) / 2;
        int p1 = query(2*node, start, mid, l, r);
        int p2 = query(2*node+1, mid+1, end, l, r);
        return Math.min(p1 , p2);
    }
    
    
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
