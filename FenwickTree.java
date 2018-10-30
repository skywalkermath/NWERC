public class FenwickTree {
    int[] tree;
    int[] array;
    int size;

    public FenwickTree(int[] arr){
        this.array = arr;
        this.size = arr.length;
        tree = new int[this.size];
        this.createTreeFromArray();
    }

    public void createTreeFromArray(){
        for(int i = 0; i < size; i++){
            this.updateTree(i, this.array[i]);
        }
    }

    public void set(int i, int newVal){
        int oldVal = this.array[i];
        this.array[i] = newVal;

        this.updateTree(i, newVal - oldVal);
    }

    public void updateTree(int i, int delta){
        while(i < size){
            this.tree[i] += delta;
            i += Integer.lowestOneBit(i + 1);
        }
    }

    public int getRangeSum(int a, int b){
        return this.getSum(b) - this.getSum(a);
    }

    public int getSum(int i){
        int sum = 0;
        while(i + 1 > 0){
            sum += tree[i];
            i -= Integer.lowestOneBit(i + 1);
        }
        return sum;
    }

    public void print(){
        for(int x: tree){
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
