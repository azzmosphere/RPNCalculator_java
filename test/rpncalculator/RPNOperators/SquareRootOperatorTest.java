package rpncalculator.RPNOperators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;
import org.junit.Test;
import static org.junit.Assert.*;

public class SquareRootOperatorTest {

    public SquareRootOperatorTest() {
    }

    @Test
    public void test_execute() throws Exception {
        System.out.println("_execute");

        SquareRootOperator instance = new SquareRootOperator();
        Stack<BigDecimal> rpnStack = new Stack<BigDecimal>();
        Stack<ArrayList<BigDecimal>> undoStack = new Stack<ArrayList<BigDecimal>>();

        // size=0 will throw an Exception
        assertEquals(0, rpnStack.size());
        try {
            instance.execute(rpnStack, undoStack);
        } catch (Exception exception) {
            assertEquals("class rpncalculator.ImminentEmptyStackException", exception.getClass().toString());
        }

        // size=1 will not throw an Exception, and stack will remain size=1
        rpnStack.add(new BigDecimal(2));
        assertEquals(1, rpnStack.size());
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
        SquareRootOperator instance = new SquareRootOperator();
        assertEquals("sqrt", instance.toString());
    }
}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved