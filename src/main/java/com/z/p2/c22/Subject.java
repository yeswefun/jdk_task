package com.z.p2.c22;

import java.util.ArrayList;
import java.util.List;

import static com.z.util.IO.println;

class Subject {

    private List<Observer> observerList = new ArrayList<>();

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (this.state == state) {
            return;
        }
        this.state = state;
        notifyAllObserver();
    }

    //注意重复添加的问题
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    public void notifyAllObserver() {
        println("------------------------------------");
        observerList.stream().forEach(Observer::update);
    }
}
