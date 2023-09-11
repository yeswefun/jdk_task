package com.z.p1.c00_phase_1;

/*
Object
    public final native Class<?> getClass();

    public native int hashCode();

    protected native Object clone() throws CloneNotSupportedException;

    public final native void notify();
    public final native void notifyAll();

    public final native void wait(long timeout) throws InterruptedException;


    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0)
            throw new IllegalArgumentException("timeout value is negative");
        if (nanos < 0 || nanos > 999999)
            throw new IllegalArgumentException("nanosecond timeout value out of range");
        if (nanos > 0)
            timeout++;
        wait(timeout);
    }
    public final void wait() throws InterruptedException {
        wait(0);
    }

    public boolean equals(Object obj) {
        return (this == obj);
    }

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    protected void finalize() throws Throwable {}
 */
interface ZObject {
    Object test();
}
