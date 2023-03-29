import java.io.File;
import java.lang.String;
import java.util.Scanner;

import static java.lang.Math.*;

public class Levit {
    public static void out(int [] arr){
        for(int p:arr){
            System.out.println(p);
        }
    }
    public static void way(int [] par){
        for (int i = 1;i < par.length;i++){
            System.out.println();
            int p = i;
            System.out.print(i + " ");
            while (p != 0){
                System.out.print(par[p] + " - ");
                p = par[p];
            }
        }
        System.out.println();
    }
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(new File("graphs.txt"));
        do{
            int iter = 0;
            //чтение строки из файла, содержащий граф
            String line = scan.nextLine();
            String [] parts = line.split(" ");
            int n = (int) sqrt(parts.length);
            int[] par = new int[n];
            DequeLevit dl = new DequeLevit(n);
            //массив указывающий наместоположение каждой вершины
            int[] arr = new int[n];
            //массив показывающий длину пути до вершин
            int[] res = new int[n];
            //массив, хранящий длины ребер
            int[][] len = new int[n][n];
            res[0] = 0;
            if(n * n != parts.length){
                throw new Exception("Graph is wrong");
            }
            //Заполнение массива результатов и хэш-массива
            int s = 0;
            for(int i = 0;i < n;i++){
                res[i] = 99999;
                arr[i] = 2;
                for (int d = 0;d < n;d++){
                    len[i][d] = Integer.parseInt(parts[s]);
                    s++;
                }
            }
            res[0] = 0;
            //перемещение начального элемента в очередь
            arr[0] = 1;
            dl.putFirst(0);
            //while  с условием того, что очередь не пуста
            while (!dl.isEmpty()) {
                //переменная actual хранит индекс взятого из начала очереди элемента
                int act = dl.lookFirst();
                //удаляем из очереди элемент и перемещаем во множество 0
                dl.getFirst();
                arr[act] = 0;
                //перебор всех ребер, выходящих из данной вершины
                for (int i = 0; i < n; i++) {
                    //если длина ребро от actual до i существует
                    if (len[act][i] != 0) {
                        //условия на принадлежность к множеству и текущий результат
                        if (arr[i] == 2) {
                            dl.putLast(i);
                            arr[i] = 1;
                            res[i] = res[act] + len[act][i];
                            par[i] = act;
                        } else if (arr[i] == 1 && res[i] > res[act] + len[act][i]) {
                            res[i] = res[act] + len[act][i];
                            par[i] = act;
                        } else if (arr[i] == 0 && res[i] > res[act] + len[act][i]) {
                            arr[i] = 1;
                            par[i] = act;
                            res[i] = res[act] + len[act][i];
                            dl.putFirst(i);
                        }
                    }
                }
            }
            out(res);
            way(par);
        }while(scan.hasNextLine());
        scan.close();
    }
}
