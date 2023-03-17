public class StackQueue {
    private Stack s1;
    private Stack s2;
    private final int capacity;
    private int size;
    public  StackQueue(int n){
        s1 = new Stack(n);
        s2 = new Stack(n);
        capacity = n;
        size = 0;
    }
    public int look() throws Exception{
        if(isEmpty()){
            throw new Exception("Stack is empty");
        }
        if(s2.isEmpty()) {
            swap(s1, s2);
        }
        return s2.look();
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void add(int x) throws Exception{
        if(size == capacity){
            throw new Exception("Queue is filled");
        }else{
            s1.add(x);
            size++;
        }
    }
    private void swap(Stack s1,Stack s2) throws Exception{
        while (!s1.isEmpty()){
            s2.add(s1.take());
        }
    }
    public int pop() throws Exception{
        if(isEmpty()){
            throw new Exception("Stack is empty");
        }
        if(s2.isEmpty()) {
            swap(s1, s2);
        }
        return s2.take();
    }

    public static void main(String[] args) throws Exception{
        StackQueue sq = new StackQueue(4);
        sq.add(1);
        sq.add(2);
        sq.add(3);
        sq.add(4);
        System.out.println(sq.pop());
        System.out.println(sq.pop());
        System.out.println(sq.pop());
        System.out.println(sq.pop());
    }
}
