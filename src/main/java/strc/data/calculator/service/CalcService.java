package strc.data.calculator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import strc.data.calculator.enums.Brackets;
import strc.data.calculator.enums.Operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CalcService {
    private final Logger logger = LoggerFactory.getLogger(CalcService.class);

    public double calculate(String input) {

        if (input == null || input.isEmpty()) {
            logger.info("Input is null or empty");
            return 0;
        }

        Stack<Double> stack = getNums(input);
        Stack<String> operations = getOperations(input);
        System.out.println(Arrays.toString(evalInput(input)));
        System.out.println(stack);
        System.out.println(operations);

        while (stack.size() != 1){
            double r = 0;
            if (operations.contains(Brackets.LEFT_BRACKET.getBracket()) && operations.contains(Brackets.RIGHT_BRACKET.getBracket())){
                int s = operations.indexOf(Brackets.LEFT_BRACKET.getBracket())+1;
                int f = operations.indexOf(Brackets.RIGHT_BRACKET.getBracket());

                for (int i = s; i < f; i++){
                    r = calc(stack.elementAt(i-1), stack.elementAt(i), operations.elementAt(i));
                    stack.removeElementAt(i);
                    stack.insertElementAt(r, i);
                    //System.out.println(r);
                }
                for (int i = s; i < f; i++){
                    stack.removeElementAt(s-1);
                    operations.removeElementAt(s);
                }
                operations.removeElementAt(s);
                operations.removeElementAt(s-1);
//                System.out.println(stack);
//                System.out.println(operations);
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
            break;
        }
        System.out.println(stack);
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
