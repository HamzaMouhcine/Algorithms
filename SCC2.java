    import java.util.*;
    import java.io.*;
    import java.math.*;
     
    public class Main {
        public static void main(String[] args) throws IOException {
            PrintWriter out = new PrintWriter(System.out);
            //Scanner sc = new Scanner();
            Reader in = new Reader();
            Main solver = new Main();
            solver.solve(out, in);
            out.flush();
            out.close();
     
        }
     
        static long INF = (long)1e10;
        static int maxn = (int)1e6+5;
        static int mod= 1000000007 ;
        static int n,m,k,t,q,d,p,min=0,cnt=0;
        
        static LinkedList<Integer> adj[];
        static boolean[] vis;
        static Stack<Integer> st;
        static int[] cost;
        
        void solve(PrintWriter out, Reader in) throws IOException{
            n = in.nextInt();
            
            cost = new int[n+1];
            for(int i=1;i<=n;i++) cost[i] = in.nextInt();
            
            m = in.nextInt();
            
            adj = new LinkedList[n+1];
            for(int i=1;i<=n;i++) adj[i] = new LinkedList<Integer>();
            
            int[] a = new int[m];
            int[] b = new int[m];
            
	    // initialize the original graph.
            for(int i=0;i<m;i++){
                a[i] = in.nextInt();
                b[i] = in.nextInt();
                adj[a[i]].add(b[i]);
            }
            
	    // ordering the nodes.
            st = new Stack<Integer>();
            vis = new boolean[n+1];
            for(int i=1;i<=n;i++){
                if(!vis[i]){
                    DFS(i,true);
                }
            }
            
            for(int i=1;i<=n;i++) adj[i].clear();
            // reversing the original graph.
            for(int i=0;i<m;i++){
                adj[b[i]].add(a[i]);
            }
            
            vis = new boolean[n+1];
            int element;
            long ans=1,totalcost=0;
	    // finding each scc and constructing the answer.
            while(st.size()!=0){
                element = st.pop();
                min = cost[element];
                cnt=1;
                if(vis[element]) continue;
                DFS(element,false);
                totalcost+=min;
                ans = (ans*cnt)%mod;
            }
            
            out.println(totalcost+" "+ans);
        }
        
        
        static void DFS(int s,boolean flag){
            vis[s] = true;
		
	    // answer queries here!
            for(int e:adj[s]){
                if(!vis[e]){
                    if(min>cost[e]){
                        min = cost[e];
                        cnt=1;
                    }else if(min==cost[e]) cnt++;
                    vis[e] = true;
                    DFS(e,flag);
                }
            }
            
            if(flag) st.push(s);
        }
        
        
        static class Reader {
     
        private InputStream mIs;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
     
        public Reader() {
            this(System.in);
        }
     
        public Reader(InputStream is) {
            mIs = is;
        }
     
        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
     
        }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = mIs.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }
     
        public String nextLine() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }
     
        public String next() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }
     
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
     
        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
     
        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
     
        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
     
        public boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
     
        }
    }
