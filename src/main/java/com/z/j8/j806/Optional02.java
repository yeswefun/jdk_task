package com.z.j8.j806;

import java.util.Optional;

class Optional02 {

    public static void main(String[] args) {
        //1
        Optional<Insurance> optional = Optional.empty();

        //2
        Optional<Insurance> optional2 = Optional.of(new Insurance());

//        Insurance insurance = optional.filter(i -> i.getName() == null).get(); // No value present

//        Insurance insurance = optional2.filter(i -> i.getName() == null).get();
//        System.out.println(insurance);

        Insurance insurance = optional2.filter(i -> i.getName() != null).get(); // No value present
        System.out.println(insurance);
    }
}
