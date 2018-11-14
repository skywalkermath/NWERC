import java.util.*;

public class Dijkstra<T> {

    public static void main(String[] args) {

        //Create this graph:
        //https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#/media/File:Dijkstra_Animation.gif
        //The value can be anything the problem requires, in this case used Strings

        DNode<String> n1 = new DNode<>("1");
        DNode<String> n2 = new DNode<>("2");
        DNode<String> n3 = new DNode<>("3");
        DNode<String> n4 = new DNode<>("4");
        DNode<String> n5 = new DNode<>("5");
        DNode<String> n6 = new DNode<>("6");

        //Set up the connections between nodes
        n1.addConnection(n2, 7);
        n1.addConnection(n3, 9);
        n1.addConnection(n6, 14);

        n2.addConnection(n3, 10);
        n2.addConnection(n4, 15);

        n3.addConnection(n4, 11);
        n3.addConnection(n6, 2);

        n4.addConnection(n5, 6);

        n5.addConnection(n6, 9);

        //Runs dijkstra on the graph
        Dijkstra<String> calc = new Dijkstra(n1, n1, n2, n3, n4, n5, n6);

        //Prints out the path from the start node (n1) to n5
        System.out.println(calc.getPath(n5));

        //Prints the distance between the start node and n5
        System.out.println(calc.getDistance(n5));

        //Prints the ending node with the shortest distance to n1
        System.out.println(calc.getBestDistance(n2, n3, n4, n5, n6));
    }

    private DNode<T> startNode;

    /**
     * Runs Dijkstra on the nodes specified
     * @param startNode Starting node to run dijkstra on
     * @param nodes A list of all nodes in the graph. startNode must be contained in this
     */
    public Dijkstra(DNode<T> startNode, DNode<T>...nodes) {
        this.startNode = startNode;

        PriorityQueue<DNode<T>> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.bestDistance));

        startNode.bestDistance = 0;
        queue.add(startNode);

        for(DNode<T> node : nodes) {
            if(node != startNode) {
                node.bestDistance = Integer.MAX_VALUE;
                queue.add(node);
            }
        }

        while(!queue.isEmpty()) {
            DNode<T> node = queue.poll();

            for(DEdge<T> neighbour : node.getConnections()) {
                int alt = node.bestDistance + neighbour.getDistance();

                if(alt < neighbour.getNode2().bestDistance) {
                    queue.remove(neighbour.getNode2());

                    neighbour.getNode2().bestDistance = alt;
                    neighbour.getNode2().previous = node;

                    queue.add(neighbour.getNode2());
                }
            }
        }
    }

    /**
     * @param endNode Node to get the distance to
     * @return The distance between the starting node and the specified ending node
     */
    public int getDistance(DNode<T> endNode) {
        return endNode.bestDistance;
    }

    /**
     * @param nodes A list of nodes to check
     * @return The closest node to the starting node
     */
    public DNode<T> getBestDistance(DNode<T>...nodes) {
        DNode<T> best = null;

        for(DNode<T> node : nodes) {
            if(node == startNode) continue;
            if(best == null || node.bestDistance < best.bestDistance) best = node;
        }

        return best;
    }

    /**
     * @param endNode The node to get a path to
     * @return A path from the starting node to the ending node
     */
    public List<DNode<T>> getPath(DNode<T> endNode) {
        List<DNode<T>> path = new LinkedList<>();
        path.add(endNode);

        DNode<T> step = endNode;
        if(step.previous == null) return null;

        while(true) {
             step = step.previous;
             if(step == null) break;
             path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

    public static class DNode<T> {

        private T value;
        private List<DEdge<T>> connections = new LinkedList<>();

        public int bestDistance = 0;
        public DNode<T> previous = null;

        public DNode(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        /**
         * Adds a connection between this node and the specified node
         * @param node the node to add a connection to
         * @param distance The distance between this node and the specified node
         */
        public void addConnection(DNode<T> node, int distance) {
            connections.add(new DEdge<>(this, node, distance));
            node.connections.add(new DEdge<>(node, this, distance));
        }

        public List<DEdge<T>> getConnections() {
            return connections;
        }

        @Override
        public String toString() {
            return value.toString();
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj.getClass() != this.getClass()) return false;
            return Objects.equals(value, ((DNode) obj).value);
        }
    }

    public static class DEdge<T> {

        private DNode<T> node1;
        private DNode<T> node2;

        private int distance;

        public DEdge(DNode<T> node1, DNode<T> node2, int distance) {
            this.node1 = node1;
            this.node2 = node2;
            this.distance = distance;
        }

        public DNode<T> getNode1() {
            return node1;
        }

        public DNode<T> getNode2() {
            return node2;
        }

        public int getDistance() {
            return distance;
        }
    }
}
