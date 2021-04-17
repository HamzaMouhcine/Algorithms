import java.util.*;
import java.io.*;
import java.math.*;
import java.text.*;

public class SegTree {
    static PrintWriter out;
    static Reader in;
    static int nodes;

    public static void main(String[] args) throws IOException {
        initInputOutput();
        SegTree solver = new SegTree();
        solver.solve();
        out.close();
        out.flush();
    }

    void solve() throws IOException {
        nodes = in.nextInt();

        int[] arr = new int[nodes];
        for (int i = 0; i < nodes; i++) arr[i] = in.nextInt();

        SegTree st = new SegTree(0, nodes - 1, arr);
    }

    static class SegTree {
        int leftmost, rightmost;
        SegTree lChild, rChild;
        long value;
        long toProp;

        public SegTree(int leftmost, int rightmost, int[] a) {
            this.leftmost = leftmost;
            this.rightmost = rightmost;
            if (leftmost != rightmost) {
                int mid = (leftmost + rightmost) / 2;
                lChild = new SegTree(leftmost, mid, a);
                rChild = new SegTree(mid + 1, rightmost, a);
                recalc();
            } else init_leaf(a[leftmost]);
        }

        public void init_leaf(int val) {
            value = val;
        }

        public void recalc() {
            value = Math.min(lChild.value, rChild.value);
        }

        public void prop() {
            if (toProp == 0) {
                return;
            }

            value += toProp;
            if (leftmost != rightmost) {
                lChild.toProp += toProp;
                rChild.toProp += toProp;
            }
            toProp = 0;
        }

        public void pointUpdate(int position, int newValue) {
            if (leftmost == rightmost) {
                value = newValue;
                return;
            }

            if (position <= lChild.rightmost) {
                lChild.pointUpdate(position, newValue);
            } else {
                rChild.pointUpdate(position, newValue);
            }
            recalc();
        }

        public void update(int l, int r, int v) {
            if (l <= leftmost && rightmost <= r) {
                toProp += v;
                prop();
                return;
            }
            prop();
            if (l > rightmost || r < leftmost) {
                return;
            }
            lChild.update(l, r, v);
            rChild.update(l, r, v);
            recalc();
        }

        public long query(int l, int r) {
            prop();
            if (l <= leftmost && rightmost <= r) {
                return value;
            }
            if (l > rightmost || r < leftmost) {
                return INF;
            }

            return Math.min(lChild.query(l, r), rChild.query(l, r));
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
    static void initInputOutput() throws IOException {
        // input
        File f = new File("in.txt");
        if (f.exists() && !f.isDirectory()) {
            in = new Reader(new FileInputStream("in.txt"));
        } else {
            in = new Reader();
        }

        // output
        f = new File("out.txt");
        if (f.exists() && !f.isDirectory()) {
            out = new PrintWriter(new File("out.txt"));
        } else {
            out = new PrintWriter(System.out);
        }
    }
}