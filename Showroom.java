import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Showroom {
    static DNode[] adjacencyList;

    enum Struct{
        WALL,
        DOOR,
        CAR
    }

    static PriorityQueue<DNode> dj;

    static class DNode implements Comparable<DNode> {
        int curDistance;
        int finalDistance;
        Struct type;
        int nodeID;
        boolean isExit;

        ArrayList<Integer> adjacentTo;

        @Override
        public int compareTo(DNode o) {
            return Integer.compare(this.curDistance, o.curDistance);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();
        sc.nextLine();

        adjacencyList = new DNode[rows * cols];

        String line;

        int count = 0;
        DNode cur;
        for(int i = 0; i < rows; i++) {
            line = sc.nextLine();
            for (int j = 0; j < cols; j++) {
                cur = new DNode();
                cur.isExit = false;
                char curC = line.charAt(j);

                if (curC == '#') {
                    cur.type = Struct.WALL;
                    count++;
                    continue;
                } else if (curC == 'D') {
                    cur.type = Struct.DOOR;
                    if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                        cur.isExit = true;
                    }
                } else {
                    cur.type = Struct.CAR;
                }
                cur.curDistance = Integer.MAX_VALUE;
                cur.finalDistance = Integer.MAX_VALUE;
                cur.adjacentTo = new ArrayList<Integer>();
                cur.nodeID = count;

                DNode nextTo;

                if (count - 1 >= 0) {
                    if (adjacencyList[count - 1] != null) {
                        nextTo = adjacencyList[count - 1];
                        cur.adjacentTo.add(count - 1);
                        nextTo.adjacentTo.add(count);
                    }
                }
                if (count - cols >= 0) {
                    if (adjacencyList[count - cols] != null) {
                        nextTo = adjacencyList[count - cols];
                        cur.adjacentTo.add(count - cols);
                        nextTo.adjacentTo.add(count);
                    }
                }
                adjacencyList[count++] = cur;
            }
        }

        for(int i = 0; i < adjacencyList.length; i++){
            if(adjacencyList[i] != null){
                System.out.print(adjacencyList[i].type + ": ");
                for(int n: adjacencyList[i].adjacentTo){
                    System.out.print(n + ", ");
                }
                System.out.println();
            }
        }


        line = sc.nextLine();
        int row = Integer.parseInt(line.split(" ")[0]);
        int column = Integer.parseInt(line.split(" ")[1]);


        cur = adjacencyList[(row - 1) * cols + column - 1];
        cur.finalDistance = 0;
        cur.curDistance = 0;
        boolean flag = true;
        dj = new PriorityQueue<DNode>(rows * cols);
        dj.add(cur);
        DNode x = null;
        while(!dj.isEmpty()){
            cur = dj.poll();
            if(cur.finalDistance == Integer.MAX_VALUE || flag){
                if(flag){
                    flag = false;
                }
                cur.finalDistance = cur.curDistance;
                if(cur.isExit){
                    System.out.println();
                    System.out.println(cur.finalDistance);
                    System.exit(0);
                }
                while(!cur.adjacentTo.isEmpty()){
                    x = adjacencyList[cur.adjacentTo.get(0)];

                    if(x.finalDistance != Integer.MAX_VALUE){
                        System.out.println("Index: " + cur.adjacentTo.get(0));
                        cur.adjacentTo.remove(0);
                        continue;
                    }
                    if(cur.type == Struct.DOOR || x.type == Struct.DOOR){
                        if(cur.type == Struct.CAR){
                            if(cur.finalDistance  + 1 < x.curDistance){
                                x.curDistance = cur.finalDistance + 1;
                            }
                        }
                        else {
                            if (cur.finalDistance < x.curDistance) {
                                x.curDistance = cur.finalDistance;
                            }
                        }
                    }
                    else{
                        if(cur.finalDistance  + 1 < x.curDistance){
                            x.curDistance = cur.finalDistance + 1;
                        }
                    }
                    System.out.println("Index: " + cur.adjacentTo.get(0) + ", curDist: " + x.curDistance);
                    cur.adjacentTo.remove(0);
                    dj.add(x);
                }
            }
        }
        System.out.println("Should never reach here");
    }
}

