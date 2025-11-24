# Stack Calculator

This project is a Spring Boot application designed to evaluate arithmetic expressions using a stack-based approach. It can parse and compute expressions containing integers, the four basic arithmetic operators (+, -, *, /), and parentheses.

## Features

*   **Arithmetic Evaluation:** Correctly calculates expressions with addition, subtraction, multiplication, and division.
*   **Operator Precedence:** Respects the standard order of operations, evaluating multiplication and division before addition and subtraction.
*   **Parentheses Support:** Handles nested parentheses to correctly group and prioritize sub-expressions.
*   **Input Validation:** Validates input strings to ensure they are well-formed expressions, checking for balanced parentheses and valid token sequences.
*   **Error Handling:** Throws custom exceptions for invalid inputs, such as empty strings or malformed expressions.
*   **RESTful Endpoint:** Includes a simple REST controller to demonstrate the calculator's functionality via a web request.

## How It Works

The calculation logic is primarily handled by `ValidatorService` and `CalcService`.

1.  **Tokenization:** The `ValidatorService` receives the input expression as a string. It uses a regular expression (`-?\\d+\\.?\\d*|[-+*/()]`) to tokenize the string into a list of numbers, operators, and brackets.

2.  **Stack Separation:** These tokens are then separated into two distinct stacks:
    *   A `Stack<Double>` for numeric operands.
    *   A `Stack<String>` for operators (`+`, `-`, `*`, `/`) and brackets (`(`, `)`).

3.  **Evaluation:** The `CalcService` takes these stacks and performs the evaluation:
    *   It iteratively finds and evaluates the innermost pair of parentheses.
    *   Within any scope (parentheses or the main expression), it first resolves all multiplication and division operations from left to right.
    *   It then resolves the remaining addition and subtraction operations.
    *   Once a sub-expression within parentheses is reduced to a single number, the parentheses are removed, and the process repeats until no parentheses are left.
    *   This continues until only one number remains on the stack, which is the final result.

## Getting Started

### Prerequisites
*   Java 21 or later
*   Apache Maven

## Usage

### As a Service
The core logic is encapsulated in the `CalcService`. You can instantiate and use it directly in your own Java code:
```java
import strc.data.calculator.service.CalcService;
import strc.data.calculator.service.ValidatorService;

// Initialize the services
ValidatorService validatorService = new ValidatorService();
CalcService calcService = new CalcService(validatorService);

// Example expressions
double result1 = calcService.calculate("(11 + 18) * 20 - 2");
System.out.println(result1); // Output: 578.0

double result2 = calcService.calculate("100 / ( 2 + 3 ) * 5");
System.out.println(result2); // Output: 100.0

double result3 = calcService.calculate("-5 + 2");
System.out.println(result3); // Output: -3.0
```

### Via REST API
The application exposes a sample endpoint at the root URL.
*   **URL**: `/`
*   **Method**: `GET`
*   **Description**: This endpoint currently calculates the hardcoded expression `-5 + 2`. You can modify `CustomController.java` to accept dynamic input.

**Example Request:**
```sh
curl http://localhost:8080/
```
**Example Response:**
```
-3.0
```

## Error Handling
The application defines custom exceptions to handle invalid input gracefully:
*   `WrongInputException`: Thrown for malformed expressions, such as unbalanced parentheses, an expression ending in an operator, or invalid characters.
*   `EmptyInputException`: Thrown if the input string is `null` or empty.

The `ValidatorService` performs these checks before the calculation begins to ensure the expression is syntactically valid.
