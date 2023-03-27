import java.util.Scanner;

/**
 * This class implements and defines all necessary functions to implement OpenTelemetry to collect
 * and visualize data from a basic calculator system
 *
 * @Author: Robert Morabito
 * @Student#: 7093230
 * @Date: 2023-03-25
 */

public class Calculator {
    /**
     * This constructor initializes and calls all necessary variables and methods
     */
    public Calculator() {
        // Welcome message
        System.out.print("Welcome to the calculator!\nThis calculator supports:\n-addition(+)\n-subtraction(-)\n-multiplication(*)\n-division(/)\n-logarithms(log & ln)\n-basic trig(sin & cos & tan)\n-exponents(^)\n-modulo(%)\n");

        // Loop until the user is done
        while (true) {
            // Get the user input
            String input = getInput();

            // Check for stop command
            if (input.equals("q")) {
                break;
            }

            // Perform evaluation
            double result = evaluateExpression(input);

            // Output results
            System.out.println(input + " = " + result);
        }

    }// Calculator

    public static void main(String[] args) {
        Calculator C = new Calculator();
    }

    /**
     * This method returns the user input from the console
     *
     * @return userInput
     */
    public String getInput() {
        System.out.print("Please enter an equation to evaluate (or enter \"q\" to quit): ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }// getInput

    /**
     * This method evaluated a string of math operations and returns the result
     *
     * @param input
     * @return
     */
    private double evaluateExpression(String input) {
        // Local variables
        double result = 0.0;
        String operand = "";

        // Split the input
        String[] splitInput = input.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=[^\\d.])|(?<=[^\\d.])(?=[\\d.])|(?<=\\D)(?=[(])");

        // Loop through and evaluate each portion of the input
        for (int i = 0; i < splitInput.length; i++) {
            // Trim blank space
            String in = splitInput[i].trim();

            // Check if we have an operator
            if (in.matches("[+*/%^()sincotalg-]")) {
                if (in.matches("[sincotalg]")) {
                    operand = operand + in;
                }
                else if(in.matches("[+/*^%-]")){
                    operand = in;
                }
                continue;
            }

            // Check if we are looking at a single operand function (i.e., trig, log, ln)
            if (operand.matches("sin|cos|tan|log|ln")) {
                // Check if there is an argument for the function
                double temp = Double.parseDouble(in);
                result = eval(operand, temp);
                operand = "";
                // Required to skip over argument
                i++;
            } else {
                double temp = Double.parseDouble(in);
                // Check we don't have a single value at the moment
                if (i == 0) {
                    result = temp;
                } else {
                    // Perform dual operand evaluation
                    result = eval(operand, result, temp);
                    operand = "";
                }
            }
        }
        return result;
    }// evaluateExpression

    /**
     * This method is an evaluation function for a single operator and operand (i.e., trig, log, ln)
     *
     * @param operator
     * @param operand
     * @return result
     */
    private double eval(String operator, double operand) {
        if ("sin".equals(operator)) {
            return Math.sin(operand);
        } else if ("cos".equals(operator)) {
            return Math.cos(operand);
        } else if ("tan".equals(operator)) {
            return Math.tan(operand);
        } else if ("log".equals(operator)) {
            return Math.log10(operand);
        } else if ("ln".equals(operator)) {
            return Math.log(operand);
        }
        return 0;
    }

    /**
     * This method is an evaluation function for multiple operands
     *
     * @param operator
     * @param operand1
     * @param operand2
     * @return result
     */
    private double eval(String operator, double operand1, double operand2) {
        if ("+".equals(operator)) {
            return operand1 + operand2;
        } else if ("-".equals(operator)) {
            return operand1 - operand2;
        } else if ("*".equals(operator)) {
            return operand1 * operand2;
        } else if ("/".equals(operator)) {
            return operand1 / operand2;
        } else if ("^".equals(operator)) {
            return Math.pow(operand1, operand2);
        } else if ("%".equals(operator)) {
            return operand1 % operand2;
        }
        return 0;
    }
}// Calculator
