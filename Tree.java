public class Tree {
    private Elem root;
    public Tree(int x){
        this.root = new Elem(x);
    }
    public Elem add(int x,Elem r) {
        if (r == null) {
            r = new Elem(x);
        } else if (x > r.value) {
            r.left = add(x,r.left);
        }else{
            r.right = add(x,r.right);
        }
        return r;
    }

}
