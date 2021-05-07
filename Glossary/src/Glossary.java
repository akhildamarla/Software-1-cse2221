import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Creates a glossary
 *
 * @author Akhil Damarla
 *
 */
public final class Glossary {

    private Glossary() {
    }

    /**
     * Generates the header, head, title, and body of HTML file
     *
     * @param out
     *            the output stream
     * @param terms
     *            the terms in alphabetical order
     * @param mappedTerms
     *            a map of the terms along with their proper definition
     * @param termArray
     *            the list of terms in an array
     * @param outputFile
     *            the output file location created by the user
     */
    private static void outputIndex(SimpleWriter out, Sequence<String> terms,
            Map<String, String> mappedTerms, String[] termArray,
            String outputFile) {

        /*
         * Creates a user defined output file along with destination
         */
        String index = outputFile + "/index.html";
        SimpleWriter indexLink = new SimpleWriter1L(index);

        /*
         * Header
         */
        indexLink.println("<html>");
        indexLink.println("<head>");
        indexLink.println("<title>" + "Glossary Index" + "</title>");
        indexLink.println("</head>");
        indexLink.println("<body>");
        indexLink.println("<p style=\"font-size:32pt;\">Glossary</p>");
        indexLink.println();
        indexLink.println("<p style=\"font-size:18pt;\">Index</p>");
        indexLink.println("<ul>");

        /*
         * creates a word page for each term and adds to the list
         */
        while (terms.length() > 0) {
            String word = terms.remove(0);
            generateWordPages(word, out, mappedTerms, termArray, outputFile);
            indexLink.println(
                    "<li><a href=\"" + word + ".html\">" + word + "</a></li>");
        }

        /*
         * Footer
         */
        indexLink.println("</ul>");
        indexLink.println("</body>");
        indexLink.println("</html>");

        indexLink.close();

    }

    /**
     * Alphabetizes terms
     *
     * @param terms
     *            the given set of terms
     * @replaces terms
     * @ensures terms = original terms alphabetized
     */
    private static String alphabeticalOrder(Set<String> terms) {

        Set<String> holder = new Set1L<>();
        String result = "";

        while (terms.size() > 0 && result.equals("")) {
            int count = 0;
            String test = terms.removeAny();
            for (String word : terms) {
                if (word.compareTo(test) < 0) {
                    count++;
                }
            }
            if (count == 0) {
                result = test;
            } else {
                holder.add(test);
            }
        }
        terms.add(holder);
        return result;
    }

    /**
     * Generates the pages for each of the terms
     *
     * @param word
     *            the term
     * @param out
     *            the output stream
     * @param mappedTerms
     *            a map of the terms along with their proper definition
     * @param termArray
     *            the list of terms in an array
     * @param outputFile
     *            the output file location created by the user
     */
    private static void generateWordPages(String word, SimpleWriter out,
            Map<String, String> mappedTerms, String[] termArray,
            String outputFile) {

        String wordPage = outputFile + "/" + word + ".html";
        SimpleWriter htmlPage = new SimpleWriter1L(wordPage);

        htmlPage.println("<html>");
        htmlPage.println("<head>");
        htmlPage.println("<title>" + word + "</title>");
        htmlPage.println("</head>");
        htmlPage.println("<body>");
        htmlPage.println(
                "<p style=\"color:red;\"><b><i>" + word + "</b></i></p>");
        htmlPage.println();

        String definition = mappedTerms.value(word);
        Set<Character> separatorSet = new Set1L<>();
        String separators = " ,";
        generateElements(separators, separatorSet);

        String pageInput = "";

        int i = 0;
        while (i < definition.length()) {
            String token = nextWordOrSeparator(definition, i, separatorSet);
            if (separatorSet.contains(token.charAt(0))) {
                pageInput = pageInput + token;
            } else {
                int n = 0;
                int count = 0;
                while (n < termArray.length) {
                    if (token.equals(termArray[n])) {
                        pageInput = pageInput + "<a href=\"" + termArray[n]
                                + ".html\">" + token + "</a>";
                        count++;
                    }
                    n++;
                }
                if (count == 0) {
                    pageInput = pageInput + token;
                }
            }
            i += token.length();
        }

        htmlPage.println("<p>" + pageInput + "</p>");
        htmlPage.println();
        htmlPage.println("Return to <a href=\"index.html\">index</a>");
        htmlPage.println("</body>");
        htmlPage.println("</html>");

        htmlPage.close();
    }

    /**
     * Adds the terms and definitions to terms and mapTerms
     *
     * @param in
     *            the input stream
     * @param mapTerms
     *            the map that will contain the terms with their associated
     *            definitions
     * @param terms
     *            a set of only the terms
     */
    private static void findTermsAndDefinitions(SimpleReader in,
            Map<String, String> mapTerms, Set<String> terms) {

        while (!in.atEOS()) {

            String nextLine = in.nextLine();
            String definition = "";
            String word = "";
            boolean emptyLine = true;

            if (nextLine.equals("")) {

                emptyLine = false;
            } else {

                word = nextLine;
            }

            while (emptyLine && !in.atEOS()) {

                nextLine = in.nextLine();
                if (!nextLine.equals("")) {
                    definition = definition + " " + nextLine;
                } else {
                    emptyLine = false;
                }
            }

            mapTerms.add(word, definition);
            terms.add(word);
        }

    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param strSet
     *            the {@code Set} to be replaced
     * @replaces strSet
     * @ensures strSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> strSet) {
        assert str != null : "Violation of: str is not null";
        assert strSet != null : "Violation of: strSet is not null";

        int i = str.length();

        while (i > 0) {
            char x = str.charAt(i - 1);
            if (!strSet.contains(x)) {
                strSet.add(x);
            }
            i--;
        }

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        char nextChar = text.charAt(position);
        String result = "" + nextChar;
        boolean sepTest = separators.contains(nextChar);
        int endStr = position;

        if (sepTest) {
            endStr++;
            if (endStr <= text.length()) {
                nextChar = text.charAt(endStr);
                while (separators.contains(nextChar)) {

                    if (!separators.contains(nextChar)) {
                        sepTest = false;
                    }
                    endStr++;
                    nextChar = text.charAt(endStr);
                }
            }

        }

        else {
            endStr++;
            if (endStr < text.length()) {
                nextChar = text.charAt(endStr);
                while (!separators.contains(nextChar)
                        && endStr != text.length()) {

                    if (separators.contains(nextChar)) {
                        sepTest = true;
                    }
                    endStr++;
                    if (endStr < text.length()) {
                        nextChar = text.charAt(endStr);
                    }
                }
            }
        }
        result = text.substring(position, endStr);

        return result;

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

        out.print("Input the file to import/read: ");
        String inputFileName = in.nextLine();
        SimpleReader inFile = new SimpleReader1L(inputFileName);
        out.print("Provide the output destination (use filepath): ");
        String outputFile = in.nextLine();

        Map<String, String> mapTerms = new Map1L<>();
        Set<String> terms = new Set1L<>();
        findTermsAndDefinitions(inFile, mapTerms, terms);

        Sequence<String> wordsList = new Sequence1L<>();
        String[] termArray = new String[terms.size()];
        int i = 0;
        while (0 < terms.size()) {
            String nextTerm = alphabeticalOrder(terms);
            wordsList.add(wordsList.length(), nextTerm);
            termArray[i] = nextTerm;
            i++;
        }

        outputIndex(out, wordsList, mapTerms, termArray, outputFile);

        in.close();
        out.close();
    }

}
