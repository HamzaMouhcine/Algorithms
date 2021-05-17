static class Scc {
        ArrayList<Integer>[] adj;
        int[] from, to;
        boolean[] vis;
        ArrayDeque<Integer> st;
        int n;

        // this creates a new adj, so it doesn't affect the original adj
        public Scc(ArrayList<Integer>[] adj, int[] from, int[] to, int n) {
            this.adj = new ArrayList[n+1];
            this.from = from;
            this.to = to;
            this.n = n;
            for (int i = 1; i <= n; i++) 
                this.adj[i] = (ArrayList<Integer>)adj[i].clone();

        }

        public ArrayList<ArrayList<Integer>> components() {
            ArrayList<ArrayList<Integer>> sccs = new ArrayList<>();

            // ordering the nodes.
            st = new ArrayDeque<Integer>();
            vis = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!vis[i]) {
                    pushElements(i);
                }
            }

            for (int i = 1; i <= n; i++) adj[i].clear();
            // reversing the original graph.
            for (int i = 0; i < from.length; i++) {
                adj[to[i]].add(from[i]);
            }

            ArrayList<Integer> newScc = new ArrayList<Integer>();
            vis = new boolean[n + 1];
            int element;
            while (st.size() != 0) {        // finding each scc.
                element = st.removeLast();
                newScc = new ArrayList<Integer>();
                if (vis[element]) continue;
                findScc(element, newScc);

                // deal with the new scc here.
                sccs.add(newScc);
            }

            return sccs;
        }

        public void pushElements(int s) {
            vis[s] = true;

            for (int e : adj[s]) {
                if (!vis[e]) {
                    vis[e] = true;
                    pushElements(e);
                }
            }
            st.addLast(s);
        }

        public void findScc(int s, ArrayList<Integer> newScc) {
            vis[s] = true;
            newScc.add(s);

            for (int e : adj[s]) {
                if (!vis[e]) {
                    vis[e] = true;
                    findScc(e, newScc);
                }
            }
        }
    }