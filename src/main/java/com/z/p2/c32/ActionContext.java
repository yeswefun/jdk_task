package com.z.p2.c32;

final class ActionContext {

    private static final ThreadLocal<Context> tl = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    /*
        序列化及反序列化
     */
    private ActionContext() {
    }

    private static class ContextHolder {
        private static final ActionContext instance = new ActionContext();
    }

    public static ActionContext getInstance() {
        return ContextHolder.instance;
    }

    /*
        每个线程中都有一份 Context
     */
    public Context getContext() {
        return tl.get();
    }
//没有实现 Cloneable，但它的子类可能会实现???
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        throw new CloneNotSupportedException();
//    }
}
