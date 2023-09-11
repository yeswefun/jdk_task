package com.z.p3.c53;

import java.util.concurrent.atomic.AtomicReference;

class ReferenceTest00 {

    static class Simple {

        private String name;
        private int score;

        public Simple(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Simple{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }

    /*
        让 自定义对象 具备 原子类型特性
            AtomicReference
     */
    public static void main(String[] args) {
        Simple java = new Simple("java", 75);
        Simple kotlin = new Simple("kotlin", 80);

        System.out.println("****************** AtomicReference");
        AtomicReference<Simple> ar = new AtomicReference<>(java);
        System.out.println(ar.get());

        System.out.println("****************** compareAndSet -> true");
        System.out.println(ar.compareAndSet(java, kotlin));
        System.out.println(ar.get());

        System.out.println("****************** compareAndSet -> false");
        System.out.println(ar.compareAndSet(java, java));
        System.out.println(ar.get());
    }
}
