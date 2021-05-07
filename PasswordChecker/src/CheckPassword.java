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
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    static boolean length;
    static boolean upperCase;
    static boolean digit;
    static int counter = 0;

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void checkPassword(String s) {
        if (s.length() >= 8) {
            length = true;
            counter++;

        }
        if (containsUpperCaseLetter(s)) {
            upperCase = true;
            counter++;
        }
        if (containsDigit(s)) {
            digit = true;
            counter++;
        }
        if (counter <= 1) {
            System.out.println("This password is invalid.");

        } else {
            System.out.println("This password is good.");
        }

    }

    private static boolean containsUpperCaseLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            upperCase = Character.isUpperCase(s.charAt(i));
            if (upperCase = true) {
                return upperCase;
            }
        }
        return upperCase;
    }

    private static boolean containsDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            digit = Character.isDigit(s.charAt(i));
            if (digit = true) {
                return digit;
            }
        }
        return digit;
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
         * Put your main program code here; it may call myMethod as shown
         */
        System.out.println("Enter a password: ");
        String password = in.nextLine();

        checkPassword(password);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
