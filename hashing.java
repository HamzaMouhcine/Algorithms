import java.util.*;
import java.io.*;
import java.math.*;

public class Hashing {
    static final int MOD = 1000000321 ;
    static int n;
    static int m;
    static long[] pwr;

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        Reader in = new Reader();
        Hashing solver = new Hashing();
        solver.solve(out, in);
        out.flush();
        out.close();
    }

    void solve(PrintWriter out, Reader in) throws IOException {
        n = in.nextInt();
        char[] str = in.next().toCharArray();
        char[] rstr = new char[n];

        m = in.nextInt();
        char[] pat = in.next().toCharArray();
        char[] rpat = new char[m];

        if (m > n) {
            out.println(0);
            return;
        }

        long[] str_hash = new long[n];
        long[] rstr_hash = new long[n];
        long[] pat_hash = new long[m];
        long[] rpat_hash = new long[m];

        pwr = new long[n];
        long p = 1, pow = 31;

        for (int i = 0; i < n; i++) {
            str[n - 1 - i] -= 'a' - 1;
            rstr[i] = str[n - 1 - i];
        }

        str_hash[0] = str[0];
        rstr_hash[0] = rstr[0];
        pwr[0] = 1;
        for (int i = 1; i < n; i++) {
            pwr[i] = p = (p * pow) % MOD;
            str_hash[i] = (str_hash[i - 1] + (p * str[i]) % MOD) % MOD;
            rstr_hash[i] = (rstr_hash[i - 1] + (p * rstr[i]) % MOD) % MOD;
        }

        for (int i = 0; i < m; i++) {
            pat[m - 1 - i] -= 'a' - 1;
            rpat[i] = pat[m - 1 - i];
        }

        pat_hash[0] = pat[0];
        rpat_hash[0] = rpat[0];

        for (int i = 1; i < m; i++) {
            pat_hash[i] = (pat_hash[i - 1] + (pwr[i] * pat[i]) % MOD) % MOD;
            rpat_hash[i] = (rpat_hash[i - 1] + (pwr[i] * rpat[i]) % MOD) % MOD;
        }

        int[] valid = new int[n];
        // valid[i] == 0  -- str[i,i+1,..,i+m-1] is valid only if we change one char.
        // valid[i] == 1  -- str[i,i+1,..,i+m-1] is valid without changing anything.
        // valid[i] == -1 -- str[i,i+1,..,i+m-1] is not valid even if we change one char.
        int left = 0;
        int right = 0;

        for (int i = 0; i <= n - m; i++) {
            left  = longestCommonPrefix(i, str_hash, pat_hash);
            right = longestCommonPrefix(n - 1 - (i + m - 1), rstr_hash, rpat_hash);

            if (left + right == m - 1)     valid[i] = 1;
            else if (left + right < m - 1) valid[i] = -1;
        }

        int changed = 0;
        int notChanged = 0;
        int ans = 0;

        for (int i = 0; i < m && i <= n - m; i++) {
            changed = 0; notChanged = 0;
            for (int j = i; j <= n - m; j += m) {
                if (valid[j] == 0) {
                    notChanged++;
                    changed++;
                } else if (valid[j] == -1) {
                    notChanged = 0;
                    changed    = 0;
                } else {
                    changed    = notChanged + 1;
                    notChanged = 0;
                }
                ans = Math.max(ans, changed);
                ans = Math.max(ans, notChanged);
            }
        }

        out.println(ans);
    }

    static int longestCommonPrefix(int s, long[] hash1, long[] hash2) {
        int lo = 0, hi = m - 1, mid = 0, res = 0;

        while (lo <= hi) {
            mid = (lo + hi) / 2;

            if (evalHash(hash1, s, s + mid) == evalHash(hash2, 0, mid)) {
                lo = mid + 1;
                res = mid + 1;
            } else hi = mid - 1;
        }

        return res;
    }

    static long evalHash (long[] hash, int s, int e) {
        long res = pwr[n - 1 - s];

        if (s == 0) {
            return (res * hash[e]) % MOD;
        } else {
            return (res * ((hash[e] - hash[s - 1] + MOD) % MOD)) % MOD;
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