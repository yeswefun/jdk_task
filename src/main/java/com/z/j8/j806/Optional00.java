package com.z.j8.j806;

class Optional00 {

    public static void main(String[] args) {
//        String insuranceName = getInsuranceName(new User());
//        String insuranceName = getInsuranceName2(new User());
        String insuranceName = getInsuranceName3(new User());
        System.out.println(insuranceName);
    }

    private static String getInsuranceName3(User user) {
        String defaultValue = "Unknown";
        if (user == null) {
            return defaultValue;
        }

        Car car = user.getCar();
        if (car == null) {
            return defaultValue;
        }

        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return defaultValue;
        }

        return insurance.getName();
    }

    private static String getInsuranceName2(User user) {
        if (user != null) {
            Car car = user.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    private static String getInsuranceName(User user) {
        return user.getCar().getInsurance().getName();
    }
}
