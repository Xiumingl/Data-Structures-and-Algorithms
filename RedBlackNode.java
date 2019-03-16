package RedBlackTreeSpellChecker;

public class RedBlackNode {
	public static final int RED = 1;
	public static final int BLACK = 0;
	private String data;
	private int color;
	private RedBlackNode p;
	private RedBlackNode lc;
	private RedBlackNode rc;

	public RedBlackNode(String data, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
		this.data = data;
		this.color = color;
		this.p = p;
		this.lc = lc;
		this.rc = rc;
	}

	@Override
	public String toString() {
		String color;
		if(this.color == 1) {
			color = "Red";	
		}else {
			color = "Black";
		}
		String node = "[ data = " + this.data + "; Color = " + color + "; Parent = " + this.p.data + "; LC = "
				+ this.lc.data + "; RC = " + this.rc.data + "]";
		return node;
	}

	public int getColor() {
		return this.color;
	}

	public String getData() {
		return this.data;
	}

	public RedBlackNode getLc() {
		return this.lc;
	}

	public RedBlackNode getP() {
		return this.p;
	}

	public RedBlackNode getRc() {
		return this.rc;
	}

	public void setColor(int color) {
		this.color = color;

	}

	public void setData(String data) {
		this.data = data;
	}

	public void setLc(RedBlackNode lc) {
		this.lc = lc;

	}

	public void setP(RedBlackNode p) {
		this.p = p;
	}

	public void setRc(RedBlackNode rc) {
		this.rc = rc;

	}

}
