package rpncalculator;

import java.util.Scanner;
import java.math.BigDecimal;

// RPNCalculator represents the Application itself, declaring static void main()
//
// Responsibility: handle input from stdin, print output to stdout, and delegate all of
//                 the actual work to the RPNController class.
//
// This Class also declares several static constants and methods to ensure consistent
// BigDecimal precision and rounding behaviour across the Application. In a more complex
// Application I'd declare these in a separate Constants or Functions java file, to keep
// the high-level Application concerns separate from global helpers and constants, but in
// an tiny Application like this it seems appropriate to keep things simple.

public class RPNCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RPNController controller = new RPNController();

        // loop forever, until we catch an unrecognised Exception or SIGHUP etc.
        while (scanner.hasNext()) {

            // I've chosen to centralise all Exception handling here in main() as this is a very simple
            // Application and we're dealing with the same Exception types throughout all Classes, so
            // rather than repeat this logic across all other Classes and re-throw Exceptions I've kept
            // all of the Exception handling isolated here as it's easier to maintain in this simple example

            try {

                String line = scanner.nextLine();
                controller.process(line);
                System.out.println("stack: " + controller.currentStack());

            } catch (IllegalStateException exception) {
                System.out.println("ERROR: stdin is closed, re-opening stdin, please try again:");
                scanner = new Scanner(System.in);
            } catch (Exception exception) {
                System.out.println("ERROR: caught an unrecognised exception:");
                exception.printStackTrace();
                System.out.println("Exiting the application.");
                System.exit(1);
            }

        }

    }

    // BigDecimal precision and rounding constants and class methods, centralised here for simplicity and consistency

    static final int PRECISION_STORAGE = 15;
    static final int PRECISION_DISPLAY = 10;
    static final int PRECISION_ROUNDING = BigDecimal.ROUND_DOWN;

    public static BigDecimal precisionStorage(BigDecimal bigDecimal) {
        BigDecimal bigDecimalStorage = bigDecimal.setScale(RPNCalculator.PRECISION_STORAGE, RPNCalculator.PRECISION_ROUNDING);
        return bigDecimalStorage;
    }

    public static BigDecimal precisionDisplay(BigDecimal bigDecimal) {
        BigDecimal bigDecimalDisplay;

        try {
            bigDecimalDisplay = bigDecimal.setScale(0, BigDecimal.ROUND_UNNECESSARY);
        } catch (ArithmeticException exception) {
            // therefore we have non-zero decimal places
            bigDecimalDisplay = bigDecimal.setScale(RPNCalculator.PRECISION_DISPLAY, RPNCalculator.PRECISION_ROUNDING).stripTrailingZeros();
        }

        return bigDecimalDisplay;
    }

}

// Copyright Niall Young <niall.young@icloud.com> 2018
// All Rights Reserved