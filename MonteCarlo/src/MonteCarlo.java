import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Monte Carlo Estimate: compute percentage of pseudo-random points in [0.0,1.0)
 * interval that fall in the left half subinterval [0.0,0.5).
 */
public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open input and output streams
         */
        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();
        /*
         * Ask user for number of points to generate
         */
        output.print("Number of points: ");
        int n = input.nextInteger();
        /*
         * Declare counters and initialize them
         */
        int ptsInSubinterval = numberOfPointsInCircle(n);
        /*
         * Estimate percentage of points generated in [0.0,1.0) interval that
         * fall in the [0.0,0.5) subinterval
         */
        double estimate = (100.0 * ptsInSubinterval) / n;
        output.println("Estimate of percentage: " + estimate + "%");
        output.println("Approximate area of circle: " + estimate / 100 * 4);
        /*
         * Close input and output streams
         */

        input.close();
        output.close();
    }

    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        if (Math.pow(xCoord - 1, 2) + Math.pow(yCoord - 1, 2) < 1) {
            return true;
        } else {
            return false;
        }

    }

    private static int numberOfPointsInCircle(int n) {
        int ptsInInterval = 0, ptsInSubinterval = 0;

        Random1L rnd = new Random1L();

        while (ptsInInterval < n) {

            double x = rnd.nextDouble();
            double y = rnd.nextDouble();

            ptsInInterval++;

            if (pointIsInCircle(x, y)) {
                ptsInSubinterval++;
            }

        }

        return ptsInSubinterval;
    }

}