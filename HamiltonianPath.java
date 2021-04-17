import java.io.*;
import java.util.*;

public class HamiltonianPath {
    public static void main(String[] args) {
        FastReader in = new FastReader();

        int nodes = in.nextInt();
        int edges = in.nextInt();

        Graph g = new Graph(nodes);
        for (int i = 0 ; i < edges ; i++) {
            int u = in.nextInt();
            int v = in.nextInt();

            g.addEdge(u, v);
        }

        if (g.Hamiltonian_Paths(arr)) System.out.println("YES");
        else System.out.println("NO");
    }

    static class Graph {
        int nodes;
        LinkedList<Integer> adj[] ;

        Graph(int nodes) {
            this.nodes = nodes;
            adj = new LinkedList[nodes + 1];

            for (int i = 1; i <= nodes; i++) {
                adj[i] = new LinkedList<Integer>();
            }
        }

        public void addEdge(int u, int v) {
            adj[u].add(v);
            adj[v].add(u);
        }

        public void BFS(int s) {
            boolean[] visited = new boolean[nodes + 1];
            Queue<Integer> q = new LinkedList<Integer>();

            visited[s] = true;
            q.add(s);
            int element;
            while (q.size() != 0) {
                element = q.remove();

                for (int e : adj[element]) {
                    if (!visited[e]) {
                        visited[e] = true;
                        q.add(e);
                    }
                }
            }

        }

        boolean Hamiltonian_Paths(boolean[][] adj) {
            boolean[][] dp = new boolean[this.nodes][1 << this.nodes];

            for (int i = 0; i < nodes; i++) {
                dp[i][1 << i] = true;
            }

            for (int i = 0; i < (1 << nodes); i++) {
                for (int j = 0; j < nodes; j++) {
                    if ((i & (1 << j)) > 0) {
                        for (int k = 0; k < nodes; k++) {
                            if ((i & (1 << k)) > 0 && adj[k][j] && k != j && dp[k][i ^ (1 << j)]) {
                                dp[j][i] = true;
                                break;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < nodes; i++) {
                if (dp[i][(1 << nodes) - 1]) {
                    return true;
                }
            }

            return false;
        }

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
