package com.z.j8.j806;

import java.util.Optional;

class Optional03 {

    public static void main(String[] args) {
        //1
        Optional<Insurance> optional = Optional.empty();

        //2
        Optional<Insurance> optional2 = Optional.of(new Insurance());

        Optional<String> s = optional2.map(o -> o.getName());
        System.out.println(s.orElse("Unknown"));

        System.out.println(s.isPresent());
        s.ifPresent(System.out::println);
    }
}
