public class TernarySearch {

    /**
     * The function for which we are to find a turning point
     */
    static double f(double x){
        return -Math.pow(x, 2);
    }

    /**
     * If there is one turning point in the specified interval,
     * this function will find it.
     * After each iteration, 1/3 of the interval is discarded.
     * When the interval -> 0 we get a very accurate result for where the turning point is.
     * It currently looks for maximum points, to change this,
     * change the direction of the comparator at line 25
     */
    static double[] searchMax(double start, double end){
        double length = end - start;
        double splitOne = start + length / 3;
        double splitTwo = start + 2 * length / 3;

        if(length == 0){
            double[] limits = {start, end};
            return limits;
        }
        else if(f(splitOne) > f(splitTwo)){
            end = splitTwo;
        }
        else{
            start = splitOne;
        }

        double[] limits = {start, end};
        return limits;
    }


    /**
     * Driver code which should really be packaged up into a function.
     * The function should take a variable number of iterations to allow
     * different precisions. A rounding function to take things to the 
     * required precision for problems would also be neat.
     */
    public static void main(String[] args){
        double oldStart = -501;
        double oldEnd = 501;


        double newStart = -500;
        double newEnd = 500;

        double[] limits;

        while(oldStart != newStart || oldEnd != newEnd){
            oldStart = newStart;
            oldEnd = newEnd;
            limits = searchMax(newStart, newEnd);
            newStart = limits[0];
            newEnd = limits[1];
        }
        System.out.println(Math.round(newStart));
    }
}
