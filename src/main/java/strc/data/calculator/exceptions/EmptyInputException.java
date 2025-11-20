package strc.data.calculator.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyInputException extends RuntimeException{
    public EmptyInputException(String message){
        super(message);
        Logger logger = LoggerFactory.getLogger(EmptyInputException.class);
        logger.error("Error: {}", message);
    }
}
