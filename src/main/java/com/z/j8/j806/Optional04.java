package com.z.j8.j806;

import java.util.Optional;

class Optional04 {

    public static void main(String[] args) {
        String insuranceName = getInsuranceName(null);
        System.out.println(insuranceName);
    }

    private static String getInsuranceName(Insurance insurance) {
//        Optional.of(insurance); // value == null 直接抛出异常
        return Optional.ofNullable(insurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
