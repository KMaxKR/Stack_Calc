package strc.data.calculator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import strc.data.calculator.enums.Brackets;
import strc.data.calculator.enums.Operators;
import strc.data.calculator.exceptions.WrongInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CalcService {
    private final Logger logger = LoggerFactory.getLogger(CalcService.class);

    public double calculate(String input) {
        if (input == null || input.isEmpty()) {
            throw new WrongInputException("Input can not be empty");
        }

        Stack<Double> stack = getNums(input);
        Stack<String> operations = getOperations(input);

//        if (operations.size() - stack.size() > 1 || stack.size() - operations.size() == 0){
//            throw new WrongInputException("Wrong input data");
//        }

        while (stack.size() != 1){
            double r = 0;

            // Process ALL brackets first - keep processing until no brackets left
            while (operations.contains(Brackets.LEFT_BRACKET.getBracket()) && operations.contains(Brackets.RIGHT_BRACKET.getBracket())){
                int s = operations.indexOf(Brackets.LEFT_BRACKET.getBracket());
                int f = operations.indexOf(Brackets.RIGHT_BRACKET.getBracket());

                // Find the innermost brackets by looking for the first closing bracket
                // and then finding its matching opening bracket
                for (int i = 0; i < operations.size(); i++) {
                    if (operations.get(i).equals(Brackets.RIGHT_BRACKET.getBracket())) {
                        f = i;
                        // Find matching opening bracket
                        for (int j = f - 1; j >= 0; j--) {
                            if (operations.get(j).equals(Brackets.LEFT_BRACKET.getBracket())) {
                                s = j;
                                break;
                            }
                        }
                        break;
                    }
                }

                // Evaluate inside brackets (start from s+1 to f-1)
                int bracketContentSize = f - s - 1;
                for (int i = 0; i < bracketContentSize; i++){
                    // Handle operator priority inside brackets
                    boolean hasPriorityOps = true;
                    while (hasPriorityOps && (f - s - 1) > 0) {
                        hasPriorityOps = false;
                        for (int j = s + 1; j < f; j++) {
                            String op = operations.elementAt(j);
                            if (op.equals(Operators.MULTIPLY.getOperator()) || op.equals(Operators.DIVIDE.getOperator())) {
                                r = calc(stack.elementAt(s + (j - s - 1)), stack.elementAt(s + (j - s)), op);
                                stack.removeElementAt(s + (j - s));
                                stack.setElementAt(r, s + (j - s - 1));
                                operations.removeElementAt(j);
                                f--; // Adjust end index
                                hasPriorityOps = true;
                                break;
                            }
                        }
                    }

                    // Then handle + and - inside brackets
                    for (int j = s + 1; j < f; j++) {
                        r = calc(stack.elementAt(s), stack.elementAt(s + 1), operations.elementAt(s + 1));
                        stack.removeElementAt(s + 1);
                        stack.setElementAt(r, s);
                        operations.removeElementAt(s + 1);
                        f--; // Adjust end index
                        break; // Restart the loop
                    }
                }

                // Remove the brackets themselves
                operations.removeElementAt(f); // Remove right bracket
                operations.removeElementAt(s); // Remove left bracket
            }

            // Handle operator priority: * and / first, then + and -
            boolean hasPriorityOps = true;
            while (hasPriorityOps && operations.size() > 0) {
                hasPriorityOps = false;
                for (int i = 0; i < operations.size(); i++) {
                    String op = operations.elementAt(i);
                    if (op.equals(Operators.MULTIPLY.getOperator()) || op.equals(Operators.DIVIDE.getOperator())) {
                        r = calc(stack.elementAt(i), stack.elementAt(i+1), op);
                        stack.removeElementAt(i);
                        stack.removeElementAt(i);
                        operations.removeElementAt(i);
                        stack.insertElementAt(r, i);
                        hasPriorityOps = true;
                        break; // Restart the loop after modification
                    }
                }
            }

            // Then handle remaining + and - operations
            for (int i = 0; i < operations.size(); i++){
                r = calc(stack.elementAt(i), stack.elementAt(i+1), operations.elementAt(i));
                stack.removeElementAt(i);
                stack.removeElementAt(i);
                operations.removeElementAt(i);
                stack.insertElementAt(r, i);
                i--; // Adjust index after removal
            }
        }
        return stack.pop();
    }

    private String[] evalInput(String input){
        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+\\.?\\d*|[-+*/()]");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens.toArray(new String[0]);
    }

    private Stack<Double> getNums(String input) {
        Stack<Double> stack = new Stack<>();
        for (String el : evalInput(input)) {
            el = el.trim();
            if (checkIfNumber(el)) {
                stack.push(Double.valueOf(el));
            }
        }
        return stack;
    }

    private boolean checkIfNumber(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("^[+-]?\\d+(\\.\\d+)?$");
    }

    private Stack<String> getOperations(String input) {
        Stack<String> op_list = new Stack<>();
        for (String el : evalInput(input)) {
            el = el.trim();
            if (!checkIfNumber(el)) {
                op_list.add(el);
            }
        }
        return op_list;
    }

    private boolean checkIfOperations(Stack<String> list){
        return !list.isEmpty();
    }

    private double calc(double num1, double num2, String op){
        if (op.equals(Operators.MULTIPLY.getOperator())) return MathOp.mul(num1, num2);
        if (op.equals(Operators.DIVIDE.getOperator())) return MathOp.div(num1, num2);
        if (op.equals(Operators.PLUS.getOperator())) return MathOp.add(num1, num2);
        if (op.equals(Operators.MINUS.getOperator())) return MathOp.sub(num1, num2);
        return 0;
    }
}
