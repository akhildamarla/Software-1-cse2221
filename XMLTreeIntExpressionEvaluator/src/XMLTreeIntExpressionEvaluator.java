import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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

    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";

        String plus = "plus";
        String minus = "minus";
        String times = "times";
        String divide = "divide";

        int total = 1;

        if (exp.numberOfChildren() > 0) {
            int num1 = evaluate(exp.child(0));
            int num2 = evaluate(exp.child(1));

            if (exp.label().equals(plus)) {
                total = num1 + num2;
            }
            if (exp.label().equals(minus)) {
                total = num1 - num2;
            }
            if (exp.label().equals(times)) {
                total = num1 * num2;
            }
            if (exp.label().equals(divide)) {
                total = num1 / num2;
            }

        } else {
            total = Integer.parseInt(exp.attributeValue("value"));
        }
        return total;
    }

    private static int size(XMLTree exp) {
        int size = 1;

        if (exp.isTag()) {
            for (int i = 0; i < exp.numberOfChildren(); i++) {
                System.out.println(size);
                size += size(exp.child(i));
            }
        }
        return size;
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
        /**
         * while (!file.equals("")) { XMLTree exp = new XMLTree1(file);
         * out.println(evaluate(exp.child(0))); out.print("Enter the name of an
         * expression XML file: "); file = in.nextLine(); }
         **/
        XMLTree exp = new XMLTree1(file);
        out.println(size(exp));

        in.close();
        out.close();
    }

}