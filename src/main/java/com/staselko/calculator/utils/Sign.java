package com.staselko.calculator.utils;

import java.util.stream.Stream;

public enum Sign {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("x"),
    DIVIDE("÷"),
    SQUARED("x²"),
    A_POWER("xª"),
    SQUARE_ROOT("√"),
    ONE_A("1/x"),
    PERCENT ("%"),
    EQUAL("=");

    private final String code;

    Sign(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Sign convert(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(Sign.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static double getResult(double result, double num, Sign sign) {
        switch (sign) {
            case PLUS -> result += num;
            case MINUS -> result -= num;
            case MULTIPLY -> result = result * num;
            case DIVIDE -> {
                if (num != 0d) {
                    result = result / num;
                } else {
                    throw new ArithmeticException();
                }
            }
            case A_POWER -> result = Math.pow(result, num);
            case SQUARED -> result = Math.pow(result, 2);
            case SQUARE_ROOT -> {
                if (num >= 0d) {
                    result = Math.sqrt(result);
                } else {
                    throw new ArithmeticException();
                }
            }
            case ONE_A -> {
                if (num != 0d) {
                    result = 1 / num;
                } else {
                    throw new ArithmeticException();
                }
            }
            case PERCENT -> {}
            case EQUAL -> result = num;
        }
        return result;
    }
}
