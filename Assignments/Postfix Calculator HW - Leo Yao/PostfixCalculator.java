import java.lang.Math;

public class PostfixCalculator {

    public static Double evaluate(String postfix) {
        ArrayStack<Double> S = new ArrayStack<>();

        // Split the postfix expression Doubleo tokens
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (isNumeric(token)) {
                // If the token is a number, push it onto the operand stack
                S.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                // If the token is an operator, pop operands from the stack and perform the operation
                Double operand2 = S.pop();
                Double operand1 = S.pop();
                Double result = performOperation(operand1, operand2, token);
                S.push(result);
            }
        }

        // The final result should be on the operand stack
        if (!S.isEmpty()) {
            return S.pop();
        } else {
            throw new IllegalArgumentException("Invalid postfix expression");
        }
    }

    private static boolean isNumeric(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.matches("[+\\-*/^]");
    }

    private static Double performOperation(Double operand1, Double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                // Handling the case of division by zero
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            case "^":
                return Math.pow(operand1, operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
