import java.util.*;
import java.io.*;
import java.math.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        //Scanner in = new Scanner(System.in);
        Reader in = new Reader();
        Main solver = new Main();
        solver.solve(out, in);
        out.flush();
        out.close();
 
    }
 
    static int INF = (int)1e5*4*4+5;
    static int maxn = (int)1e5*2+1;
    static int mod=(int)1e9+7 ;
    static int m,k,t,q,x,a,b,y,d;
    static long n;
    
    static long mat[][];
    static long stat[][];
    void solve(PrintWriter out, Reader in) throws IOException{
        n = in.nextLong();
        m = in.nextInt();
        k = in.nextInt();
        mat = new long[m][m];
        for(int i=0;i<m;i++)
            for(int j=0;j<m;j++)
                mat[i][j] = 1;
        
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(int i=0;i<26;i++){
            map.put((char)(i+'a'),i);
            map.put((char)(i+'A'),i+26);
        }
        
        String str = "";
        for(int i=0;i<k;i++){
            str = in.next();
            
            mat[map.get(str.charAt(1))][map.get(str.charAt(0))] = 0;
        }
        
        stat = new long[m][m];
        for(int i=0;i<m;i++)
            for(int j=0;j<m;j++)
                stat[i][j] = mat[i][j];
        
        
        if(n==1) out.println(m);
        else out.println(matpow(mat,n-1));
    }
    
    //<>
    
    
    static long matpow(long[][] mat,long p){
        
        
        
        long ans = 0;
        if(p==1){
            for(int i=0;i<m;i++)
                for(int j=0;j<m;j++)
                    ans = (ans+mat[i][j])%mod;
            return ans;
        }else if(p==0) return 0;
        
        matpow(mat,p/2);
        matmul(mat,mat);
        
        if(p%2==1){
            matmul(mat,stat);
        }
        
        for(int i=0;i<m;i++)
            for(int j=0;j<m;j++)
            ans = (ans+mat[i][j])%mod;
        return ans;
    }
    
    static void matmul(long[][] ma,long[][] mb){
        long[][] c = new long[m][m];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                for(int k=0;k<m;k++){
                    c[i][j] = ((ma[i][k]*mb[k][j])%mod+c[i][j])%mod;
                }
            }
        }
        
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                ma[i][j] = c[i][j];
            }
        }
        return;
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