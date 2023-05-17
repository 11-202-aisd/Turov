import java.util.Scanner;

public class Connectivity {
    public static void outElem(int [] arr){
        for (int i = 0;i < arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        int [] arr = new int[n];
        int [] count = new int[n];
        for (int i = 0;i < arr.length;i++){
            arr[i] = i;
            count[i] = 1;
        }
        int a = sc.nextInt();
        int b = sc.nextInt();
        while (a != -1 && b != -1){
            if (arr[a] == a && arr[b] == b){
                arr[a] = b;
                count[b]++;
            }else{
                int i = a;
                while (i != arr[i]){
                    i = arr[i];
                }
                int j = b;
                while (j != arr[j]){
                    j = arr[j];
                }
                if (i != j) {
                    if (count[i] < count[j]) {
                        arr[i] = j;
                        count[i] += count[j];
                    } else {
                        arr[j] = i;
                        count[j] += count[i];
                    }
                }
            }
            a = sc.nextInt();
            b = sc.nextInt();
        }
        outElem(arr);
        outElem(count);
        System.out.println(isUnited(arr));
    }
    public static boolean isUnited(int [] arr){
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        while (i != arr[i]){
            i = arr[i];
        }
        int j = sc.nextInt();
        while (j != arr[j]){
            j = arr[j];
        }
        return i == j;
    }
}
