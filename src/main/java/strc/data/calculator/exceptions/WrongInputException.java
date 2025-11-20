package strc.data.calculator.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WrongInputException extends RuntimeException {
    public WrongInputException(String message){
        super(message);
        Logger logger = LoggerFactory.getLogger(WrongInputException.class);
        logger.error("Error: {}", message);
    }

}
