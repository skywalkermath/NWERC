import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class H {
	public static int min(int a, int b) {
		if(a < b) {
			return a;
		}
		return b;
	}
	
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		double tape = sc.nextInt();
		
		
		int balls = sc.nextInt();
		
		ArrayList<Ball> bs = new ArrayList<Ball>(balls);
		int n = 0;
		
		for (int i = 0; i < balls; i++)
		{
			Ball b = new Ball(sc.nextInt(), circ(sc.nextInt()));
			bs.add(b);
		}
		bs.sort(bs.get(0));
//		for (int i =0; i< bs.size(); i++) {
//			System.out.print(bs.get(i).c + " ");
//		}
		int count = 0;
		int i = 0;
		int result;
		while(i < bs.size() && tape >= bs.get(i).c)
		{
			 
			result = (int)(tape/(bs.get(i).c));
			tape = tape - min(result, bs.get(i).number) * bs.get(i).c;
			count += min(result, bs.get(i).number);
			i++;
		}
		//result = (int)(tape/(bs.get(1).c));
		//System.out.println(min(result, bs.get(i).number));
		System.out.println(count);
	}
	public static double circ(int r)
	{
		return 2 * r * Math.PI;
	}
	
	static class Ball implements Comparator<Ball>{
		public int number;
		public double c;
		
		public Ball(int n, double c) {
			number = n;
			this.c = c;
		}

		@Override
		public int compare(Ball arg0, Ball arg1) {
			if (arg0.c < arg1.c) {
				return -1;
			}
			else if(arg0.c == arg1.c) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}
}
