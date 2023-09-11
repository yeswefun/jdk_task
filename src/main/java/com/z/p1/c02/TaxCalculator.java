package com.z.p1.c02;

class TaxCalculator {

    private final double salary;

    private final double bonus;

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public TaxCalculator(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    protected double calcTax() {
        return 0.0;
    }

    public double calculate() {
        return calcTax();
    }
}
