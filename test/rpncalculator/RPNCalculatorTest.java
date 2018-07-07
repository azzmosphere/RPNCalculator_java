package rpncalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import static org.junit.Assert.*;

public class RPNCalculatorTest {

    public RPNCalculatorTest() {
    }

    @Test
    public void testPrecisionStorage() {
        System.out.println("precisionStorage");

        ArrayList<BigDecimal> bigDecimalList;
        Iterator<BigDecimal> iterator;

        // Floats
        bigDecimalList = this.listOfBigDecimalFloats();
        iterator = bigDecimalList.iterator();
        while (iterator.hasNext()) {
            BigDecimal nextItem = iterator.next();

            assertEquals(RPNCalculator.PRECISION_STORAGE, RPNCalculator.precisionStorage(nextItem).scale());
        }

        // Integers
        bigDecimalList = this.listOfBigDecimalFloats();
        iterator = bigDecimalList.iterator();
        while (iterator.hasNext()) {
            BigDecimal nextItem = iterator.next();

            assertEquals(RPNCalculator.PRECISION_STORAGE, RPNCalculator.precisionStorage(nextItem).scale());
        }
    }

    @Test
    public void testPrecisionDisplayIntegers() {
        System.out.println("precisionDisplay() Integers");

        ArrayList<BigDecimal> bigDecimalList = this.listOfBigDecimalIntegers();
        Iterator<BigDecimal> iterator = bigDecimalList.iterator();
        while (iterator.hasNext()) {
            BigDecimal nextItem = iterator.next();
            nextItem = RPNCalculator.precisionDisplay(nextItem);
            assertEquals(0, nextItem.scale());
        }
    }

    @Test
    public void testPrecisionDisplayFloats() {
        System.out.println("precisionDisplay() Floats");

        ArrayList<BigDecimal> bigDecimalList = this.listOfBigDecimalFloats();
        Iterator<BigDecimal> iterator = bigDecimalList.iterator();
        while (iterator.hasNext()) {
            BigDecimal nextItem = iterator.next();
            nextItem = RPNCalculator.precisionDisplay(nextItem);
            assertTrue(nextItem.scale() >= 0 && nextItem.scale() <= 10);
        }
    }

    public ArrayList<BigDecimal> listOfBigDecimalIntegers() {
        ArrayList<BigDecimal> bigDecimalList = new ArrayList<BigDecimal>();
        bigDecimalList.add(new BigDecimal(0));
        bigDecimalList.add(new BigDecimal(-1));
        bigDecimalList.add(new BigDecimal(9));
        bigDecimalList.add(new BigDecimal(42));
        bigDecimalList.add(new BigDecimal(9999));

        return bigDecimalList;
    }

    public ArrayList<BigDecimal> listOfBigDecimalFloats() {
        ArrayList<BigDecimal> bigDecimalList = new ArrayList<BigDecimal>();
        bigDecimalList.add(new BigDecimal(0.0));
        bigDecimalList.add(new BigDecimal(-1.23));
        bigDecimalList.add(new BigDecimal(10.111111111111111));
        bigDecimalList.add(new BigDecimal(1000.000001100000));
        bigDecimalList.add(new BigDecimal(11.2345678901234567890));

        return bigDecimalList;
    }
}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved