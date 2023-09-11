package com.z.j8.j802;

@FunctionalInterface
public interface TripleFunction<X, Y, Z, R> {
    R apply(X x, Y y, Z z);
}
