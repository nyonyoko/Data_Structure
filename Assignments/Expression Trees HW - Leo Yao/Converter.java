import java.util.*;

public class Converter {
    private String infix; // Instance variable to store the infix expression

    public Converter(String infix) {
        this.infix = infix;
    }

    public boolean isOperator(String s) {
        return (s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("^"));
    }

    public String toPostFix() {
        String[] tokens = infix.split("((?<=\\+|\\-|\\*|/|\\(|\\))|(?=\\+|\\-|\\*|/|\\(|\\)))");
    
        String postfix = "";
        Stack<String> S = new ArrayStack<>();
        Map<String, Integer> precedence = new HashMap<>();
        precedence.put("^", 3);
        precedence.put("*", 2);
        precedence.put("/", 2);
        precedence.put("+", 1);
        precedence.put("-", 1);
    
        boolean lastTokenWasOperator = true;
    
        for (String token : tokens) {
            token = token.trim();
    
            if (token.isEmpty()) {
                continue;
            }
    
            char c = token.charAt(0);
    
            if (Character.isDigit(c)) {
                postfix += token + " ";
                lastTokenWasOperator = false;
            } else if (c == '(') {
                S.push(token);
                lastTokenWasOperator = true;
            } else if (c == ')') {
                while (!S.isEmpty() && !S.top().equals("(")) {
                    postfix += S.pop() + " ";
                }
                S.pop();
                lastTokenWasOperator = false;
            } else if (isOperator(token)) {
                if (token.equals("-") && lastTokenWasOperator) {
                    S.push("~");
                    lastTokenWasOperator = true;
                } else {
                    while (!S.isEmpty() && isOperator(S.top()) &&
                           precedence.get(S.top()) >= precedence.get(token)) {
                        postfix += S.pop() + " ";
                    }
                    S.push(token);
                    lastTokenWasOperator = true;
                }
            } else {
                System.err.println("Invalid token: " + token);
            }
        }
    
        while (!S.isEmpty()) {
            postfix += S.pop() + " ";
        }
    
        return postfix.trim();
    }
    
    
}