package rpncalculator;

import java.util.EmptyStackException;

// ImminentEmptyStackException is a custom Exception class used by the RPNOperator subclasses to indicate
// that there are insufficient parameters for that operation on the rpnStack.

public class ImminentEmptyStackException extends EmptyStackException {

    public ImminentEmptyStackException() { super(); }

}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved