package com.z.p1.c02;

class TaxCalculator2 {

    private final double salary;

    private final double bonus;

    private CalculatorStrategy calculatorStrategy;

    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    //public TaxCalculator2(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
    public TaxCalculator2(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    protected double calcTax() {
        return calculatorStrategy.calculate(salary, bonus);
    }

    public double calculate() {
        return calcTax();
    }
}
