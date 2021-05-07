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
public final class Newton4 {
    public static double e = 0.0001;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Calculates the square root of a double x using Newton Iteration
     */
    private static double sqrt(double x) {
        double r = 0;
        if (x > 0) {
            r = Math.sqrt(x);
            while (Math.abs(Math.pow(r, 2) - x) / x > e * e) {
                r = (r + x / r) / 2;
            }
            return r;
        } else {
            return 0;
        }
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
            System.out.println("Enter a number to be square rooted: ");
            int answer = in.nextInteger();

            if (answer < 0) {
                holder = false;
            }

            if (answer >= 0) {
                System.out.println("Enter the value of E as a decimal: ");
                e = in.nextDouble();

                double answer1 = sqrt(answer);
                if (answer1 > 0) {
                    System.out.println(
                            "The square root of " + answer + " is " + answer1);
                } else {
                    System.out.println(
                            "The number you entered isn't a valid number, 0 does not have a square root.");
                }

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
