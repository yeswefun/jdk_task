package com.z.p2.c32;

class QueryFromHttp {

    public void execute() {
        Context ctx = ActionContext.getInstance().getContext();
        String secret = query(ctx.getData());
        ctx.setSecret(secret);
    }

    private String query(String data) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "[" + data + "]@" + Thread.currentThread().getId();
    }
}
