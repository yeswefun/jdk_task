package com.z.p2.c33_balk;

import static com.z.util.IO.println;

class BalkingData {

    private final String fileName;

    private String content;

    private boolean changed;

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        changed = true; // 新创建的文件也表示发生内容变化
    }

    /*
        change 和 save 必须 成对 出现
     */
    public synchronized void change(String content) {
        this.content = content;
        this.changed = true;
    }

    public synchronized void save() {
        if (!changed) {
            return;
        }
        doSave();
        changed = false;
    }

    private void doSave() {
        println(Thread.currentThread().getName() + " doSave: " + content);
    }
}
