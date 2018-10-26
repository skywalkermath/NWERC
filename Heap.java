
public class Heap {
	
	int[] a;
	static int pointer;
	boolean isMaxHeap;
	
	public static void main(String[] args) {
		int[] x = {8, 9, 5, 6, 3, 1};
		
		Heap h = new Heap(x, false);
		h.print();
	}
	
	public Heap(int[] x, boolean isMax) {
		pointer = x.length;
		this.isMaxHeap = isMax;
		this.a = x;
		
		for(int i = pointer / 2; i >= 0; i--) {
			if(isMax) {
				this.maxHeapify(i);
			}
			else {
				this.minHeapify(i);
			}
		}
		
	}
	
	public void delete(int i) {
		toRoot(i);
		a[0] = a[--pointer];
	
		if(this.isMaxHeap) {
			maxHeapify(0);
		}
		else {
			minHeapify(0);
		}
	}
	
	public void maxHeapify(int i){
		int max;
		while(this.getLeftChild(i) != 0) {
			max = this.getMax(i);
			if(max == i) return;
			swap(i, max);
			i = max;
		}
	}
	
	public void minHeapify(int i){
		int min;
		while(this.getLeftChild(i) != 0) {
			min = this.getMin(i);
			if(min == i) return;
			swap(i, min);
			i = min;
		}
	}
	
	private void toRoot(int i) {
		int parent = this.getParent(i);;
		while (parent > -1) {
			swap(i, parent);
			i = parent;
			parent = this.getParent(i);
		}
	}
	
	private int getMin(int i) {
		int left = this.getLeftChild(i);
		int right = this.getRightChild(i);

		if(left == -1 && right == -1) {
			return i;
		}
		else if(left == -1){
			if(a[right] < a[i]) return right;
			return i;
		}
		else if(right == -1) {
			if(a[left] < a[i]) return left;
			return i;
		}
		else {
			int min = Math.min(Math.min(a[i], a[left]), a[right]);
		
			if(min == a[i]) { 
				return i;
			}
			else if(min == a[left]) {
				return left;
			}
			else{
				return right;
			}
		}
	}
	
	private int getMax(int i) {
		int left = this.getLeftChild(i);
		int right = this.getRightChild(i);

		if(left == -1 && right == -1) {
			return i;
		}
		else if(left == -1){
			if(a[right] > a[i]) return right;
			return i;
		}
		else if(right == -1) {
			if(a[left] > a[i]) return left;
			return i;
		}
		else {
			int max = Math.max(Math.max(a[i], a[left]), a[right]);
			
			if(max == a[i]) {
				return i;
			}
			else if(max == a[left]) {
				return left;
			}
			else {
				return right;
			}
		}
	}

	public Heap(int size, boolean maxHeap) {
		this.a = new int[size];
		pointer =  0;
		this.isMaxHeap = maxHeap;
	}
	
	public void print() {
		for(int i = 0; i < pointer; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.print("\n");
	}
	
	public void add(int x) {
		if(pointer >= a.length) this.resize();
		a[pointer] = x;
		int i = pointer++;
		while(i > -1) {
			if(this.isMaxHeap) {
				this.preserveMax(i);
			}
			else {
				this.preserveMin(i);
			}
			i = this.getParent(i);
		}
	}
	
	
	
	private void preserveMin(int i) {
		int left = this.getLeftChild(i);
		int right = this.getRightChild(i);

		if(left == -1 && right == -1) {
			return;
		}
		else if(left == -1){
			if(a[right] < a[i]) {
				swap(right, i);
			}
			return;
		}
		else if(right == -1) {
			if(a[left] < a[i]) {
				swap(left, i);
			}
			return;
		}
		else {
			int min = Math.min(Math.min(a[i], a[left]), a[right]);
			
			if(min == a[i]) return;
			
			if(min == a[left]) {
				swap(i, left);
				return;
			}
			if(min == a[right]) {
				swap(i, right);
				return;
			}
			
		}
	}
	
	private void preserveMax(int i) {
		int left = this.getLeftChild(i);
		int right = this.getRightChild(i);

		if(left == -1 && right == -1) {
			return;
		}
		else if(left == -1){
			if(a[right] > a[i]) {
				swap(right, i);
			}
			return;
		}
		else if(right == -1) {
			if(a[left] > a[i]) {
				swap(left, i);
			}
			return;
		}
		else {
			int max = Math.max(Math.max(a[i], a[left]), a[right]);
			
			if(max == a[i]) return;
			
			if(max == a[left]) {
				swap(i, left);
				return;
			}
			if(max == a[right]) {
				swap(i, right);
				return;
			}
			
		}
	}
	
	private void swap(int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	public int getParent(int i) {
		if (i == 0) return -1;
		return (i + 1) / 2 - 1;
	}
	
	public int getLeftChild(int i) {
		if(2 * (i + 1) - 1 >= pointer) return -1;
		return 2 * (i + 1) - 1;
	}
	
	public int getRightChild(int i) {
		if(2 * (i + 1) >= pointer) return -1;
		return 2 * (i + 1);
	}
	
	private void resize() {
		int[] b = new int[2 * a.length];
		for(int i = 0; i < a.length; i++) {
			b[2 * i] = -1;
			b[i] = a[i];
		}
		a = b;
	}
}
