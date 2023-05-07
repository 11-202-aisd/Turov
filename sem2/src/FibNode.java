public class FibNode {
    public int key;
    public int degree;
    public FibNode left;
    public FibNode right;
    public FibNode child;
    public FibNode parent;
    public boolean marked;

    public FibNode(int key) {
        this.key = key;
        this.degree = 0;
        this.marked = false;
        this.left = this;
        this.right = this;
        this.parent = null;
        this.child = null;
    }
}
