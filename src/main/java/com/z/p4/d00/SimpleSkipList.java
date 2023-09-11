package com.z.p4.d00;

import java.util.Random;

class SimpleSkipList {

    private static final byte HEAD_NODE = -1;
    private static final byte DATA_NODE = 0;
    private static final byte TAIL_NODE = 1;

    private static class Node {

        private Integer value;

        private Node up, down, left, right;

        private byte bit;

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value) {
            this(value, DATA_NODE);
        }
    }

    private Node head;
    private Node tail;

    private int size;
    private int height;
    private Random random;

    public SimpleSkipList() {
        this.head = new Node(null, HEAD_NODE);
        this.tail = new Node(null, TAIL_NODE);

        head.right = tail;
        tail.left = head;

        this.random = new Random(System.currentTimeMillis());
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private Node find(Integer elem) {

        return null;
    }
}
