package com.z.p1.c02;

import static com.z.util.IO.println;

class TaxCalculatorMain {

    public static void main(String[] args) {
        TaxCalculator taxCalculator = new TaxCalculator(10000, 2000) {
            @Override
            protected double calcTax() {
                return getSalary() * 0.1 + getBonus() * 0.2;
            }
        };
        double tax = taxCalculator.calculate();
        println("tax: " + tax);
    }
}
