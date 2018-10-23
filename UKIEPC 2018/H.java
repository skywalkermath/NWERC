import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class H {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		double tapeLength = (double) Integer.parseInt(reader.readLine());
		int totalSizes = Integer.parseInt(reader.readLine());

		Ball[] balls = new Ball[totalSizes];
		for(int i = 0; i < totalSizes; i++) {
			int[] data = readInts();
			balls[i] = new Ball(data[0], data[1], data[1] * 2.0 * Math.PI);
		}
		reader.close();
		
		Arrays.sort(balls);
		
		int counter = 0;
		top:
		for(int i = 0; i < totalSizes; i++) {
			Ball ball = balls[i];
			
			for(int j = 0; j < ball.numb; j++) {
				tapeLength -= ball.circ;
				if(tapeLength < 0) break top;
				counter++;
			}
		}
		System.out.println(counter);
	}
	
	private static int[] readInts() throws Exception {
		String[] parts = reader.readLine().split(" ");
		int[] ints = new int[parts.length];
		for(int i = 0; i < parts.length; i++) ints[i] = Integer.parseInt(parts[i]);
		
		return ints;
	}
	
	public static class Ball implements Comparable<Ball> {
		int numb;
		int radius;
		double circ;
		
		Ball(int numb, int radius, double circ) {
			this.numb = numb;
			this.radius = radius;
			this.circ = circ;
		}
		
		@Override
		public int compareTo(Ball o) {
			return Integer.compare(radius, o.radius);
		}
	}
}
