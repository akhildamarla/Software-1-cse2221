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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
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

        out.print("Amount of change in cents: ");
        int change = in.nextInteger();

        int dollar = 0;
        int halfdollar = 0;
        int quarter = 0;
        int dime = 0;
        int nickel = 0;
        int penny = 0;

        while (change >= 1) {
            while (change > 5) {
                while (change > 10) {
                    while (change > 25) {
                        while (change > 50) {
                            while (change > 100) {
                                change = change - 100;
                                dollar++;
                            }
                            if (change >= 50) {
                                change = change - 50;
                                halfdollar++;
                            }
                        }
                        if (change >= 25) {
                            change = change - 25;
                            quarter++;
                        }
                    }
                    if (change >= 10) {
                        change = change - 10;
                    }
                    dime++;
                }
                if (change >= 5) {
                    change = change - 5;
                    nickel++;
                }
            }
            if (change >= 1) {
                change = change - 1;
                penny++;
            }
        }
        System.out.println("Number of dollars: " + dollar);
        System.out.println("Number of halfdollars: " + halfdollar);
        System.out.println("Number of quarter: " + quarter);
        System.out.println("Number of dime: " + dime);
        System.out.println("Number of nickel: " + nickel);
        System.out.println("Number of penny: " + penny);

        in.close();
        out.close();
    }

}
