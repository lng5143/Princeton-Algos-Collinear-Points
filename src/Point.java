import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    public double slopeTo(Point that) {
        if (this.compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        else if(this.y == that.y) return 0;
        else if(this.x == that.x) return Double.POSITIVE_INFINITY;
	    else return ((double)(that.y - this.y)/(that.x - this.x));
    }

    public int compareTo(Point that) {
        if (this.y < that.y) return -1;
        else if (this.y == that.y && this.x < that.x) return -1;
        else if (this.y == that.y && this.x == that.x) return 0;
        else return 1;
    }

    public Comparator<Point> slopeOrder() {
        return new pointSlopeOrder();
    }

    private class pointSlopeOrder implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            if (slopeTo(o1) < slopeTo(o2)) return -1;
            else if (slopeTo(o1) > slopeTo(o2)) return 1;
            else return 0;
        }
    }

    public static void main(String[] args) {

    }
}