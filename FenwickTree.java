static class FenwickTree {
    int[] bit;
    int size;

    FenwickTree(int size) {
        bit = new int[size + 1];
        this.size = size;
    }

    int query(int l, int r) {
        if (l > r)
            return 0;
        return query(r) - query(l - 1);
    }

    int query(int idx) {
        int s = 0;
        while (idx > 0) {
            s += bit[idx];
            idx ^= idx & -idx;
        }
        return s;
    }

    void update(int idx, int val) {
        while (idx < bit.length) {
            bit[idx] += val;
            idx += idx & -idx;
        }
    }
}