package com.z.p2.c27_immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ImmutableObj {

    private final int id;
    private final String name;

    private final List<String> list;

    public ImmutableObj(int id, String name) {
        this.id = id;
        this.name = name;
        list = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /*
        fuck
     */
    public List<String> getList() {
        //return list; // non-safe
        return Collections.unmodifiableList(list);
    }
}
