package com.z.p1.c02;

class CalculatorStrategyComplexImpl implements CalculatorStrategy {

    private static final double SALARY_RATE = 0.2;
    private static final double BONUS_RATE = 0.2;
    private static final int EXTRA = 100;

    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE + EXTRA;
    }
}
