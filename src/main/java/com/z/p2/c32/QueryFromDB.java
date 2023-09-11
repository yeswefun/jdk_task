package com.z.p2.c32;

class QueryFromDB {

    public void execute() {
        try {
            Thread.sleep(500);
            String data = "data@" + Thread.currentThread().getId();
            ActionContext.getInstance().getContext().setData(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
