import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    public static int total = 0;

    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        String plus = "plus";
        String minus = "minus";
        String times = "times";
        String divide = "divide";

        NaturalNumber total = new NaturalNumber2(0);
        NaturalNumber temp = new NaturalNumber2(0);

        if (exp.numberOfChildren() > 0) {
            total.copyFrom(evaluate(exp.child(0)));
            temp.copyFrom(evaluate(exp.child(1)));

            if (exp.label().equals(plus)) {
                total.add(temp);
            }
            if (exp.label().equals(minus)) {
                if (total.compareTo(temp) < 0) {
                    Reporter.fatalErrorToConsole(
                            "Error: illegal use of subtraction "
                                    + "(second number larger than first)");
                }
                total.subtract(temp);
            }
            if (exp.label().equals(times)) {
                total.multiply(temp);
            }
            if (exp.label().equals(divide)) {
                if (temp.isZero()) {
                    Reporter.fatalErrorToConsole(
                            "Error: illegal use of division "
                                    + "(divide by zero error)");
                }
                total.divide(temp);
            }

        } else {
            total.copyFrom(new NaturalNumber2(exp.attributeValue("value")));
        }
        return total;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}