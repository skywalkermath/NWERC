import java.util.Arrays;

public class Permutations {

    public static void permutationsRecursive(int[] array, int n) {
        if(n == 1) {
            //This is where you do something instead of printing
            System.out.println(Arrays.toString(array));
        } else {
            for(int i = 0; i< n; i++) {
                permutationsRecursive(array, n - 1);

                int j = (n % 2 == 0) ? i : 0;
                int temp = array[n - 1];
                array[n - 1] = array[j];
                array[j] = temp;
            }
        }
    }

    /*
        Likely going to be faster than the recursive method
     */
    public static void main(String[] args) {
		permutationsRecursive(new int[] {1, 2, 3}, 3);
	}
}
