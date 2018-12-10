import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Coins {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr;
    static int[] coins;
    static int[] c = {1, 5, 10, 25};

    public static void main(String[] args) {
        try {
            arr = new int[Integer.parseInt(br.readLine()) + 1];
            coins = new int[4];
            String[] l = br.readLine().split(" ");
            br.close();
            coins[0] = Integer.parseInt(l[0]);
            coins[1] = Integer.parseInt(l[1]);
            coins[2] = Integer.parseInt(l[2]);
            coins[3] = Integer.parseInt(l[3]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        long max = coins[0];
        if(max >= arr.length -1){
            System.out.println(arr.length-1);
            System.exit(0);
        }
        for(int i = 0; i <= max && i < arr.length; i++){
            arr[i] = i;
        }
        for(int i = 0; i < c.length; i++){
            if(coins[i] > 0 && c[i] < arr.length && arr[c[i]] == 0) arr[c[i]] = 1;
        }
        for(int i = 1; i < 4; i++){
            max += (long)coins[i] * c[i];
            for(int j = 0; j < arr.length && j <= max; j++){
                if(j >= c[i] && arr[j-c[i]] + 1 > arr[j] && arr[j - c[i]] != 0){
                    arr[j] = arr[j-c[i]] + 1;
                }
            }
        }

//        for(int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
        if(arr[arr.length-1] == 0)
            System.out.println("impossible");
        else
            System.out.println(arr[arr.length-1]);
    }
}
