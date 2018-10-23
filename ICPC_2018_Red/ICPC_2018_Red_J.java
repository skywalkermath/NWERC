import java.util.Scanner;
import java.util.Stack;


public class J {
	public static int doors;
	public static int funding;
	public static double scaling;
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		
		int doors = sc.nextInt();
		int funding = sc.nextInt();
		float scaling = sc.nextFloat();
	
		
		System.out.println(scaling * doors);
		System.out.println(Math.sqrt(Math.pow(scaling*doors, 2)-funding));
		System.out.println(funding);
		System.out.println(scaling);
		
		double d1 = (double) ((scaling * doors + Math.sqrt(Math.pow(scaling*doors, 2) - funding))/scaling);
		double d2 = (double) ((scaling * doors - Math.sqrt(Math.pow(scaling*doors, 2) - funding))/scaling);
		System.out.println(d1);
		System.out.println(d2);

		double idealDoors = max(d1, d2);
		System.out.println(idealDoors);
		
	}
	
	public static double exp(double d)
	{
		double result = (funding - Math.pow(d * scaling, 2))/(doors-d);
		return result;
	}
	
	public static double max(double a, double b)
	{
		if (exp(a) < exp(b))
			return b;
		return a;
	}
}
