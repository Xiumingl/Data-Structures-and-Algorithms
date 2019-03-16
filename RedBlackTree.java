package RedBlackTreeSpellChecker;

public class RedBlackTree {
	public static final int RED = 1;
	public static final int BLACK = 0;
	private RedBlackNode root;
	private RedBlackNode sentinel;
	private static int recentCompares;
	private static int diameter;

	/**
	 * Create a red black tree.
	 * @big_theta_notation best:成(1) worst:成(1)
	 * 
	 **/
	public RedBlackTree() {
		sentinel = new RedBlackNode("-1", BLACK, null, null, null);
		root = new RedBlackNode(null, BLACK, sentinel, sentinel, sentinel);
		recentCompares = 0;

	}

	/**
	 * returns a value close to v in the tree
	 * 
	 * @precondition The tree is not empty
	 * @big_theta_notation best:成(1) worst:成(log(n))
	 * 
	 **/
	public String closeBy(String v) {
		RedBlackNode x = this.root;
		RedBlackNode y = this.sentinel;
		while (x != this.sentinel) {
			y = x;
			if (x.getData().compareTo(v) < 0) {
				x = x.getRc();
			} else if (x.getData().compareTo(v) > 0) {
				x = x.getLc();
			} else if (x.getData().compareTo(v) == 0) {
				return x.getData();
			}
		}
		return y.getData();

	}

	/**
	 * cehck if the String v is in the RedBlackTree
	 * @big_theta_notation best:成(1) worst:成(log(n))
	 * 
	 **/
	public boolean contains(String v) {
		RedBlackNode r = this.root;
		recentCompares = 0;
		while (r != sentinel) {
			if (r.getData().compareTo(v) == 0) {
				recentCompares += 1;
				return true;
			} else if (r.getData().compareTo(v) < 0) {
				r = r.getRc();
				recentCompares += 1;
			} else if (r.getData().compareTo(v) > 0) {
				r = r.getLc();
				recentCompares += 1;
			}
		}
		return false;
	}

	/**
	 * return recentcompares
	 * @big_theta_notation best:成(1) worst:成(1)
	 * 
	 **/
	public int getRecentCompares() {
		return recentCompares;
	}

	/**
	 * get number of values in the tree.
	 * @big_theta_notation best:成(1) worst:成(1)
	 * 
	 **/
	public int getSize() {
		int size = this.getSize(root);
		return size;
	}

	/**
	 * get number of values in the tree recursively.
	 * @big_theta_notation best:成(n) worst:成(n)
	 * 
	 **/
	public int getSize(RedBlackNode t) {
		if (t != sentinel) {
			int size = 1 + getSize(t.getLc()) + getSize(t.getRc());
			return size;
		} else {
			return 0;
		}

	}

	/**
	 * call the recursive method of height
	 * @big_theta_notation best:成(1) worst:成(1)
	 * 
	 **/
	public int height() {
		int treeHeight = this.height(root) - 1;
		return treeHeight;
	}

	/**
	 * a recursive routine that is used to compute the height of the red black tree
	 * @big_theta_notation best:成(log(n)) worst:成(log(n))
	 * 
	 **/
	public int height(RedBlackNode t) {
		if (t == sentinel) {
			return 0;
		}
		int leftHeight = height(t.getLc());
		int rightHeight = height(t.getRc());
		return Math.max(leftHeight, rightHeight) + 1;
	}

	/**
	 * call the recursive inOrderTraversal(RedBlackNode) - passing the root
	 * @big_theta_notation best:成(1) worst:成(1)
	 * 
	 **/
	public void inOrderTraversal() {
		this.inOrderTraversal(root);
		return;
	}

	/**
	 * Perfrom an inorder traversal of the tree
	 * @big_theta_notation best:成(n) worst:成(n)
	 * 
	 **/
	public void inOrderTraversal(RedBlackNode t) {
		if (t.getLc() != sentinel) {
			inOrderTraversal(t.getLc());
		}
		System.out.println(t.toString());
		if (t.getRc() != sentinel) {
			inOrderTraversal(t.getRc());
		}

	}

	/**
	 * place a data item into the tree
	 * @big_theta_notation best:成(log(n)) worst:成(log(n))
	 * 
	 **/
	public void insert(String value) {
		RedBlackNode newNode = new RedBlackNode(value, RED, sentinel, sentinel, sentinel);
		RedBlackNode x = this.root;
		RedBlackNode y = this.sentinel;
		if (root.getData() == null) {
			root.setData(newNode.getData());
			this.RBInsertFixup(newNode);
		} else {
			while (x != this.sentinel) {
				y = x;
				if (x.getData().compareTo(value) < 0) {
					x = x.getRc();
				} else if (x.getData().compareTo(value) > 0) {
					x = x.getLc();
				} else if (x.getData().compareTo(value) == 0) {
					return;
				}
			}
			if (value.compareTo(y.getData()) < 0) {
				y.setLc(newNode);
				newNode.setP(y);
			} else {
				y.setRc(newNode);
				newNode.setP(y);
			}
			this.RBInsertFixup(newNode);
		}
	}

	/**
	 * perform a single left rotation.
	 * @precondition The The node has right child
	 * @big_theta_notation best:成(1) worst:成(1)
	 * 
	 **/
	public void leftRotate(RedBlackNode x) {
		RedBlackNode y = x.getRc();
		x.setRc(y.getLc());
		y.getLc().setP(x);
		y.setP(x.getP());
		if (x.getP() == this.sentinel) {
			this.root = y;
		} else if (x == x.getP().getLc()) {
			x.getP().setLc(y);
		} else {
			x.getP().setRc(y);
		}
		y.setLc(x);
		x.setP(y);

	}

	/**
	 * display the RedBlackTree in level order
	 * @big_theta_notation best:成(n) worst:成(n)
	 * 
	 **/
	public void levelOrderTraversal() {
		Queue q = new Queue();
		if (this.root == sentinel) {
			return;
		}
		q.enQueue(this.root);
		while (!q.isEmpty()) {
			RedBlackNode currentNode = (RedBlackNode) q.deQueue();
			System.out.println(currentNode.toString());
			if (currentNode.getLc() != sentinel) {
				q.enQueue(currentNode.getLc());
			}
			if (currentNode.getRc() != sentinel) {
				q.enQueue(currentNode.getRc());
			}

		}

	}
	
	/**
	 * Fixing up the tree 
	 * @postcondition  Red Black Properties are preserved
	 * @big_theta_notation best:成(1) worst:成(log(n)))
	 * 
	 **/
	public void RBInsertFixup(RedBlackNode z) {
		while (z.getP().getColor() == RED) {
			if (z.getP() == z.getP().getP().getLc()) {
				RedBlackNode y = z.getP().getP().getRc();
				if (y.getColor() == RED) {
					z.getP().setColor(BLACK);
					y.setColor(BLACK);
					z.getP().getP().setColor(RED);
					z = z.getP().getP();
				} else {
					if (z == z.getP().getRc()) {
						z = z.getP();
						this.leftRotate(z);
					}
					z.getP().setColor(BLACK);
					z.getP().getP().setColor(RED);
					this.rightRotate(z.getP().getP());
				}
			} else {
				RedBlackNode y = z.getP().getP().getLc();
				if (y.getColor() == RED) {
					z.getP().setColor(BLACK);
					y.setColor(BLACK);
					z.getP().getP().setColor(RED);
					z = z.getP().getP();

				} else {
					if (z == z.getP().getLc()) {
						z = z.getP();
						this.rightRotate(z);
					}
					z.getP().setColor(BLACK);
					z.getP().getP().setColor(RED);
					this.leftRotate(z.getP().getP());
				}
			}
		}
		this.root.setColor(BLACK);

	}

	/**
	 * call the recursive reverseOrderTraversal(RedBlackNode) - passing the root
	 * @big_theta_notation best:成(1) worst:成(1)
	 * 
	 **/
	public void reverseOrderTraversal() {
		this.reverseOrderTraversal(root);
		return;

	}

	/**
	 * Perform a reverseOrder traversal of the tree.
	 * @big_theta_notation best:成(n) worst:成(n)
	 * 
	 **/
	public void reverseOrderTraversal(RedBlackNode t) {
		if (t.getRc() != sentinel) {
			inOrderTraversal(t.getRc());
		}
		System.out.println(t.toString());
		if (t.getLc() != sentinel) {
			inOrderTraversal(t.getLc());
		}

	}

	/**
	 * perform a single right rotation
	 * @precondition The node has left child
	 * @big_theta_notation best:成(1) worst:成(1)
	 * 
	 **/
	public void rightRotate(RedBlackNode x) {
		RedBlackNode y = x.getLc();
		x.setLc(y.getRc());
		y.getRc().setP(x);
		y.setP(x.getP());
		if (x.getP() == this.sentinel) {
			this.root = y;
		} else if (x == x.getP().getLc()) {
			x.getP().setLc(y);
		} else {
			x.getP().setRc(y);
		}
		y.setRc(x);
		x.setP(y);

	}

	/**
	 * get the diameter of the tree
	 * @big_theta_notation best:成(1) worst:成(1)
	 * 
	 **/
	public int getDiameter() {
		this.depth(root);
		return diameter;

	}

	/**
	 *  get the diameter of the tree recursively
	 * @big_theta_notation best:成(log(n)) worst:成(log(n))
	 * 
	 **/
	public int depth(RedBlackNode t) {
		if (t == sentinel) {
			return 0;
		}
		int leftDepth = depth(t.getLc());
		int rightDepth = depth(t.getRc());
		diameter = Math.max(diameter, leftDepth + rightDepth);
		return Math.max(leftDepth, rightDepth) + 1;
	}

	public static void main(String[] args) {
		RedBlackTree rbt = new RedBlackTree();

		for (int j = 1; j <= 5; j++)
			rbt.insert("" + j);

		// after 1..5 are inserted
		System.out.println("RBT in order");
		rbt.inOrderTraversal();
		System.out.println("RBT level order");
		rbt.levelOrderTraversal();

		// is 3 in the tree

		if (rbt.contains("" + 3))
			System.out.println("Found 3");
		else
			System.out.println("No 3 found");

		// display the height
		System.out.println("The height is " + rbt.height());

	}

}
