import java.util.*;

public class MoP1 {
    public static Scanner in=new Scanner(System.in).useLocale(Locale.US);


    void printArr(int [][] arr)
    {
        System.out.println();
        int xl = arr.length;
        int yl = arr[1].length;
        for (int i = 0; i < xl; i++)
        {
            //System.out.print(i + "|  ");
            for (int j = 0; j < yl; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
        System.out.println("-----------------------------------------------");
    }


    // Find augmenting paths
    /**
     * An augmenting path is a simple path (no cycles)
     * It will only use edges of positive weight
     */
    boolean augment(int[][] cap,boolean[] done,int from,int to,int add) {
        System.out.println("        Recursive Augment Call: from: " + from + " to: " + to + " add: " + add);
        if(done[from])  //BC
        {

            System.out.println("False BC");
            return false;
        }
        else
            done[from]=true;
        if(from==to)  //if im already there
        {
            System.out.println("True, from == to");
            return true;
        }
        for(int i=0;i<cap[from].length;++i) {
            if (cap[from][i] >= add && augment(cap, done, i, to, add)) {
                cap[from][i] -= add;
                cap[i][from] += add;
                System.out.println("True");
                return true;
            }
        }
        System.out.println("False");
        return false;
    }

    /**
     * Will figure out if all the penguins can get to a specific floe
     * @param cap copy of the cap array
     * @param from 2*n
     * @param to 2 * i, the variable that is iterated through
     * @return how many penguins can get to the given floe
     */
    int maxflow(int[][] cap,int from,int to) {
        int ret=0;
        int n=cap.length; //2*number of floes + 1
        /**
         * I'm not sure I understand the iteration here
         * start at 8, divide by 2 as you go
         * so it is:
         *  8
         *  4
         *  2
         *  1
         *  Done, but why not start at 0 and iterate up to 4?
         */
        for(int add=8;add>=1;add/=2) {
            boolean[] done=new boolean[n];
            /**
             * if there is an augmenting path then you can get another penguin to the floe
             */
            System.out.println("    Call to Augment from MaxFlow: from: " + from + " to: " + to + " add: " + add);
            while(augment(cap,done,from,to,add))
            {

                ret+=add;
                done=new boolean[n];
                System.out.println("    Call to Augment from MaxFlow: from: " + from + " to: " + to + " add: " + add);
            }
        }
        return ret; //number of penguins which can get to the floe
    }

    public void run() {
        int n=in.nextInt(); double maxdist=in.nextDouble();	//n is # of ice floes and maxdist is the max jumping distance of penguins
        int[] x = new int[n], y= new int[n], nrstart = new int[n], nrjump = new int[n];
        for(int i=0;i<n;++i) {
            x[i]=in.nextInt();  //x coord of floe
            y[i]=in.nextInt();	//y coord of floe
            nrstart[i]=in.nextInt();  //# of penguins on the floe
            nrjump[i]=in.nextInt();	  //# of jumps from floe before it sinks
        }

        int[][] cap=new int[2*n+1][2*n+1]; //stores all the problem details in one array
        //printArr(cap);
        for(int i=0;i<n;++i){  //compare each node to every other node
            for(int j=0;j<n;++j){
                if((x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j])<=maxdist*maxdist){ //if jump is possible
                    cap[2*i+1][2*j]=nrjump[i]; //indexing doesnt make sense atm, stores how many jumps you can make from floe
                    /**
                     * Loops to self would be stored at cap[2i + 1][2i]
                     * perhaps the arrows are unidirectional
                     * this stores how many penguins can jump off the floe
                     * Stores in odd rows and even columns
                     */
                }
            }
        }
        for(int i=0;i<n;++i){
            cap[2*i][2*i+1]=nrjump[i]; // Floe to itself but stored at cap[2i][2i + 1] stores number of jumps which can be made
        }
        for(int i=0;i<n;++i){
            cap[2*n][2*i]=nrstart[i]; //Stores how many penguins are at the floe
        }

        /**
         * The graph is now set up. It stores all the data required to run max flow for this problem
         * Interesting hack is the indexing. By making sure parity is always different, you could store max 4 different
         * classes of information in this way.
         * [odd][odd]
         * [odd][even]
         * [even][odd]
         * [even][even]
         * this creates 4 disjoint sets each of size arr.length^2 /4
         * here the biggest set is the unidirectional arrows which can be up to n^2 (each node can go to any node in the graph)
         * as arr.length is (2n + 1) we have a 2d array of size 4n^2 + 4n + 1 so we can store at most n^2 + n items in each disjoint set
         * this is chill in this case
         */

        /**
         * Sum up total number
         * of penguins
         */
        int tot=0;
        for(int i=0;i<n;++i){
            tot+=nrstart[i];
        }

        printArr(cap);
        /**
         * ret holds solutions
         */
        List<Integer> ret = new ArrayList<Integer>();
        /**
         * Copy cap into copycap, presumably so it can be manipulated
         */
        for(int i=0;i<n;++i) {
            int[][] copycap=new int[2*n+1][2*n+1];
            for(int j=0;j<2*n+1;++j) {
                for (int k = 0; k < 2 * n + 1; ++k) {
                    copycap[j][k] = cap[j][k];
                }
            }


            /**
             * if a floe allows all penguins to reach it, add that floe to the list of possible floes
             */
            System.out.println("Call from run() to maxflow(): from: " + 2*n + " to: " + (2 * i));
            if(maxflow(copycap,2*n,2*i)==tot){
                ret.add(i);
            }
        }
        /**
         * ret seems to be the floes to which the penguins can go
         */
        if(ret.size() == 0) {
            System.out.println("-1");
        } else {
            for(int i=0;i<ret.size();++i) {
                if(i!=0)
                {
                    System.out.print(" ");
                }
                System.out.print(ret.get(i));
            }
            System.out.println();
        }
    }

    //driver code
    //b1().run() solves one test case
    //new instance created for each test case
    public static void main(String[] args) {
        int n=in.nextInt(); for(int i=0;i<n;++i) new MoP1().run();
    }
}
