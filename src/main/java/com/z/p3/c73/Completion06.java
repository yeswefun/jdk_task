package com.z.p3.c73;

import java.util.concurrent.*;

class Completion06 {

    /*
        ms
            100
            10

        get
            100, 碰巧
            10

        take - 总是能获取最先执行完的任务的结果
            10
            100
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        ExecutorCompletionService<Event> service = new ExecutorCompletionService<>(executorService);

        Event event = new Event(1);
        service.submit(new MyTask(event), event);
        Future<Event> future = service.take();
        System.out.println(future.get().result);
    }

    private static void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class MyTask implements Runnable {

        private final Event event;

        public MyTask(Event event) {
            this.event = event;
        }

        @Override
        public void run() {
            sleep(2);
            event.setResult(event.getEventId() + " -> finished!");
        }
    }

    private static class Event {

        private final int eventId;
        private String result;

        public Event(int eventId) {
            this.eventId = eventId;
        }

        public int getEventId() {
            return eventId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
