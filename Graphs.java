import java.util.Scanner;
public class Graphs {
    public static void outElem(int [] arr){
        for (int i = 0;i < arr.length;i++){
            System.out.print(arr[i] + " ");
        }
    }
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int len = sc.nextInt();
       int [] arr = new int[sc.nextInt()];
       for(int i = 0;i < arr.length;i++){
           arr[i] = i;
       }
       for(int d = 0;d < len;d++){
           System.out.println("Введите связанные вершины");
           int a = sc.nextInt();
           int b = sc.nextInt();
           for(int i = 0;i < arr.length;i++){
               int k = arr[b];
               if(arr[i] == k){
                   arr[i] = arr[a];
               }
           }
       }
        System.out.println("Введите вершины для проверки");
        int a = sc.nextInt();
        int b = sc.nextInt();
        if(arr[a] == arr[b]){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
        outElem(arr);
    }
}
