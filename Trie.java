import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FastReader in = new FastReader();
        root = new TrieNode();
        for(int i=0 ;i<3 ;i++){
            insert(in.next());
        }
        System.out.println(search("azea"));

    // main
    }
    
    
    static class TrieNode{
        Map<Character,TrieNode> children;
        boolean leaf;
        
        TrieNode(){
            leaf = false;
            children = new HashMap<>();
        }
    }
    static TrieNode root = new TrieNode();
    
    static void insert(String key){
        char idx;
        TrieNode mark = root;
        
        for(int lvl=0 ;lvl<key.length() ; lvl++){                
            idx = key.charAt(lvl);
            

            if(mark.children.get(idx)==null) mark.children.put(idx,new TrieNode());
            
            mark = mark.children.get(idx);
        }
        mark.leaf = true;
    }
    
    static boolean search(String key){
        char idx;
        TrieNode mark = root;
        
        for(int lvl=0 ;lvl<key.length() ; lvl++){
            idx = key.charAt(lvl);
            
            if(mark.children.get(idx)==null)
                return false;
            mark = mark.children.get(idx);
        }
        if(mark!=null && mark.leaf) return true;
        else return false;
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
