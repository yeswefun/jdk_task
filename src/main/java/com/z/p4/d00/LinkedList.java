package com.z.p4.d00;

class LinkedList<E> {

    private Node<E> first;

    private int size;

    public LinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E elem) {
        Node<E> newNode = new Node<>(elem);
        newNode.next = first;
        first = newNode;
        ++size;
    }

    public static <E> LinkedList<E> of(E... elems) {
        LinkedList<E> list = new LinkedList<>();
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
        LinkedList<String> list = LinkedList.of("a", "b", "c", "d", "e", "f");
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.isEmpty());

        list.addFirst("----");
        String s = list.removeFirst();
        System.out.println("s: " + s);
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.isEmpty());

        System.out.println("--- list");
        while (!list.isEmpty()) {
            System.out.println(list.removeFirst());
        }

        System.out.println("--- list2");
        LinkedList<String> list2 = new LinkedList<>();
        System.out.println(list2);
        System.out.println(list2.size());
        System.out.println(list2.isEmpty());
    }
}
