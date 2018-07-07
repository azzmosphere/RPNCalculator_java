package rpncalculator.RPNOperators;

import rpncalculator.RPNOperator;

import java.util.ArrayList;
import java.util.Stack;
import java.math.BigDecimal;

// ClearOperator represents a simple addition operation class, implementing the IOperator interface.

public final class ClearOperator extends RPNOperator {

    @Override
    protected BigDecimal _execute(Stack<BigDecimal> rpnStack, Stack<ArrayList<BigDecimal>> undoStack) throws Exception {
        rpnStack.clear();
        undoStack.clear();
        return null;
    }

    @Override
    public String toString() { return "clear"; }

}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved