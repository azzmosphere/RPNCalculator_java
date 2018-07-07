package rpncalculator.RPNOperators;

import rpncalculator.RPNOperator;
import rpncalculator.ImminentEmptyStackException;

import java.util.ArrayList;
import java.util.Stack;
import java.math.BigDecimal;
import java.lang.Math.*;

// SquareRootOperator represents a simple addition operation class, implementing the IOperator interface.

public final class SquareRootOperator extends RPNOperator {

    @Override
    public BigDecimal _execute(Stack<BigDecimal> rpnStack, Stack<ArrayList<BigDecimal>> undoStack) throws Exception {

        // don't proceed if rpnStack has insufficient parameters
        if (rpnStack.size() < 1) {
            throw new ImminentEmptyStackException();
        }

        BigDecimal operand = rpnStack.pop();

        ArrayList<BigDecimal> undo = new ArrayList<BigDecimal>();
        undo.add(operand);
        undoStack.push(undo);

        Double result = java.lang.Math.sqrt(operand.doubleValue());
        BigDecimal bigDecimalResult = new BigDecimal(result);

        return bigDecimalResult;
    }

    @Override
    public String toString() { return "sqrt"; }

}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved