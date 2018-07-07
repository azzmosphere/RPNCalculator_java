package rpncalculator.RPNOperators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClearOperatorTest {

    public ClearOperatorTest() {
    }

    @Test
    public void test_execute() throws Exception {
        System.out.println("_execute");

        ClearOperator instance = new ClearOperator();
        Stack<BigDecimal> rpnStack = new Stack<BigDecimal>();
        Stack<ArrayList<BigDecimal>> undoStack = new Stack<ArrayList<BigDecimal>>();

        // size=0
        assertEquals(0, rpnStack.size());
        try {
            instance.execute(rpnStack, undoStack);
        } catch (Exception exception) {
            fail("Caught an Exception!");
        }
        assertEquals(0, rpnStack.size());

        // size=1
        rpnStack.add(new BigDecimal(1));
        assertEquals(1, rpnStack.size());
        try {
            instance.execute(rpnStack, undoStack);
        } catch (Exception exception) {
            fail("Caught an Exception!");
        }
        assertEquals(0, rpnStack.size());

        // size=2
        rpnStack.add(new BigDecimal(1));
        rpnStack.add(new BigDecimal(2));
        assertEquals(2, rpnStack.size());
        try {
            instance.execute(rpnStack, undoStack);
        } catch (Exception exception) {
            fail("Caught an Exception!");
        }
        assertEquals(0, rpnStack.size());

    }

    @Test
    public void testToString() {
        System.out.println("toString");
        ClearOperator instance = new ClearOperator();
        assertEquals("clear", instance.toString());
    }
}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved