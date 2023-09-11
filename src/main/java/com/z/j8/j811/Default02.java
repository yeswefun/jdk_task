package com.z.j8.j811;

class Default02 {

    /*
        Priority
            Classese always win
            Otherwise, sub-interface win
            still ambiguouse, must override the method yourself
     */
    public static void main(String[] args) {
        C c = new C();
        c.hello(); //B

        A c2 = new C();
        c2.hello();//B
    }

    private interface A {
        default void hello() {
            System.out.println("=== A#hello");
        }
    }

    private interface B extends A {
        @Override
        default void hello() {
            System.out.println("=== B#hello");
        }
    }

//    private static class C implements A, B {}
//    private static class C implements B, A {}
    private static class C implements A, B {
        // 若 B 不是 A 的 子接口，则 C 必须重写 hello (B.super.hello())
        @Override
        public void hello() {
            System.out.println("=== C#hello");
//            B.super.hello();
//            A.super.hello();
        }
    }
}
