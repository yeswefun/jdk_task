package com.z.p3.c55;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

class UnsafeFooTest01 {

    static class Guard {

        private int accessAllowed = 1;

        private boolean allow() {
            return 6 == accessAllowed;
        }

        public void work() {
            if (allow()) {
                System.out.println("work allow");
            } else {
                System.out.println("********* not allow");
            }
        }
    }

    /*
        ********* not allow
        work allow
     */
    public static void main(String[] args) throws Exception {
        Guard guard = new Guard();
        guard.work();

        Field field = guard.getClass().getDeclaredField("accessAllowed");
        Unsafe unsafe = UnsafeUtil.getUnsafe();
        unsafe.putInt(guard, unsafe.objectFieldOffset(field), 6);
        guard.work();
    }
}
