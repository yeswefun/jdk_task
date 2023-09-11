package com.z.p2.c23;

import java.util.List;
import java.util.Random;

import static com.z.util.IO.println;

class ThreadLifecycleObserver implements LifecycleListener {

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {

        if (ids == null || ids.isEmpty())
            return;

        ids.stream().forEach(id -> new Thread(new ObserverableRunnable(this) {
            @Override
            public void run() {
                try {
                    println("query for id: " + id);
                    notifyChanged(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    Thread.sleep(1000);
                    if (new Random().nextBoolean()) {
                        throw new RuntimeException("*************** true ***************");
                    }
                    notifyChanged(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChanged(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }, id).start());
    }

    /*
        TODO: 存在多个线程共享数据的问题
     */
    @Override
    public void onStateChanged(ObserverableRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            println(event.getThread().getName() + " : " + event.getState());
            if (event.getCause() != null) {
                println("*************************** " + event.getThread().getName() + " failed!");
                event.getCause().printStackTrace();
            }
        }
    }
}
