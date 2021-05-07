import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void addToSetAvoidingSubstringsTest1() {
        SimpleWriter out = new SimpleWriter1L();
        String test1 = "Hello World";
        String test2 = "Hello";
        Set<String> result = new Set1L<>();
        result.add(test1);

        StringReassembly.addToSetAvoidingSubstrings(result, test2);
        out.println(result);

        out.close();
        //Should print "Hello World"

    }

    @Test
    public void addToSetAvoidingSubstringsTest2() {
        SimpleWriter out = new SimpleWriter1L();
        String test1 = "Ambidextrous";
        String test2 = "idextr";
        Set<String> result = new Set1L<>();
        result.add(test1);

        StringReassembly.addToSetAvoidingSubstrings(result, test2);
        out.println(result);

        out.close();
        //Should print "Ambidextrous"
    }

    @Test
    public void addToSetAvoidingSubstringsTest3() {
        SimpleWriter out = new SimpleWriter1L();
        String word1 = "Doggy";
        String word2 = "ogy";
        Set<String> result = new Set1L<>();
        result.add(word2);

        //Added string Set has the word Doggy already put in
        StringReassembly.addToSetAvoidingSubstrings(result, word1);
        out.println(result);

        out.close();
        //Should print "ogy, Doggy"

    }

    @Test
    public void addToSetAvoidingSubstringsTest4() {
        SimpleWriter out = new SimpleWriter1L();
        String word1 = "Doggy";
        String word2 = "Dog";
        String word3 = "og";
        Set<String> result = new Set1L<>();
        result.add(word2);
        result.add(word3);
        //Added string Set has "Dog, og" already put in
        StringReassembly.addToSetAvoidingSubstrings(result, word1);
        out.println(result);

        out.close();
        //Should print "Doggy"
    }

    @Test
    public void addToSetAvoidingSubstringsTest5() {
        SimpleWriter out = new SimpleWriter1L();
        String word1 = "Carmel";
        String word2 = "Mel";
        String word3 = "Medical";
        String word4 = "car";
        Set<String> result = new Set1L<>();
        result.add(word2);
        result.add(word3);
        result.add(word4);
        //Added string Set has "Mel, Medical, car" already put in
        StringReassembly.addToSetAvoidingSubstrings(result, word1);
        out.println(result);

        out.close();
        //Should print "Carmel, Medical"
    }

    @Test
    public void printWithLineSeparatorsTest1() {
        SimpleWriter out = new SimpleWriter1L();
        String test1 = "Let's ~ Go ~ Buckeyes!";

        StringReassembly.printWithLineSeparators(test1, out);

        out.close();
    }

    @Test
    public void printWithLineSeparatorsTest2() {
        SimpleWriter out = new SimpleWriter1L();
        String test1 = "Let's ~ Go ~ Buckeyes! ~ ";

        StringReassembly.printWithLineSeparators(test1, out);

        out.close();
    }

    @Test
    public void printWithLineSeparatorsTest3() {
        SimpleWriter out = new SimpleWriter1L();
        String test1 = "Let's Go ~ Buckeyes!";

        StringReassembly.printWithLineSeparators(test1, out);

        out.close();
    }

    @Test
    public void combinationTest1() {

        String test1 = "Peter Piper picked a pe";
        String test2 = "peck of pickled peppers";
        String resultExp = "Peter Piper picked a peck of pickled peppers";

        int overlay = 2;

        String result = StringReassembly.combination(test1, test2, overlay);

        assertEquals(resultExp, result);

    }

    @Test
    public void combinationTest2() {

        String test1 = "Peter Piper picked a pe";
        String test2 = "ck of pickled peppers";
        String resultExp = "Peter Piper picked a peck of pickled peppers";

        int overlay = 0;

        String result = StringReassembly.combination(test1, test2, overlay);

        assertEquals(resultExp, result);

    }

    @Test
    public void combinationTest3() {

        String test1 = "Peter Piper";
        String test2 = "Peter Piper";
        String resultExp = "Peter Piper";

        int overlay = 11;

        String result = StringReassembly.combination(test1, test2, overlay);

        assertEquals(resultExp, result);

    }

}
