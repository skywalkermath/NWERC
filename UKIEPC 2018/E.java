import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class E {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int memberCount = Integer.parseInt(reader.readLine());
		
		int[][] members = new int[memberCount][2];
		
		for(int i = 0; i < memberCount; i++) {
			members[i] = readInts();
			members[i][1]--;
		}
		int[] tall = new int[memberCount / 2];
		int[] small = new int[memberCount / 2];
		
		int numOf0 = 0;
		int numOf1 = 0;
		
		for(int i = 0; i < memberCount; i++) {
			if(members[i][0] == 0) {
				small[numOf0++] = i;
			} else {
				tall[numOf1++] = i;
			}
		}
		
		top:
		for(int i = 0; i < tall.length; i++) {
			if (members[tall[i]][1] == small[i] || members[small[i]][1] == tall[i]) {
				for(int j = 0; j < tall.length; j++) {
					if(j == i) continue;
					
					if(members[tall[j]][1] != small[i] && members[small[i]][1] != tall[j] && members[tall[i]][1] != small[j] && members[small[j]][1] != tall[i]) {
						int temp = tall[i];
						tall[i] = tall[j];
						tall[j] = temp;
						continue top;
					}
				}
				System.out.println("impossible");
				System.exit(0);
			}
		}
		
		printArr(tall);
		printArr(small);
	}
	
	private static void printArr(int[] arr) {
		StringBuilder builder = new StringBuilder(arr.length * 2);
		for(int i = 0; i < arr.length; i++) {
			if(i != 0) builder.append(' ');
			builder.append(arr[i] + 1);
		}
		System.out.println(builder.toString());
	}

	private static int[] readInts() throws Exception {
		String[] parts = reader.readLine().split(" ");
		int[] ints = new int[parts.length];
		for(int i = 0; i < parts.length; i++) ints[i] = Integer.parseInt(parts[i]);
		
		return ints;
	}
}
