package rpncalculator;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import static org.junit.Assert.*;

public class RPNControllerTest {

    public RPNControllerTest() {
    }

    @Test
    public void testProcess_currentStack() {
        System.out.println("process() and currentStack()");

        RPNController rpnController = new RPNController();

        ArrayList<ArrayList<String>> exampleTestData = exampleTestData();
        Iterator<ArrayList<String>> exampleIterator = exampleTestData.iterator();

        while (exampleIterator.hasNext()) {
            ArrayList<String> nextExample = exampleIterator.next();
            Iterator<String> stringIterator = nextExample.iterator();

            while (stringIterator.hasNext()) {
                String input = stringIterator.next();
                String stack = stringIterator.next();

                try {
                    rpnController.process(input);
                } catch (Exception exception) {
                    fail("Caught an Exception!");
                }

                assertEquals(stack, rpnController.currentStack());
            }
        }

    }

    // example test data from the problem statement (slightly modified, see below)
    public ArrayList<ArrayList<String>> exampleTestData() {

        // between each example we'll force a "clear" using "point5"
        ArrayList<String> point5 = new ArrayList<String>();
        point5.add("clear"); point5.add("");

        // build the top-level ArrayList, with "point5" "clear" between each to reset example state
        ArrayList<ArrayList<String>> exampleTestData = new ArrayList<ArrayList<String>>();

        ArrayList<String> example1 = new ArrayList<String>();
        example1.add("5 2"); example1.add("5 2");
        exampleTestData.add(example1);

            exampleTestData.add(point5);

        ArrayList<String> example2 = new ArrayList<String>();
        example2.add("clear 2 sqrt"); example2.add("1.4142135623");
        example2.add("clear 9 sqrt"); example2.add("3");
        exampleTestData.add(example2);

            exampleTestData.add(point5);

        ArrayList<String> example3 = new ArrayList<String>();
        example3.add("clear 5 2 -"); example3.add("3");
        example3.add("3 -"); example3.add("0");
        example3.add("clear"); example3.add("");
        exampleTestData.add(example3);

            exampleTestData.add(point5);

        ArrayList<String> example4 = new ArrayList<String>();
        example4.add("5 4 3 2"); example4.add("5 4 3 2");
        example4.add("undo undo *"); example4.add("20");
        example4.add("5 *"); example4.add("100");
        example4.add("undo"); example4.add("20 5");
        exampleTestData.add(example4);

            exampleTestData.add(point5);

        ArrayList<String> example5 = new ArrayList<String>();
        example5.add("7 12 2 /"); example5.add("7 6");
        example5.add("*"); example5.add("42");
        example5.add("4 /"); example5.add("10.5");
        exampleTestData.add(example5);

            exampleTestData.add(point5);

        ArrayList<String> example6 = new ArrayList<String>();
        example6.add("1 2 3 4 5"); example6.add("1 2 3 4 5");
        example6.add("*"); example6.add("1 2 3 20");
        example6.add("clear 3 4 -"); example6.add("-1");
        exampleTestData.add(example6);

            exampleTestData.add(point5);

        ArrayList<String> example7 = new ArrayList<String>();
        example7.add("1 2 3 4 5"); example7.add("1 2 3 4 5");
        example7.add("* * * *"); example7.add("120");
        exampleTestData.add(example7);

            exampleTestData.add(point5);

        ArrayList<String> example8 = new ArrayList<String>();
        example8.add("1 2 3 * 5 + * * 6 5"); example8.add("11");
        exampleTestData.add(example8);

            exampleTestData.add(point5); // in case we add more examples later

        return exampleTestData;
    }
}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved