import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private int N = 0;
    private ArrayList<LineSegment> segments;

    //finds all line segment containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (!validPoints(points)) throw new IllegalArgumentException();

        Point[] localPoints = points.clone();

        Arrays.sort(localPoints, Point::compareTo);
        segments = new ArrayList<>();

        for (int i = 0; i < points.length - 3; i++)
            for (int j = i + 1; j < points.length - 2; j++)
                for (int k = j + 1; k < points.length - 1; k++)
                    for (int l = k + 1; l < points.length; l++)
                        if (localPoints[i].slopeTo(localPoints[j]) == localPoints[i].slopeTo(localPoints[k])
                                && localPoints[i].slopeTo(localPoints[j]) == localPoints[i].slopeTo(localPoints[l])) {
                            LineSegment lineSegment = new LineSegment(localPoints[i], localPoints[l]);
                            segments.add(lineSegment);
                            N++;
                            }
    }

    //the number of line segments
    public int numberOfSegments() {
        return N;
    }

    private static boolean validPoints(Point[] points) {
        if (points == null) return false;

        if (points[0] == null) return false;

        for(int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) return false;
                else if(points[j] == null) return false;
            }
        }
        return true;
    }

    //the line segments -> return a line segment array
    public LineSegment[] segments() {
        LineSegment[] segmentsArray = new LineSegment[N];
        for (int i = 0; i < N; i ++)
            segmentsArray[i] = segments.get(i);
        return segmentsArray;
    }

    public static void main(String[] args) {
        Point[] pointsArray = new Point[6];
        Point point1 = new Point(0, 0);
        Point point2 = new Point(1, 1);
        Point point3 = new Point(4, 4);
        Point point4 = new Point(3, 3);
        Point point5 = new Point(2, 3);
        Point point6 = new Point(0, 1);
        pointsArray[0] = point1;
        pointsArray[1] = point2;
        pointsArray[2] = point3;
        pointsArray[3] = point4;
        pointsArray[4] = point5;
        pointsArray[5] = point6;

        Arrays.sort(pointsArray, Point::compareTo);
        for(Point point : pointsArray) System.out.println(point.toString());

        /*BruteCollinearPoints points = new BruteCollinearPoints(pointsArray);
        System.out.println(pointsArray[0].slopeTo(pointsArray[1]));
        System.out.println(Arrays.toString(pointsArray));
        System.out.println(points.numberOfSegments());
        System.out.println(Arrays.toString(points.segments()));*/
    }
}