package strc.data.calculator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Stack;

@Component
public class CalcService {
    private final Logger logger = LoggerFactory.getLogger(CalcService.class);

    public double calculate(String input) {
        if (input == null || input.isEmpty()) {
            logger.error("Input is null or empty");
            return 0;
        }

        Stack<Double> stack = getNums(input);
        LinkedList<Character> operations = getOperations(input);

        for (int i = 0; i < stack.size(); i++){
            if (!operations.isEmpty()){
                System.out.println("Hi");
            }
        }

        //stack.stream().forEach(el -> System.out.print(el + " "));
        return -1;
    }

    private Stack<Double> getNums(String input) {
        String[] list = input.split("(?<=[-+*/(])|(?=[-+*/)])");
        Stack<Double> stack = new Stack<>();
        for (String el : list) {
            el = el.trim();
            if (el.isEmpty()){
                continue;
            }
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

    private LinkedList<Character> getOperations(String input) {
        return null;
    }
}
