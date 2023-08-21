import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private static int N = 0;
    private static ArrayList<LineSegment> segments = new ArrayList<>();

    //finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (!validPoints(points)) throw new IllegalArgumentException();

        int length = points.length;

        Point[] localPoints = points.clone();
        Arrays.sort(points, Point::compareTo);

        /*for (int i = 0; i < length; i++) {
            Point base = points[i];
            Arrays.sort(localPoints, Point::compareTo);
            Arrays.sort(localPoints, base.slopeOrder());
            int count = 1;
            Point lineBeginning = null;

            for (int j = 0; j < localPoints.length; j++) {
                if(localPoints[j].slopeTo(base) == localPoints[j + 1].slopeTo(base))
                {
                    count++;
                    if (count == 2)
                    {
                        lineBeginning = localPoints[j];
                        count++;
                    }
                    else if (count >= 4 && j + 1 == localPoints.length - 1)
                    {
                        if (lineBeginning.compareTo(base) > 0)
                        {
                            segments.add(new LineSegment(base, localPoints[j + 1]));
                        }
                        count = 1;
                    }
                }
                else if (count >= 4)
                {
                    if (lineBeginning.compareTo(base) > 0)
                    {
                        segments.add(new LineSegment(base, localPoints[j]));
                    }
                    count = 1;
                }
                else
                {
                    count = 1;
                }
            }
        }*/

        for (int i = 0; i <length; i++) {
            int count = 1;
            int pointer;
            Point base = points[i];
            Point lineBeginning = null;
            Arrays.sort(localPoints, Point::compareTo);
            Arrays.sort(localPoints, base.slopeOrder());

            for(int j = 1; j < length ; j++) {
                if (base.slopeTo(localPoints[j]) != base.slopeTo(localPoints[j - 1]))
                {
                    count = 1;
                    pointer = j;
                }
                else
                {
                    count++;
                    if (count >= 3 &&
                            ((j < length - 1) && base.slopeTo(localPoints[j]) != base.slopeTo(localPoints[j + 1])
                                    || j == length - 1))
                    {
                        Point[] pointsInSegment = new Point[count + 1];
                        pointsInSegment[0] = base;

                        for (int k = 1; k < count + 1; k++)
                        {
                            pointsInSegment[k] = localPoints[pointer + k - 1];
                        }
                        if (validPointsInSegment(pointsInSegment))
                        {
                            LineSegment lineSegment = new LineSegment(pointsInSegment[0], pointsInSegment[count]);
                            segments.add(lineSegment);
                            N++;
                        }
                    }
                }
            }
        }
    }

    private static boolean validPointsInSegment(Point[] points) {
        Point min = points[0];

        for (int i = 1; i < points.length; i ++)
            if (min.compareTo(points[i]) >= 0)
                min = points[i];

        return min == points[0];
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

    //the number of line segments
    public int numberOfSegments() {
        return N;
    }

    //the line segments
    public LineSegment[] segments() {
        LineSegment[] segmentsArray = new LineSegment[N];
        for (int i = 0; i < N; i ++)
            segmentsArray[i] = segments.get(i);
        return segmentsArray;
    }

    public static void main(String[] args) {


        Point[] points = new Point[]
                {new Point(0, -2),
                        new Point(1, 0),
                        new Point(1, 1),
                        new Point(2, 2),
                        new Point(3, 3),
                        new Point(0, 1),
                        new Point(0, 2),
                        new Point(0, 4),
                        new Point(0, 5),
                        new Point(0, 6)};

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);

        for (Point point : points) System.out.println(point.toString());

        System.out.println(points[0].compareTo(points[1]));
        System.out.println(points[0].compareTo(points[2]));
        System.out.println(points[0].compareTo(points[3]));
        System.out.println(points[0].compareTo(points[4]));
        System.out.println(points[0].compareTo(points[5]));
        System.out.println(points[0].compareTo(points[6]));
        System.out.println(points[0].compareTo(points[7]));
        System.out.println(points[0].compareTo(points[8]));
        System.out.println(points[0].compareTo(points[9]));

        System.out.println((validPointsInSegment(points)));
        System.out.println(fastCollinearPoints.segments.size());
        LineSegment[] segments = fastCollinearPoints.segments();
        for (LineSegment segment : segments) System.out.println(segment.toString());



        //double[] slopeArray = {1, 2, 2, 2, 2, 3, 4, 4, 4, 4, 5, 6};
        //System.out.println(findIndexOfSortedPoints(slopeArray));*/
    }
}
