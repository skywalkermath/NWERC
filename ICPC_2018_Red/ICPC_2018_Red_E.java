import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class L {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		User [] s = new User[n/2];
		User [] t = new User[n/2];
		
		int sn = 0;
		int tn = 0;
		
		Stack<Integer> stack = new Stack<Integer>();
		Queue<User> su = new LinkedList<User>();
		stack.push(sn);
		
		
		for (int i = 0; i < n; i++) {
			boolean st = (sc.nextInt() == 1);
			int mentor = sc.nextInt();
			User u = new User(st, mentor, i+1);
			if (u.tall)
			{
				t[tn++] = u;
			}
			else
				su.add(u);
		}
		
		for (int i = 0; i < tn; i++)
		{
			for (int j = 0; j < su.size(); j++) {
				User temp = su.remove();
				if (t[i].index == temp.mentor || temp.index == t[i].mentor)
				{
					su.add(temp);
					if (j == su.size() - 1)
					{
						System.out.print("impossible");
						System.exit(0);
					}
					continue;	
				}
				else
				{
					s[sn++] = temp;
					break;
				}
				
			}
		}
		for (int i = 0; i < t.length; i++) {
			System.out.print(t[i].index + " ");
		}
		System.out.println();
		for (int i = 0; i < t.length; i++) {
			System.out.print(s[i].index + " ");
		}
		System.out.println();
	}
	
	static class User {
		public boolean tall;
		public int mentor;
		public int index;
		
		public User(boolean t, int n, int index)
		{
			tall = t;
			mentor = n;
			this.index = index;
		}
	}

}
