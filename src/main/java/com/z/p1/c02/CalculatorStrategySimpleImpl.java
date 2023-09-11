package com.z.p1.c02;

class CalculatorStrategySimpleImpl implements CalculatorStrategy {

    private static final double SALARY_RATE = 0.1;
    private static final double BONUS_RATE = 0.2;

    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
