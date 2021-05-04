public static Random gen = new Random();

static public class Node {
	long value;
	long priority;
	Node left, right, parent;
	int count;
	boolean toProp;

	Node(long value) {
		this.value = value;
		priority = gen.nextLong();
		toProp = false;
		recalc(this);
	}
}

static Node recalc(Node a) { // recalculates all the data of Node a using both children.
	if (a == null) return null;
	if (a.left != null) prop(a.left);
	if (a.right != null) prop(a.right);

	a.count = 1;
	if (a.left != null) {
		a.count += a.left.count;
	}
	if (a.right != null) {
		a.count += a.right.count;
	}
	return a;
}

static int count(Node a) {
	return a == null ? 0 : a.count;
}

static void setParent(Node a, Node par) {
	if (a != null) a.parent = par;
}

static Node merge(Node a, Node b) {   // if I want the inorder of the treap to be sorted: a.values < b.values must be verified
	if (b == null) return a;
	if (a == null) return b;
	prop(a); 
	prop(b);

	if (a.priority > b.priority) {
		setParent(a.right, null);
		setParent(b, null);
		a.right = merge(a.right, b);
		setParent(a.right, a);
		return recalc(a);
	} else {
		setParent(a, null);
		setParent(b.left, null);
		b.left = merge(a, b.left);
		setParent(b.left, b);
		return recalc(b);
	}
}

static Node[] split(Node a, int k) {  // split a into two nodes: first node represents first k elements
									  // and the second node represents the remaining elements
	if (a == null) return new Node[]{null, null};
		prop(a);

	if (k <= count(a.left)) {
		setParent(a.left, null);
		Node[] sp = split(a.left, k);
		a.left = sp[1];
		setParent(a.left, a);
		sp[1] = recalc(a);
		return sp;
	} else {
		setParent(a.right, null);
		Node[] sp = split(a.right, k-count(a.left)-1);
		a.right = sp[0];
		setParent(a.right, a);
		sp[0] = recalc(a);
		return sp;
	}
}

static void prop(Node a) { // updates the value in this node, and pushes the updates to the children.
	if (a == null || a.toProp == false) return;

	a.toProp = false;	
	// update


	// push updates
	if (a.left != null) { 
		a.left.toProp = !a.left.toProp;
	}
	if (a.right != null) {
		a.right.toProp = !a.right.toProp;
	}
}

static Node insertb(Node root, Node x) {
	int idx = lowerBound(root, x.value);
	return insert(root, idx, x);
}

static Node insert(Node a, int k, Node b) {  // k =>  0-indexed
	if (a == null) return b;
	if (b == null) return a;
	prop(a);
	prop(b);

	if (b.priority < a.priority) {
		if (k <= count(a.left)) {
			a.left = insert(a.left, k, b);
			setParent(a.left, a);
		} else {
			a.right = insert(a.right, k-count(a.left)-1, b);
			setParent(a.right, a);
		}
		return recalc(a);
	} else {
		Node[] ch = split(a, k);
		b.left = ch[0];
		b.right = ch[1];
		setParent(b.left, b);
		setParent(b.right, b);
		return recalc(b);
	}
}

static int lowerBound(Node a, long q) {
	int lcount = 0;
	prop(a);

	while (a != null) {
		if (a.value >= q) {
			a = a.left;
		} else {
			lcount += count(a.left) + 1;
			a = a.right;
		}
	}
	return lcount;
}

// delete k-th (0-indexed)
static Node erase(Node a, int k) {
	if (a == null) return null;
	prop(a);

	if (k < count(a.left)) {
		a.left = erase(a.left, k);
		setParent(a.left, a);
		return recalc(a);
	} else if (k == count(a.left)) {
		setParent(a.left, null);
		setParent(a.right, null);
		Node aa = merge(a.left, a.right);
		disconnect(a);
		return aa;
	} else {
		a.right = erase(a.right, k-count(a.left)-1);
		setParent(a.right, a);
		return recalc(a);
	}
}

static void disconnect(Node a) {
	if (a == null) return;
	a.left = a.right = a.parent = null;
}

static void bfsNode(Node a) {
	ArrayDeque<Node> dq = new ArrayDeque<>();
	dq.addFirst(a);

	while (dq.size() != 0) {
		Node rem = dq.removeFirst();
		printNode(rem);
		if (rem.left != null) dq.addLast(rem.left);
		if (rem.right != null) dq.addLast(rem.right);
	}
}

static void dfsNode(Node a) {
	if (a.left != null) dfsNode(a.left);
	printNode(a);
	if (a.right != null) dfsNode(a.right);
}

static void printNode(Node a) {
	out.println(a.value+" pro: "+a.priority+", size: "+a.count);
	if (a.left != null) out.println("left: "+a.left.priority);
	if (a.right != null) out.println("right: "+a.right.priority);
}