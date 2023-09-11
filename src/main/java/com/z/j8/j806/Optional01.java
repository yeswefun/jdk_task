package com.z.j8.j806;

import java.util.Optional;

class Optional01 {

    public static void main(String[] args) {
        //1
        Optional<Insurance> optional = Optional.empty();
//        System.out.println(optional.get()); // No value present

        //2
        Optional<Insurance> optional2 = Optional.of(new Insurance());
        System.out.println(optional2.get());

        //3
        Optional<Insurance> optional3 = Optional.ofNullable(null);
//        optional3.orElse(insurance2).getName();

//        optional3.orElseGet(Insurance::new).getName();
//        optional3.orElseGet(() -> new Insurance()).getName();

//        optional3.orElseThrow(RuntimeException::new).getName();
//        optional3.orElseThrow(() -> new RuntimeException("Not have reference")).getName();

    }
}
