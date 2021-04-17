import java.util.*;
import java.io.*;
import java.math.*;

public class DijkstraPq {
	public static void main(String args[]) throws Exception {
		Scanner in = new Scanner(new File("in.txt"));

		int nodes = in.nextInt();
		int edges = in.nextInt();

		Graph gr = new Graph(nodes, edges);

		for (int i = 0; i < edges; i++) {
			int u = in.nextInt(),
			    v = in.nextInt(),
			    cost = in.nextInt();
			gr.addEdge(u, v, cost);
		}

		gr.shortPath(1);

		for (int i = 1; i <= nodes; i++) {
			System.out.println("Shortest path 1 -> " + i + " = " + gr.distance[i]);
		}
	}

	static class Graph {
		public int nodes, edges, distance[];
		public List<Edge> adj[];

		Graph (int nodes, int edges) {
			this.nodes = nodes;
			this.edges = edges;
			distance = new int[nodes + 1];
			adj = new ArrayList[nodes + 1];
			for (int i = 1; i <= nodes; i++) {
				adj[i] = new ArrayList<Edge>();
				distance[i] = Integer.MAX_VALUE;
			}
		}

		public void addEdge(int u, int v, int unites) {
			adj[u].add(new Edge(v, unites));
			adj[v].add(new Edge(u, unites));
		}

		// Calculates the shortest path between s and all the other nodes.
		public void shortPath(int s) {
			PriorityQueue<Edge> q = new PriorityQueue<Edge>();
			q.add(new Edge(s, 0));

			distance[s] = 0;
			while (q.size() > 0) {
				Edge edge = q.remove();
				int element = edge.to;
				if (edge.weight > distance[edge.to]) continue;

				for (Edge e : adj[element]) {
					if (distance[element] + e.weight < distance[e.to]) {
						distance[e.to] = distance[element] + e.weight;
						q.add(new Edge(e.to, distance[e.to]));
					}

				}
			}
		}
	}

	private static class Edge implements Comparable<Edge> {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge e) {
			// compare Edges with respect to their weights increasing
			return this.weight - e.weight;
		}

		@Override
		public String toString() {
			return "(" + this.to + ", " + this.weight + ")";
		}
	}
}
