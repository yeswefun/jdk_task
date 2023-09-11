package com.z.p2.c32;

class ExecutionTask implements Runnable {

    private QueryFromDB queryFromDB = new QueryFromDB();

    private QueryFromHttp queryFromHttp = new QueryFromHttp();

    @Override
    public void run() {
        Context ctx = ActionContext.getInstance().getContext();

        queryFromDB.execute();
        System.out.println("data: " + ctx.getData());

        queryFromHttp.execute();
        System.out.println("secret: " + ctx.getSecret());
    }
}
