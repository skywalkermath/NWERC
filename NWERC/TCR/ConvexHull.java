import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


class Point implements Comparable<Point> {

    double x, y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Point p) {
        if (this.x == p.x) {
            return (int)(this.y - p.y);
        } else {
            return (int)(this.x - p.x);
        }
    }

    public String toString() {
        return "("+x + "," + y+")";
    }

}
public class ConvexHull {


    // Calculates orientation of 
    public static double cross(Point O, Point A, Point B) {
        return (A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x);
    }

    public static Point[] convex_hull(Point[] P) {

        if (P.length > 1) {
            int n = P.length, k = 0;
            Point[] H = new Point[2 * n];

            Arrays.sort(P);

            // Build lower hull
            // K starts at 0
            for (int i = 0; i < n; ++i) {
                while (k >= 2 && cross(H[k - 2], H[k - 1], P[i]) <= 0)
                    k--;
                H[k++] = P[i];
            }

            // Build upper hull
            for (int i = n - 2, t = k + 1; i >= 0; i--) {
                while (k >= t && cross(H[k - 2], H[k - 1], P[i]) <= 0)
                    k--;
                H[k++] = P[i];
            }
            if (k > 1) {
                H = Arrays.copyOfRange(H, 0, k - 1); // remove non-hull vertices after k; remove k - 1 which is a duplicate
            }
            return H;
        } else if (P.length <= 1) {
            return P;
        } else{
            return null;
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        Point [] pArr = new Point[rows];
        int i = 0;
        System.out.println(rows);
        while(i < rows)
        {
            double x = (double)sc.nextInt();
            double y = (double)sc.nextInt();
            Point p = new Point(x, y);
            pArr[i++] = p;
        }

        Point[] hull = convex_hull(pArr.clone());

        for (i = 0; i < hull.length; i++) {
            if (hull[i] != null)
                System.out.print(hull[i]);
        }
    }


}
