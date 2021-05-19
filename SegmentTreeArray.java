static class SegmentTree {
	int[] max, lazy;

	SegmentTree() {
		max = new int[4 * n];
		lazy = new int[4 * n];
	}

	void init() {
		for (int i = 0; i < max.length; i++) {
			max[i] = lazy[i] = 0;
		}
	}

	int query(int l, int r) {
		return query(1, 1, n, l, r);
	}

	int query(int node, int tl, int tr, int l, int r) {
		if (r < tl || tr < l)
			return 0;
		if (tl >= l && tr <= r)
			return max[node];
		propagate(node);
		int mid = tl + tr >> 1, left = node << 1, right = left | 1;
		return Math.max(query(left, tl, mid, l, r), query(right, mid + 1, tr, l, r));

	}

	void update(int l, int r, int v) {
		update(1, 1, n, l, r, v);
	}

	void update(int node, int tl, int tr, int l, int r, int v) {
		if (r < tl || tr < l)
			return;
		if (tl >= l && tr <= r) {
			max[node] += v;
			lazy[node] += v;
		} else {
			propagate(node);
			int mid = tl + tr >> 1, left = node << 1, right = left | 1;
			update(left, tl, mid, l, r, v);
			update(right, mid + 1, tr, l, r, v);
			max[node] = Math.max(max[left], max[right]);
		}
	}

	void propagate(int node) {
		int x = lazy[node];
		for (int i = 2 * node; i <= 2 * node + 1; i++) {
			max[i] += x;
			lazy[i] += x;
		}
		lazy[node] = 0;
	}
}