package com.z.p1.c02;

@FunctionalInterface
interface CalculatorStrategy {
    double calculate(double salary, double bonus);
}
