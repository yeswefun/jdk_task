package com.z.p1.c02;

import static com.z.util.IO.println;

class TaxCalculatorMain2 {

    /*
        策略设计模式
     */
    public static void main(String[] args) {

        TaxCalculator2 taxCalculator = new TaxCalculator2(10000, 2000);

        CalculatorStrategySimpleImpl simple = new CalculatorStrategySimpleImpl();
        taxCalculator.setCalculatorStrategy(simple);
        double tax1 = taxCalculator.calculate();
        println("tax1: " + tax1);

        CalculatorStrategyComplexImpl complex = new CalculatorStrategyComplexImpl();
        taxCalculator.setCalculatorStrategy(complex);
        double tax2 = taxCalculator.calculate();
        println("tax2: " + tax2);

        taxCalculator.setCalculatorStrategy((salary, bonus) -> salary * 0.2 + bonus * 0.2 + 100);
        double tax3 = taxCalculator.calculate();
        println("tax3: " + tax3);
    }
}
