package com.z.j8.j801;

import java.util.function.Supplier;

class Lambda_FI03 {

    public static void main(String[] args) {
        Supplier<String> s = String::new;
        System.out.println(s.get().getClass());
    }
}
