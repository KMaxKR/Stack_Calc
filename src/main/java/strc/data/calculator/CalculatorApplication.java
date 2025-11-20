package strc.data.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import strc.data.calculator.service.CalcService;

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);

        CalcService calcService = new CalcService();
        double r = calcService.calculate("(8 - 3) * 4 + 10 / 2");
        System.out.println(r);
        //System.out.println(calcService.calculate("(11 + 18) * 20 - 2"));
	}

    //TODO
    // Use the stack to evaluate arithmetic expressions.
    // The program will be tested with compound expressions with multiple operators and parentheses.
    // For simplicity assume that the operands are integers, and the operators are of four types:
    //  +, -, *, /.
    // Catch all the errors that you can find.
}
