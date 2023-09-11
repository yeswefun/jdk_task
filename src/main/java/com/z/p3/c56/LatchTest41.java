package com.z.p3.c56;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LatchTest41 {

    static class TaskGroup implements Watcher {

        private CountDownLatch latch;

        private Event event;

        public TaskGroup(int size, Event event) {
            this.latch = new CountDownLatch(size);
            this.event = event;
        }

        @Override
        public void done(Table table) {
            latch.countDown();
            if (latch.getCount() == 0) {
                System.out.println("------------ event finish: " + event.id);
            }
        }
    }

    static class Event {
        int id;

        public Event(int id) {
            this.id = id;
        }
    }

    interface Watcher {
        void done(Table table);
    }

    static class TaskBatch implements Watcher {

        private CountDownLatch latch;

        private TaskGroup taskGroup;

        public TaskBatch(int size, TaskGroup taskGroup) {
            this.latch = new CountDownLatch(size);
            this.taskGroup = taskGroup;
        }

        @Override
        public void done(Table table) {
            latch.countDown();
            if (latch.getCount() == 0) {
                System.out.println("************ table finish: " + table);
                taskGroup.done(table);
            }
        }
    }

    static class Table {
        int id;
        String tableName;
        long srcRecordCount;
        long dstRecordCount;
        String srcColumnSchema = "...";
        String dstColumnSchema = "***";

        public Table(int id, String tableName, long srcRecordCount) {
            this.id = id;
            this.tableName = tableName;
            this.srcRecordCount = srcRecordCount;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "id=" + id +
                    ", tableName='" + tableName + '\'' +
                    ", srcRecordCount=" + srcRecordCount +
                    ", dstRecordCount=" + dstRecordCount +
                    ", srcColumnSchema='" + srcColumnSchema + '\'' +
                    ", dstColumnSchema='" + dstColumnSchema + '\'' +
                    '}';
        }
    }

    private static List<Table> capture(Event e) {
        List<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table(0, "table#" + e.id + "#" + i, i * 1000));
        }
        return list;
    }

    /*
        更新数据库 10 次
     */
    public static void main(String[] args) {
        Event[] events = {new Event(1), new Event(2)};
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (Event event : events) {
            List<Table> tables = capture(event);
            TaskGroup taskGroup = new TaskGroup(tables.size(), event);
            for (Table table : tables) {
                TaskBatch taskBatch = new TaskBatch(2, taskGroup);
                RecordCountRunnable countRunnable = new RecordCountRunnable(table, taskBatch);
                RecordColumnRunnable columnRunnable = new RecordColumnRunnable(table, taskBatch);
                service.submit(countRunnable);
                service.submit(columnRunnable);
            }
        }
        //service.awaitTermination();
    }

    private static final Random r = new Random();

    static class RecordCountRunnable implements Runnable {

        private final Table table;

        private final TaskBatch taskBatch;

        public RecordCountRunnable(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.dstRecordCount = table.srcRecordCount;
//            System.out.println(depth(table.id) + "table count: " + table.tableName);
            taskBatch.done(table);
        }
    }

    static class RecordColumnRunnable implements Runnable {

        private final Table table;

        private final TaskBatch taskBatch;

        public RecordColumnRunnable(Table table, TaskBatch taskBatch) {
            this.table = table;
            this.taskBatch = taskBatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.dstColumnSchema = table.srcColumnSchema;
//            System.out.println(depth(table.id) + "table column: " + table.tableName);
            taskBatch.done(table);
        }
    }

    private static String depth(int i) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i; j++) {
            sb.append("\t");
        }
        return sb.toString();
    }

}
