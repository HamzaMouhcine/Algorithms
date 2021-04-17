import java.util.*;
import java.io.*;
import java.math.*;

public class KhanTopologicalSort {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner in = new Scanner(System.in);

        int nodes = in.nextInt();
        int edges = in.nextInt();

        int[] lvl = new int[nodes + 1];
        PriorityQueue<Integer>[] adj = new PriorityQueue[nodes + 1];

        for (int i = 1 ; i <= nodes ; i++) adj[i] = new PriorityQueue();

        for (int i = 0 ; i < edges ; i++) {
            int u = in.nextInt();
            int v = in.nextInt();

            adj[u].add(v);
            lvl[v]++;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = nodes ; i >= 1 ; i--) {
            if (lvl[i] == 0) {
                // add leafs
                pq.add(i);
            }
        }

        int cnt = 0;
        int e;
        int element;
        while (pq.size() > 0) {
            element = pq.poll();
            q.add(element);

            while (adj[element].size() > 0) {
                e = adj[element].poll();
                lvl[e]--;
                if (lvl[e] == 0) pq.add(e);
            }
            cnt++;
        }

        if (cnt != nodes) {
            System.out.print("Sandro fails.");
        } else {
            System.out.print(q.remove());
            while (q.size() > 0) {
                System.out.print(" " + q.remove());
            }
        }
    }
}