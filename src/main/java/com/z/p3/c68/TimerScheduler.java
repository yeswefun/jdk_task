package com.z.p3.c68;

import java.util.Timer;
import java.util.TimerTask;

class TimerScheduler {

    /*
        定时任务
            Timer / TimerTask
                源码分析，不建议使用

            crontab, linux
                间隔正确
                crontab -e
                    * * * * * bash /root/test/scripts/cron_run.sh >> /root/test/cron_run.log

                cron_run.log
                    #!/bin/bash
                    echo `date "+%Y-%m-%d %H:%M:%S"`

            quartz
                间隔正确

            SchedulerExecutorService
            cron4j
            Control-M
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            int i = 0;

            @Override
            public void run() {
                System.out.println("i -> " + (++i));
                //当任务耗时超过 period 就会出现问题
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }
}
