package strc.data.calculator.service;

import org.springframework.stereotype.Component;

@Component
public interface MathOp {
    static double add(double a, double b) {
        return a + b;
    }
    static double sub(double a, double b) {
        return a - b;
    }
    static double mul(double a, double b) {
        return a * b;
    }
    static double div(double a, double b) {
        return a / b;
    }
}
