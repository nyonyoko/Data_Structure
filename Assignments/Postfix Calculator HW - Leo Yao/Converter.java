import java.util.*;

public class Converter {
    private String infix; // Instance variable to store the infix expression

    public Converter(String infix) {
        this.infix = infix;
    }

    public String toPostFix() {
        List<String> tokens = ParserHelper.parse(infix.toCharArray());
        // Create an empty stack (S) and an empty output string (postfix)
        String postfix = "";
        // Employ a stack to facilitate the conversion process
        Stack<String> S = new ArrayStack<>();

        // Define operator precedence
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('^', 3);
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('+', 1);
        precedence.put('-', 1);

        // Identify '-' operator as unary or binary
        boolean lastTokenWasOperator = true;

        // Read all tokens from left to right and process them accordingly
        for (String token : tokens) {
            // Each character token for examination
            char c = token.charAt(0);
            // For an operand, it appends it to the output string
            if (Character.isDigit(c)) {
                postfix += token + " ";
                lastTokenWasOperator = false;
            // For an open parenthesis, it pushes it onto the stack
            } else if (c == '(') {
                S.push(token);
                lastTokenWasOperator = true;
            // For a closed parenthesis, it pops and discards both parentheses
            } else if (c == ')') {
                while (!S.isEmpty() && !S.top().equals("(")) {
                    postfix += S.pop() + " ";
                }
                S.pop();
                lastTokenWasOperator = false;
            // For an operator, it checks the precedence and pushes or pops operators as needed
            } else if (c == '-' && lastTokenWasOperator) {
                // Handle unary - as a special symbol ~
                S.push("~");
                lastTokenWasOperator = true;
            } else {
                while (!S.isEmpty() && precedence.containsKey(S.top().charAt(0)) &&
                       precedence.get(S.top().charAt(0)) >= precedence.get(c)) {
                    postfix += S.pop() + " ";
                }
                S.push(token);
                lastTokenWasOperator = true;
            }
        }

        // Pop and append remaining operators from the stack
        while (!S.isEmpty()) {
            postfix += S.pop() + " ";
        }

        return postfix.trim(); // Trim the final result
    }
}