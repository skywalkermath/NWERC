import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Coins {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[][] arr;
    static int[] coins;
    static int[] c = {1, 5, 10, 25};

    public static void main(String[] args) {
        try {
            arr = new long[4][Integer.parseInt(br.readLine()) + 1];
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
        if(max >= arr[0].length -1){
            System.out.println(arr[0].length-1);
            System.exit(0);
        }
        for(int i = 0; i <= max && i < arr[0].length; i++){
            arr[0][i] = i;
        }
        for(int i = 0; i < c.length; i++){
            if(coins[i] > 0 && c[i] < arr[0].length) arr[i][c[i]] = 1;
        }
        for(int i = 1; i < 4; i++){
            max += (long)coins[i] * c[i];
            for(int j = 0; j < arr[i].length && j <= max; j++){
                arr[i][j] = Math.max(arr[i-1][j], arr[i][j]);
                if(j >= c[i] && arr[i][j-c[i]] + 1 > arr[i][j] && arr[i][j - c[i]] != 0){
                    arr[i][j] = arr[i][j-c[i]] + 1;
                }
                if(j == arr[i].length-1 && arr[i][j] != 0){
                    System.out.println(arr[i][j]);
                    System.exit(0);
                }
            }
        }

//        for(int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println("impossible");
    }
}
