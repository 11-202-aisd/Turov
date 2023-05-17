public class BST {
    public Node insert(int x, Node cur)
    {
        if(cur == null)
        {
            Node newNode = new Node(x);
            return newNode;
        }
        else
        {
            if(x < cur.value)
                cur.left = insert(x, cur.left);
            else
                cur.right = insert(x, cur.right);
        }
        return cur;
    }
    public static boolean contains(int x, Node cur)
    {
        if(cur == null)
            return false;
        if(cur.value == x)
            return true;
        else
        {
            if(x < cur.value)
                return contains(x, cur.left);
            else
                return contains(x, cur.right);
        }
    }
    public static Node delete(int x, Node cur)
    {
        if(cur == null)
            return cur;
        if(x < cur.value)
            cur.left = delete(x, cur.left);
        else if(x > cur.value)
            cur.right = delete(x, cur.right);
        else if(x == cur.value)
        {
            if(cur.left != null && cur.right != null)
            {
                int min = cur.right.value;
                Node temp = cur.right;
                while(temp.left != null)
                {
                    min = temp.left.value;
                    temp = temp.left;
                }
                cur.value = min;
                cur.right = delete(min, cur.right);
            }
            else if(cur.left == null)
                return cur.right;
            else if(cur.right == null)
                return cur.left;
            else
                return null;
        }
        return cur;
    }
    public static void main(String[] args) {
        Node root = new Node(5);
        BST bst = new BST();
        bst.insert(10,root);
        bst.insert(1,root);
        bst.insert(7,root);
        System.out.println(bst.contains(7,root));
        bst.delete(7,root);
        System.out.println(bst.contains(7,root));
        System.out.println(bst.contains(11,root));
    }
}
