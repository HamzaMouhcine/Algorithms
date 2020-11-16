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

    void solve(PrintWriter out, Reader in) throws IOException{
	
    }
    
    //<>
    
    
    static int add(int x, int y) {
        x += y;
        if(x >= Mod) {
            x -= Mod;
        }
        return x;
    }

    static int sub(int x, int y) {
        x -= y;
        if(x < 0) {
            x += Mod;
        }
        return x;
    }

    static int mul(int x, int y) {
        long result = (long) x * y % Mod;
        return (int) result;
    }

    static int[][] Ident(int n) {
        int[][] result = new int[n][n];
        for(int i = 0; i < n; i++) {
            result[i][i] = 1;
        }
        return result;
    }

    static int[][] Multy(int[][] a, int[][] b) {
        int na = a.length, ma = a[0].length;
        int nb = b.length, mb = b[0].length;
        assert ma == nb;
        int[][] c = new int[na][mb];
        for(int i = 0; i < na; i++) {
            for(int j = 0; j < mb; j++) {
                for(int k = 0; k < nb; k++) {
                    c[i][j] = add(c[i][j], mul(a[i][k], b[k][j]));
                }
            }
        }
        return c;
    }

    static int[][] Power(int[][] a, long k) {
        int n = a.length;
        int[][] b = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                b[i][j] = a[i][j];
            }
        }
        int[][] result = Ident(n);
        for( ; k > 0; k >>= 1) {
            if (k % 2 == 1) {
                result = Multy(result, b);
            }
            b = Multy(b, b);
        }
        return result;
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
