import java.util.PriorityQueue;

public class Dijkstra {
    int[][] adjacencyMatrix;
    DNode[] nodes;

    PriorityQueue<DNode> nextNode;

    static class DNode implements Comparable<DNode>{
        int nodeID;
        int curDistance;
        int finalDistance;
        boolean isFinal;

        public  DNode(){
            isFinal = false;
            curDistance = Integer.MAX_VALUE;
            finalDistance = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(DNode o) {
            return Integer.compare(this.curDistance, o.curDistance);
        }
    }

    /**
     * n is starting node
     * Returns length of shortest path;
     */
    int go(int n){
        nextNode = new PriorityQueue<>();
        nodes[n].curDistance = 0;
        nextNode.add(nodes[n]);
        boolean foundFinal = false;
        DNode minFinal = null;
        DNode cur;
        while(!nextNode.isEmpty()){
            cur = nextNode.poll();
            cur.finalDistance = cur.curDistance;
            if(foundFinal && cur.finalDistance >= minFinal.finalDistance){
                return minFinal.finalDistance;
            }
            else if(foundFinal && cur.finalDistance < minFinal.finalDistance){
                minFinal = cur;
            }
            if(!foundFinal && cur.isFinal){
                foundFinal = true;
                minFinal = cur;
            }
            for(int j = 0; j < adjacencyMatrix[cur.nodeID].length; j++){
                if(adjacencyMatrix[cur.nodeID][j] != 0){
                    if(nodes[j].finalDistance != Integer.MAX_VALUE) continue;
                    nodes[j].curDistance = Math.min(cur.finalDistance + adjacencyMatrix[cur.nodeID][j], nodes[j].curDistance);
                    nextNode.add(nodes[j]);
                }
            }
        }
        if(foundFinal){
            return minFinal.finalDistance;
        }
        return -1; //no path
    }
}
