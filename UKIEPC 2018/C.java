import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class C {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		int[] data = readInts();
		int height = data[0];
		int width = data[1];
		
		int passcodeLength = Integer.parseInt(reader.readLine());
		
		Map<TileType, Integer> tileNumbs = new HashMap<>();
		
		reader.close();
	}

	private static int[] readInts() throws Exception {
		String[] parts = reader.readLine().split(" ");
		int[] ints = new int[parts.length];
		for(int i = 0; i < parts.length; i++) ints[i] = Integer.parseInt(parts[i]);
		
		return ints;
	}
	
	private static enum TileType {
		MIDDLE,
		EDGE,
		CORNER;
	}
}
