package rpncalculator.RPNOperators;

import rpncalculator.RPNOperator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;
import java.math.BigDecimal;

// UndoOperator represents a simple addition operation class, implementing the IOperator interface.

public final class UndoOperator extends RPNOperator {

    @Override
    public BigDecimal _execute(Stack<BigDecimal> rpnStack, Stack<ArrayList<BigDecimal>> undoStack) throws Exception {

        if (!rpnStack.isEmpty()) {
            BigDecimal lastResult = rpnStack.pop(); // discard the top-most item/result

            // NOTE: originally I interpreted "undo" as completely reversing the previous operation,
            // but checking with the example output in the problem statement it looks like it isn't
            // a complete reversal e.g. stack="20"; input="5 *"; stack="100"; input="undo"; stack="20 5"!
            //
            // By only removing the top-most item, which isn't necessarily the result from a previous
            // operation, our undo operation seems a bit confusing and we're polluting the Stack with
            // input numbers from the previous operation e.g. the "5".
            //
            // I was planning to implement a full-reversal undo which would make the final stack="20",
            // and this would mean we could undo a "clear", and undo an "undo". Abandoned this approach
            // once I realised this wasn't what was intended from the examples.

            if (!undoStack.isEmpty()) {
                ArrayList<BigDecimal> lastOperands = undoStack.pop();
                Iterator<BigDecimal> iterator = lastOperands.iterator();

                while (iterator.hasNext()) {
                    rpnStack.push(iterator.next());
                }
            }
        }

        return null;
    }

    @Override
    public String toString() { return "undo"; }

}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved