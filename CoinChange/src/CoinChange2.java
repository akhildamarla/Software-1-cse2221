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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
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

        String[] denominations = { "dollar", "halfdollar", "quarter", "dime",
                "nickel", "penny" };
        int[] coinCounts = new int[denominations.length];

        while (change >= 1) {
            while (change > 5) {
                while (change > 10) {
                    while (change > 25) {
                        while (change > 50) {
                            while (change > 100) {
                                change = change - 100;
                                coinCounts[0]++;
                            }
                            if (change >= 50) {
                                change = change - 50;
                                coinCounts[1]++;
                            }
                        }
                        if (change >= 25) {
                            change = change - 25;
                            coinCounts[2]++;
                        }
                    }
                    if (change >= 10) {
                        change = change - 10;
                    }
                    coinCounts[3]++;
                }
                if (change >= 5) {
                    change = change - 5;
                    coinCounts[4]++;
                }
            }
            if (change >= 1) {
                change = change - 1;
                coinCounts[4]++;
            }
        }
        System.out.println(coinCounts);

        in.close();
        out.close();
    }

}
