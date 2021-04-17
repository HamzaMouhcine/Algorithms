import java.util.*;
import java.io.*;
import java.math.*;

public class LiChaoTree {
    static final int INF = (int)1e9;
    static final int MAXN = (int)1e6 + 100;
    static int nodes;
    static int cnt = 2;
    static long[] xs, ys, value;
    static Line[] line;

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        Reader in = new Reader();
        LiChaoTree solver = new LiChaoTree();
        solver.solve(out, in);
        out.flush();
        out.close();
    }

    void solve(PrintWriter out, Reader in) throws IOException {
        nodes = in.nextInt();

        line = new Line[maxn * 4];
        line[1] = new Line(0, 0);

        xs = new long[nodes];
        ys = new long[nodes];
        value = new long[nodes];
        Integer[] indices = new Integer[nodes];
        for (int i = 0; i < nodes; i++) {
            indices[i] = i;
            xs[i] = in.nextLong();
            ys[i] = in.nextLong();
            value[i] = in.nextLong();
        }

        Arrays.sort(indices, Comparator.comparingLong(i -> xs[i]));
        long ans = 0;
        for (int i = 0; i < nodes; i++) {
            long temp = query(1, 1, INF, ys[indices[i]]) + xs[indices[i]] * ys[indices[i]] - value[indices[i]];
            ans = Math.max(ans, temp);
            addLine(1, 1, INF, new Line(-xs[indices[i]], temp));
        }
        out.println(ans);
    }


    static void addLine(int idx, int s, int e, Line nw) {
        int mid = (s + e) / 2;

        boolean s_nw   = nw.eval(s)   > line[idx].eval(s);
        boolean mid_nw = nw.eval(mid) > line[idx].eval(mid);

        if (mid_nw) {
            Line tmp = new Line(nw.m, nw.c);
            nw = new Line(line[idx].m, line[idx].c);
            line[idx].m = tmp.m;
            line[idx].c = tmp.c;
        }

        if (s == e) return;
        if (s_nw != mid_nw) {
            if (line[idx].l == 0) {
                line[idx].l = cnt++;
                line[cnt - 1] = nw;
                return;
            }
            addLine(line[idx].l, s, mid, nw);
        } else {
            if (line[idx].r == 0) {
                line[idx].r = cnt++;
                line[cnt - 1] = nw;
                return;
            }
            addLine(line[idx].r, mid + 1, e, nw);
        }
    }

    static long query(int idx, int s, int e, long x) {
        long res = line[idx].eval(x);

        if (s == e) {
            return res;
        }

        int mid = (s + e) / 2;
        if (x <= mid) {
            if (line[idx].l == 0) {
                return res;
            } else {
                return Math.max(res, query(line[idx].l, s, mid, x));
            }
        } else {
            if (line[idx].r == 0) {
                return res;
            } else {
                return Math.max(res, query(line[idx].r, mid + 1, e, x));
            }
        }
    }

    static class Line {
        long m, c;
        int l = 0, r = 0;

        Line(long m, long c) {
            this.m = m;
            this.c = c;
        }

        double inter(Line l) {
            return (double)(l.c - c) / (double)(m - l.m);
        }

        long eval(long x) {
            return m * x + c;
        }
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

        double nextDouble() {
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
