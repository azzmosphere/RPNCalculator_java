package rpncalculator;

import rpncalculator.RPNOperators.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.math.BigDecimal;

// RPNController encapsulates all Reverse Polish Notation behaviour.
//
// Responsibility: manage both RPN Application Stacks (rpnStack and undoStack), process the RPN String
//                 received from RPNCalculator, and delegate all operations to the IOperator implementing
//                 RPNOperator sub-classes (Interpreter Pattern).
//
// This Class also generates a String representing the current rpnStack contents, which RPNCalculator
// calls to print to stdout.

public class RPNController {

    // public in case we need to inject and/or mock these in later unit tests
    public Stack<BigDecimal> rpnStack; // BigDecimal for simple, consistent, high-precision arithmetic
    public Stack<ArrayList<BigDecimal>> undoStack; // Operators take variable input so we need to undo variable operands

    public RPNController() {
        rpnStack = new Stack<BigDecimal>();
        undoStack = new Stack<ArrayList<BigDecimal>>();
    }

    public void process(String line) throws Exception {

        // iteratively parse the String that we've already received from stdin in RPNCalculator
        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {

            // convert all Integers and Floats to BigDecimal for consistent, high-precision arithmetic
            if (lineScanner.hasNextInt()) {
                BigDecimal bigDecimalInt = new BigDecimal(lineScanner.nextInt());
                RPNCalculator.precisionStorage(bigDecimalInt);
                rpnStack.push(bigDecimalInt);
                continue;
            }
            if (lineScanner.hasNextFloat()) {
                BigDecimal bigDecimalFloat = new BigDecimal(lineScanner.nextFloat());
                RPNCalculator.precisionStorage(bigDecimalFloat);
                rpnStack.push(bigDecimalFloat);
                continue;
            }

            // We're only dealing with Operators from here on, nothing more to push onto rpnStack
            String operatorString;
            IOperator operator = null;
            int matched = 0;

            // use an Enum lookup to avoid a large if-else Operator block
            for (RPNOperatorEnum rpnOperator : RPNOperatorEnum.values()) {
                if (lineScanner.hasNext(rpnOperator.patternMatch())) {
                    operatorString = lineScanner.next(rpnOperator.patternMatch());
                    operator = rpnOperator.operator();
                    matched = 1;
                    break;
                }
            }
            if (matched == 0) {
                continue; // invalid input = ignore all other input
            }

            // if we've not already broken out then we have a valid operation to execute.
            // If any operator detects that there are insufficient parameters to process on rpnStack
            // it will throw an ImminentEmptyStackException and do nothing:
            try {
                if (operator != null) {
                    operator.execute(rpnStack, undoStack);
                } else {
                    break; // invalid input = ignore all other input
                }
            } catch (ImminentEmptyStackException exception) {

                try {
                    int position = lineScanner.match().end();
                    System.out.println("operator " + operator.toString() + " (position: " + position + "): insufficient parameters");
                } catch (IllegalStateException matchException) {
                    System.out.println("operator " + operator.toString() + " (position: UNKNOWN): insufficient parameters");
                }

                break;
            }

        }
    }

    // return the current content of rpnStack as a String, for RPNCalculator to print to stdout
    public String currentStack() {
        String stackString = "";

        Iterator<BigDecimal> iterator = rpnStack.iterator();
        while (iterator.hasNext()) {
            BigDecimal nextItem = iterator.next();
            nextItem = RPNCalculator.precisionDisplay(nextItem); // print DISPLAY precision not storage precision
            stackString += nextItem.toString();
            if (iterator.hasNext()) {
                stackString += " ";
            }
        }

        return stackString;
    }

    // ENum lookup to avoid a messy if-else Operator block in process()
    // TODO move this into a configuration file for external maintenance outside of this code
    public enum RPNOperatorEnum {

        ADDITION_OP {
            @Override
            public Pattern patternMatch() { return Pattern.compile("\\+"); }
            @Override
            public IOperator operator() { return new AdditionOperator(); }
        },

        SUBTRACTION_OPERATOR {
            @Override
            public Pattern patternMatch() { return Pattern.compile("\\-"); }
            @Override
            public IOperator operator() { return new SubtractionOperator(); }
        },

        MULTIPLICATION_OPERATOR {
            @Override
            public Pattern patternMatch() { return Pattern.compile("\\*"); }
            @Override
            public IOperator operator() { return new MultiplicationOperator(); }
        },

        DIVISION_OPERATOR {
            @Override
            public Pattern patternMatch() { return Pattern.compile("\\/"); }
            @Override
            public IOperator operator() { return new DivisionOperator(); }
        },

        SQUAREROOT_OPERATOR {
            @Override
            public Pattern patternMatch() { return Pattern.compile("sqrt"); }
            @Override
            public IOperator operator() { return new SquareRootOperator(); }
        },

        UNDO_OPERATOR {
            @Override
            public Pattern patternMatch() { return Pattern.compile("undo"); }
            @Override
            public IOperator operator() { return new UndoOperator(); }
        },

        CLEAR_OPERATOR {
            @Override
            public Pattern patternMatch() { return Pattern.compile("clear"); }
            @Override
            public IOperator operator() { return new ClearOperator(); }
        };

        // Overridden by Enum values above
        public Pattern patternMatch() { return null; }
        public IOperator operator() { return null; }

    }
}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved