package com.z.p1.c11;

class Demo20_fix {

    private interface TestInterface {
        void test() throws Exception;
    }

    public static void main(String[] args) {
        testXxx(new TestInterface() {
            @Override
            public void test() throws Exception {
                throw new Exception("error");
            }
        });
    }

    public static void testXxx(TestInterface t) {

    }
}
