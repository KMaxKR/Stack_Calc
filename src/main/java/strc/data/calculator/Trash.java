package strc.data.calculator;

import strc.data.calculator.enums.Brackets;
import strc.data.calculator.enums.Operators;
import strc.data.calculator.service.MathOp;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class Trash {
    private static final Map<Character, Integer> ORDER = Map.of(
            '+', 1,
            '-', 1,
            '*', 2,
            '/', 2,
            '^', 3,
            '(', 4,
            ')', 4,
            '[', 5,
            ']', 5
    );
}


//String[] list = evalInput(input);

//stack.stream().forEach(el -> System.out.print(el + " "));
//operations.stream().forEach(el -> System.out.print(el + " "));

//if (!operations.isEmpty()){
//            for (int i = 0; i < operations.size(); i++){
//                if (operations.get(i).equals(Brackets.LEFT_BRACKET.name())){
//                    //for ()
//                    if (operations.get(i).equals(Brackets.RIGHT_BRACKET.name())){
//                        break;
//                    }
//                }
//            }
//            for (int i = 0; i < list.length; i++){
//                if (list[i].equals(Brackets.LEFT_BRACKET.getBracket())){
//                    for (int j = i+1; j < list.length; j++){
//                        if (list[i].equals(Brackets.RIGHT_BRACKET.getBracket())){
//                            j = list.length;
//                            break;
//                        }
//                        if (checkIfNumber(list[j])){
//                            stack.push(Double.valueOf(list[j]));
//                        }
//                    }
//                }
//            }
//}else {
//    logger.info("No operation detected!");
//}



//
//private Stack<Double> firstGradeOp(Stack<Double> stack, Stack<String> operations){
//    int i = 0;
//    int j = 0;
//    double r = 0;
//    while (j < operations.size()) {
//        if (operations.get(j).equals(Operators.MULTIPLY.getOperator())) {
//            double var1 = stack.elementAt(i);
//            double var2 = stack.elementAt(i + 1);
//            r = MathOp.mul(var1, var2);
//            stack.removeElementAt(i + 1);
//            stack.removeElementAt(i);
//            stack.insertElementAt(r, i);
//        }
//        if (operations.get(j).equals(Operators.DIVIDE.getOperator())) {
//            double var1 = stack.elementAt(i);
//            double var2 = stack.elementAt(i + 1);
//            r = MathOp.div(var1, var2);
//            stack.removeElementAt(i + 1);
//            stack.removeElementAt(i);
//            stack.insertElementAt(r, i);
//        }
//        if (j < operations.size()) {
//            j++;
//        } else {
//            break;
//        }
//    }
//    System.out.println(r);
//    return stack;
//}

//
//
//public double calculate(String input) {
//
//    if (input == null || input.isEmpty()) {
//        logger.info("Input is null or empty");
//        return 0;
//    }
//
//    Stack<Double> stack = getNums(input);
//    Stack<String> operations = getOperations(input);
//    System.out.println(Arrays.toString(evalInput(input)));
//    System.out.println(stack);
//    System.out.println(operations);
//
//    while (stack.size() != 1){
//        if (operations.contains(Brackets.LEFT_BRACKET.getBracket()) && operations.contains(Brackets.RIGHT_BRACKET.getBracket())){
//            int s = operations.indexOf(Brackets.LEFT_BRACKET.getBracket())+1;
//            int f = operations.indexOf(Brackets.RIGHT_BRACKET.getBracket());
//            double r = 0;
//            for (int i = s; i < f; i++){
//                r = calc(stack.elementAt(i-1), stack.elementAt(i), operations.elementAt(i));
//                stack.removeElementAt(i);
//                stack.insertElementAt(r, i);
//                System.out.println(r);
//            }
//            System.out.println("first cycle done");
//            for (int i = s; i < f-1; i++){
//                stack.removeElementAt(i);
//            }
//        }else {
//            break;
//        }
//        break;
//    }
//    System.out.println(stack);
//    return -1;
//}

//
//public double calculate(String input) {
//
//    if (input == null || input.isEmpty()) {
//        logger.info("Input is null or empty");
//        return 0;
//    }
//
//    Stack<Double> stack = getNums(input);
//    Stack<String> operations = getOperations(input);
//    System.out.println(Arrays.toString(evalInput(input)));
//    System.out.println(stack);
//    System.out.println(operations);
//
//    while (stack.size() != 1){
//        double r = 0;
//        if (operations.contains(Brackets.LEFT_BRACKET.getBracket()) && operations.contains(Brackets.RIGHT_BRACKET.getBracket())){
//            int s = operations.indexOf(Brackets.LEFT_BRACKET.getBracket())+1;
//            int f = operations.indexOf(Brackets.RIGHT_BRACKET.getBracket());
//
//            for (int i = s; i < f; i++){
//                r = calc(stack.elementAt(i-1), stack.elementAt(i), operations.elementAt(i));
//                stack.removeElementAt(i);
//                stack.insertElementAt(r, i);
//                //System.out.println(r);
//            }
//            for (int i = s; i < f; i++){
//                stack.removeElementAt(s-1);
//                operations.removeElementAt(s);
//            }
//            operations.removeElementAt(s);
//            operations.removeElementAt(s-1);
//            System.out.println(stack);
//            System.out.println(operations);
//        }
//        for (int i = 0; i < operations.size(); i++){
//            r = calc(stack.elementAt(i), stack.elementAt(i+1), operations.elementAt(i));
//            stack.removeElementAt(i);
//            //stack.removeElementAt(i);
//            stack.insertElementAt(r, i+1);
//        }
//        break;
//    }
//    System.out.println(stack);
//    return stack.pop();
//}


//
//
//public double calculate(String input) {
//    if (input == null || input.isEmpty()) {
//        throw new WrongInputException("Input can not be empty");
//    }
//
//    Stack<Double> stack = getNums(input);
//    Stack<String> operations = getOperations(input);
//
//    if (operations.size() - stack.size() > 1 || stack.size() - operations.size() == 0){
//        throw new WrongInputException("Wrong input data");
//    }
//
//    while (stack.size() != 1){
//        double r = 0;
//        if (operations.contains(Brackets.LEFT_BRACKET.getBracket()) && operations.contains(Brackets.RIGHT_BRACKET.getBracket())){
//            int s = operations.indexOf(Brackets.LEFT_BRACKET.getBracket())+1;
//            int f = operations.indexOf(Brackets.RIGHT_BRACKET.getBracket());
//
//            for (int i = s; i < f; i++){
//                r = calc(stack.elementAt(i-1), stack.elementAt(i), operations.elementAt(i));
//                stack.removeElementAt(i);
//                stack.insertElementAt(r, i);
//            }
//            for (int i = s; i < f; i++){
//                stack.removeElementAt(s-1);
//                operations.removeElementAt(s);
//            }
//            operations.removeElementAt(s);
//            operations.removeElementAt(s-1);
//        }
//
//        // Handle operator priority: * and / first, then + and -
//        boolean hasPriorityOps = true;
//        while (hasPriorityOps && operations.size() > 0) {
//            hasPriorityOps = false;
//            for (int i = 0; i < operations.size(); i++) {
//                String op = operations.elementAt(i);
//                if (op.equals(Operators.MULTIPLY.getOperator()) || op.equals(Operators.DIVIDE.getOperator())) {
//                    r = calc(stack.elementAt(i), stack.elementAt(i+1), op);
//                    stack.removeElementAt(i);
//                    stack.removeElementAt(i);
//                    operations.removeElementAt(i);
//                    stack.insertElementAt(r, i);
//                    hasPriorityOps = true;
//                    break; // Restart the loop after modification
//                }
//            }
//        }
//
//        // Then handle remaining + and - operations
//        for (int i = 0; i < operations.size(); i++){
//            r = calc(stack.elementAt(i), stack.elementAt(i+1), operations.elementAt(i));
//            stack.removeElementAt(i);
//            stack.removeElementAt(i);
//            operations.removeElementAt(i);
//            stack.insertElementAt(r, i);
//            i--; // Adjust index after removal
//        }
//        break;
//    }
//    return stack.pop();
//}