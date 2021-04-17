import java.io.*;
import java.util.*;

public class TrieXor {
    static TrieNode root;

    public static void main(String[] args) {
        FastReader in = new FastReader();
        root = new TrieNode();

        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            insert(in.nextInt());
        }

        int res;
        for (int i = 0; i < n; i++) {
            System.out.print(search(arr[i]) + " ");
        }
        System.out.println();
    }

    static class TrieNode {
        TrieNode left, right;
        int cnt;

        TrieNode() {
            this.cnt = 0;
        }
    }

    static void insert(int key) {
        int idx;
        TrieNode mark = root;

        for (int lvl = 29; lvl >= 0; lvl--) {
            idx = (key >> lvl) & 1;

            if (idx == 0) {
                if (mark.left == null) {
                    mark.left = new TrieNode();
                }
                mark = mark.left;
            } else {
                if (mark.right == null) {
                    mark.right = new TrieNode();
                }
                mark = mark.right;
            }
            mark.cnt++;
        }
    }

    // Searching for the min xor.
    static int search(Integer key) {
        int res = 0;
        int idx;
        TrieNode mark = root;

        for (int lvl = 29; lvl >= 0; lvl--) {
            idx = (key >> lvl) & 1;

            if (idx == 0) {
                if (mark.left != null && mark.left.cnt != 0) {
                    mark = mark.left;
                } else {
                    res += 1 << lvl;
                    mark = mark.right;
                }
            } else {
                if (mark.right != null && mark.right.cnt != 0) {
                    mark = mark.right;
                } else {
                    res += 1 << lvl;
                    mark = mark.left;
                }
            }
            mark.cnt--;
        }
        return res;
    }

    static class FastReader  {
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
