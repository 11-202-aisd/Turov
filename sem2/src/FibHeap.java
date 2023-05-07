import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FibHeap {
    private int keyNum;
    private FibNode min;
    private int count;
    public FibHeap() {
        keyNum = 0;
        min = null;
        count = 0;
    }
    private void removeNode(FibNode node) {
        node.left.right = node.right;
        node.right.left = node.left;
    }
    private void addNode(FibNode node, FibNode root) {
        node.left = root.left;
        root.left.right = node;
        node.right = root;
        root.left = node;
    }
    private void insert(FibNode node) {
        if (keyNum == 0) {
            min = node;
        } else {
            addNode(node, min);
            if (node.key < min.key) {
                min = node;
            }
        }
        keyNum++;
    }
    public void insert(int key) {
        FibNode node = new FibNode(key);
        if (node == null) {
            return;
        }
        insert(node);
    }
    private FibNode extractMin() {
        FibNode p = min;
        if (p == p.right) {
            min = null;
        }else{
            removeNode(p);
            min = p.right;
        }
        p.left = p.right = p;
        return p;
    }
    private void link(FibNode node, FibNode root) {
        removeNode(node);
        if (root.child == null) {
            root.child = node;
        }else {
            addNode(node, root.child);
        }
        node.parent = root;
        root.degree++;
        node.marked = false;
    }
    private void consolidate() throws IOException{
        int maxDegree = (int) Math.floor(Math.log(keyNum) / Math.log(2.0));
        int D = maxDegree + 1;
        FibNode[] cons = new FibNode[D + 1];
        for (int i = 0; i < D; i++) {
            cons[i] = null;
        }
        while (min != null) {
            FibNode x = extractMin();
            int d = x.degree;
            while (cons[d] != null) {
                this.count++;
                FibNode y = cons[d];
                if (x.key > y.key) {
                    FibNode tmp = x;
                    x = y;
                    y = tmp;
                }
                link(y, x);
                cons[d] = null;
                d++;
            }
            cons[d] = x;
        }
        min = null;
        for (int i = 0; i < D; i++) {
            count++;
            if (cons[i] != null) {
                if (min == null) {
                    min = cons[i];
                } else {
                    addNode(cons[i], min);
                    if ((cons[i]).key < min.key) {
                        min = cons[i];
                    }
                }
            }
        }
    }
    public void removeMin() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("quantityofdel.txt",true));
        if (min==null) {
            return;
        }
        FibNode m = min;
        while (m.child != null) {
            this.count++;
            FibNode child = m.child;
            removeNode(child);
            if (child.right == child) {
                m.child = null;
            }else {
                m.child = child.right;
            }
            addNode(child, min);
            child.parent = null;
        }
        removeNode(m);
        if (m.right == m) {
            min = null;
        } else {
            min = m.right;
            consolidate();
        }
        keyNum--;
        m = null;
        bw.write(this.count + "\n");
        bw.flush();
        bw.close();
    }
    public int minimum() {
        if (min==null) {
            return -1;
        }
        return min.key;
    }
    private void renewDegree(FibNode parent, int degree) {
        parent.degree -= degree;
        if (parent. parent != null) {
            renewDegree(parent.parent, degree);
        }
    }
    private void cut(FibNode node, FibNode parent) {
        removeNode(node);
        renewDegree(parent, node.degree);
        if (node == node.right) {
            parent.child = null;
        } else {
            parent.child = node.right;
        }
        node.parent = null;
        node.left = node.right = node;
        node.marked = false;
        addNode(node, min);
    }
    private void cascadingCut(FibNode node) {
        FibNode parent = node.parent;
        if (parent != null) {
            if (node.marked == false) {
                node.marked = true;
            }else{
                cut(node, parent);
                cascadingCut(parent);
            }
        }
    }
    private void decrease(FibNode node, int key) {
        if (min==null ||node==null) {
            return;
        }
        if (key > node.key) {
            return ;
        }
        FibNode parent = node.parent;
        node.key = key;
        if (parent!=null && (node.key < parent.key)) {
            cut(node, parent);
            cascadingCut(parent);
        }
        if (node.key < min.key) {
            min = node;
        }
    }
    public static int generate(){
        return (int)(Math.random()*1000) + 1;
    }
    public static int [] returnArr() {
        int [] arr = new int[10000];
        for (int i = 0;i < arr.length;i++){
            arr[i] = generate();
        }
        return arr;
    }
    public static void main(String[] args) throws IOException {
        /*У меня в программе реализовано добавление элемента, удаление и получение минимума, объединение куч.
        * Остальные операции не реализованы, так как алгоритм используется обычно для быстрого получения минимума*/
        FibHeap fh = new FibHeap();
        int[] arr = returnArr();
        BufferedWriter bw = new BufferedWriter(new FileWriter("timeofinster.txt",true));
        BufferedWriter bw1 = new BufferedWriter(new FileWriter("timeofsearch.txt",true));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("timeofdel.txt",true));
        for (int i = 0; i < arr.length; i++) {
            long start = System.nanoTime();
            fh.insert(arr[i]);
            // Добавление происходит без циклов, количество действий считать не нужно
            long finish = System.nanoTime();
            int dif = (int) (finish - start);
            bw.write(dif + "\n");
            bw.flush();
        }
        bw.close();
        for (int i = 0; i < 100; i++) {
            long start = System.nanoTime();
            fh.minimum();
            //Поиск минимума происходит без циклов, считать количество действий не нужно
            long finish = System.nanoTime();
            int dif = (int) (finish - start);
            bw1.write(dif +"\n");
            bw1.flush();
        }
        bw1.close();
        for (int i = 0; i < 1000; i++) {
            long start = System.nanoTime();
            fh.removeMin();
            long finish = System.nanoTime();
            int dif = (int) (finish - start);
            bw3.write(dif +"\n");
            bw3.flush();
        }
        bw3.close();

    }
}