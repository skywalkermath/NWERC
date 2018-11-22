import java.util.Arrays;

//https://www.geeksforgeeks.org/k-dimensional-tree-set-3-delete/
public class KDTree {

    private final int k;
    private Node root;

    public KDTree(int k) {
        this.k = k;
    }

    private Node insertRec(Node root, int[] point, int depth) {
        if(root == null) return new Node(point);

        int currentDepth = depth % k;
        if(point[currentDepth] < root.point[currentDepth]) root.left = insertRec(root.left, point, depth + 1);
        else root.right = insertRec(root.right, point, depth + 1);

        return root;
    }

    private boolean searchRec(Node root, int[] point, int depth) {
        if(root == null) return false;
        if(Arrays.equals(point, root.point)) return true;

        int currentDepth = depth % k;
        if(point[currentDepth] < root.point[currentDepth]) return searchRec(root.left, point, depth + 1);
        else return searchRec(root.right, point, depth + 1);
    }

    private Node findMinRec(Node root, int d, int depth) {
        if(root == null) return null;

        int currentDepth = depth % k;
        if(currentDepth == d) {
            if(root.left == null) return root;
            else return findMinRec(root.left, d, depth + 1);
        }
        return minNode(d, root, findMinRec(root.left, d, depth + 1), findMinRec(root.right, d, depth + 1));
    }

    private Node minNode(int d, Node...nodes) {
        Node best = null;

        for(Node node : nodes) {
        	if(node != null) {
        		if(best == null || node.point[d] < best.point[d]) best = node;
        	}
        }
        return best;
    }

    
    //Commented out because its broken...didn't have time to fix it
    
//    private Node deleteNodeRec(Node root, int[] point, int depth) {
//        if(root == null) return null;
//
//        int currentDepth = depth / k;
//
//        if(Arrays.equals(root.point, point)) {
//            if(root.right != null) {
//                Node min = findMinRec(root.right, currentDepth, 0);
//                root.point = min.point.clone();
//                root.right = deleteNodeRec(root.right, min.point, depth + 1);
//            } else if(root.left != null) {
//                Node min = findMinRec(root.left, currentDepth, 0);
//                root.point = min.point.clone();
//                root.right = deleteNodeRec(root.left, min.point, depth + 1);
//                root.left = null;
//            } else {
//                return null;
//            }
//            return root;
//        }
//
//        if(point[currentDepth] < root.point[currentDepth]) root.left = deleteNodeRec(root.left, point, depth + 1);
//        else root.right = deleteNodeRec(root.right, point, depth + 1);
//
//        return root;
//    }

    //Public methods - USE THIS NOT THE OTHER ONES
    public int[] findMin(int d) {
        Node node = findMinRec(root, d, 0);
        if(node == null) return null;
        return node.point;
    }

    public void insert(int[] point) {
        root = insertRec(root, point, 0);
    }

    public boolean search(int[] point) {
        return searchRec(root, point, 0);
    }

//    public void deleteNode(int[] point) {
//        root = deleteNodeRec(root, point, 0);
//    }

    private class Node {
        int[] point;
        Node left, right;

        Node(int[] point) {
            this.point = point.clone();
        }
    }
    
    public static void main(String[] args) {
		int[][] array = new int[][] {
			{3, 6},
			{17, 15},
			{13, 15},
			{6, 12}, 
            {9, 1},
            {2, 7},
            {10, 19}
		};
		KDTree tree = new KDTree(2);
		for(int[] arr : array) tree.insert(arr);
		
		System.out.println(tree.search(new int[] { 10, 19 }));
		System.out.println(tree.search(new int[] { 12, 19 }));
		
		System.out.println(Arrays.toString(tree.findMin(0)));
		System.out.println(Arrays.toString(tree.findMin(1)));
		
//		tree.deleteNode(new int[] { 9, 1 });
//		
//		System.out.println(tree.search(new int[] { 10, 19 }));
//		System.out.println(tree.search(new int[] { 12, 19 }));
//		
//		System.out.println(Arrays.toString(tree.findMin(0)));
//		System.out.println(Arrays.toString(tree.findMin(1)));
	}
}
