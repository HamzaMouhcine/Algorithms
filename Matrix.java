public class Matrix {
    static final MOD = (int)1e9 + 7;

    static int add(int x, int y) {
        x += y;
        if (x >= MOD) {
            x -= MOD;
        }
        return x;
    }

    static int sub(int x, int y) {
        x -= y;
        if (x < 0) {
            x += MOD;
        }
        return x;
    }

    static int mul(int x, int y) {
        long result = (long) x * y % MOD;
        return (int) result;
    }

    static int[][] Ident(int n) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }
        return result;
    }

    static int[][] Multy(int[][] a, int[][] b) {
        int na = a.length, ma = a[0].length;
        int nb = b.length, mb = b[0].length;
        assert ma == nb;
        int[][] c = new int[na][mb];
        for (int i = 0; i < na; i++) {
            for (int j = 0; j < mb; j++) {
                for (int k = 0; k < nb; k++) {
                    c[i][j] = add(c[i][j], mul(a[i][k], b[k][j]));
                }
            }
        }
        return c;
    }

    static int[][] Power(int[][] a, long k) {
        int n = a.length;
        int[][] b = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = a[i][j];
            }
        }
        int[][] result = Ident(n);
        for ( ; k > 0; k >>= 1) {
            if (k % 2 == 1) {
                result = Multy(result, b);
            }
            b = Multy(b, b);
        }
        return result;
    }
}
