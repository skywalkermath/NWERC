import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

public class A extends ProblemTest {

	public static void main(String[] args) throws Exception {
		//Tests 'A', max time is 1000 milliseconds, tests are sample and testdata
		new A().test(1000, "cases/A/sample", "cases/A/testdata");
	}
	
	@Override
	public void run() throws Exception {
		int tests = Integer.parseInt(scanner.nextLine());
		for(int i = 0; i < tests; i++) {
			doTest();
		}
	}
	
	public void doTest() throws Exception {
		int[] data = getInts(scanner.nextLine());
		int components = data[0];
		int budget = data[1];
		int highestQuality = 0;
		
		Map<String, List<Component>> componentMap = new HashMap<>();
		for(int i = 0; i < components; i++) {
			String[] lineData = scanner.nextLine().split(" ");
			
			String type = lineData[0];
			int cost = Integer.parseInt(lineData[2]);
			int quality = Integer.parseInt(lineData[3]);
			
			if(quality > highestQuality) highestQuality = quality;
			Component component = new Component(type, cost, quality);
			
			List<Component> set = componentMap.get(type);
			if(set == null) set = new ArrayList<>();
			set.add(component);
			
			componentMap.put(type, set);
		}
		for(List<Component> c : componentMap.values()) {
			Collections.sort(c);
			
			int lowest = -1;
			ListIterator<Component> iterator = c.listIterator(c.size() - 1);
			while(iterator.hasPrevious()) {
				Component component = iterator.previous();
				if(lowest == -1) lowest = component.price;
				else if(component.price < lowest) lowest = component.price;
				else iterator.remove();
			}
		}

		int start = 0;
		int end = highestQuality;
		int bestPossible = -1;
		
		while(start <= end) {
			int middle = (start + end) / 2;
			
			if(isPossible(middle, budget, componentMap)) {
				bestPossible = middle;
				start = middle + 1;
			} else {
				end = middle - 1;
			}
		}
		System.out.println(bestPossible);
	}
	
	private static boolean isPossible(int quality, int budget, Map<String, List<Component>> componentMap) {
		int total = 0;
		for(Entry<String, List<Component>> entry : componentMap.entrySet()) {
			List<Component> components = entry.getValue();
			
			int index = getLowestIndexes(components, quality);
			if(index < 0) return false;
			total += components.get(index).price;
			if(total > budget) return false;
		}
		return total <= budget;
	}
	
	private static int getLowestIndexes(List<Component> components, int quality) {
		int start = -1;
		for(int i = components.size() - 1; i >= 0; i--) {
			Component c = components.get(i);
			if(c.quality < quality) break;
			start = i;
		}
		return start;
	}
	
	private static int[] getInts(String line) {
		String[] parts = line.split(" ");
		int[] ints = new int[parts.length];
		for(int i = 0; i < ints.length; i++) ints[i] = Integer.parseInt(parts[i]);
		return ints;
	}
	
	private static class Component implements Comparable<Component> {
		String type;
		int price;
		int quality;
		
		public Component(String type, int price, int quality) {
			this.type = type;
			this.price = price;
			this.quality = quality;
		}
		
		@Override
		public int compareTo(Component o) {
			int compare = Integer.compare(quality, o.quality);
			if(compare != 0) return compare;
			
			return Integer.compare(price, o.price);
		}
		
		@Override
		public String toString() {
			return "{" + type + ", " + price + ", " + quality + "}";
		}
	}
}
