import java.io.BufferedReader;
import java.io.InputStreamReader;

public class J {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		String[] data = reader.readLine().split(" ");
		
		int doors = Integer.parseInt(data[0]);
		int prizeFund = Integer.parseInt(data[1]);
		double scale = Double.parseDouble(data[2]);
		
		double a = (doors*scale*scale + Math.sqrt(scale*scale*(doors*doors*scale*scale-prizeFund)))/(scale*scale);
		double b = (doors*scale*scale - Math.sqrt(scale*scale*(doors*doors*scale*scale-prizeFund)))/(scale*scale);
		
		double x1 = 1/(doors-Math.floor(a))*(prizeFund-(Math.floor(a)*scale)*(Math.floor(a)*scale));
		double x2 = 1/(doors-Math.ceil(a))*(prizeFund-(Math.ceil(a)*scale)*(Math.ceil(a)*scale));
		double x3 = 1/(doors-Math.floor(b))*(prizeFund-(Math.floor(b)*scale)*(Math.floor(b)*scale));
		double x4 = 1/(doors-Math.ceil(b))*(prizeFund-(Math.ceil(b)*scale)*(Math.ceil(b)*scale));
		
		System.out.println(x1);
		System.out.println(x2);
		System.out.println(x3);
		System.out.println(x4);
	}
	
	private static int[] readInts() throws Exception {
		String[] parts = reader.readLine().split(" ");
		int[] ints = new int[parts.length];
		for(int i = 0; i < parts.length; i++) ints[i] = Integer.parseInt(parts[i]);
		
		return ints;
	}
}
