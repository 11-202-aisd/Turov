import java.util.Deque;

public class DequeLevit{
    int size;
    private int headFirst;
    private int headLast;
    private int [] arr;
    int capacity;
    public DequeLevit(int n) {
        this.arr = new int[n];
        this.capacity = n;
        this.headFirst = n/2;
        this.headLast = this.headFirst;
        this.size = 0;
    }
    private void changeFirst(){
        if(headFirst == capacity){
            headFirst = 0;
        }
        if(headFirst == -1){
            headFirst = capacity - 1;
        }
    }
    private void changeLast(){
        if(headLast == capacity){
            headLast = 0;
        }
        if(headLast == -1){
            headLast = capacity - 1;
        }
    }
    public int lookFirst(){
        int k = 0;
        if(headFirst == 0){
            k = arr[capacity - 1];
        }else{
            changeFirst();
            k = arr[headFirst];
        }
        return k;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void getFirst(){
        changeFirst();
        arr[headFirst] = 0;
        headFirst--;
        changeFirst();
        size--;
    }
    public void putFirst(int x){
        headFirst++;
        changeFirst();
        arr[headFirst] = x;
        size++;
    }
    public void putLast(int x){
        arr[headLast] = x;
        headLast--;
        changeLast();
        size++;
    }
}
