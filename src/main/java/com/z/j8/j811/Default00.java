package com.z.j8.j811;

class Default00 {

    public static void main(String[] args) {
        A a = () -> 10;
        System.out.println(a.size());
        System.out.println(a.isEmpty());
    }

//    private static interface A {} // no-need static
    private interface A {

        int size();

        default boolean isEmpty() {
            return size() == 0;
        }
    }
}
