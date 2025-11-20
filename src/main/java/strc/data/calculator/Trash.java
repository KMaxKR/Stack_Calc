package strc.data.calculator;

import java.util.Map;

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
