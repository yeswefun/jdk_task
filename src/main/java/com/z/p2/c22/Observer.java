package com.z.p2.c22;

abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        // 向 被观察者 注册 观察者
        subject.attach(this);
    }

    public abstract void update();
}
