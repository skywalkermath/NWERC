import java.util.ArrayList;
import java.util.Stack;

/**
 * Dinic's Max Flow:
 * Traverse graph using BFS
 * Rank nodes as such:
 *  Rank of source = 0
 *  Rank of nodes that source goes to = 1
 *  Rank of nodes rank one nodes go to = 2
 *  etc.
 * Construct path ST rank of each node increases
 * What's the max flow I can push through this?
 * Create new Residual graph.
 */

public class DinicMaxFlow {
    int[][] adjacencyMatrix; //row[i] -> col[j] => node I -> node J
    int[] levels;

    int sink;
    int source;
    int curFlow;
    int maxLevel;

    /**
     * Create graph
     */
    public DinicMaxFlow(){
        curFlow = 0;
        //TODO: create graph and initialise all fields
    }

    /**
     * Display state of adjacency matrix
     */
    void print(){
        for (int i = 0; i < adjacencyMatrix.length; i++){
            for (int j = 0; j < adjacencyMatrix[i].length; j++){
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    void sendFlow(int[] path){
        int max = Integer.MAX_VALUE;

        for(int i = 0; i < path.length - 1; i++){
            max = Math.min(max, adjacencyMatrix[path[i]][path[i+1]]);
        }

        adjacencyMatrix[path[0]][path[1]] -= max;
        adjacencyMatrix[path[path.length - 1]][path[path.length - 2]] += max;

        for(int i = 1; i < path.length - 1; i++){
            adjacencyMatrix[path[i]][path[i + 1]] -= max;
            adjacencyMatrix[path[i]][path[i - 1]] += max;
        }

        curFlow += max;
    }

    /**
     * Uses depth first search to find paths through
     * levels and adjusts residual graph
     */
    void augment(){
        Stack<Integer> nodes = new Stack<Integer>();
        int[] path = new int[maxLevel + 1];
        nodes.push(source);
        int cur;
        boolean pathMade = false;
        boolean[] done = new boolean[adjacencyMatrix[0].length];
        int level;

        while(!nodes.empty()){
            cur = nodes.pop();
            if(done[cur]) continue;
            done[cur] = true;
            level = levels[cur];
            if(level == maxLevel) pathMade = true;
            path[level] = cur;

            for(int j = 0; j < adjacencyMatrix[cur].length;j++){
                if(adjacencyMatrix[cur][j] != 0 && levels[cur] + 1 == levels[j]){
                    nodes.push(j);
                }
            }
            if(pathMade){
                System.out.println("lol");
                sendFlow(path);
            }
        }
    }


    /**
     * Run BFS and populate levels as you go.
     */
    boolean BFS(){
        if(source == sink) return true;

        ArrayList<Integer> nodes = new ArrayList<Integer>();
        ArrayList<Integer> levelQ = new ArrayList<Integer>();
        boolean[] done = new boolean[adjacencyMatrix[0].length];
        boolean foundSink = false;
        int cur;

        nodes.add(source);
        levelQ.add(0);
        maxLevel = 0;
        done[0] = true;

        while(!nodes.isEmpty()){
            cur = nodes.get(0);
            levels[cur] = levelQ.get(0);
            nodes.remove(0);
            levelQ.remove(0);

            if(cur == sink) foundSink = true;

            for(int j = 0; j < adjacencyMatrix[cur].length; j++){
                if(adjacencyMatrix[cur][j] == 0) continue;
                else if(!done[j]){
                    done[j] = true;
                    nodes.add(j);
                    levelQ.add(levels[cur] + 1);
                    maxLevel = levels[cur] + 1;
                }
            }
        }
        return foundSink;
    }

    public static void main(String[] args){
        DinicMaxFlow dmx = new DinicMaxFlow();
        while (dmx.BFS())
            dmx.augment();
        System.out.println(dmx.curFlow);
    }
}
