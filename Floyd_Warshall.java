import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        FastReader in = new FastReader();
        int n = in.nextInt();
        int m = in.nextInt();
        int u,v;
        Map<String,Integer> map = new HashMap<String,Integer>();
        int[][] dp = new int[n+1][n+1];
        
        for(int i=1 ;i<=n ;i++) map.put(in.next(),i);
        for(int i=0 ;i<m ;i++) {
            u = map.get(in.next());
            v = map.get(in.next());
            dp[u][v] = in.nextInt();
            dp[v][u] = dp[u][v];
        }
        for(int i=1;i<=n ;i++)
            for(int j=1;j<=n;j++)
                if(dp[i][j]==0 && i!=j)
                    dp[i][j]=Integer.MAX_VALUE;
        for(int k=1;k<=n;k++)
            for(int i=1;i<=n;i++)
                for(int j=1;j<=n;j++)
                    if(dp[i][k]!=Integer.MAX_VALUE && dp[k][j]!=Integer.MAX_VALUE && dp[i][k]+dp[k][j] < dp[i][j])
                        dp[i][j]=dp[i][k]+dp[k][j];
        int q = in.nextInt();
        for(int i=0 ;i<q ;i++)
            System.out.println(dp[map.get(in.next())][map.get(in.next())]);
        


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
