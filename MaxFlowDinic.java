import java.util.*;
import java.io.*;
import java.math.*;

public class MaxFlowDinic {
    static final long flowInf = (long)1e18;
    static PrintWriter out = new PrintWriter(System.out);
    static Reader in = new Reader();
    static int nodes;
    static int edges;
    static ArrayList<Edge> edges;
    static ArrayList<Integer>[] adj;
    static int cntEdge = 0;
    static int[] lvl;
    static int[] ptr;

    public static void main(String[] args) throws IOException {
        MaxFlowDinic solver = new MaxFlowDinic();
        solver.solve();
        out.flush();
        out.close();
    }

    static int source = 'S' - 'A' + 1, sink = 'T' - 'A' + 1; // initialize source and sink!

    void solve() throws IOException {
        nodes = in.nextInt();
        edges = in.nextInt();

        edges = new ArrayList<Edge>();
        adj = new ArrayList[nodes + 1];
        for (int i = 1; i <= nodes; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        // adding edges of the form (u -> v, capacity).
        for (int i = 0; i < edges; i++) {
            addEdge(in.nextInt(), in.nextInt(), in.nextInt());
        }

        // lvl[u] = depth[u] where source is the root.
        lvl = new int[nodes + 1];
        // ptr[u] = points to the next edge that we're going to use(optimizes the algorithm).
        ptr = new int[nodes + 1];

        out.println(flow());
    }

    // use : initialize source (source) and sink (sink) and call this function, it modifies the edges when used.
    // multiple tcs : initialize source and sink and cntEdges each testcase.
    // time complexity : O(edges*nodes^2) on normal graphs, and O(edges*sqrt(nodes) in maximum bipartite matching;
    // returns : the max flow.
    static long flow() {
        long f = 0;

        while (true) {
            fill(lvl, nodes + 1, -1);
            lvl[source] = 0;

            if (!bfs(source)) {
                break;
            }
            
            fill(ptr, nodes + 1, 0);
            long pushed = 0;
            while ((pushed = dfs(source, flowInf)) > 0) {
                f += pushed;
            }
        }
        return f;
    }

    // finding the blocking flow.
    static long dfs(int u, long pushed) {
        if (pushed == 0) {
            return 0;
        }

        if (u == sink) {
            return pushed;
        }

        for (; ptr[u] < (int)adj[u].size(); ptr[u]++) {
            int id = adj[u].get(ptr[u]);
            int v = edges.get(id).v;
            if (lvl[u] + 1 != lvl[v] || edges.get(id).cap - edges.get(id).flow < 1) {
                continue;
            }

            long tr = dfs(v, Math.min(pushed, edges.get(id).cap - edges.get(id).flow));
            if (tr == 0) {
                continue;
            }
            edges.get(id).flow += tr;
            edges.get(id ^ 1).flow -= tr;
            return tr;
        }
        return 0;
    }

    // calculates the depth of each node.
    static boolean bfs(int s) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(s);

        while (q.size() != 0) {
            int u = q.peek();
            q.remove();
            for (int id : adj[u]) {
                if (edges.get(id).cap - edges.get(id).flow < 1) {
                    continue;
                }

                if (lvl[edges.get(id).v] != -1) {
                    continue;
                }
                lvl[edges.get(id).v] = lvl[u] + 1;
                q.add(edges.get(id).v);
            }
        }
        return lvl[sink] != -1;
    }

    static void addEdge(int u, int v, int cap) {
        edges.add(new Edge(u, v, cap, 0));
        adj[u].add(cntEdge++);
        edges.add(new Edge(v, u, 0, 0));
        adj[v].add(cntEdge++);
    }

    static void fill(int[] arr, int len, int v) {
        for (int i = 0; i < len; i++) {
            arr[i] = v;
        }
    }

    static class Edge {
        int u, v, cap, flow;

        Edge(int u, int v, int cap, int flow) {
            this.u = u;
            this.v = v;
            this.cap = cap;
            this.flow = flow;
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
