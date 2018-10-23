import java.io.BufferedReader;
import java.io.InputStreamReader;

public class L {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		
		String s = reader.readLine();
		int numb = Integer.parseInt(reader.readLine());
		
		int start = 0;
		int end = 0;
		
		for(int i = 0; i < numb; i++) {
			int[] part = readInts();
			
			start += part[0];
			end = start + part[1];
		}
		System.out.println(s.substring(start, end));
		
	}
	
	private static int[] readInts() throws Exception {
		String[] parts = reader.readLine().split(" ");
		int[] ints = new int[parts.length];
		for(int i = 0; i < parts.length; i++) ints[i] = Integer.parseInt(parts[i]);
		
		return ints;
	}
}
