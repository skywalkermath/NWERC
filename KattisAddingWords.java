import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class KattisAddingWords {

	public static void main(String[] args) throws Exception {
		Map<String, Integer> variables = new HashMap<>();
		Map<Integer, String> variableValues = new HashMap<>();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String line = reader.readLine();
			if(line == null || line.isEmpty()) break;
			
			if(line.equals("clear")) {
				variables.clear();
				continue;
			}
			String[] parts = line.split(" ");
			if(parts[0].equals("def")) {
				variables.put(parts[1], Integer.parseInt(parts[2]));
				variableValues.put(Integer.parseInt(parts[2]), parts[1]);
			} else {
				int total = 0;
				boolean operation = false;
				boolean impossible = false;

				if(!variables.containsKey(parts[1])) {
					impossible = true;
				} else {
					total = variables.get(parts[1]);
					
					for(int i = 2; i < parts.length - 1; i++) {
						if(i % 2 == 0) operation = parts[i].equals("+");
						else {
							String variableName = parts[i];
							Integer value = variables.get(variableName);
							if(value == null) {
								impossible = true;
								break;
							}
							int val = value;
							if(!operation) val = -val;
							total += val;
						}
					}
				}
				String name = "unknown";
				if(!impossible) {
					String varName = variableValues.get(total);
					if(varName != null) name = varName;
				}
				System.out.println(line.substring(5, line.length()) + " " + name);
			}
		}
		reader.close();
	}
}
