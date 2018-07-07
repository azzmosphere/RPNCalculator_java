package rpncalculator;

import java.util.ArrayList;
import java.util.Stack;
import java.math.BigDecimal;

// IOperator declares an Interface for all RPNOperator sub-classes to implement
//
// Together with RPNOperator this implements the Interpreter Pattern, where each interpreted operator
// is represented by its own Class, adhering to a common public interface.

public interface IOperator {
    public void execute(Stack<BigDecimal> rpnStack, Stack<ArrayList<BigDecimal>> undoStack) throws Exception;
}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved