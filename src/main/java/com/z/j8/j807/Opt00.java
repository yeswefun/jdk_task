package com.z.j8.j807;

import java.util.Optional;

class Opt00 {

    public static void main(String[] args) {
        String insuranceName = getInsuranceName(null);
        System.out.println(insuranceName);

        Optional.ofNullable(getInsuranceName(null)).ifPresent(System.out::println);
    }

    /*
        public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
            Objects.requireNonNull(mapper);
            if (!isPresent())
                return empty();
            else {
                return Optional.ofNullable(mapper.apply(value));    // differ
            }
        }

        public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
            Objects.requireNonNull(mapper);
            if (!isPresent())
                return empty();
            else {
                return Objects.requireNonNull(mapper.apply(value)); //differ
            }
        }
     */
    private static String getInsuranceName(User user) {
        return Optional.ofNullable(user)    // Optional<User>
//                .map(u -> u.getCar())     // Optional<Optional<Car>>
                .flatMap(u -> u.getCar())   // Optional<Car>
                .flatMap(c -> c.getInsurance()) // Optional<Insurance>
                .map(i -> i.getName())      // Optional<String>
                .orElse("Unknown");
    }
}
