import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Newton1 {
    public static final double e = 0.0001;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Calculates the square root of a double x using Newton Iteration and
     * checks if the number is 0 and if it is it will catch it
     */
    private static double sqrt(double x) {
        double r = Math.sqrt(x);
        while (Math.abs(Math.pow(r, 2) - x) / x > e * e) {
            r = (r + x / r) / 2;
        }
        return r;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * The while loop will ask the user if they want to continue and will
         * calculate a square root if they want to
         */
        boolean holder = true;

        while (holder) {
            System.out.println("Would you like to find a square root (y,n): ");
            String answer = in.nextLine();

            //if they type n then the program will end

            if (answer.indexOf('n') >= 0) {
                holder = false;
            }

            //if they type y then the program will ask for a number to find the square root of
            //and outputs the square root of the number

            if (answer.indexOf('y') >= 0) {
                System.out.println("Enter a number to be square rooted: ");
                double square = in.nextDouble();
                double answer1 = sqrt(square);
                System.out.println(
                        "The square root of " + square + " is " + answer1);
            }

        }
        System.out.println("Thanks for using the program!");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
