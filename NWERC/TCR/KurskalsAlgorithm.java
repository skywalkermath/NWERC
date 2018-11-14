package kurskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KurskalsAlgorithm {

    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";

        List<Path<String>> paths = Arrays.asList(
            new Path(a, b, 7),
            new Path(b, c, 6),
            new Path(c, d, 5),
            new Path(a, d, 2)
        );
        paths = link(paths);

        for(Path<String> path : paths) {
            System.out.println(path);
        }
    }

    public static <T> List<Path<T>> link(List<Path<T>> paths) {
        List<Path<T>> found = new ArrayList<>(paths.size());

        Collections.sort(paths);

        for(Path<T> path : paths) {
            int nodesFound = 0;

            for(Path check : found) {
                if(path.node1.equals(check.node1)) nodesFound++;
                if(path.node1.equals(check.node2)) nodesFound++;
                if(path.node2.equals(check.node2)) nodesFound++;
                if(path.node2.equals(check.node1)) nodesFound++;
            }

            if(nodesFound < 2) found.add(path);
        }
        return found;
    }

    public static class Path<T> implements Comparable<Path<T>> {

        private T node1;
        private T node2;

        private int distance;

        public Path(T path1, T path2, int distance) {
            this.node1 = path1;
            this.node2 = path2;
            this.distance = distance;
        }

        public T getNode1() {
            return node1;
        }

        public T getNode2() {
            return node2;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Path<T> o) {
            return Integer.compare(distance, o.distance);
        }

        @Override
        public String toString() {
            return "{" + node1 + " -> " + node2 + " = " + distance + "}";
        }
    }
}
