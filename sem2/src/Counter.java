import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Counter {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("timeofsearch.txt"));
        int i = 0;
        int sum = 0;
        do{
            sum+=Integer.parseInt(sc.nextLine());
            i++;
        }while (sc.hasNextLine());
        System.out.println(sum/i);
    }
}
