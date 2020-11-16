import java.util.*;
import java.io.*;
import java.math.*;
 
public class Main {
    static PrintWriter out;
    static Reader in;
    public static void main(String[] args) throws IOException {
        //in = new Reader(new FileInputStream(args[0])); // for test.
        input_output();
        Main solver = new Main();
        solver.solve();
        out.close(); // remove when testing.
        out.flush(); // remove when testing.
    }
 
    static int INF = (int)1e9;
    static int maxn = (int)2e5+5;
    static int Mod = (int)1e9+7;
    static int n, m, q, t, k;

    // Problem: Initially you have an empty graph, you're given q queries
    // of three types: 1_ add an edge, 2_ remove an existing edge
    // 3_ how many connected components exists.

    // Solution: We use dynamic connectivity to solve this problem:
    // we change queries format to: edge(a, b) exists 
    // in different segments of time, and we use a segment tree to push
    // these segments as updates into the segment tree such that each segment
    // will be pushed log2(N) times, so the space complexity is O(q(log2(N))),
    // and then we use DSU to connect nodes and track the number of components
    // at the same time we keep a stack of updates so we can rollback updates.

    static List<Node> tree[];
    static int[] ans;

    void solve() throws IOException{
    	n = in.nextInt();
    	q = in.nextInt();

    	if (q == 0) return;

    	Map<Long, Integer> map = new HashMap<Long, Integer>();
    	List<Integer> qrs = new ArrayList<Integer>();
    	tree = new ArrayList[q*4];
    	for (int i = 0; i < q*4; i++) tree[i] = new ArrayList<Node>();

    	int[] x = new int[q],
    		  y = new int[q];
    	for (int i = 0; i < q; i++) {
    		int type = in.next().charAt(0);

    		if (type == '?') {
    			qrs.add(i);
    		} else {
    			int a = in.nextInt(),
    				b = in.nextInt();
    			if (a > b) {
    				int tmp = a;
    				a = b;
    				b = tmp;
    			}

    		    if (type == '+') {
    		    	x[i] = a;
    		    	y[i] = b;
    		    	map.put(hash(a, b), i);
    		    } else {
    		    	update(1, 0, q-1, map.get(hash(a, b)), i, a, b);
    		    	map.put(hash(a, b), -1);
    		    }
    		}
    	}


    	for (int i = 0; i < q; i++) {
    		if (x[i]+y[i] == 0 || map.get(hash(x[i], y[i])) == -1) continue;
    		update(1, 0, q-1, map.get(hash(x[i], y[i])), q-1, x[i], y[i]);
    		map.put(hash(x[i], y[i]), -1);
    	}

    	ans = new int[q];
    	par = new int[n+1];
    	size = new int[n+1];
    	st = new ArrayDeque<Integer>();
    	for (int i = 1; i <= n; i++) {
    		par[i] = i;
    		size[i] = 1;
    	}

    	DFS(1, 0, q-1);


    	for (int i = 0; i < qrs.size(); i++) {
    		out.println(ans[qrs.get(i)]);
    	}
   	}

    //<>

   	static ArrayDeque<Integer> st; // stores updates.

   	static void rollback (int moment) { // removes all the updates that happened after moment.
   		while (st.size() > moment) {
   			int a = st.removeLast();
   			size[par[a]] -= size[a];
   			par[a] = a;
   		}
   	}

    static void DFS(int idx, int s, int e) {
    	int moment = st.size();
    	if (s > e) return;
    	for (int i = 0; i < tree[idx].size(); i++) {
    		Node edge = tree[idx].get(i);
    		union(edge.a, edge.b);
    	}
    	if (s == e) {
    		ans[s] = n-st.size();
    	} else {
    		int mid = (s+e)/2;
    		DFS(idx*2, s, mid);
    		DFS(idx*2+1, mid+1, e);
    	}

    	rollback(moment);
    }

    static int[] par, size;

    static int find(int a) {
    	while (a != par[a]) a = par[a];
    	return a;
    }

    static void union(int a, int b) {
    	a = find(a);
    	b = find(b);
    	if (a == b) return;
    	if (size[a] > size[b]) {
    		int tmp = a;
    		a = b;
    		b = tmp;
    	}

    	par[a] = b;
    	size[b] += size[a];
    	st.addLast(a);
    }

   	static void update(int idx, int s, int e, int l, int r, int a, int b) {
   		if (e < l || r < s) return;
   		if (l <= s && e <= r) {
   			tree[idx].add(new Node(a, b));
   			return;
   		}
   		int mid = (s+e)/2;
   		update(idx*2, s, mid, l, r, a, b);
   		update(idx*2+1, mid+1, e, l, r, a, b);
   		return;
   	}

    static long hash(int a, int b) {
    	return 1_000_000L*b+a;
    }

    static class Node {
    	int a, b;

    	Node (int a, int b) {
    		this.a = a;
    		this.b = b;
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
    static void input_output() throws IOException {
        File f = new File("connect.in");
		in = new Reader(new FileInputStream("connect.in"));
        f = new File("connect.out");
        out = new PrintWriter(new File("connect.out"));
    }
}