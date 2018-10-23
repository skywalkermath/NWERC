import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class A {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		int[] line1 = readInts();

		int numbConnections = line1[1];

		int[] pianoWidth = readInts();

		int[][] data = new int[numbConnections][2];
		for(int i = 0; i < numbConnections; i++) {
			data[i] = readInts();
		}

		boolean[] accountedFor = new boolean[numbConnections];

		int[][] bucket = new int[numbConnections][numbConnections];

		for (int i = 0;i<numbConnections;i++) {
			accountedFor[i] = false;
		}
		int bucketCounter = 0;
		
		int[] sizeOfBucket = new int[numbConnections];

		for (int i = 0; i<numbConnections;i++) {

			if(!accountedFor[i]) {
				bucket[bucketCounter][sizeOfBucket[bucketCounter]++] = data[i][0];
				bucket[bucketCounter][sizeOfBucket[bucketCounter]++] = data[i][1];
				accountedFor[i] = true;
				int p = 0;
				while(p<sizeOfBucket[bucketCounter]) {
					for(int j = i+1;j < numbConnections; j++) {
						if(!accountedFor[j]) {
							if(bucket[bucketCounter][p] == data[j][0]) {
								accountedFor[j] = true;
								int k = 0;
								boolean found = false;
								while (k<sizeOfBucket[bucketCounter]) {
									if(bucket[bucketCounter][k] == data[j][1]) {
										found = true;
										break;
									}
									k++;
								}
								if(!found) bucket[bucketCounter][sizeOfBucket[bucketCounter]++] = data[j][1];
							}else if(bucket[bucketCounter][p] == data[j][1]) {

								accountedFor[j] = true;
								if(bucket[bucketCounter][p] == data[j][1]) {
									int k = 0;
									boolean found = false;
									while (k<sizeOfBucket[bucketCounter]) {
										if(bucket[bucketCounter][k] == data[j][0]) {
											found = true;
											break;
										}
										k++;
									}
									if(!found) bucket[bucketCounter][sizeOfBucket[bucketCounter]++] = data[j][0];

								}
							}
						}
					}
					p++;
				}
				bucketCounter++;				
			}
		}
		
		int total = 0;
		
		for(int i = 0; i < bucketCounter; i++) {
			int[] b = bucket[i];
			
			Map<Integer, Integer> map = new HashMap<>();
			int size = sizeOfBucket[i];
			for(int j = 0; j < size; j++) {
				int width = pianoWidth[b[j] - 1];
				
				if(map.containsKey(width)) map.put(width, map.get(width) + 1);
				else map.put(width, 1);
			}
			int biggest = 0;
			for(int numb : map.values()) {
				if(numb > biggest) biggest = numb;
			}
			total += sizeOfBucket[i] - biggest;
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
