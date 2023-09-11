package com.z.p2.c27_immutable;

/*
线程安全的类
    多个线程对同一个User对象进行访问不会造成线程安全问题
 */
final class User {

    private final String name;
    private final String address;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name + "@" + address;
    }
}
