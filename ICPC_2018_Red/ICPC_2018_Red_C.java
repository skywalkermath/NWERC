import java.math.BigInteger;
import java.util.Scanner;
import java.util.Stack;

public class C {
	
	static int rows;
	static int columns;
	static int length;
	static int[][] memo;
	
	public static long [][] getNext(long [][] previous){
		long [][] next = new long [rows][columns];
		long max = sumSquare(previous);
		
		long count = 0;
		for(int x = 0; x < rows; x++) 
		{
			for(int y = 0; y < columns; y++)
			{
				count = 0;
				for (int i = x - 1; i < x + 2; i++)
				{
					for (int j = y - 1; j < j + 2; j++)
					{
						if (i >= 0 && i < rows && j >= 0 && j < columns) {
							count = (count +  previous[i][j]);
						}
					}
				}
				next[x][y] = (max - count)% 1000000007;
			}
		}
		return next;
	}
	
	public static long sumSquare(long[][] sqr) {
		long lol = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				lol = (lol + sqr[i][j]) % 1000000007;
			}
		}
		return lol;
	}
	
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		
		rows = sc.nextInt();
		columns = sc.nextInt();
		length = sc.nextInt();
		
		if (length-- == 1)
		{
			System.out.println(rows*columns);
			System.exit(0);
		}
		long [][] previous = choices();
		long [][] next = previous;
		while (length > 1)
		{
			next = getNext(previous);
			System.out.println(length--);
		}
		long result = sumSquare(next);
		
		System.out.println(result);
	}
	
	public static long[][] choices() {
		long [][] initial = new long [rows][columns];

		long count;
		for(int x = 0; x < rows; x++) {
			for(int y = 0; y < columns; y++) {
				count =0;
				for (int i = 0; i < rows; i++)
				{
					for (int j = 0; j < columns; j++)
					{
						if (allowed(i, j, x, y))
							count++;
					}
				}
				initial[x][y] = count;
			}
		}
		return initial;
	}
	
	public static boolean allowed(int x, int y, int lastX, int lastY) {
		if(lastX == -1 && lastY == -1) {
			return true;
		}
		else if(lastX - 1 <= x && lastX + 1 >= x 
				&& lastY - 1 <= y && lastY + 1 >= y) {
			return false;
		}
		else {
			return true;
		}
	}
}