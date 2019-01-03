import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Clocks {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] clockA = null;
    static int[] clockB = null;
    static int n = 0;

    /**
     * Linear time check for pattern (b) in a string (a)
     * To modify 
     * - change the input
     * - adjust the if statements for the new input
     * To return instances of matching pattern
     * - adjust return statement to return a list of (i - len(b)) when triggered
     **/
    static boolean kmp(int[] a, int[] b){
        int[] t = new int[n];
        t[0] = 0;
        for(int i = 1; i < n; i++){
            if(b[t[i-1]] == b[i]){
                t[i] = t[i-1] + 1;
            }
            else{
                t[i] = 0;
            }
        }
        int i = 0;
        int j = 0;
        while(i < 2*n){
            if(a[i%n] == b[j]){
                i++;
                j++;
            }
            else{
                if(j != 0){
                    j = t[j-1];
                }
                else{
                    i++;
                }
            }
            if(j == n) return true;
        }

        return false;
    }

    /**
     * Linear time sorting algorithm (counting sort)
     */
    static void sort(){
        int[] books = new int[360000];
        for(int i = 0; i < n; i++){
            books[clockA[i]]++;
        }
        int j = 0;
        for(int i = 0; i < 360000; i++){
            while(books[i] != 0){
                books[i]--;
                clockA[j++] = i;
            }
        }
        books = new int[360000];
        for(int i = 0; i < n; i++){
            books[clockB[i]]++;
        }
        j = 0;
        for(int i = 0; i < 360000; i++){
            while(books[i] != 0){
                books[i]--;
                clockB[j++] = i;
            }
        }
    }

    public static void main(String[] args){
        try{
            n = Integer.parseInt(br.readLine());
            clockA = new int[n];
            clockB = new int[n];
            String[] l = br.readLine().split(" ");
            for(int i = 0; i < n; i++){
                clockA[i] = Integer.parseInt(l[i]);
            }
            l = br.readLine().split(" ");
            for(int i = 0; i < n; i++){
                clockB[i] = Integer.parseInt(l[i]);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        int[] anglesA = new int[n];
        int[] anglesB = new int[n];

        sort();

        for(int i = 0; i < n-1; i++){
            anglesA[i] = clockA[i+1] - clockA[i];
            anglesB[i] = clockB[i+1] - clockB[i];
        }
        anglesA[n-1] = 360000 - clockA[n-1] + clockA[0];
        anglesB[n-1] = 360000 - clockB[n-1] + clockB[0];

        if(kmp(anglesA, anglesB)){
            System.out.print("possible");
        }
        else{
            System.out.print("impossible");
        }
    }
}
