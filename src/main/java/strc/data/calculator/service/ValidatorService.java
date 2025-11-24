package strc.data.calculator.service;

import org.springframework.stereotype.Service;
import strc.data.calculator.enums.Brackets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidatorService {


    public Stack<Double> getNums(String input) {
        Stack<Double> stack = new Stack<>();
        for (String el : evalInput(input)) {
            el = el.trim();
            if (checkIfNumber(el)) {
                stack.push(Double.valueOf(el));
            }
        }
        return stack;
    }

    public boolean checkIfNumber(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("^[+-]?\\d+(\\.\\d+)?$");
    }

    public Stack<String> getOperations(String input) {
        Stack<String> op_list = new Stack<>();
        for (String el : evalInput(input)) {
            el = el.trim();
            if (!checkIfNumber(el)) {
                op_list.add(el);
            }
        }
        return op_list;
    }

    public boolean checkIfOperations(Stack<String> list){
        return !list.isEmpty();
    }

    public boolean validateInput(String input){
        String[] arr = evalInput(input);
        String last_el = arr[arr.length-1];

        Stack<String> operations = new Stack<>();
        for (String el : getOperations(input)){
            if (el.equals(Brackets.LEFT_BRACKET.getBracket())){
                operations.push(el);
            }
            if (el.equals(Brackets.RIGHT_BRACKET.getBracket()) && !operations.isEmpty()){
                operations.pop();
            }
            if (el.equals(Brackets.RIGHT_BRACKET.getBracket()) && operations.isEmpty()) {
                return false;
            }
        }

        return  checkIfNumber(last_el) || last_el.equals(Brackets.RIGHT_BRACKET.getBracket()) && operations.isEmpty();
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
}
