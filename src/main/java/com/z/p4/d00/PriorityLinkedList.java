package com.z.p4.d00;

class PriorityLinkedList<E extends Comparable<E>> {

    private Node<E> first;

    private int size;

    public PriorityLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //有序
    public void addFirst(E elem) {
        Node<E> newNode = new Node<>(elem);

        Node<E> prev = null;
        Node<E> cur = first;

        while (cur != null && elem.compareTo(cur.value) > 0) {
            prev = cur;
            cur = cur.next;
        }

        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
        newNode.next = cur;

        ++size;
    }

    public static <E extends Comparable<E>> PriorityLinkedList<E> of(E... elems) {
        PriorityLinkedList<E> list = new PriorityLinkedList<>();
        if (elems != null && elems.length != 0) {
            for (E elem : elems) {
                list.addFirst(elem);
            }
        }
        return list;
    }

    public boolean contains(E elem) {
        Node<E> cur = first;
        while (cur != null) {
            if (cur.value == elem) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> node = first;
        first = node.next;
        --size;
        return node.value;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> cur = this.first;
        while (cur != null) {
            sb.append(cur.value).append(",");
            cur = cur.next;
        }
//        sb.deleteCharAt(sb.length() - 1);
//        sb.append("]");
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString();
    }

    private static class Node<E> {

        E value;

        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (value == null) {
                return "null";
            } else {
                return value.toString();
            }
        }
    }

    public static void main(String[] args) {
        PriorityLinkedList<Integer> list = PriorityLinkedList.of(6, 10, 2, 7, 3, 8, 1, 9, 5);
        System.out.println(list);
    }
}
