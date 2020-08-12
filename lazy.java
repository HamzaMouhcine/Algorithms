import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) {
        FastReader in = new FastReader();
        int n = in.nextInt();
        
        arr = new int[n];
        for(int i=0 ;i<n ;i++) arr[i] = in.nextInt();
        
        
        tree = new int[10*n];
        lazy = new int[10*n];
        build(1,0,n-1);
        
        update(1,0,n-1,0,3);
        System.out.println(query(1,0,n-1,0,3));

        


    }
    
    static int[] arr,tree,lazy;
    
    static void build(int node,int s,int e){
        if(s==e) tree[node] = arr[s];
        else{
            int mid = (e+s)/2;
            build(node*2,s,mid);
            build(node*2+1,mid+1,e);
            tree[node] = tree[2*node]+tree[node*2+1];
        }
    }
    
    static void update(int node,int s,int e,int l,int r){
        if(lazy[node]!=0){
            tree[node] += lazy[node]*(e-s+1);
            if(s!=e){
                lazy[node*2]+=lazy[node];
                lazy[node*2+1]+=lazy[node];
            }
            lazy[node]=0;
        }
        
        if(e<l || r<s) return;
        
        if(l<=s && e<=r){
            tree[node] += e-s+1;
            if(s!=e){
                lazy[2*node]++;
                lazy[2*node+1]++;
            }
            return;
        }
        
        int mid = (s+e)/2;
        update(2*node,s,mid,l,r);
        update(2*node+1,mid+1,e,l,r);
        tree[node] = tree[2*node]+tree[2*node+1];
        
    }
    
    static int query(int node,int s,int e,int l,int r){
        if(lazy[node]!=0) {
            tree[node] += lazy[node]*(e-s+1);
            if(s!=e){
                lazy[node*2]+=lazy[node];
                lazy[node*2+1]+=lazy[node];
            }
            lazy[node]=0;
        }
        
        if(r<s || e<l) return 0;
        if(l<=s && e<=r) return tree[node];
        
        int mid = (s+e)/2;
        int p1 = query(2*node,s,mid,l,r);
        int p2 = query(2*node+1,mid+1,e,l,r);
        return p1 + p2; 
        
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
