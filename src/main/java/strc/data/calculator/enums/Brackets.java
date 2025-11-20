package strc.data.calculator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Brackets {
    LEFT_BRACKET("("),
    RIGHT_BRACKET(")");

    public final String Bracket;
}
