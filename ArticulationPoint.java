import java.util.*;
import java.io.*;
import java.math.*;

public class ArticulationPoint {
	public static void main(String args[]) throws Exception {
		Scanner in = new Scanner(new File("in.txt"));
		int nodes = in.nextInt();
		int edges = in.nextInt();

		Graph gr = new Graph(nodes, edges);

		for (int i = 0 ; i < edges; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			gr.addEdge(u, v);
		}


		System.out.print("Articulation Points:");
		ArrayList<Integer> articulationPoints = gr.findArticulationPoints(1);
		for (int i = 0; i < articulationPoints.size(); i++) {
			System.out.print(" " + articulationPoints.get(i));
		}
		System.out.println();
	}

	static class Graph {
		public int nodes, edges;
		public List<Integer> adj[];
		public int[] disc, low;
		public int time;

		Graph (int nodes, int edges) {
			this.nodes = nodes;
			this.edges = edges;
			adj = new ArrayList[nodes + 1];
			for (int i = 1; i <= nodes; i++) adj[i] = new ArrayList<Integer>();

			disc = new int[nodes + 1];
			low = new int[nodes + 1];
			time = 0;
		}

		public void addEdge(int u, int v) {
			adj[u].add(v);
			adj[v].add(u);
		}

		public ArrayList<Integer> findArticulationPoints(int s) {
			ArrayList<Integer> articulationPoints = new ArrayList<>();
			boolean[] visited = new boolean[nodes + 1];
			int[] parent = new int[nodes + 1];
			DFS(1, visited, parent, articulationPoints);

			return articulationPoints;
		}

		private void DFS(int s, boolean[] visited, int[] parent, ArrayList<Integer> articulationPoints) {
			visited[s] = true;
			disc[s] = low[s] = time++;
			int childs = 0;

			for (int e : adj[s]) {
				if (!visited[e]) {
					childs++;
					parent[e] = s;
					DFS(e, visited, parent, articulationPoints);

					low[s] = Math.min(low[s], low[e]);

					if (parent[s] == 0 && childs > 1) articulationPoints.add(s);
					if (parent[s] != 0 && low[e] >= disc[s]) articulationPoints.add(s);
				} else if (parent[s] != e) {
					low[s] = Math.min(low[s], disc[e]);
				}

			}
		}

	}
}
