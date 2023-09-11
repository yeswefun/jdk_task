package com.z.p2.c31;

class ExecutionTask implements Runnable {

    private QueryFromDB queryFromDB = new QueryFromDB();

    private QueryFromHttp queryFromHttp = new QueryFromHttp();

    @Override
    public void run() {
        Context ctx = new Context();

        queryFromDB.execute(ctx);
        System.out.println("data: " + ctx.getData());

        queryFromHttp.execute(ctx);
        System.out.println("secret: " + ctx.getSecret());
    }
}
