package rpncalculator.RPNOperators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;
import org.junit.Test;
import static org.junit.Assert.*;

public class MultiplicationOperatorTest {

    public MultiplicationOperatorTest() {
    }

    @Test
    public void test_execute() throws Exception {
        System.out.println("_execute");

        MultiplicationOperator instance = new MultiplicationOperator();
        Stack<BigDecimal> rpnStack = new Stack<BigDecimal>();
        Stack<ArrayList<BigDecimal>> undoStack = new Stack<ArrayList<BigDecimal>>();

        // size=0 will throw an Exception
        assertEquals(0, rpnStack.size());
        try {
            instance.execute(rpnStack, undoStack);
        } catch (Exception exception) {
            assertEquals("class rpncalculator.ImminentEmptyStackException", exception.getClass().toString());
        }

        // size=1 will throw an Exception
        rpnStack.add(new BigDecimal(1));
        assertEquals(1, rpnStack.size());
        try {
            instance.execute(rpnStack, undoStack);
        } catch (Exception exception) {
            assertEquals("class rpncalculator.ImminentEmptyStackException", exception.getClass().toString());
        }

        // size=2 will not throw an Exception, but stack will be reduced to size=1
        rpnStack.add(new BigDecimal(2));
        assertEquals(2, rpnStack.size());
        try {
            instance.execute(rpnStack, undoStack);
        } catch (Exception exception) {
            fail("Caught an Exception!");
        }
        assertEquals(1, rpnStack.size());

    }

    @Test
    public void testToString() {
        System.out.println("toString");
        MultiplicationOperator instance = new MultiplicationOperator();
        assertEquals("*", instance.toString());
    }
}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved