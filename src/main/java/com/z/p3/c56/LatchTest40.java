package com.z.p3.c56;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class LatchTest40 {

    static class Event {

        int id;

        public Event(int id) {
            this.id = id;
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
    }

    private static List<Table> capture(Event e) {
        List<Table> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Table(0, "table#" + e.id + "#" + i, i * 1000));
        }
        return list;
    }

    /*
        更新数据库 40 次
     */
    public static void main(String[] args) throws InterruptedException {
        Event[] events = {new Event(1), new Event(2)};
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (Event event : events) {
            List<Table> tables = capture(event);
            for (Table table : tables) {
                RecordCountRunnable countRunnable = new RecordCountRunnable(table);
                RecordColumnRunnable columnRunnable = new RecordColumnRunnable(table);
                service.submit(countRunnable);
                service.submit(columnRunnable);
            }
        }
        //service.awaitTermination(1, TimeUnit.HOURS);
    }

    private static final Random r = new Random();

    static class RecordCountRunnable implements Runnable {

        private final Table table;

        public RecordCountRunnable(Table table) {
            this.table = table;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.dstRecordCount = table.srcRecordCount;
            System.out.println(depth(table.id) + "table count: " + table.tableName);
        }
    }

    static class RecordColumnRunnable implements Runnable {

        private final Table table;

        public RecordColumnRunnable(Table table) {
            this.table = table;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table.dstColumnSchema = table.srcColumnSchema;
            System.out.println(depth(table.id) + "table column: " + table.tableName);
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
