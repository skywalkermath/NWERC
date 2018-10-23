import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		 int[] line1 = readInts();
		 
		 int startStop = line1[0] - 1;
		 int buses = line1[1];
		 int numbStops = line1[2];
		 
		 List<boolean[]> possibleBuses = new ArrayList<>();
		 
		 for(int i = 0; i < buses; i++) {
		 	String line = reader.readLine();
		 	if(line.charAt(startStop) == '0') continue;
		 	boolean[] arr = new boolean[line.length()];
		 	for(int j = 0; j < line.length(); j++) {
		 		arr[j] = line.charAt(j) == '1';
		 	}
		 	possibleBuses.add(arr);
		 	
		 }
		 reader.close();
		 
		 buses = possibleBuses.size();

 		int total = 0;
		 for(int i = 0; i < numbStops; i++) {
			 if(i == startStop) {
				 continue;
			 }
			 
			 for(int j = 0; j < buses; j++) {
				 if(possibleBuses.get(j)[i]) {
					 total++;
					 break;
				 }
			 }
		 }
		 System.out.println(total);
	}

	private static int[] readInts() throws Exception {
		String[] parts = reader.readLine().split(" ");
		int[] ints = new int[parts.length];
		for(int i = 0; i < parts.length; i++) ints[i] = Integer.parseInt(parts[i]);
		
		return ints;
	}
}
