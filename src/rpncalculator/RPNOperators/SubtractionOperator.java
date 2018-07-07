package rpncalculator.RPNOperators;

import rpncalculator.RPNOperator;
import rpncalculator.ImminentEmptyStackException;

import java.util.ArrayList;
import java.util.Stack;
import java.math.BigDecimal;

// SubtractionOperator represents a simple addition operation class, implementing the IOperator interface.

public final class SubtractionOperator extends RPNOperator {

    @Override
    public BigDecimal _execute(Stack<BigDecimal> rpnStack, Stack<ArrayList<BigDecimal>> undoStack) throws Exception {

        // don't proceed if rpnStack has insufficient parameters
        if (rpnStack.size() < 2) {
            throw new ImminentEmptyStackException();
        }

        BigDecimal rhs = rpnStack.pop();
        BigDecimal lhs = rpnStack.pop();

        ArrayList<BigDecimal> undo = new ArrayList<BigDecimal>();
        undo.add(lhs); undo.add(rhs);
        undoStack.push(undo);

        BigDecimal result = lhs.subtract(rhs);

        return result;
    }

    @Override
    public String toString() { return "-"; }

}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved