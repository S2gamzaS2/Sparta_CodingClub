package com.sparta.springprepare.calculator;

public class Calculator {

    public Double operate(double num1, String op, double num2) {
        switch (op) {
            case "*":
                return num1 * num2;
            case "/":
                if(num2 != 0) {
                    return num1 / num2;
                } else return null;
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            default:
                throw new IllegalArgumentException("잘못된 연산자입니다");
        }
    }
}
