package com.z.p2.c31;

class QueryFromDB {

    public void execute(Context ctx) {
        try {
            Thread.sleep(500);
            String data = "data@" + Thread.currentThread().getId();
            ctx.setData(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
