package rpncalculator;

import java.util.ArrayList;
import java.util.Stack;
import java.math.BigDecimal;

// RPNOperator is an abstract superclass for shared behaviour for all RPNOperator subclasses.
//
// Together with IOperator this implements the Interpreter Pattern, where each interpreted operator
// is represented by its own Class, adhering to a common public interface.

public abstract class RPNOperator implements IOperator {

    @Override
    final public void execute(Stack<BigDecimal> rpnStack, Stack<ArrayList<BigDecimal>> undoStack) throws Exception {

        BigDecimal result = _execute(rpnStack, undoStack);

        if (result != null) {
            rpnStack.push(result);
        }

    }

    protected abstract BigDecimal _execute(Stack<BigDecimal> rpnStack, Stack<ArrayList<BigDecimal>> undoStack) throws Exception;

    public String toString() {
        // override in concrete subclasses
        return "";
    }
}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved