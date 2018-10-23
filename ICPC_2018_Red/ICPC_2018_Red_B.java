import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class B {
	public static void main (String [] args) {
		Scanner sc = new Scanner(System.in);
		
		
		int max = sc.nextInt();
		int routes = sc.nextInt();
		int stops = sc.nextInt();
		int [] possible = new int[stops];
		sc.nextLine();
		int sights = 0;
		int mostSights = 0;
		
		for (int i = 1; i <= routes; i++)
		{
			String r = sc.nextLine();
			if (r.charAt(max-1) == '1') {
				for (int j = 0; j < stops; j++)
				{
					if(r.charAt(j) == '1' && j != max-1)
					{
						possible[j] = 1;
					}
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < stops; i++) {
			
			if (possible[i] == 1)
				sum++;
		}
		System.out.println(sum);
		sc.close();
	}
}
