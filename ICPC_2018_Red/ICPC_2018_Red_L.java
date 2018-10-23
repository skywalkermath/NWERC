import java.util.Scanner;

public class TheRealL {
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int start = 0;
		int length = 0;
		
		int ops = sc.nextInt();
		
		for (int i = 0; i < ops; i++)
		{
			start += sc.nextInt();
			if (i == ops-1)
			{
				length = sc.nextInt();
				break;
			}
			sc.nextInt();
		}
		System.out.println(s.substring(start, start+length));
	}
}
