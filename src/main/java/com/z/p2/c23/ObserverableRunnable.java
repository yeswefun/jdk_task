package com.z.p2.c23;

abstract class ObserverableRunnable implements Runnable {

    protected LifecycleListener lifecycleListener;

    public ObserverableRunnable(LifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
    }

    protected void notifyChanged(RunnableEvent event) {
        lifecycleListener.onStateChanged(event);
    }

    public enum RunnableState {
        RUNNING,
        ERROR,
        DONE;
    }

    public static class RunnableEvent {

        private RunnableState state;
        private Throwable cause;
        private Thread thread;

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }

    @Override
    public void run() {
    }
}
